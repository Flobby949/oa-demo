package top.flobby.oa.service.impl;

import top.flobby.oa.common.LevelEnum;
import top.flobby.oa.entity.Employee;
import top.flobby.oa.entity.LeaveForm;
import top.flobby.oa.entity.ProcessFlow;
import top.flobby.oa.mapper.LeaveFormMapper;
import top.flobby.oa.mapper.ProcessFlowMapper;
import top.flobby.oa.service.EmployeeService;
import top.flobby.oa.service.LeaveFormService;
import top.flobby.oa.service.exception.LeaveFormException;
import top.flobby.oa.utils.DateUtils;
import top.flobby.oa.utils.MybatisUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static top.flobby.oa.common.Constant.*;

/**
 * @author : Flobby
 * @program : my-oa
 * @description :
 * @create : 2023-03-03 09:17
 **/

public class LeaveFormServiceImpl implements LeaveFormService {

    private final EmployeeService employeeService = new EmployeeServiceImpl();


    @Override
    public void addLeave(LeaveForm leaveForm) {
        MybatisUtils.executeUpdate(sqlSession -> {
            LeaveFormMapper leaveFormMapper = sqlSession.getMapper(LeaveFormMapper.class);
            ProcessFlowMapper processFlowMapper = sqlSession.getMapper(ProcessFlowMapper.class);
            // 获取员工以及领导信息
            Employee employee = employeeService.getInfo(leaveForm.getEmployeeId());
            if (LevelEnum.BOSS.getLevel().equals(employee.getLevel())) {
                leaveForm.setState(RESULT_APPROVE);
            } else {
                leaveForm.setState(RESULT_PROCESS);
            }
            Employee leader = employeeService.getLeader(employee.getEmployeeId());
            // 插入请假记录
            leaveForm.setCreateTime(LocalDateTime.now());
            leaveFormMapper.insertLeaveForm(leaveForm);
            // 判断请假时长是否超过72h
            boolean checkHours =
                    DateUtils.checkHours(leaveForm.getStartTime(), leaveForm.getEndTime());
            // 添加申请流程记录
            ProcessFlow applyForm = ProcessFlow.builder()
                    .formId(leaveForm.getFormId())
                    .operatorId(leaveForm.getEmployeeId())
                    .action(ACTION_APPLY)
                    .createTime(LocalDateTime.now())
                    .orderNo(1)
                    .state(STATE_COMPLETE)
                    .isLast(0)
                    .build();
            processFlowMapper.insertFlow(applyForm);
            switch (employee.getLevel()) {
                case 1, 2, 3, 4, 5, 6 -> {
                    ProcessFlow managerFlow = ProcessFlow.builder()
                            .formId(leaveForm.getFormId())
                            .operatorId(leader.getEmployeeId())
                            .action(ACTION_AUDIT)
                            .createTime(LocalDateTime.now())
                            .orderNo(2)
                            .state(STATE_PROCESS)
                            .build();
                    if (checkHours) {
                        // 大于72小时
                        managerFlow.setIsLast(0);
                        processFlowMapper.insertFlow(managerFlow);
                        // 上报总经理
                        ProcessFlow bossFlow = ProcessFlow.builder()
                                .formId(leaveForm.getFormId())
                                .operatorId(employeeService.getLeader(leader.getEmployeeId()).getEmployeeId())
                                .action(ACTION_AUDIT)
                                .createTime(LocalDateTime.now())
                                .orderNo(3)
                                .state(STATE_READY)
                                .isLast(1)
                                .build();
                        processFlowMapper.insertFlow(bossFlow);
                    } else {
                        // 小于72小时
                        managerFlow.setIsLast(1);
                        processFlowMapper.insertFlow(managerFlow);
                    }
                }
                case 7 -> {
                    // 上报总经理
                    ProcessFlow bossFlow = ProcessFlow.builder()
                            .formId(leaveForm.getFormId())
                            .operatorId(employeeService.getLeader(leader.getEmployeeId()).getEmployeeId())
                            .action(ACTION_AUDIT)
                            .createTime(LocalDateTime.now())
                            .orderNo(2)
                            .state(STATE_PROCESS)
                            .isLast(1)
                            .build();
                    processFlowMapper.insertFlow(bossFlow);
                }
                case 8 -> {
                    // 自动通过
                    ProcessFlow bossFlow = ProcessFlow.builder()
                            .formId(leaveForm.getFormId())
                            .operatorId(leaveForm.getEmployeeId())
                            .action(ACTION_AUDIT)
                            .createTime(LocalDateTime.now())
                            .result(RESULT_APPROVE)
                            .reason("自动通过")
                            .auditTime(LocalDateTime.now())
                            .orderNo(2)
                            .state(STATE_COMPLETE)
                            .isLast(1)
                            .build();
                    processFlowMapper.insertFlow(bossFlow);
                }
                default -> {
                    throw new LeaveFormException("没有该等级的员工");
                }
            }
            return null;
        });
    }

    @Override
    public List<Map<String, Object>> selectList(String state, Long operatorId) {
        return (List<Map<String, Object>>) MybatisUtils.executeQuery(sqlSession -> {
            LeaveFormMapper mapper = sqlSession.getMapper(LeaveFormMapper.class);
            return mapper.selectByParams(state, operatorId);
        });
    }

    @Override
    public void auditLeave(Long formId, Long operatorId, String result, String reason) {
        MybatisUtils.executeUpdate(sqlSession -> {
            // 无论结果，修改状态为complete
            ProcessFlowMapper processFlowMapper = sqlSession.getMapper(ProcessFlowMapper.class);
            List<ProcessFlow> flowList = processFlowMapper.selectByFormId(formId);
            if (flowList.isEmpty()) {
                throw new LeaveFormException("无效流程");
            }

            // 获取当前任务流程对象
            List<ProcessFlow> processList = flowList.stream().filter(p -> Objects.equals(p.getOperatorId(), operatorId) && STATE_PROCESS.equals(p.getState())).toList();

            ProcessFlow processFlow;

            if (processList.isEmpty()) {
                throw new LeaveFormException("未找到待处理任务节点");
            } else {
                // 获取待处理第一条任务
                processFlow = processList.get(0);
                processFlow.setState(STATE_COMPLETE);
                processFlow.setResult(result);
                processFlow.setReason(reason);
                processFlow.setAuditTime(LocalDateTime.now());
                processFlowMapper.update(processFlow);
            }

            LeaveFormMapper leaveFormMapper = sqlSession.getMapper(LeaveFormMapper.class);
            LeaveForm leaveForm = leaveFormMapper.selectById(formId);

            // 如果当前是最后一个流程，更新请假单状态
            if (processFlow.getIsLast() == 1) {
                leaveForm.setState(result);
                leaveFormMapper.updateLeaveForm(leaveForm);
            } else {
                // 后续任务节点
                List<ProcessFlow> readyList = flowList.stream().filter(p -> STATE_READY.equals(p.getState())).toList();
                if (RESULT_APPROVE.equals(result)) {
                    ProcessFlow readyFlow = readyList.get(0);
                    readyFlow.setState(STATE_PROCESS);
                    processFlowMapper.update(readyFlow);
                } else if (RESULT_REFUSE.equals(result)) {
                    // 如果不是最后一个节点，且被驳回，后续流程全部取消
                    for (ProcessFlow flow : readyList) {
                        flow.setState(STATE_CANCEL);
                        processFlowMapper.update(flow);
                    }
                    leaveForm.setState(RESULT_REFUSE);
                    leaveFormMapper.updateLeaveForm(leaveForm);
                }
            }
            return null;
        });
    }
}
