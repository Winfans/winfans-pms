<%@ page import="cn.edu.lingnan.dto.User" %>
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

                User user = (User) session.getAttribute("user");

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            %>
            <th>编号</th>
            <td><%=user.getUserId()%>
            </td>
            <th rowspan="3">头像</th>
            <td rowspan="3">
                <img style="width: 70px;height: 70px;" src="<%=user.getHeadPortrait()%>" alt="">
            </td>
            <tr>
                <th>用户名</th>
                <td><%=user.getUsername()%>
                </td>
            </tr>
            <tr>
                <th>管理员</th>
                <td><%=user.getIsAdmin()%>
                </td>
            </tr>
            <tr>
                <th>手机号码</th>
                <td><%=user.getPhone()%>
                </td>
                <th>性别</th>
                <td><%=user.getSex()%>
                </td>
            </tr>
            <tr>
                <th>城市</th>
                <td><%=user.getCity()%>
                </td>
                <th>兴趣</th>
                <td><%=user.getInterest()%>
                </td>
            </tr>
            <tr>
                <th>创建时间</th>
                <td><%=simpleDateFormat.format(user.getCreateTime())%>
                </td>
                <th>更新时间</th>
                <td><%=simpleDateFormat.format(user.getUpdateTime())%>
                </td>
            </tr>
            <tr>
                <th>个性签名</th>
                <td colspan="4"><%=user.getPersonalizedSignature()%>
                </td>
            </tr>
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
            }
        });


    }();
</script>
</body>
</html>

