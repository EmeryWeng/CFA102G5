<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@ page import="com.roomType.model.*"%>
<%
  RoomTypeVO roomTypeVO = (RoomTypeVO) request.getAttribute("roomTypeVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>
	<h1>新增房型</h1>
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color:red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color:red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<h4><a href="<%=request.getContextPath()%>/back_end/roomType/roomTypeHome.jsp">回首頁</a></h4>
	
	<jsp:useBean id="roomTypeService" class="com.roomType.model.RoomTypeService"/>

	<form method="post" action="<%=request.getContextPath()%>/room/RoomType" name="addRoomType">
		<table>
			<tr>
				<td>房型名稱</td>
				<td><input type="TEXT" name="type_name" size="10" value="<%=(roomTypeVO == null) ? "" : roomTypeVO.getType_name()%>"></td>
			</tr>
			<tr>
				<td>容納人數</td>
				<td><input type="TEXT" name="type_qty" value="<%=(roomTypeVO == null) ? "" : roomTypeVO.getType_qty()%>"></td>
			</tr>
			<tr>
				<td>金額</td>
				<td><input type="TEXT" name="type_price" value="<%=(roomTypeVO == null) ? "" : roomTypeVO.getType_price()%>"></td>
			</tr>
			<tr>
				<td>房型大小</td>
				<td><input type="TEXT" name="type_size" value="<%=(roomTypeVO == null) ? "" : roomTypeVO.getType_size()%>"></td>
			</tr>
			<tr>
				<td>床型</td>
				<td><input type="TEXT" name="bed_size" value="<%=(roomTypeVO == null) ? "" : roomTypeVO.getBed_size()%>"></td>
			</tr>
			<tr>
				<td>房型資訊</td>
				<td><input type="TEXT" name="type_info" value="<%=(roomTypeVO == null) ? "" : roomTypeVO.getType_info()%>"></td>
			</tr>
			<tr>
				<td>房型設施</td>
				<td><input type="TEXT" name="type_facility" value="<%=(roomTypeVO == null) ? "" : roomTypeVO.getType_facility()%>"></td>
			</tr>
		</table>
		<br> 
		<input type="hidden" name="action" value="insert"> <input type="submit" value="新增">
	</form>

</body>
</html>