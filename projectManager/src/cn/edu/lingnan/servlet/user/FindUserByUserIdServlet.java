package cn.edu.lingnan.servlet.user;


import cn.edu.lingnan.dao.UserDAO;
import cn.edu.lingnan.dto.User;
import cn.edu.lingnan.vo.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 杨炜帆
 * @description 通过用户id查找用户的控制器
 */
@WebServlet(urlPatterns = {"/admin/findUserByUserId"})
public class FindUserByUserIdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userId = req.getParameter("userId");

        UserDAO userDAO = new UserDAO();
        User user = userDAO.findUserByUserId(Integer.parseInt(userId));

        // 返回json数据
        resp.setContentType("application/json;charset=UTF-8");
        String jsonString = JSONObject.toJSONString(user);
        PrintWriter writer = resp.getWriter();
        writer.write(jsonString);
    }
}
