package top.flobby.oa.service;

import top.flobby.oa.entity.LeaveForm;

import java.util.List;
import java.util.Map;

/**
 * @author : JinChenXing
 * @program : my-oa
 * @description :
 * @create : 2023-03-03 09:17
 **/

public interface LeaveFormService {
    /**
     * 普通员工请假
     *
     * @param leaveForm 请假信息
     */
    void addLeave(LeaveForm leaveForm);

    /**
     * 查询列表
     *
     * @param state      状态
     * @param operatorId 操作人id
     * @return {@link List}<{@link Map}<{@link String}, {@link Object}>>
     */
    List<Map<String, Object>> selectList(String state, Long operatorId);

    /**
     * 审批
     *
     * @param formId     表单id
     * @param operatorId 操作人id
     * @param result     结果
     * @param reason     原因
     */
    void auditLeave(Long formId, Long operatorId, String result, String reason);
}
