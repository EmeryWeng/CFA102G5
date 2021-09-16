<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.department.model.*"%>
<%
DepartmentVO depVO = (DepartmentVO) request.getAttribute("depVO");
%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../commonCSS.file" %> <!-- 基本CSS檔案 -->
<title>Insert title here</title>
<style>

</style>
</head>
<body>
		<%@ include file="/back_end/header.file" %> <!-- Header -->
		<%@ include file="/back_end/sidebar.file" %> <!-- sidebar -->


<div class="main-content card card-body table-responsive">
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
<table id="example4" class="display" style="min-width: 845px">
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
</div>
<%@ include file="/back_end/commonJS.file" %> <!-- 基本JS檔案 -->
<script>
// 			● 可在這更改這一頁header的標題，不寫也可以，但請變成空字串 
			$("#pagename").text("新增部門");
		</script>

</body>
</html>