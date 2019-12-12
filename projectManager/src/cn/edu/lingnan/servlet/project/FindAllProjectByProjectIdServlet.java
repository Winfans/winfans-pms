package cn.edu.lingnan.servlet.project;


import cn.edu.lingnan.dao.ProjectDAO;
import cn.edu.lingnan.dao.UserDAO;
import cn.edu.lingnan.dto.Project;
import cn.edu.lingnan.dto.User;
import cn.edu.lingnan.vo.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 杨炜帆
 * @description 通过项目id查找项目的控制器
 */
@WebServlet(urlPatterns = {"/admin/findProjectByProjectId", "/admin2/findProjectByProjectId"})
public class FindAllProjectByProjectIdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String projectId = req.getParameter("projectId");

        ProjectDAO projectDAO = new ProjectDAO();
        Project project = projectDAO.findProjectByProjectId(Integer.parseInt(projectId));

        if (req.getRequestURI().contains("/admin/findProjectByProjectId")) {
            // 返回json数据
            resp.setContentType("application/json;charset=UTF-8");
            String jsonString = JSONObject.toJSONString(project);
            PrintWriter writer = resp.getWriter();
            writer.write(jsonString);
        } else {
            resp.sendRedirect(req.getContextPath() + "/admin2/insertPorject.jsp");
        }
    }
}
