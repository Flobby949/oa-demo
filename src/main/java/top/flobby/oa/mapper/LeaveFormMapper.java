package top.flobby.oa.mapper;

import org.apache.ibatis.annotations.Param;
import top.flobby.oa.entity.LeaveForm;

import java.util.List;
import java.util.Map;

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

    /**
     * 更新离开形式
     * 修改请假表
     *
     * @param leaveForm 请假记录
     * @return {@link Integer}
     */
    Integer updateLeaveForm(LeaveForm leaveForm);

    /**
     * 通过id
     *
     * @param id id
     * @return {@link LeaveForm}
     */
    LeaveForm selectById(Long id);

    /**
     * 查询
     *
     * @param state      状态
     * @param operatorId 操作人id
     * @return {@link List}<{@link Map}<{@link String}, {@link Object}>>
     */
    List<Map<String, Object>> selectByParams(@Param("state") String state, @Param("operatorId") Long operatorId);
}
