package cn.edu.lingnan.servlet.user;

import cn.edu.lingnan.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 杨炜帆
 * @description 删除用户的控制器
 */
@WebServlet(urlPatterns = {"/admin2/deleteUserByUserId", "/admin/deleteUserByUserId"})
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        UserDAO userDAO = new UserDAO();
        userDAO.deleteUserByUserId(Integer.parseInt(userId));

        if (req.getRequestURI().contains("admin/deleteUserByUserId")) {
            resp.sendRedirect(req.getContextPath() + "/admin/index.html");
        } else {
            resp.sendRedirect(req.getContextPath() + "/admin2/findAllUser");
        }
    }
}
