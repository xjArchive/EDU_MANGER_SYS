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
                    <table class="layui-hide" id="TranTable" lay-filter="TranTable"></table>
                    <script type="text/html" id="bar">
                        <a class="layui-btn layui-btn-xs" lay-event="edit">审核</a>
                        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
                    </script>
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
        var tableObj = table.render({
            elem: '#TranTable'
            ,url: '/admin/getAllTran'
            ,toolbar: true
            ,page: true
            , parseData: function (res) { //解析服务器端返回的数据
                console.log(res)
            }
            ,title: '事务列表'
            ,response: { //自定义数据状态的字段名称
                statusName: 'code',
                statusCode: 200
            }
            ,cols: [[
                {field:'createBy', title:'提交用户'}
               , {field:'title', title:'标题'}
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
                ,{fixed: 'right', title:'操作', toolbar: '#bar', width:150}
            ]]
            ,id: 'testReload'

        });


        //监听工具栏事件
        table.on('tool(TranTable)', function(obj) {
            var data = obj.data;
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            if(layEvent === 'del'){ //删除
                layer.confirm('确定删除该条信息么？',{icon: 3, title:'温馨提示'}, function(index){
                    obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                    layer.close(index);
                    console.log(data.id)
                    //向服务端发送删除请求
                    $.post("/admin/deleteTran",{id:data.id},function (res) {
                        if (res.code == 200){ //删除成功
                            obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                            layer.close(index);
                            layer.msg("删除成功");
                            tableObj.reload();//重新刷新数据表格
                        }else {
                            layer.close(index);
                            layer.msg(res.msg);
                            tableObj.reload();//重新刷新数据表格
                        }
                    })
                });

            }
            if(layEvent === 'edit'){ //删除
                //询问框
                layer.confirm('您确定批准该申请？', {
                    btn: ['同意','拒绝'] //按钮
                }, function(){
                    //组织数剧
                    var id = data.id;
                    var req = {
                        "id":id
                    }
                    $.post("/admin/passTran",req,function (res) {
                        if (res.code == 200){
                            layer.msg('操作成功', {icon: 1});
                            tableObj.reload();//重新刷新数据表格
                        }else {
                            layer.msg(res.msg, {icon: 2});
                        }
                    })
                },function () {
                    //组织数剧
                    var id = data.id;
                    var req = {
                        "id":id
                    }
                    $.post("/admin/rejectTran",req,function (res) {
                        if (res.code == 200){
                            layer.msg('操作成功', {icon: 1});
                            tableObj.reload();//重新刷新数据表格
                        }else {
                            layer.msg(res.msg, {icon: 2});
                        }
                    })
                });

            }
        });


    });
</script>
</body>
</html>