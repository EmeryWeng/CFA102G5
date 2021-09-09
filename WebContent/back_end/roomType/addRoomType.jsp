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
	<h1>�s�W�Ы�</h1>
	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color:red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color:red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<h4><a href="<%=request.getContextPath()%>/back_end/roomType/roomTypeHome.jsp">�^����</a></h4>
	
	<jsp:useBean id="roomTypeService" class="com.roomType.model.RoomTypeService"/>

	<form method="post" action="<%=request.getContextPath()%>/room/RoomType" name="addRoomType">
		<table>
			<tr>
				<td>�Ы��W��</td>
				<td><input type="TEXT" name="type_name" size="10" value="<%=(roomTypeVO == null) ? "" : roomTypeVO.getType_name()%>"></td>
			</tr>
			<tr>
				<td>�e�ǤH��</td>
				<td><input type="TEXT" name="type_qty" value="<%=(roomTypeVO == null) ? "" : roomTypeVO.getType_qty()%>"></td>
			</tr>
			<tr>
				<td>���B</td>
				<td><input type="TEXT" name="type_price" value="<%=(roomTypeVO == null) ? "" : roomTypeVO.getType_price()%>"></td>
			</tr>
			<tr>
				<td>�Ы��j�p</td>
				<td><input type="TEXT" name="type_size" value="<%=(roomTypeVO == null) ? "" : roomTypeVO.getType_size()%>"></td>
			</tr>
			<tr>
				<td>�ɫ�</td>
				<td><input type="TEXT" name="bed_size" value="<%=(roomTypeVO == null) ? "" : roomTypeVO.getBed_size()%>"></td>
			</tr>
			<tr>
				<td>�Ы���T</td>
				<td><input type="TEXT" name="type_info" value="<%=(roomTypeVO == null) ? "" : roomTypeVO.getType_info()%>"></td>
			</tr>
			<tr>
				<td>�Ы��]�I</td>
				<td><input type="TEXT" name="type_facility" value="<%=(roomTypeVO == null) ? "" : roomTypeVO.getType_facility()%>"></td>
			</tr>
		</table>
		<br> 
		<input type="hidden" name="action" value="insert"> <input type="submit" value="�s�W">
	</form>

</body>
</html>