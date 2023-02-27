package top.flobby.oa.service;

import top.flobby.oa.entity.User;

/**
 * @author : JinChenXing
 * @program : my-oa
 * @description :
 * @create : 2023-02-27 14:50
 **/

public interface UserService {

    /**
     * 登录
     * @param username username
     * @param password password
     * @return User
     */
    User login(String username, String password);
}
