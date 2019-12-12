package cn.edu.lingnan.servlet;

import cn.edu.lingnan.dao.ProjectDAO;
import cn.edu.lingnan.dao.UserDAO;
import cn.edu.lingnan.dto.Project;
import cn.edu.lingnan.dto.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author 杨炜帆
 * @description 查找所有信息的控制器
 */
@WebServlet(urlPatterns = {"/admin2/index"})
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext context = getServletContext();
        Integer times = (Integer) context.getAttribute("times");
        if (times == null) {
            times = 1;
        } else {
            times = times + 1;
        }

        context.setAttribute("times",times);

        // 从数据库中取出所有用户
        UserDAO userDAO = new UserDAO();
        List<User> allUser = userDAO.findAllUser();

        // 将所有用户添加到session中
        HttpSession session = req.getSession();
        session.setAttribute("allUser", allUser);

        // 从数据库中取出所有项目
        ProjectDAO projectDAO = new ProjectDAO();
        List<Project> allProject = projectDAO.findAllProject();

        // 将所有项目添加到session中
        session.setAttribute("allProject", allProject);

        resp.sendRedirect(req.getContextPath() + "/admin2/index.jsp");
    }

}
