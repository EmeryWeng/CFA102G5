<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
<title>Insert title here</title>
<style type="text/css">
img{
width:250px;
heigh:250px;
}

</style>
</head>
<body>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<div class="div1">
	<div class="card" style="width: 18rem;">
	<div id="divstorall" class="card-body">
	<a href='allStoreImg.jsp' class="btn btn-primary" role="button">查詢全部店家照片</a>
    </div>
    </div>
    
    <div class="card" style="width: 18rem;">
    <div class="div2" class="card-body">
	 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/FoodImg.do" >
        <b>搜尋店家照片編號</b>
        <input type="text" name="fd_no" required placeholder="填入數字">
        <input type="hidden" name="action" value="getOneFoodStoreImg">
        <input type="submit" class="btn btn-primary" value="送出">
    </FORM>
    </div>
    </div>
    
    <div class="card" style="width: 18rem;">
     <div class="div3" class="card-body">
    <FORM METHOD="post" action="<%=request.getContextPath()%>/FoodImg.do" enctype="multipart/form-data">
        <b>請輸入要新增圖片店家的編號</b>
        <input type="text" name="fd_no" required placeholder="填入數字">
        <input type="file" name="fd_img" id="upload" onchange="loadImageFile(event)" required>
        <img id="image" src="" >
        <input type="submit" value="送出" class="btn btn-primary"> 
        <input type="hidden" name="action" value="addImg">
    </FORM>
       </div>
       </div>
</div>
</body>

<script>
function loadImageFile(event){ 
	let image = document.getElementById('image');
	image.src = URL.createObjectURL(event.target.files[0]); };
</script>
</html>