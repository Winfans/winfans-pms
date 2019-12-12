package cn.edu.lingnan.servlet.project;

import cn.edu.lingnan.dao.ProjectDAO;
import cn.edu.lingnan.dto.Project;
import cn.edu.lingnan.vo.JSONArray;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author 杨炜帆
 * @description 分页查找所有项目信息的控制器
 */
@WebServlet(urlPatterns = {"/admin/findAllProjectByPageAndLimit", "/admin2/findAllProjectByPageAndLimit",  "/user2/findAllProjectByPageAndLimit"})
public class FindAllProjectByPageAndLimitServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        // 从数据库中取出所有项目
        ProjectDAO projectDAO = new ProjectDAO();
        List<Project> allProjectByPageAndLimitServlet = projectDAO.findAllProjectByPageAndLimit(Integer.parseInt(req.getParameter("page")),
                Integer.parseInt(req.getParameter("limit")));

        int total = projectDAO.findAllProject().size();

        // 返回json数据
        resp.setContentType("application/json;charset=UTF-8");
        String jsonString = JSONArray.toJSONString(allProjectByPageAndLimitServlet, total);
        PrintWriter writer = resp.getWriter();
        writer.write(jsonString);
    }
}
