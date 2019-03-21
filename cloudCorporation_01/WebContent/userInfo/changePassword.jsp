<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html >
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta name="description" content="">
    <meta name="author" content="">
	
    <title>修改密码</title>
	
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
    <jsp:include page="../top.jsp"/>


     <div id="page-content" class="single-page">
        <div class="container">
             <div class="row">
                    
               <!-- 修改密码 -->
                   <div class="col-md-6">
                      <div class="heading"><h2> 修改密码 </h2></div>
                      <form name="form2" id="ff2" method="post" action="${pageContext.request.contextPath }/userInfoService">
                          <div id="userCue" class="cue"></div>
                          <input type="hidden" name="op" value="changePassword">
                          <div class="form-group">
                            <input type="password" class="form-control" placeholder="原密码"
							 name="oldPassword" id="oldPassword" >
							 <span id="userName_msg"></span>
                           </div>
                           <div class="form-group">
                               <input type="password" class="form-control" placeholder="新密码" 
                               name="newPassword" id="newPassword" >
                           </div>
                            <div class="form-group">
                               <input type="password" class="form-control" placeholder="确认密码" 
                               name="cfmPassword" id="cfmPassword" >
                           </div>  
                          
                           <button type="submit" class="btn btn-1">修改密码</button>                                     
                      </form>
                   </div>
             </div>
        </div>
       
     </div>
   
   
</body>
</html>
