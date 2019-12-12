package cn.edu.lingnan.servlet.projectuser;


import cn.edu.lingnan.dao.ProjectUserDAO;
import cn.edu.lingnan.dto.ProjectUser;
import cn.edu.lingnan.vo.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author 杨炜帆
 * @description 通过项目名字查找所有项目-用户
 */
@WebServlet(urlPatterns = {"/admin/findAllProjectUserByProjectName", "/admin2/findAllProjectUserByProjectName",
        "/user2/findAllProjectUserByProjectName"})
public class FindAllProjectUserByProjectNameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String projectName = req.getParameter("projectName");

        ProjectUserDAO projectUserDAO = new ProjectUserDAO();
        List<ProjectUser> projectUserList = projectUserDAO.findAllProjectUserByProjectName(projectName);

        // 返回json数据
        resp.setContentType("application/json;charset=UTF-8");
        String jsonString = JSONArray.toJSONString(projectUserList);
        PrintWriter writer = resp.getWriter();
        writer.write(jsonString);

    }
}
