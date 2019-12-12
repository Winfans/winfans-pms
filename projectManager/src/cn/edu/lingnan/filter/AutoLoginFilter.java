package cn.edu.lingnan.filter;

import cn.edu.lingnan.dao.AutoLoginDAO;
import cn.edu.lingnan.dao.UserDAO;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

/**
 * @author 杨炜帆
 * @description 自动登录过滤器
 */
//@WebFilter(urlPatterns = {"/*"})
public class AutoLoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();

        Cookie[] cookies = request.getCookies();

        String autoLogin = null;
        String userId = null;
        String sessionId = null;
        String isAdmin = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String cookieName = cookie.getName();
                String cookieValue = cookie.getValue();
                if ("AUTOLOGIN".equals(cookieName)) {
                    autoLogin = cookieValue;
                    System.out.println(autoLogin);
                } else if ("USERID".equals(cookieName)) {
                    userId = cookieValue;
                    System.out.println(userId);
                } else if ("SESSIONID".equals(cookieName)) {
                    sessionId = cookieValue;
                    System.out.println(sessionId);
                } else if ("ISADMIN".equals(cookieName)) {
                    isAdmin = cookieValue;
                    System.out.println(isAdmin);
                }
            }

        }


        if ("on".equals(autoLogin) && Objects.nonNull(sessionId) && Objects.nonNull(userId) && Objects.nonNull(isAdmin)) {
            AutoLoginDAO autoLoginDAO = new AutoLoginDAO();
            if (autoLoginDAO.isExistsByUserIdAndSessionId(Integer.parseInt(userId), sessionId)) {
                UserDAO userDAO = new UserDAO();
                Integer isAdminTemp = userDAO.findUserByUserId(Integer.parseInt(userId)).getIsAdmin();
                session.setAttribute("isAdmin", isAdminTemp);
                System.out.println("pass");
            }
        }

        filterChain.doFilter(request, response);

    }
}
