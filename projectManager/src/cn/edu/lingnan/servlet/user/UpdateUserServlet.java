package cn.edu.lingnan.servlet.user;

import cn.edu.lingnan.dao.UserDAO;
import cn.edu.lingnan.dto.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author 杨炜帆
 * @description 修改用户信息的控制器
 */
@WebServlet(urlPatterns = {"/admin/updateUser", "/admin2/updateUser", "/admin2/updateInfo", "/user2/updateInfo"})
public class UpdateUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String headPortrait = req.getParameter("headPortrait");
        Integer isAdmin = "on".equals(req.getParameter("isAdmin")) ? 1 : 2;
        String createTime = req.getParameter("createTime");
        String sex = req.getParameter("sex");
        String phone = req.getParameter("phone");
        String city = req.getParameter("city");

        String code = "on".equals(req.getParameter("interest[code]")) ? "编程" : "";
        String read = "on".equals(req.getParameter("interest[read]")) ? "读书" : "";
        String sport = "on".equals(req.getParameter("interest[sport]")) ? "运动" : "";
        String[] interests = new String[]{code, read, sport};
        StringBuilder interestBuilder = new StringBuilder();
        for (String interest : interests) {
            if (!("".equals(interest))) {
                interestBuilder.append(interest).append(",");
            }
        }


        String personalizedSignature = req.getParameter("personalizedSignature");

        User user = new User();
        user.setUserId(Integer.parseInt(userId));
        user.setUsername(username);
        user.setPassword(password);
        user.setHeadPortrait(headPortrait);
        user.setIsAdmin(isAdmin);
        user.setSex(Integer.parseInt(sex));
        user.setPhone(phone);
        user.setCity(city);
        user.setInterest(interestBuilder.toString().substring(0, interestBuilder.length() - 1));
        user.setPersonalizedSignature(personalizedSignature);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            user.setCreateTime(new Timestamp(sdf.parse(createTime).getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        UserDAO userDAO = new UserDAO();
        userDAO.updateUser(user);
        if (req.getRequestURI().contains("/admin/updateUser")) {
            resp.sendRedirect(req.getContextPath() + "/admin/index.html");
        } else if (req.getRequestURI().contains("/admin2/updateUser")) {
            resp.sendRedirect(req.getContextPath() + "/admin2/findAllUser");
        } else if (req.getRequestURI().contains("/admin2/updateInfo")) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/admin2/findAllUser");
        } else if (req.getRequestURI().contains("/user2/updateInfo")) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/user2/findAllProject");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
