package cn.edu.lingnan.servlet.project;

import cn.edu.lingnan.dao.ProjectDAO;
import cn.edu.lingnan.dao.UserDAO;
import cn.edu.lingnan.dto.Project;
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
 * @description 修改项目信息的控制器
 */
@WebServlet(urlPatterns = {"/admin/updateProject", "/admin2/updateProject"})
public class UpdateProjectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String projectId = req.getParameter("projectId");
        String projectName = req.getParameter("projectName");
        String content = req.getParameter("content");
        String creator = req.getParameter("creator");
        String leader = req.getParameter("leader");
        String planStartTime = req.getParameter("planStartTime");
        String planEndTime = req.getParameter("planEndTime");
        String createTime = req.getParameter("createTime");
        String totalMoney = req.getParameter("totalMoney");
        String status = req.getParameter("status");

        ProjectDAO projectDAO = new ProjectDAO();
        Project project = new Project();

        project.setProjectId(Integer.parseInt(projectId));
        project.setProjectName(projectName);
        project.setContent(content);
        project.setCreator(creator);
        project.setLeader(leader);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            project.setPlanStartTime(new Timestamp(simpleDateFormat.parse(planStartTime).getTime()));
            project.setPlanEndTime(new Timestamp(simpleDateFormat.parse(planEndTime).getTime()));
            project.setCreateTime(new Timestamp(simpleDateFormat.parse(createTime).getTime()));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        // 计算项目周期
        int planTime = Math.toIntExact((project.getPlanEndTime().getTime() - project.getPlanStartTime().getTime()) / 1000 / 60 / 60 / 24);
        project.setPlanTime(planTime + "天");

        if (!"".equals(totalMoney)) {
            project.setTotalMoney(Integer.parseInt(totalMoney));
        }
        if (!"".equals(status)) {
            project.setStatus(Integer.parseInt(status));
        }

        projectDAO.updateProject(project);

        if (req.getRequestURI().contains("/admin/updateProject")) {
            resp.sendRedirect(req.getContextPath() + "/admin/index.html");
        } else {
            resp.sendRedirect(req.getContextPath() + "/admin2/findAllProject");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
