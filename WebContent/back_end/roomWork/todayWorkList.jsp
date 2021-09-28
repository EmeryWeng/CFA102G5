<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.roomType.model.*"%>
<%@ page import="com.roomOrder.model.*"%>
<%@ page import="com.roomOrderDetail.model.*"%>
<%@ page import="java.time.LocalDate"%>

<jsp:useBean id="roomTypeSvc" class="com.roomType.model.RoomTypeService" />
<jsp:useBean id="orderSvc" class="com.roomOrder.model.RoomOrderService" />
<jsp:useBean id="detailSvc" class="com.roomOrderDetail.model.RoomOrderDetailService" />

<%	
	//用來計算各個狀態的有幾筆資料
	pageContext.setAttribute("orderSvc", orderSvc);

	pageContext.setAttribute("detailSvc", detailSvc);
	
	// 第一次進來執行if裡面，list是getAll
	// 不是第一次進來(點擊狀態分類從controller過來的)，table中就用forward過來的list
	if (request.getAttribute("ordList") == null) {
		List<RoomOrderVO> ordList = orderSvc.getAllRoomOrder();
		pageContext.setAttribute("ordList", ordList);
	}

	// 切換分類的下底線，第一次進來分類0，第一個li加底線
	if (request.getAttribute("ord_state") == null && request.getAttribute("type_no") != null) {
		pageContext.setAttribute("ord_state", 9);
	} else if (request.getAttribute("ord_state") == null && request.getAttribute("type_no") == null) {
		pageContext.setAttribute("ord_state", 0);
	}

	// 切換房型分類的下底線，第一次進來分類0，第一個li加底線
	if (request.getAttribute("type_no") == null) {
		pageContext.setAttribute("type_no", 0);
	}
%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.9/css/jquery.dataTables.min.css" />
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/responsive/1.0.7/css/responsive.dataTables.min.css" />
<%@ include file="/back_end/commonCSS.file"%>
<!-- 基本CSS檔案 -->
<style>
.card {
	background: rgba(247,246,242,1);
	padding: 10px 10px 0 10px;
}
.aa {
	margin: 10px;
}
</style>
</head>
<body>
	<%-- 		<%@ include file="/back_end/loading.file" %> <!-- loading --> --%>
	<%@ include file="/back_end/header.file"%>
	<!-- Header -->
	<%@ include file="/back_end/sidebar.file"%>
	<!-- sidebar -->

	<div class="main-content">
		<div class="row d-flex justify-content-around">
			<div class="col-xl-3 card">
				<div class="text-center row">

					<div class="col">
						<h4>今日待入住訂單</h4>
						<h3>150</h3>
					</div>

					<div class="col">
						<span>已check-in房間</span>
						<div id="radialChart" class="radialChart"></div>
					</div>

				</div>
			</div>

			<div class="col-xl-3 card">
				<div class="text-center row">

					<div class="col">
						<h4>今日待退房訂單</h4>
						<h3>111</h3>
					</div>

					<div class="col">
						<span>已check-out房間</span>
						<div id="radialChart1" class="radialChart"></div>
					</div>

				</div>
			</div>

			<div class="col-xl-3 card">
				<div class="text-center row">

					<div class="col">
						<h4>入住中訂單</h4>
						<h3>221</h3>
					</div>

					<div class="col">
						<span>入住中房間</span>
						<div id="radialChart2" class="radialChart"></div>
					</div>

				</div>
			</div>
		</div>
		
		<table class="table fold-table">
			
			<tr>
				<td colspan="7" class="list-title">今日待入住訂單</td>
			</tr>
			<tr>
				<th>訂單編號</th>
				<th>會員編號</th>
				<th>預計入住日期</th>
				<th>預計退房日期</th>
				<th>房型</th>
				<th>間數</th>
				<th>辦理入住</th>
			</tr>

			<tr class="view">
				<td>11</td>
				<td>22</td>
				<td>33</td>
				<td>44</td>
				<td>55</td>
				<td>66</td>
				<th>XX辦理入住</th>
			</tr>
			<c:if test="0">
                <tr>
                    <td colspan="7" class="no-data">今日無待入住客戶</td>
                </tr>
            </c:if>
			<tr class="fold">
				<td colspan="7" class="d-flex justify-content-between">
				<div class="input-group mb-3 aa">
				  <div class="input-group-prepend">
				    <div class="input-group-text">
				      <input type="checkbox" aria-label="Checkbox for following text input">
				    </div>
				  </div>
				  <input type="text" class="form-control" aria-label="Text input with checkbox">
				</div>
				<div class="input-group mb-3 aa">
				  <div class="input-group-prepend">
				    <div class="input-group-text">
				      <input type="checkbox" aria-label="Checkbox for following text input">
				    </div>
				  </div>
				  <input type="text" class="form-control" aria-label="Text input with checkbox">
				</div>
				<div class="input-group mb-3 aa">
				  <div class="input-group-prepend">
				    <div class="input-group-text">
				      <input type="checkbox" aria-label="Checkbox for following text input">
				    </div>
				  </div>
				  <input type="text" class="form-control" aria-label="Text input with checkbox">
				</div>
<!-- 					<div class="row d-flex justify-content-around my-2"> -->
<%-- 							<c:forEach var="detailVO" items="${detailSvc.getAllByOrdno(orderVO.ord_no)}"> --%>
<!-- 								<tr> -->
<%-- 									<td>${detailVO.ord_no}</td> --%>
<%-- 									<td>${detailVO.detail_no}</td> --%>
<%-- 									<td>${detailVO.checkin_date}</td> --%>
<%-- 									<td>${detailVO.checkout_date}</td> --%>
<%-- 									<td><c:choose> --%>
<%-- 											<c:when test="${orderSvc.getOneRoomOrder(detailVO.ord_no).ord_state==3}"> --%>
<%-- 												<i class='bx bxs-square' style='color: red'></i>已取消</c:when> --%>
<%-- 											<c:when test="${detailVO.detail_state==1}"> --%>
<%-- 												<i class='bx bxs-square' style='color: green'></i>待入住</c:when> --%>
<%-- 											<c:when test="${detailVO.detail_state==2}"> --%>
<%-- 												<i class='bx bxs-square' style='color: orange'></i>入住中 (${detailVO.rm_no})</c:when> --%>
<%-- 											<c:when test="${detailVO.detail_state==3}"> --%>
<%-- 												<i class='bx bxs-square' style='color: gray'></i>已退房</c:when> --%>
<%-- 										</c:choose></td> --%>
<!-- 								</tr> -->
<%-- 							</c:forEach> --%>
<!-- 							<tr> -->
<!-- 								<td></td> -->
<!-- 								<td></td> -->
<!-- 								<td></td> -->
<%-- 								<td>單價 <fmt:formatNumber value="${orderVO.price}" pattern="$###,###,###" /> X ${orderVO.rm_num}間 --%>
<!-- 								</td> -->
<%-- 								<td>總金額 <fmt:formatNumber value="${orderVO.total_price}" pattern="$###,###,###" /> <c:if test="${orderVO.ord_state==3}"> --%>
<!-- 										<div>(已扣掉取消後退款金額)</div> -->
<%-- 									</c:if> --%>
<!-- 								</td> -->
<!-- 							</tr> -->
				</td>
			</tr>
		</table>
	</div>

	<%@ include file="/back_end/commonJS.file"%>
	<!-- 基本JS檔案 -->
	<script>
		$(document).ready(
			function() {
				$("#pagename").text("當日房務");
				$(".fold-table tr.view").on("click",function() {
					$(this).toggleClass("open").next(".fold").toggleClass("open");
				});
				$("li.nav-item:eq(${ord_state+1})").children().addClass("nav-link active");
		});
	</script>
</body>
</html>