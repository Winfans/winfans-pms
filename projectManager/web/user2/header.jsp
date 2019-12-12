<%@ page import="cn.edu.lingnan.dto.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<div class="layui-header" style="background-color: #009688;z-index: 999999999">
    <div class="layui-logo" style="background-color: #007d71;color: #fff;">项目管理系统</div>
    <ul class="layui-nav layui-layout-right">
        <li class="layui-nav-item">
            <a href="javascript:;" style="color: #fff;">
                <%
                    User user = (User) session.getAttribute("user");
                    if (user != null) {
                %>
                <img src="<%=user.getHeadPortrait()%>" class="layui-nav-img">
                <%=user.getUsername()%>
                <%
                    }
                %>
            </a>
            <dl class="layui-nav-child">
                <dd><a href="baseInfo.jsp">基本资料</a></dd>
                <dd><a href="updateInfo.jsp">修改资料</a></dd>
            </dl>
        </li>
        <li class="layui-nav-item"><a href="logout" style="color: #fff;">退出</a></li>
    </ul>
</div>
</body>
</html>
