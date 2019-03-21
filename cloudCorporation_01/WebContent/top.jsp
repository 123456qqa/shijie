<%@ page language="java" pageEncoding="UTF-8"%>

	<!--Navigation-->
    <nav id="menu" class="navbar">
		<div class="container">
			<div class="navbar-header"><span id="heading" class="visible-xs">云协作系统</span>
			  <button type="button" class="btn btn-navbar navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse"><i class="fa fa-bars"></i></button>
			</div>
			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<ul class="nav navbar-nav">
					<li><a href="${pageContext.request.contextPath }/corporationRoomService?op=showMain">主页</a></li>
					<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">用户管理</a>
						<div class="dropdown-menu">
							<div class="dropdown-inner">
								<ul class="list-unstyled">
									<li><a href="${pageContext.request.contextPath }/userInfo/updateUserInfo.jsp">完善用户信息</a></li>
									<li><a href="">修改密码</a></li>
									<li><a href="">退出登录</a></li>
								</ul>
							</div>
						</div>
					</li>
					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">好友管理</a>
						<div class="dropdown-menu">
							<div class="dropdown-inner">
								<ul class="list-unstyled">
									<li><a href="">新增好友</a></li>
									<li><a href="">确认好友</a></li>
								    <li><a href="">显示好友</a></li>
								</ul>
							</div>
						</div>
					</li>					
					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">云协作室 管理</a>
						<div class="dropdown-menu">
							<div class="dropdown-inner">
								<ul class="list-unstyled">
									<li><a href="$">创建云协作室</a></li>
								</ul>
							</div>
						</div>	
					</li>
					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">协作内容</a>
					</li>
	
				</ul>
			</div>
		</div>
	</nav>

