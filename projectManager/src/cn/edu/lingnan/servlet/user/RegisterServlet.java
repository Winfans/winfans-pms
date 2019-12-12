package cn.edu.lingnan.servlet.user;


import cn.edu.lingnan.dao.UserDAO;
import cn.edu.lingnan.dto.User;
import cn.edu.lingnan.utils.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * @author 杨炜帆
 * @description 注册控制器
 */
@WebServlet(urlPatterns = {"/register", "/admin/insertUser", "/admin2/insertUser"})
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String headPortrait = req.getParameter("headPortrait");
        Integer isAdmin = "on".equals(req.getParameter("isAdmin")) ? 1 : 2;
        String sex = req.getParameter("sex");
        String phone = req.getParameter("phone");
        String city = req.getParameter("city");
        String code = "on".equals(req.getParameter("interest[code]")) ? "编程" : "";
        String read = "on".equals(req.getParameter("interest[read]")) ? "读书" : "";
        String sport = "on".equals(req.getParameter("interest[sport]")) ? "运动" : "";

        String[] interests = new String[] {code, read, sport};
        StringBuilder interestBuilder = new StringBuilder();
        for (String interest : interests ) {
            if (!("".equals(interest))) {
                interestBuilder.append(interest).append(",");
            }
        }

        String personalizedSignature = req.getParameter("personalizedSignature");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setHeadPortrait(headPortrait);
        user.setIsAdmin(isAdmin);
        if (sex != null) {
            user.setSex(Integer.parseInt(sex));
        }
        user.setPhone(phone);
        user.setCity(city);

        // 去除字符串最后的逗号
        String interestStr = interestBuilder.toString();
        if (!"".equals(interestStr)) {
            user.setInterest(StringUtil.removeLastChar(interestBuilder.toString()));
        }

        user.setPersonalizedSignature(personalizedSignature);

        UserDAO userDAO = new UserDAO();
        userDAO.insertUser(user);

        if (req.getRequestURI().contains("/register")) {
            resp.sendRedirect(req.getContextPath() + "/index.html");
        } else {
            resp.sendRedirect(req.getContextPath() + "/admin2/findAllUser");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
