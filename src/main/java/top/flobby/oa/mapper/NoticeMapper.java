package top.flobby.oa.mapper;

import top.flobby.oa.entity.Notice;

import java.util.List;

/**
 * @author : Flobby
 * @program : my-oa
 * @description :
 * @create : 2023-03-03 16:15
 **/

public interface NoticeMapper {
    /**
     * 插入
     *
     * @param notice 通知
     * @return {@link Integer}
     */
    Integer insert(Notice notice);

    /**
     * 根据接收人获取通知列表
     *
     * @param id id
     * @return {@link List}<{@link Notice}>
     */
    List<Notice> getByReceiverId(Long id);
}
