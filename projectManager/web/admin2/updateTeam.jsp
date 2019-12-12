<%@ page import="cn.edu.lingnan.dto.ProjectUser" %>
<%@ page import="java.util.List" %>
<%@ page import="cn.edu.lingnan.enums.ProjectRoleEnum" %>
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
<div id="edit-team">
    <div class="edit-team-wrappper">
        <form class="layui-form" id="edit-project-form" action="updateProjectUser" method="post">
            <%
                String userId = request.getParameter("userId");
                String projectId = request.getParameter("projectId");

                // 从session中取所有项目
                List<ProjectUser> allProjectUser = (List<ProjectUser>) session.getAttribute("allProjectUser");

                ProjectUser projectUserTemp = null;

//                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                for (ProjectUser projectUser : allProjectUser) {
                    if (projectUser.getUserId() == Integer.parseInt(userId)
                            && projectUser.getProjectId() == Integer.parseInt(projectId)) {
                        projectUserTemp = projectUser;
            %>

            <input type="hidden" value="<%=projectUser.getProjectId()%>" name="projectId">

            <div class="layui-form-item">
                <label class="layui-form-label">项目名称</label>
                <div class="layui-input-block">
                    <input type="text" name="projectName" required lay-verify="required" placeholder="请输入项目名称"
                           autocomplete="off"
                           value="<%=projectUser.getProjectName()%>"
                           class="layui-input">
                </div>
            </div>
            <input type="hidden" value="<%=projectUser.getUserId()%>" name="userId">

            <div class="layui-form-item">
                <label class="layui-form-label">用户名</label>
                <div class="layui-input-block">
                    <input type="text" name="username" required lay-verify="required" placeholder="请输入用户名"
                           autocomplete="off"
                           value="<%=projectUser.getUsername()%>"
                           class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">角色</label>
                <div class="layui-input-block">
                    <select name="role" id="edit-team-role">
                        <option value=""></option>
                        <option value="1">项目管理员</option>
                        <option value="2">项目负责人</option>
                        <option value="3">项目成员</option>
                    </select>
                </div>
            </div>
            <%
                    }
                }
            %>

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="formDemo">确定修改</button>
                    <%--<button type="reset" class="layui-btn layui-btn-primary">重置</button>--%>
                </div>
            </div>
        </form>

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
    ;!function () {
        var layer = layui.layer, // 弹层
            form = layui.form, // 表单
            laydate = layui.laydate; // 时间

        layer.open({
                move: false,
                type: 1,
                title: "详情信息",
                area: ['calc(100% - 200px)', '100%'],
                shade: 0,
                offset: ['60px', '200px'],
                shadeClose: false,
                content: $('#edit-team'),
                closeBtn: 1,
                success: function (layero, index) {
                    var $eidtTeamRole = $('#edit-team-role');
                    <%
                    Integer role = projectUserTemp.getRole();
                    if (role.equals(ProjectRoleEnum.PROJECT_ADMIN.getRoleId())) {
                    %>
                    $eidtTeamRole.val("1");
                    <%
                    } else if(role.equals(ProjectRoleEnum.PROJECT_LEADER.getRoleId())){
                    %>
                    $eidtTeamRole.val("2");
                    <%
                    } else if (role.equals(ProjectRoleEnum.PROJECT_MEMBER.getRoleId())) {
                    %>
                    $eidtTeamRole.val("3");
                    <%
                    } else {
                    %>
                    $eidtTeamRole.val("0");
                    <%
                    }
                    %>
                    form.render();
                },
                cancel: function () {
                    window.location.href = getRootPath() + "/admin2/findAllProjectUser";
                }
            }
        )
    }();
</script>
</body>
</html>
