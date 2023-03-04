package top.flobby.oa.mapper;

import top.flobby.oa.entity.ProcessFlow;

import java.util.List;

/**
 * @author : JinChenXing
 * @program : my-oa
 * @description :
 * @create : 2023-03-03 09:11
 **/

public interface ProcessFlowMapper {

    /**
     * 插入流
     *
     * @param processFlow 流程
     * @return {@link Integer}
     */
    Integer insertFlow(ProcessFlow processFlow);


    /**
     * 更新
     *
     * @param processFlow 流程
     * @return {@link Integer}
     */
    Integer update(ProcessFlow processFlow);

    /**
     * 根据请假id获取列表
     *
     * @param id id
     * @return {@link List}<{@link ProcessFlow}>
     */
    List<ProcessFlow> selectByFormId(Long id);
}
