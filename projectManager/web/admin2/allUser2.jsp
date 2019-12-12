<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*,cn.edu.lingnan.dto.*" %>
<html>
<head>
    <title>所有用户信息-项目管理系统</title>
</head>
<body>
<h2>查看所有用户信息</h2>
<table>
    <tr>
        <td>编号</td>
        <td>姓名</td>
        <td>注册时间</td>
        <td>权限</td>
    </tr>
    <%
        // 从session中取所有用户
        List<User> allUser = (List<User>) session.getAttribute("allUser");
        Iterator<User> iterator = allUser.iterator();

        User user;
        while (iterator.hasNext()) {
            user = iterator.next();
    %>
    <tr>
        <td><%=user.getUserId() %>
        </td>
        <td><%=user.getUsername() %>
        </td>
        <td><%=user.getCreateTime() %>
        </td>
        <td><%=user.getIsAdmin() == 1 ? "管理员" : "普通用户" %>
        </td>
        <td>
            <a href="updateUser2.jsp?userId=<%=user.getUserId()%>">修改</a>
        </td>
        <td>
            <a href="deleteUserByUserId?userId=<%=user.getUserId()%>">删除</a>
        </td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
