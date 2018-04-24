<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link href="/static/umeditor1.2.3-utf8-jsp/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="/static/umeditor1.2.3-utf8-jsp/third-party/template.min.js"></script>
<script type="text/javascript" charset="utf-8" src="/static/umeditor1.2.3-utf8-jsp/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="/static/umeditor1.2.3-utf8-jsp/umeditor.min.js"></script>
<script type="text/javascript" src="/static/umeditor1.2.3-utf8-jsp/lang/zh-cn/zh-cn.js"></script>

<style>
	.content {
  min-height: 800px;
}
</style>
<div class="content-wrapper">

	<section class="content-header">
		<h1>
			<small>
	          <ol class="breadcrumb ">
	            <li><a href="/lms/book/list"><i class="fa fa-dashboard"></i> 首页</a></li>
	            <li class="active">
			            	<#if type==1 >
					           	添加 新闻
					        <#elseif type ==2 > 
					        	添加动态  	 
					        <#elseif type ==3 >
					       		添加 公告
					       	</#if> 
	            </li>
	          </ol>
			</small>
		</h1>

    </section>
	<section class="content" >
		<form class="form-horizontal" role="form">
		  <div class="form-group">
		    <label " class="col-sm-1 control-label">			            	
		    				<#if type==1 >
					           	 新闻标题
					        <#elseif type ==2 > 
					        	动态标题  	 
					        <#elseif type ==3 >
					       		 公告标题
					       	</#if> </label>
		    <div class="col-sm-3">
		      <input type="input" class="form-control"id="title"  placeholder="新闻标题">
		    </div>
		  </div>
		  <div class="form-group">
		    <label  class="col-sm-1 control-label">学校</label>
		    <div class="col-sm-3">
		      <select class="form-control "  id = "schoolId">
              		<#if  schools?? >
              		<#list schools as school>
              			<option value="${school.id}">${(school.name)}</option>
              		</#list>
              		</#if>
              </select>
		    </div>
		  </div>
		  <div class="form-group">
		    <label  class="col-sm-1 control-label">内容</label>
		    <div class="col-sm-6">
				<div  id="myEditor" ></div>
		    </div>
		  </div>
			<div class="form-group">
				<label class="col-sm-1 control-label"></label>
				<div class="col-sm-3">
					<div >
							<button type='button' class='btn btn-primary' id='buttion_sure'>确定</button>
				   	</div>
				</div>
			</div>
		</form>
		<input type="hidden" value="${type}" id="type">
	</section>
</div>
<script type="text/javascript" src="${ (project.staticDomain)! }/js/lms/pagejs/event/add.js">
</script>
