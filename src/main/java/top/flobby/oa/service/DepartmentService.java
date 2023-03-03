package top.flobby.oa.service;

import top.flobby.oa.entity.Department;

/**
 * @author : Flobby
 * @program : my-oa
 * @description :
 * @create : 2023-03-03 14:49
 **/

public interface DepartmentService {
    /**
     * 得到部门
     *
     * @param id id
     * @return {@link Department}
     */
    Department getDepartment(Long id);
}
