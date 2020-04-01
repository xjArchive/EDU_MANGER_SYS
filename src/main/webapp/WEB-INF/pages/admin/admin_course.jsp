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
                            <label class="layui-form-label">课程代码</label>
                            <div class="layui-inline">
                                <input class="layui-input" name="courseCode" id="courseCode" autocomplete="off">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <button class="layui-btn search" data-type="reload">搜索</button>
                        </div>
                        <div class="layui-inline">
                            <div class="layui-inline layui-col-md-offset8">
                                <button    lay-submit lay-filter="addCourse" class="layui-btn" id ="addBtn"><i class="layui-icon">&#xe608;</i>添加课程</button>
                            </div>
                        </div>

                        <div class="layui-inline">
                            <div class="layui-inline layui-col-md-offset8">
                                <button    lay-submit lay-filter="addType" class="layui-btn" id ="addTypeBtn"><i class="layui-icon">&#xe608;</i>添加课程类型</button>
                            </div>
                        </div>

                    </div>

                    <table class="layui-hide" id="courseTable" lay-filter="courseTable"></table>
                    <script type="text/html" id="bar">
                        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
                        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
                    </script>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/html" id="addType" >
    <div class="layui-col-md12">
        <form class="layui-form" id="AddCourseType" method="post" action="">

            <div class="layui-form-item">
                <label class="layui-form-label layui-required">课程类型名称：</label>
                <div class="layui-input-block">
                    <input type="text" id="name" name="name" class="layui-input" lay-verify="required">
                </div>
            </div>
            <div class="layui-form-item" style="display: none;">
                <div class="layui-input-block">
                    <button id="addTpBtn" type="button" class="layui-btn layui-btn-normal" lay-submit lay-filter="addTpBtn">提交</button>
                </div>
            </div>
        </form>
    </div>
</script>


<script type="text/html" id="test" >
    <div class="layui-col-md12 addForm">
        <form class="layui-form" id="AddCourse" method="post" action="">

            <input type="hidden" id = "id" name="id" class="layui-input">

            <div class="layui-form-item">
                <label class="layui-form-label layui-required">课程名称：</label>
                <div class="layui-input-block">
                    <input type="text" id = "courseno" name="courseName" class="layui-input" lay-verify="required">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-required">课程代码：</label>
                <div class="layui-input-block">
                    <input type="text" id="courseco" name="courseCode" class="layui-input" lay-verify="required" placeholder="课程流水号（你只需要输入数字，后台默认会在其前面加上NCHK1n(n代表课程类型，必修就是1)）">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label layui-required">课程类型：</label>
                <div class="layui-input-block">
                    <select name="courseType" id="coursetp" lay-filter="mySelect" class="mySelect" lay-verify="required">
                        <option value="" >请选择课程类型</option>
                    </select>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label layui-required">是否参与选课：</label>
                <div class="layui-input-block">
                    <select name="type" id="type"  lay-verify="required">
                        <option value="0" >专业必修</option>
                        <option value="1" >全院任选</option>
                    </select>
                </div>
            </div>

           <%-- <div class="layui-form-item">
                <label class="layui-form-label layui-required">上课时间：</label>
                <div class="layui-input-block">
                    <input type="text" id="startTime" name="startTime" class="layui-input" lay-verify="required" placeholder="第x周到第x周，周一">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label layui-required">上课地点：</label>
                <div class="layui-input-block">
                    <input type="text" id="address" name="address" class="layui-input" lay-verify="required" placeholder="教室号，如科301">
                </div>
            </div>--%>

            <div class="layui-form-item">
                <label class="layui-form-label layui-required">课程学分：</label>
                <div class="layui-input-block">
                    <input type="text" id="score" name="score" class="layui-input" lay-verify="required|number">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label layui-required">课时：</label>
                <div class="layui-input-block">
                    <input type="text" id="courseLong" name="courseLong" class="layui-input" lay-verify="required" placeholder="x周，如18周">
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
        var tableObj = table.render({
            elem: '#courseTable'
            ,url: '/admin/getAllCourse'
            ,toolbar: true
            , parseData: function (res) { //解析服务器端返回的数据
                console.log(res)
            }
            ,title: '课程数据表'
            ,page: true
            ,response: { //自定义数据状态的字段名称
                statusName: 'code',
                statusCode: 200
            }
            ,cols: [[
                ,{field:'courseName', title:'课程名称',}
                ,{field:'courseCode', title:'课程代码'}
                ,{field:'name', title:'课程类型'}
                ,{field:'startTime', title:'上课时间'}
                ,{field:'address', title:'上课地点'}
                ,{field:'type', title:'是否是选课',templet : function(d) {
                        if (d.type == 0)
                            return "<div class='not'>否</div>";
                        else
                            return "<div class='is'>是</div>";
                    }}
                ,{field:'score', title:'学分', sort: true}
                ,{field:'courseLong', title:'课时',sort: true}
                ,{field:'createDate', title:'创建日期', unresize: true, sort: true}
                ,{field:'updateDate', title:'更新日期', unresize: true, sort: true}
                ,{fixed: 'right', title:'操作', toolbar: '#bar', width:150}
            ]]
            ,id: 'testReload'

        });


        //监听工具栏事件
        table.on('tool(courseTable)', function(obj) {
            var data = obj.data;
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            if(layEvent === 'del'){ //删除
                layer.confirm('确定删除该课程么？',{icon: 3, title:'温馨提示'}, function(index){
                    obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                    layer.close(index);
                    console.log(data.id)
                    //向服务端发送删除请求
                    $.post("/admin/delCourseById",{courseId:data.id},function (res) {
                        if (res.code == 200){ //删除成功
                            layer.msg("删除成功");
                            obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                            layer.close(index);
                            tableObj.reload();//重新刷新数据表格
                        }else {
                            layer.msg(res.msg);
                            layer.close(index);
                            tableObj.reload();//重新刷新数据表格
                        }
                    })
                });
            }
            if(layEvent === 'edit'){ //编辑

                $.get("/admin/getCourseType",function (res) {
                    if (res.code == 200){
                        for(var i =0;i<res.data.length;i++) {
                            $("#coursetp").append("<option value=\"" + res.data[i].id + "\">" + res.data[i].name + "</option>");
                        }
                        layui.form.render("select");
                    }
                });

                layer.open({
                    type: 1,
                    title: '编辑课程信息',
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
                        //回显数据

                        $('#id').val(data.id)
                        $('#courseno').val(data.courseName)
                        $('#courseco').val(data.courseCode)
                       // $('#startTime').val(data.startTime)
                       // $('#address').val(data.address)
                        $('#score').val(data.score)
                        $('#courseLong').val(data.courseLong)
                        $('#coursetp').val(data.courseType)
                        $('#courseco').attr("disabled","disabled")
                        form.render();  //表单渲染

                        //提交数据
                        form.on('submit(addSubmit)',function (formData) {
                            console.log(formData)
                            $.post("/admin/UpdateCourse",formData.field,function (res) {
                                console.log("响应"+res.msg)
                                if(res.code == 200){//成功
                                    layer.msg("修改成功")
                                    layer.close(index); //关闭弹层
                                    tableObj.reload();//重新刷新数据表格
                                }else { //操作失败
                                    layer.msg(res.msg)
                                    $("#AddCourse")[0].reset();
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
                , url: '/admin/getCourseListByCondition'
                , where: {
                    'courseName':$('#courseName').val().trim(),
                    'courseCode':$('#courseCode').val().trim()
                }
                , page: {
                    curr: 1
                }
            });
        });


        //弹出添加页面
        $("#addBtn").click(function(){

            //获取课程类型列表进而渲染多选框
            $.get("/admin/getCourseType",function (res) {
                if (res.code == 200){
                    for(var i =0;i<res.data.length;i++) {
                        $("#coursetp").append("<option value=\"" + res.data[i].id + "\">" + res.data[i].name + "</option>");
                    }
                    layui.form.render("select");
                }
            });


            layer.open({
                type: 1,
                title: '添加课程',
                skin: 'layui-layer-rim',
                resize: true,
                maxmin: true,   //开启大小化
                offset: 'auto',
                btn: ['提交'],
                btnAlign: 'c',
                shade: 0.4,   //遮罩透明度
                area: ['600px','350px'],
                content: $('#test').html() ,//这里content是一个普通的String,
                success: function(layero, index){

                    form.render();  //表单渲染
                    form.on('submit(addSubmit)',function (data) {
                        console.log(data)
                        $.post("/admin/AddCourse",data.field,function (res) {
                            if(res.code == 10108){//课程已存在
                                layer.msg(res.msg)
                                $("#AddCourse")[0].reset();

                            }else if (res.code == 200){
                                layer.msg("添加成功")
                                layer.close(index); //关闭弹层
                                tableObj.reload();//重新刷新数据表格

                            }else if (res.code == 500){
                                layer.msg(res.msg)
                            }
                        });
                    })

                },
                yes: function (index,layero) {
                    layero.find('#addSubmitBtn').click();
                }
            });

        })

        $("#addTypeBtn").click(function () {

            layer.open({
                type: 1,
                title: '添加课程类型',
                skin: 'layui-layer-rim',
                resize: true,
                maxmin: true,   //开启大小化
                offset: 'auto',
                btn: ['提交'],
                btnAlign: 'c',
                shade: 0.4,   //遮罩透明度
                area: ['350px','200px'],
                content: $('#addType').html() ,//这里content是一个普通的String,
                success: function(layero, index){

                    form.render();  //表单渲染
                    form.on('submit(addTpBtn)',function (data) {
                        console.log(data)
                        $.post("/admin/AddCourseType",data.field,function (res) {
                            console.log(data)
                            if(res.code == 10107){//班级已存在
                                layer.msg(res.msg)
                                $("#AddCourseType")[0].reset();

                            }else if (res.code == 200){
                                layer.close(index); //关闭弹层
                                layer.msg("添加成功")

                            }
                        });
                    })

                },
                yes: function (index,layero) {
                    layero.find('#addTpBtn').click();
                }
            });

        })

    });
</script>
</body>
</html>