package top.flobby.oa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author : Flobby
 * @program : my-oa
 * @description :
 * @create : 2023-03-03 16:15
 **/

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notice {
    private Long noticeId;
    private Long receiverId;
    private String content;
    private LocalDateTime createTime;
}
