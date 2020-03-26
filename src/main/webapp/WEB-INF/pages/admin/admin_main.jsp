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
    <style>
        img{
            background-size: cover;
            width: 100%;
        }
    </style>
</head>
<body>
    <div class="layui-carousel" id="lun">
        <div carousel-item>
            <div>
                <img src="../../img/sch01.png">
            </div>
            <div>
                <img src="../../img/sch02.png">
            </div>
            <div>
                <img src="../../img/sch03.png">
            </div>
            <div>
                <img src="../../img/sch04.png">
            </div>
            <div>
                <img src="../../img/sch05.png">
            </div>
            <div>
                <img src="../../img/sch06.png">
            </div>
            <div>
                <img src="../../img/sch07.png">
            </div>
            <div>
                <img src="../../img/sch08.png">
            </div>
        </div>
    </div>

   <div class="container">
        <div class="layui-row">
            校园新闻展示区,后期再说
        </div>
    </div>


<script src="../../layuiadmin/layui/layui.js"></script>
<script>
    layui.use(['carousel','jquery'], function(){
        var carousel = layui.carousel;
        var $ = layui.jquery;
        //建造实例
        carousel.render({
            elem: '#lun'
            ,width: '100%' //设置容器宽度
            ,arrow: 'always' //始终显示箭头
            ,height: '690px'
            //,anim: 'updown' //切换动画方式
        });
    });
</script>
</body>
</html>