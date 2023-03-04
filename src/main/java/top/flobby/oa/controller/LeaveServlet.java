package top.flobby.oa.controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import top.flobby.oa.entity.LeaveForm;
import top.flobby.oa.service.LeaveFormService;
import top.flobby.oa.service.exception.LeaveFormException;
import top.flobby.oa.service.impl.LeaveFormServiceImpl;
import top.flobby.oa.utils.ResponseUtils;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;

import static top.flobby.oa.common.Constant.STATE_PROCESS;

/**
 * @author : Flobby
 * @program : my-oa
 * @description :
 * @create : 2023-03-03 13:46
 **/

@Slf4j
@WebServlet("/api/leave/*")
public class LeaveServlet extends HttpServlet {
    private LeaveFormService leaveFormService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        leaveFormService = new LeaveFormServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        String requestURI = request.getRequestURI();
        String uri = requestURI.substring(requestURI.lastIndexOf("/") + 1);
        switch (uri) {
            case "create" -> createLeave(request, response);
            case "list" -> leaveList(request, response);
            case "audit" -> auditLeave(request, response);
            default -> response.getWriter().write("请求失败");
        }
    }

    public void createLeave(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String eid = request.getParameter("eid");
        String formType = request.getParameter("formType");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String reason = request.getParameter("reason");

        ResponseUtils responseUtils;

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

    public void leaveList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResponseUtils responseUtils;

        String eid = request.getParameter("eid");
        log.info(eid);
        try {
            List<Map<String, Object>> list = leaveFormService.selectList(STATE_PROCESS, Long.valueOf(eid));
            responseUtils = new ResponseUtils().put("list", list);
        } catch (Exception e) {
            responseUtils = new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
        }
        response.getWriter().write(responseUtils.toJsonString());
    }

    public void auditLeave(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResponseUtils responseUtils;
        String operatorId = request.getParameter("eid");
        String formId = request.getParameter("formId");
        String result = request.getParameter("result");
        String reason = request.getParameter("reason");

        try {
            leaveFormService.auditLeave(Long.parseLong(formId), Long.parseLong(operatorId), result, reason);
            responseUtils = new ResponseUtils();
        } catch (Exception e) {
            responseUtils = new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
        }
        response.getWriter().write(responseUtils.toJsonString());
    }
}
