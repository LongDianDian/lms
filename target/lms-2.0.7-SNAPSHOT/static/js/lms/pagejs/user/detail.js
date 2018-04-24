		$(function() {
			var user = null;
			if(id!=""){
				user = $.parseJSON(userJson);
			}
			//初始化控件
			initComponents();
			//监听所有event事件
			addCurrentEventListener();
		});
		/*
		 * Init components. ==========================================
		 */
		function initComponents(){
			initGroupTree();
			initGroupCheckBoxChecked();
			initRoleCheckBoxChecked();
			initFormValidate();
			refreshGroupCheck();
			refreshRoleCheck();
		}
		/*
		 * Add event listener. ==========================================
		 */
		function addCurrentEventListener(){
			$('#saveBtn').click(save);
			$('#toListBtn').click(toList);
			$('#addsub').click(addsub);
			$('#orgsubUp').click(orgsubUp);
			$('#orgsub').click(orgsub);
			$('#roleSubUp').click(roleSubUp);
			$('#roleSub').click(roleSub);
			$('#finishSubUp').click(finishSubUp);
			
//			$("button[name='addDiv']").click(addDiv);
			initGroupDivClick();
			initGroupCheckSingle();
			initRoleTrClick();
			initRoleCheckSingle();
		}
		/*
		 * 初始化组织机构树
		 */
		function initGroupTree() {
			showGroupTree(readGroupJson(groupsJson));
		}
		function initGroupCheckBoxChecked(){
			if(user!=null && user.groups!=null && user.groups.length>0){
				for(var i=0;i<user.groups.length;i++){
					var group = user.groups[i];
					$('#groupCheckBox_'+group.id).attr("checked",true);
				}
			}
		}
		function initRoleCheckBoxChecked(){
			if(user!=null && user.roles!=null && user.roles.length>0){
				for(var i=0;i<user.roles.length;i++){
					var role = user.roles[i];
					$('#roleCheckBox_'+role.id).attr("checked",true);
				}
			}
		}
		function initFormValidate(){
			$('#form').validate({
				rules:{
					nickname: {
						required: true,
						isNickName:true
					},
					username: {
						required: true,
						isUserName:true
					},
					password: {
					    required: true,
					    ispassword:true
					},
					confirmPassword: {
						required: true,
						isconfirmpassword:true,
					    equalTo: "#password"
					},
					email: {
						required: false,
						isemail:true
					},
					address: {
						required: false,
						rangelength:[0,128]
					},
					telelphone: {
						required: false,
						istelelphone:true
					},
					description: {
						required: false,
						rangelength:[0,256]
					}
				},
				messages:{
					nickname: {
						//required:jQuery.validator.format("请输入1-8位中文姓名")
						required:"请必填",
						isNickName:"请输入1-100位中文或英文姓名"
					},
					username: {
						required:"请必填",
						isUserName:"请输入长度为1-32位英文或数字帐号,不能以下划线结尾"
					},
					password: {
						required:"请必填",
						ispassword:"密码长度为6-16位字母、数字或下划线"
					},
					confirmPassword: {
						required:"请必填",
						isconfirmpassword:"密码长度为6-16位字母、数字或下划线",
						equalTo:"两次密码输入不一致"
					},
					email: {
						required:"",
						isemail:"请输入正确的电子邮箱"
					},
					address: {
						required:"",
						rangelength:"您输入的地址信息过长"
					},
					telelphone: {
						required:"",
						istelelphone:"请输入长度为1-18位的电话"
					},
					description: {
						required:"",
						rangelength:"您输入的备注信息过长"
					}
				}
			});			
		}
		//验证帐号是否重复
		jQuery.validator.addMethod("isUserNameCanUse",function(value,element){
			var canUse = checkUserNameCanUse(value);
			return this.optional(element) || canUse;
		});
		//验证姓名
		jQuery.validator.addMethod("isNickName",function(value,element){
			var length = value.length;
			var reg = /^[\u4e00-\u9fa5A-Za-z\·]+$/;
			return this.optional(element) || (length >= 1 && length <= 100 && reg.test(value));
			
		});
		//验证帐号
		jQuery.validator.addMethod("isUserName",function(value,element){
			var length = value.length;
			//(?!_)(?!-)
			var reg = /^(?!.*?_$)(?!.*?-$)[0-9A-Za-z\@\-\_\.]+$/;
			return this.optional(element) || (length >= 1 && length <= 32 && reg.test(value));
		});
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
		//验证电话
		jQuery.validator.addMethod("istelelphone",function(value,element){
			var length = value.length;
			var reg = /^[0-9\+\-\s]+$/;
			return this.optional(element) || (length <= 18 && reg.test(value));
		});
		//验证电子邮箱
		jQuery.validator.addMethod("isemail",function(value,element){
			var length = value.length;
			var reg = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
			return this.optional(element) || (length >= 1 && length <= 50 && reg.test(value));
		});
		
		/*
		 * 解析GroupJson
		 */
		function readGroupJson(json){
			var nodes = [];
			var groups = $.parseJSON(json);
			if(groups!=null){
				$.each(groups,function(index,group){
	                var node = {
	                    id : group.id,
	                    pId : group.parent==null?"":group.parent.id,
	                    name : "",
	                    obj : group,
	                    open : true
	                };
	                nodes.push(node);
				});
			}
			return nodes;
		}
		/*
		 * 展示组织架构树
		 */
		function showGroupTree(nodes){
			var setting = {
				view: {
					addDiyDom: addName
				},
	            check: {
					enable: true
				},
	            data: {
	                simpleData: {
	                    enable: true
	                }
	            }
	        };
			$.fn.zTree.init($("#groupTree"), setting, nodes);
		}
		function addName(treeId, treeNode){
			var nodeObj = $("#" + treeNode.tId + "_a");
			var NameStr = "";
			NameStr += "<div id='groupLine_"+treeNode.obj.id+"' name='groupLine' class='treeNodeDiv'>";
			NameStr += "<input type='hidden' id='groupName_"+treeNode.obj.id+"' value='"+treeNode.obj.name+"'/>";
			NameStr += "<input type='checkbox' id='groupCheckBox_"+treeNode.obj.id+"' name='groupCheckBox' value='"+treeNode.obj.id+"'/>";
			NameStr += "<span id='nameSpan_"+treeNode.obj.id+"' name='nameSpan'>"+treeNode.obj.name + (treeNode.obj.category==null?"":"("+treeNode.obj.category.name+")")+"</span>";
			NameStr += "</div>";
			nodeObj.append(NameStr);
		}
		function save(){
			if($('#address').length>0&&$('#address').val().trim()==""){
				$('#address').val("无");
			}
			if($('#telelphone').length>0&&$('#telelphone').val().trim()==""){
				$('#telelphone').val("0");
			}
			if($('#description').length>0&&$('#description').val().trim()==""){
				$('#description').val("无");
			}
			
//			if($('#address').val().trim()==""){
//				$('#address').val("无");
//			}
//			if($('#telelphone').val().trim()==""){
//				$('#telelphone').val("0");
//			}
//			if($('#description').val().trim()==""){
//				$('#description').val("无");
//			}
			if(id==""){
				if(!checkUserNameCanUse($('#username').val())){
					$.Zebra_Dialog('该帐号已存在，不能重复添加!', {
						'type':     'information',
						'title':    '提示',
						'buttons':  [
						             {caption: '确定', callback: function(){
						            	 $(".finish").css("display","none");
						            	 $(".addUser").css("display","block");
						             }}
							        ]
					});
					$('.ZebraDialog_Close').click(function(){
						$('.ZebraDialog_Button_0').trigger("click");
					});
				}else{
					form.action="save";
				}
			}else{
				form.action="update";
			}
			form.submit();
		}
		function toList(){
			window.location.href="list";
			window.event.returnValue = false;
		}
		//点击新增用户下一步
		function addsub(){
			if($('#form').valid()){
				$("[name = 'nicknameSpan']").each(function(index,element){
					element.innerHTML = $('#nickname').val();
				});
				$("[name = 'usernameSpan']").each(function(index,element){
					element.innerHTML = $('#username').val();
				});
				$("[name = 'descriptionSpan']").each(function(index,element){
					element.innerHTML = $('#description').val();
				});
				$("[name = 'enableSpan']").each(function(index,element){
					if($("[name = 'enable']:checked").val() == "true"){
						element.innerHTML = "启用";
					}else if($("[name = 'enable']:checked").val() == "false"){
						element.innerHTML = "停用";
					}
				});
				if(id=="" && !checkUserNameCanUse($('#username').val())){
					$.Zebra_Dialog('该帐号已存在，不能重复添加!', {
						'type':     'information',
						'title':    '提示',
						'buttons':  ['确定']
					});
				}else{
					$(".addUser").css("display","none");
					$(".organization").css("display","block");
				}
				return false;
			}
		}
		//点击组织机构下一步
		function orgsub(){
			var groupIds = new Array();
			var groupNames = "";
			var i = 0;
			$("input[name='groupCheckBox']").each(function(index,element){
				if($('#'+element.id).attr("checked")=="checked"){
					if(i!=0){
						groupNames += ",";
					}
					groupIds[i] = parseInt(element.value);
					groupNames += $('#groupName_'+element.value).val();
					i = i+1;
				}
			});
			$('#groupIds').val(groupIds);
			console.info(groupNames);
			$("[name = 'groupsSpan']").each(function(index,element){
				element.innerHTML = groupNames;
			});
			$(".organization").css("display","none");
			$(".role").css("display","block");
			return false;
		}
		//点击组织机构上一步
		function orgsubUp(){
			$(".addUser").css("display","block");
			$(".organization").css("display","none");
			return false;
		}
		//点击角色分配下一步
		function roleSub(){
			var roleIds = new Array();
			var roleNames = "";
			var i = 0;
			$("input[name='roleCheckBox']").each(function(index,element){
				if($('#'+element.id).attr("checked")=="checked"){
					if(i!=0){
						roleNames += ",";
					}
					roleIds[i] = parseInt(element.value);
					roleNames += $('#roleName_'+element.value).val();
					i = i+1;
				}
			});
			$('#roleIds').val(roleIds);
			$("[name = 'rolesSpan']").each(function(index,element){
				element.innerHTML = roleNames;
			});
			$(".role").css("display","none");
			$(".finish").css("display","block");
			return false;
		}
		//点击角色分配上一步
		function roleSubUp(){
			$(".role").css("display","none");
			$(".organization").css("display","block");
			return false;
		}
		//点击完成上一步
		function finishSubUp(){
			$(".finish").css("display","none");
			$(".role").css("display","block");
			return false;
		}
		function initRoleTrClick(){
			$("tr[name='roleLine']").each(function(){
				var tr = $(this);
				tr.children("td").not("[name = 'roleCheckTd']").click(function(){
					tr.children("td").children("input[name = 'roleCheckBox']").trigger("click");
				});
			});
		}
		function initRoleCheckSingle(){
			$("input[name='roleCheckBox']").each(function(){
				$('#roleCheckBox_'+$(this).val()).click(function(){
					activeRoleCheckLine($(this).val());
				});
			});
		}
		function refreshRoleCheck(){
			$("input[name='roleCheckBox']").each(function(){
				activeRoleCheckLine($(this).val());
			});
		}
		function activeRoleCheckLine(id){
			$('#roleLine_'+id).removeClass("activeTr");
			if($('#roleCheckBox_'+ id).attr("checked")=="checked"){
				$('#roleLine_'+id).addClass("activeTr");
			}
		}
		function initGroupDivClick(){
			$("div[name='groupLine']").each(function(){
				var div = $(this);
				div.children("span").click(function(){
					div.children("input[name = 'groupCheckBox']").trigger("click");
				});
			});
		}
		function initGroupCheckSingle(){
			$("input[name='groupCheckBox']").each(function(){
				$('#groupCheckBox_'+$(this).val()).click(function(){
					activeGroupCheckLine($(this).val());
				});
			});
		}
		function refreshGroupCheck(){
			$("input[name='groupCheckBox']").each(function(){
				activeGroupCheckLine($(this).val());
			});
		}
		function activeGroupCheckLine(id){
			$('#groupLine_'+id).removeClass("activeTreeDiv");
			if($('#groupCheckBox_'+ id).attr("checked")=="checked"){
				$('#groupLine_'+id).addClass("activeTreeDiv");
			}
		}
		function checkUserNameCanUse(username){
			console.log(username)
			var canUse = false;
			var params = "username="+username;
			$.ajax({
				type:"POST",
				url:"checkUsername",
				data:params,
				async:false,
				success:function(json){
					if(json.statusCode==200){
						canUse = true;
					}else{
						canUse = false;
					}
				},
				error:function(){
					canUse = false;
				}
			});
			return canUse;
        }
