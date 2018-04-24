<link href="${ (project.staticDomain)! }/libs/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
<!-- Font Awesome Icons -->
<link href="${ (project.staticDomain)! }/css/common/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<!-- Ionicons -->
<link href="${ (project.staticDomain)! }/css/common/ionicons/css/ionicons.min.css" rel="stylesheet" type="text/css" />
<!-- Theme style -->
<link href="${ (project.staticDomain)! }/libs/AdminLTE-2.1.1/dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
<!-- base css -->
<link href="${ (project.staticDomain)! }/css/lms/base.css" rel="stylesheet" type="text/css" />
<!-- common -->
<link href="${ (project.staticDomain)! }/css/lms/common.css" rel="stylesheet" type="text/css" />

<!-- AdminLTE Skins. Choose a skin from the css/skins folder instead of downloading all of them to reduce the load. -->
<link href="${ (project.staticDomain)! }/libs/AdminLTE-2.1.1/dist/css/skins/_all-skins.min.css" rel="stylesheet" type="text/css" />

<link rel="stylesheet" type="text/css" href="${ (project.staticDomain)! }/libs/Zebra_Dialog/css/flat/zebra_dialog.css"/>
<link href="${(project.staticDomain!)}/css/lms/pagecss/pagination/dataTables.bootstrap.css"	rel="stylesheet" type="text/css" />
 <!-- jQuery 2.1.4 -->
<script src="${ (project.staticDomain)! }/libs/jquery/jquery-1.11.0.min.js"></script>
<!-- Bootstrap 3.3.2 JS -->
<script src="${ (project.staticDomain)! }/libs/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<!-- AdminLTE App -->
<script src="${ (project.staticDomain)! }/libs/AdminLTE-2.1.1/dist/js/app.min.js" type="text/javascript"></script>

<script src="${ (project.staticDomain)! }/libs/Zebra_Dialog/js/zebra_dialog.js"></script>

<script src="${ (project.staticDomain)! }/js/lms/pagejs/pagination/jquery.dataTables.min.js"></script>
<script src="${ (project.staticDomain)! }/js/lms/pagejs/pagination/dataTables.bootstrap.js"></script>



<!-- jquery validate -->
<script src="${ (project.staticDomain)! }/libs/jquery-validate/jquery.validate.min.js"></script>
<script src="${ (project.staticDomain)! }/libs/jquery-validate/additional-methods.min.js"></script>
<script src="${ (project.staticDomain)! }/js/lms/pagejs/include/navigator.js"></script>
<script src="${(project.staticDomain!)}/libs/My97DatePicker/WdatePicker.js"></script>
<script src="${ (project.staticDomain)! }/js/lms/common.js"></script>

<style>
.form_check>span{ 
	color: red;
}
.showMenuBar{
	display: none;
}
</style>
<title>文档标题</title>
<!-- header 头  -->
<header class="main-header">
    <!-- Logo -->
    <a href="/cas" class="logo">
      <span class="logo-mini">LMS</span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg">图书管理系统</span>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top" role="navigation">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </a>
      <div class="navbar-custom-menu">
      	<ul class="nav navbar-nav">
      	<li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">
          <span class="username">${(userInfo.nickname)!"管理员"}</span>
           <span class="caret"></span>
           </a>
          <ul class="dropdown-menu" role="menu" >
       		<li><a  id="user-edit-password" data-toggle="modal" data-target="#editPasswordDialog" href="#"><span class="glyphicon glyphicon-edit"></span> 修改密码</a></li>
             <li><a  id="user-login-out" href="#"><span class="glyphicon glyphicon-log-out"></span> 退出</a></li>
          </ul>
        </li>
      	</ul>
      </div>
    </nav>
 </header>
 <!-- 右侧菜单 -->
 <aside class="main-sidebar">
    <section class="sidebar">
      <ul class="sidebar-menu">
        <li class="<#if  requestPath[2] == 'book'> active</#if>">
          <a  href="/lms/book/list">
            <i class="fa fa-sitemap"></i><span>图书管理</span>
          </a>
        </li>
        <li class="<#if  requestPath[2] == 'bookType'> active</#if>">
          <a  href="/lms/bookType/list">
            <i class="fa fa-group"></i><span>图书分类管理</span>
          </a>
        </li>
        <li class="<#if  requestPath[2] == 'school'> active</#if>">
          <a  href="/lms/school/list">
            <i class="fa fa-sliders"></i><span>学校信息管理</span>
          </a>
        </li>
        <li class="<#if  requestPath[2] == 'machine'> active</#if>">
          <a  href="/lms/machine/list">
            <i class="fa fa-sliders"></i><span>借阅机信息管理</span>
          </a>
        </li>
        <li class="<#if  requestPath[4] == '1'> active</#if>">
          <a  href="/lms/event/list/1">
            <i class="fa fa-sliders"></i><span>校园新闻管理机</span>
          </a>
        </li>
        <li class="<#if  requestPath[4] == '2'> active</#if>">
          <a  href="/lms/event/list/2">
            <i class="fa fa-sliders"></i><span>校园动态管理</span>
          </a>
        </li>
        <li class="<#if  requestPath[4] == '3'> active</#if>">
          <a  href="/lms/event/list/3">
            <i class="fa fa-sliders"></i><span>校园通知管理</span>
          </a>
        </li>
        <li class="<#if  requestPath[4] == '1'> active</#if>">
          <a  href="/lms/library/list/1">
            <i class="fa fa-sliders"></i><span>校园风采</span>
          </a>
        </li>
        <li class="<#if  requestPath[4] == '2'> active</#if>">
          <a  href="/lms/library/list/2">
            <i class="fa fa-sliders"></i><span>教学文案</span>
          </a>
        </li>
      </ul>
    </section>
    <!-- /.sidebar -->
 </aside>
<div class='modal fade' id='editPasswordDialog' tabindex='-1' role='dialog' aria-labelledby='myModalLabel' hidden='true'>
 	
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">修改密码</h4>
      </div>
      <div class="modal-body">
     	<form class="form-horizontal"  id="editPassword-form" name="editPassword-form">
		  <div class="form-group">
		    <label for="editPasswordUsername" class="col-sm-2 control-label">用户名</label>
		    <div class="col-sm-6">
		      <input type="text" class="form-control" value="${(userInfo.username)!}" disabled="disabled" id="editPasswordUsername" name="editPasswordUsername">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="editPasswordOldPassword" class="col-sm-2 control-label">旧密码</label>
		    <div class="col-sm-6 form_check">
		      <input class="form-control" type="password" id="editPasswordOldPassword" name="editPasswordOldPassword">
		      <span></span>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="editPasswordNewPassword" class="col-sm-2 control-label">新密码</label>
		    <div class="col-sm-6 form_check">
		      <input  class="form-control" type="password" id="editPasswordNewPassword" name="editPasswordNewPassword">
		      <span></span>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="editPasswordConfirmPassword" class="col-sm-2 control-label">确认密码</label>
		    <div class="col-sm-6 form_check">
		      <input  class="form-control" type="password" id="editPasswordConfirmPassword" name="editPasswordConfirmPassword">
		       <span></span>
		    </div>
		  </div>
		  </form>
      </div>
	<div class='modal-footer'>
		<button type='button' class='btn btn-default btn-sm' data-dismiss='modal'>取消</button>
		<button type='button' class='btn btn-primary btn-sm' id='user-edit-password-confirm'>确认</button>
	</div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
 	
</div>
