package top.flobby.oa.mapper;

import top.flobby.oa.entity.Department;

/**
 * @author : Flobby
 * @program : my-oa
 * @description :
 * @create : 2023-03-03 13:43
 **/

public interface DepartmentMapper {
    /**
     * 根据id查询
     *
     * @param id id
     * @return {@link Department}
     */
    Department selectById(Long id);
}
