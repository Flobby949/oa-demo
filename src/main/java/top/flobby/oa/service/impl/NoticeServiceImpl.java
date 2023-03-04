package top.flobby.oa.service.impl;

import top.flobby.oa.entity.Notice;
import top.flobby.oa.mapper.NoticeMapper;
import top.flobby.oa.service.NoticeService;
import top.flobby.oa.utils.MybatisUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author : Flobby
 * @program : my-oa
 * @description :
 * @create : 2023-03-04 15:43
 **/

public class NoticeServiceImpl implements NoticeService {

    @Override
    public void sendNotice(Long receiverId, String content) {
        MybatisUtils.executeUpdate(sqlSession -> {
            NoticeMapper mapper = sqlSession.getMapper(NoticeMapper.class);
            return mapper.insert(Notice.builder()
                    .receiverId(receiverId)
                    .content(content)
                    .createTime(LocalDateTime.now())
                    .build());
        });
    }

    @Override
    public List<Notice> noticeList(Long employeeId) {
        return (List<Notice>) MybatisUtils.executeQuery(sqlSession -> {
            NoticeMapper mapper = sqlSession.getMapper(NoticeMapper.class);
            return mapper.getByReceiverId(employeeId);
        });
    }

}
