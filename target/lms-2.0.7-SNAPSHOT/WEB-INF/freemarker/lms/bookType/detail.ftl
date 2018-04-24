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
            .inputs{
			    width: 36px;
			    height: 24px;
			    display: none;
			    border: 1px solid #2c7edc;
			    border-radius: 5px;
			    outline: none;
            }
        </style>
<div class="content-wrapper">

	<section class="content-header">
		<h1>
			<small>
	          <ol class="breadcrumb ">
	            <li ><a href="/lms/book/list"><i class="fa fa-dashboard"></i> 首页</a></li>
	            <li ><a href="/lms/bookType/list">分类(${(bookType.name)})详情</a></li>
	          </ol>
			</small>
		</h1>
 <div class="content-box">
 	<input type="hidden" id = "bookTypeId" name="param" value="${(bookType.id)}">
    <div class="input-group1">
        <label  class="control-label" for="bookname">书名</label>
        <div class="input-box">
            <input type="input" id="bookname" name="param">
        </div>
    </div>
    <div class="input-group1">
        <label  class="control-label" for="author">作者</label>
        <div class="input-box">
            <input type="input" id="author" name="param" >
        </div>
    </div>
    <div class="input-group1">
        <label  class="control-label" for="press">出版社</label>
        <div class="input-box">
            <input type="input"  id="press" name="param">
        </div>
    </div>
    <div class="input-group1">
        <label  class="control-label" for="publicationDate">出版日期</label>
        <div class="input-box">
            <input type="input"  class="form_datetime" id="publicationDate" name="param">
        </div>
    </div>
    <input type="button"  value="搜索"  id="search" >
</div>         

    </section>
	<section class="content" >
		<div class="row marginbottom10">
			<div class="col-xs-12">
				<div class="pull-right">
					<button type="button" class="btn btn-primary btn-sm" id="addBook">添加</button>
					<button type="button" class="btn btn-primary btn-sm" id="batch_shelve" name="top" disabled>上架</button>
					<button type="button" class="btn btn-primary btn-sm" id="batch_unshelve" name="top" disabled>下架</button>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12">
				<table id="example" class="table table-striped table-bordered " cellspacing="0" width="100%" >
					<thead>
						<tr class="bg-LTE">
							<th><input type="checkbox" id="one_checkAll"></th>
							<th>序号</th>
							<th>书名</th>
							<th>作者</th>
							<th>出版社</th>
							<th>出版日期</th>
							<th>状态</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
		<!---------model-------->
		<div class='modal fade' id='showModal' tabindex='-1' role='dialog' aria-labelledby='myModalLabel' hidden='true'>
		  <div class="modal-dialog" style="width:80%">
		    <div class="modal-content"  style="height: 50%;">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="title_h4">添加图书</h4>
		      </div>
		      <div class="modal-body">
		       <div class="content-box">
			    <div class="input-group1">
			        <label  class="control-label" for="bookname">书名</label>
			        <div class="input-box">
			            <input type="input" idForModel="bookname" name="paramForModel">
			        </div>
			    </div>
			    <div class="input-group1">
			        <label  class="control-label" for="author">作者</label>
			        <div class="input-box">
			            <input type="input" idForModel="author" name="paramForModel" >
			        </div>
			    </div>
			    <div class="input-group1">
			        <label  class="control-label" for="press">出版社</label>
			        <div class="input-box">
			            <input type="input"  idForModel="press" name="paramForModel">
			        </div>
			    </div>
			    <div class="input-group1">
			        <label  class="control-label"   for="publicationDate">出版日期</label>
			        <div class="input-box">
			            <input type="input"  idForModel="publicationDate" class="form_datetime" name="paramForModel">
			        </div>
			    </div>
			    <input type="button"  value="搜索"  id="searchForModel" >
			</div>  
		      
		      
		      
		      	<div class="col-xs-12">
					<table id="add_div_table" class="table table-striped table-bordered " cellspacing="0" width="100%" >
						<thead>
							<tr class="bg-LTE">
								<th><input type="checkbox" id="checkAll">序号</th>
								<th>书名</th>
								<th>作者</th>
								<th>出版社</th>
								<th>出版日期</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
		      </div>
			<div class='modal-footer'>
				<input type="hidden"  id="bookTypeId" data="params">
				<button type='button' class='btn btn-default btn-sm' data-dismiss='modal'>取消</button>
				<button type='button' class='btn btn-primary btn-sm' id='sureForModal'>确认</button>
			</div>
		    </div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		<!---------model end---------->
		
	</section>
</div>
<script type="text/javascript" src="${ (project.staticDomain)! }/js/lms/pagejs/bookType/detail.js">
</script>