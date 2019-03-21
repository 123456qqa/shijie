<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ><head>
<title>注册和登录页面</title> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%-- ${pageContext.request.contextPath }相当于<%=request.getContextPath()%>   --%>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.11.3.js"></script>
<script type="text/javascript" >
$(function(){
	
	$('#switch_qlogin').click(function(){
		$('#switch_login').removeClass("switch_btn_focus").addClass('switch_btn');
		$('#switch_qlogin').removeClass("switch_btn").addClass('switch_btn_focus');
		$('#switch_bottom').animate({left:'0px',width:'70px'});
		$('#qlogin').css('display','none');
		$('#web_qr_login').css('display','block');
		
		});
	$('#switch_login').click(function(){
		
		$('#switch_login').removeClass("switch_btn").addClass('switch_btn_focus');
		$('#switch_qlogin').removeClass("switch_btn_focus").addClass('switch_btn');
		$('#switch_bottom').animate({left:'154px',width:'70px'});
		
		$('#qlogin').css('display','block');
		$('#web_qr_login').css('display','none');
		});
if(getParam("a")=='0')
{
	$('#switch_login').trigger('click');
}

});
	
function logintab(){
	scrollTo(0);
	$('#switch_qlogin').removeClass("switch_btn_focus").addClass('switch_btn');
	$('#switch_login').removeClass("switch_btn").addClass('switch_btn_focus');
	$('#switch_bottom').animate({left:'154px',width:'96px'});
	$('#qlogin').css('display','none');
	$('#web_qr_login').css('display','block');
	
}


//根据参数名获得该参数 pname等于想要的参数名 
function getParam(pname) { 
    var params = location.search.substr(1); // 获取参数 平且去掉？ 
    var ArrParam = params.split('&'); 
    if (ArrParam.length == 1) { 
        //只有一个参数的情况 
        return params.split('=')[1]; 
    } 
    else { 
         //多个参数参数的情况 
        for (var i = 0; i < ArrParam.length; i++) { 
            if (ArrParam[i].split('=')[0] == pname) { 
                return ArrParam[i].split('=')[1]; 
            } 
        } 
    } 
}  


var reMethod = "POST",
	pwdmin = 8;
	
	//验证手机号
	function checkMobilePhone(){
		console.log("checkMobilePhone") ;
		//验证手机号是否符合规则
		var reg = /^1[345789][0-9]{9}$/; //手机号规则
		var mobilePhone = document.getElementById("mobilePhone").value ;
		var userCueObj = document.getElementById("userCue") ;  
		if ( ! reg.test(mobilePhone) ) {
			userCueObj.innerHTML= "手机号不符合规则" ;
			userCueObj.style.color="red" ;
			return false ;
		}
		userCueObj.innerHTML= "手机号符合规则" ;
		userCueObj.style.color="green" ;
		return true ;
	}
	
	//验证密码
	function checkPassword() {
		var reg = /^\w{8,}$/ ;
		var password = document.getElementById("password").value ;
		var userCueObj = document.getElementById("userCue") ;
		if ( !reg.test(password) ) {
			userCueObj.innerHTML = "密码不符合规则" ;
			userCueObj.style.color = "red" ;
			return false ;
		}
		userCueObj.innerHTML = "密码符合规则" ;
		userCueObj.style.color = "green" ;
		return true ;
	}
	
	//验证两次密码是否一致
	function checkCfmPassword() {
		var password = document.getElementById("password").value ;
		var cfmPassword = document.getElementById("cfmPassword").value ;
		var userCueObj = document.getElementById("userCue") ;
		if ( password != cfmPassword ) {
			userCueObj.innerHTML = "两次输入的密码不一致" ;
			userCueObj.style.color = "red" ;
			return false ;
		}
		userCueObj.innerHTML = "两次输入的密码一致" ;
		userCueObj.style.color = "green" ;
		return true ;
	}
	

$(document).ready(function() {
	
	$('#reg').click(function() {
		//验证手机号、密码、确认密码
		//console.log("All:"+( checkMobilePhone() && checkPassword() && checkCfmPassword() ) ) ;
		if (  checkMobilePhone() && checkPassword() && checkCfmPassword() ) {
			$('#regUser').submit();
		}
		
	});


});
</script>
<link href="${pageContext.request.contextPath }/css/login2.css" rel="stylesheet" type="text/css" />
</head>
<body>
<h1>注册和登录页面<sup>V2019</sup></h1>

<div class="login" style="margin-top:50px;">
    
    <div class="header">
        <div class="switch" id="switch"><a class="switch_btn_focus" id="switch_qlogin" href="javascript:void(0);" tabindex="7">快速登录</a>
			<a class="switch_btn" id="switch_login" href="javascript:void(0);" tabindex="8">快速注册</a><div class="switch_bottom" id="switch_bottom" style="position: absolute; width: 64px; left: 0px;"></div>
        </div>
    </div>    
  
    
    <div class="web_qr_login" id="web_qr_login" style="display: block; height: 235px;">    

            <!--登录-->
            <div class="web_login" id="web_login">
               
               
               <div class="login-box">
    
            
			<div class="login_form">
				<form action="${pageContext.request.contextPath}/userInfoService" name="loginform" 
				accept-charset="utf-8" id="login_form" class="loginForm" method="post">
               <input type="hidden" name="op" value="login"/>
                <div class="uinArea" id="uinArea">
                <label class="input-tips" for="u">帐号：</label>
                <div class="inputOuter" id="uArea">
                    
                    <input type="text" id="accountNo" name="accountNo" class="inputstyle" placeholder="请输入手机号或邮箱或用户名"/>
                </div>
                </div>
                <div class="pwdArea" id="pwdArea">
               <label class="input-tips" for="p">密码：</label> 
               <div class="inputOuter" id="pArea">
                    
                    <input type="password" id="userPassLogin" name="password" class="inputstyle"/>
                </div>
                </div>
               
                <div style="padding-left:50px;margin-top:20px;"><input type="submit" value="登 录" style="width:150px;" class="button_blue"/></div>
              </form>
           </div>
           
            	</div>
               
            </div>
            <!--登录end-->
  </div>

  <!--注册-->
    <div class="qlogin" id="qlogin" style="display: none; ">
   
    <div class="web_login">
    <form name="form2" id="regUser" accept-charset="utf-8"  action="${pageContext.request.contextPath }/userInfoService" method="post">
	      <input type="hidden" name="op" value="register"/>
        <ul class="reg_form" id="reg-ul">
        		<div id="userCue" class="cue">快速注册请注意格式</div>
                <li>
                    <label for="user"  class="input-tips2">手机号：</label>
                    <div class="inputOuter2">
                        <input type="text" id="mobilePhone" name="mobilePhone" maxlength="16" class="inputstyle2" 
                        onblur="checkMobilePhone()"/>
                    </div>
               </li>
                <li>
                <label for="passwd" class="input-tips2">密码：</label>
                    <div class="inputOuter2">
                        <input type="password" id="password"  name="password" maxlength="16" class="inputstyle2"
                        onblur="checkPassword()"/>
                    </div>
                </li>
                <li>
                <label for="passwd2" class="input-tips2">确认密码：</label>
                    <div class="inputOuter2">
                        <input type="password" id="cfmPassword" name="" maxlength="16" class="inputstyle2" 
                        onblur="checkCfmPassword()"/>
                    </div>
                </li>
                 <li>
                    <div class="inputArea">
                        <input type="button" id="reg"  style="margin-top:10px;margin-left:85px;" class="button_blue" value="同意协议并注册"/>
                         <a href="#" class="zcxy" target="_blank">注册协议</a>
                    </div>
                 </li><div class="cl"></div>
            </ul></form>
           
    
    </div>
   
    
    </div>
    <!--注册end-->
</div>
<div class="jianyi">*推荐使用ie8或以上版本ie浏览器或Chrome内核浏览器访问本站</div>
</body></html>