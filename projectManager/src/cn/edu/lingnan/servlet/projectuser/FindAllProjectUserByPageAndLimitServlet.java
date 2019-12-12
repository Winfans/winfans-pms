package cn.edu.lingnan.servlet.projectuser;

import cn.edu.lingnan.dao.ProjectUserDAO;
import cn.edu.lingnan.dto.ProjectUser;
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
 * @description 分页查找所有项目-用户信息的控制器
 */
@WebServlet(urlPatterns = {"/admin/findAllProjectUserByPageAndLimit", "/admin2/findAllProjectUserByPageAndLimit",
        "/user2/findAllProjectUserByPageAndLimit"})
public class FindAllProjectUserByPageAndLimitServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        // 从数据库中取出所有项目
        ProjectUserDAO projectUserDAO = new ProjectUserDAO();
        List<ProjectUser> projectUserList = projectUserDAO.findAllProjectUserByPageAndLimit(Integer.parseInt(req.getParameter("page")),
                Integer.parseInt(req.getParameter("limit")));

        int total = projectUserDAO.findAllProjectUser().size();

        // 返回json数据
        resp.setContentType("application/json;charset=UTF-8");
        String jsonString = JSONArray.toJSONString(projectUserList, total);
        PrintWriter writer = resp.getWriter();
        writer.write(jsonString);
    }
}
