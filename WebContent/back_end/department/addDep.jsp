<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.department.model.*"%>
<%
DepartmentVO depVO = (DepartmentVO) request.getAttribute("depVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>
</head>
<body>
<table id="table-1">
	<tr><td>
		 <h3>部門新增 - addEmp.jsp</h3></td><td>
		 <h4><a href="/CFA102G5/back_end/department/XXXXXXXXXXXXXX.jsp">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/DepartmentServlet.do" name="form1">
<table>


	<tr>
		<td>部門名稱:</td>
		<td><input type="text" maxlength="5" name="dep_name" size="20"
			 value="<%=(depVO==null)? "" :depVO.getDep_name()%>" /></td>
	</tr>
	<tr>
		<td>部門狀態:</td>
		<td><input type="radio" name="dep_state" value="true" checked>使用</td>
		<td><input type="radio" name="dep_state" value="false">不使用</td>
	</tr>


</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>
</html>