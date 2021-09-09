<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.employee.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    EmpService empSvc = new EmpService();
    List<EmployeeVO> list = empSvc.getAllEmp();
    pageContext.setAttribute("list",list);
%>
<jsp:useBean id="deptSvc" scope="page" class="com.department.model.DepService" />
<html>
<head>
<title>所有員工資料</title>

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
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>所有員工資料</h3>
		 <h4><a href="/CFA102G5/back_end/emp/selectPage.jsp">回首頁</a></h4>
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
		<th>員工編號</th>
		<th>名字</th>
		<th>郵件</th>
		<th>狀態</th>
		<th>所在部門</th>
		<th>修改</th>

	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="EmployeeVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${EmployeeVO.emp_no}</td>
			<td>${EmployeeVO.emp_name}</td>
			<td>${EmployeeVO.emp_mail}</td>
			<td>${EmployeeVO.emp_state}</td>
			<td>
				${deptSvc.getOnePK(EmployeeVO.dep_no).dep_name}
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/EmployeeServlet.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="emp_no" value="${EmployeeVO.emp_no}">
			     <input type="hidden" name="action"	value="Update_One"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>