<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.foodStore.model.*"%>

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
        <%@ include file="/front_end/commonCSS.file" %> <!-- 基本CSS檔案 -->
<meta http-equiv="Content-Type" content="text/html">
<title>Insert title here</title>
<style>
*{
  box-sizing: border-box;
}
body{
  margin: 0;
  padding: 10px;
}
#header{
max-width:100%;
height:100px;
}
#sidebar{
width:100%;
height:800px;
margin: 0 0 10px 0 ;
}
#svc{
height:800px; 
width:33%;
display: inline-block;
vertical-align: top;
margin-right: 10px;
overflow: auto;
overflow-x: hidden;
}
#map-canvas{
height:800px; 
width: calc(77% - 200px - 1em);
display: inline-block;
vertical-align: top;
}
#footer{
max-width:100%;
  margin: 10px auto 0 auto;
  border: 1px solid #999;
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
</head>
<body>
<div id="header">
        <%@ include file="/front_end/header.file" %> <!-- Header -->
</div>
		<div id="sidebar">
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
    			   <input type="submit" value="送出" class="btn btn-primary" style="width:150px">
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
		
				<table style="width:100%;">
			<tr>
				<th>店家照片</th>
				<th>店家類別</th>
				<th>店家名稱</th>
				<th>店家地址</th>
			</tr>
			<c:forEach var="storeVO" items="${storeVO}">
		
				<tr onclick="initMap(${storeVO.fd_longitude},${storeVO.fd_latitude})" style="border: 1px solid #F6F7F7;">
					<td><img src="${pageContext.request.contextPath}/FoodImg.do?fd_no=${storeVO.fd_no}" style="width:125px;height:125px"></td>
					<td>
						${storeSvc.getClassPK(storeVO.fd_class_no).fd_class_name}
					</td>
					<td>${storeVO.fd_name}</td>
					<td>${storeVO.fd_address}</td>
				</tr>
			</c:forEach>
				</table>
				</div>
<div id="map-canvas"></div>					<!--地圖生成 --> 
		</div>

 <div id="footer">
         <%@ include file="/front_end/footer.file" %> <!-- Footer -->      
       	 <%@ include file="/front_end/commonJS.file" %> <!-- 基本JS檔案 -->
 </div>
<script src="http://maps.google.com/maps/api/js?key=AIzaSyArfUAIQgXQuAQZ8vViswotNOMyWb35r9k">
</script>
<script>
var map;
function initMap(lng1, lat1) {
	this.lat1 = lat1;
	this.lng1 = lng1;
	//載入路線服務與路線顯示圖層
  var directionsService = new google.maps.DirectionsService();
  var directionsDisplay = new google.maps.DirectionsRenderer();
  //初始化地圖
  map = new google.maps.Map(document.getElementById('map-canvas'), {
    zoom: 16,
    center: { lat: 23.99483, lng: 121.630453 }			//初始化地點
  });
  
  var destination = {lat: lat1, lng: lng1}				//宣告物件加入經緯度 
  //放置路線圖層
  directionsDisplay.setMap(map);
  //路線相關設定 
  var request = {
    origin: { lat: 23.99483, lng: 121.630453 },				//起始地點
    destination,											//加入destination物件
    travelMode: 'DRIVING',									//預設就是DRIVING
  };
  //繪製路線
  directionsService.route(request, function (result, status) {
      if (status == 'OK') {
    	  //回傳路線上每個步驟的細節
          console.log(result.routes[0].legs[0].steps);
          directionsDisplay.setDirections(result);
      } else {
          console.log(status);
      }
  });
}
google.maps.event.addDomListener(window, 'load', initMap);
</script>
</body>
</html>