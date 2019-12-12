package cn.edu.lingnan.servlet.project;


import cn.edu.lingnan.dao.ProjectDAO;
import cn.edu.lingnan.dto.Project;
import cn.edu.lingnan.vo.JSONArray;
import cn.edu.lingnan.vo.JSONObject;

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
 * @description 通过项目名字查找所有项目
 */
@WebServlet(urlPatterns = {"/admin/findAllProjectByProjectName", "/admin2/findAllProjectByProjectName", "/user2/findAllProjectByProjectName"})
public class FindAllProjectByProjectNameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String projectName = req.getParameter("projectName");

        ProjectDAO projectDAO = new ProjectDAO();
        List<Project> projectList = projectDAO.findAllProjectByProjectName(projectName);

        // 返回json数据
        resp.setContentType("application/json;charset=UTF-8");
        String jsonString = JSONArray.toJSONString(projectList);
        PrintWriter writer = resp.getWriter();
        writer.write(jsonString);

    }
}
