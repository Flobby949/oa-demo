package top.flobby.oa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Flobby
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements Serializable {
    private static final long serialVersionUID = 830991826493187997L;
    /**
    * 用户编号
    */
    private Long userId;
    /**
    * 用户名
    */
    private String username;
    /**
    * 密码
    */
    private String password;
    /**
    * 员工编号
    */
    private Long employeeId;
    /**
    * 盐值
    */
    private Integer salt;
}
