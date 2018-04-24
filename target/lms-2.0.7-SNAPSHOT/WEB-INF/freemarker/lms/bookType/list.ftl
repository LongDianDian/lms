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
	            <li class="active">图书类型列表</li>
	          </ol>
			</small>
		</h1>

    </section>
	<section class="content" >
		<div class="row marginbottom10">
			<div class="col-xs-12">
				<div class="pull-right">
					<button type="button" class="btn btn-primary btn-sm" id="addBookType">新增</button>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-xs-12">
				<table id="example" class="table table-striped table-bordered " cellspacing="0" width="100%" >
					<thead>
						<tr class="bg-LTE">
							<th>序号</th>
							<th>类型名称</th>
							<!--
							<th>操作</th>
							-->
						</tr>
					</thead>
					<tbody>
					<#if (list)?? >
						<#list list as type>
						<tr typeId="${(type.id)!}">
							<td  width="20%" style="cursor:pointer" name="detail">${(type.order)!}</td>
							<td width="60%" style="cursor:pointer" name="detail"><a>${(type.name)!}</a></td>
							<!--
							<td width="20%">
								<button  class="btn btn-primary btn-sm" name="update" typeId="${(type.id)!}" paramName="${(type.name)!}" paramOrder="${(type.order)!}" >修改</button>
								<button  class="btn btn-primary btn-sm" name="top" typeId="${(type.id)!}" >置顶</button>
								<button class="btn btn-danger btn-sm" name="delete" typeId="${(type.id)!}">删除</button>
							</td>
							-->
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
				    <label  class="col-sm-2 control-label">序号</label>
				    <div class="col-sm-6">
				      <input  type="text" class="form-control" id="order" data="params" name="order">
				      <span style="color:red"></span>
				    </div>
				  </div>
				  <div class="form-group">
				    <label  class="col-sm-2 control-label">分类名称</label>
				    <div class="col-sm-6 form_check">
				      <input class="form-control" type="text" id="typeName"  data="params" name="typeName">
				      <span style="color:red"></span>
				    </div>
				  </div>
				  </form>
		      </div>
			<div class='modal-footer'>
				<input type="hidden"  id="bookTypeId" data="params">
				<button type='button' class='btn btn-default btn-sm' data-dismiss='modal'>取消</button>
				<button type='button' class='btn btn-primary btn-sm' id='sure'>确认</button>
			</div>
		    </div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
	</section>

 	
</div>
<script type="text/javascript" src="${ (project.staticDomain)! }/js/lms/pagejs/bookType/list.js">
</script>