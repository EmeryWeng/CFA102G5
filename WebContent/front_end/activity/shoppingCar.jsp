<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/front_end/commonCSS.file"%>
<!-- 基本CSS檔案 -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/activity/css/styleCar.css">

<style>
	.delete{
		font-size:2rem;
	}
	.delete:hover{
		cursor: pointer;
	}
</style>
</head>
<body>
	<%-- 	<%@ include file="/front_end/loading.file" %> <!-- loading --> --%>
	<%@ include file="/front_end/header.file"%>
	<!-- Header -->

	<div class="mt-5 mb-5 ptb-70 container">
		<section class="cart-area ptb-50">
			<div class="container">
				<div class="row">
					<div class="col-lg-8 col-md-12">
						<form>
							<div class="cart-table table-responsive">
<a href="<%=request.getContextPath()%>/front_end/activity/actList.jsp" style="color:#2894FF"><b style="font-size:2rem;">回商品列表</b></a>
								<table class="table table-bordered">
									<thead>
										<tr>
											<th scope="col">活動名稱</th>
											<th scope="col">開始日期</th>
											<th scope="col">開始時間</th>
											<th scope="col">人數</th>
											<th scope="col">金額</th>
										</tr>
									</thead>

									<tbody>
									<c:forEach var="map" items="${shoppingCar}">
										<input type="hidden" id="deleteIndex" value="${shoppingCar.indexOf(map)}">
											
										<tr class="top-class">
											
											<td class="product-name"><a href="<%=request.getContextPath()%>/activity/Activity?action=frontAct&actNo=${map['act_no']}">${map["act_name"]}</a></td>

											<td class="product-price"><span class="unit-amount">${map["act_date"]}</span></td>

											<td class="product-quantity">
												<span class="minus-btn">${map["act_session_start_time"]}</span>											
											</td>

											<td class="product-subtotal"><span class="subtotal-amount">${map["act_people_number"]}</span></td>
											
											<td class="product-subtotal"><span class="subtotal-amount actPrice">${map["act_price"] * map["act_people_number"]}</span></td>
											
											<td><i class='bx bx-trash delete'></i></td>
										</tr>
									</c:forEach>								
									</tbody>
								</table>
							</div>

							<div class="cart-buttons">
								<div class="row align-items-center">
									<div class="col-lg-7 col-sm-7 col-md-7">
										
									</div>

									<div class="col-lg-5 col-sm-5 col-md-5 text-right">
										<button type="button" id="deleteAll" class="btn btn-secondary" ${empty shoppingCar ? 'disabled':''}> 清除所有項目 <span></span>
										</button>
									</div>
								</div>
							</div>
						</form>
					</div>

					<div class="col-lg-4 col-md-12">
						<div class="cart-totals">
							<h3>Cart Totals</h3>

							<ul>
								<li>總金額<span id="totalPrice">$599.00</span></li>
							</ul>

							<button class="btn btn-primary">結帳(SessionList)</button>
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>


	<%@ include file="/front_end/message.file"%>
	<!-- Message -->
	<%@ include file="/front_end/footer.file"%>
	<!-- Footer -->
	<%@ include file="/front_end/commonJS.file"%>
	<!-- 基本JS檔案 -->
	
<script>
	window.addEventListener('load',function(){
		let array = document.getElementsByClassName('actPrice');
		let total = 0;
		for(let i=0;i<array.length;i++){
			total += parseInt(array[i].innerText);
		}
		document.getElementById("totalPrice").textContent = total;
	});
	
	let deleteRequest = null;
	$(".delete").click(function(){
		deleteRequest = $.ajax({
			url:"<%=request.getContextPath()%>/activity/ActivityOrder",
			type:"post",
			data:{
				action:"actShoppingCart",
				carAction:"delete",
				deleteIndex:$("#deleteIndex").val()
			},
			success:function(){
				location.reload();
				deleteRequest.abort();
			}
		});
	});
	
	
	let deleteAllRequest = null;
	$("#deleteAll").click(function(){
		Swal.fire({
			  title: '確定清除購物車?',
			  icon: 'warning',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: 'OK'
			}).then((result) => {
			  if (result.isConfirmed) {
				  deleteAllRequest = $.ajax({
						url:"<%=request.getContextPath()%>/activity/ActivityOrder",
						type:"post",
						data:{
							action:"actShoppingCart",
							carAction:"deleteAll"
						},
						success:function(){
							location.reload();
							deleteAllRequest.abort();
						}
					})
			    
			  }
			})
	});
</script>
</body>
</html>