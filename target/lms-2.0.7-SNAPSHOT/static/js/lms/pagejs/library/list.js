$(function() {
	// 初始化控件
	initCurrentComponents();
	// 监听所有event事件
	addCurrentEventListener();
});
/*
 * Init components. ==========================================
 */
function initCurrentComponents() {
	paging();
}
/*
 * Add event listener. ==========================================
 */
function addCurrentEventListener() {
	$("#showAdd").on("click",function(){showModal("add",$(this));});
	$("#sure").click(sure);
	$("button[name='update']").on("click",function(){showModal("update",$(this));});
	$("button[name='delete']").click(deleteLibrary);
	$("td[name='detail']").click(bookTypeDetail);
}
/*
 * 分页
 */
function paging(){
	//分页
	$('#example').dataTable().fnDestroy();	
	var t = $('#example').dataTable({
				paging: false,
				"info": true,
		         "language": {
		             "lengthMenu": "每页 _MENU_ 条记录",
		             "zeroRecords": "没有找到记录",
		             "info": "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
		             "infoEmpty": "无记录",
		             "infoFiltered": "(从 _MAX_ 条记录过滤)",
		             "search":"",
		             "searchPlaceholder":"搜索",
		             "paginate":{
		            	 "next":"下一页",
		            	 "previous":"上一页"
		             }}
			});
}

/**
 * 添加
 */
function showModal(type,obj){
	if(type=="add"){
		$("#title_h4").html("添加主题");
		$("#libraryId").val("");
	}else{
		$("#title_h4").html("修改主题");
		$("#libraryId").val( $(obj).attr("libraryId"));
		$("#libraryName").val($(obj).attr("libraryName"));
	}
	$("#showModal").modal("show");
}

function sure(){
	var id = $("#libraryId").val();
	var title =$.trim($("#libraryName").val());
	var kind = $("#kind").val();
	if(""==title){
		$("#libraryName").next().html("主题不能为空！");
		return false;
	}else{
		$("#libraryName").next().html("");
	}
		
	$.ajax({
		url:"/lms/library/addOrUpdate?id="+id+"&title="+title+"&kind="+kind,
		contentType:"application/json",
		type:"post",
		async:false,
		success:function(src){
			$("#showModal").modal("hide");
			if(src.statusCode==200){
				window.location.href = window.location.href ;
			}else{
				showMessage("操作异常");
			}
		},
		error:function(){
			$("#showModal").modal("hide");
			showMessage("操作异常");
		}
		
	});
}
function deleteLibrary(){
	var libraryId = $(this).attr("libraryId");
	$.Zebra_Dialog('你确定要删除吗？', {
		'type' : 'question',
		'title' : '请确认',
		'buttons' : [ '确定', '取消' ],
		'onClose' : function(caption) {
			var option = (caption != '' ? '"' + caption + '"'
					: 'nothing');
			if ("\"确定\"" == option) {
				$.ajax({
					url:"/lms/library/delete/"+libraryId,
					contentType:"application/json",
					type:"get",
					async:false,
					success:function(src){
						if(src.statusCode==200){
							window.location.href = window.location.href ;
						}else{
							showMessage("删除异常");
						}
					},
					error:function(){
						showMessage("删除异常");
					}
					
				});
				}
			}
	});
	
}
function bookTypeDetail(){
	var libraryId = $(this).parent().attr("libraryId");
	window.location.href= "/lms/library/detail/"+libraryId;
}
function showMessage(message){
	$.Zebra_Dialog(message, {
		'type':     'information',
		'title':    '提示',
		'buttons':  ['确定'],
	});
}