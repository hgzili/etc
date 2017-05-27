<#import "/decorators/includes/macros.ftl" as m>
<#assign path = request.servletPath>
<!-- BEGIN SIDEBAR -->
<div class="page-sidebar nav-collapse collapse">
	<!-- BEGIN SIDEBAR MENU -->        
	<ul class="page-sidebar-menu">
		<li <#if path?starts_with("/index.action")>class="active"</#if>>
			<a href="${base}/index.action">
			<!--i class="icon-home"></i--> 
			<span class="title">主页</span>
			<#if path?starts_with("/index.action")><span class="selected"></span></#if>
			</a>
		</li>
		<li <#if path?starts_with("/admin/")>class="active"</#if>>
			<a href="javascript:;">
			<!--i class="icon-cogs"></i--> 
			<span class="title">设置</span>
			<#if path?starts_with("/admin/")><span class="selected"></span></#if>
			<span class="arrow <#if path?starts_with("/admin/")>open</#if>"></span>
			</a>
			<ul class="sub-menu">
				<li <#if path?starts_with("/admin/accountMaintenance.action")>class="active"</#if>>
					<a href="${base}/admin/accountMaintenance.action">账号管理</a>
				</li>
				<li <#if path?starts_with("/admin/pictureMaintenance.action")>class="active"</#if>>
					<a href="${base}/admin/pictureMaintenance.action">皮肤管理</a>
				</li>
			</ul>
		</li>	
	</ul>
	<!-- END SIDEBAR MENU -->
</div>
<!-- END SIDEBAR -->