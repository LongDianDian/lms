<style>
	.content {
  min-height: 900px;
}
</style>
<div class="content-wrapper">

	<section class="content-header">
		<h1>
			<small>
	          <ol class="breadcrumb ">
	            <li><a href="/lms/book/list"><i class="fa fa-dashboard"></i> 首页</a></li>
	            <li class="active">学校信息修改</li>
	          </ol>
			</small>
		</h1>

    </section>
	<section class="content" >
		<form class="form-horizontal" role="form">
		  <div class="form-group">
		    <label " class="col-sm-1 control-label">学校名称</label>
		    <div class="col-sm-3">
		      <input type="input" class="form-control"id="schoolName"  value="${(school.name)}" placeholder="学校名称">
		    </div>
		  </div>
		  <div class="form-group">
		    <label  class="col-sm-1 control-label">校训</label>
		    <div class="col-sm-3">
		      <textarea rows="3" class="form-control" id="schoolMotto"  style="border-radius:0px;" placeholder="校训">${(school.schoolMotto)}</textarea>
		    </div>
		  </div>
  		  <div class="form-group">
		    <label  class="col-sm-1 control-label">头部海报</label>
		    <div class="col-sm-3">
				<div  style="position:relative;float: left;" >
			    	<img id="headfileArea" style="position: absolute;z-index: 10;" src="${ (project.staticDomain)! }/images/lms/postaddColumn.png"  width="96px" height="96px">
			    	<input id="headPosterFile" enctype="multipart/form-data" type="file" name="file" src=""  style="position:relative;width: 96px;z-index: 12200;height: 96px;opacity: 0;">
			    </div>
			    <div  id="div_headPoster" style="position:relative;float: left;" >
					<img id="headPosterFile_img" src="${(school.headPostUrl)}"  width="96px" height="96px">
					<img id="deleteHeadPoster" src="${ (project.staticDomain)! }/images/lms/delete.png" style="visibility:hidden;position: absolute;top: 0;right: 0;height:30px;width:30px"/>
					<input type="hidden" id="headPosterFile_base64">
			    </div>
		    </div>
		  </div>
		  <div class="form-group">
		    <label  class="col-sm-1 control-label">底部海报</label>
		    <div class="col-sm-3">
  				<div  style="position:relative;float: left;" >
			    	<img id="footfileArea" style="position: absolute;z-index: 10;" src="${ (project.staticDomain)! }/images/lms/postaddColumn.png"  width="96px" height="96px">
			    	<input id="footPosterFile" enctype="multipart/form-data" type="file" name="file" src=""  style="position:relative;width: 96px;z-index: 12200;height: 96px;opacity: 0;">
			    </div>
			    <div  id="div_footPoster" style="position:relative;float: left;" >
					<img id="footPosterFile_img" src="${(school.footPostUrl)}"  width="96px" height="96px">
					<img id="deleteFootPoster" src="${ (project.staticDomain)! }/images/lms/delete.png" style="visibility:hidden;position: absolute;top: 0;right: 0;height:30px;width:30px"/>
					<input type="hidden" id="footPosterFile_base64">
			    </div>
		    </div>
		  </div>
			<div class="form-group">
				<label class="col-sm-1 control-label"></label>
				<div class="col-sm-3">
					<div >
							<input type="hidden" id="schoolId" value="${(school.id)}">
							<button type='button' class='btn btn-primary' id='buttion_sure'>确定</button>
				   	</div>
				</div>
			</div>
		</form>
	</section>

 	
</div>
<script type="text/javascript" src="${ (project.staticDomain)! }/js/lms/pagejs/school/update.js">
</script>