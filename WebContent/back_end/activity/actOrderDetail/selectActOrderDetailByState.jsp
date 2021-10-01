<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import ="com.activityOrderDetail.model.*" %>
<%@ page import ="com.activityOrder.model.*" %>
<%@ page import ="com.activitySession.model.*" %>
<%@ page import ="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
<title>Feliz-後台</title>
<%@ include file="/back_end/commonCSS.file"%><!-- 基本CSS檔案 -->
<link href="<%=request.getContextPath()%>/back_end/activity/css/act/selectAct.css" rel="stylesheet">
<style>
	div.queryByPage{
		position: absolute;
   		right: 50px;
    }
    table tbody tr td {
    	font-size: 20px;
    	text-align: center;
    	position:relative;
		top:-50px;
	}
    table tbody tr th{
    	font-size: 20px;
   	 	text-align: center;
   	 	position:relative;
		top:-50px;
    }
    table tbody tr{
    	position:relative;
		top:-30px;
    }
    table tbody .VOContent{
    	top:-110px;
    }
	table tr td span.cancel{
		color:#FF2D2D;
	}
	table tr td span.change{
		color:#EA7500;
	}
	.modal-content{
		left: -350px;
		width: 1400px;
	}
	.modal-body{
		width: 1400px;
	}
	.stateDiv .canceled{
		position: relative;
		top:-31px;
	}
	.stateDiv .changeDate{
		position: relative;
		left:80px;
    	top:-62px;
	}
	.stateDiv{
		position: relative;
    	width: 200px;
  		left: 150px;
	}
	
</style>
</head>
<body>
	<%-- 	<%@ include file="/back_end/loading.file"%> --%>
	<!-- loading -->
	<%@ include file="/back_end/header.file"%>
	<!-- Header -->
	<%@ include file="/back_end/sidebar.file"%>
	<!-- sidebar -->
	
	
<div class="main-content">
	<div class="table-responsive">
		<div class="updateAndSwitch">			
			<button type="button" class="btn btn-primary btn-xs switchBtn" data-bs-toggle="modal" data-bs-target="#staticBackdropSwitchActOrderDetailState">切換訂單明細狀態</button>
				<!-- 切換上下架的modal -->
			<jsp:include page="/back_end/activity/modal/actOrderDetail/switchActOrderDetailStateModal.jsp"/>
				<div class="stateDiv">
					<form method="post" action="<%=request.getContextPath()%>/activity/ActivityOrderDetail">
						<input type="hidden" name="action" value="canceled">
						<button type="submit" class="btn btn-danger btn-xs canceled">已取消</button>
					</form>
					<form method="post" action="<%=request.getContextPath()%>/activity/ActivityOrderDetail">
						<input type="hidden" name="action" value="changeDate">
						<button type="submit" class="btn btn-warning btn-xs changeDate">已改期</button>
					</form>
				</div>
		</div>
			<table class="table">
				<tr>
					<th>明細編號</th>
					<th>訂單編號</th>
					<th>場次編號</th>
					<th>實際參與人數</th>
					<th>活動場次單價</th>				
					<th>折價券金額</th>				
					<th>活動場次金額</th>				
					<th>活動訂單狀態</th>				
					<th>修改明細</th>
				</tr>
				
			<c:forEach var="actOrderDetailVO" items="${selectByState}">
				<tr class="VOContent">
					<th>${actOrderDetailVO.act_order_detail_no}</th>
					<td>
						<button type="button" class="btn light btn-secondary" data-bs-toggle="modal" data-bs-target="#staticBackdropActOrder${actOrderDetailVO.act_order_no}">${actOrderDetailVO.act_order_no}</button>
											<!--查看訂單的modal -->
						<jsp:include page="/back_end/activity/modal/actOrderDetail/selectActOrderModal.jsp">
							<jsp:param name="actOrderNo" value="${actOrderDetailVO.act_order_no}" />
						</jsp:include>					
					</td>
					<td>
						<button type="button" class="btn light btn-secondary" data-bs-toggle="modal" data-bs-target="#staticBackdropActSession${actOrderDetailVO.act_session_no}">${actOrderDetailVO.act_session_no}</button>
											<!--查看場次的modal -->
						<jsp:include page="/back_end/activity/modal/actOrderDetail/selectActSessionModal.jsp">
							<jsp:param name="actSessionNo" value="${actOrderDetailVO.act_session_no}" />
						</jsp:include>					
					</td>
					
					<td>${actOrderDetailVO.act_real_join_number}</td>
					<td>${actOrderDetailVO.act_order_price}</td>
					<td>${actOrderDetailVO.act_coupon_price}</td>
					<td>${actOrderDetailVO.act_price_total}</td>
					<td>
						<c:if test="${actOrderDetailVO.act_order_detail_state == 1}">
							<span class="paid">已付款</span>
						</c:if>
						<c:if test="${actOrderDetailVO.act_order_detail_state == 2}">
							<span class="cancel">已取消</span>
						</c:if>
						<c:if test="${actOrderDetailVO.act_order_detail_state == 3}">
							<span class="change">已改期</span>
						</c:if>
					</td>
					
					<td>
						<form method="post" action="<%=request.getContextPath()%>/activity/ActivityOrderDetail">
							<input type="hidden" name="action" value="updateActOrderDetail">
							<input type="hidden" name="updateActOrderDetailNo" value="${actOrderDetailVO.act_order_detail_no}">
							<input type="hidden" name="actSessionNo" value="${actOrderDetailVO.act_session_no}">
							<button type="submit" class="btn btn-primary">修改</button>
						</form>
					</td>				
				</tr>
				</c:forEach>
		</table>
	</div>
</div>
	
	<%@ include file="/back_end/commonJS.file"%>
	
	<script>
		window.addEventListener('load',function(){
			if(${selectByState.size()} == 0){
				alert("該分類下為空");
				location.href="<%=request.getContextPath()%>/back_end/activity/actOrderDetail/selectActOrderDetail.jsp";
			}
		});
		
		function createWhichPage(){
			let select = document.getElementById('switchActOrderDetailStateSelect');
			let value = select.options[select.selectedIndex].value; //option value
			let myForm = document.getElementById('switchActOrderDetailStateForm');
			let input = document.createElement('input');
			let goBackPage = 0;
			if(value % 5 == 0){
				goBackPage = value/4;
			}else{
				goBackPage = (value/4)+1;
			}
			input.setAttribute("type","hidden");
			input.setAttribute("name","whichPage");
			input.setAttribute("value",parseInt(goBackPage));
			myForm.appendChild(input)
			
			myForm.submit();
		}
		// ● 可在這更改header的標題，不寫也可以，但請變成空字串 
		$("#pagename").text("");
	</script>
</body>
</html>