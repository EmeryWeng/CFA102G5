<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.foodStore.model.*"%>

<%
    FoodStoreService fdStoreSvc = new FoodStoreService();

    List<FoodStoreVO> list = fdStoreSvc.getAllFoodStore();
    pageContext.setAttribute("list",list);
%>
<jsp:useBean id="fdclassSvc" scope="page" class="com.foodClass.model.FoodClassService" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 1200px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>
</head>
<body>

<table id="table-1">
	<tr><td>
		 <h3>所有店家資料</h3>
		 <h4><a href="/CFA102G5/back_end/foodStore/store_page.jsp">店家首頁</a></h4>
	</td></tr>
</table>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<table>
	<tr>
		<th>店家編號</th>
		<th>店家類別</th>
		<th>店家名稱</th>
		<th>店家地址</th>
		<th>店家經度</th>
		<th>店家緯度</th>
		<th>店家服務</th>
		<th>店家狀態</th>
		<th>修改(未完成)</th>

	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="FoodStoreVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${FoodStoreVO.fd_no}</td>
			<td>
				${fdclassSvc.getClassPK(FoodStoreVO.fd_class_no).fd_class_name}
			</td>
			<td>${FoodStoreVO.fd_name}</td>
			<td>${FoodStoreVO.fd_address}</td>
			<td>${FoodStoreVO.fd_longitude}</td>
			<td>${FoodStoreVO.fd_latitude}</td>
			<td>${FoodStoreVO.fd_service}</td>
			<td>${FoodStoreVO.fd_state}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/FoodStoreServlet.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="fd_no" value="${FoodStoreVO.fd_no}">
			     <input type="hidden" name="action"	value="Update_One"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>


</body>
</html>