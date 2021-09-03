<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>房型圖片 Home</title>
<style>
input[type="submit"] {
	background-color: #E4D6C4;
}
</style>
</head>
<body>
	<h1>房型圖片 Home</h1>

	<jsp:useBean id="roomTypeSvc" class="com.roomType.model.RoomTypeService" />
	
	<h3>查看房型圖片</h3>
	<form method="post" action="<%=request.getContextPath()%>/room/RoomImg">
		<select name="type_no">
			<c:forEach var="roomTypeVO" items="${roomTypeSvc.allRoomType}">
				<option value="${roomTypeVO.type_no}">${roomTypeVO.type_name}
			</c:forEach>
		</select>
		<br>
		<br>
		<input type="hidden" name="action" value="getAllByType">
		<input type="submit" value="查看圖片">
	</form>
	
	<br><hr>
	
	<h3>新增房型圖片</h3>
	<form method="post" action="<%=request.getContextPath()%>/room/RoomImg" enctype="multipart/form-data">
		<select name="roomType">
			<c:forEach var="roomTypeVO" items="${roomTypeSvc.allRoomType}">
				<option value="${roomTypeVO.type_no}">${roomTypeVO.type_name}
			</c:forEach>
		</select>
		<input type="file" name="roomImg"><br><br><br> 
		<input type="hidden" name="action" value="addRoomImg">
		<input type="submit" value="新增圖片">
	</form>


</body>
</html>