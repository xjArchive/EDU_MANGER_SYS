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
                        <label class="layui-form-label">用户名</label>
                        <div class="layui-inline">
                            <input class="layui-input" name="username" id="userNameReload" autocomplete="off">
                        </div>
                     </div>
                     <div class="layui-inline">
                        <label class="layui-form-label">身份证</label>
                        <div class="layui-inline">
                            <input class="layui-input" name="idCard" id="idCard" autocomplete="off">
                        </div>
                     </div>
                     <div class="layui-inline">
                        <button class="layui-btn search" data-type="reload">搜索</button>
                     </div>
                     <div class="layui-inline">
                        <div class="layui-inline layui-col-md-offset8">
                            <button    lay-submit lay-filter="addTeacher" class="layui-btn" id ="addBtn"><i class="layui-icon">&#xe608;</i>添加</button>
                        </div>
                     </div>

                    </div>

                    <table class="layui-hide" id="teacherTable" lay-filter="teacherTable"></table>
                    <script type="text/html" id="bar">
                        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
                        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
                    </script>
                </div>
            </div>
        </div>
    </div>
</div>



<script type="text/html" id="test" >
    <div class="layui-col-md12">
        <form class="layui-form" id="AddTeacher" method="post" action="">

            <input type="hidden" id = "id" name="id" class="layui-input">

            <div class="layui-form-item">
                <label class="layui-form-label layui-required">工号：</label>
                <div class="layui-input-block">
                    <input type="text" id = "username" name="username" class="layui-input" lay-verify="required|number">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-required">姓名：</label>
                <div class="layui-input-block">
                    <input type="text" id="realName" name="realName" class="layui-input" lay-verify="required">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">性别：</label>
                <div class="layui-input-block">
                    <input type="radio"  name="sex" value="1" title="男" checked>
                    <input type="radio" name="sex" value="0" title="女">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">出生年月：</label>
                <div class="layui-input-block">
                    <input  id="birth" name="birth" autocomplete="off"
                            class="layui-input" placeholder="yyyy-MM-dd" lay-key="2">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-required">手机号码：</label>
                <div class="layui-input-block">
                    <input type="text" id="mobile" name="mobile" class="layui-input" lay-verify="required|phone">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-required">学历：</label>
                <div class="layui-input-block">
                    <select name="degree" id="degree">
                        <option value="">请选择学历</option>
                        <option value="本科">本科</option>
                        <option value="专科">专科</option>
                        <option value="高中及初中以下">高中及初中以下</option>
                        <option value="高中及初中以下">研究生</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-required">职称：</label>
                <div class="layui-input-block">
                    <select name="level" id="level">
                        <option value="">请选择职称</option>
                        <option value="初级">初级</option>
                        <option value="中级">中级</option>
                        <option value="高级">高级</option>
                        <option value="特级">特级</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-required">院系：</label>
                <div class="layui-input-block">
                    <select name="collegeId" id="collegeId">
                        <option value="">请选择院系</option>
                        <option value="1">计算机系</option>
                        <option value="2">机械系</option>
                        <option value="3">土木工程系</option>
                        <option value="4">空乘系</option>
                        <option value="5">电子商务</option>
                        <option value="6">会计</option>
                        <option value="7">外语</option>
                        <option value="8">临川医学</option>
                        <option value="9">播音主持</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-required">身份证：</label>
                <div class="layui-input-block">
                    <input type="text" id="formIdcard" name="idCard" class="layui-input identity">
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
            elem: '#teacherTable'
            ,url: '/admin/getAllTeacher'
            ,toolbar: true
            , parseData: function (res) { //解析服务器端返回的数据
                console.log(res)
            }
            ,title: '用户数据表'
            ,page: true
            ,response: { //自定义数据状态的字段名称
                statusName: 'code',
                statusCode: 200
            }
            ,cols: [[
                {type: 'checkbox', fixed: 'left'}
                ,{field:'id', title:'ID', fixed: 'left', unresize: true, sort: true}
                ,{field:'username', title:'用户名', width:100}
                ,{field:'realName', title:'真实姓名',width:120}
                ,{field:'birth', title:'出生年月', width:150, sort: true}
                ,{field:'mobile', title:'电话', width:120}
                ,{field:'degree', title:'学历'}
                ,{field:'idCard', title:'身份证', width:150, sort: true}
                ,{field:'sex', title:'性别', width:20,templet : function(d) {
                        if (d.sex == 0)
                            return "<div class='woman'>女</div>";
                        else
                            return "<div class='man'>男</div>";
                    }}
                ,{field:'createDate', title:'创建日期', width:120, sort: true}
                ,{fixed: 'right', title:'操作', toolbar: '#bar', width:150}
            ]]
            ,id: 'testReload'

        });


        //监听工具栏事件
        table.on('tool(teacherTable)', function(obj) {
            var data = obj.data;
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            if(layEvent === 'del'){ //删除
                layer.confirm('确定删除该条信息么？',{icon: 3, title:'温馨提示'}, function(index){
                    obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                    layer.close(index);
                    console.log(data.id)
                    //向服务端发送删除请求
                    $.post("/admin/delUserById",{userId:data.id})
                });
            }
            if(layEvent === 'edit'){ //删除
                layer.open({
                    type: 1,
                    title: '编辑教师',
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
                        //日期组件渲染
                        var laydate = layui.laydate;
                        laydate.render({
                            elem: '#birth' //指定元素
                            ,trigger: 'click'
                        });
                        //回显数据
                        $("#id").val(data.id);
                        $("#level").val(data.level);
                        $("#degree").val(data.degree);
                        $("#username").val(data.username);
                        $("#realName").val(data.realName);
                        $("#formIdcard").val(data.idCard);
                        $("#birth").val(data.birth);
                        $("#mobile").val(data.mobile);
                        $("#collegeId").val(data.collegeId);
                        $("input[name='sex'][value="+data.sex+"]").prop("checked",true);
                        form.render();  //表单渲染

                        //提交数据
                        form.on('submit(addSubmit)',function (formData) {
                            console.log(formData)
                            $.post("/admin/UpdateTeacher",formData.field,function (res) {
                                console.log("响应"+res.msg)
                                if(res.code == 200){//成功
                                    layer.msg("修改成功")
                                    layer.close(index); //关闭弹层
                                    tableObj.reload();//重新刷新数据表格
                                }else { //操作失败
                                    layer.msg(res.msg)
                                    $("#AddTeacher")[0].reset();
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
                , url: '/admin/getListByCondition'
                , where: {
                    'username':$('#userNameReload').val().trim(),
                   'idCard':$('#idCard').val().trim()
                }
                , page: {
                    curr: 1
                }
            });
        });


        //弹出添加页面
        $("#addBtn").click(function(){
            layer.open({
                type: 1,
                title: '添加教师',
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
                    //日期组件渲染
                    var laydate = layui.laydate;
                    laydate.render({
                        elem: '#birth' //指定元素
                        ,trigger: 'click'
                    });
                    form.render();  //表单渲染
                    form.on('submit(addSubmit)',function (data) {
                        console.log(data)
                        $.post("/admin/AddTeacher",data.field,function (res) {
                            console.log("响应"+res.msg)
                            if(res.code == 10101){//用户已存在
                                layer.msg(res.msg)
                                $("#AddTeacher")[0].reset();

                            }else if (res.code == 10102){ //身份证被占用
                                layer.msg(res.msg)
                                $("#AddTeacher")[0].reset();
                            }else if (res.code == 200){
                                layer.msg("添加成功")
                                layer.close(index); //关闭弹层
                                tableObj.reload();//重新刷新数据表格

                            }
                        });
                    })

                },
                yes: function (index,layero) {
                    layero.find('#addSubmitBtn').click();
                }
            });

        })

    });
</script>
</body>
</html>