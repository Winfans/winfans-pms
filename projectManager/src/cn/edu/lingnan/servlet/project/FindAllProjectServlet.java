package cn.edu.lingnan.servlet.project;

import cn.edu.lingnan.dao.ProjectDAO;
import cn.edu.lingnan.dao.UserDAO;
import cn.edu.lingnan.dto.Project;
import cn.edu.lingnan.dto.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author 杨炜帆
 * @description 查找所有项目信息的控制器
 */
@WebServlet(urlPatterns = {"/admin2/findAllProject", "/user2/findAllProject"})
public class FindAllProjectServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        // 从数据库中取出所有项目
        ProjectDAO projectDAO = new ProjectDAO();
        List<Project> allProject = projectDAO.findAllProject();

        // 将所有项目添加到session中
        HttpSession session = req.getSession();
        session.setAttribute("allProject", allProject);

        // 返回页面
        if (req.getRequestURI().contains("/admin2/findAllProject")) {
            resp.sendRedirect(req.getContextPath() + "/admin2/allProject.jsp");
        } else if (req.getRequestURI().contains("/user2/findAllProject")) {
            resp.sendRedirect(req.getContextPath() + "/user2/allProject.jsp");
        }
    }
}
