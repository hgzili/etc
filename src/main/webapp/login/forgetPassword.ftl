<!DOCTYPE html>
<html lang="en" id="html">
	<head>
		<title>projectx | Login Page</title>
		<#include "/includes/head.ftl">
	    <link href="${base}/theme/global/plugins/select2/css/select2.min.css" rel="stylesheet" type="text/css" />
	    <link href="${base}/theme/global/plugins/select2/css/select2-bootstrap.min.css" rel="stylesheet" type="text/css" />
	    <link href="${base}/theme/pages/css/login-4.min.css" rel="stylesheet" type="text/css" />
	</head>
	
    <body class=" login" id="login" <#if loginPicture?has_content>style="background:url(../attachment/downloadPictureAttachment.action?pictureInfoId=${loginPicture.id?if_exists});background-repeat:no-repeat;background-size:100% 100%;"</#if>>
        <!-- BEGIN LOGO -->
        <div class="logo" style="margin-top:0px;">
            <a href="index.html">
                <img src="${base}/images/logo.png" alt=""  width="107px" height="auto"/> 
            </a>
        </div>
        <!-- END LOGO -->
        <!-- BEGIN LOGIN -->
        <div class="content">
            <!-- BEGIN LOGIN FORM -->
            <form class="login-form" name="forgetForm" action="forgetPasswordSendMail.action" method="post">            
				<h3 class="form-title">忘记密码 ?</h3>
				<p>请输入账号</p> 
                <#assign actionErrors = stack.findValue("actionErrors")?if_exists/>
				<#if actionErrors?has_content || fieldErrors?has_content>
				<div class="alert alert-error">
					<button class="close" data-dismiss="alert"></button>
					<span>
						<#list actionErrors as error>
						${action.getText(error)?if_exists?replace("\n", "<br/>")}</br>
						</#list>
			    	</span>
		    	</div>
	    		</#if>
	    		
	    		<div class="form-group">
	    			<label class="control-label visible-ie8 visible-ie9">账号</label>
                    <div class="input-icon">
                        <i class="fa fa-user"></i>
                        <input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="请输入账号" name="account" />
                     </div>
				</div>
				<div class="form-group">
                    <label class="control-label visible-ie8 visible-ie9">邮箱</label>
                    <div class="input-icon">
                        <i class="fa fa-lock"></i>
                        <input class="form-control placeholder-no-fix" type="email" autocomplete="off" placeholder="请输入邮箱地址" name="email" /> 
                    </div>
                </div>
			<div class="form-actions">
				<center>
				<button type="submit" class="btn green">
					提交 <i class="m-icon-swapright m-icon-white"></i>
				</button> 
				</center> 
				<p></P>
				<#if returnMsg?has_content&&returnMsg=="SUCCESS">
	    		<font color="green"><li>新密码已发送至您的邮箱，请注意查收。谢谢！</li></font>
	    		</#if>          
			</div>
			 
			<a href="viewLogin.action" class="pull-right"> 返回登陆页面</a>  
            </form>
        
            <!-- END LOGIN FORM -->
        </div>
        <!-- END LOGIN -->
        <!-- BEGIN COPYRIGHT -->
        <!--div class="copyright"> Copyright © 2013-2016 Cropland Consulting Corporation. All rights reserved. </div-->
        <!-- END COPYRIGHT -->
      
		<!-- BEGIN CORE PLUGINS -->
		<script src="${base}/theme/global/plugins/jquery.min.js" type="text/javascript"></script>
		<script src="${base}/theme/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
		<script src="${base}/theme/global/plugins/js.cookie.min.js" type="text/javascript"></script>
		<script src="${base}/theme/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
		<script src="${base}/theme/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
		<script src="${base}/theme/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
		<script src="${base}/theme/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
		<script src="${base}/theme/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
		<!-- END CORE PLUGINS -->
		
        <!-- BEGIN PAGE LEVEL PLUGINS -->
        <script src="${base}/theme/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
        <script src="${base}/theme/global/plugins/jquery-validation/js/additional-methods.min.js" type="text/javascript"></script>
        <script src="${base}/theme/global/plugins/select2/js/select2.full.min.js" type="text/javascript"></script>
        <script src="${base}/theme/global/plugins/backstretch/jquery.backstretch.min.js" type="text/javascript"></script>
        <!-- END PAGE LEVEL PLUGINS -->
        <!-- BEGIN THEME GLOBAL SCRIPTS -->
        <script src="${base}/theme/global/scripts/app.min.js" type="text/javascript"></script>
        <!-- END THEME GLOBAL SCRIPTS -->
        <!-- BEGIN PAGE LEVEL SCRIPTS -->
        <!-- script src="${base}/theme/pages/scripts/login-4.js" type="text/javascript"></script -->
        <!-- END PAGE LEVEL SCRIPTS -->
        <!-- BEGIN THEME LAYOUT SCRIPTS -->
        <!-- END THEME LAYOUT SCRIPTS -->
        <!-- BEGIN JAVASCRIPTS --> 
        <script>
		<#if loginPicture?has_content>
		var img = new Image();
		img.src="../attachment/downloadPictureAttachment.action?pictureInfoId=${loginPicture.id?if_exists}";
		img.onload=function(){	
			var div = document.getElementById("login");
			var html = document.getElementById("html");
			div.style.height= document.documentElement.clientHeight-60+"px";
			div.style.width=(document.documentElement.clientWidth)+"px"; 
			html.style.height= document.documentElement.clientHeight+"px";
			html.style.width=(document.documentElement.clientWidth)+"px";	
			
		};
		</#if>
		</script>
	<!-- END JAVASCRIPTS --> 
    </body>
    <!-- END BODY -->
</html>