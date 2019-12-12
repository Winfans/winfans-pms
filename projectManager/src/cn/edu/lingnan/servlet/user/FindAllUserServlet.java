package cn.edu.lingnan.servlet.user;

import cn.edu.lingnan.dao.UserDAO;
import cn.edu.lingnan.dto.User;
import cn.edu.lingnan.vo.JSONArray;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author 杨炜帆
 * @description 查找所有用户信息的控制器
 */
@WebServlet(urlPatterns = {"/admin2/findAllUser"})
public class FindAllUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        // 从数据库中取出所有用户
        UserDAO userDAO = new UserDAO();
        List<User> allUser = userDAO.findAllUser();

        // 将所有用户添加到session中
        HttpSession session = req.getSession();
        session.setAttribute("allUser", allUser);

        // 返回页面
        resp.sendRedirect(req.getContextPath() + "/admin2/allUser.jsp");


    }
}
