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
	            <li class="active">校园风采</li>
	          </ol>
			</small>
		</h1>

    </section>
	<section class="content" >
		<div class="row marginbottom10">
			<div class="col-xs-12">
				<div class="pull-right">
					<button type="button" class="btn btn-primary btn-sm" id="showAdd">新增主题</button>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-xs-12">
				<table id="example" class="table table-striped table-bordered " cellspacing="0" width="100%" >
					<thead>
						<tr class="bg-LTE">
							<th>主题名称</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
					<#if (list)?? >
						<#list list as library>
						<tr libraryId="${(library.id)!}">
							<td  width="60%" style="cursor:pointer" name="detail"><a>${(library.title)!}</a></td>
							<td width="20%">
								<button  class="btn btn-primary btn-sm" name="update" libraryName="${(library.title)!}" libraryId="${(library.id)!}" >编辑</button>
								<button class="btn btn-danger btn-sm" name="delete" libraryId="${(library.id)!}">删除</button>
							</td>
						</tr>
						</#list>
					</#if>
					</tbody>
				</table>
			</div>
		</div>
		
		<div class='modal fade' id='showModal' tabindex='-1' role='dialog' aria-labelledby='myModalLabel' hidden='true'>
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="title_h4">新增分类</h4>
		      </div>
		      <div class="modal-body">
		     	<form class="form-horizontal"  id="add-form" >
				  <div class="form-group">
				    <label  class="col-sm-2 control-label">主题名称</label>
				    <div class="col-sm-6">
				      <input  type="text" class="form-control" id="libraryName" data="params" name="order">
				      <span style="color:red"></span>
				    </div>
				  </div>
				  </form>
		      </div>
			<div class='modal-footer'>
				<input type="hidden"  id="libraryId" data="params">
				<input type="hidden"  id="kind" value="${(kind)}">
				<button type='button' class='btn btn-default btn-sm' data-dismiss='modal'>取消</button>
				<button type='button' class='btn btn-primary btn-sm' id='sure'>确认</button>
			</div>
		    </div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
	</section>
	
 	
</div>
<script type="text/javascript" src="${ (project.staticDomain)! }/js/lms/pagejs/library/list.js">
</script>