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
	            <li class="active">学校管理</li>
	          </ol>
			</small>
		</h1>

    </section>
	<section class="content" >
		<div class="row marginbottom10">
			<div class="col-xs-12">
				<div class="pull-right">
					<button type="button" class="btn btn-primary btn-sm" id="addSchool">新增</button>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-xs-12">
				<table id="example" class="table table-striped table-bordered " cellspacing="0" width="100%" >
					<thead>
						<tr class="bg-LTE">
							<th>学校名称</th>
							<th>顶部海报</th>
							<th>底部海报</th>
							<th>校训</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
					<#if (list)?? >
						<#list list as school>
						<tr>
							<td>${(school.name)!}</td>
							<td><img width="96px" height="96px" src="${(school.headPostUrl)!}"></td>
							<td><img width="96px" height="96px" src="${(school.footPostUrl)!}"></td>
							<td>${(school.schoolMotto)!}</td>
							<td>
								<button  class="btn btn-primary btn-sm" name="update" schoolId="${(school.id)!}" name="update">修改</button>
								<button class="btn btn-danger btn-sm" name="delete" schoolId="${(school.id)!}" name="delete">删除</button>
							</td>
						</tr>
						</#list>
					</#if>
					</tbody>
				</table>
			</div>
		</div>
		
		
	</section>

 	
</div>
<script type="text/javascript" src="${ (project.staticDomain)! }/js/lms/pagejs/school/list.js">
</script>