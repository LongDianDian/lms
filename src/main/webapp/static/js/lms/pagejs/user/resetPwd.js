define([ "jquery", "jquery-migrate", "zebra-dialog", "jquery-ztree-core", "jquery-ztree-excheck", "jquery-ztree-exedit", "jquery-validate"], function() {
	(function($) {
		$(function() {
			//初始化控件
			initComponents();
			//监听所有event事件
			addEventListener();
		});
		/*
		 * Init components. ==========================================
		 */
		function initComponents(){
			initFormValidate();
		}
		/*
		 * Add event listener. ==========================================
		 */
		function addEventListener(){
			$("#updateBtn").click(updateBtnClicked);
			$("#cancleBtn").click(cancleBtnClicked);
		}
		
		function cancleBtnClicked(){
			window.location = "/cas/user/list";
		}
		
		function updateBtnClicked(){
			if($('#form').valid()){
				var paramObj = {
						"username":$("#username").val(),
						"newPassword":$("#newPassword").val()
				};
				$.ajax({
			        type:"POST",
			        url:"resetPassword",
			        data:JSON.stringify(paramObj), 
			        dataType: 'json',
			        contentType:'application/json;charset=UTF-8',  
			        async:false,
			        success:function(json){
			        	if(json.statusCode=="200"){
			        		info = "重置密码成功！";
			        		$.Zebra_Dialog(info, {
				        		'type':     'information',
				        		'title':    '提示',
				        		'buttons':  ["确定"],
				        		'onClose': function(caption) {
						        		location.href="list";
				        		}
				        	});
			        	}else{
			        		info = "重置密码失败！";
			        		$.Zebra_Dialog(info, {
				        		'type':     'information',
				        		'title':    '提示',
				        		'buttons':  ["确定"]
				        	});
			        	}
			        	
			        },
			        error:function(){
			        	$.Zebra_Dialog("重置密码异常！", {
			        		'type':     'information',
			        		'title':    '提示',
			        		'buttons':  ["确定"]
			        	});
			        }
			    });
			}
		}
		function initFormValidate(){
			$('#form').validate({
				rules:{
					oldPassword: {
						required: true,
						rangelength:[1,128]
					},
					newPassword: {
						required: true,
						rangelength:[1,128]
					},
					confirmPassword: {
						required: true,
						isconfirmpassword:true,
					    equalTo: "#newPassword"
					}
				},
				messages:{
					oldPassword: {
						required:"请必填",
						ispassword:"密码长度为6-16位字母、数字或下划线"
					},
					newPassword: {
						required:"请必填",
						ispassword:"密码长度为6-16位字母、数字或下划线"
					},
					confirmPassword: {
						required:"请必填",
						isconfirmpassword:"密码长度为6-16位字母、数字或下划线",
						equalTo:"两次密码输入不一致"
					}
				}
			});			
		}
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
	})(jQuery);
});
