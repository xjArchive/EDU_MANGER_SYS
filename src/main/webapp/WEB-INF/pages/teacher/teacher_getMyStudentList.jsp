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

					</div>

					<table class="layui-hide" id="studentTable" lay-filter="studentTable"></table>
					<script type="text/html" id="bar">
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
				<label class="layui-form-label layui-required">院系：</label>
				<div class="layui-input-block">
					<select name="collegeId" id="collegeId">
					</select>
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label layui-required">班级：</label>
				<div class="layui-input-block">
					<select name="className" id="className">
					</select>
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label layui-required">身份证：</label>
				<div class="layui-input-block">
					<input type="text" id="formIdcard" name="idCard" class="layui-input" lay-verify = "required|identity">
				</div>
			</div>

			<div class="layui-form-item" style="display: none;">
				<div class="layui-input-block">
					<button id="addSubmitBtn" type="button" class="layui-btn layui-btn-normal" lay-submit lay-filter="addSubmit" disabled = "disabled">提交</button>
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
		var teacherNo = ${user.username}
		console.log(teacherNo)
		var tableObj = table.render({
			elem: '#studentTable'
			,url: '/teacher/getMyStudent?teacherNo='+teacherNo
			,toolbar: true
			, parseData: function (res) { //解析服务器端返回的数据
				console.log(res)
			}
			,title: '我的学生表'
			,page: true
			,response: { //自定义数据状态的字段名称
				statusName: 'code',
				statusCode: 200
			}
			,cols: [[
				{type: 'checkbox', fixed: 'left'}
				,{field:'id', title:'ID', fixed: 'left', sort: true,width:80}
				,{field:'username', title:'用户名', width:150}
				,{field:'realName', title:'真实姓名',width:120}
				,{field:'idCard', title:'身份证',width:120}
				,{field:'className', title:'班级名称', width:120}
				,{field:'collegeName', title:'院系名称', width:120}
				,{field:'birth', title:'出生年月', width:150, sort: true}
				,{field:'mobile', title:'电话', width:120}
				,{field:'sex', title:'性别', unresize: true,templet : function(d) {
						if (d.sex == 0)
							return "<div class='woman'>女</div>";
						else
							return "<div class='man'>男</div>";
					}}
				,{field:'createDate', title:'创建日期', width:180, sort: true}
				,{fixed: 'right', title:'操作', toolbar: '#bar', width:150}
			]]
			,id: 'testReload'

		});


		//监听工具栏事件
		table.on('tool(studentTable)', function(obj) {
			var data = obj.data;
			var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
			if(layEvent === 'del'){ //删除
					layer.msg("抱歉，你没有该权限，请联系管理员")
			}
		});


		//layui重载搜索
		// 执行搜索，表格重载
		$('.search').on('click', function () {
			var table = layui.table;
			// 搜索条件
			table.reload('testReload',{
				method: 'get'
				, url: '/teacher/getStudentByCondition?teacherNo='+teacherNo
				, where: {
					'username':$('#userNameReload').val().trim(),
					'idCard':$('#idCard').val().trim()
				}
				, page: {
					curr: 1
				}
			});
		});


	});
</script>
</body>
</html>