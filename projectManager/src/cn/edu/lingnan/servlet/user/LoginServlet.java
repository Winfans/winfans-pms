package cn.edu.lingnan.servlet.user;

import cn.edu.lingnan.dao.AutoLoginDAO;
import cn.edu.lingnan.dao.UserDAO;
import cn.edu.lingnan.dto.AutoLogin;
import cn.edu.lingnan.dto.User;
import cn.edu.lingnan.enums.RoleEnum;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.UUID;

/**
 * @author 杨炜帆
 * @description 登录控制器
 */
@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1 从页面拿参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String autoLoginParm = req.getParameter("autoLogin");

        // 2 处理数据，可能需要访问数据库
        HttpSession session = req.getSession();

        // 登录返回角色，并添加到session,用于权限控制
        UserDAO userDAO = new UserDAO();
        Integer roleId = userDAO.login(username, password).getRoleId();
        session.setAttribute("isAdmin", roleId);

        // 将用户信息存到session
        User user = userDAO.findUserByUsernameAndPassword(username, password);
        session.setAttribute("user", user);

        // 如果勾上自动登录
        if ("on".equals(autoLoginParm)) {

            req.getCookies();

            Cookie cookie = new Cookie("AUTOLOGIN", "on");
            cookie.setMaxAge(60 * 60 * 24 * 365 * 100);
            cookie.setPath(req.getContextPath());
            resp.addCookie(cookie);

            // 取得当前的session id,存到cookies
            String sessionId = session.getId();
            Cookie cookie2 = new Cookie("SESSIONID", sessionId);
            cookie2.setMaxAge(60 * 60 * 24 * 365 * 100);
            cookie2.setPath(req.getContextPath());
            resp.addCookie(cookie2);

            // 获取用户id
            String userId = user.getUserId().toString();

            // 将用户id存在cookies
            Cookie cookie3 = new Cookie("USERID", userId);
            cookie3.setMaxAge(60 * 60 * 24 * 365 * 100);
            cookie3.setPath(req.getContextPath());
            resp.addCookie(cookie3);

            // 将用户角色存到cookies
            Cookie cookie4 = new Cookie("ISADMIN", roleId.toString());
            cookie4.setMaxAge(60 * 60 * 24 * 365 * 100);
            cookie4.setPath(req.getContextPath());
            resp.addCookie(cookie4);

            // 将自动登录信息存到数据库
            AutoLogin autoLogin = new AutoLogin();
            autoLogin.setUserId(Integer.parseInt(userId));
            autoLogin.setSessionId(sessionId);

            AutoLoginDAO autoLoginDAO = new AutoLoginDAO();
            autoLoginDAO.insertAutoLogin(autoLogin);
        }

        // 3 返回客户端
        // 管理员界面
        if (roleId.equals(RoleEnum.ADMIN.getRoleId())) {
            resp.sendRedirect(req.getContextPath() + "/admin2/index");
        } else if (roleId.equals(RoleEnum.USER.getRoleId())) {
            resp.sendRedirect(req.getContextPath() + "/user2/findAllProject");
        } else {
            resp.sendRedirect(req.getContextPath() + "/index.html?password=error");
        }
    }


}
