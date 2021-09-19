<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<html lang="en">
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Login</title>
	<link rel="stylesheet" href="Login2.css">
	<link href='https://unpkg.com/boxicons@2.0.8/css/boxicons.min.css' rel='stylesheet'>

	<%@ include file="/back_end/commonCSS.file" %> <!-- 基本CSS檔案 -->

</head>
<style>
	@import url('https://fonts.googleapis.com/css2?family=Comfortaa:wght@500&display=swap');
*
{
	margin: 0;
	padding: 0;
	font-family: 'Comfortaa', cursive;
	box-sizing: border-box;
}
body{
	background: #1111;
}
.Feliz{
	max-width: 440px;
	padding: 0 20px;
	margin: 170px auto;
}
.wrapper{
	width: 100%;
	background: #fff;
	border-radius: 5px;
	
}
.wrapper .title{
	line-height: 90px;
	background: #CDBCA9;
	text-align: center;
	border-radius: 5px 5px 0 0;
	font-size: 30px;
	color: #fff;
	font-weight: 600;
}
.wrapper form{
	padding: 30px 25px 25px 25px;
}
.wrapper form .row{
	position: relative;
	height: 45px;
	margin-bottom: 15px;
}
.wrapper form .row input{
	height: 100%;
	width: 100%;
	outline: none;
	padding-left: 60px;
	border-radius: 5px;
	border: 1px solid lightgray;
	font-size: 16px;
}
.wrapper form .row i{
	position: absolute;
	width: 47px;
	height: 100%;
	color: #fff;
	font-size: 18px;
	background: #CDBCA9;
	border: 1px solid #CDBCA9;
	border-radius: 5px 0 0 5px;
	display: flex;
	align-items: center;
	justify-content: center;
}
.wrapper form .pass{
	margin: -8px 0 20px 0;
}
.wrapper form .pass a{
	font-size: 17px;
	color: #CDBCA9;
	text-decoration: none;
}
.wrapper form .pass a:hover{
	text-decoration: underline;
}
.wrapper form .button input{
	color: #fff;
	font-size: 20px;
	padding-left: 0px;
	background: #CDBCA9;
	border: 1px solid #CDBCA9;
	cursor: pointer;
}
</style>
<body>
	<div class="Feliz">
		<div class="wrapper">
		<div class="title"><span>Login Form</span></div>
		<form action="<%=request.getContextPath()%>/EmployeeServlet.do" method="post">
			<div class="row">
				<i class='bx bx-mail-send'></i>
				<input type="text" name="emp_mail" placeholder="Email" required>
			</div>
			<div class="row">
				<i class='bx bxs-key'></i>
				<input type="password" name="emp_password" placeholder="Password" required>
			</div>
			<div class="pass"><a href="">Forgot password?</a></div>
			<div class="row button">
				<input type="submit" name="action" value="Login">
			</div>
		</form>
	</div>
	<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
	
	</div>
</body>
</html>