package top.flobby.oa.mapper;

import org.junit.jupiter.api.Test;
import top.flobby.oa.entity.Employee;
import top.flobby.oa.utils.MybatisUtils;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeMapperTest {
    @Test
    void test01() {
        Map<String, Object> map = new HashMap<>();
        map.put("level", 8);
        MybatisUtils.executeQuery(sqlSession -> {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = mapper.getEmployeeBoss(map).get(0);
            System.out.println(employee);
            return null;
        });
    }

}