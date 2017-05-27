<style>
b{
	font-size:20px;
	}
</style>

<!-- BEGIN PAGE LEVEL STYLES --> 
<link href="${base}/metronic.bootstrap/media/css/bootstrap-fileupload.css" rel="stylesheet" />
<link href="${base}/metronic.bootstrap/media/css/chosen.css" rel="stylesheet" />
<!-- END PAGE LEVEL STYLES -->
	
<!-- BEGIN PAGE CONTAINER-->
<div class="container-fluid" style="padding-left: 0px; padding-right:0px !important">
	<!-- BEGIN PAGE HEADER-->
	<div class="row-fluid">
		<div class="span12">
			<!-- BEGIN PAGE TITLE & BREADCRUMB-->
			<ul class="breadcrumb">
				<h4 class="text-center">修改密码</h4>
			</ul>
			<!-- END PAGE TITLE & BREADCRUMB-->
		</div>
	</div>
	<!-- END PAGE HEADER-->
</div>

<div class="container-fluid">	
	<!-- BEGIN PAGE CONTENT-->
	<div class="row-fluid">
		<div class="span12">
			<form class="form-horizontal" name="updatePasswordForm" action="doUpdatePassword.action" method="post">
			<#assign actionErrors = stack.findValue("actionErrors")?if_exists/>
			<#if actionErrors?has_content || fieldErrors?has_content>
				<div class="alert alert-error">
					<button class="close" data-dismiss="alert"></button>
					<span>
						旧密码错误!
					</span>
				</div>
			</#if>
			<div class="control-group">
				<label class="control-label">旧密码</label>
				<div class="controls">
					<input type="password" name="oldPassword" id="oldPsd" class="input span6">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">新密码</label>
				<div class="controls">
					<input type="password" name="newPassword" id="psd1" class="input span6">
				</div>
			</div>
			
			<div class="control-group">	
				<label class="control-label">新密码再次输入</label>
				<div class="controls">
					<input type="password" id="psd2" name="newPassword2" class="input span6">
				</div>
			</div>
			
			<div class="form-actions">
				<input type="button" value=" 修  改  确  认 " onclick="changePassword()" class="btn blue">
			</div>
			</form>
			<#if !userLogin.accountInfo.lastChangePassword?exists>
			<div>
			<center>
				<img src="${base}/images/tishi.jpg" > </br>
				<b style="font-family:楷体">此次登陆为第一次登陆，请修改密码！</b>
			</center>
			</div>
			</#if>
		</div>
	</div>
	<!-- END PAGE CONTENT-->	
</div>
<!-- END PAGE CONTAINER--> 
</div>
<!-- END PAGE --> 	

<#include "/includes/footer.ftl">
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script type="text/javascript" src="${base}/metronic.bootstrap/media/js/chosen.jquery.min.js"></script>
<script type="text/javascript" src="${base}/metronic.bootstrap/media/js/select2.min.js"></script>
<script type="text/javascript" src="${base}/metronic.bootstrap/media/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="${base}/metronic.bootstrap/media/js/date.js"></script>
<script type="text/javascript" src="${base}/metronic.bootstrap/media/js/bootstrap-timepicker.js"></script>
<script type="text/javascript" src="${base}/metronic.bootstrap/media/js/jquery.inputmask.bundle.min.js"></script>   
<script type="text/javascript" src="${base}/metronic.bootstrap/media/js/jquery.input-ip-address-control-1.0.min.js"></script>
<script type="text/javascript" src="${base}/metronic.bootstrap/media/js/jquery.multi-select.js"></script>   
<!-- END PAGE LEVEL PLUGINS -->

<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${base}/metronic.bootstrap/media/js/app.js"></script>
<script src="${base}/metronic.bootstrap/media/js/form-components.js"></script>     
<!-- END PAGE LEVEL SCRIPTS -->

<!-- BEGIN JAVASCRIPTS -->   
<script>
function changePassword(){
    var psd1 = document.getElementById("psd1");
    var psd2 = document.getElementById("psd2");
    var oldPsd = document.getElementById("oldPsd");
    if (oldPsd.value == psd1.value){
        alert("新密码和旧密码一样!");
        psd1.focus();
        return;
    }

    if (psd1.value != psd2.value){
        alert("两次新密码输入不一致！");
        psd2.focus();
        return;
    }
    document.updatePasswordForm.submit();
}
</script>

<script>
	jQuery(document).ready(function() {       
	   // initiate layout and plugins
	   App.init();
	   FormComponents.init();
	});
</script>
<!-- END JAVASCRIPTS -->   
	
</body>
<!-- END BODY -->
</html>


