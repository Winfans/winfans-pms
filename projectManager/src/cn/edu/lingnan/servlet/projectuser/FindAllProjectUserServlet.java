package cn.edu.lingnan.servlet.projectuser;

import cn.edu.lingnan.dao.ProjectUserDAO;
import cn.edu.lingnan.dto.ProjectUser;

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
@WebServlet(urlPatterns = {"/admin2/findAllProjectUser","/user2/findAllProjectUser"})
public class FindAllProjectUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        // 从数据库中取出所有用户
        ProjectUserDAO projectUserDAO = new ProjectUserDAO();
        List<ProjectUser> allProjectUser = projectUserDAO.findAllProjectUser();

//        // 将所有用户添加到session中
        HttpSession session = req.getSession();
        session.setAttribute("allProjectUser", allProjectUser);

        // 返回页面
        if (req.getRequestURI().contains("/admin2/findAllProjectUser")) {
            resp.sendRedirect(req.getContextPath() + "/admin2/allTeam.jsp");
        } else if (req.getRequestURI().contains("/user2/findAllProjectUser")) {
            resp.sendRedirect(req.getContextPath() + "/user2/allTeam.jsp");
        }
    }
}
