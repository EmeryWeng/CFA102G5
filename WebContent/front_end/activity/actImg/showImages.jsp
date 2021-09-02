<%-- <%@page import="com.activityImage.model.ActivityImageVO"%> --%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>顯示圖片</title>
</head>
<body>

	<h1><a href="<%=request.getContextPath()%>/front_end/activity/actImg/actHome.jsp">回首頁</a></h1>
	<table border="1">
		<tr>
			<th>活動圖片編號</th>
			<th>活動編號</th>
			<th>圖片</th>
		</tr>
		<c:choose>
			<c:when test="${not empty actImgVO}">
				<tr>
					<td>${actImgVO.act_img_no}</td>
					<td>${actImgVO.act_no}</td>
					<td><img src="data:image/jpg;base64,${actImgVO.imgToBase64(actImgVO.act_img)}" width="240"
						height="300" /></td>
					<td>
						<form method="post" action="<%=request.getContextPath()%>/activity/ActivityImage">
							<input type="hidden" name="action" value="updateActImgNo">
							<input type="hidden" name="actImgNo" value="${actImgVO.act_img_no}">
							<input type="submit" value="修改"> 
						</form>
					</td>
					<td>
						<form method="post" action="<%=request.getContextPath()%>/activity/ActivityImage">
							<input type="submit" value="刪除"> 
							<input type="hidden" name="action" value="deleteImg">
							<input type="hidden" name="actImgNo" value="${actImgVO.act_img_no}">
						</form>
					</td>
				</tr>
			</c:when>
		
		<c:otherwise>
				<c:forEach var="actImgVO" items="${actImgList}">
		
				<tr>
					<td>${actImgVO.act_img_no}</td>
					<td>${actImgVO.act_no}</td>
					<td><img src="data:image/jpg;base64,${actImgVO.imgToBase64(actImgVO.act_img)}" width="240"
						height="300" /></td>
					<td>
						<form method="post" action="<%=request.getContextPath()%>/activity/ActivityImage">
							<input type="hidden" name="action" value="updateActImgNo">
							<input type="hidden" name="actImgNo" value="${actImgVO.act_img_no}">
							<input type="submit" value="修改"> 
						</form>
					</td>
					<td>
						<form method="post" action="<%=request.getContextPath()%>/activity/ActivityImage">
							<input type="submit" value="刪除"> 
							<input type="hidden" name="action" value="deleteImg">
							<input type="hidden" name="actImgNo" value="${actImgVO.act_img_no}">
						</form>
					</td>
				</tr>
			</c:forEach>
			</c:otherwise>
		</c:choose>
	</table>

</body>
</html>