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
<div class="layui-fluids">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body">
                    <%--layui搜索--%>
                    <div class="test-table-reload-btn" style="margin-bottom: 10px;">
                        <div class="layui-inline">
                            <label class="layui-form-label">学号</label>
                            <div class="layui-inline">
                                <input class="layui-input" name="stuNo" id="username" autocomplete="off">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">课程代码</label>
                            <div class="layui-inline">
                                <input class="layui-input" name="courseCode" id="courseco" autocomplete="off">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">班级</label>
                            <div class="layui-inline">
                                <input class="layui-input" name="className" id="className" autocomplete="off">
                            </div>
                        </div>


                        <div class="layui-inline">
                            <label class="layui-form-label">状态</label>
                            <div class="layui-input-inline layui-form">
                                <select name = "flag" id="flag">
                                    <option value="">请选择</option>
                                    <option value="1">已打分</option>
                                    <option value="0">未打分</option>
                                </select>
                            </div>
                        </div>
                        <div class="layui-inline layui-col-space10">
                            <button class="layui-btn search" data-type="reload">搜索</button>
                        </div>

                    </div>

                    <table class="layui-hide" id="GradeTable" lay-filter="GradeTable"></table>
                    <script type="text/html" id="bar">
                        <a class="layui-btn layui-btn-xs" lay-event="edit" id="make">评分</a>
                    </script>
                </div>
            </div>
        </div>
    </div>
</div>




<script type="text/html" id="test" >
    <div class="layui-col-md12">
        <form class="layui-form" id="MakeGrade" method="post" action="">

            <input type="hidden" id = "id" name="id" class="layui-input">

            <div class="layui-form-item">
                <label class="layui-form-label layui-required">学号：</label>
                <div class="layui-input-block">
                    <input type="text" id = "stuNo" name="stuNo" class="layui-input" lay-verify="required|number" disabled = "disabled">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label layui-required">姓名：</label>
                <div class="layui-input-block">
                    <input type="text" id = "realName" name="realName" class="layui-input" lay-verify="required" disabled = "disabled">
                </div>
            </div>


            <div class="layui-form-item">
                <label class="layui-form-label layui-required">班级：</label>
                <div class="layui-input-block">
                    <input type="text" id = "clsName" name="className" class="layui-input" lay-verify="required" disabled = "disabled">
                </div>
            </div>


            <div class="layui-form-item">
                <label class="layui-form-label layui-required">课程代码：</label>
                <div class="layui-input-block">
                    <input type="text" id = "courseCode" name="courseCode" class="layui-input"  disabled = "disabled">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-required">评分：</label>
                <div class="layui-input-block">
                    <input type="text" id="marks" name="marks" class="layui-input" lay-verify="required|number">
                </div>
            </div>
            <div class="layui-form-item" style="display: none;">
                <div class="layui-input-block">
                    <button id="addSubmitBtn" type="button" class="layui-btn layui-btn-normal" lay-submit lay-filter="addSubmit">提交</button>
                </div>
            </div>
        </form>
    </div>
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
        var teacherNo = ${user.username}
        var tableObj = table.render({
            elem: '#GradeTable'
            ,url: '/teacher/getMyStudentGrade?teacherNo='+teacherNo
            ,toolbar: true
            , parseData: function (res) { //解析服务器端返回的数据
                console.log(res)
            }
            ,title: '学生成绩表'
            ,page: true
            ,response: { //自定义数据状态的字段名称
                statusName: 'code',
                statusCode: 200
            }
            ,cols: [[
                {type: 'checkbox', fixed: 'left'}
                ,{field:'id', title:'ID', fixed: 'left',width:80, sort: true}
                ,{field:'stuNo', title:'学号', width:120}
                ,{field:'realName', title:'真实姓名',width:120}
                ,{field:'className', title:'班级',width:120}
                ,{field:'courseName', title:'课程名称', width:150, sort: true}
                ,{field:'courseCode', title:'课程代码', width:120}
                ,{field:'collegeName', title:'院系',width:120}
                ,{field:'marks', title:'成绩',width:120,sort: true}
                ,{field:'flag', title:'标记', width:120,templet : function(d) {
                        if (d.flag == 0)
                            return "<div class='not'>未打分</div>";
                        else
                            return "<div class='is'>已打分</div>";
                    }}
                ,{fixed: 'right', title:'操作', toolbar: '#bar', width:150}
            ]]
            ,id: 'testReload'

        });


        //监听工具栏事件
        table.on('tool(GradeTable)', function(obj) {
            var data = obj.data;
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            if (data.flag == 1){
               return false;
            }
            if(layEvent === 'edit'){ //
                layer.open({
                    type: 1,
                    title: '教师评分',
                    skin: 'layui-layer-rim',
                    resize: true,
                    maxmin: true,   //开启大小化
                    offset: 'auto',
                    btn: ['提交'],
                    btnAlign: 'c',
                    shade: 0.4,   //遮罩透明度
                    area: '600px',
                    width: '600px',
                    content: $('#test').html() ,//这里content是一个普通的String,
                    success: function(layero, index){
                        console.log(data)
                        //回显数据
                        $("#id").val(data.id);
                        $("#stuNo").val(data.stuNo);
                        $("#courseCode").val(data.courseCode);
                        $("#clsName").val(data.className);
                        $("#realName").val(data.realName);
                        form.render();  //表单渲染

                        //提交数据
                        form.on('submit(addSubmit)',function (formData) {
                            console.log(formData)
                            $.post("/teacher/MakeGrade",formData.field,function (res) {
                                console.log("响应"+res.msg)
                                if(res.code == 200){//成功
                                    layer.close(index); //关闭弹层
                                    layer.msg("打分成功")
                                    tableObj.reload();//重新刷新数据表格
                                }else { //操作失败
                                    layer.msg(res.msg)
                                    $("#MakeGrade")[0].reset();
                                }
                            });
                        })



                    },
                    yes: function (index,layero) {
                        layero.find('#addSubmitBtn').click();
                    }
                });


            }
        });


        //layui重载搜索
        // 执行搜索，表格重载
        $('.search').on('click', function () {
            var table = layui.table;
            // 搜索条件
            table.reload('testReload',{
                method: 'get'
                , url: '/teacher/getStudentGradeByCondition?teacherNo='+teacherNo
                , where: {
                    'stuNo':$('#username').val().trim(),
                    'courseCode':$('#courseco').val().trim(),
                    'className':$('#className').val().trim(),
                    'flag':$('#flag').val().trim()
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