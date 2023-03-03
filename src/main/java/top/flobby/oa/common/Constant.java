package top.flobby.oa.common;

/**
 * @author : JinChenXing
 * @program : my-oa
 * @description :
 * @create : 2023-03-03 12:57
 **/

public interface Constant {

    /**
     * 申请
     */
    String ACTION_APPLY = "apply";
    /**
     * 审批
     */
    String ACTION_AUDIT = "audit";

    /**
     * 同意
     */
    String RESULT_APPROVE = "approved";
    /**
     * 驳回
     */
    String RESULT_REFUSE = "refused";
    /**
     * 处理中
     */
    String RESULT_PROCESS = "processing";

    /**
     * 准备状态
     */
    String STATE_READY = "ready";
    /**
     * 处理状态
     */
    String STATE_PROCESS = "process";
    /**
     * 完成状态
     */
    String STATE_COMPLETE = "complete";
    /**
     * 取消状态
     */
    String STATE_CANCEL = "cancel";
}
