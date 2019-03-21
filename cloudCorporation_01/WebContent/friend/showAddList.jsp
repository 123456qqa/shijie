<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html >
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta name="description" content="">
    <meta name="author" content="">
	
    <title>显示可以加好友的列表</title>
	
    <!-- Bootstrap Core CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css"  type="text/css">
	
	<!-- Custom CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/style.css">
	
	
	<!-- Custom Fonts -->
    <link rel="stylesheet" href="${pageContext.request.contextPath }/font-awesome/css/font-awesome.min.css"  type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/fonts/font-slider.css" type="text/css">
	
	<!-- jQuery and Modernizr-->
	<script src="${pageContext.request.contextPath }/js/jquery-1.11.3.js"></script>
	
	<!-- Core JavaScript Files -->  	 
    <script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
	
	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="${pageContext.request.contextPath }/js/html5shiv.js"></script>
        <script src="${pageContext.request.contextPath }/js/respond.min.js"></script>
    <![endif]-->
</head>

<body>
 
	<!--Navigation-->
    <jsp:include page="../top.jsp" />

   <!-- 显示创建的云协作室列表 -->
    
    <h3>可以加好友的列表</h3>
	<div role="grid" id="editabledatatable_wrapper"
		class="dataTables_wrapper form-inline no-footer">
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th>手机号</th>
					<th>用户姓名</th>
					<th>邮箱</th>
					<th>性别</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
               <c:set value="${requestScope.toAddUserList }" var="toAddUserList" />
               <c:forEach items="${toAddUserList }" var="userInfo">
                   <tr>
                      <td>${userInfo.mobilePhone }</td>
                      <td>${userInfo.trueName }</td>
                      <td>${userInfo.email }</td>
                      <td>${userInfo.gender }</td>
                      <td><a href="#">加为好友</a></td>
                   </tr>
               </c:forEach>

			</tbody>
		</table>
		<div class="row DTTTFooter margin-top-10">
			<div class="col-sm-6">
				<div class="dataTables_paginate paging_bootstrap pull-right"
					id="editabledatatable_paginate">
					<div class="tcdPageCode"></div>
				</div>
			</div>
		</div>
	</div>
   
  
   
</body>
</html>
