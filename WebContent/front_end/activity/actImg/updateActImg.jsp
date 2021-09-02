<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>修改</title>
</head>
<body>
	<jsp:useBean id="actService" class="com.activity.model.ActivityService"/>
	<h1><a href="<%=request.getContextPath()%>/front_end/activity/actImg/actHome.jsp">回首頁</a></h1>
	<h1>修改</h1>
	<div>
    	<form method="post" action="<%=request.getContextPath()%>/activity/ActivityImage" enctype="multipart/form-data">     
        	<h3>活動圖片編號:${actImgNo}</h3>
        	<select name="actNo">
         		<c:forEach var="actVO" items="${actService.all}">
         			<option value="${actVO.act_no}">${actVO.act_name}</option>
         		</c:forEach>
        	</select>
       	 	 <br><br>
      		 <input type="file" name="actImg"><br><br><br>
      	     <input type="hidden" name="actImgNo" value="${actImgNo}"><br>
      	     <input type="hidden" name="action" value="updateImg"><br>
      	     <input type="submit" value="確定修改">
		</form>
    </div>
</body>
</html>