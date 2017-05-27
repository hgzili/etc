<style>
.menuDiv{
	position:fixed; 
	width:100%;
	left:0px; 
	bottom:0px; 
	_position:absolute;
	margin-bottom:0px !important;
}

.btn-group-customize{
	width:25%;
	margin-left:0px !important;
	margin-bottom:0px !important;
}

.btn-customize{
	width:100%;
}

.btn-menu {
	margin-left: 0px; 
}
.btn-menu:before,
.btn-menu:after {
    content: " ";
    display: table; 
}
.btn-menu:after {
    clear: both; 
}
.btn-menu .btn,
.btn-menu .btn-group,
.btn-menu .input-group {
    float: left; 
}
.btn-menu > .btn,
.btn-menu > .btn-group,
.btn-menu > .input-group {
    margin-left: 0px; 
}
</style>
</br></br>
<div class="menuDiv btn-menu">
	<div class="btn-group btn-group-customize">
		<button class="btn btn-customize" data-toggle="dropdown">Menu10</button>
		<ul class="dropdown-menu bottom-up">
			<li><button class="btn btn-customize">Menu11</button></li>
			<li><button class="btn btn-customize">Menu12</button></li>
			<li><button class="btn btn-customize">Menu13</button></li>
		</ul>
	</div>
	<div class="btn-group btn-group-customize">
		<button class="btn red btn-customize" data-toggle="dropdown">Menu20</button>
		<ul class="dropdown-menu bottom-up">
			<li><button class="btn red btn-customize">Menu21</button></li>
			<li><button class="btn red btn-customize">Menu22</button></li>
			<li><button class="btn red btn-customize">Menu23</button></li>
		</ul>
	</div>
	<div class="btn-group btn-group-customize">
		<button class="btn green btn-customize" data-toggle="dropdown">Menu30</button>
		<ul class="dropdown-menu bottom-up pull-right">
			<li><button class="btn green btn-customize">Menu31</button></li>
			<li><button class="btn green btn-customize">Menu32</button></li>
			<li><button class="btn green btn-customize">Menu33</button></li>
		</ul>
	</div>
	<div class="btn-group btn-group-customize">
		<button class="btn blue btn-customize" data-toggle="dropdown">Menu40</button>
		<ul class="dropdown-menu bottom-up pull-right">
			<li><button class="btn blue btn-customize">Menu41</button></li>
			<li><button class="btn blue btn-customize">Menu42</button></li>
			<li><button class="btn blue btn-customize">Menu43</button></li>
		</ul>
	</div>
</div>
<!--div class="btn-group  menuDiv" style="width:100%">
	<button href="javascript:;" style="width:25%" class="btn">Tools</button>
	<button href="javascript:;" style="width:25%" class="btn red">Settings</button>
	<button href="javascript:;" style="width:25%" class="btn purple">About</button>
	<button href="javascript:;" style="width:25%" class="btn blue">Help</button>
</div-->

<script>
	function initialMenu(){
		var screenWidth = $(window).width();//获取屏幕可视区域的宽度。
		$(".menuDiv").width(screenWidth);//将宽度赋值给menuDiv使其可以贯穿整个屏幕。
		var screenHeight = $(window).height();//获取屏幕可视区域的高度。
		var divHeight = $(".menuDiv").height() + 1;//menuDiv的高度再加上它一像素的边框。
		
		$(window).scroll(function(){
			var scrollHeight = $(document).scrollTop();//获取滚动条滚动的高度。
			if(!window.XMLHttpRequest){
				$(".menuDiv").css("top",screenHeight + scrollHeight - divHeight);	
			}//判断是否为IE6，如果是，执行大括号中内容
		})
	}
</script>
<!-- END CORE PLUGINS -->