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
                            <label class="layui-form-label">教师工号</label>
                            <div class="layui-inline">
                                <input class="layui-input" name="teacherNo" id="teacherNo" autocomplete="off" >
                            </div>
                        </div>

                        <div class="layui-inline">
                            <button class="layui-btn search" data-type="reload">搜索</button>
                        </div>
                        <div class="layui-inline">
                            <div class="layui-inline layui-col-md-offset8">
                                <button    lay-submit lay-filter="addCourse" class="layui-btn" id ="addBtn"><i class="layui-icon">&#xe608;</i>排课</button>
                            </div>
                        </div>

                    </div>

                    <table class="layui-hide" id="courseArrangeTable" lay-filter="courseArrangeTable"></table>
                    <script type="text/html" id="bar">
                        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
                        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
                    </script>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/html" id="addCourseArrage" >
    <div class="layui-col-md12">
        <form class="layui-form" id="AddCourseArrange" method="post" action="">
            <input type="hidden" id = "id" name="id" class="layui-input">
            <div class="layui-form-item">
                <label class="layui-form-label layui-required">上课地点：</label>
                <div class="layui-input-block">
                    <input type="text" id="caddress" name="courseAddress" class="layui-input" lay-verify="required">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label layui-required">课程名称：</label>
                <div class="layui-input-block">
                    <select name="courseCode" id="course" lay-filter="selectCourse" class="selectCourse" lay-verify="required">
                    </select>
                </div>
             </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-required">开课班级：</label>
                <div class="layui-input-block">
                    <select name="className" id="className" lay-filter="selectClass" class="selectClass" lay-verify="required">
                    </select>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label layui-required">授课教师：</label>
                <div class="layui-input-block">
                    <select name="username" id="teacher" lay-filter="selectTeacher" class="selectTeacher" lay-verify="required">
                    </select>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label layui-required">开课院系：</label>
                <div class="layui-input-block">
                    <select name="collegeName" id="college" lay-filter="selectCollege" class="selectCollege" lay-verify="required">

                    </select>
                </div>
            </div>


            <div class="layui-form-item" style="display: none;">
                <div class="layui-input-block">
                    <button id="addSubmit" type="button" class="layui-btn layui-btn-normal" lay-submit lay-filter="addSubmit">提交</button>
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
        var tableObj = table.render({
            elem: '#courseArrangeTable'
            ,url: '/admin/getCourseArrangeList'
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
                ,{field:'id', title:'ID', fixed: 'left', sort: true}
                ,{field:'courseName', title:'课程名称',}
                ,{field:'courseCode', title:'课程代码'}
                ,{field:'collegeName', title:'院系名称'}
                ,{field:'courseAddress', title:'上课地点'}
                ,{field:'teacherNo', title:'教师工号'}
                ,{fixed: 'right', title:'操作', toolbar: '#bar', width:150}
            ]]
            ,id: 'testReload'

        });



        //监听工具栏事件
        table.on('tool(courseArrangeTable)', function(obj) {
            var data = obj.data;
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            if(layEvent === 'del'){ //删除
                layer.confirm('确定删除该条信息么？',{icon: 3, title:'温馨提示'}, function(index){
                    console.log(data.id)
                    //向服务端发送删除请求
                    $.post("/admin/delCollegeArrangeById",{CourseArrangeId:data.id},function (res) {
                        console.log(res)
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
            if(layEvent === 'edit'){ //编辑


                //获取所有的课程
                $.get("/admin/getAllCourseList",function (res) {
                    if (res.code == 200){
                        for(var i =0;i<res.data.length;i++) {
                            $("#course").append("<option value=\"" + res.data[i].courseCode + "\">" + res.data[i].courseName + "</option>");
                        }
                        layui.form.render("select");
                    }
                });
                //获取所有的教师
                $.get("/admin/getAllTeacherList",function (res) {
                    if (res.code == 200){
                        for(var i =0;i<res.data.length;i++) {
                            $("#teacher").append("<option value=\"" + res.data[i].username + "\">" + res.data[i].realName + "</option>");
                        }
                        layui.form.render("select");
                    }
                });

                //获取所有的院系
                $.get("/admin/getCollegeList",function (res) {
                    if (res.code == 200){
                        for(var i =0;i<res.data.length;i++) {
                            $("#college").append("<option value=\"" + res.data[i].deptName + "\">" + res.data[i].deptName + "</option>");
                        }
                        layui.form.render("select");
                    }
                });

                //获取所有的班级
                $.get("/admin/getClassList",function (res) {
                    if (res.code == 200){
                        for(var i =0;i<res.data.length;i++) {
                            $("#className").append("<option value=\"" + res.data[i].className + "\">" + res.data[i].className + "</option>");
                        }
                        layui.form.render("select");
                    }
                });



                layer.open({
                    type: 1,
                    title: '编辑排课安排',
                    skin: 'layui-layer-rim',
                    resize: true,
                    maxmin: true,   //开启大小化
                    offset: 'auto',
                    btn: ['提交'],
                    btnAlign: 'c',
                    shade: 0.4,   //遮罩透明度
                    area: '600px',
                    width: '600px',

                    content: $('#addCourseArrage').html() ,//这里content是一个普通的String,
                    success: function(layero, index){
                        //回显数据
                        $("#id").val(data.id);
                        $("#caddress").val(data.courseAddress);
                        $("#className").val(data.className);
                        $("#teacher").val(data.username);
                        $("#college").val(data.collegeName);
                        form.render();  //表单渲染

                        //提交数据
                        form.on('submit(addSubmit)',function (formData) {
                            console.log(formData)
                            $.post("/admin/UpdateCollegeArrange",formData.field,function (res) {
                                console.log("响应"+res.msg)
                                if(res.code == 200){//成功
                                    layer.close(index); //关闭弹层
                                    layer.msg("修改成功")
                                    tableObj.reload();//重新刷新数据表格
                                }else { //操作失败
                                    layer.msg(res.msg)
                                    $("#AddCollege")[0].reset();
                                }
                            });
                        })



                    },
                    yes: function (index,layero) {
                        layero.find('#addSubmit').click();
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
                , url: '/admin/getCourseArrangeListByCondition'
                , where: {
                    'courseName':$('#courseName').val().trim(),
                    'collegeName':$('#collegeName').val().trim(),
                    'teacherNo':$('#teacherNo').val().trim(),
                }
                , page: {
                    curr: 1
                }
            });
        });



        //弹出排课页面
        $("#addBtn").click(function(){

            //获取所有的课程
            $.get("/admin/getAllCourseList",function (res) {
                if (res.code == 200){
                    for(var i =0;i<res.data.length;i++) {
                        $("#course").append("<option value=\"" + res.data[i].courseCode + "\">" + res.data[i].courseName + "</option>");
                    }
                    layui.form.render("select");
                }
            });
            //获取所有的教师
            $.get("/admin/getAllTeacherList",function (res) {
                if (res.code == 200){
                    for(var i =0;i<res.data.length;i++) {
                        $("#teacher").append("<option value=\"" + res.data[i].username + "\">" + res.data[i].realName + "</option>");
                    }
                    layui.form.render("select");
                }
            });

            //获取所有的院系
            $.get("/admin/getCollegeList",function (res) {
                if (res.code == 200){
                    for(var i =0;i<res.data.length;i++) {
                        $("#college").append("<option value=\"" + res.data[i].deptName + "\">" + res.data[i].deptName + "</option>");
                    }
                    layui.form.render("select");
                }
            });

            //获取所有的班级
            $.get("/admin/getClassList",function (res) {
                if (res.code == 200){
                    for(var i =0;i<res.data.length;i++) {
                        $("#className").append("<option value=\"" + res.data[i].className + "\">" + res.data[i].className + "</option>");
                    }
                    layui.form.render("select");
                }
            });


            layer.open({
                type: 1,
                title: '排课管理',
                skin: 'layui-layer-rim',
                resize: true,
                maxmin: true,   //开启大小化
                offset: 'auto',
                btn: ['提交'],
                btnAlign: 'c',
                shade: 0.4,   //遮罩透明度
                area: '600px',
                width: '600px',
                content: $('#addCourseArrage').html() ,//这里content是一个普通的String,
                success: function(layero, index){

                   /* //获取院系，并与班级进行联动
                    form.on('select(selectCollege)',function (resClass) {
                        var dpname = resClass.value
                        $.get("/admin/getClassList", {deptName: dpname}, function (res) {
                            if (res.code == 200) {
                                for (var i = 0; i < res.data.length; i++) {
                                    $("#className").append("<option value=\"" + res.data[i].className + "\">" + res.data[i].className + "</option>");
                                }
                                layui.form.render("select");
                            }
                        });
                    })*/
                    form.render() //表单渲染
                    form.on('submit(addSubmit)',function (data) {
                        console.log(data)
                        $.post("/admin/AddCourseArrange",data.field,function (res) {
                            console.log("响应"+res.msg)
                            if (res.code == 200){
                                layer.close(index); //关闭弹层
                                layer.msg("添加成功")
                                tableObj.reload();//重新刷新数据表格

                            }else {
                                layer.msg(res.msg)
                                $("#AddCourseArrange")[0].reset();
                            }
                        });
                    })

                },
                yes: function (index,layero) {
                    layero.find('#addSubmit').click();
                }
            });

        })


    });
</script>
</body>
</html>