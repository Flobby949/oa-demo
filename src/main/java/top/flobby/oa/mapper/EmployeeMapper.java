package top.flobby.oa.mapper;

import top.flobby.oa.entity.Employee;
import top.flobby.oa.utils.MybatisUtils;

import java.util.List;
import java.util.Map;

/**
 * @author : JinChenXing
 * @program : my-oa
 * @description :
 * @create : 2023-02-27 17:42
 **/

public interface EmployeeMapper {

    /**
     * 通过id获取员工信息
     *
     * @param id id
     * @return {@link Employee}
     */
    Employee getEmployeeInfoById(Long id);

    /**
     * 得到员工老板
     *
     * @param map 参数
     * @return {@link Employee}
     */
    List<Employee> getEmployeeBoss(Map<String, Object> map);
}
