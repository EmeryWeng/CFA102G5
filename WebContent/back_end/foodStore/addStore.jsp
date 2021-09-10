<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.foodStore.model.*"%>
<%
FoodStoreVO storeVO = (FoodStoreVO) request.getAttribute("storeVO");
%>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="../commonCSS.file" %> <!-- 基本CSS檔案 -->
<title>Insert title here</title>
<style>
  
  
</style>
<title>Insert title here</title>
</head>
<body>
		<%@ include file="/back_end/header.file" %> <!-- Header -->
		<%@ include file="/back_end/sidebar.file" %> <!-- sidebar -->

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<div class="main-content card card-body table-responsive">
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/FoodStoreServlet.do" name="form1">
<table id="example4" class="display" style="min-width: 845px">
	<tr>
		<td>店名:</td>
		<td><input type="TEXT" maxlength="10" name="fd_name" size="20" 
			 value="<%= (storeVO==null)? "" : storeVO.getFd_name()%>" /></td>
	</tr>
	<tr>
		<td>店址 :</td>
		<td><input type="TEXT" maxlength="50" name="fd_address" size="20"
			 value="<%=(storeVO==null)? "" : storeVO.getFd_address()%>" /></td>
	</tr>
	<tr>
		<td>經度:</td>
		<td><input type="text"  name="fd_longitude" size="20"
			 value="<%=(storeVO==null)? "" :storeVO.getFd_longitude()%>" /></td>
	</tr>
	<tr>
		<td>緯度:</td>
		<td><input type="text"  name="fd_latitude" size="20"
			 value="<%=(storeVO==null)? "" :storeVO.getFd_latitude()%>" /></td>
	</tr>
	<tr>
		<td>服務:</td>
		<td><input type="text"  name="fd_service" size="20"
			 value="<%=(storeVO==null)? "" :storeVO.getFd_service()%>" /></td>
	</tr>
	<tr>
		<td>狀態:</td>
		<td><input type="radio" name="fd_state" value="true" checked>有營業</td>
		<td><input type="radio" name="fd_state" value="false">已關店</td>
	</tr>

	<jsp:useBean id="fdsvc" scope="page" class="com.foodClass.model.FoodClassService" />
	<tr>
		<td>店家類別:<font color=red><b>*</b></font></td>
		<td><select size="1" name="fd_class_no" >
			<c:forEach var="fdsvcVO" items="${fdsvc.all}">
				<option value="${fdsvcVO.fd_class_no}" ${(vo.fd_class_no==fdsvcVO.fd_class_no)?'selected':'' } >${fdsvcVO.fd_class_name}
			</c:forEach>
		</select></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</div>
<%@ include file="/back_end/commonJS.file" %> <!-- 基本JS檔案 -->
<script>
// 			● 可在這更改這一頁header的標題，不寫也可以，但請變成空字串 
			$("#pagename").text("新增店家");
		</script>
		

</body>
</html>