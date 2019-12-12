<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="cn.edu.lingnan.dto.Project" %>
<%@ page import="java.text.SimpleDateFormat" %>
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


<div id="view-project"
     style="display:none;">
    <div class="view-project-wrappper" style="padding: 12px;">
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
                String projectId = request.getParameter("projectId");

                // 从session中取所有项目
                List<Project> allProject = (List<Project>) session.getAttribute("allProject");

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                Iterator<Project> iterator = allProject.iterator();
                Project project;
                while (iterator.hasNext()) {
                    project = iterator.next();
                    if (project.getProjectId() == Integer.parseInt(projectId)) {
            %>
            <th>编号</th>
            <td><%=project.getProjectId()%>
            </td>
            <th>项目名称</th>
            <td><%=project.getProjectName()%>
            </td>
            </tr>
            <tr>
                <th>项目周期</th>
                <td><%=project.getPlanTime()%>
                </td>
                <th>总金额</th>
                <td><%=project.getTotalMoney()%>
                </td>
            </tr>
            <tr>
                <th>创建人</th>
                <td><%=project.getCreator()%>
                </td>
                <th>负责人</th>
                <td><%=project.getLeader()%>
                </td>
            </tr>
            <tr>
                <th>创建时间</th>
                <td><%=simpleDateFormat.format(project.getCreateTime())%>
                </td>
                <th>更新时间</th>
                <td><%=simpleDateFormat.format(project.getUpdateTime())%>
                </td>
            </tr>
            <tr>
                <th>计划开始时间</th>
                <td><%=simpleDateFormat.format(project.getPlanStartTime())%>
                </td>
                <th>计划结束时间</th>
                <td><%=simpleDateFormat.format(project.getPlanEndTime())%>
                </td>
            </tr>
            <tr>
            </tr>
            <tr>
                <th>项目内容</th>
                <td colspan="4"><%=project.getContent()%>
                </td>
            </tr>
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
        var element = layui.element,
            layer = layui.layer, // 弹层
            form = layui.form, // 表单
            laydate = layui.laydate; // 时间

        layer.open({
            move: false,
            type: 1,
            title: "详情信息",
            area: ['calc(100% - 200px)', 'calc(100% - 60px)'],
            shade: 0,
            offset: ['60px', '200px'],
            shadeClose: false,
            content: $('#view-project'),
            closeBtn: 1,
            cancel: function () {
                window.history.back(-1);
                // window.location.href = getRootPath() + "/user2/findAllProject";
            }
        });
    }();
</script>
</body>
</html>

