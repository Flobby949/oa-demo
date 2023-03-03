package top.flobby.oa.controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import top.flobby.oa.entity.LeaveForm;
import top.flobby.oa.service.LeaveFormService;
import top.flobby.oa.service.exception.LeaveFormException;
import top.flobby.oa.service.impl.LeaveFormServiceImpl;
import top.flobby.oa.utils.ResponseUtils;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author : Flobby
 * @program : my-oa
 * @description :
 * @create : 2023-03-03 13:46
 **/

@WebServlet("/api/leave/*")
public class LeaveServlet extends HttpServlet {
    private LeaveFormService leaveFormService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        leaveFormService = new LeaveFormServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String uri = requestURI.substring(requestURI.lastIndexOf("/") + 1);
        switch (uri) {
            case "create" -> createLeave(request, response);
            case "list" -> leaveList(request, response);
            case "audit" -> auditLeave(request, response);
            default -> System.out.println("请求错误");
        }
    }

    public void createLeave(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String eid = request.getParameter("eid");
        String formType = request.getParameter("formType");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String reason = request.getParameter("reason");

        ResponseUtils responseUtils;
        response.setContentType("application/json;charset=utf-8");

        LeaveForm leaveForm = LeaveForm.builder()
                .employeeId(Long.parseLong(eid))
                .formType(Integer.valueOf(formType))
                .startTime(Instant.ofEpochMilli(Long.parseLong(startTime)).atZone(ZoneOffset.ofHours(8)).toLocalDateTime())
                .endTime(Instant.ofEpochMilli(Long.parseLong(endTime)).atZone(ZoneOffset.ofHours(8)).toLocalDateTime())
                .reason(reason)
                .build();
        try {
            leaveFormService.addLeave(leaveForm);
            responseUtils = new ResponseUtils();
        } catch (LeaveFormException e) {
            responseUtils = new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
        }
        response.getWriter().write(responseUtils.toJsonString());
    }

    public void leaveList(HttpServletRequest request, HttpServletResponse response) {

    }

    public void auditLeave(HttpServletRequest request, HttpServletResponse response) {

    }
}
