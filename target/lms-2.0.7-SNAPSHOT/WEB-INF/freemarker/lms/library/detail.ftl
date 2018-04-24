  <style>
.content {
  min-height: 900px;
}
#list{position:relative;}
#list li{display:none;}
#addImgC:hover img{
	box-shadow: 0 0 12px 0 #45463d;
}
li{
list-style: none;
}
#list li span{
    position: absolute;
    top: 10px;
    right: 10px;
    display:none;
}
.imgA{
	height: 100%;
    display: block;
}
.imgA:hover .del{
	display:block !important;
}
.imgA:hover img{
	box-shadow: 0 0 12px 0 #45463d;
}
</style>
  <div class="content-wrapper">
  	<section class="content-header">
		<h1>
			<small>
	          <ol class="breadcrumb ">
	            <li><a href="/lms/book/list"><i class="fa fa-dashboard"></i> 首页</a></li>
	            <li class="active">校园风采</li>
	          </ol>
			</small>
		</h1>
    </section>
    <section class="content" >
    	<div>
    	<ul id="list">
		  	<li ><a id="addImgC"><img id="addImg" src="${ (project.staticDomain)! }/images/postadd.png"  width="300" height="300"  ></a><p></p></li>
        <#list medias as media>
		  	<li>
		  	<a class="imgA"><span name="del" mediaId="${(media.id)}" class="glyphicon glyphicon-trash del"></span><img src="${(media.url)}" width="300"   <#if media.height!=0 > height="${(media.height)}" </#if> ></a><p></p></li>
        </#list>
		</ul>
        </div>
        
        
	<div class='modal fade' id='showModal' tabindex='-1' role='dialog' aria-labelledby='myModalLabel' hidden='true'>
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="title_h4">上传图片</h4>
		      </div>
		      <div class="modal-body">
		     	<form class="form-horizontal"  id="add-form" >
				  <div class="form-group">
				    <label  class="col-sm-2 control-label">选择</label>
				    <div class="col-sm-6">
				      <input  type="file" class="form-control" id="file" data="params" name="order">
				      <span style="color:red"></span>
				    </div>
				  </div>
				  </form>
		      </div>
			<div class='modal-footer'>
				<button type='button' class='btn btn-default btn-sm' data-dismiss='modal'>取消</button>
				<button type='button' class='btn btn-primary btn-sm' id='sure'>确认</button>
			</div>
		    </div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->   
	</section>
    
    
    <input type="hidden" id="base64" value="">
    <input type="hidden" id="libraryId" value="${(libraryId)!}">
    <input type="hidden" id="width" value="">
    <input type="hidden" id="height" value="">

  </div>
<script type="text/javascript" src="/static/js/lms/jquery.wookmark.min.js"></script>
<script type="text/javascript" src="${ (project.staticDomain)! }/js/lms/pagejs/library/detail.js">
</script>
