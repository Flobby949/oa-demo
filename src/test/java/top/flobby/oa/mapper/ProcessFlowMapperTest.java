package top.flobby.oa.mapper;

import org.junit.jupiter.api.Test;
import top.flobby.oa.entity.ProcessFlow;
import top.flobby.oa.utils.MybatisUtils;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProcessFlowMapperTest {

    @Test
    void insertFlow() {
        ProcessFlow processFlow = ProcessFlow.builder()
                .formId(1L)
                .operatorId(1L)
                .action("apply")
                .createTime(LocalDateTime.now())
                .orderNo(0)
                .state("complete")
                .isLast(1)
                .build();
        MybatisUtils.executeUpdate(sqlSession -> {
            ProcessFlowMapper mapper = sqlSession.getMapper(ProcessFlowMapper.class);
            return mapper.insertFlow(processFlow);
        });
        System.out.println(processFlow);
    }

    @Test
    void update() {
        ProcessFlow processFlow = ProcessFlow.builder()
                .processId(1L)
                .formId(1L)
                .operatorId(1L)
                .action("xiugai")
                .createTime(LocalDateTime.now())
                .orderNo(0)
                .state("xiugai")
                .isLast(1)
                .build();
        MybatisUtils.executeUpdate(sqlSession -> {
            ProcessFlowMapper mapper = sqlSession.getMapper(ProcessFlowMapper.class);
            return mapper.update(processFlow);
        });
    }

    @Test
    void select() {
        List<ProcessFlow> processFlows = (List<ProcessFlow>) MybatisUtils.executeQuery(sqlSession -> {
            ProcessFlowMapper mapper = sqlSession.getMapper(ProcessFlowMapper.class);
            return mapper.selectByFormId(3L);
        });
        System.out.println(processFlows);
    }
}