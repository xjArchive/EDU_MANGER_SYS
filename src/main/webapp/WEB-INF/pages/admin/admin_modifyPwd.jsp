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
        .topTip {
            margin-top: 50px;
            height: 100px;
        }
        h2{
            color: red;
        }
    </style>
</head>
<body>
<div class="layui-container">
    <div class="layui-row topTip">
        <div class="layui-col-md12">
            <div class="layui-col-md4 layui-col-md-offset4">
                <span>
                    <h2><i>Tips: 重置密码(请使用绑定过的手机号便于接收验证码)</i></h2>
                </span>

            </div>
        </div>
    </div>
    <div class="layui-row">
        <div class="layui-col-md12">
            <div class="layui-col-md10 layui-col-md-offset1">
                <form class="layui-form" id="" method="post" action="">
                    <input type="hidden" id = "id" name="id" value="${user.id}" class="layui-input">
                    <div class="layui-form-item">
                        <label class="layui-form-label">新密码：</label>
                        <div class="layui-input-block">
                            <input type="password" id="newPassword" name="newPassword" class="layui-input" lay-verify = "required">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label layui-required">手机号码：</label>
                        <div class="layui-input-block">
                            <input type="text" id="mobile" name="mobile" class="layui-input" lay-verify = "phone|required">
                        </div>
                        <div class="layui-input-block">
                            <input type="button" class="layui-btn obtain generate_code" value=" 获取验证码" id="getCode" lay-event="getCode">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label layui-required">验证码：</label>
                        <div class="layui-input-block">
                            <input type="text" id="verifyCode" name="verifyCode" class="layui-input" lay-verify = "required|number">
                        </div>

                    </div>

                    <div class="layui-form-item" style="text-align: center">
                        <div class="layui-input-block">
                            <button id="addSubmitBtn" type="button" class="layui-btn layui-btn-normal" lay-submit lay-filter="addSubmit">确定</button>
                        </div>
                    </div>

                </form>
            </div>
        </div>
    </div>

</div>


<script src="../../layuiadmin/layui/layui.js"></script>
<script>
    layui.use(['element','laydate','form','jquery','layer'], function(){
        var $ = layui.jquery;
        var layer = layui.layer;
        var element = layui.element;
        var form = layui.form;
        var obj =  $("#getCode")
        var wait=60; //初始化发送频率
        obj.click(function () {
            var mobile = $("#mobile").val()
            if (!new RegExp("^1[345789]\\d{9}$").test(mobile)){
                layer.msg("请填写正确格式的手机号码");
                return false;
            }
            $.post("/sendCode",{mobile: mobile},function (res) {
                if (res.code == 200){
                    layer.msg("发送成功")
                }
            });
            setTime(this)
        });
        function setTime(obj) {
            if (wait == 0) {
                obj.removeAttribute("disabled");
                obj.value="获取验证码";
                wait = 60;
            } else {
                obj.setAttribute("disabled", true);
                obj.value="重新发送(" + wait + ")";
                wait--;
                setTimeout(function() {
                    setTime(obj)
                },1000)}
        }

        form.on('submit(addSubmit)',function (data) {
            $.post("/modifyPwd",data.field,function (res) {
                if (res.code == 200){
                    layer.msg("重置密码成功")
                    window.location.href="../../login.jsp";
                }else {
                    layer.msg(res.msg)
                }
            })
        })





    });
</script>
</body>
</html>