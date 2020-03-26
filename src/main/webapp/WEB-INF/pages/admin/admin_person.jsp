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
            margin-top: 30px;
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
                    <h2><i>Tips: 以下是您的基本信息,根据提示可以进行编辑，及修改密码等操作</i></h2>
                </span>

            </div>
        </div>
    </div>
    <div class="layui-row">
        <div class="layui-col-md12">
            <div class="layui-col-md10 layui-col-md-offset1">
                 <form class="layui-form" id="" method="post" action="">
                     <input type="hidden" id = "id" name="id" class="layui-input">
                     <div class="layui-form-item">
                         <label class="layui-form-label">登录名(工号)：</label>
                         <div class="layui-input-block">
                    <input type="text" id = "username" name="username" class="layui-input" readonly>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-required">真实姓名：</label>
                <div class="layui-input-block">
                    <input type="text" id="realName" name="realName" class="layui-input" readonly>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label layui-required">手机号码：</label>
                <div class="layui-input-block">
                    <input type="text" id="mobile" name="mobile" class="layui-input" readonly>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label layui-required">身份证：</label>
                <div class="layui-input-block">
                    <input type="text" id="idCard" name="idCard" class="layui-input" readonly>
                </div>
            </div>
                     <div class="layui-form-item">
                         <label class="layui-form-label layui-required">出生年月：</label>
                         <div class="layui-input-block">
                             <input type="text" id="birth" name="birth" class="layui-input" readonly>
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
                <label class="layui-form-label layui-required">当前登录IP：</label>
                <div class="layui-input-block">
                    <input type="text" id="ip" name="IP" class="layui-input" readonly>
                </div>
            </div>

                 </form>
            </div>
        </div>
    </div>
    <div class="layui-row topTip">
        <div class="layui-col-md12">
            <div class="layui-col-md4 layui-col-md-offset5">
                <button id="edit" type="button" class="layui-btn layui-btn-normal">编辑</button>
            </div>
        </div>
    </div>

</div>

<script type="text/html" id="editTemplete" >
    <div class="layui-col-md12">
        <form class="layui-form" id="editInfo" method="post" action="">

            <input type="hidden" id = "uid" name="id" class="layui-input" value="${user.id}">

           <%-- <div class="layui-form-item">
                <label class="layui-form-label layui-required">登录账号：</label>
                <div class="layui-input-block">
                    <input type="text" id = "uname" name="username" class="layui-input" lay-verify="required">
                </div>
            </div>--%>
            <div class="layui-form-item">
                <label class="layui-form-label layui-required">真实姓名：</label>
                <div class="layui-input-block">
                    <input type="text" id="rname" name="realName" class="layui-input" lay-verify="required">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label layui-required">出生年月：</label>
                <div class="layui-input-block">
                    <input  id="bir" name="birth" autocomplete="off"
                            class="layui-input" placeholder="yyyy-MM-dd" lay-key="2">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">性别：</label>
                <div class="layui-input-block">
                    <input type="radio"  name="sex" value="1" title="男" checked>
                    <input type="radio" name="sex" value="0" title="女">
                </div>
            </div>

            <div class="layui-form-item" style="display: none;">
                <div class="layui-input-block">
                    <button id="editSubmitBtn" type="button" class="layui-btn layui-btn-normal" lay-submit lay-filter="editSubmitBtn">提交</button>
                </div>
            </div>
        </form>
    </div>
</script>


<script src="../../layuiadmin/layui/layui.js"></script>
<script>
    layui.use(['laydate','form','jquery','layer'], function(){
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.jquery;
        var element = layui.element;
        var form = layui.form;


        //页面加载时渲染个人数据
        $(function () {
            var userId = ${user.id}
                $.get("/admin/getPersonInfo",{id:userId},function (res) {
                    if (res.code == 200){
                        $('#id').val(res.data.id)
                        $('#username').val(res.data.username)
                        $('#realName').val(res.data.realName)
                        $('#mobile').val(res.data.mobile)
                        $('#idCard').val(res.data.idCard)
                        $('#birth').val(res.data.birth)
                        $('#ip').val(res.data.ip)
                        $("input[name='sex'][value="+res.data.sex+"]").prop("checked",true);
                    }
                });
        })

        /*编辑弹窗*/
        $('#edit').click(function () {

            layer.open({
                type: 1,
                title: '编辑个人信息',
                skin: 'layui-layer-rim',
                resize: true,
                maxmin: true,   //开启大小化
                offset: 'auto',
                btn: ['提交'],
                btnAlign: 'c',
                shade: 0.4,   //遮罩透明度
                area: '600px',
                width: '600px',
                content: $('#editTemplete').html() ,//这里content是一个普通的String,
                success: function(layero, index){
                    //日期组件渲染
                    var laydate = layui.laydate;
                    laydate.render({
                        elem: '#bir' //指定元素
                        ,trigger: 'click'
                    });
                    //回显数据
                    form.render();  //表单渲染

                    //提交数据
                    form.on('submit(editSubmitBtn)',function (formData) {
                        console.log(formData)
                        $.post("/admin/UpdatePersionInfo",formData.field,function (res) {
                            console.log("响应"+res.msg)
                            if(res.code == 200){//成功
                                layer.msg("修改成功")
                                layer.close(index); //关闭弹层
                                window.location.reload()
                            }else { //操作失败
                                layer.msg(res.msg)
                            }
                        });
                    })



                },
                yes: function (index,layero) {
                    layero.find('#editSubmitBtn').click();
                }


            });
        });

    });
</script>
</body>
</html>