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
	initEditPasswordFormValidate();
}
/*
 * Add event listener. ==========================================
 */
function addCurrentEventListener() {
	$("#addBookType").on("click",function(){addBookType("add");});
	$("button[name='update']").on("click",function(){addBookType("update",$(this));});
	$("#sure").click(sure);
	$("button[name='top']").click(toTop);
	$("button[name='delete']").click(deleteType);
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

/**
 * 添加
 */
function addBookType(type,obj){
	$("input[data='params']").val("");
	if(type=="add"){
		$("#title_h4").val("添加图书分类");
		$("#bookTypeId").val("");
	}else{
		$("#title_h4").val("修改图书分类");
		$("#bookTypeId").val( $(obj).attr("typeId"));
		$("#order").val($(obj).attr("paramOrder"));
		$("#typeName").val($(obj).attr("paramName"));
	}
	$("#showModal").modal("show");
}
/**
 * 校验参数
 */
function initEditPasswordFormValidate(){
	$('#add-form').validate({
		rules:{
			order: {
			    required: true,
			    ispassword:true
			},
			typeName: {
			    required: true,
			    rangelength:[0,10]
			}
		},
		messages:{
			order: {
				required:"请必填",
				ispassword:"1-100的整数"
			},
			typeName: {
				required:"请必填",
				rangelength:"1-10个字符"
			}
		},
		errorPlacement:function(error, element) { 
			error.appendTo(element.parent().find("span")); 
		} 
	});//验证帐号
	jQuery.validator.addMethod("ispassword",function(value,element){
		var length = value.length;
		var reg = /^(([1-9]\d?)|100)$/;
		return this.optional(element) || (reg.test(value));
	});
	
}

function sure(){
	
	if($('#add-form').valid()){
		var params = {
			order:$.trim($("#order").val()),
			name:$.trim($("#typeName").val()),
			id:$.trim($("#bookTypeId").val())
		};
		
		$.ajax({
			url:"/lms/bookType/addOrUpdate",
			contentType:"application/json",
			type:"post",
			data:JSON.stringify(params),
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
}
/**
 * 置顶
 */
function toTop(){
	var typeId = $(this).attr("typeId");
	$.ajax({
		url:"/lms/bookType/toTop/"+typeId,
		contentType:"application/json",
		type:"get",
		async:false,
		success:function(src){
			if(src.statusCode==200){
				window.location.href = window.location.href ;
			}else{
				showMessage("置顶异常");
			}
		},
		error:function(){
			showMessage("置顶异常");
		}
		
	});
}
function deleteType(){
	var typeId = $(this).attr("typeId");
	$.Zebra_Dialog('你确定要删除吗？', {
		'type' : 'question',
		'title' : '请确认',
		'buttons' : [ '确定', '取消' ],
		'onClose' : function(caption) {
			var option = (caption != '' ? '"' + caption + '"'
					: 'nothing');
			if ("\"确定\"" == option) {
				$.ajax({
					url:"/lms/bookType/delete/"+typeId,
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
	var typeId = $(this).parent().attr("typeId");
	window.location.href= "/lms/bookType/detail/"+typeId;
}
function showMessage(message){
	$.Zebra_Dialog(message, {
		'type':     'information',
		'title':    '提示',
		'buttons':  ['确定'],
	});
}