<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>測試圖片上傳 </title>
</head>
<body>

<!-- 錯誤處理 -->
	<c:if test="${not empty error}">
		<font style="color:red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="errorMsg" items="${error}">
				<li style="color:red">${errorMsg}</li>
			</c:forEach>
		</ul>
	</c:if>

	<jsp:useBean id="actService" class="com.activity.model.ActivityService"/>
    <h1><a href="<%=request.getContextPath()%>/front_end/activity/actImg/actHome.jsp">回首頁</a></h1>
    <h1> 測試圖片上傳 </h1>
    <div>
    	<form action="<%=request.getContextPath()%>/activity/ActivityImage" method="post" enctype="multipart/form-data">     
        	<select name="actNo">
        		<c:forEach var="actVO" items="${actService.all}">
         			<option value="${actVO.act_no}">${actVO.act_name}</option>
         		</c:forEach>
        	</select>
       	 	 <br><br>
      		 <input type="file" name="actImg"><br><br><br>
      	     <input type="submit" value="新增圖片"><br>
      	     <input type="hidden" name="action" value="addImg">    
		</form>
    </div>
</body>
</html>