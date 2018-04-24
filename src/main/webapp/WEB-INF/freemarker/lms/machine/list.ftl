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
	            <li class="active">借阅机信息列表</li>
	          </ol>
			</small>
		</h1>

    </section>
	<section class="content" >
		<!---
		<div class="row marginbottom10">
			<div class="col-xs-12">
				<div class="pull-right">
					<button type="button" class="btn btn-primary btn-sm" id="addBookType">新增</button>
				</div>
			</div>
		</div>
		--->
		<div class="row">
			<div class="col-xs-12">
				<table id="example" class="table table-striped table-bordered " cellspacing="0" width="100%" >
					<thead>
						<tr class="bg-LTE">
							<th>借阅机信息</th>
							<th>学校名称</th>
							<th>机器编号</th>
							<th>状态</th>
							<th>创建时间</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
					<#if (list)?? >
						<#list list as mac>
						<tr typeId="${(mac.id)!}">
							<td  width="20%" style="cursor:pointer" name="detail">${(mac.mac)!}</td>
							<td width="20%" style="cursor:pointer" name="detail">${(mac.school.name)!}</td>
							<td width="20%" style="cursor:pointer" name="detail">${(mac.macNo)!}</td>
							<#assign macStatus=(mac.disabled)?string("true","false")>
							<td width="10%" style="cursor:pointer" name="detail"><#if macStatus =="true"  >禁用<#else> 启用</#if> </td>
							<td width="10%" style="cursor:pointer" name="detail">${(mac.createTime)!}</td>
							<td width="20%">
								<#if macStatus =="true"  >
								<button  class="btn btn-primary btn-sm" name="updateStatus" typeId="${(mac.id)!}">启用</button>
								<#else> 
								<button  class="btn btn-primary btn-sm" name="updateStatus" typeId="${(mac.id)!}">禁用</button>
								</#if>
								<button class="btn btn-danger btn-sm" name="update" macNo="${(mac.macNo)!}" mac="${(mac.mac)}"  schoolId ="${(mac.school.id)!}"  typeId="${(mac.id)!}">修改</button>
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
		        <h4 class="modal-title" id="title_h4">机器信息修改</h4>
		      </div>
		      <div class="modal-body">
		     	<form class="form-horizontal"  id="add-form" >
				  <div class="form-group">
				    <label  class="col-sm-2 control-label">借阅机信息</label>
				    <div class="col-sm-6">
				      <input  type="text" class="form-control" id="macAddress" data="params"  disabled>
				      <span style="color:red"></span>
				    </div>
				  </div>
				  <div class="form-group">
				    <label  class="col-sm-2 control-label">学校名称</label>
				    <div class="col-sm-6 form_check">
				      <select class="form-control"  id="schoolId">
				      	<#if schoolList?? >
				      	<#list schoolList as school>
				      		<option value="${(school.id)}">${(school.name)}</option>
				      	</#list>
				      	</#if>
				      </select>
				      <span style="color:red"></span>
				    </div>
				  </div>
  				  <div class="form-group">
				    <label  class="col-sm-2 control-label">机器编号</label>
				    <div class="col-sm-6">
				      <input  type="text" class="form-control" id="macNo" data="params" >
				      <span style="color:red"></span>
				    </div>
				  </div>
				  </form>
		      </div>
			<div class='modal-footer'>
				<input type="hidden"  id="macId" data="params">
				<button type='button' class='btn btn-default btn-sm' data-dismiss='modal'>取消</button>
				<button type='button' class='btn btn-primary btn-sm' id='sure'>确认</button>
			</div>
		    </div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
	</section>

 	
</div>
<script type="text/javascript" src="${ (project.staticDomain)! }/js/lms/pagejs/machine/list.js">
</script>