<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.foodStore.model.*"%>
<%
	FoodStoreVO vo = (FoodStoreVO)request.getAttribute("vo");
%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../commonCSS.file" %> <!-- 基本CSS檔案 -->
<meta charset="UTF-8">
</head>
<body>
		<%@ include file="/back_end/header.file" %> <!-- Header -->
		<%@ include file="/back_end/sidebar.file" %> <!-- sidebar -->
		

<div class="main-content card card-body table-responsive">
     <h4><a href="<%=request.getContextPath()%>/back_end/foodStore/allStore.jsp">回店家列表</a></h4>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/FoodStoreServlet.do" name="form1">
<table id="example4" class="display" style="min-width: 845px">
	<tr>
		<td>店家編號:<font color=red><b>*</b></font></td>
		<td><%=vo.getFd_no()%></td>
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
	
	<tr>
		<td>店名:</td>
		<td><input type="TEXT" name="fd_name" size="20" maxlength="10" value="<%=vo.getFd_name()%>" /></td>
	</tr>
	<tr>
		<td>店家地址:</td>
		<td><input type="TEXT" name="fd_address" size="50" maxlength="50" value="<%=vo.getFd_address()%>" /></td>
	</tr>
	<tr>
		<td>店家經度:</td>
		<td><input type="TEXT" name="fd_longitude" size="50" value="<%=vo.getFd_longitude()%>" /></td>
	</tr>
	<tr>
		<td>店家緯度:</td>
		<td><input type="TEXT" name="fd_latitude" size="50" value="<%=vo.getFd_latitude()%>" /></td>
	</tr>
	<tr>
		<td>店家服務:</td>
		<td><input type="TEXT" name="fd_service" size="50" value="<%=vo.getFd_service()%>" /></td>
	</tr>
	<tr>
		<td>店家狀態:</td>
		<td><input type="radio" name="fd_state"  value="true" checked>有此店家</td>
		<td><input type="radio" name="fd_state"  value="false" >無此店家</td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="fd_no" value="<%=vo.getFd_no()%>">
<input type="submit" value="送出修改"></FORM>
</div>
<%@ include file="/back_end/commonJS.file" %> <!-- 基本JS檔案 -->
<script>
// 			● 可在這更改這一頁header的標題，不寫也可以，但請變成空字串 
			$("#pagename").text("修改店家資料");
		</script>
		
</body>
</html>