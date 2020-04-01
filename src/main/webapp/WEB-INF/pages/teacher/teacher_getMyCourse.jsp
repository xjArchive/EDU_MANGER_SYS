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
                        <div class="layui-inline">
                            <label class="layui-form-label">课程名称</label>
                            <div class="layui-inline">
                                <input class="layui-input" name="courseName" id="courseName" autocomplete="off" >
                            </div>
                        </div>

                        <div class="layui-inline">
                            <label class="layui-form-label">院系名称</label>
                            <div class="layui-inline">
                                <input class="layui-input" name="collegeName" id="collegeName" autocomplete="off" >
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">班级名称</label>
                            <div class="layui-inline">
                                <input class="layui-input" name="className" id="className" autocomplete="off" >
                            </div>
                        </div>


                        <div class="layui-inline">
                            <button class="layui-btn search" data-type="reload">搜索</button>
                        </div>

                    </div>

                    <table class="layui-hide" id="courseArrangeTable" lay-filter="courseArrangeTable"></table>
                    <script type="text/html" id="bar">
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
        var teacherNo = ${user.username};
        console.log(teacherNo)
        var tableObj = table.render({
            elem: '#courseArrangeTable'
            ,url: '/teacher/getMyCourssArrange?teacherNo='+teacherNo
            ,toolbar: true
            , parseData: function (res) { //解析服务器端返回的数据
                console.log(res)
            }
            ,title: '排课列表'
            ,page: true
            ,response: { //自定义数据状态的字段名称
                statusName: 'code',
                statusCode: 200
            }
            ,cols: [[
                {type: 'checkbox', fixed: 'left'}
                ,{field:'id', title:'ID', fixed: 'left', sort: true,width:80}
                ,{field:'courseName', title:'课程名称',}
                ,{field:'courseCode', title:'课程代码'}
                ,{field:'collegeName', title:'院系名称'}
                ,{field:'className', title:'班级名称'}
                ,{field:'startTime', title:'上课时间'}
                ,{field:'courseLong', title:'课时'}
                ,{field:'score', title:'学分'}
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
        });



        //layui重载搜索
        // 执行搜索，表格重载
        $('.search').on('click', function () {
            var table = layui.table;
            // 搜索条件
            table.reload('testReload',{
                method: 'get'
                , url: '/teacher/getMyCourseListByCondition?teacherNo='+teacherNo
                , where: {
                    'courseName':$('#courseName').val().trim(),
                    'className':$('#className').val().trim(),
                    'collegeName':$('#collegeName').val().trim(),
                }
                , page: {
                    curr: 1
                }
            });
        });


    });
</script>
</body>
</html>