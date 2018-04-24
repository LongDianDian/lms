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
	
}
/*
 * Add event listener. ==========================================
 */
function addCurrentEventListener() {
	$("#div_headPoster").mousemove(function(){
		var url = $.trim($("#headPosterFile_img").attr("src"));
		if(url!=""){
			$("#deleteHeadPoster").css("visibility","visible");
		}
	});
	$("#div_headPoster").mouseout(function(){
			$("#deleteHeadPoster").css("visibility","hidden");
	});
	$("#div_footPoster").mousemove(function(){
		var url = $.trim($("#footPosterFile_img").attr("src"));
		if(url!=""){
			$("#deleteFootPoster").css("visibility","visible");
		}
	});
	$("#div_footPoster").mouseout(function(){
			$("#deleteFootPoster").css("visibility","hidden");
	});
	$("#headPosterFile").change(fileChange);
	$("#footPosterFile").change(fileChange);
	$("#deleteHeadPoster").click(deleteHeadPoster);
	$("#deleteFootPoster").click(deleteFootPoster);
	$("#buttion_sure").click(add);
	
}
function fileChange(){
	var objId = $(this).attr("id");
	if (FileReader) {
		var reader = new FileReader(), file = this.files[0];
		reader.onload = function(e) {
			var image = new Image();
			image.src = e.target.result;
			$("#"+objId+"_base64").val(e.target.result);
			image.onload = function() {
				var objUrl = getObjectURL(file);
				$("#"+objId+"_img").attr("src", objUrl);
			};
		};
		reader.readAsDataURL(file);
	}else{
		showMessage("该浏览器不支持FileReader属性，请更换浏览器重试！");
	}
}
function deleteHeadPoster(){
	$("#headPosterFile_img").attr("src","");
	$("#headPosterFile_base64").val("");
}
function deleteFootPoster(){
	$("#footPosterFile_img").attr("src","");
	$("#footPosterFile_base64").val("");
	
}
function showMessage(message){
	$.Zebra_Dialog(message, {
		'type':     'information',
		'title':    '提示',
		'buttons':  ['确定'],
	});
}
function getObjectURL(file) {
	var url = null;
	if (window.createObjectURL != undefined) { // basic
		url = window.createObjectURL(file);
	} else if (window.URL != undefined) { // mozilla(firefox)
		url = window.URL.createObjectURL(file);
	} else if (window.webkitURL != undefined) { // webkit or chrome
		url = window.webkitURL.createObjectURL(file);
	}
	return url;
}
function add(){
	var schoolName = $.trim($("#schoolName").val());
	var schoolMotto = $.trim($("#schoolMotto").val());
	if(schoolName==""){
		showMessage("学校名称不能为空！");
		return false;
	}
	if(schoolName!=""&&schoolName.length>20){
		showMessage("学校名称长度不能大于20！");
		return false;
	}
	if(schoolMotto!=""&& schoolMotto.length>500){
		showMessage("校训长度不能大于500！");
		return false;
	}
	var params ={
			name:schoolName,
			schoolMotto:schoolMotto,
			headPostUrl:$.trim($("#headPosterFile_base64").val()),
			footPostUrl:$.trim($("#footPosterFile_base64").val()),
	};
	console.log(JSON.stringify(params));
	$.ajax({
		url:"/lms/school/add",
		contentType:"application/json",
		type:"post",
		data:JSON.stringify(params),
		async:false,
		success:function(src){
			if(src!=null && src.statusCode==200){
				window.location.href = "/lms/school/list";
			}else{
				showMessage(status.message);
			}
		},
		error:function(){
			showMessage("添加学校异常!");
		}
	});
}