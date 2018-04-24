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
	$("button[name='update']").click(toUpdate);
	$("#addSchool").click(toAdd);
	$("button[name='delete']").click(deleteSchool);
	$("button[name='update']").click(updateSchool);
}
/*
 * 分页
 */
function paging(){
	//分页
	$('#example').dataTable().fnDestroy();	
	var t = $('#example').dataTable({
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
		             }
		         }
			});
}
function toUpdate(){
	window.location.href="/lms/school/toUpdate/"+$(this).attr("schoolId");
}
function toAdd(){
	window.location.href="/lms/school/toAdd";
}
function deleteSchool(){
	var schoolId = $(this).attr("schoolId");
	$.Zebra_Dialog('你确定要删除吗？', {
		'type' : 'question',
		'title' : '请确认',
		'buttons' : [ '确定', '取消' ],
		'onClose' : function(caption) {
			var option = (caption != '' ? '"' + caption + '"'
					: 'nothing');
			if ("\"确定\"" == option) {
				$.ajax({
					url:"/lms/school/delete/"+schoolId,
					contentType:"application/json",
					type:"get",
					async:false,
					success:function(src){
						if(src.statusCode==200){
							window.location.href = "/lms/school/list" ;
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
function updateSchool(){
	window.location.href="/lms/school/toUpdate/"+$(this).attr("schoolId");
}
function showMessage(message){
	$.Zebra_Dialog(message, {
		'type':     'information',
		'title':    '提示',
		'buttons':  ['确定'],
	});
}