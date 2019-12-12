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
<div id="add-user">
    <div class="add-user-wrappper">
        <form class="layui-form" action="insertUser" method="post">
            <div class="layui-form-item">
                <label class="layui-form-label">头像</label>
                <div class="layui-input-block">
                    <button type="button" class="layui-btn layui-btn-sm" id="add-user-head-portrait-upload">
                        <i class="layui-icon layui-icon-upload"></i>上传头像
                    </button>
                    <input type="hidden" name="headPortrait" value="" id="head-portrait-input">
                    <img id="add-user-head-portrait" class="layui-anim-scaleSpring" src="" alt="头像"/>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">用户名</label>
                <div class="layui-input-block">
                    <input type="text" name="username" required lay-verify="required" placeholder="请输入用户名"
                           autocomplete="off"
                           class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">密码</label>
                <div class="layui-input-block">
                    <input type="password" name="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">手机号码</label>
                <div class="layui-input-block">
                    <input type="text" name="phone" placeholder="请输入手机号码"
                           autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">城市</label>
                <div class="layui-input-block">
                    <select name="city">
                        <option value=""></option>
                        <option value="北京">北京</option>
                        <option value="上海">上海</option>
                        <option value="广州">广州</option>
                        <option value="深圳">深圳</option>
                        <option value="杭州">杭州</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">兴趣</label>
                <div class="layui-input-block">
                    <input type="checkbox" name="interest[code]" title="编程" checked>
                    <input type="checkbox" name="interest[read]" title="读书">
                    <input type="checkbox" name="interest[sport]" title="运动">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">性别</label>
                <div class="layui-input-block">
                    <input type="radio" name="sex" value="1" title="男" checked>
                    <input type="radio" name="sex" value="2" title="女">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">管理员</label>
                <div class="layui-input-block">
                    <input type="checkbox" name="isAdmin" lay-skin="switch">
                </div>
            </div>
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">个性签名</label>
                <div class="layui-input-block">
                    <textarea name="personalizedSignature" placeholder="请输入内容" class="layui-textarea"></textarea>
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
            element = layui.element,
            upload = layui.upload; // 文件上传

        layer.open({
            move: false,
            type: 1,
            title: "添加用户",
            area: ['calc(100% - 200px)', '100%'],
            shade: 0,
            offset: ['60px', '200px'],
            shadeClose: false,
            content: $('#add-user'),
            closeBtn: 1,
            cancel: function () {
                window.location.href = getRootPath() + "/admin2/findAllUser";
            }
        });

        //添加用户的上传文件实例
        upload.render({
            elem: '#add-user-head-portrait-upload' //绑定元素
            , url: 'http://www.wffanshao.top/office-api/upload/image', //上传接口，本人其他项目的接口
            progress: function (n) {
                var percent = n + '%'; //获取进度百分比
                element.progress('demo', percent); //可配合 layui 进度条元素使用
            }
            , done: function (res) {
                //上传完毕回调
                $('#add-user-head-portrait-upload').css('display', "none");
                $('#add-user-head-portrait').css('display', "block");
                $('#add-user-head-portrait').attr('src', res.data);
                $('#head-portrait-input').attr('value', res.data);
            }
            , error: function () {
                //请求异常回调
            }
        });
    }();
</script>
</body>
</html>

