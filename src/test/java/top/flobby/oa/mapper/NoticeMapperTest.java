package top.flobby.oa.mapper;

import org.junit.jupiter.api.Test;
import top.flobby.oa.entity.Notice;
import top.flobby.oa.utils.MybatisUtils;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NoticeMapperTest {

    @Test
    void insert() {
        MybatisUtils.executeUpdate(sqlSession -> {
            NoticeMapper mapper = sqlSession.getMapper(NoticeMapper.class);
            return mapper.insert(Notice.builder()
                            .receiverId(1L)
                            .content("insert")
                            .createTime(LocalDateTime.now())
                    .build());
        });
    }

    @Test
    void getByReceiverId() {
        List<Notice> noticeList = (List<Notice>) MybatisUtils.executeQuery(sqlSession -> {
            NoticeMapper mapper = sqlSession.getMapper(NoticeMapper.class);
            return mapper.getByReceiverId(1L);
        });
        System.out.println(noticeList);
    }
}