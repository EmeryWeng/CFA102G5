<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.department.model.*"%>

<%
    DepService depSvc = new DepService();
    List<DepartmentVO> list = depSvc.getAll();
    pageContext.setAttribute("list",list);
%>
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
	width: 800px;
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
		 <h3>所有員工資料</h3>
		 <h4><a href="/CFA102G5/back_end/department/XXXXXXXXXXXXXXXX.jsp">未完成</a></h4>
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
		<th>部門編號</th>
		<th>部門名稱</th>
		<th>部門狀態</th>
		<th>修改</th>

	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="DepartmentVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${DepartmentVO.dep_no}</td>
			<td>${DepartmentVO.dep_name}</td>
			<td>${DepartmentVO.dep_state}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/DepartmentServlet.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="dep_no" value="${DepartmentVO.dep_no}">
			     <input type="hidden" name="action"	value="Update_One"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
</body>
</html>