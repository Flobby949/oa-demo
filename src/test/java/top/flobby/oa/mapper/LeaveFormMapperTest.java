package top.flobby.oa.mapper;

import org.junit.jupiter.api.Test;
import top.flobby.oa.entity.LeaveForm;
import top.flobby.oa.utils.MybatisUtils;

import java.time.LocalDateTime;

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
}