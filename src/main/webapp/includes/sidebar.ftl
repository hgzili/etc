            <#import "/includes/macros.ftl" as m>
			<#assign path = request.servletPath>
            <div class="page-sidebar-wrapper">
                <div class="page-sidebar navbar-collapse collapse">
                    <ul class="page-sidebar-menu  page-header-fixed " data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200" style="padding-top: 20px">
                        <!--li class="sidebar-toggler-wrapper hide">
                            <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
                            <!--div class="sidebar-toggler"> </div>
                            <!-- END SIDEBAR TOGGLER BUTTON -->
                        <!--/li>
                        <!-- DOC: To remove the search box from the sidebar you just need to completely remove the below "sidebar-search-wrapper" LI element -->
                        <!--li class="sidebar-search-wrapper">
                            <form class="sidebar-search  " action="page_general_search_3.html" method="POST">
                                <a href="javascript:;" class="remove">
                                    <i class="icon-close"></i>
                                </a>
                                <div class="input-group">
                                    <input type="text" class="form-control" placeholder="Search...">
                                    <span class="input-group-btn">
                                        <a href="javascript:;" class="btn submit">
                                            <i class="icon-magnifier"></i>
                                        </a>
                                    </span>
                                </div>
                            </form>
                            <!-- END RESPONSIVE QUICK SEARCH FORM -->
                        <!--/li-->
                        <li class="nav-item start <#if path?starts_with("/index.action")>active</#if>">
                            <a href="${base}/index.action" class="nav-link ">
                                <i class="icon-home"></i>
                                <span class="title">主页</span>
                            </a>
                        </li>
                        <li class="nav-item start <#if path?starts_with("/admin/")>open active</#if>">
                            <a href="javascript:;" class="nav-link nav-toggle">
                                <i class="icon-home"></i>
                                <span class="title">设置</span>
                                <span class="arrow"></span>
                            </a>
                            <ul class="sub-menu">
                                <li class="nav-item start <#if path?starts_with("/admin/accountMaintenance.action")>active</#if>">
                                    <a href="${base}/admin/accountMaintenance.action" class="nav-link ">
                                        <i class="icon-bar-chart"></i>
                                        <span class="title">账号管理</span>
                                    </a>
                                </li>
                                <li class="nav-item start  <#if path?starts_with("/admin/pictureMaintenance.action")>active</#if>">
                                    <a href="${base}/admin/pictureMaintenance.action" class="nav-link ">
                                        <i class="icon-bulb"></i>
                                        <span class="title">皮肤管理</span>
                                    </a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                    <!-- END SIDEBAR MENU -->
                    <!-- END SIDEBAR MENU -->
                </div>
                <!-- END SIDEBAR -->

            </div>
