<%@ page import="cn.edu.lingnan.dto.User" %>
<%@ page import="java.util.List" %>
<%@ page import="cn.edu.lingnan.dto.Project" %>
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
    <script src="../static/js/echarts.js"></script>

</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">

    <%--头部--%>
    <jsp:include page="header.jsp"></jsp:include>


    <%--侧边栏--%>
    <jsp:include page="side.jsp"></jsp:include>

    <%

        // 从session中取所有项目
        List<User> allUser = (List<User>) session.getAttribute("allUser");
        List<Project> allProject = (List<Project>) session.getAttribute("allProject");

        int userCount = allUser.size();
        int projectCount = allProject.size();

        int beijing = 0;
        int shanghai = 0;
        int guangzhou = 0;
        int shenzhen = 0;
        int hangzhou = 0;
        int male = 0;
        int female = 0;
        for (User user : allUser) {
            if (user.getSex() == 1) {
                male++;
            } else if (user.getSex() == 2) {
                female++;
            }
            if ("北京".equals(user.getCity())) {
                beijing++;
            } else if ("上海".equals(user.getCity())) {
                shanghai++;
            } else if ("广州".equals(user.getCity())) {
                guangzhou++;
            } else if ("深圳".equals(user.getCity())) {
                shenzhen++;
            } else if ("杭州".equals(user.getCity())) {
                hangzhou++;
            }
        }
    %>

    <div class="layui-body">
        <div style="padding: 10px;">
            <blockquote class="layui-elem-quote">首页</blockquote>
            <div class="panel-box row" style="display: flex;margin-left: 10px;margin-top: 20px;">
                <div class="panel" style="width: 33%;height: 94px;border-radius: 8px;overflow: hidden;">
                    <div class="panel_icon"
                         style="width: 40%;display:inline-block;height: 100%;line-height: 94px;text-align: center;background-color: #FF5722;padding:0;">
                        <i class="layui-icon" style="font-size: 38px;color: #fff;"></i>
                    </div>
                    <div class="panel_word newMessage"
                         style="border-top-right-radius:8px;border-bottom-right-radius:8px;padding:0;vertical-align:top;width: 59%;position:relative;left:-5px;height:100%;display:inline-block;background-color: #F2F2F2;text-align: center;">
                        <div style="width:80px;height:50px;position: absolute;top: calc(50% - 25px);left: calc(50% - 40px);">
                            <p style="font-size: 25px;"><%=userCount%></p>
                            <p style="font-size: 20px;">用户总数</p>
                        </div>
                    </div>
                </div>
                <div class="panel" style="width: 33%;height: 94px;border-radius: 8px;overflow: hidden;">
                    <div class="panel_icon"
                         style="width: 40%;display:inline-block;height: 100%;line-height: 94px;text-align: center;background-color: #54ade8;padding:0;">
                        <i class="layui-icon" style="font-size: 38px;color: #fff;"></i>
                    </div>
                    <div class="panel_word newMessage"
                         style="border-top-right-radius:8px;border-bottom-right-radius:8px;padding:0;vertical-align:top;width: 59%;position:relative;left:-5px;height:100%;display:inline-block;background-color: #F2F2F2;text-align: center;">
                        <div style="width:80px;height:50px;position: absolute;top: calc(50% - 25px);left: calc(50% - 40px);">
                            <p style="font-size: 25px;"><%=projectCount%></p>
                            <p style="font-size: 20px;">项目总数</p>
                        </div>
                    </div>
                </div>
                <div class="panel" style="width: 33%;height: 94px;border-radius: 8px;overflow: hidden;">
                    <div class="panel_icon"
                         style="width: 40%;display:inline-block;height: 100%;line-height: 94px;text-align: center;background-color: #009688;padding:0;">
                        <i class="layui-icon" style="font-size: 38px;color: #fff;"></i>
                    </div>
                    <div class="panel_word newMessage"
                         style="border-top-right-radius:8px;border-bottom-right-radius:8px;padding:0;vertical-align:top;width: 59%;position:relative;left:-5px;height:100%;display:inline-block;background-color: #F2F2F2;text-align: center;">
                        <div style="width:80px;height:50px;position: absolute;top: calc(50% - 25px);left: calc(50% - 40px);">
                            <p style="font-size: 25px;"><%=pageContext.getServletContext().getAttribute("times")%></p>
                            <p style="font-size: 20px;">访问总数</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div style="position: relative;width: 100%;">
            <div style="display: inline-block;position: absolute;left: 10px;width: 49%;">
                <blockquote class="layui-elem-quote" style="margin-top: 20px;">城市情况</blockquote>
                <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
                <div id="main1" style="width: 500px;height:400px;"></div>
            </div>
            <div style="display: inline-block;position: absolute;right: 10px;width: 49%;">
                <blockquote class="layui-elem-quote" style="margin-top: 20px;">性别情况</blockquote>
                <div id="main2" style="width: 500px;height:400px;"></div>
            </div>
        </div>
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


    // 基于准备好的dom，初始化echarts实例
    var myChart1 = echarts.init(document.getElementById('main1'));
    var myChart2 = echarts.init(document.getElementById('main2'));

    option1 = {
        title: {
            text: '用户情况统计',
            subtext: '居住城市',
            left: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            // orient: 'vertical',
            // top: 'middle',
            bottom: 10,
            left: 'center',
            data: ['北京', '上海', '广州', '深圳', '杭州']
        },
        series: [
            {
                type: 'pie',
                radius: '65%',
                center: ['50%', '50%'],
                selectedMode: 'single',
                data: [
                    {value: <%=beijing%>, name: '北京'},
                    {value: <%=shanghai%>, name: '上海'},
                    {value: <%=guangzhou%>, name: '广州'},
                    {value: <%=shenzhen%>, name: '深圳'},
                    {value: <%=hangzhou%>, name: '杭州'},
                ],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };
    option2 = {
        title: {
            text: '用户情况统计',
            subtext: '性别',
            left: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            // orient: 'vertical',
            // top: 'middle',
            bottom: 10,
            left: 'center',
            data: ['男', '女']
        },
        series: [
            {
                type: 'pie',
                radius: '65%',
                center: ['50%', '50%'],
                selectedMode: 'single',
                data: [
                    {value: <%=male%>, name: '男'},
                    {value: <%=female%>, name: '女'},

                ],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };


    // 使用刚指定的配置项和数据显示图表。
    myChart1.setOption(option1);
    myChart2.setOption(option2);

    //JavaScript代码区域
    !function () {
        var element = layui.element,
            form = layui.form,
            laypage = layui.laypage, // 分页
            table = layui.table; // 表格

    }();
</script>
</body>
</html>
