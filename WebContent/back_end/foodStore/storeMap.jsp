<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.foodStore.model.*"%>
<%

    FoodStoreService fdStoreSvc = new FoodStoreService();
    List<FoodStoreVO> list = fdStoreSvc.getAllFoodStore();
    pageContext.setAttribute("list",list);
    
%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../commonCSS.file" %> <!-- 基本CSS檔案 -->
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<style>
#map-canvas{
height:800px; 
width:65%;
border: 1px solid red;
left:35%;
}

#header{
max-width:100%;
height:100px;
}

#sidebar{
margin-top:80px;
width:35%;
height:800px;
overflow:auto;
overflow-x: hidden;
position:fixed;
}
#svc{
margin-left:350px;
}
.select {
  border:0px;
  width:100px;
  height:34px;
  padding-left:2px;
  padding-right:40px;
  background-color:#F6F7F7;
  color:gray;
}
</style>
<script
  	src="http://maps.google.com/maps/api/js?key=AIzaSyArfUAIQgXQuAQZ8vViswotNOMyWb35r9">
</script>
<script>
var map;
function initMap(lng1, lat1) {
	this.lat1 = lat1;
	this.lng1 = lng1;
  // 載入路線服務與路線顯示圖層
  var directionsService = new google.maps.DirectionsService();
  var directionsDisplay = new google.maps.DirectionsRenderer();
  // 初始化地圖
  map = new google.maps.Map(document.getElementById('map-canvas'), {
    zoom: 14,
    center: { lat: 23.99483, lng: 121.630453 }			//初始化地點
  });
  
  var destination = {lat: lat1, lng: lng1}				//宣告物件加入經緯度	
  // 放置路線圖層
  directionsDisplay.setMap(map);
  // 路線相關設定
  var request = {
    origin: { lat: 23.99483, lng: 121.630453 },				//起始地點
    destination,											//加入destination 物件
    travelMode: 'DRIVING',									//預設就是DRIVING
  };
  // 繪製路線
  directionsService.route(request, function (result, status) {
      if (status == 'OK') {
          // 回傳路線上每個步驟的細節
          console.log(result.routes[0].legs[0].steps);
          directionsDisplay.setDirections(result);
      } else {
          console.log(status);
      }
  });
}
google.maps.event.addDomListener(window, 'load', initMap);
</script>
</head>


<body>
<div id="header">
		<div id="svc">
			<jsp:useBean id="storeSvc" scope="page" class="com.foodClass.model.FoodClassService" />
    		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/FoodStoreServlet.do" >
     			  <b>您要搜尋哪個類別 </b>
      			 <select size="1" name="fd_class_no" class="select">
       			  <c:forEach var="stroeVO" items="${storeSvc.all}" > 
       			   <option value="${stroeVO.fd_class_no}">${stroeVO.fd_class_name}
      			   </c:forEach>   
     			  </select>
     			  <input type="hidden" name="action" value="getStoreFK">
    			   <input type="submit" value="送出">
   			 </FORM>
   			 	<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color:red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color:red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
		</div>
		<div id="sidebar">
		
				<table style="width:100%;">
			<tr>
				<th>店家照片</th>
				<th>店家類別</th>
				<th>店家名稱</th>
				<th>店家地址</th>
			</tr>
			<c:forEach var="storeVO" items="${storeVO}">
		
				<tr onclick="initMap(${storeVO.fd_longitude},${storeVO.fd_latitude})" style="border: 1px solid red;">
					<td><img src="${pageContext.request.contextPath}/FoodImg.do?fd_no=${storeVO.fd_no}" style="width:200px;height:200px"></td>
					<td>
						${storeSvc.getClassPK(storeVO.fd_class_no).fd_class_name}
					</td>
					<td>${storeVO.fd_name}</td>
					<td>${storeVO.fd_address}</td>
				</tr>
			</c:forEach>
				</table>
		</div>
</div>
 <div id="map-canvas"></div>
</body>
</html>