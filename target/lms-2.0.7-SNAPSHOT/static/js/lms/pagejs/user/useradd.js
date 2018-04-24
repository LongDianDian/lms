//点击新增用户下一步
function addsub(){
	$(".addUser").css("display","none");
	$(".organization").css("display","block");
	document.form1.reset();
	return false;
}
//点击组织机构下一步
function orgsub(){
	$(".organization").css("display","none");
	$(".roledis").removeClass("display-none");
	document.form1.reset();
	return false;
}
//点击组织机构上一步
function orgsubUp(){
	$(".addUser").css("display","block");
	$(".organization").css("display","none");
	document.form1.reset();
	return false;
}
//点击角色分配下一步
function roledisSub(){
	$(".roledis").css("display","none");
	$(".finish").removeClass("display-none");
	document.form1.reset();
	return false;
}
//点击角色分配上一步
function roledisSubUp(){
	$(".roledis").addClass("display-none");
	$(".organization").css("display","block");
	document.form1.reset();
	return false;
}
//点击完成
function finishSub(){
	
}
