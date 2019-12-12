package cn.edu.lingnan.servlet.projectuser;


import cn.edu.lingnan.dao.ProjectDAO;
import cn.edu.lingnan.dao.ProjectUserDAO;
import cn.edu.lingnan.dao.UserDAO;
import cn.edu.lingnan.dto.Project;
import cn.edu.lingnan.dto.ProjectUser;
import cn.edu.lingnan.dto.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 杨炜帆
 * @description 添加项目的控制器
 */
@WebServlet(urlPatterns = {"/admin/insertProjectUser", "/admin2/insertProjectUser"})
public class InsertProjectUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String projectName = req.getParameter("projectName");
        String username = req.getParameter("username");
        String role = req.getParameter("role");

        ProjectUserDAO projectUserDAO = new ProjectUserDAO();
        ProjectUser projectUser = new ProjectUser();
        projectUser.setRole(Integer.parseInt(role));

        UserDAO userDAO = new UserDAO();
        ProjectDAO projectDAO = new ProjectDAO();
        User user = userDAO.findUserByUsername(username);
        Project project = projectDAO.findProjectByProjectName(projectName);

        projectUser.setUserId(user.getUserId());
        projectUser.setProjectId(project.getProjectId());
        projectUserDAO.insertProjectUser(projectUser);

        if (req.getRequestURI().contains("/admin/insertProjectUser")) {
            resp.sendRedirect(req.getContextPath() + "/admin/index.html");
        } else {
            resp.sendRedirect(req.getContextPath() + "/admin2/findAllProjectUser");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        doGet(req, resp);
    }
}
