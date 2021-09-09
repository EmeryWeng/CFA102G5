<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.foodImg.model.*"%>

    <%
    FoodImgVO foodImgVO = (FoodImgVO)request.getAttribute("imgvo"); 
    
    %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
<meta charset="BIG5">
<title>店家照片修改</title>
</head>
<style>


</style>
<body>

<table id="table-1">
	<tr><td>
		 <h3>店家照片修改</h3>
		 <h4><a href="back_end/foodimg/allStoreImg.jsp">上一頁</a></h4>
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
	
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/FoodImg.do" name="form1" enctype="multipart/form-data">
<table class="tab1">
	
	<tr>
		<td>照片編號:<font color=red><b>*</b></font></td>
		<td><input type="text" readonly="readonly" name="fd_img_no" value="<%=foodImgVO.getFd_img_no()%>"></td>
	</tr>
	<tr>
		<td>店家編號:<font color=red><b>*</b></font></td>
		<td><input type="text" readonly="readonly" name="fd_no" value="<%=foodImgVO.getFd_no()%>"></td>
	</tr>
</table>
	<div>
		<a>欲修改照片:<font color=red><b>*</b></font></a>
		<a><img src="${pageContext.request.contextPath}/FoodImgReader?fd_img_no=<%=foodImgVO.getFd_img_no()%>" style="width:250px;high:250px"></a>
	</div>	
	
	<div>
		<a><input type="file" name="fd_img" size="45" value="<%=foodImgVO.getFd_img()%>" onchange="loadImageFile(event)"></a>
	</div>
		<a>修改照片 :<img id="image" src="" style="width:250px;high:250px"></a>
		

<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="fd_img_no" value="<%=foodImgVO.getFd_img_no()%>">
<input type="submit" value="送出修改">

<script>
function loadImageFile(event){ 
	let image = document.getElementById('image');
	image.src = URL.createObjectURL(event.target.files[0]); };
</script>
</FORM>


</body>
</html>