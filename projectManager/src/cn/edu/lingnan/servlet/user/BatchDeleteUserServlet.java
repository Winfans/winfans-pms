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
 * @description 批量删除用户的控制器
 */
@WebServlet(urlPatterns = {"/admin2/batchDeleteUser", "/admin/batchDeleteUser"})
public class BatchDeleteUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String[] userIds = req.getParameter("userIdList").split(",");
        UserDAO userDAO = new UserDAO();

        for (String userId : userIds) {
            userDAO.deleteUserByUserId(Integer.parseInt(userId));
        }

        if (req.getRequestURI().contains("admin/batchDeleteUser")) {
            resp.sendRedirect(req.getContextPath() + "/admin/index.html");
        } else {
            resp.sendRedirect(req.getContextPath() + "/admin2/findAllUser");
        }
    }
}
