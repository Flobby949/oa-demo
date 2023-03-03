package top.flobby.oa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Flobby
 * @program : my-oa
 * @description :
 * @create : 2023-03-03 13:42
 **/

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    private Long departmentId;
    private String departmentName;
}
