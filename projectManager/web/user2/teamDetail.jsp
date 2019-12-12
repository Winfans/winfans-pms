<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="cn.edu.lingnan.dto.ProjectUser" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>项目管理系统</title>
    <link rel="stylesheet" href="../static/layui/css/layui.css">
    <link rel="stylesheet" href="../static/css/initial.css">
    <link rel="stylesheet" href="../static/css/admin/index.css">
</head>


<div id="view-user">
    <div class="view-user-wrappper" style="padding: 12px;">
        <div class="" style="padding: 10px 15px;
            font-weight: bold;
            border: 1px solid #c9ced1;
            border-bottom: none;">基本信息
        </div>

        <table class="layui-table base-info-table" style="position: relative;top: -10px;">
            <colgroup>
                <col width="120px">
                <col>
                <col width="120px">
                <col>
            </colgroup>
            <tbody>
            <%
                String userId = request.getParameter("userId");
                String projectId = request.getParameter("projectId");


                // 从session中取所有项目-用户
                List<ProjectUser> allProjectUser = (List<ProjectUser>) session.getAttribute("allProjectUser");

//                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                Iterator<ProjectUser> iterator = allProjectUser.iterator();
                ProjectUser projectUser;
                while (iterator.hasNext()) {
                    projectUser = iterator.next();
                    if (projectUser.getUserId() == Integer.parseInt(userId) &&
                            projectUser.getProjectId() == Integer.parseInt(projectId)) {
            %>
            <tr>
                <th>项目编号</th>
                <td><%=projectUser.getProjectId()%>
                </td>
                <th>项目名称</th>
                <td>
                    <%=projectUser.getProjectName()%>
                </td>
            </tr>

            <tr>
                <th>用户编号</th>
                <td><%=projectUser.getUserId()%>
                </td>
                <th>用户名</th>
                <td><%=projectUser.getUsername()%>
                </td>
            </tr>
            <tr>
                <th>角色</th>
                <td><%=projectUser.getRole()%>
                </td>
                <th>其他</th>
                <td>占位
                </td>
            </tr>
            <%--<tr>--%>
                <%--<th>创建时间</th>--%>
                <%--<td><%=simpleDateFormat.format(user.getCreateTime())%>--%>
                <%--</td>--%>
                <%--<th>更新时间</th>--%>
                <%--<td><%=simpleDateFormat.format(user.getUpdateTime())%>--%>
                <%--</td>--%>
            <%--</tr>--%>
            <%
                    }
                }
            %>
            </tbody>
        </table>

    </div>
</div>


<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">

    <%--头部--%>
    <jsp:include page="header.jsp"></jsp:include>

    <%--侧边栏--%>
    <jsp:include page="side.jsp"></jsp:include>

    <%--底部固定区域--%>
    <jsp:include page="footer.jsp"></jsp:include>

</div>
<script src="../static/js/jquery-1.11.3.js"></script>
<script src="../static/layui/layui.all.js"></script>
<!--工具-->
<script src="../static/js/util.js"></script>
<!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
<!--[if lt IE 9]>
<script static="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
<script static="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
<script>
    //JavaScript代码区域
    !function () {
        var layer = layui.layer; // 弹层
        layer.open({
            move: false,
            type: 1,
            title: "详情信息",
            area: ['calc(100% - 200px)', 'calc(100% - 60px)'],
            shade: 0,
            offset: ['60px', '200px'],
            shadeClose: false,
            content: $('#view-user'),
            closeBtn: 1,
            cancel: function () {
                window.history.back(-1);
                // window.location.href = getRootPath() + "/admin2/findAllProjectUser";
            }
        });
    }();
</script>
</body>
</html>

