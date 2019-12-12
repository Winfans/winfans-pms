package cn.edu.lingnan.filter;

import cn.edu.lingnan.enums.RoleEnum;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * @author 杨炜帆
 * @description 权限控制过滤器
 */
//@WebFilter(urlPatterns = {"/admin/*", "/admin2/*"})
public class AuthorityFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        // 拿到用户的登陆权限
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        Integer flag = (Integer) session.getAttribute("isAdmin");

        // 根据登陆权限去到相对应的页面
        if (flag != null) {
            if (flag.equals(RoleEnum.ADMIN.getRoleId())) {
                filterChain.doFilter(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/noAuth.html");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/index.html");
        }

    }
}
