<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.roomType.model.*"%>
<%@ page import="com.roomImg.model.*"%>

<jsp:useBean id="roomTypeSvc" scope="page" class="com.roomType.model.RoomTypeService" />
<jsp:useBean id="roomImgSvc" scope="page" class="com.roomImg.model.RoomImgService" />

<!DOCTYPE html>
<html lang="en">
	<head>
		<%@ include file="/back_end/commonCSS.file" %> <!-- 基本CSS檔案 -->
		<style>
		table.dataTable thead th, td {
			color: #30504F;
		}
		
		.add {
			padding-bottom: 30px;
		}
		
		#roomTypeTable_filter {
			color: #30504F;
		}
		
		#roomTypeTable_filter input {
			border: .5px solid #8FC2C2;
		}
		
		.btn-icon-start {
			background-color: transparent;
			margin-right: 0;
		}
		
		i.bxs-pencil, i.bxs-image {
			color: #30504F;
			font-size: 16px;
			padding-right: 2px;
		}
		
		.state label {
			position: relative;
			display: inline-block;
			width: 42px;
			height: 22px;
			background-color: #E4D6C4;
			border-radius: 25px;
			cursor: pointer;
			box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.3);
			margin-top: 16px;
		}

		.state label:before {
			content: '';
			position: absolute;
			top: 3px;
			left: 4px;
			height: 15px;
			width: 15px;
			background-color: white;
			border-radius: 24px;
			box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
		}
		
		.state input {
			display: none;
		}
		
		.state input:checked+label {
			background-color: #996A4D;
		}
		
		.state input:checked+label:before {
			transform: translateX(20px);
			box-shadow: 0 0 5px rgba(0, 0, 0, 0.3);
		}
		h3>span {
			color: #30504F;
			font-weight: 700;
		}
		.img-container {
			width: 250px;
			border-radius: 0 30px 0 30px;
			overflow: hidden;
		}
		img {
			max-width: 100%;
		}
		</style>
	</head>

	<body>
<%-- 		<%@ include file="/back_end/loading.file" %> <!-- loading --> --%>
		<%@ include file="/back_end/header.file" %> <!-- Header -->
		<%@ include file="/back_end/sidebar.file" %> <!-- sidebar -->
		
		<div class="main-content">
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color:red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color:red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
			<div class="add">
				<a href='<%=request.getContextPath()%>/back_end/roomType/addRoomType.jsp'>
					<button type="button" class="btn btn-rounded btn-primary"><span
						class="btn-icon-start text-primary"><i class='bx bx-plus' style='color:#fff' ></i>
					</span>新增</button>				
				</a>
			</div>
			<!-- Modal -->
			<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h3 class="modal-title" id="exampleModalLabel">上下架確認</h3>
						</div>
						<div class="modal-body">
							<h3>是否要將編號 <span id="no_text"></span> 的房型 <span id="state_text"></span> ?</h3>
						</div>
						<div class="modal-footer">
							<form method="post" action="<%=request.getContextPath()%>/room/RoomType">
								<input type="hidden" id="modal_type_no" name="type_no">
								<input type="hidden" id="modal_type_state" name="type_state">
				     			<input type="hidden" name="action"	value="change_state">
				     			<button type="button" class="btn btn-secondary cancel">取消</button>
								<button type="submit" class="btn btn-primary">確定</button>
			     			</form>
						</div>
					</div>
				</div>
			</div>	
			
			<div class="table-responsive">
				<table id="roomTypeTable" class="display default-table" style="min-width: 845px;">
					<thead>
						<tr>
							<th>房型編號</th>
							<th>房型圖片</th>
							<th>房型名稱</th>
							<th>容納人數</th>
							<th>金額</th>
							<th>房型大小</th>
							<th>床型</th>
							<th>上下架</th>
							<th>圖片</th>
							<th>修改</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="roomTypeVO" items="${roomTypeSvc.getAllRoomType()}">
						<tr>
							<td>${roomTypeVO.type_no}</td>
							<td>
								<div class="img-container">
								<c:choose>
									<c:when test="${roomImgSvc.getAllByType(roomTypeVO.type_no).size() > 0}">
										<img src="<%=request.getContextPath()%>/room/RoomImg?type_no=${roomTypeVO.type_no}&action=showFirstImages">
									</c:when>
									<c:otherwise>
										<img src="<%=request.getContextPath()%>/back_end/assets/img/noimages.png">
									</c:otherwise>
								</c:choose>
							</td>
							<td>${roomTypeVO.type_name}</td>
							<td>${roomTypeVO.type_qty}人</td>
							<td><fmt:formatNumber value="${roomTypeVO.type_price}"    pattern="$###,###"/></td>
							<td>${roomTypeVO.type_size} m<sup>2</sup></td>
							<td>${roomTypeVO.bed_size}</td>
							<td class="state">
			                    <input type="checkbox" ${roomTypeVO.type_state == true ? 'checked' : '' }>
			                    <label class="switches" data-no="${roomTypeVO.type_no}" data-state="${roomTypeVO.type_state}"></label>
							</td>
							<td>
								<form method="post" action="<%=request.getContextPath()%>/room/RoomType">
									<input type="hidden" name="type_no"  value="${roomTypeVO.type_no}">
			     					<input type="hidden" name="action"	value="getOne_For_Update">
									<button type="submit" class="btn btn-secondary btn-sm"><i class='bx bxs-image'></i>查看</button>
			     				</form>
							</td>
							<td>
								<form method="post" action="<%=request.getContextPath()%>/room/RoomType">
									<input type="hidden" name="type_no"  value="${roomTypeVO.type_no}">
			     					<input type="hidden" name="action"	value="getOne_For_Update">
									<button type="submit" class="btn btn-secondary btn-sm"><i class='bx bxs-pencil'></i>修改</button>
			     				</form>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>


		<%@ include file="/back_end/commonJS.file" %> <!-- 基本JS檔案 -->
		<script>
			$(document).ready(function() {
				$("#pagename").text("房型列表");
			    $("#roomTypeTable").DataTable( {
			    	"lengthMenu": [20, 10, 5],
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
			    $(".switches").click(function(){
			    	$("#staticBackdrop").modal('show');
			    	var data_no = $(this).data('no');
			    	var data_state = $(this).data('state');
			    	var state_text = (data_state == true) ? "下架" : "上架";
			        $('#no_text').text(data_no);
			    	$('#state_text').text(state_text);
			        $('#modal_type_no').val(data_no);
			        $('#modal_type_state').val(data_state);
			    });
			    $(".cancel").click(function(){
			    	$("#staticBackdrop").modal('hide');
			    });
			} );
		</script>
	</body>
</html>