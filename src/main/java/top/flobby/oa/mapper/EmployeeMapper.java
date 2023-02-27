package top.flobby.oa.mapper;

import top.flobby.oa.entity.Employee;
import top.flobby.oa.utils.MybatisUtils;

/**
 * @author : JinChenXing
 * @program : my-oa
 * @description :
 * @create : 2023-02-27 17:42
 **/

public class EmployeeMapper {

    public Employee getEmployeeInfoById(Long id) {
        return (Employee) MybatisUtils.executeQuery(sqlSession ->
                sqlSession.selectOne("top.flobby.oa.mapper.EmployeeMapper.getEmployeeInfoById", id));
    }
}
