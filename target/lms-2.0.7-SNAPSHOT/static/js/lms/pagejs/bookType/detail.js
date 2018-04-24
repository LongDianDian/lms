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
	initCheckBox();
	initCalendarYM(".form_datetime");
}
/*
 * Add event listener. ==========================================
 */
function addCurrentEventListener() {
	$("#search").click(paging);
	$("#addBook").click(showAddModal);
	$("#searchForModel").click(pagingForModel);
	$("#sureForModal").click(sureForModal);
	$("#batch_shelve").click(batchShelve);
	$("#batch_unshelve").click(batchShelve);
}
/*
 * 分页
 */
function paging(){
	//分页
	$('#example').dataTable().fnDestroy();	
	var t = $('#example').dataTable({
				"searching": false,
				"processing": true,
				"serverSide": true,
				"ajax": {
					url:"/lms/bookType/mapping/list",
					contentType:'application/json;',  
					data:function ( param ) {
						$("input[name='param']").each(function(){
							var value = $.trim($(this).val());
							var id =$(this).attr("id");
							if(value!=""){
								if("publicationDate"==id){
									param[$(this).attr("id")]=value.replace("-","");
									console.log(value.replace("-",""))
								}else{
									param[$(this).attr("id")]=value;
								}
							}
						});
		            }
				},
				columns:[
				         {data:function(data){
				        	 var html='<input type="checkbox"  name="bookId" bookId='+data.id+'>';
				        	 return html;
				         }},
				         {data:function(data){
				        	 if(data.bookOrder){
				        		 if(data.bookOrder==100){
				        			 return "";
				        		 }
				        	 var html='<span style="cursor:pointer"  name="up-priority" bookId='+data.id+'  ><i  class="glyphicon glyphicon-arrow-up"></i></span>';
				        		 html+='<span style="cursor:pointer"   name="updata-priority"   >'+data.bookOrder+'</span>';
				        		 html+='<input class="inputs"  bookId='+data.id+'  name="input-priority" value='+data.bookOrder+' style="display:none">';
				        		 html+='<span  style="cursor:pointer"  name="down-priority" bookId='+data.id+' ><i class="glyphicon glyphicon-arrow-down"></i></span>';
				        		 return html;	 
				        	 }else{
				        		 return '';
				        	 }
				         }},
				         {data:"book.bookname"},
				         {data:"book.author"},
				         {data:"book.press"},
				         {data:function(  data ){
				        	 if(data.book.publicationDate!=null){
				        		 var y = data.book.publicationDate.toString().substring(0,4);
				        		 var str = y+"年";
				        		 var month=data.book.publicationDate.toString().substring(4,6);
				        		 str+=month+"月";
				        		 return str;
				        	 }else{
				        		 return "";
				        	 }
				         }},
				         {data:function(data){
				        	 if(data.shelveStatus==1){
				        		 return "已上架";
				        	 }else{
				        		 return "未上架";
				        	 }
				         }},{
				        	 data:function(data){
				        		 var html ='';
				        		 if(data.shelveStatus==1){
				        			 html +='<button class="btn btn-primary btn-sm" onclick="unShelve('+data.id+')" >下架</button>';
				        		 }else{
				        			 html +='<button class="btn btn-primary btn-sm" onclick="shelve('+data.id+')" >上架</button>';
				        		 }
				        		 html +='&nbsp<button class="btn btn-primary btn-sm" onclick="toTop('+data.id+')" >置顶</button>';
				        		 html +='&nbsp<button class="btn btn-danger btn-sm" onclick="removeFromType('+data.id+')" >移除</button>';
				        		 return html;
				        	 }
				         }
				         ],
		    "aoColumnDefs":[//设置列的属性，此处设置第一列不排序
		                    {"bSortable": false, 
		                      "targets": [0,1,2,3,4,5,6,7]
		                    }
		                ],
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
		         },
		         "fnDrawCallback": function(){
		        	 	$("span[name='up-priority']").click(function(){
	        				var parentId = $(this).attr("bookId");
	        				var priorityOld = parseInt($(this).next().text());
	        				var priority =priorityOld-1;
	        				var reg = /^[1-9][0-9]?$/;
	        				if(!reg.test(priority)){
	        					showMessageWithoutClose("序号为1-99！.");
	        					return false;
	        				}
	        				updateOrder(parentId,priority);
		        	 	});
		        	 	$("span[name='down-priority']").click(function(){
	        				var parentId = $(this).attr("bookId");
	        				var priorityOld = parseInt($(this).prev().prev().text());
	        				var priority =priorityOld+1;
	        				var reg = /^[1-9][0-9]?$/;
	        				if(!reg.test(priority)){
	        					showMessageWithoutClose("序号为1-99！.");
	        					return false;
	        				}
	        				updateOrder(parentId,priority);
		        	 	});
		        		$("span[name='updata-priority']").each(function(){
		        			$(this).click(function(){
		        				$(this).hide();
		        				$(this).next().show();
		        				$(this).next().focus();
		        			});
		        			$(this).next().blur(function(){
		        				
		        				var parentId = $(this).attr("bookId");
		        				var priorityOld = parseInt($(this).prev().text());
		        				var priority =$.trim($(this).val());
		        				var reg =/^[1-9][0-9]?$/;
		        				if(!reg.test(priority)){
		        					showMessageWithoutClose("序号为1-99的整数.");
		        					$(this).val(priorityOld);
		        					return false;
		        				}
		        				if(priorityOld == priority){
		        					$(this).hide();
		        					$(this).prev().show();
		        					return  false;
		        				}
		        				updateOrder(parentId,priority);
		        			});
	        			}); 
		        		$("input[name='bookId']").change(function(){
		        			var list = $("input[name='bookId']:checked");
		        			if(list.length>0){
		        				$("button[name='top']").attr("disabled",false);
		        			}else{
		        				$("button[name='top']").attr("disabled",true);
		        			}
		        		});
		         }
			});
	$("#one_chackAll").prop("checked",false);
	$("button[name='top']").attr("disabled",true);
}

function showMessageWithoutClose(message){
	$.Zebra_Dialog(message, {
		'type':     'information',
		'title':    '提示',
		'buttons':  ['确定']
	});
}
function showMessage(message,methd){
	$.Zebra_Dialog(message, {
		'type':     'information',
		'title':    '提示',
		'buttons':  ['确定'],
		'onClose':function(){
			methd();
		}
	});
}

/**
 * 展示添加book的modal
 */
function showAddModal(){
	$("input[name='paramForModel']").val("");
	pagingForModel();
	$("#showModal").modal("show");
	
}
/*
 * 添加图书到分类的分页
 */
function pagingForModel(){
	$("#checkAll").prop("checked",false);
	$("#sureForModal").attr("disabled",true);
	//分页
	$('#add_div_table').dataTable().fnDestroy();	
	var t = $('#add_div_table').dataTable({
				"searching": false,
				"processing": true,
				"serverSide": true,
				"ajax": {
					url:"/lms/bookType/addBookForModel",
					contentType:'application/json;',  
					data:function ( param ) {
						param["bookTypeId"]=$("#bookTypeId").val();
						$("input[name='paramForModel']").each(function(){
							var value = $.trim($(this).val());
							var id =$(this).attr("idForModel");
							if(value!=""){
								if("publicationDate"==id){
									param[$(this).attr("idForModel")]=value.replace("-","");
								}else{
									param[$(this).attr("idForModel")]=value;
								}
							}
						});
						console.log(param);
					}
				},
				columns:[
				         {data:null	},
				         {data:"bookname"},
				         {data:"author"},
				         {data:"press"},
				         {data:function(  data ){
				        	 if(data.publicationDate!=null){
				        		 var y = data.publicationDate.toString().substring(0,4);
				        		 var str = y+"年";
				        		 var month=data.publicationDate.toString().substring(4,6);
				        		 str+=month+"月";
				        		 return str;
				        	 }else{
				        		 return "";
				        	 }
				         }}
				         ],
		    "aoColumnDefs":[//设置列的属性，此处设置第一列不排序
		                    {"bSortable": false, 
		                      "targets": [0,1,2,3,4]
		                    }
		                ],
            "rowCallback": function( row, data, index ) {
                  $('td:eq(0)', row).html("<input type='checkbox' bookId="+data.id+" name='bookIdForModal' onclick='initOneCheckBox()'>");
              },       
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
function initCheckBox(){
	$("#one_checkAll").change(function(){
		if($(this).prop("checked")){
			$("input[name='bookId']").prop("checked",true);
		}else{
			$("input[name='bookId']").prop("checked",false);
		}
		var list = $("input[name='bookId']:checked");
		if(list.length>0){
			$("button[name='top']").attr("disabled",false);
		}else{
			$("button[name='top']").attr("disabled",true);
		}
	});
	$("#checkAll").change(function(){
		if($(this).prop("checked")){
			$("input[name='bookIdForModal']").prop("checked",true);
		}else{
			$("input[name='bookIdForModal']").prop("checked",false);
		}
		var list = $("input[name='bookIdForModal']:checked");
		if(list.length>0){
			$("#sureForModal").attr("disabled",false);
		}else{
			$("#sureForModal").attr("disabled",true);
		}
	});
}
function initOneCheckBox(){
	var list = $("input[name='bookIdForModal']:checked");
	if(list.length>0){
		$("#sureForModal").attr("disabled",false);
	}else{
		$("#sureForModal").attr("disabled",true);
	}
}
/**
 * 添加图书到对应分类
 */
function sureForModal(){
	var bookIds = new Array();
	$("input[name='bookIdForModal']:checked").each(function(){
		bookIds.push($(this).attr("bookId"));
	});
	var bookTypeId = $("#bookTypeId").val();
	$.ajax({
		url:"/lms/bookType/addMapping/"+bookTypeId,
		type:"post",
		contentType:"application/json",
		data:JSON.stringify(bookIds),
		async:false,
		success:function(src){
			if(src.statusCode ==200){
				window.location.href = "/lms/bookType/detail/"+bookTypeId;
			}else{
				$("#showModal").modal("hide");
				showMessageWithoutClose("添加图书到该栏目异常！");
			}
		},
		error:function(){
			showMessageWithoutClose("添加图书到该栏目异常！");
		}
	});
}
/**
 * 上架
 */
function shelve(id){
	$.ajax({
		url:"/lms/bookType/shelve/"+id+"/1",
		dataType:"json",
		success:function(src){
			if(src.statusCode==200){
				showMessage("上架成功！",paging);
			}else{
				showMessageWithoutClose("上架异常！");
			}
		},
		error:function(){
			showMessageWithoutClose("上架异常！");
		}
	});
}
/**
 * 下架
 */
function unShelve(id){
	$.ajax({
		url:"/lms/bookType/shelve/"+id+"/0",
		dataType:"json",
		success:function(src){
			if(src.statusCode==200){
				showMessage("下架成功！",paging);
			}else{
				showMessageWithoutClose("下架异常！");
			}
		},
		error:function(){
			showMessageWithoutClose("下架异常！");
		}
	});
}
/**
 * 置顶
 */
function toTop(id){
	var bookTypeId = $("#bookTypeId").val();
	$.ajax({
		url:"/lms/bookType/mappingToTop/"+id+"/"+bookTypeId,
		dataType:"json",
		success:function(src){
			if(src.statusCode==200){
				showMessage("操作成功！",paging);
			}else{
				showMessageWithoutClose("操作异常！");
			}
		},
		error:function(){
			showMessageWithoutClose("操作异常！");
		}
	});
}
/**
 * 移除
 */
function removeFromType(id){
	$.ajax({
		url:"/lms/bookType/mappingRemove/"+id,
		dataType:"json",
		success:function(src){
			if(src.statusCode==200){
				showMessage("操作成功！",paging);
			}else{
				showMessageWithoutClose("操作异常！");
			}
		},
		error:function(){
			showMessageWithoutClose("操作异常！");
		}
	});
}

function updateOrder(id ,order){
	var bookTypeId = $("#bookTypeId").val();
	var param = {
			mappingId: parseInt(id),
			mappingOrder:parseInt(order),
			bookTypeId:parseInt(bookTypeId)
	}
	$.ajax({
		url:"/lms/bookType/mappingUpdataOrder",
		type:"post",
		contentType:"application/json",
		data:JSON.stringify(param),
		dataType:"json",
		async:false,
		success:function(src){
			if(src.statusCode==200){
				showMessage("操作成功！",paging);
			}else{
				showMessageWithoutClose("修改异常！");
			}
		},
		error:function(){
			showMessageWithoutClose("修改异常！");
		}
	});
}

function batchShelve(){
	var ids = new Array();
	$("input[name='bookId']:checked").each(function(){
		ids.push(parseInt($(this).attr("bookId")));
	});
	var url = "/lms/bookType/batchShelve/1";
	if($(this).attr("id")=="batch_unshelve"){
		 url = "/lms/bookType/batchShelve/0";
	}
	$.ajax({
		url:url,
		type:"post",
		contentType:"application/json",
		data:JSON.stringify(ids),
		dataType:"json",
		async:false,
		success:function(src){
			if(src.statusCode==200){
				showMessage("操作成功！",paging);
			}else{
				showMessageWithoutClose("操作异常！");
			}
		},
		error:function(){
			showMessageWithoutClose("操作异常！");
		}
	});
}
