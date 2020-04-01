<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>欢迎你，教师</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<link rel="stylesheet" href="../../layuiadmin/layui/css/layui.css" media="all">
	<link rel="stylesheet" href="../../layuiadmin/style/admin.css" media="all">
	<link rel="stylesheet" href="https://www.layui.com/admin/std/dist/layuiadmin/layui/css/modules/layer/default/layer.css?v=3.1.1" media="all">

	<script>
		// /^http(s*):\/\//.test(location.href) || alert('请先部署到 localhost 下再访问');
	</script>
</head>
<body class="layui-layout-body">

<div id="LAY_app">
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header">
			<!-- 头部区域 -->
			<ul class="layui-nav layui-layout-left">
				<li class="layui-nav-item layadmin-flexible" lay-unselect>
					<a href="javascript:;" layadmin-event="flexible" title="侧边伸缩">
						<i class="layui-icon layui-icon-shrink-right" id="LAY_app_flexible"></i>
					</a>
				</li>
				<li class="layui-nav-item layui-hide-xs" lay-unselect>
					<a href="http://kjxy.nchu.edu.cn/" target="_blank" title="校园官网">
						<i class="layui-icon layui-icon-website"></i>
					</a>
				</li>
				<li class="layui-nav-item" lay-unselect>
					<a href="javascript:;" layadmin-event="refresh" title="刷新">
						<i class="layui-icon layui-icon-refresh-3"></i>
					</a>
				</li>
				<li class="layui-nav-item layui-hide-xs" lay-unselect>
					<input type="text" placeholder="搜索..." autocomplete="off" class="layui-input layui-input-search" layadmin-event="serach" lay-action="template/search.html?keywords=">
				</li>
			</ul>
			<ul class="layui-nav layui-layout-right" lay-filter="layadmin-layout-right">

				<li class="layui-nav-item" lay-unselect>
					<a lay-href="View/noticeView" layadmin-event="message" lay-text="消息中心">
						<i class="layui-icon layui-icon-notice"></i>

						<!-- 如果有新消息，则显示小圆点 -->
						<span class="layui-badge-dot"></span>
					</a>
				</li>

				<li class="layui-nav-item layui-hide-xs" lay-unselect>
					<a href="javascript:;" layadmin-event="note">
						<i class="layui-icon layui-icon-note"></i>
					</a>
				</li>
				<li class="layui-nav-item layui-hide-xs" lay-unselect>
					<a href="javascript:;" layadmin-event="fullscreen">
						<i class="layui-icon layui-icon-screen-full"></i>
					</a>
				</li>
				<li class="layui-nav-item" lay-unselect>
					<a href="javascript:;">
						<cite>${user.realName}</cite>
					</a>
					<dl class="layui-nav-child">
						<dd><a lay-href="View/personView">基本资料</a></dd>
						<dd><a lay-href="View/modifyPwsView">修改密码</a></dd>
						<hr>
						<dd  style="text-align: center;"><a href='javascript:void(0)' id = 'exit'>退出</a></dd>
					</dl>
				</li>

				<li class="layui-nav-item layui-hide-xs" lay-unselect>
					<a href="javascript:;" layadmin-event="about"><i class="layui-icon layui-icon-more-vertical"></i></a>
				</li>
				<li class="layui-nav-item layui-show-xs-inline-block layui-hide-sm" lay-unselect>
					<a href="javascript:;" layadmin-event="more"><i class="layui-icon layui-icon-more-vertical"></i></a>
				</li>
			</ul>
		</div>

		<!-- 侧边菜单 -->
		<div class="layui-side layui-side-menu">
			<div class="layui-side-scroll">
				<div class="layui-logo" lay-href="home/console.html">
					<span>欢迎你，教师</span>
				</div>

				<ul class="layui-nav layui-nav-tree" lay-shrink="all" id="LAY-system-side-menu" lay-filter="layadmin-system-side-menu">
					<li data-name="home" class="layui-nav-item layui-nav-itemed">
						<a href="javascript:;" lay-tips="主页" lay-direction="2">
							<i class="layui-icon layui-icon-home"></i>
							<cite>主页</cite>
						</a>
						<dl class="layui-nav-child">
							<dd data-name="console" class="layui-this">
								<a lay-href="View/mainView">校园概览</a>
							</dd>
							<dd data-name="console">
								<a lay-href="View/personView">个人中心</a>
							</dd>
						</dl>
					</li>
					<li data-name="component" class="layui-nav-item">
						<a href="javascript:;" lay-tips="组件" lay-direction="2">
							<i class="layui-icon layui-icon-user"></i>
							<cite>用户管理</cite>
						</a>
						<dl class="layui-nav-child">
							<dd data-name="button">
								<a lay-href="View/getStudentListView">学生管理</a>
							</dd>
						</dl>
					</li>
					<li data-name="template" class="layui-nav-item">
						<a href="javascript:;" lay-tips="课程管理" lay-direction="2">
							<i class="layui-icon layui-icon-template"></i>
							<cite>课程管理</cite>
						</a>
						<dl class="layui-nav-child">
							<dd><a lay-href="View/MyCourseView">我的课程</a></dd>
						</dl>
					</li>


					<li data-name="grade" class="layui-nav-item">
						<a href="javascript:;" lay-tips="成绩管理" lay-direction="2">
							<i class="layui-icon layui-icon-senior"></i>
							<cite>成绩管理</cite>
						</a>
						<dl class="layui-nav-child">
							<dd>
								<a lay-href="View/getMyStudentGrade">学生成绩管理</a>
							</dd>
						</dl>
					</li>

					<li data-name="transcation" class="layui-nav-item">
						<a href="javascript:;" lay-tips="事务管理" lay-direction="2">
							<i class="layui-icon layui-icon-senior"></i>
							<cite>事务管理</cite>
						</a>
						<dl class="layui-nav-child">
							<dd>
								<a lay-href="View/t_commitTran">提交事务</a>
							</dd>
							<dd>
								<a lay-href="View/t_tranList">事务列表</a>
							</dd>
						</dl>
					</li>

				</ul>
			</div>
		</div>

		<!-- 页面标签 -->
		<div class="layadmin-pagetabs" id="LAY_app_tabs">
			<div class="layui-icon layadmin-tabs-control layui-icon-prev" layadmin-event="leftPage"></div>
			<div class="layui-icon layadmin-tabs-control layui-icon-next" layadmin-event="rightPage"></div>
			<div class="layui-icon layadmin-tabs-control layui-icon-down">
				<ul class="layui-nav layadmin-tabs-select" lay-filter="layadmin-pagetabs-nav">
					<li class="layui-nav-item" lay-unselect>
						<a href="javascript:;"></a>
						<dl class="layui-nav-child layui-anim-fadein">
							<dd layadmin-event="closeThisTabs"><a href="javascript:;">关闭当前标签页</a></dd>
							<dd layadmin-event="closeOtherTabs"><a href="javascript:;">关闭其它标签页</a></dd>
							<dd layadmin-event="closeAllTabs"><a href="javascript:;">关闭全部标签页</a></dd>
						</dl>
					</li>
				</ul>
			</div>
			<div class="layui-tab" lay-unauto lay-allowClose="true" lay-filter="layadmin-layout-tabs">
				<ul class="layui-tab-title" id="LAY_app_tabsheader">
					<li lay-id="home/console.html" lay-attr="home/console.html" class="layui-this"><i class="layui-icon layui-icon-home"></i></li>
				</ul>
			</div>
		</div>


		<!-- 主体内容 -->
		<div class="layui-body" id="LAY_app_body">
			<div class="layadmin-tabsbody-item layui-show">
				<iframe src="View/mainView" frameborder="0" class="layadmin-iframe"></iframe>
			</div>
		</div>

		<!-- 辅助元素，一般用于移动设备下遮罩 -->
		<div class="layadmin-body-shade" layadmin-event="shade"></div>
	</div>
</div>

<script src="../../layuiadmin/layui/layui.js"></script>
<script>
	layui.config({
		base: '../../layuiadmin/' //静态资源所在路径
	}).extend({
		index: '../../layuiadmin/lib/index' //主入口模块
	}).use(['index','layer','element','jquery'],function () {
		var $ = layui.jquery;
		$("#exit").click(function () {
			layer.confirm('确定要注销当前用户吗？', {
				btn: ['确定', '取消']
			},function () { //确定
				$.post("/logout",function (res) {
					if (res.code == 200){
						//注销成功
						window.location.href="../../login.jsp";
					}else {
						//注销失败
					}
				})
			},function (index) {  //取消
				layer.close(index); //关闭弹层
			});


		});
	});
</script>

</body>
</html>


