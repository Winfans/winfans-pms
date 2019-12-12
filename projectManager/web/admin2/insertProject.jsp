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
<div id="add-project">
    <div class="add-project-wrappper">
        <form class="layui-form" action="insertProject" method="post">

            <input type="hidden" name="userId" id="add-project-userId">

            <div class="layui-form-item">
                <label class="layui-form-label">项目名称</label>
                <div class="layui-input-block">
                    <input type="text" name="projectName" required lay-verify="required" placeholder="请输入项目名称"
                           autocomplete="off"
                           class="layui-input">
                </div>
            </div>

            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">项目内容</label>
                <div class="layui-input-block">
                    <textarea name="content" placeholder="请输入项目内容" class="layui-textarea"></textarea>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">总金额</label>
                <div class="layui-input-block">
                    <input type="text" lay-verify="number" name="totalMoney" placeholder="请输入总金额"
                           autocomplete="off"
                           class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">负责人</label>
                <div class="layui-input-block">
                    <input type="text" name="leader" placeholder="请输入负责人"
                           autocomplete="off"
                           class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">时间</label>
                <div class="layui-input-block">
                    <div class="layui-col-md6">
                        <input type="text" name="planStartTime" placeholder="计划开始时间"
                               autocomplete="off"
                               class="layui-input"
                               id="add-plan-start-time">
                    </div>
                    <div class="layui-col-md6">
                        <input type="text" name="planEndTime" placeholder="计划结束时间"
                               autocomplete="off"
                               class="layui-input"
                               id="add-plan-end-time">
                    </div>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">状态</label>
                <div class="layui-input-block">
                    <select name="status">
                        <option value=""></option>
                        <option value="1">未开始</option>
                        <option value="2">进行中</option>
                        <option value="3">已完成</option>
                    </select>
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
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
    !function () {
        var layer = layui.layer, // 弹层
            form = layui.form, // 表单
            laydate = layui.laydate; // 时间

        layer.open({
            move: false,
            type: 1,
            title: "添加项目",
            area: ['calc(100% - 200px)', '100%'],
            shade: 0,
            offset: ['60px', '200px'],
            shadeClose: false,
            content: $('#add-project'),
            closeBtn: 1,
            cancel: function () {
                window.location.href = getRootPath() + "/admin2/findAllProject";
            }
        });

        // 计划开始时间laydate
        laydate.render({
            elem: '#add-plan-start-time', //指定元素
            type: 'datetime'
        });

        // 计划结束时间
        laydate.render({
            elem: '#add-plan-end-time', //指定元素
            type: 'datetime'
        });


    }();
</script>
</body>
</html>

