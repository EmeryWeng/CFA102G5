<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.roomImg.model.*"%>
<%
    List<RoomImgVO> list = (List<RoomImgVO>) request.getAttribute("list");
    pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>房型圖片</title>
</head>
<body>
	<h3>這是房型的所有圖片</h3>
	<table>
		<tr>
			<th>圖片編號</th>
			<th>房型編號</th>
			<th>圖片</th>
			<th>刪除</th>
		</tr>
		<c:forEach var="roomImgVO" items="${list}">
			<tr>
				<td>${roomImgVO.img_no}</td>
				<td>${roomImgVO.type_no}</td>
				<td><img src="<%=request.getContextPath()%>/roomType/RoomImg?img_no=${roomImgVO.img_no}" width="240" height="300" /></td>		
				<td>
					<form method="post" action="<%=request.getContextPath()%>/room/RoomImg">
						<input type="hidden" name="img_no" value="${roomImgVO.img_no}">
						<input type="hidden" name="action" value="deleteRoomImg">
					</form> <input type="submit" value="刪除">
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>