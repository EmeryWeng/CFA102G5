<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>活動:Home(MVC)</title>
</head>
<body>
	<h1>資料查詢</h1>
	<ul>
		<li>
			<form method="post" action="<%=request.getContextPath()%>/activity/ActivityImage">
				<input type="hidden" name="action" value="getAllImg">
				<input type="submit" value="查詢圖片">
			</form>
		</li>
	</ul>
	
	<h1>新增圖片</h1>
	<ul>
		<li>
			<form method="post" action="<%=request.getContextPath()%>/front_end/activity/actImg/addActImg.jsp">
				<input type="submit" value="新增圖片">
			</form>
		</li>
	</ul>
</body>
</html>