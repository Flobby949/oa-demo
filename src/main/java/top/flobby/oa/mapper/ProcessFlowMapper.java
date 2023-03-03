package top.flobby.oa.mapper;

import top.flobby.oa.entity.ProcessFlow;

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
}
