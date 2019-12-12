package cn.edu.lingnan.servlet.user;

import cn.edu.lingnan.dao.UserDAO;
import cn.edu.lingnan.dto.User;
import cn.edu.lingnan.vo.JSONArray;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author 杨炜帆
 * @description 查找所有用户信息的控制器
 */
@WebServlet(urlPatterns = {"/admin/findAllUserByPageAndLimit", "/admin2/findAllUserByPageAndLimit"})
public class FindAllUserByPageAndLimitServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        // 从数据库中取出所有用户
        UserDAO userDAO = new UserDAO();
        List<User> allUserByPageAndLimit = userDAO.findAllUserByPageAndLimit(Integer.parseInt(req.getParameter("page")),
                Integer.parseInt(req.getParameter("limit")));

        int total = userDAO.findAllUser().size();

        // 返回json数据
        resp.setContentType("application/json;charset=UTF-8");
        String jsonString = JSONArray.toJSONString(allUserByPageAndLimit, total);
        PrintWriter writer = resp.getWriter();
        writer.write(jsonString);

    }
}
