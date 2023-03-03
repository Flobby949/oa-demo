package top.flobby.oa.mapper;

import top.flobby.oa.entity.LeaveForm;

/**
 * @author : JinChenXing
 * @program : my-oa
 * @description :
 * @create : 2023-03-03 09:12
 **/

public interface LeaveFormMapper {

    /**
     * 插入请假记录
     *
     * @param leaveForm 请假记录
     * @return {@link LeaveForm}
     */
    Integer insertLeaveForm(LeaveForm leaveForm);
}
