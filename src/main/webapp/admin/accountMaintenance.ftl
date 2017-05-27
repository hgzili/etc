<!DOCTYPE html>
<html lang="en">
    <head>
        <title>project | template page</title>
        <#include "/includes/head.ftl">
        <link href="${base}/theme/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css" />
    </head>
    <!-- END HEAD -->

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
        			<div class="container-fluid">
						<div class="row-fluid">
							<div class="span12">
								<!-- BEGIN PAGE TITLE & BREADCRUMB-->
								<ul class="breadcrumb">
									<h4 class="text-center">账户维护</h4>
								</ul>
								<!-- END PAGE TITLE & BREADCRUMB-->
							</div>
						</div>
					</div>
					<!-- END PAGE HEADER-->
                    
                    <!-- BEGIN PAGE CONTENT-->
                    <div class="row">
                        <div class="col-md-12">
                            <div class="tab-content">
                                <div class="tab-pane active" id="tab_0">
                                    <div class="portlet box blue">
                                        <div class="portlet-title">
                                            <div class="caption">
                                                <i class="fa fa-gift"></i>账户维护</div>
                                        </div>                                   
                                        <div class="portlet-body form">                 
											<form class="form-horizontal" name="accountForm" action="createAccountInfo.action" method="post">
												<div class="form-body">
													<#if accountInfo?has_content>
													<input name="accountInfoId" type="hidden" value="${accountInfo.id}">
													</#if>
													<div class="form-group">
	                                                    <label class="col-md-3 control-label">账号</label>
	                                                    <div class="col-md-4">
	                                                        <input type="text" class="form-control" name="account" id="account" value="<#if accountInfo?has_content>${accountInfo.account?if_exists}<#else></#if>" required/>                                                    
	                                                    </div>
	                                                </div>
													
													<#if !accountInfo?has_content>
													<div class="form-group">
					                                    <label class="col-md-3 control-label">密码</label>
					                                    <div class="col-md-4">
					                                    	<input type="password" class="form-control" name="password" id="password" required/>
					                               		</div>
					                                </div>
													</#if>
													<div class="form-group">
					                                    <label class="col-md-3 control-label">邮箱</label>
					                                    <div class="col-md-4">
					                                    	<input type="mail" class="form-control" name="email" id="email" value="<#if accountInfo?has_content>${accountInfo.email?if_exists}</#if>"/>
					                                	</div>
					                                </div>
					                                
					                                <div class="form-group">
					                                    <label class="col-md-3 control-label">角色</label>
					                                    <div class="col-md-4">
						                                    <select class="form-control" name="role" onchange="resetProviderInfo();" data-placeholder="点击选择角色" tabindex="1" required>
						                                        <option value=""></option>
																	<option value="管理员" <#if accountInfo?exists && accountInfo.role=='管理员'>selected='selected'</#if> >管理员</option>
						                                    </select>
					                                    </div>
					                                </div>
					                                
					                                <div class="form-group">
					                                    <label class="col-md-3 control-label">生效日期</label>
					                                    <div class="col-md-4" data-date-format="yyyy-mm-dd" data-date-viewmode="years">
					                                        <input class="m-wrap m-ctrl-medium form_datetime form-control" name="startDate" size="16" type="text" value="<#if accountInfo?has_content><#if accountInfo.startDate?exists>${accountInfo.startDate?string("yyyy-MM-dd")}</#if><#else>${nowDate?if_exists}</#if>" />
					                                    </div>
					                                </div>
					                                
					                                <div class="form-group">
					                                    <label class="col-md-3 control-label">失效日期</label>
					                                    <div class="col-md-4" data-date-format="yyyy-mm-dd" data-date-viewmode="years">
					                                        <input class="m-wrap m-ctrl-medium form_datetime form-control" name="endDate" size="16" type="text" value="<#if accountInfo?has_content><#if accountInfo.endDate?exists>${accountInfo.endDate?string("yyyy-MM-dd")}</#if><#else>${nowDate?if_exists}</#if>" />
					                                    </div>
					                                </div>						
													<div class="form-actions right">
													 	<#if !accountInfo?has_content>
															<input type="submit" value="新增" class="btn blue"/>
														<#else>
															<input type="button" onclick="updateAccountInfo()" value="更新" class="btn blue"/>
														</#if>                                           
					                                </div>
					                               </div>								
											</form>
										</div>
										
										<div class="portlet box blue">
											<div class="portlet-title">
												<div class="caption"><i class="icon-globe"></i>查询结果</div>
											</div>
											<div class="portlet-body">
												<table class="table table-striped table-bordered table-hover table-full-width" id="sample_2">
													<thead>
														<tr>
															<th>账号</th> 
															<th>邮箱</th>
															<th>角色</th>
															<th>生效日期</th>
															<th>失效日期</th>
															<th>状态</th>
															<th>操作</th>
														</tr>
													</thead>
													<tbody>
														<#if accountInfoList?has_content>
														<#list accountInfoList as item>
														<tr class="b"> 
															<td>${item.account?if_exists}</td>
														  	<td>${item.email?if_exists}</td>
														  	<td>${item.role?if_exists}</td>
														  	<td><#if item.startDate?exists>${item.startDate?string("yyyy-MM-dd")}</#if></td>
														  	<td><#if item.endDate?exists>${item.endDate?string("yyyy-MM-dd")}</#if></td>
															<td>    
																<div class="col-md-9">
																	<input type="checkbox" class="make-switch" data-size="normal" <#if item.active>checked="checked"</#if> onchange="updateStatus(${item.id?if_exists},this.checked);"/>
																</div>
														  	</td>
														  	<td>
														  	  	<a href="accountMaintenance.action?accountInfoId=${item.id?if_exists}"  class="btn blue">修改</a>
														  	  	<button onclick="deleteAccountInfo(${item.id?if_exists});" class="btn">删除</button>
														  	</td> 
														</tr>
														</#list>
														</#if>
													</tbody>
												</table>
											</div>
										</div>										
									</div>				                  
			                    </div>
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
        <!--[if lt IE 9]>
		<script src="${base}/theme/global/plugins/respond.min.js"></script>
		<script src="${base}/theme/global/plugins/excanvas.min.js"></script> 
		<![endif]-->
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
        <script src="${base}/theme/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
        <!-- END THEME LAYOUT SCRIPTS -->
        <!-- BEGIN JAVASCRIPTS -->   
        <script>
        $(".form_datetime").datetimepicker({
	    	format: 	'yyyy-mm-dd',
	    	autoclose: 	true,
	    	startView: 	2,
	    	minView:	2,
	    	minuteStep:	2
    	});
		function updateStatus(id,active){
			if(active) updateActiveTrue(id);
			else updateActiveFalse(id); 
		}
		    
		function updateActiveTrue(accountInfoId){
			location.href="updateAccountInfoActive.action?active=true&accountInfoId="+accountInfoId;
		}
		function updateActiveFalse(accountInfoId){
			location.href="updateAccountInfoActive.action?active=false&accountInfoId="+accountInfoId;
		}
		
		function updateAccountInfo(){
			accountForm.action="updateAccountInfo.action";
			accountForm.submit();
		}
		
		function deleteAccountInfo(accountInfoId){
			 if(confirm("确定要删除该账号吗？")){
			 	location.href="deleteAccountInfo.action?accountInfoId="+accountInfoId;
			 }
		}
        </script>    
		<!-- END JAVASCRIPTS --> 
    </body>
    <!-- END BODY -->
</html>