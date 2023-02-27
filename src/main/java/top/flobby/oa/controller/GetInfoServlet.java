package top.flobby.oa.controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import top.flobby.oa.entity.Employee;
import top.flobby.oa.service.EmployeeService;
import top.flobby.oa.service.impl.EmployeeServiceImpl;
import top.flobby.oa.utils.ResponseUtils;

import java.io.IOException;

/**
 * @author : Flobby
 * @program : my-oa
 * @description : 获取个人信息
 * @create : 2023-02-27 18:24
 **/

@WebServlet("/api/userInfo/*")
public class GetInfoServlet extends HttpServlet {
    private EmployeeService employeeService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        employeeService = new EmployeeServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        ResponseUtils responseUtils;
        String uri = request.getRequestURI().trim();
        String id = uri.substring(uri.lastIndexOf("/") + 1);
        System.out.println(id);
        try {
            Employee info = employeeService.getInfo(Long.valueOf(id));
            info.setDepartmentId(null);
            info.setLevel(null);
            responseUtils = new ResponseUtils().put("employee", info);
        } catch (Exception e) {
            responseUtils = new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
        }
        response.getWriter().write(responseUtils.toJsonString());
    }
}
