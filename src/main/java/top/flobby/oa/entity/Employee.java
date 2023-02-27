package top.flobby.oa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Flobby
 * @program : my-oa
 * @description : 用户信息
 * @create : 2023-02-27 17:37
 **/

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private Long employeeId;
    private String name;
    private Long departmentId;
    private String title;
    private Integer level;
    private String avatar;
}
