$(function () {
    addEventListener();
    initComponents();
    
});

function addEventListener(){
    $("#user-login-out").click(updates);
    $("#user-edit-password").click(toEditPassword);
    $("#user-edit-password-confirm").click(editPassword);
}

/**
 * 组装时间控件
 */
function initComponents()
{
	initEditPasswordFormValidate();
}

function updates(){
	window.location.href="/lms/login/logoutAndReferer";
}

function toEditPassword(){
	$("#editPasswordOldPassword").val("");
	$("#editPasswordNewPassword").val("");
	$("#editPasswordConfirmPassword").val("");
}

function editPassword(){
	if($('#editPassword-form').valid()){
		var params ={
				"username":$("#editPasswordUsername").val(),
				"oldPassword":$("#editPasswordOldPassword").val(),
				"newPassword":$("#editPasswordNewPassword").val(),
				"confirmPassword":$("#editPasswordConfirmPassword").val()
		}
		$("#editPasswordDialog").modal("hide");
		$.ajax({
			type:"POST",
			url:"/lms/login/editPassword",
			data:JSON.stringify(params),
			contentType:"application/json",
			async:false,
			success:function(json){
				if(json.statusCode==200){
					$.Zebra_Dialog('修改密码成功', {
						'type':     'information',
						'title':    '提示',
						'buttons':  ['确定'],
					});
				}else{
					$.Zebra_Dialog(json.message, {
						'type':     'information',
						'title':    '提示',
						'buttons':  ['确定'],
					});
				}
			},
			error:function(){
				$.Zebra_Dialog('修改密码失败', {
					'type':     'information',
					'title':    '提示',
					'buttons':  ['确定'],
				});
			}
		});
	}
	$("#editPasswordOldPassword").val("");
	$("#editPasswordNewPassword").val("");
	$("#editPasswordConfirmPassword").val("");
}

function initEditPasswordFormValidate(){
	$('#editPassword-form').validate({
		rules:{
//			editPasswordUsername: {
//				required: true,
//				isUserName:true
//			},
			editPasswordOldPassword: {
			    required: true,
			    ispassword:true
			},
			editPasswordNewPassword: {
			    required: true,
			    ispassword:true
			},
			editPasswordConfirmPassword: {
				required: true,
				isconfirmpassword:true,
			    equalTo: "#editPasswordNewPassword"
			},
		},
		messages:{
//			editPasswordUsername: {
//				required:"请必填",
//				isUserName:"请输入长度为1-32位英文帐号,不能以下划线结尾"
//			},
			editPasswordOldPassword: {
				required:"请必填",
				ispassword:"密码长度为6-16位字母、数字或下划线"
			},
			editPasswordNewPassword: {
				required:"请必填",
				ispassword:"密码长度为6-16位字母、数字或下划线"
			},
			editPasswordConfirmPassword: {
				required:"请必填",
				isconfirmpassword:"密码长度为6-16位字母、数字或下划线",
				equalTo:"两次密码输入不一致"
			},
		},
		errorPlacement:function(error, element) { 
			error.appendTo(element.parent().find("span")); 
		} 
	});//验证帐号
//	jQuery.validator.addMethod("isUserName",function(value,element){
//		var length = value.length;
//		//(?!_)(?!-)
//		var reg = /^(?!.*?_$)(?!.*?-$)[A-Za-z\@\-\_\.]+$/;
//		return this.optional(element) || (length >= 1 && length <= 32 && reg.test(value));
//	});
	//验证密码
	jQuery.validator.addMethod("ispassword",function(value,element){
		var length = value.length;
		var reg = /^[a-z0-9\_]+$/;
		return this.optional(element) || (length >= 6 && length <= 16 && reg.test(value));
	});
	//验证确认密码
	jQuery.validator.addMethod("isconfirmpassword",function(value,element){
		var length = value.length;
		var reg = /^[a-z0-9\_]+$/;
		return this.optional(element) || (length >= 6 && length <= 16 && reg.test(value));
	});
}
