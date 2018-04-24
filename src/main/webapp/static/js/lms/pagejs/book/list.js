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
	$("#search").click(paging);
	initCalendarYM(".form_datetime");
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
					url:"/lms/book/getDate",
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
				         {data:null	},
				         {data:"bookname"},
				         {data:"author"},
				         {data:"press"},
				         {data:function(  data ){
				        	 if(data.publicationDate!=null){
				        		 var date=new Date();
				        		 date.setTime(data.publicationDate);
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
		        	var api = this.api();
		        	api.column(0).nodes().each(function(cell, i) {
	        			cell.innerHTML =  i + 1;
		        	});
		        }
			});
}
function getSearchParams(){
	var obj = {};
	$("input[name='param']").each(function(){
		var id = $(this).attr("id");
		var value = $.trim($(this).val());
		if(value!=""){
			if("publicationDate"==id){
				obj[$(this).attr("id")]=value;
			}else{
				obj[$(this).attr("id")]=value;
			}
		}
	});
	console.log(obj);
	return obj;
}

