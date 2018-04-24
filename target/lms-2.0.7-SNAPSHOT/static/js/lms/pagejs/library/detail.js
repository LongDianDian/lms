$(function() {
	// 初始化控件
	initCurrentComponents();
	// 监听所有event事件
	addCurrentEventListener();
	var isCheck = false;
});
/*
 * Init components. ==========================================
 */
function initCurrentComponents() {
	  $('#list li').wookmark();
	// 自定义参数调用
	$('#list li').wookmark({
	  container: $('#list'),
	  offset: 10,
	  itemWidth: 300
	});
}
/*
 * Add event listener. ==========================================
 */
function addCurrentEventListener() {
	$("#addImg").click(showModal);
	$("#file").change(fileChange);
	$("#sure").click(sure);
	$("span[name='del']").click(del);
	
}

function  showModal(){
	$("#showModal").modal("show");
	$("#file").val();
	$("#file").next().html("");
}

function fileChange(){
	isCheck = false;
	var val = $(this).val();
	if(val==""){
		$("#file").next().html('未选择内容'); 
		isCheck = false;
		return false;
	}
	if(!val.match(/.*(.jpg|.png|.jpeg|.bmp)$/) ){ 
		$("#file").next().html('图片格式无效！'); 
		isCheck = false;
		return false;
	}
	if (FileReader) {
		var reader = new FileReader(), file = this.files[0];
		if(file.size > 1024*1024*2){
			$("#file").next().html("上传文件不能大于2兆");
			isCheck = false;
			return false;
		}
		reader.onload = function(e) {
			var image = new Image();
			image.src = e.target.result;
			$("#base64").val(e.target.result);
			$("#width").val(image.width);
			$("#height").val(image.height);
		};
		isCheck = true;
		$("#file").next().html(''); 
		reader.readAsDataURL(file);
	}else{
		showMessage("该浏览器不支持FileReader属性，请更换浏览器重试！");
	}
}
function sure(){
	if(isCheck){
		var params = {};
		params["libraryId"]=$("#libraryId").val();
		params["url"]=$("#base64").val();
		params["type"]=1;
		params["kind"]=2;
		params["width"]=$("#width").val();
		params["height"]=$("#height").val();
		$.ajax({
			url:"/lms/media/add",
			contentType:"application/json",
			type:"post",
			data:JSON.stringify(params),
			async:false,
			success:function(src){
				if(src!=null && src.statusCode==200){
					window.location.href = "/lms/library/detail/"+$("#libraryId").val();
				}else{
					showMessage(status.message);
				}
			},
			error:function(){
				showMessage("添加学校异常!");
			}
		});
	}
}
function del (){
	$.ajax({
		url:"/lms/media/del/"+$(this).attr("mediaId"),
		type:"get",
		async:false,
		success:function(src){
			if(src!=null && src.statusCode==200){
				window.location.href = "/lms/library/detail/"+$("#libraryId").val();
			}else{
				showMessage(status.message);
			}
		},
		error:function(){
			showMessage("添加学校异常!");
		}
	});
}