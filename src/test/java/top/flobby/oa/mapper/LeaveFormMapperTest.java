package top.flobby.oa.mapper;

import org.junit.jupiter.api.Test;
import top.flobby.oa.entity.LeaveForm;
import top.flobby.oa.utils.MybatisUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LeaveFormMapperTest {

    @Test
    void insertLeaveForm() {
        LeaveForm leaveForm = LeaveForm.builder()
                .employeeId(1L)
                .formType(1)
                .startTime(LocalDateTime.of(2023,3,3,9,30))
                .endTime(LocalDateTime.of(2023,4,4,10,30))
                .reason("mapper插入测试,返回主键")
                .createTime(LocalDateTime.now())
                .state("approved")
                .build();
        MybatisUtils.executeUpdate(sqlSession -> {
            LeaveFormMapper mapper = sqlSession.getMapper(LeaveFormMapper.class);
            return mapper.insertLeaveForm(leaveForm);
        });
        System.out.println(leaveForm);
    }

    @Test
    void update() {
        LeaveForm leaveForm = LeaveForm.builder()
                .formId(2L)
                .employeeId(1L)
                .formType(1)
                .startTime(LocalDateTime.of(2023,3,3,9,30))
                .endTime(LocalDateTime.of(2023,4,4,10,30))
                .reason("mapper修改测试")
                .createTime(LocalDateTime.now())
                .state("approved")
                .build();
        MybatisUtils.executeUpdate(sqlSession -> {
            LeaveFormMapper mapper = sqlSession.getMapper(LeaveFormMapper.class);
            return mapper.updateLeaveForm(leaveForm);
        });
    }

    @Test
    void selectById() {
        LeaveForm leaveForm = (LeaveForm) MybatisUtils.executeQuery(sqlSession -> {
            LeaveFormMapper mapper = sqlSession.getMapper(LeaveFormMapper.class);
            return mapper.selectById(2L);
        });
        System.out.println(leaveForm);
    }

    @Test
    void selectByParams() {
        List<Map<String, Object>> process = (List<Map<String, Object>>) MybatisUtils.executeQuery(sqlSession -> {
            LeaveFormMapper mapper = sqlSession.getMapper(LeaveFormMapper.class);
            return mapper.selectByParams("process", 6L);
        });
        process.forEach(item -> item.entrySet().forEach(System.out::println));
    }
}