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
                    <h2><i>Tips: 上传图片可以多张上传，事务列表仅显示第一张</i></h2>
                </span>

            </div>
        </div>
    </div>
    <div class="layui-row">
        <div class="layui-col-md12">
            <div class="layui-col-md10 layui-col-md-offset1">
                <form class="layui-form" id="" method="post" action="">
                    <input type="hidden" id = "id" name="username" class="layui-input" value="${user.username}">
                    <div class="layui-form-item">
                        <label class="layui-form-label">标题：</label>
                        <div class="layui-input-block">
                            <input type="text" id = "title" name="title" class="layui-input" lay-verify="required">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label layui-required">提交内容：</label>
                        <div class="layui-input-block">
                            <textarea id="con" name="content" style="display: none;" lay-verify="content"></textarea>
                        </div>
                    </div>

                    <div class="layui-form-item">
                                <label class="layui-form-label">附加图片：</label>
                                <div class="layui-input-block">
                                    <button type="button" class="layui-btn" id="choiceImg">
                                        <i class="layui-icon">&#xe67c;</i>选择图片
                                    </button>
                                    <button type="button" class="layui-btn" id="startUpload">开始上传</button>
                                </div>
                          <button type="button" class="layui-btn" id="cleanImgs">
                        <i class="fa fa-trash-o fa-lg">
                        </i>清空图片预览</button>
                                <blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;">
                                    预览图：
                                    <div class="layui-upload-list" id="demo2"></div>
                                </blockquote>
                    </div>
                        
                        <input type="text" id="img" name="img" style="display: none;" class="layui-input">

                    <div class="layui-form-item">
                        <div class="layui-input-block">
                            <button id="addSubmitBtn" type="button" class="layui-btn layui-btn-normal" lay-submit lay-filter="addSubmit">提交</button>
                        </div>
                    </div>

                </form>
            </div>
        </div>
    </div>

</div>

<script src="../../layuiadmin/layui/layui.js"></script>
<script src="../../js/jquery.min.js"></script>
<script>

    var success=0;
    var fail=0;
    var imgurls="";

    $(function (){
        var imgsName="";
        layui.use(['upload','layer','jquery','layedit','form'],function() {
            var $ = layui.jquery;
            var layer = layui.layer;
            var form = layui.form;
            var layedit = layui.layedit;
            var upload = layui.upload;
         var  index =  layedit.build("con")
            form.verify({
                content:function (value) {
                    return layedit.sync(index);
                }
            })
            upload.render({
                elem: '#choiceImg',
                url: '/student/upload',
                multiple: true,
                auto:false,
//			上传的单个图片大小
                size:102400,
//			最多上传的数量
                number:20,
//			MultipartFile file 对应，layui默认就是file,要改动则相应改动
                field:'file',
                bindAction: '#startUpload',
                before: function(obj) {
                    //预读本地文件示例，不支持ie8
                    obj.preview(function(index, file, result) {
                        $('#demo2').append('<img src="' + result
                            + '" alt="' + file.name
                            +'"height="92px" width="92px" class="layui-upload-img uploadImgPreView">')
                    });
                },
                done: function(res, index, upload) {
                    //每个图片上传结束的回调，成功的话，就把新图片的名字保存起来，作为数据提交
                    console.log(res.code);
                    if(res.code== 500){
                        fail++;
                    }else{

                        success++;
                        imgurls=imgurls+""+res.data.src+",";
                        $('#img').val(imgurls);
                        console.log(imgurls)   //图片集合
                    }
                },
                allDone:function(obj){
                    layer.msg("总共要上传图片总数为："+(fail+success)+"\n"
                        +"其中上传成功图片数为："+success+"\n"
                        +"其中上传失败图片数为："+fail
                    )
                }
            });

            $("#addSubmitBtn").click(function (index) {
                var layer = layui.layer;
                var form = layui.form;

                form.on('submit(addSubmit)',function (formdata) {
                    $.post("/student/saveTran",formdata.field,function (res) {
                        if (res.code == 200){
                            layer.msg("提交成功")
                        }else {
                            layer.msg("提交失败")
                        }
                    })
                });
            });


            $("#cleanImgs").click(function () {
                success=0;
                fail=0;
                $('#demo2').html("");
                $('#imgUrls').val("");
            })



        });

    //清空预览图片
       // cleanImgsPreview();
        //保存商品
       // saveTran();
    });
</script>
</body>
</html>