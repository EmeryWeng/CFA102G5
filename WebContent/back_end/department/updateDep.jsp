<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.department.model.*"%>

<%
DepartmentVO DepVO = (DepartmentVO) request.getAttribute("DepVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table id="table-1">
	<tr><td>
		 <h3>部門資料修改</h3>
		 <h4><a href="back_end/department/XXXXXXXXXXX.jsp">未完成</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

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
		<td>部門編號:<font color=red><b>*</b></font></td>
		<td><%=DepVO.getDep_no()%></td>
	</tr>
	<tr>
		<td>部門名稱:</td>
		<td><input type="TEXT" name="dep_name" size="20" value="<%=DepVO.getDep_name()%>" /></td>
	</tr>
	<tr>
		<td>部門狀態</td>
		<td><input type="radio" id="true" name="dep_state"  value="true" checked>使用</td>
		<td><input type="radio" id="false" name="dep_state"  value="false" >不使用</td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="dep_no" value="<%=DepVO.getDep_no()%>">
<input type="submit" value="送出修改"></FORM>

</body>
</html>