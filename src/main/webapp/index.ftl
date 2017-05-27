<!DOCTYPE html>
<html lang="en">
	<!-- BEGIN HEAD -->
	<head>
        <title>project | template page</title>
        <#include "/includes/head.ftl">
    </head>
    <!-- END HEAD -->
	<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white">
		<!-- BEGIN HEADER -->
		<#include "/includes/header.ftl">
		<!-- END HEADER -->
		<!-- BEGIN HEADER & CONTENT DIVIDER -->
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
					<div class="container-fluid">
					<center><img src="${base}/images/homepage.jpg" width="600px"/></center>
					
					</div>
				</div>
				<!-- END CONTENT BODY -->
			</div>
			<!-- END CONTENT -->
		</div>
		<!-- END CONTAINER -->
        <!-- BEGIN FOOTER -->
        <#include "/includes/footer.ftl">
        <!-- END FOOTER -->
 		<!-- BEGIN CORE PLUGINS -->
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
		<!-- BEGIN JAVASCRIPTS -->   
		<script>
			jQuery(document).ready(function() {       
			   // initiate layout and plugins
			   App.init();
			});
		</script>
		<!-- END JAVASCRIPTS -->  
	</body>
	<!-- END BODY -->
</html>