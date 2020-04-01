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
        .tab_body{
            margin-top: 40px;
        }
        th{
           width: 80px;
            background-color: #bce8f1;
        }
        td{
            height: 90px;
            text-align: center;
            background-color: #00FFFF;
            color: dodgerblue;
            font-size: 80px;
        }

    </style>
</head>
<body>
<div class="layui-container">

    <div class="layui-row tab_body">
        <table class="layui-table tab">
            <thead>
            <tr>
                <th>节次</th>
                <th>星期一</th>
                <th>星期二</th>
                <th>星期三</th>
                <th>星期四</th>
                <th>星期五</th>
                <th>星期六</th>
                <th>星期日</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th>第1大节</th>
                <td id="table_1_1"></td>
                <td id="table_2_1"></td>
                <td id="table_3_1"></td>
                <td id="table_4_1"></td>
                <td id="table_5_1"></td>
                <td id="table_6_1"></td>
                <td id="table_7_1"></td>
            </tr>
            <tr>
                <th>第2大节</th>
                <td id="table_1_2"></td>
                <td id="table_2_2"></td>
                <td id="table_3_2"></td>
                <td id="table_4_2"></td>
                <td id="table_5_2"></td>
                <td id="table_6_2"></td>
                <td id="table_7_2"></td>
            </tr>
            <tr>
                <th>第3大节</th>
                <td id="table_1_3"></td>
                <td id="table_2_3"></td>
                <td id="table_3_3"></td>
                <td id="table_4_3"></td>
                <td id="table_5_3"></td>
                <td id="table_6_3"></td>
                <td id="table_7_3"></td>
            </tr>
            <tr>
                <th>第4大节</th>
                <td id="table_1_4"></td>
                <td id="table_2_4"></td>
                <td id="table_3_4"></td>
                <td id="table_4_4"></td>
                <td id="table_5_4"></td>
                <td id="table_6_4"></td>
                <td id="table_7_4"></td>
            </tr>
            <tr>
                <th>第5大节(晚自习)</th>
                <td id="table_1_5"></td>
                <td id="table_2_5"></td>
                <td id="table_3_5"></td>
                <td id="table_4_5"></td>
                <td id="table_5_5"></td>
                <td id="table_6_5"></td>
                <td id="table_7_5"></td>
            </tr>
            </tbody>
        </table>
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
        var userNo = ${user.username};
        console.log(userNo)

        //页面加载时渲染个人数据
        $(function () {
            var userId = ${user.id}
                $.get("/student/getMyCourse",{studentNo:userId},function (res) {

                    console.log(res)
                    var data = res.data;
                    //清空课程表
                    for(var i=1;i<6;i++){
                        for(var j=1;j<8;j++){
                            $("#table_"+j+"_"+i).html("");
                        }
                    }
                    //遍历课程表
                    for (var i=0;i<data.length;i++) {
                        $("#table_"+data[i].week+"_"+data[i].jieci).html(data[i].courseName+"<br>"+data[i].courseAddress+"<br>"+data[i].teacherName);
                    }

                });
        })

    });
</script>
</body>
</html>