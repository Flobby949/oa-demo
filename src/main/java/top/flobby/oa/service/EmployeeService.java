package top.flobby.oa.service;

import top.flobby.oa.entity.Employee;

/**
 * @author : JinChenXing
 * @program : my-oa
 * @description :
 * @create : 2023-02-27 17:43
 **/

public interface EmployeeService {

    /**
     * 得到用户信息
     *
     * @param id id
     * @return {@link Employee}
     */
    Employee getInfo(Long id);
}
