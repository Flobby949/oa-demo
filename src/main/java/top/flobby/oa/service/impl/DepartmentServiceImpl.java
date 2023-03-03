package top.flobby.oa.service.impl;

import top.flobby.oa.entity.Department;
import top.flobby.oa.mapper.DepartmentMapper;
import top.flobby.oa.service.DepartmentService;
import top.flobby.oa.utils.MybatisUtils;

/**
 * @author : Flobby
 * @program : my-oa
 * @description :
 * @create : 2023-03-03 14:49
 **/

public class DepartmentServiceImpl implements DepartmentService {

    @Override
    public Department getDepartment(Long id) {
        return (Department) MybatisUtils.executeQuery(sqlSession -> {
            DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
            return mapper.selectById(id);
        });
    }
}
