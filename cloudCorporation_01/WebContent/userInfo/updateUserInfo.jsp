
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
	
  <title>完善用户信息</title>
	
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


     <div id="page-content" class="single-page">
        <div class="container">
             <div class="row">
               <!-- 头像上传 -->
                   <div class="col-md-4">
   					<form name="form1" id="ff1" method="post" action="${pageContext.request.contextPath }/fileUploadService"
   					 enctype="multipart/form-data" >
					
					    <input type="hidden" name="op" value="changeHeadImage">
						<div class="form-group">
						  <!-- 显示图片 -->
							<input type="image" class="form-control" placeholder="picture :"
							 name="picture" id="picture" src="${pageContext.request.contextPath }/headImage/${sessionScope.userInfo.headImage }" >
						</div>
						<div class="form-group">
							<input type="file" class="form-control" placeholder="头像文件 :"
							 name="headImage" id="headImage" value="${sessionScope.userInfo.headImage }">
						</div>
						<input type="hidden" name="userId" value="">
						<button type="submit" class="btn btn-1" name="upload" id="upload">上传头像</button>
					</form>
	                  
                   </div>
               <!-- 完善信息 -->
                   <div class="col-md-6">
                      <div class="heading"><h2>完善用户信息</h2></div>
                      <form name="form2" id="ff2" method="post" action="${pageContext.request.contextPath }/userInfoService"
                        onsubmit="return checkForm()" >
                          <div id="userCue" class="cue"></div>
                          <input type="hidden" name="op" value="updateUserInfo">
                          <div class="form-group">
                            <input type="text" class="form-control" placeholder="用户名"
                            <%--${sessionScope.userInfo.userName }   ((UserInfo)session.getAttribute("userInfo")).getUserName()      --%>
							 name="userName" id="userName" value="${sessionScope.userInfo.userName }"
							 onblur="checkUserName()">
							 <span id="userName_msg"></span>
                           </div>
                           <div class="form-group">
                               <input type="text" class="form-control" placeholder="邮箱"  onblur="checkEmail()"
                               name="email" id="email" value="${sessionScope.userInfo.email }">
                               <span id="email_msg"></span>
                           </div>
                            <div class="form-group">
                               <input type="text" class="form-control" placeholder="真实姓名" 
                               name="trueName" id="trueName" value="${sessionScope.userInfo.trueName }">
                           </div>  
                           <c:set value="${sessionScope.userInfo.gender }" var="gender" />
                           <c:choose>
                              <c:when test="${gender == 'male' }">
                               <div class="form-group">
                               <input type="radio" placeholder="性别" 
                               name="gender" id="gender1" value="male" checked="checked" >男
                               <input type="radio" placeholder="性别" 
                               name="gender" id="gender2" value="female">女
                              </div>   
                              </c:when>
                               <c:when test="${gender == 'female' }">
                               <div class="form-group">
                               <input type="radio" placeholder="性别" 
                               name="gender" id="gender1" value="male" >男
                               <input type="radio" placeholder="性别" 
                               name="gender" id="gender2" value="female" checked="checked" >女
                              </div>   
                              </c:when>                          
                              <c:otherwise>
                                 <div class="form-group">
                               <input type="radio" placeholder="性别" 
                               name="gender" id="gender1" value="male"  >男
                               <input type="radio" placeholder="性别" 
                               name="gender" id="gender2" value="female">女
                              </div>
                              </c:otherwise>
                          </c:choose>                    
                             <div class="form-group">
                               <input type="text" class="form-control" placeholder="简要说明" 
                               name="brief" id="brief" value="${sessionScope.userInfo.brief }">
                           </div>                 
                           <button type="submit" class="btn btn-1">完善信息</button>                                     
                      </form>
                   </div>
             </div>
        </div>
       
     </div>
     <script type="text/javascript">
        function checkUserName() {
        	var userName = $("#userName").val() ;
        	var result = true ;
        	$.ajax({
        		type:"GET",
        		async:false ,
        		url:"${pageContext.request.contextPath}/userInfoService?op=isExistsUserName&userName="+userName,
        		success:function(data){
        			if ( data == "true" ) {
        			
        				$("#userName_msg").html("<font color='red'>用户名已存在</font>") ;
        				result = false ;
        			} else {
        				
        				$("#userName_msg").html("<font color='green'>用户名可以使用</font>") ;
        				result = true ;
        			}
        		}
        	});
        	return result ;
        }
        
        function checkEmail() {
        	var email = $("#email").val() ;
        	console.log("1") ;
        	var  result = true ;
        	$.ajax({
        		type:"GET",
        		async:false,
        		url:"${pageContext.request.contextPath}/userInfoService?op=isExistsEmail&email="+email,
        	    success:function(data) {
        	    	if ( data == "true" ) {
        	    		
        	    		$("#email_msg").html("<font color='red'>邮箱名称已存在</font>") ;
        	    		console.log("2-1") ;
        	    		result = false ; 
        	    	} else {
        	    	
        	    		$("#email_msg").html("<font color='green'>邮箱名称可以使用</font>") ;
        	    		console.log("2-2") ;
        	    		result =  true ;
        	    	}
        	    }
        	}) ;
        	console.log("3") ;
        	return result  ;
        }
        
        function checkForm() {
        	console.log(checkEmail()) ;
        	return checkUserName() && checkEmail() ;
        	
        }
    
    </script> 
   
</body>
</html>
