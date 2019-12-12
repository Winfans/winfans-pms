package cn.edu.lingnan.servlet.projectuser;

import cn.edu.lingnan.dao.ProjectUserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 杨炜帆
 * @description 删除用户的控制器
 */
@WebServlet(urlPatterns = {"/admin2/batchDeleteProjectUser", "/admin/batchDeleteProjectUser"})
public class BatchDeleteProjectUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String[] userIds = req.getParameter("userIdList").split(",");
        String[] projectIds = req.getParameter("projectIdList").split(",");

        ProjectUserDAO projectUserDAO = new ProjectUserDAO();

        for (int i = 0; i < userIds.length; i++) {
            projectUserDAO.deleteProjectUserByProjectIdAndUserId(Integer.parseInt(projectIds[i]),
                    Integer.parseInt(userIds[i]));
        }

        if (req.getRequestURI().contains("admin/batchDeleteProjectUser")) {
            resp.sendRedirect(req.getContextPath() + "/admin/index.html");
        } else {
            resp.sendRedirect(req.getContextPath() + "/admin2/findAllProjectUser");
        }
    }
}
