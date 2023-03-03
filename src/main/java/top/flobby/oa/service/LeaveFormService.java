package top.flobby.oa.service;

import top.flobby.oa.entity.LeaveForm;

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
}
