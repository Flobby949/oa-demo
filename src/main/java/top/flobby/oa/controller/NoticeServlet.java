package top.flobby.oa.controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import top.flobby.oa.entity.Notice;
import top.flobby.oa.service.NoticeService;
import top.flobby.oa.service.impl.NoticeServiceImpl;
import top.flobby.oa.utils.ResponseUtils;

import java.io.IOException;
import java.util.List;

/**
 * @author : Flobby
 * @program : my-oa
 * @description :
 * @create : 2023-03-04 17:10
 **/

@WebServlet("/api/notice")
public class NoticeServlet extends HttpServlet {

    private NoticeService noticeService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        noticeService = new NoticeServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");
        ResponseUtils responseUtils;
        try {
            String eid = req.getParameter("eid");
            List<Notice> noticeList = noticeService.noticeList(Long.parseLong(eid));
            responseUtils = new ResponseUtils().put("list", noticeList);
        } catch (Exception e) {
            responseUtils = new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
        }
        resp.getWriter().write(responseUtils.toJsonString());
    }
}
