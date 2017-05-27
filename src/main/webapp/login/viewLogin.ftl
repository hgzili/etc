<!DOCTYPE html>
<html lang="en" id="html">
	<head>
		<title>projectx | Login Page</title>
		<#include "/includes/head.ftl">
		
		<!-- BEGIN PAGE LEVEL PLUGINS -->
	    <link href="${base}/theme/global/plugins/select2/css/select2.min.css" rel="stylesheet" type="text/css" />
	    <link href="${base}/theme/global/plugins/select2/css/select2-bootstrap.min.css" rel="stylesheet" type="text/css" />
	    <!-- END PAGE LEVEL PLUGINS -->
	    
	    <!-- BEGIN PAGE LEVEL STYLES -->
	    <link href="${base}/theme/pages/css/login-4.min.css" rel="stylesheet" type="text/css" />
	    <!-- END PAGE LEVEL STYLES -->
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
            <form class="login-form" action="login.action" method="post">
               <center> <h3 class="form-title">账号登录&nbsp;<span>或</span>&nbsp;
                    <a href="javascript:" id="link-wechat-signin" style="text-decoration:none;color:black" >微信登录</a></h3></center>
                <div class="alert alert-danger display-hide">
                    <button class="close" data-close="alert"></button>
                    <span>请输入账号和密码！</span>
                </div>
                
                <#assign actionErrors = stack.findValue("actionErrors")?if_exists/>
				<#if actionErrors?has_content || fieldErrors?has_content>
				<div class="alert alert-error">
					<button class="close" data-dismiss="alert"></button>
					<span>
						${actionErrors[0]}
			    	</span>
		    	</div>
		    	</#if>
                <div class="form-group">
                    <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
                    <label class="control-label visible-ie8 visible-ie9">账号</label>
                    <div class="input-icon">
                        <i class="fa fa-user"></i>
                        <input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="Username" name="account" /> </div>
                </div>
                <div class="form-group">
                    <label class="control-label visible-ie8 visible-ie9">密码</label>
                    <div class="input-icon">
                        <i class="fa fa-lock"></i>
                        <input class="form-control placeholder-no-fix" type="password" autocomplete="off" placeholder="Password" name="password" /> </div>
                </div>
                <div class="form-actions">
                <input type="hidden" name="errmsg" value="${errmsg?if_exists}" id="errmsg">
                <p>
                    <label class="checkbox">
                        <a href="forgetPassword.action" width="50px" style="color:blue;">忘记密码</a></label>
                    <button type="submit" class="btn green pull-right">登陆</button>
                 </p>
                   
                </div>
                
            </form>
            <div name="wxloginDiv" class="form-vertical weixin-form" action="login.action" method="post">
		        <div >
		        <center>
		        	<h3 class="form-title">
					<a href="javascript:;" id="link-email-signin" style="text-decoration:none;color:black">账号登录 </a><span style="">或</span>&nbsp;微信登录</h3>
                 </center>  
                
                </div>		
				<div id="wechat-login"></div>
			</div>
            <!-- END LOGIN FORM -->
        </div>
        <!-- END LOGIN -->
        <!-- BEGIN COPYRIGHT -->
        <!--div class="copyright"> Copyright © 2013-2016 Cropland Consulting Corporation. All rights reserved. </div-->
        <!-- END COPYRIGHT -->
      
		<!-- BEGIN CORE PLUGINS -->
		<script type="text/javascript" src="//res.wx.qq.com/connect/zh_CN/htmledition/js/wxLogin.js"></script>
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
        <script>
		(function() {
	    new WxLogin({
	    	id: 'wechat-login',
	    	appid: '${weixinLoginAppid?if_exists}',
	    	scope: 'snsapi_login',
	    	redirect_uri: '${weixinLoginRedirectUrl?if_exists}',
	    	href: '${weixinLoginCssFile?if_exists}',
	    	state: '${weixinLoginState?if_exists}'
	    });
	    $('iframe').height(370);
		})();
	

		jQuery(document).ready(function() { 
			jQuery('.login-form').show();
            jQuery('.weixin-form').hide();
		});
		if($('#errmsg').val()){
    		jQuery('.login-form').hide();
            jQuery('.weixin-form').show();
    	}
	    jQuery('#link-wechat-signin').click(function () {
            jQuery('.login-form').hide();
            jQuery('.weixin-form').show();
        });
        jQuery('#link-email-signin').click(function () {
            jQuery('.login-form').show();
            jQuery('.weixin-form').hide();
        });
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
    </body>

</html>