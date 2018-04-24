<html><head>
 <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <title>${systemName!}</title>
  <link href="${ (project.staticDomain)! }/libs/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <!-- Font Awesome Icons -->
  <link href="${ (project.staticDomain)! }/css/common/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
  <!-- Ionicons -->
  <link href="${ (project.staticDomain)! }/css/common/ionicons/css/ionicons.min.css" rel="stylesheet" type="text/css">
  <!-- Theme style -->
  <link href="${ (project.staticDomain)! }/libs/AdminLTE-2.1.1/dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css">
  <link href="${ (project.staticDomain)! }/css/lms/pagecss/user/login.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <div id="container">
    	<img src="${ (project.staticDomain)! }/images/lms/login/login.png" id="login">
        <form id="login-form" action="" method="post" >
	    	<div class="user-form">
	    		<span id="user-img" class="glyphicon glyphicon-user"></span>
		        <input id="username" name="username" type="text" placeholder="请输入账号" autocomplete="off">
	        </div>
	        <div class="err-msg err-msg1">
	        	<span class="err-img"></span>
	        	<span class="error">用户名不能为空</span>
	        </div>
	        <div class="pwd-form">
	        	<span id="pwd-img" class="glyphicon glyphicon-lock"></span>
		        <input id="password" name="password" type="password" placeholder="请输入密码">
	        </div>
	        <div class="err-msg err-msg2">
	        	<span class="err-img"></span>
	        	<span class="error">账号或密码错误</span>
	        </div>
		    <button type="submit" id="register">登录</button>
		    <input id="referer" name="referer" type="hidden" value="">
		</form>
    </div>
<!-- page script -->
<script src="${ (project.staticDomain)! }/libs/jquery/jquery-1.11.0.min.js"></script>
<script src="${ (project.staticDomain)! }/libs/jquery-json/jquery.json-2.4.js"></script>
<script src="${ (project.staticDomain)! }/js/lms/user/login.js"></script></body></html>
