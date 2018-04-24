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
	            <li class="active">图书管理</li>
	          </ol>
			</small>
		</h1>
 <div class="content-box">
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
            <input type="input" class="form_datetime"  id="publicationDate" name="param">
        </div>
    </div>
    <input type="button"  value="搜索"  id="search" >
</div>         

    </section>
	<section class="content" >
		<div class="row">
			<div class="col-xs-12">
				<table id="example" class="table table-striped table-bordered " cellspacing="0" width="100%" >
					<thead>
						<tr class="bg-LTE">
							<th>序号</th>
							<th>书名</th>
							<th>作者</th>
							<th>出版社</th>
							<th>出版日期</th>
						</tr>
					</thead>
					<tbody>
					<!--
					<#if (books)?? >
						<#list books as book>
						<tr>
							<td>${book_index+1}</td>
							<td>${(book.bookname)!}</td>
							<td>${(book.author)!}</td>
							<td>${(book.press)!}</td>
							<td>${(book.publicationDate)!}</td>
						</tr>
						</#list>
					</#if>
					-->
					</tbody>
				</table>
			</div>
		</div>
	</section>
</div>
<script type="text/javascript" src="${ (project.staticDomain)! }/js/lms/pagejs/book/list.js">
</script>