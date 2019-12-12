<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*,cn.edu.lingnan.dto.*" %>

<html>
<head>
    <title>修改用户信息-项目管理系统</title>
</head>
<body>
<h2>修改用户信息</h2>
<form action="updateUser" method="post">
    <table>
        <tr>
            <td>编号</td>
            <td>姓名</td>
            <td>注册时间</td>
            <td>权限</td>
        </tr>
        <%
            String userId = request.getParameter("userId");
            // 从session中取所有用户
            List<User> allUser = (List<User>) session.getAttribute("allUser");
            Iterator<User> iterator = allUser.iterator();

            User user;
            while (iterator.hasNext()) {
                user = iterator.next();
                if (user.getUserId() == Integer.parseInt(userId)) {
        %>
        <tr>
            <td>
                <input type="hidden" name="userId" value="<%=user.getUserId()%>">
                <%=user.getUserId() %>
            </td>
            <td>
                <input type="text" name="username" value="<%=user.getUsername()%>">
            </td>
            <td>
                <input type="hidden" name="createTime" value="<%=user.getCreateTime()%>">
                <%=user.getCreateTime()%>
            </td>
            <td>
                <input type="text" name="isAdmin" value="<%=user.getIsAdmin()%>">
            </td>
            <td>
                <input type="submit" value="修改">
            </td>
        </tr>
        <%
                }
            }
        %>
    </table>
</form>
</body>
</html>
