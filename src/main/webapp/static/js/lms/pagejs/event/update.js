$(function() {
	
	var uploadEditor ;
	// 初始化控件
	initCurrentComponents();
	// 监听所有event事件
	addCurrentEventListener();
});
/*
 * Init components. ==========================================
 */
function initCurrentComponents() {
	uploadEditor = UM.getEditor('myEditor');
}
/*
 * Add event listener. ==========================================
 */
function addCurrentEventListener() {
	$("#buttion_sure").click(getparams);
	
}
function getparams(){
	var title = $.trim($("#title").val());
	if(title=="" || title.length>100){
		showMessage("标题必须为1-100字符！");
		return false;
	}
	var schoolId = $("#schoolId").val();
	if(!uploadEditor.hasContents()){
		showMessage("内容不能为空！");
		return false;
	}
	var params = {};
	params["content"]=uploadEditor.getContent();
	var imgs =[];
	$("#myEditor img").each(function(){
		imgs.push($(this).attr("src"));
	});
	params["imgs"]= imgs;
	params["schoolId"]=schoolId;
	params["title"]=title;
	params["outline"]= $.trim(uploadEditor.getContentTxt()).substring(0,200);
	params["type"]=$("#type").val();
	params["id"]=$("#eventId").val();
	
	var type = $("#type").val();
	$.ajax({
		url:"/lms/event/add",
		contentType:"application/json",
		type:"post",
		data:JSON.stringify(params),
		async:false,
		success:function(src){
			if(src!=null && src.statusCode==200){
				window.location.href = "/lms/event/list/"+type;
			}else{
				showMessage(status.message);
			}
		},
		error:function(){
			showMessage("添加失败!");
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
