package top.flobby.oa.service.impl;

import top.flobby.oa.common.LevelEnum;
import top.flobby.oa.entity.Employee;
import top.flobby.oa.mapper.EmployeeMapper;
import top.flobby.oa.service.EmployeeService;
import top.flobby.oa.service.exception.EmployeeException;
import top.flobby.oa.utils.MybatisUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author : Flobby
 * @program : my-oa
 * @description :
 * @create : 2023-02-27 18:07
 **/

public class EmployeeServiceImpl implements EmployeeService{


    @Override
    public Employee getInfo(Long id) {
        return (Employee)MybatisUtils.executeQuery(sqlSession -> {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = mapper.getEmployeeInfoById(id);
            if (employee == null) {
                throw new EmployeeException("用户信息不存在");
            }
            return employee;
        });
    }

    @Override
    public Employee getLeader(Long id) {
        return (Employee)MybatisUtils.executeQuery(sqlSession -> {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = mapper.getEmployeeInfoById(id);
            Integer level = employee.getLevel();
            Employee leader = null;
            Map<String, Object> map = new LinkedHashMap<>();
            // 判断员工级别，获取领导
            if (level < LevelEnum.DEPARTMENT_MANAGER.getLevel()) {
                // 普通员工
                map.put("departmentId", employee.getDepartmentId());
                map.put("level", LevelEnum.DEPARTMENT_MANAGER.getLevel());
                leader = mapper.getEmployeeBoss(map).get(0);
            } else if (LevelEnum.DEPARTMENT_MANAGER.getLevel().equals(level)) {
                // 部门经理
                map.put("level", LevelEnum.BOSS.getLevel());
                leader = mapper.getEmployeeBoss(map).get(0);
            } else if (LevelEnum.BOSS.getLevel().equals(level)){
                // 总经理
                leader = employee;
            }
            return leader;
        });
    }
}
