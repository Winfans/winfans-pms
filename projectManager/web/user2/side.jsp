<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<div class="layui-side">
    <div class="layui-side-scroll">
        <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
        <ul class="layui-nav layui-nav-tree">
            <li class="layui-nav-item layui-nav-itemed system-management">
                <a class="" href="javascript:;">信息查询</a>
                <ul class="layui-nav-child">
                    <li><a href="findAllProject" id="project-management">项目列表</a></li>
                    <li><a href="findAllProjectUser" id="team-management">团队列表</a></li>
                </ul>
            </li>
            <%--<li class="layui-nav-item">--%>
                <%--<a href="javascript:;">帮助文档</a>--%>
            <%--</li>--%>
            <li class="layui-nav-item"><a href="https://github.com/Winfans" target="_blank">Github</a></li>
            <li class="layui-nav-item"><a href="https://blog.csdn.net/qq1123642601" target="_blank">CSDN</a></li>
        </ul>
    </div>
    <script>
        if (window.location.href.includes("Project") ) {
            document.getElementById('project-management').className = "layui-this"
        } else if (window.location.href.includes("User")) {
            document.getElementById('user-management').className = "layui-this"
        }else if (window.location.href.includes("Team")) {
            document.getElementById('team-management').className = "layui-this"
        }
    </script>
</div>

</body>
</html>
