package cn.edu.lingnan.servlet.user;

import cn.edu.lingnan.dao.AutoLoginDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

/**
 * @author 杨炜帆
 * @description 注销控制器
 */
@WebServlet(urlPatterns = {"/admin/logout", "/admin2/logout", "/logout", "/user2/logout"})
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        // 销毁session
        session.invalidate();

        // 获取cookies
        Cookie[] cookies = req.getCookies();

        String userId = null;
        String sessionId = null;

        // 清除cookies
        for (Cookie cookie : cookies) {
            String cookieName = cookie.getName();
            String cookieValue = cookie.getValue();
            if ("AUTOLOGIN".equals(cookieName)) {
                cookie.setMaxAge(0);
                cookie.setPath(req.getContextPath());
                resp.addCookie(cookie);
            } else if ("USERID".equals(cookieName)) {
                userId = cookieValue;
                cookie.setMaxAge(0);
                cookie.setPath(req.getContextPath());
                resp.addCookie(cookie);
            } else if ("SESSIONID".equals(cookieName)) {
                sessionId = cookieValue;
                cookie.setMaxAge(0);
                cookie.setPath(req.getContextPath());
                resp.addCookie(cookie);
            } else if ("ISADMIN".equals(cookieName)) {
                cookie.setMaxAge(0);
                cookie.setPath(req.getContextPath());
                resp.addCookie(cookie);
            }

        }

        // 删除数据库中自动登录的信息
        AutoLoginDAO autoLoginDAO = new AutoLoginDAO();

        // 如果用户id和session id不为空
        if (Objects.nonNull(userId) && Objects.nonNull(sessionId)) {
            autoLoginDAO.deleteByUserIdAndSessionId(Integer.parseInt(userId), sessionId);
        }

        // 重定向页面
        resp.sendRedirect(req.getContextPath() + "/index.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
