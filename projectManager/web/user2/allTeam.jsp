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
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">

    <%--头部--%>
    <jsp:include page="header.jsp"></jsp:include>

    <%--侧边栏--%>
    <jsp:include page="side.jsp"></jsp:include>

    <div class="layui-body">
        <table class="layui-hide" id="team-table" lay-filter="team-filter">
        </table>

        <script type="text/html" id="team-left-toolbar">
            <form class="layui-form" lay-filter="team-form-filter">
                <div class="layui-input-inline search-input">
                    <input type="text" name="projectName" placeholder="请输入项目名称" autocomplete="off"
                           class="layui-input">
                </div>
                <a class="layui-btn" lay-event="search">搜索</a>
            </form>
        </script>

        <script type="text/html" id="team-row-toolbar">
            <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
        </script>
    </div>

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
            form = layui.form,
            laypage = layui.laypage, // 分页
            table = layui.table; // 表格

        // 项目管理table
        table.render({
            elem: '#team-table',
            url: getRootPath() + '/user2/findAllProjectUserByPageAndLimit', // 数据接口
            parseData: function (res) { //res 即为原始返回的数据
                return {
                    "code": res.status, //解析接口状态
                    "count": res.total, //解析数据长度
                    "data": res.data //解析数据列表
                };
            },
            title: '项目表',
            page: {theme: '#009688'},// 开启分页
            toolbar: '#team-left-toolbar', // 开启工具栏，此处显示默认图标，可以自定义模板，详见文档
            cols: [
                [   // 表头
                    {type: 'checkbox', fixed: 'left'},
                    {field: 'projectName', title: '项目名称', sort: true, fixed: 'left'},
                    {field: 'username', title: '用户名'},
                    {field: 'role', title: '角色'},
                    {fixed: 'right', width: 165, align: 'center', toolbar: '#team-row-toolbar'}
                ]
            ]
        });

        // 监听项目table头工具栏事件
        table.on('toolbar(team-filter)', function (obj) {
            var checkStatus = table.checkStatus(obj.config.id)
                , data = checkStatus.data; //获取选中的数据
            var formData = form.val("team-form-filter");

            switch (obj.event) {
                case 'search':
                    window.location.href = getRootPath() + "/user2/searchTeam.jsp?projectName=" + formData.projectName;
                    break;
                default:
                    break;
            }
        });

        //监听项目table行工具事件
        table.on('tool(team-filter)', function (obj) { // tool 是工具条事件名，user-filter 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data, // 获得当前行数据
                layEvent = obj.event; //获得 lay-event 对应的值

            if (layEvent === 'detail') {
                window.location.href = getRootPath() + '/user2/teamDetail.jsp?projectId=' + data.projectId + "&userId=" + data.userId;
            }
        });
    }();
</script>
</body>
</html>
