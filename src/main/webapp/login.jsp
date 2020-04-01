<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
	<head>
		<title>登录页面 - 高校教务管理系统</title>
		<link href="./css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
		<link href="./css/font-awesome.min.css" type="text/css" rel="stylesheet"/>
		<link href="./css/ace-fonts.css" type="text/css" rel="stylesheet" />
		<link href="./css/ace.min.css" id="main-ace-style" type="text/css" rel="stylesheet"/>
        <link href="layui/css/layui.css">
		<!--[if lte IE 9]>
			<link  href="./css/ace-part2.min.css" type="text/css" rel="stylesheet" />
		<![endif]-->
		<!--[if lte IE 9]>
		  <link  href="./css/ace-ie.min.css" type="text/css" rel="stylesheet" />
		<![endif]-->
		<!--<script src="/js/ace-extra.min.js"></script>-->
		<!--[if lte IE 8]>
		<script src="./js/html5shiv.min.js"></script>
		<script src="./js/respond.min.js" type="text/javascript"></script>
		<![endif]-->

        <style>
            .blur-login{
                background-image: url("css/images/back.jpg");
                background-repeat: no-repeat;
                background-size: cover;
            }
            .code{
                width: 400px;
                height: 400px;
                background-repeat: no-repeat;
                background-image: url("css/images/code.png");
                margin-left: 80px;
            }
            .tip{
                margin-left: 30px;
            }
            .tip span h2{
                color: red;
            }
        </style>

	</head>
	<body class="login-layout blur-login">
		<div class="main-container">
			<div class="main-content">
				<div class="row">
					<div class="col-sm-10 col-sm-offset-1">
						<div class="login-container">
							<div class="center">
								<h1>
									<span class="white">高校教务管理系统</span>
								</h1>
							</div>

							<div class="space-6"></div>

							<div class="position-relative">
								<div id="login-box" class="login-box visible widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header blue lighter bigger" style="display:${message!=null?'none':'block' } " >
												<i class="icon-coffee green"></i>  请输入您的账号和密码
											</h4>
											<h4 class="header red lighter bigger" style="display:${message!=null?'block':'none' } ">
												<i class="icon-coffee red"></i>  ${message }
											</h4>
											<div class="space-6"></div>

											<form action="/login" method="post">
												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" class="form-control" id = "userName" placeholder="用户名" name="username" />
															<i class="ace-icon fa fa-user"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" class="form-control" placeholder="密码" name="password" />
															<i class="ace-icon fa fa-lock"></i>
														</span>
													</label>

													<div class="space"></div>

													<div class="clearfix">
														<label class="inline">
															<input type="checkbox" class="ace" />
															<span class="lbl"> 记住我</span>
														</label>

														<button type="submit" class="width-35 pull-right btn btn-sm btn-primary">
															<i class="ace-icon fa fa-key"></i>
															<span class="bigger-110">登录</span>
														</button>
													</div>

													<div class="space-4"></div>
												</fieldset>
											</form>

										</div><!-- /.widget-main -->

										<div class="toolbar clearfix">
											<div col-sm-10 col-sm-offset-8>
												<a href="#" data-target="#forgot-box" class="forgot-password-link">
													<i class="ace-icon fa fa-arrow-left"></i>
													忘记密码
												</a>
											</div>

											<%--<div>
												<a href="#" data-target="#signup-box" class="user-signup-link">
													用户注册
													<i class="ace-icon fa fa-arrow-right"></i>
												</a>
											</div>--%>
										</div>
									</div><!-- /.widget-body -->
								</div><!-- /.login-box -->




                                <div id="forgot-box" class="forgot-box widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header red lighter bigger">
												<i class="ace-icon fa fa-key"></i>
												重置密码
											</h4>

											<div class="space-6"></div>
											<p>
												输入您已绑定的手机号码，用以接收短信验证码
											</p>

											<form  class="layui-form" action="modifyPwdInLogin" method="post">
												<fieldset>
													<label class="block clearfix">手机号码
														<span class="block input-icon input-icon-right">
															<input class="form-control" placeholder="手机号码" id="mobile" name="mobile" lay-verify = "pho" />
															<i class="ace-icon fa fa-phone"></i>
														</span>
                                                       <button type="button" class="width-35 pull-right btn btn-sm btn-danger" id="send">
                                                      发送验证码
                                                        </button>
													</label>
													<label class="block clearfix">登录账号
														<span class="block input-icon input-icon-right">
															<input  class="form-control" placeholder="登录账号" id="loginName" name="username" lay-verify = "required" />
															<i class="ace-icon fa fa-language"></i>
														</span>
													</label>

                                                    <label class="block clearfix">新密码
                                                        <span class="block input-icon input-icon-right">
															<input type="password"  class="form-control" placeholder="新密码" id="password" name="password" lay-verify = "required" />
															<i class="ace-icon fa fa-language"></i>
														</span>
                                                    </label>

													<label class="block clearfix">身份证
														<span class="block input-icon input-icon-right">
															<input  class="form-control" placeholder="身份证" id="idCard"  name="idCard" lay-verify = "required|identity" />
															<i class="ace-icon fa fa-credit-card"></i>
														</span>
													</label>
													<label class="block clearfix">验证码
														<span class="block input-icon input-icon-right">
															<input class="form-control" placeholder="请填入你所收到的手机验证码" id="veriCode" name="code" lay-verify = "required"/>
															<i class="ace-icon fa fa-code"></i>
														</span>
													</label>

													<div class="clearfix">

														<button type="button" class="width-35 pull-right btn btn-sm btn-danger" lay-submit lay-filter="addSubmit" id="sub">
															<i class="ace-icon fa fa-lightbulb-o"></i>
															<span class="bigger-110" id="sendValue">提交</span>
														</button>
													</div>
												</fieldset>
											</form>
										</div><!-- /.widget-main -->

										<div class="toolbar center">
											<a href="#" data-target="#login-box" class="back-to-login-link">
												返回登录
												<i class="ace-icon fa fa-arrow-right"></i>
											</a>
										</div>
									</div><!-- /.widget-body -->
								</div><!-- /.forgot-box -->

								<div id="signup-box" class="signup-box widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header green lighter bigger">
												<i class="ace-icon fa fa-users blue"></i>
												新用户注册
											</h4>

											<div class="space-6"></div>
											<p> 输入详细信息: </p>

											<form>
												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="email" class="form-control" placeholder="Email" />
															<i class="ace-icon fa fa-envelope"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" class="form-control" placeholder="用户名" />
															<i class="ace-icon fa fa-user"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" class="form-control" placeholder="密码" />
															<i class="ace-icon fa fa-lock"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" class="form-control" placeholder="确认密码" />
															<i class="ace-icon fa fa-retweet"></i>
														</span>
													</label>

													<label class="block">
														<input type="checkbox" class="ace" />
														<span class="lbl">
															我接受
															<a href="#">用户协议</a>
														</span>
													</label>

													<div class="space-24"></div>

													<div class="clearfix">
														<button type="reset" class="width-30 pull-left btn btn-sm">
															<i class="ace-icon fa fa-refresh"></i>
															<span class="bigger-110">重置</span>
														</button>

														<button type="button" class="width-65 pull-right btn btn-sm btn-success">
															<span class="bigger-110">注册</span>

															<i class="ace-icon fa fa-arrow-right icon-on-right"></i>
														</button>
													</div>
												</fieldset>
											</form>
										</div>

										<div class="toolbar center">
											<a href="#" data-target="#login-box" class="back-to-login-link">
												<i class="ace-icon fa fa-arrow-left"></i>
												返回登录
											</a>
										</div>
									</div><!-- /.widget-body -->
								</div><!-- /.signup-box -->
							</div><!-- /.position-relative -->
                            <%--  此处用于存放连接二维码--%>
                            <div class="tip">
                                <span><h2>扫描以下二维码，手机访问</h2></span>
                            </div>
                            <div class="code">

                            </div>
						</div>
					</div><!-- /.col -->
				</div><!-- /.row -->
			</div><!-- /.main-content -->

		</div><!-- /.main-container -->

		<!-- basic scripts -->

		<!--[if !IE]> -->
		<script type="text/javascript">
			window.jQuery || document.write("<script src='./js/jquery.min.js'>"+"<"+"/script>");
		</script>

		<!-- <![endif]-->

		<!--[if IE]>
		<script type="text/javascript">
		 window.jQuery || document.write("<script type='text/javascript' src='/js/jquery1x.min.js'>"+"<"+"/script>");
		</script>
		<![endif]-->
		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>

		<!-- inline scripts related to this page -->


        <script type="text/javascript" src="layui/layui.js"></script>
        <script type="text/javascript">
            layui.use(['layer'], function(){
                var layer = layui.layer;

            });
        </script>
		<script type="text/javascript">
			jQuery(function($) {
			 $(document).on('click', '.toolbar a[data-target]', function(e) {
				e.preventDefault();
				var target = $(this).data('target');
				$('.widget-box.visible').removeClass('visible');//hide others
				$(target).addClass('visible');//show target
			 });
			});

			// 给文本框获取焦点
			$("#userName").focus();

            var wait=60; //初始化发送频率
            $("#send").click(function () {
                var mobile = $("#mobile").val()
                if (!new RegExp("^1[345789]\\d{9}$").test(mobile)){
                    layer.msg("请填写正确格式的手机号码")
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
                console.log(obj)
                if (wait == 0) {
                    obj.removeAttribute("disabled");
                    obj.innerText="获取验证码";
                    //obj.value="获取验证码";
                    wait = 60;
                } else {
                    obj.setAttribute("disabled", true);
                    obj.innerText="重新发送(" + wait +")";
                    //obj.value="重新发送(" + wait + ")";
                    wait--;
                    setTimeout(function() {
                        setTime(obj)
                    },1000)}
            }



			$("#sub").click(function () {

			    //获取表单值
                var mobile = $("#mobile").val();
                var username = $("#loginName").val();
                var idCard = $("#idCard").val();
                var code = $("#veriCode").val();
                var password = $("#password").val();

                if (mobile == '' ||username == '' ||idCard == '' ||code == '' ||password == ''){
                    layer.msg("请先补充相关信息")
                    return false;
                }
                var data = {
                    "mobile":mobile,
                     "username":username,
                    "idCard":idCard,
                    "code":code,
                    "password":password
                }
                $.post("/modifyPwdInLogin",data,function (res) {
                    if (res.code == 200 ){
                        layer.msg("重置成功，请返回登录")
                    }else{
                        layer.msg(res.msg)
                    }
                })


            });




		</script>
	</body>
</html>
