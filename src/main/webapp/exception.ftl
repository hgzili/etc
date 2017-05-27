<!DOCTYPE html>
<html lang="en">
	<head>
        <title>project | template page</title>
        <#include "/includes/head.ftl">
        <link href="${base}/theme/pages/css/error.min.css" rel="stylesheet" type="text/css" />
    </head>
	<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white">
		<!-- BEGIN HEADER -->
        <#include "/includes/header.ftl">
        <!-- END HEADER -->
        <!-- BEGIN CONTAINER -->
        <div class="clearfix"> </div>
        <!-- END HEADER & CONTENT DIVIDER -->
        <!-- BEGIN CONTAINER -->
        <div class="page-container">
            <!-- BEGIN SIDEBAR -->
            <#include "/includes/sidebar.ftl">
            <!-- END SIDEBAR -->
            <!-- BEGIN CONTENT -->
            <div class="page-content-wrapper">
                <!-- BEGIN CONTENT BODY -->
                <div class="page-content">
	                <#include "/includes/actionerrors.ftl">
                	<!-- BEGIN PAGE HEADER-->
                	<!-- BEGIN PAGE HEADER-->
                	<!-- BEGIN PAGE CONTENT-->  
                    
                    <!-- END PAGE TITLE-->
                    <!-- END PAGE HEADER-->
                    <div class="row">
                        <div class="col-md-12 page-500">
                            <div class=" number font-red"> 500 </div>
                            <div class=" details">
                                <h3>哎呀！系统奔溃了。</h3>
                                <p> 我们正在修理它！请在稍后再来。。
                                    <br/> </p>
                                <p>
                                    <a href="index.action" class="btn red btn-outline"> 返回首页 </a>
                                    <br> </p>
                            </div>
                        </div>
	                </div>
	             <!-- END PAGE CONTENT-->
	            </div>
	            <!-- END CONTENT BODY -->
	        </div>
	        <!-- END CONTENT -->
	        <!-- BEGIN QUICK SIDEBAR -->
	        <!-- END QUICK SIDEBAR -->
	    </div>
	    <!-- END CONTAINER -->
	    <!-- BEGIN FOOTER -->
	    <#include "/includes/footer.ftl">
	    <!-- END FOOTER -->
	    <script src="${base}/theme/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
	    <script src="${base}/theme/global/plugins/js.cookie.min.js" type="text/javascript"></script>
	    <script src="${base}/theme/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
	    <script src="${base}/theme/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
	    <script src="${base}/theme/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
	    <script src="${base}/theme/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
	    <script src="${base}/theme/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
	    <!-- END CORE PLUGINS -->
	    <!-- BEGIN THEME GLOBAL SCRIPTS -->
	    <script src="${base}/theme/global/scripts/app.min.js" type="text/javascript"></script>
	    <!-- END THEME GLOBAL SCRIPTS -->
	    <!-- BEGIN THEME LAYOUT SCRIPTS -->
	    <script src="${base}/theme/layouts/layout/scripts/layout.min.js" type="text/javascript"></script>
	    <script src="${base}/theme/layouts/layout/scripts/demo.min.js" type="text/javascript"></script>
	    <script src="${base}/theme/layouts/global/scripts/quick-sidebar.min.js" type="text/javascript"></script>
	    <!-- END THEME LAYOUT SCRIPTS -->
	</body>
	<!-- END BODY -->
</html>