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

                    <table class="layui-hide" id="courseArrangeTable" lay-filter="courseArrangeTable"></table>
                    <script type="text/html" id="bar">
                        <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit">选择该课题</a>
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
            ,url: '/student/AllSelectList'
            ,toolbar: true
            , parseData: function (res) { //解析服务器端返回的数据
                console.log(res)
            }
            ,title: '所有可选课题列表'
            ,page: true
            ,response: { //自定义数据状态的字段名称
                statusName: 'code',
                statusCode: 200
            }
            ,cols: [[
                {field:'name', title:'课题名称'}
                ,{field:'content', title:'课题内容'}
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
                    var req = {
                        "id":data.id,
                        "username":studentNo
                    }
                    $.post("/student/SelectList",req,function (res) {
                        if (res.code == 200){
                            layer.msg('恭喜! 选题成功', {icon: 1});
                        }else {
                            layer.msg(res.msg, {icon: 2});
                        }
                    })
                }, function(){

                });

            }
        });


    });
</script>
</body>
</html>