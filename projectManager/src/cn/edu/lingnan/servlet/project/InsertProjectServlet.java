package cn.edu.lingnan.servlet.project;


import cn.edu.lingnan.dao.ProjectDAO;
import cn.edu.lingnan.dao.UserDAO;
import cn.edu.lingnan.dto.Project;
import cn.edu.lingnan.dto.User;
import cn.edu.lingnan.utils.ObjectUtil;

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
 * @description 添加项目的控制器
 */
@WebServlet(urlPatterns = {"/admin/insertProject", "/admin2/insertProject"})
public class InsertProjectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String projectName = req.getParameter("projectName");
        String content = req.getParameter("content");
        String totalMoney = req.getParameter("totalMoney");
        String leader = req.getParameter("leader");
        String planStartTime = req.getParameter("planStartTime");
        String planEndTime = req.getParameter("planEndTime");
        String status = req.getParameter("status");

        HttpSession session = req.getSession();
        Integer userId = ((User) session.getAttribute("user")).getUserId();

        ProjectDAO projectDAO = new ProjectDAO();
        Project project = new Project();

        project.setProjectName(projectName);
        project.setContent(content);
        if (!"".equals(totalMoney)) {
            project.setTotalMoney(Integer.parseInt(totalMoney));
        }
        project.setLeader(leader);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            project.setPlanStartTime(new Timestamp(simpleDateFormat.parse(planStartTime).getTime()));
            project.setPlanEndTime(new Timestamp(simpleDateFormat.parse(planEndTime).getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (!"".equals(status)) {
            project.setStatus(Integer.parseInt(status));
        }

        // 计算项目周期
        int planTime = Math.toIntExact((project.getPlanEndTime().getTime() - project.getPlanStartTime().getTime()) / 1000 / 60 / 60 / 24);
        project.setPlanTime(planTime + "天");

        // 通过用户id拿到用户名，即创建人
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findUserByUserId(userId);
        project.setCreator(user.getUsername());

        projectDAO.insertProject(project);

        if (req.getRequestURI().contains("/admin/insertProject")) {
            resp.sendRedirect(req.getContextPath() + "/admin/index.html");
        } else {
            resp.sendRedirect(req.getContextPath() + "/admin2/findAllProject");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        doGet(req, resp);
    }
}
