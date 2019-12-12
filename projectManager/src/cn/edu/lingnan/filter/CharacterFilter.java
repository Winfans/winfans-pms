package cn.edu.lingnan.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

/**
 *
 * @author 杨炜帆
 * @description 字符编码过滤器
 */
@WebFilter(
        urlPatterns = {"/admin2/*", "/user2/*"},
        initParams = {@WebInitParam(name = "newChar", value = "UTF-8")}
)
public class CharacterFilter implements Filter {

    private String newChar = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        newChar = filterConfig.getInitParameter("newChar");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(newChar);
        servletResponse.setCharacterEncoding(newChar);
        servletResponse.setContentType("text/html;charset=utf-8");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        newChar = null;
    }
}
