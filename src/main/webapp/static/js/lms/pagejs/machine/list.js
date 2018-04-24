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
	$("button[name='updateStatus']").on("click",function(){updateStatus($(this));});
	$("button[name='update']").on("click",function(){update($(this));});
	$("#sure").click(sure);
}
/*
 * 分页
 */
function paging(){
	//分页
	$('#example').dataTable().fnDestroy();	
	var t = $('#example').dataTable({
				paging: false,
				"info": false,
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
function updateStatus(obj){
	var id = $(obj).attr("typeId");
	$.ajax({
		url:"/lms/machine/updateStatus?id="+id,
		type:"get",
		success:function(src){
			if(src.statusCode==200){
				window.location.href = "/lms/machine/list";
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
/**
 *展示更新信息 
 */
function update(obj){
	var id = $(obj).attr("typeId");
	var mac = $(obj).attr("mac");
	var schoolId = $(obj).attr("schoolId");
	var macNo = $(obj).attr("macNo");
	$("#macAddress").val(mac);
	$("#macNo").val(macNo);
	$("#schoolId").val(schoolId);
	$("#macId").val(id);
	$("#showModal").modal("show");
}
function sure(){
	var obj = {
			macNo :$("#macNo").val(),
			schoolId:$("#schoolId").val(),
			macId:$("#macId").val()
	};
	$.ajax({
		url:"/lms/machine/update",
		contentType:"application/json",
		type:"post",
		data:JSON.stringify(obj),
		async:false,
		async:false,
		success:function(src){
			if(src.statusCode==200){
				window.location.href = "/lms/machine/list";
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


function showMessage(message){
	$.Zebra_Dialog(message, {
		'type':     'information',
		'title':    '提示',
		'buttons':  ['确定'],
	});
}