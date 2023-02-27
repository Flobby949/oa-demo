package top.flobby.oa.service.impl;

import top.flobby.oa.entity.Employee;
import top.flobby.oa.mapper.EmployeeMapper;
import top.flobby.oa.service.EmployeeService;
import top.flobby.oa.service.exception.EmployeeException;

/**
 * @author : Flobby
 * @program : my-oa
 * @description :
 * @create : 2023-02-27 18:07
 **/

public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeMapper employeeMapper = new EmployeeMapper();

    @Override
    public Employee getInfo(Long id) {
        Employee employee = employeeMapper.getEmployeeInfoById(id);
        if (employee == null) {
            throw new EmployeeException("用户信息不存在");
        }
        return employee;
    }
}
