<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>开启头部工具栏 - 数据表格</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="../../layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="../../layuiadmin/style/admin.css" media="all">
    <style>
        .layui-form-label.layui-required:after{
            content:"*";
            color:red;
            position: absolute;
            top:5px;
            left:15px;
        }
        .addForm{
            margin-top: 20px;
        }
    </style>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body">
                    <%--layui搜索--%>
                    <div class="test-table-reload-btn" style="margin-bottom: 10px;">
                        <div class="layui-inline ">
                            <button class="layui-btn" id="mySelect">我的选课</button>
                        </div>

                    </div>

                    <table class="layui-hide" id="courseArrangeTable" lay-filter="courseArrangeTable"></table>
                    <script type="text/html" id="bar">
                        <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit">选课</a>
                        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
                    </script>
                </div>
            </div>
        </div>
    </div>
</div>


<script type="text/html" id="editTemplete" >
    <table class="layui-hide" id="SelectTable" lay-filter="SelectTable"></table>
</script>


<script src="../../layuiadmin/layui/layui.js"></script>
<script>
    layui.use(['element','table','laydate','form','jquery','layer'], function(){
        var table = layui.table;
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.jquery;
        var element = layui.element;
        var form = layui.form;
        var studentNo = ${user.username};
        console.log(studentNo)
        var tableObj = table.render({
            elem: '#courseArrangeTable'
            ,url: '/student/AllSelectCourse'
            ,toolbar: true
            , parseData: function (res) { //解析服务器端返回的数据
                console.log(res)
            }
            ,title: '所有选课列表'
            ,page: true
            ,response: { //自定义数据状态的字段名称
                statusName: 'code',
                statusCode: 200
            }
            ,cols: [[
                {field:'courseName', title:'课程名称'}
                ,{field:'courseCode', title:'课程代码'}
                ,{field:'teacherName', title:'教师'}
                ,{field:'courseLong', title:'课时',sort:true}
                ,{field:'score', title:'学分',sort:true}
                ,{field:'courseAddress', title:'上课地点'}
                ,{fixed: 'right', title:'操作', toolbar: '#bar', width:150}
            ]]
            ,id: 'testReload'

        });



        //监听工具栏事件
        table.on('tool(courseArrangeTable)', function(obj) {
            var data = obj.data;
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            if(layEvent === 'del'){ //删除
                layer.msg("抱歉，您没有权限,请联系管理员进行操作")

            }
            if(layEvent === 'edit'){ //删除
                //询问框
                layer.confirm('您确定选择该课程？', {
                    btn: ['确定','取消'] //按钮
                }, function(){
                    //发送ajax选择该课程
                    //组织数剧
                    var courseId = data.id;
                    var req = {
                        "studentNo":studentNo,
                        "courseId":courseId
                    }
                    $.post("/student/SelectCourse",req,function (res) {
                        if (res.code == 200){
                            layer.msg('恭喜! 你成功的选择了该课程', {icon: 1});
                        }else {
                            layer.msg(res.msg, {icon: 2});
                        }
                    })
                }, function(){

                });

            }
        });




        $('#mySelect').click(function () {

            layer.open({
                type: 1,
                title: '我的选课',
                skin: 'layui-layer-rim',
                resize: true,
                maxmin: true,   //开启大小化
                offset: 'auto',
                shade: 0.4,   //遮罩透明度
                area: ['600px','300px'],
                content: $('#editTemplete').html() ,//这里content是一个普通的String,
                success: function(layero, index) {
                  /*  $.get("/student/mySelect",{"studentNo":studentNo},function (res) {
                        //数剧回显
                        if (res.code == 200){
                            $('#courseAddress').val(res.data.courseAddress)
                            $('#cname').val(res.data.courseName)
                            $('#tname').val(res.data.teacherName)
                            $('#score').val(res.data.score)
                        }
                    })*/

                    var mySelect = table.render({
                        elem: '#SelectTable'
                        ,url: '/student/mySelect?studentNo='+studentNo
                        , parseData: function (res) { //解析服务器端返回的数据
                            console.log(res)
                        }
                        ,title: '我的选课'
                        ,response: { //自定义数据状态的字段名称
                            statusName: 'code',
                            statusCode: 200
                        }
                        ,cols: [[
                            {field:'courseName', title:'课程名称'}
                            ,{field:'courseCode', title:'课程代码'}
                            ,{field:'score', title:'成绩'}
                            ,{field:'flag', title:'是否评分',sort:true,templet: function (d) {
                                if (d.flag == 0)
                                    return "<div class='not' style='color: red'>未评分</div>";
                                else
                                    return "<div class='is'>已评分</div>";
                            }}
                        ]]
                    });

                }
             });
        });

    });
</script>
</body>
</html>