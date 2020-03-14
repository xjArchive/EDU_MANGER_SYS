<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
	<head>
		<meta c*-harset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<title></title>
		<script src="../../js/jquery-2.0.3.min.js"></script>
		<link rel="stylesheet" href="../../layui/css/layui.css">
	</head>
	<body>
	<div class="layui-fluid">

		<div class="layui-row" >
			<div class="layui-col-md12" style="background-color: #00FF00">
				该位置暂不使用，待开发
			</div>
		</div>
		<div class="layui-row">
			<div class="layui-col-md8">

				<div class="layui-form">
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">用户名</label>
							<div class="layui-input-inline">
								<input   name="searchtype" autocomplete="off"
										 class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">身份证</label>
							<div class="layui-input-inline">
								<input   name="seachcontent" autocomplete="off"
										 class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<button    lay-submit lay-filter="searchquestionbtn" class="layui-btn "><i class="layui-icon">&#xe615;</i>查询</button>
						</div>
					</div>
				</div>
			</div>

            <div class="layui-col-md4">
                <div class="layui-inline layui-col-md-offset8">
                    <button    lay-submit lay-filter="addTeacher" class="layui-btn" id ="addBtn"><i class="layui-icon">&#xe608;</i>添加</button>
                </div>
            </div>
		</div>

		<div class="layui-row">
			<table id="teacherTable" lay-filter="teacherTable"></table>
		</div>
	</div>
    <%--添加教师表单模板--%>
    <!-- 需要弹出的添加员工界面 -->
    <div class="layui-row" id="test" style="display: none;">
        <div class="layui-col-md12">
            <form class="layui-form" id="AddTeacher" method="post" action="">
                <div class="layui-form-item">
                    <label class="layui-form-label">工号：</label>
                    <div class="layui-input-block">
                        <input type="text" name="username" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">姓名：</label>
                    <div class="layui-input-block">
                        <input type="text" name="realName" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">性别：</label>
                    <div class="layui-input-block">
                        <input type="radio" name="sex" value="男" title="男" checked>
                        <input type="radio" name="sex" value="女" title="女">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">手机号码：</label>
                    <div class="layui-input-block">
                        <input type="text" name="mobile" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">学历：</label>
                    <div class="layui-input-block">
                        <input type="text" name="degree" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">职称：</label>
                    <div class="layui-input-block">
                        <input type="text" name="level" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">身份证：</label>
                    <div class="layui-input-block">
                        <input type="text" name="idCard" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">出生年月：</label>
                    <div class="layui-input-block">
                        <input type="text" name="birth" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item" style="display: none;">
                    <div class="layui-input-block">
                        <button id="addSubmitBtn" type="button" class="layui-btn layui-btn-normal" lay-submit lay-filter="addSubmit">提交</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
	</body>

    <%--工具栏模板--%>
    <script type="text/html" id="toolbar">
        <button class="layui-btn layui-btn-sm" lay-event="delete">删除</button>
        <button class="layui-btn layui-btn-sm" lay-event="update">编辑</button>
        <button class="layui-btn layui-btn-sm" lay-event="detail">详情</button>
    </script>
	<script type="text/javascript" src="../../layui/layui.js"></script>
	<script type="text/javascript">
		layui.use([ 'element', "table" ,"laydate","form","jquery","layer"], function() {
		    var form = layui.form;
			var table = layui.table;
            var element = layui.element;
            var layer = layui.layer;
            var $ = layui.jquery;
			//第一个实例
			table.render({
				elem: '#teacherTable'
				,height: 312
				,url: '/admin/getAllTeacher' //数据接口
				,page: true //开启分页，
                ,toolbar: "#toolbar"
				,loading: true //是否显示加载条
				, parseData: function (res) { //解析服务器端返回的数据
					console.log(res)
				}
				,response: { //自定义数据状态的字段名称
							statusName: 'code',
							statusCode: 200
						}
				,cols: [[ //表头
					{type : "checkbox",fixed : 'left'}
					,{field: 'id', title: 'ID', sort: true, fixed: 'left'}
					,{field: 'username', title: '用户名'}
					,{field: 'realName', title: '真实姓名'}
					,{field: 'birth', title: '出生年月'}
					,{field: 'mobile', title: '电话'}
					,{field: 'degree', title: '学历'}
					,{field: 'idCard', title: '身份证'}
					,{field: 'sex', title: '性别'}
					,{field: 'createDate', title: '创建日期'}
                   ,{field: 'tool',title: '操作',align: 'center',toolbar: '#toolbar'}
				]]
			});


            //监听工具栏事件
            table.on('toolbar(teacherTable)', function(obj) {

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
                    area: '450px',
                    content: $('#test').html() ,//这里content是一个普通的String,
                    success: function(layero, index){
                        form.render();  //表单渲染
                        form.on('submit(addSubmit)',function (data) {
                            console.log(data)
                            $.post("../admin/AddTeacher",function () {
                                alert("请求后台成功")
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
</html>
