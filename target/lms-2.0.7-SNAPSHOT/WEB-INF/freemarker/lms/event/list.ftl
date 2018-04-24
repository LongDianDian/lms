<style>
	.content {
  min-height: 900px;
}


</style>
        <style type="text/css">
            .input-box{
                display:inline-block;
                margin-left: 2%;
            }
            .input-group1{
                display:inline-block;
                width:20%;
            }
            .content-box{
                width: 95%;
                margin-left: 5%;
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
			           	 新闻管理
			        <#elseif type ==2 > 
			        	校园动态管理  	 
			        <#elseif type ==3 >
			       		 通知公告管理
			       	</#if>   	 
	            </li>
	          </ol>
			</small>
		</h1>

    </section>
	<section class="content" >
		<div class="row marginbottom10">
			<div class="col-xs-12">
				<div class="pull-right">
					<button type="button" class="btn btn-primary btn-sm" id="toAdd">新增</button>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12">
				<table id="example" class="table table-striped table-bordered " cellspacing="0" width="100%" >
					<thead>
						<tr class="bg-LTE">
							<th>
			            	<#if type==1 >
					           	 新闻标题
					        <#elseif type ==2 > 
					        	动态标题  	 
					        <#elseif type ==3 >
					       		 公告标题
					       	</#if> 
							</th>
							<th>时间</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
	</section>
	<input type="hidden" id="type" value="${(type)!}" />
</div>
<script type="text/javascript" src="${ (project.staticDomain)! }/js/lms/pagejs/event/list.js">
</script>