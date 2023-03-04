package top.flobby.oa.service;

import top.flobby.oa.entity.Notice;

import java.util.List;

/**
 * @author : JinChenXing
 * @program : my-oa
 * @description :
 * @create : 2023-03-04 15:43
 **/

public interface NoticeService {


    /**
     * 发送通知
     *
     * @param receiverId 接收人id
     * @param content    内容
     */
    void sendNotice(Long receiverId, String content);

    /**
     * 通知列表
     *
     * @param employeeId 雇员id
     * @return {@link List}<{@link Notice}>
     */
    List<Notice> noticeList(Long employeeId);
}
