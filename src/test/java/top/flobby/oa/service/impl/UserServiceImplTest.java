package top.flobby.oa.service.impl;

import org.junit.jupiter.api.Test;
import top.flobby.oa.entity.User;
import top.flobby.oa.service.UserService;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {
    UserService userService = new UserServiceImpl();

    @Test
    void login() {
        User user = userService.login("m8", "test");
        System.out.println(user);
    }
}