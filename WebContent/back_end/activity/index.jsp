<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Feliz後台頁面</title>

</head>
<body>
	<form method="post" action="<%=request.getContextPath()%>/activity/ActivityClass">
		<input type="hidden" name="action" value="forwardAct">
		<input type="submit" value="活動後台頁面">
	</form>
</body>
</html>