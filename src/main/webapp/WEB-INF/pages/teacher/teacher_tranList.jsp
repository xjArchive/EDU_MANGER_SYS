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
                    <table class="layui-hide" id="TranTable" lay-filter="GradeTable"></table>
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
            elem: '#TranTable'
            ,url: '/student/getMyTran?username='+username
            ,toolbar: true
            , parseData: function (res) { //解析服务器端返回的数据
                console.log(res)
            }
            ,title: '事务列表'
            ,response: { //自定义数据状态的字段名称
                statusName: 'code',
                statusCode: 200
            }
            ,cols: [[
                {field:'title', title:'标题'}
                ,{field:'content', title:'内容'}
                ,{field:'createDate', title:'提交时间'}
               ,{field:'img', title:'图片',templet : function(d) {
                            return "<div class='not'> <img src='/uploadImgs'+d.img></div>";
                    }}
                ,{field:'status', title:'是否审核',templet : function(d) {
                        if (d.status == 0){
                            return "<div class='not' style='color: red'>未审核</div>";
                        } else if(d.status == 1){
                            return "<div class='is'>审核通过</div>";
                        }else{
                            return "<div class='is'>审核失败</div>";
                        }
                    }}
            ]]
            ,id: 'testReload'

        });

    });
</script>
</body>
</html>