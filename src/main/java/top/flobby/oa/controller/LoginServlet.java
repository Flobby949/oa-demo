package top.flobby.oa.controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import top.flobby.oa.entity.User;
import top.flobby.oa.service.UserService;
import top.flobby.oa.service.impl.UserServiceImpl;
import top.flobby.oa.utils.ResponseUtils;

import java.io.IOException;

/**
 * @author : Flobby
 * @program : my-oa
 * @description : 登录
 * @create : 2023-02-27 15:59
 **/

@WebServlet("/api/login")
public class LoginServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResponseUtils responseUtils;
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            User user = userService.login(username, password);
            user.setPassword(null);
            user.setSalt(null);
            responseUtils = new ResponseUtils().put("user", user);
        } catch (Exception e) {
            responseUtils = new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
        }
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(responseUtils.toJsonString());
    }
}
