<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ page import="com.roomType.model.*"%>

<%RoomTypeVO roomTypeVO = (RoomTypeVO) request.getAttribute("roomTypeVO");%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>房型資訊</title>
</head>
<body>
	<h1>房型資訊</h1>
	<h4><a href="<%=request.getContextPath()%>/back_end/roomType/roomTypeHome.jsp">回首頁</a></h4>

	<table>
		<tr>
			<th>房型編號</th>
			<th>房型名稱</th>
			<th>容納人數</th>
			<th>金額</th>
			<th>房型大小</th>
			<th>床型</th>
			<th>房型資訊</th>
			<th>房型設施</th>
		</tr>
		<tr>
			<td><%=roomTypeVO.getType_no()%></td>
			<td><%=roomTypeVO.getType_name()%></td>
			<td><%=roomTypeVO.getType_qty()%></td>
			<td><%=roomTypeVO.getType_price()%></td>
			<td><%=roomTypeVO.getType_size()%></td>
			<td><%=roomTypeVO.getBed_size()%></td>
			<td><%=roomTypeVO.getType_info()%></td>
			<td><%=roomTypeVO.getType_facility()%></td>
		</tr>
	</table>

</body>
</html>