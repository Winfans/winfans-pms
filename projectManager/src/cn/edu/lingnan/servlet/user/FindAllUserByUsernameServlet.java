package cn.edu.lingnan.servlet.user;


import cn.edu.lingnan.dao.UserDAO;
import cn.edu.lingnan.dto.User;
import cn.edu.lingnan.vo.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author 杨炜帆
 * @description 通过用户名查找所有用户
 */
@WebServlet(urlPatterns = {"/admin/findAllUserByUsername", "/admin2/findAllUserByUsername"})
public class FindAllUserByUsernameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");

        UserDAO userDAO = new UserDAO();
        List<User> userList = userDAO.findAllUserByUsername(username);

        // 返回json数据
        resp.setContentType("application/json;charset=UTF-8");
        String jsonString = JSONArray.toJSONString(userList);
        PrintWriter writer = resp.getWriter();
        writer.write(jsonString);

    }
}
