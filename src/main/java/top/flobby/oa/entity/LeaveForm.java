package top.flobby.oa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author : Flobby
 * @program : my-oa
 * @description : 请假表
 * @create : 2023-03-03 09:05
 **/

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeaveForm {
    private Long formId;
    private Long employeeId;
    /**
     * 请假类型 1-事假 2-病假 3-工伤假 4-婚假 5-产假 6-丧假
     */
    private Integer formType;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String reason;
    private LocalDateTime createTime;
    /**
     * processing-正在审批 approved-审批已通过 refused-审批被驳回
     */
    private String state;
}
