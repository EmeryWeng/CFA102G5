<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Feliz-後台</title>
<%@ include file="/front_end/commonCSS.file" %> <!-- 基本CSS檔案 -->
<link href="<%=request.getContextPath()%>/front_end/activity/css/act/addAct.css" rel="stylesheet">

<style>
.col-lg-6 {
	position: relative;
	left: -1rem;
	margin-top: 1rem;
}
</style>
</head>
<body>
<%-- 	<%@ include file="/front_end/loading.file" %> <!-- loading --> --%>
        <%@ include file="/front_end/header.file" %> <!-- Header -->
	
	<jsp:useBean id="actOrderService" class="com.activityOrder.model.ActivityOrderService" />
	<jsp:useBean id="memberService" class="com.member.model.MemberService" />
	
	<div class="mt-5 mb-5 ptb-70 container" style="padding-top: 2rem; padding-bottom: 15rem;">

		<div class="row">
			<div class="col-lg-12">
				<div class="card allForm">
					<div class="card-header">
						<h1 class="card-title" style="font-size: 3rem; color: green;">訂購人資料</h1>
					</div>
					<div>
						<label class="col-form-label" for="sameAsMember">同會員</label>
						<input type="checkbox" id="sameAsMember">
					</div>
					<div class="card-body">
						<div class="form-validation">
							<form id="checkoutForm" class="needs-validation" method="post" action="<%=request.getContextPath()%>/activity/Activity">
								<div class="row">
									<div class="col-xl-6">
										<div class="mb-3 row">
											<label class="col-lg-4 col-form-label">稱謂 <span class="text-danger">*</span></label>
											<select name="actOrderTitleSelect" class="select" id="actOrderTitleSelect" style="position: relative; top: 1.5rem;">
												<c:forEach var="orderTitle" items="${actOrderService.getAll().stream().map(order -> order.getAct_order_title()).toList()}">
													<option value="${orderTitle}">${orderTitle}</option>
												</c:forEach>
											</select>
										</div>

										<div class="mb-3 row">
											<label class="col-lg-4 col-form-label" for="orderName">姓名
												<span class="text-danger">*</span>
											</label>
											<div class="col-lg-6">
												<input type="text" class="form-control" name="orderName"
													id="orderName" max="5" value="${addAct_actVO.act_name}">
											</div>
										</div>
										<div class="mb-3 row">
											<label class="col-lg-4 col-form-label" for="orderPhone">電話
												<span class="text-danger">*</span>
											</label>
											<div class="col-lg-6">
												<input type="tel" id="orderPhone" class="form-control"
													pattern="[0-9]{10}" value="${addAct_actVO.act_price}">
											</div>
										</div>
										<div class="mb-3 row">
											<label class="col-lg-4 col-form-label" for="orderEmail">Email
												<span class="text-danger">*</span>
											</label>
											<div class="col-lg-6">
												<input type="email" name="orderEmail" id="orderEmail"
													class="form-control">
											</div>
										</div>
										<div class="mb-3 row">
											<label class="col-lg-4 col-form-label" for="orderCreditCard">信用卡號
												<span class="text-danger">*</span>
											</label>
											<div class="col-lg-6">
												<input type="tel" id="orderCreditCard" pattern="[0-9]{16}" title="請輸入信用卡16位數字" placeholder="請輸入16位數字" name="orderCreditCard" class="form-control">
											</div>
										</div>
									</div>
								</div>
								<input type="hidden" name="action" value="checkout">
								<div class="mb-3 row twoBtn">
									<div class="col-lg-2">
										<button type="button" class="btn btn-primary" onclick="check();">確定</button>
									</div>
									<div class="col-lg-2">
										<button type="reset" class="btn btn-secondary btn">重填</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<%@ include file="/front_end/message.file" %> <!-- Message --> 
    <%@ include file="/front_end/footer.file" %> <!-- Footer -->      
    <%@ include file="/front_end/commonJS.file" %> <!-- 基本JS檔案 --> 
    
	<script>
	let currentRequest = null;
		$("#sameAsMember").change(function(){
			if(this.checked){
				currentRequest = $.ajax({
					url:"<%=request.getContextPath()%>/activity/ActivityOrder",
					type:"POST",
					data:{
						action:'sameAsMember',		
					},
					success:function(response){
						
						let obj = JSON.parse(response);
						
						console.log(obj);
						
						$("#actOrderTitleSelect select").val(obj.mem_title);
						$("#orderName").val(obj.mem_name);
						$("#orderPhone").val(obj.mem_phone);
						$("#orderEmail").val(obj.mem_email);
						$("#orderCreditCard").val(obj.mem_creditCard);
					}
				});
			}else{
				$("#actOrderTitleSelect select").val('');
				$("#orderName").val('');
				$("#orderPhone").val('');
				$("#orderEmail").val('');
				$("#orderCreditCard").val('');
			}
		});
		
		function check(){
			let name = document.getElementById('orderName').value;
			let phone = document.getElementById('orderPhone').value;
			let email = document.getElementById('orderEmail').value;
			let creditCard = document.getElementById('orderCreditCard').value;
			let form = document.getElementById('checkoutForm');
			if(name === ''){
				autoClose();
				name.focus();
				return false;
			}
			if(phone === ''){
				autoClose();
				phone.focus();
				return false;
			}
			if(creditCard === ''){
				autoClose();
				creditCard.focus();
				return false;
			}
			
		}
		
		function autoClose() {
			swal.fire({
				icon : 'error', //常用的還有'error'
				title : '該欄位請勿空白',
				showConfirmButton : false, //因為會自動關閉，所以就不顯示ok按鈕
				timer : 1000
			// 單位毫秒，1秒後自動關閉跳窗
			})
		}
		// ● 可在這更改header的標題，不寫也可以，但請變成空字串 
		$("#pagename").text("");
	</script>
</body>
</html>
