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
	$("#toAdd").click(function(){
		window.location.href="/lms/event/toAdd/"+$("#type").val();
	})
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
					url:"/lms/event/getDate",
					contentType:'application/json;',  
					data:function ( param ) {
						var type = $("#type").val();
						if(""!=type){
							param["type"]=$("#type").val();
						}
		            }
				},
				columns:[
				         {data:"title"},
				         {data:function(date){
				        	 return formatDateTime(date.createTime);
				         }},
				         {
				        	 data:function(data){
				        		 var html ='';
			        			 html +='<button class="btn btn-primary btn-sm"  eventId="'+data.id+'"  name="update">编辑</button>';
				        		 html +='&nbsp<button class="btn btn-danger btn-sm"  eventId="'+data.id+'" name="delete" >删除</button>';
				        		 return html;
				        	 }}
				         ],
		         "fnDrawCallback": function(){  
		        	 	$("button[name='update']").click(function(){
		        	 		window.location.href="/lms/event/toUpdate/"+$("#type").val()+"?eventId="+$(this).attr("eventId");
		        	 	});
		        	 	$("button[name='delete']").click(function(){
		        	 		$.ajax({
		        	 			url:"/lms/event/delete/"+$(this).attr("eventId"),
		        	 			type:"get",
		        	 			success:function(src){
		        	 				if(src.statusCode==200){
		        	 					paging();
		        	 				}else{
		        	 					showMessage("删除失败！");
		        	 				}
		        	 			}
		        	 		})
		        	 	})
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
	         }});
}

function formatDateTime(longDateTime){
	var date=new Date(longDateTime);
	var year=date.getFullYear();
	var month=date.getMonth()+1;
	if(month<9)
		month="0"+month;
	var today=date.getDate();
	if(today<10)
		today="0"+today;
	var hour=date.getHours();
	if(hour<10)
		hour="0"+hour;
	var minute=date.getMinutes();
	if(minute<10)
		minute="0"+minute;
	var second=date.getSeconds();
	if(second<10)
		second="0"+second;
	return year+"-"+month+"-"+today+" "+hour+":"+minute+":"+second;
}

function showMessage(message){
	$.Zebra_Dialog(message, {
		'type':     'information',
		'title':    '提示',
		'buttons':  ['确定'],
	});
}
