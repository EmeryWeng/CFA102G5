<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.employee.model.*"%>
<%
EmployeeVO empVO = (EmployeeVO) request.getAttribute("empVO");
%>
<html>
<head>

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
		 <h3>員工資料新增 - addEmp.jsp</h3></td><td>
		 <h4><a href="/CFA102G5/back_end/emp/selectPage.jsp">回首頁</a></h4>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/EmployeeServlet.do" name="form1">
<table>
	<tr>
		<td>姓名:</td>
		<td><input type="TEXT" maxlength="5" name="emp_name" size="20" 
			 value="<%= (empVO==null)? "" : empVO.getEmp_name()%>" /></td>
	</tr>
	<tr>
		<td>郵件:</td>
		<td><input type="TEXT" maxlength="50" name="emp_mail" size="20"
			 value="<%=(empVO==null)? "" : empVO.getEmp_mail()%>" /></td>
	</tr>
	<tr>
		<td>密碼:</td>
		<td><input type="text" maxlength="15" name="emp_password" size="20"
			 value="<%=(empVO==null)? "" :empVO.getEmp_password()%>" /></td>
	</tr>
	<tr>
		<td>狀態:</td>
		<td><input type="radio" name="emp_state" value="true" checked>在職</td>
		<td><input type="radio" name="emp_state" value="false">離職</td>
	</tr>

	<jsp:useBean id="deptSvc" scope="page" class="com.department.model.DepService" />
	<tr>
		<td>部門:<font color=red><b>*</b></font></td>
		<td><select size="1" name="dep_no">
			<c:forEach var="deptVO" items="${deptSvc.all}">
				<option value="${deptVO.dep_no}" ${(empVO.dep_no==deptVO.dep_no)? 'selected':'' } >${deptVO.dep_name}
			</c:forEach>
		</select></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>
</html>