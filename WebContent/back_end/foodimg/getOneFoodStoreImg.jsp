<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.foodImg.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>店家照片</title>
</head>
<body>
	<table id="table-1">
	<tr><td>
		 <h4><a href="back_end/foodimg/select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
	<tr>
		<th>照片編號</th>
		<th>照片店家</th>
		<th>照片</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<c:forEach var="FoodImgVO" items="${imgVO}" >
		<tr>
			<td>${FoodImgVO.fd_img_no}</td>
			<td>${FoodImgVO.fd_no}</td>
			<td><img src="<%=request.getContextPath()%>/FoodImgReader?fd_img_no=${FoodImgVO.fd_img_no}" style="width:300px;high:300px"></td>
		
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/FoodImg.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="fd_img_no" value="${FoodImgVO.fd_img_no}">
			     <input type="hidden" name="action"	value="One_Img_Update"></FORM>
			</td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/FoodImg.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="fd_img_no"  value="${FoodImgVO.fd_img_no}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
			
		</tr>
	</c:forEach>
	
</table>
</body>
</html>