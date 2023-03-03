package top.flobby.oa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author : Flobby
 * @program : my-oa
 * @description : 请假流程
 * @create : 2023-03-03 09:08
 **/

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcessFlow {
    private Long processId;
    private Long formId;
    private Long operatorId;
    /**
     * apply-申请  audit-审批
     */
    private String action;
    /**
     * approved-同意 refused-驳回
     */
    private String result;
    private String reason;
    private LocalDateTime createTime;
    private LocalDateTime auditTime;
    private Integer orderNo;
    /**
     * ready-准备 process-正在处理 complete-处理完成 cancel-取消
     */
    private String state;
    /**
     * 是否最后节点,0-否 1-是
     */
    private Integer isLast;
}
