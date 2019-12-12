package cn.edu.lingnan.servlet.project;

import cn.edu.lingnan.dao.ProjectDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 杨炜帆
 * @description 删除项目的控制器
 */
@WebServlet(urlPatterns = {"/admin/deleteProjectByProjectId", "/admin2/deleteProjectByProjectId"})
public class DeleteProjectByProjectIdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String projectId = req.getParameter("projectId");

        ProjectDAO projectDAO = new ProjectDAO();
        projectDAO.deleteProjectByProjectId(Integer.parseInt(projectId));

        if (req.getRequestURI().contains("/admin/deleteProjectByProjectId")) {
            resp.sendRedirect(req.getContextPath() + "/admin/index.html");
        } else {
            resp.sendRedirect(req.getContextPath() + "/admin2/findAllProject");
        }

    }
}
