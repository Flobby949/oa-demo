package top.flobby.oa.service;

import org.junit.jupiter.api.Test;
import top.flobby.oa.entity.LeaveForm;
import top.flobby.oa.service.impl.LeaveFormServiceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static top.flobby.oa.common.Constant.*;

class LeaveFormServiceTest {
    LeaveFormService leaveFormService = new LeaveFormServiceImpl();

    @Test
    void add1() {
        LeaveForm leaveForm = LeaveForm.builder()
                .employeeId(1L)
                .formType(1)
                .startTime(LocalDateTime.of(2023, 3, 3, 10, 30))
                .endTime(LocalDateTime.of(2023, 3, 4, 11, 30))
                .reason("总经理请假")
                .createTime(LocalDateTime.now())
                .build();
        leaveFormService.addLeave(leaveForm);
    }

    @Test
    void add2() {
        LeaveForm leaveForm = LeaveForm.builder()
                .employeeId(2L)
                .formType(1)
                .startTime(LocalDateTime.of(2023, 3, 3, 10, 30))
                .endTime(LocalDateTime.of(2023, 3, 4, 11, 30))
                .reason("部门经理请假")
                .createTime(LocalDateTime.now())
                .build();
        leaveFormService.addLeave(leaveForm);
    }

    @Test
    void add3() {
        LeaveForm leaveForm = LeaveForm.builder()
                .employeeId(3L)
                .formType(1)
                .startTime(LocalDateTime.of(2023, 3, 3, 10, 30))
                .endTime(LocalDateTime.of(2023, 3, 4, 11, 30))
                .reason("员工72小时内")
                .createTime(LocalDateTime.now())
                .build();
        leaveFormService.addLeave(leaveForm);
    }

    @Test
    void add4() {
        LeaveForm leaveForm = LeaveForm.builder()
                .employeeId(3L)
                .formType(2)
                .startTime(LocalDateTime.of(2023, 3, 3, 10, 30))
                .endTime(LocalDateTime.of(2023, 4, 4, 11, 30))
                .reason("研发部超过72小时")
                .createTime(LocalDateTime.now())
                .build();
        leaveFormService.addLeave(leaveForm);
    }

    @Test
    void selectList() {
        List<Map<String, Object>> maps = leaveFormService.selectList(STATE_PROCESS, null);
        maps.forEach(map -> {
            map.entrySet().forEach(System.out::println);
            System.out.println();
        });
    }

    @Test
    void audit() {
        leaveFormService.auditLeave(1L, 1L, RESULT_REFUSE, "拒绝");
    }
}