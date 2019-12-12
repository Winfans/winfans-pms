<%@ page import="cn.edu.lingnan.dto.User" %>
<%@ page import="java.util.List" %>
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

<div id="edit-user">
    <div class="edit-user-wrappper">
        <form class="layui-form" action="updateInfo" method="post">
            <%
                User user = (User) session.getAttribute("user");
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            %>

            <div class="layui-form-item">
                <label class="layui-form-label">头像</label>
                <div class="layui-input-block">
                    <%
                        if ("".equals(user.getHeadPortrait()) || user.getHeadPortrait() == null) {
                    %>
                    <button type="button" class="layui-btn layui-btn-sm" id="edit-head-portrait-upload">
                        <i class="layui-icon layui-icon-upload"></i>上传头像
                    </button>
                    <img id="edit-head-portrait" class="layui-anim-scaleSpring" src="<%=user.getHeadPortrait()%>"
                         style="display: none;"
                         alt="头像"/>
                    <%
                    } else {
                    %>
                    <img id="edit-head-portrait" class="layui-anim-scaleSpring" src="<%=user.getHeadPortrait()%>"
                         alt="头像"/>
                    <%
                        }
                    %>

                    <input type="hidden" name="headPortrait" value="<%=user.getHeadPortrait()%>"
                           id="edit-head-portrait-input">
                </div>
            </div>

            <input type="hidden" name="createTime" value="<%=simpleDateFormat.format(user.getCreateTime())%>">

            <input type="hidden" name="userId" value="<%=user.getUserId()%>">

            <div class="layui-form-item">
                <label class="layui-form-label">用户名</label>
                <div class="layui-input-block">
                    <input id="edit-username" type="text" name="username" required lay-verify="required"
                           placeholder="请输入用户名"
                           autocomplete="off"
                           value="<%=user.getUsername()%>"
                           class="layui-input">
                </div>
            </div>
            <input type="hidden" name="password" value="<%=user.getPassword()%>" id="edit-password">
            <div class="layui-form-item">
                <label class="layui-form-label">手机号码</label>
                <div class="layui-input-block">
                    <input type="text" name="phone" placeholder="请输入手机号码"
                           autocomplete="off"
                           id="edit-phone"
                           value="<%=user.getPhone()%>"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">城市</label>
                <div class="layui-input-block">
                    <select name="city" id="edit-user-city">
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
                <label class="layui-form-label" id="edit-interest">兴趣</label>
                <div class="layui-input-block">
                    <input type="checkbox" name="interest[code]" title="编程" id="edit-user-interest-code">
                    <input type="checkbox" name="interest[read]" title="读书" id="edit-user-interest-read">
                    <input type="checkbox" name="interest[sport]" title="运动" id="edit-user-interest-sport">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">性别</label>
                <div class="layui-input-block" id="edit-sex">
                    <input type="radio" name="sex" value="1" title="男" id="edit-user-sex-male">
                    <input type="radio" name="sex" value="2" title="女" id="edit-user-sex-female">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">管理员</label>
                <div class="layui-input-block" id="edit-is-admin">
                    <input type="checkbox" name="isAdmin" lay-skin="switch" id="edit-user-is-admin">
                </div>
            </div>
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">个性签名</label>
                <div class="layui-input-block" id="edit-personalized-signature">
                    <textarea id="edit-user-personalized-signature" name="personalizedSignature" placeholder="请输入内容"
                              class="layui-textarea"
                    ><%=user.getPersonalizedSignature().trim()%>
                    </textarea>
                </div>
            </div>

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
    !function () {
        var layer = layui.layer, // 弹层
            form = layui.form, // 表单
            element = layui.element, // 动态操作
            upload = layui.upload; // 文件上传

        layer.open({
                move: false,
                type: 1,
                title: "详情信息",
                area: ['calc(100% - 200px)', '100%'],
                shade: 0,
                offset: ['60px', '200px'],
                shadeClose: false,
                content: $('#edit-user'),
                closeBtn: 1,
                success: function (layero, index) {


                    // 城市处理
                    var $editUserCity = $('#edit-user-city');
                    <%
                    String city = user.getCity();
                    if ("北京".equals(city)) {
                    %>
                    $editUserCity.val("北京");
                    <%
                    } else if("上海".equals(city)){
                    %>
                    $editUserCity.val("上海");
                    <%
                    } else if ("广州".equals(city)) {
                    %>
                    $editUserCity.val("广州");
                    <%
                    } else if ("深圳".equals(city)) {
                    %>
                    $editUserCity.val("深圳");
                    <%
                    } else if ("杭州".equals(city)) {
                    %>
                    $editUserCity.val("杭州");
                    <%
                    } else {
                    %>
                    $editUserCity.val("");
                    <%
                   }
                   %>

                    <%--兴趣处理--%>
                    <%
                    String interestStr = user.getInterest();
                    if (interestStr != null && !"".equals(interestStr)) {

                    String[] interests = interestStr.split(",");
                    for (String interest : interests) {
                        if ("编程".equals(interest)) {
                    %>
                    $('#edit-user-interest-code').attr("checked", true);
                    <%
                        } else if ("读书".equals(interest)) {
                    %>
                    $('#edit-user-interest-read').attr("checked", true);
                    <%

                        } else if ("运动".equals(interest)) {
                    %>
                    $('#edit-user-interest-sport').attr("checked", true);
                    <%
                        }
                    }
                    }
                    %>

                    <%--性别处理--%>
                    <%
                    Integer sex = user.getSex();
                    if (sex == 1) {
                    %>
                    $('#edit-user-sex-male').attr("checked", true);
                    <%
                    } else if (sex == 2) {
                    %>
                    $('#edit-user-sex-female').attr("checked", true);
                    <%
                    }
                    %>

                    <%--管理员处理--%>
                    <%
                    Integer isAdmin = user.getIsAdmin();
                    if (isAdmin == 1) {
                    %>
                    $('#edit-user-is-admin').attr("checked", true);
                    <%
                    }
                    %>
                    form.render();
                },
                cancel: function () {
                    window.history.back(-1);
                    // window.location.href = getRootPath() + "/admin2/findAllUser";
                }
            }
        );

        //编辑用户的上传文件实例
        upload.render({
            elem: '#edit-head-portrait-upload' //绑定元素
            , url: 'http://www.wffanshao.top/office-api/upload/image', //上传接口
            progress: function (n) {
                var percent = n + '%'; //获取进度百分比
                element.progress('demo', percent); //可配合 layui 进度条元素使用
            }
            , done: function (res) {
                //上传完毕回调
                $('#edit-head-portrait-upload').css('display', "none");
                $('#edit-head-portrait').css('display', "block").attr('src', res.data);
                $('#edit-head-portrait-input').attr('value', res.data);
            }
            , error: function () {
                //请求异常回调
            }
        });


    }();
</script>
</body>
</html>

