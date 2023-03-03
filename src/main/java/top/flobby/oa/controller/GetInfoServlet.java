package top.flobby.oa.controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import top.flobby.oa.entity.Department;
import top.flobby.oa.entity.Employee;
import top.flobby.oa.entity.Node;
import top.flobby.oa.service.DepartmentService;
import top.flobby.oa.service.EmployeeService;
import top.flobby.oa.service.NodeService;
import top.flobby.oa.service.impl.DepartmentServiceImpl;
import top.flobby.oa.service.impl.EmployeeServiceImpl;
import top.flobby.oa.service.impl.NodeServiceImpl;
import top.flobby.oa.utils.ResponseUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : Flobby
 * @program : my-oa
 * @description : 获取个人信息
 * @create : 2023-02-27 18:24
 **/

@WebServlet("/api/index")
public class GetInfoServlet extends HttpServlet {
    private EmployeeService employeeService;
    private NodeService nodeService;
    private DepartmentService departmentService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        employeeService = new EmployeeServiceImpl();
        nodeService = new NodeServiceImpl();
        departmentService = new DepartmentServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        ResponseUtils responseUtils;
        String uid = request.getParameter("uid");
        String eid = request.getParameter("eid");
        try {
            Employee info = employeeService.getInfo(Long.parseLong(uid));
            List<Node> nodeList = nodeService.getNodeList(Long.parseLong(eid));
            Department department = departmentService.getDepartment(info.getDepartmentId());
            List<Map<String, Object>> menuList = new ArrayList<>();
            Map<String, Object> module = null;
            for (Node node : nodeList) {
                if (node.getParentId() == null) {
                    module = new LinkedHashMap<>();
                    module.put("node", node);
                    module.put("children", new ArrayList<Node>());
                    menuList.add(module);
                } else {
                    List<Node> children = (List<Node>) module.get("children");
                    children.add(node);
                }
            }
            responseUtils = new ResponseUtils().put("employee", info).put("nodeList", menuList).put("department", department);
        } catch (Exception e) {
            responseUtils = new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
        }
        response.getWriter().write(responseUtils.toJsonString());
    }
}
