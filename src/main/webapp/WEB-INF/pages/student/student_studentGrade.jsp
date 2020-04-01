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
        .layui-table-cell {
            height: auto;
            overflow: visible;
            text-overflow: inherit;
        }
    </style>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body">
                    <table class="layui-hide" id="GradeTable" lay-filter="GradeTable"></table>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="../../layuiadmin/layui/layui.js"></script>
<script>
    layui.use(['element','table','laydate','form','jquery','layer'], function(){
        var table = layui.table;
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.jquery;
        var element = layui.element;
        var form = layui.form;
        var username = ${user.username}
        console.log(username)
        var tableObj = table.render({
            elem: '#GradeTable'
            ,url: '/student/getMyGrade?stuNo='+username
            ,toolbar: true
            , parseData: function (res) { //解析服务器端返回的数据
                console.log(res)
            }
            ,title: '学生成绩表'
            ,response: { //自定义数据状态的字段名称
                statusName: 'code',
                statusCode: 200
            }
            ,cols: [[
                ,{field:'courseName', title:'课程名称'}
                ,{field:'courseCode', title:'课程代码'}
                ,{field:'teacherName', title:'授课老师'}
                ,{field:'courseType', title:'课程类型'}
                ,{field:'marks', title:'成绩'}
                ,{field:'pass', title:'是否合格',templet : function(d) {
                        if (d.pass == 0)
                            return "<div class='not' style='color: red'>挂科</div>";
                        else
                            return "<div class='is'>通过</div>";
                    }}
            ]]
            ,id: 'testReload'

        });

    });
</script>
</body>
</html>