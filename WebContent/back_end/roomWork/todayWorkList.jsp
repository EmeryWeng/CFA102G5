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
// 	RoomOrderService roomOrderSvc = new RoomOrderService();
// 	RoomOrderDetailService detailSvc1 = new RoomOrderDetailService();
// 	List<RoomOrderVO> checkInList = roomOrderSvc.
// 	List<RoomOrderDetailVO> checkOutList = detailSvc.checkoutList();
// 	List<RoomOrderDetailVO> stayList = detailSvc.stayList();
// 	pageContext.setAttribute("checkInList", checkInList);
// 	pageContext.setAttribute("checkOutList", checkOutList);
// 	pageContext.setAttribute("stayList", stayList);
%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.9/css/jquery.dataTables.min.css" />
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/responsive/1.0.7/css/responsive.dataTables.min.css" />
<link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
	
<%@ include file="/back_end/commonCSS.file"%>
<!-- 基本CSS檔案 -->
<style>
		table.dataTable.dtr-inline.collapsed>tbody>tr>td:first-child:before, table.dataTable.dtr-inline.collapsed>tbody>tr>th:first-child:before {
		    top: 42%;
		    height: 20px;
		    width: 20px;
		    border-radius: 16px;
		    line-height: 20px;
		    background-color: #996A4D;
		}
		table.dataTable.dtr-inline.collapsed>tbody>tr>td:first-child, table.dataTable.dtr-inline.collapsed>tbody>tr>th:first-child {
		    position: relative;
		    padding-left: 50px;
		    cursor: pointer;
		}
		table.dataTable.dtr-inline.collapsed>tbody>tr.parent>td:first-child:before, table.dataTable.dtr-inline.collapsed>tbody>tr.parent>th:first-child:before {
			background-color: #30504F;
		}
		table.dataTable>tbody>tr.child span.dtr-title {
		    display: inline-block;
		    min-width: 100px;
		    font-weight: bold;
		}
		table.dataTable.dtr-inline.collapsed>tbody>tr>td:first-child, table.dataTable.dtr-inline.collapsed>tbody>tr>th:first-child {
		    border-bottom: 1px solid #d5dcdb;
		}
		td.sorting_1 {
			border-bottom: #F2F2F2 !important;
		}
		table li:last-child {
			height: 100px;
			text-align: center;
		}
		table li:first-child {
			text-align: center;
			background-color: #ccc;
		}
</style>
</head>
<body>
	<%-- 		<%@ include file="/back_end/loading.file" %> <!-- loading --> --%>
	<%@ include file="/back_end/header.file"%> <!-- Header -->
	<%@ include file="/back_end/sidebar.file"%> <!-- sidebar -->

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
		
		<h3>今日待入住<h3>
		<table id="checkInTable" class="display" style="min-width: 800px;">
			<thead>
				<tr>
					<th>訂單編號</th>
					<th>會員資料</th>
					<th>預計入住日</th>
					<th>預計退房日</th>
					<th>訂購人姓名</th>
					<th>訂購人電話</th>
					<th>入住</th>
					<th class="none">備註</th>
					<th class="none big">選擇房間</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="roomTypeVO" items="${roomTypeSvc.getAllRoomType()}">
					<tr>
						<td>${roomTypeVO.type_no}</td>
						<td>${roomTypeVO.type_name}</td>
						<td>${roomTypeVO.type_qty}人</td>
						<td>${roomTypeVO.type_price}</td>
						<td>${roomTypeVO.type_price}</td>
						<td>${roomTypeVO.type_price}</td>
						<td><a class="btn btn-secondary btn-sm" href="<%=request.getContextPath()%>/room/RoomType?type_no=${roomTypeVO.type_no}&action=getOneForUpdate">CHECK IN</a></td>
						<td>${roomTypeVO.bed_size}</td>
						<td>

							<div class="card-body">
                                <select id="limit-selection" name="states[]" multiple="multiple">
                                    <option value="AL">Alabama</option>
                                    <option value="WY">Wyoming</option>
                                    <option value="BY">Lorem</option>
                                    <option value="DY">Ipsum</option>
                                    <option value="MY">Dolor</option>
                                </select>
                            </div>
<select class="js-example-basic-single" name="state">
  <option value="AL">Alabama</option>
    ...
  <option value="WY">Wyoming</option>
</select>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<h3>今日待退房<h3>
		<table id="checkOutTable" class="display" style="min-width: 800px;">
			<thead>
				<tr>
					<th>明細編號</th>
					<th>會員資料</th>
					<th>實際入住日</th>
					<th>退房日期</th>
					<th>入住人姓名</th>
					<th>房號</th>
					<th>退房</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="checkOutVO" items="${checkOutList}">
					<tr>
						<td>${checkOutVO.detail_no}</td>
						<td>mem_no</td>
						<td>${checkOutVO.checkin_date}</td>
						<td>${checkOutVO.checkout_date}</td>
						<td>${checkOutVO.name} ${checkOutVO.title}</td>
						<td>rm_no</td>
						<td><a class="btn btn-secondary btn-sm" href="<%=request.getContextPath()%>/room/RoomType?type_no=${roomTypeVO.type_no}&action=getOneForUpdate">CHECK OUT</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<h3>入住中清單<h3>
		<table id="stayTable" class="display" style="min-width: 800px;">
			<thead>
				<tr>
					<th>明細編號</th>
					<th>會員資料</th>
					<th>實際入住日</th>
					<th>退房日期</th>
					<th>入住人姓名</th>
					<th>房號</th>
					<th>提前退房</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="roomTypeVO" items="${roomTypeSvc.getAllRoomType()}">
					<tr>
						<td>${roomTypeVO.type_no}</td>
						<td>${roomTypeVO.type_name}</td>
						<td>${roomTypeVO.type_qty}人</td>
						<td>${roomTypeVO.type_price}</td>
						<td>${roomTypeVO.type_price}</td>
						<td>${roomTypeVO.type_price}</td>
						<td><a class="btn btn-secondary btn-sm" href="<%=request.getContextPath()%>/room/RoomType?type_no=${roomTypeVO.type_no}&action=getOneForUpdate">提前退房</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>


	<%@ include file="/back_end/commonJS.file"%> <!-- 基本JS檔案 -->
	<script type="text/javascript" src="https://cdn.datatables.net/1.10.9/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="https://cdn.datatables.net/responsive/1.0.7/js/dataTables.responsive.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>	
	<script>
		$(document).ready(
			function() {
				$("#pagename").text("當日房務");
				$("#checkInTable").DataTable( {
			    	"responsive": true,
			    	"paging": false,
			    	"lengthMenu": false,
			    	"info": false,
			    	"searching": false,
			        "language": {
			        	"processing": "處理中...",
			            "loadingRecords": "載入中...",
			            "lengthMenu": "顯示 _MENU_ 項結果",
			            "zeroRecords": "沒有符合的結果",
			            "info": "顯示第 _START_ 至 _END_ 項結果，共 _TOTAL_ 項",
			            "infoEmpty": "顯示第 0 至 0 項結果，共 0 項",
			            "infoFiltered": "(從 _MAX_ 項結果中過濾)",
			            "infoPostFix": "",
			            "search": "搜尋",
			            "searchPlaceholder": "...",
			            "paginate": {
			                "first": "第一頁",
			                "previous": "上一頁",
			                "next": "下一頁",
			                "last": "最後一頁"
			            },
			        }
			    } );
				$("#checkOutTable").DataTable( {
			    	"responsive": true,
			    	"paging": false,
			    	"lengthMenu": false,
			    	"info": false,
			    	"searching": false,
			        "language": {
			        	"processing": "處理中...",
			            "loadingRecords": "載入中...",
			            "lengthMenu": "顯示 _MENU_ 項結果",
			            "zeroRecords": "沒有符合的結果",
			            "info": "顯示第 _START_ 至 _END_ 項結果，共 _TOTAL_ 項",
			            "infoEmpty": "顯示第 0 至 0 項結果，共 0 項",
			            "infoFiltered": "(從 _MAX_ 項結果中過濾)",
			            "infoPostFix": "",
			            "search": "搜尋",
			            "searchPlaceholder": "...",
			            "paginate": {
			                "first": "第一頁",
			                "previous": "上一頁",
			                "next": "下一頁",
			                "last": "最後一頁"
			            },
			        }
			    } );
				$("#stayTable").DataTable( {
			    	"responsive": true,
			    	"paging": false,
			    	"lengthMenu": false,
			    	"info": false,
			    	"searching": false,
			        "language": {
			        	"processing": "處理中...",
			            "loadingRecords": "載入中...",
			            "lengthMenu": "顯示 _MENU_ 項結果",
			            "zeroRecords": "沒有符合的結果",
			            "info": "顯示第 _START_ 至 _END_ 項結果，共 _TOTAL_ 項",
			            "infoEmpty": "顯示第 0 至 0 項結果，共 0 項",
			            "infoFiltered": "(從 _MAX_ 項結果中過濾)",
			            "infoPostFix": "",
			            "search": "搜尋",
			            "searchPlaceholder": "...",
			            "paginate": {
			                "first": "第一頁",
			                "previous": "上一頁",
			                "next": "下一頁",
			                "last": "最後一頁"
			            },
			        }
			    } );
				$('.limit-selection').select2({
					placeholder: '請選擇房間',
					allowClear: true
				});
				// In your Javascript (external .js resource or <script> tag)
				$(document).ready(function() {
				    $('.js-example-basic-single').select2();
				});
		});
	</script>
</body>
</html>