define([ "jquery", "jquery-migrate"], function() {
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
			
		}
		/*
		 * Add event listener. ==========================================
		 */
		function addEventListener(){
//			$('#checkAll').click(checkAll);
			$('#searchBtn').click(search);
			$('#savePageBtn').click(savePage);
			$("button[name='resetPwdBtn']").click(function(){resetBtnClicked(this)});
			initUpdatePageBtnListener();
		}
		
		/*//checkbox全选事件
		function checkAll(){
			$("input[name='id']").attr("checked",$('#checkAll').attr("checked")=="checked");
		}*/
		//搜索
		function search(){
			searchForm.submit();
		}
		//跳转到新增页面
		function savePage(){
			window.location.href="savePage";
		}
		function resetBtnClicked(obj){
			window.location.href="resetPwdPage?username=" + $(obj).val();
		}
		//初始化修改按钮监听事件
		function initUpdatePageBtnListener(){
			$("[name = 'updatePageBtn']").each(
				function addUpdagePageListener(index,element){
					$('#updatePageBtn_'+element.value).click(
						function updatePage(){
							window.location.href="updatePage?username=" + $('#ID_'+element.value).val();
						}
					);
				}
			);	
		}
	})(jQuery);
});
