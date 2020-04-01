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
        .lunbo{
            height: 300px;
            background-color: #00FFFF;
        }
        .notice{
            border: 1px solid red;
            height: 200px;
            overflow: auto;
          }
        img{width: 100%}
        .not{margin-top: 30px}
        #title>li{
            margin: 15px;
            border-bottom: 1px solid black;
            cursor: pointer;
        }
        #title>li:hover{
           color: orangered;
        }
        .top_right{
            border: 1px solid red;
            height: 200px;
        }
        .top{
            margin-top: 20px;
        }
        p{
            text-indent: 2em; /*em是相对单位，2em即现在一个字大小的两倍*/
        }
        #date{margin-top: 20px}
        #content{
            margin-top: 25px;
        }
    </style>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-row topTip">
        <div class="layui-col-md12">
            <div class="lunbo layui-carousel" id="lunbo">
                <div carousel-item>
                    <div>
                        <img src="../../img/notice.png">
                    </div>
                    <div>
                        <img src="../../img/notice.png">
                    </div >
                    <div>
                        <img src="../../img/notice.png">
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="layui-row not">
        <div class="layui-col-md6">
            <div class="notice layui-form">

                <div class="layui-col-lg-offset6">
                    所有通知
                </div>
                <ul id="title">
                </ul>
            </div>
        </div>
        <div class="layui-col-md6 top_right">
            <div class="right">
                <ul id="right">
                </ul>
            </div>
        </div>
    </div>

</div>


<script type="text/html" id="test" >
    <div class="layui-col-md12">
        <form class="layui-form">

            <input type="hidden" id = "id" name="id" class="layui-input">
            <div class=" top">
                <h2 id="tit" style="text-align: center"></h2>
            </div>
            <div class="layui-col-lg-offset7 date">
                <h5 id="date"></h5>
            </div>


            <div class="layui-form-item" id="content">
                <p id="con"></p>
            </div>
        </form>
    </div>
</script>



<script src="../../layuiadmin/layui/layui.js"></script>
<script>
    layui.use(['carousel','jquery','form'], function(){
        var carousel = layui.carousel;
        var $ = layui.jquery;
        var form = layui.form;
        //建造实例
        carousel.render({
            elem: '#lunbo'
            ,width: '100%' //设置容器宽度
            ,arrow: 'always' //始终显示箭头
            ,height: '300px'
            //,anim: 'updown' //切换动画方式
        });


        //通知渲染
        $(function () {
            $.get("/admin/getRecentNotice",function (res) {
                var data = res.data;
                for (var i= 0;i<data.length;i++){
                    $("#title").append("<li onclick=detail("+data[i].id+")>"+data[i].title+"</li>")
                }
            })
        })
    });

    function detail(id) {
        layui.use(['element','jquery','layer','form'],function () {
            var $ = layui.jquery;
            var form = layui.form;

            layer.open({
                type: 1,
                title: '查看通知详情',
                skin: 'layui-layer-rim',
                resize: true,
                maxmin: true,   //开启大小化
                offset: 'auto',
                btnAlign: 'c',
                shade: 0.4,   //遮罩透明度
                area: ['600px','620px'],
                content: $('#test').html() ,//这里content是一个普通的String,
                success: function(layero, index){
                    $.get("/admin/getNoticeDetail",{"id":id},function (res) {
                        console.log(res)
                        $("#tit").html(res.data.title);
                        $("#con").html(res.data.content)
                        $("#date").html("发布日期:"+res.data.updateDate)
                    })
                    form.render();  //表单渲染
                }
            });

        });
    }
</script>
</body>
</html>