<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<%@ include file="/front_end/commonCSS.file"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/activity/css/style2.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/activity/css/fotorama.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back_end/activity/datetimepicker/jquery.datetimepicker.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/activity/css/innerAct.css" />

<!-- 基本CSS檔案 -->

</head>
<body>
	<%-- <%@ include file="/front_end/loading.file" %> <!-- loading --> --%>
	<%@ include file="/front_end/header.file"%>
	<!-- Header -->

	<jsp:useBean id="actImgService" class="com.activityImage.model.ActivityImageService" />
	
	
<div class="mt-5 mb-5 ptb-70 container" style="padding-top: 2rem; padding-bottom: 15rem;">
	<div id="wrapper">
      <div class="tour-details-main">
        <div class="container">
          <div class="row">
            <div class="col-lg-9 col-md-8 col-sm-12 col-xs-12">
              <!-- Tour Slider Start  輪播  -->
              <div class="tour-gallery actCarousel">
                <div class="fotorama" data-width="100%" data-fit="scaledown" data-nav="thumbs" data-thumbwidth="128" data-thumbheight="128" data-allowfullscreen="true" data-loop="true" data-autoplay="7000" data-keyboard="true">
            <c:forEach var="actImgVO" items="${actImgService.getActImageByActNo(actVO.getAct_no())}">    
                  <img src="<%=request.getContextPath()%>/activity/ActivityImage?action=innerAct&actImgNo=${actImgVO.act_img_no}">
            </c:forEach>    
                </div>
              </div>
              <!-- Tour Slider End -->
              
<!--輪播下的內容 -->
              <div class="read-more collapsed" style="margin-top:-3rem;">
                <h1 class="read-more-title">臺灣花蓮 |	<span style="color:blue;">${actVO.act_name}</span></h1>
                	<div><i class='bx bx-map' style="color:#F00078;font-size:1.8rem;" >${actVO.act_location}</i></div>
                	<br><br>
                	<div><i class='bx bx-globe'>中文	導覽</i><i class='bx bx-bell'>2天前可免費取消</i></div>
                	<br>
                <div class="read-more__content">
                  <p><h5>${actVO.act_instruction}</h5></p>               
                </div>
              </div>
<!--輪播下內容結束  -->
<!--右側開始 -->
	<div class="col-lg-3 col-md-4 col-sm-12 col-xs-12">
		<div class="tour-booking" id="tourbooking">
                <!-- Tour Booking Start -->
 			<div class="actPrice">
				<div>
                    <h3>TWD	${actVO.act_price }</h3>
				</div>
				<div>
					<a href="#act_session" class="chooseSession">選擇場次</a>
				</div>
            </div>
		</div>
	</div>
                
<!--右側結束 -->
<br><br><br>
<div class="col-lg-3 col-md-4 col-sm-12 col-xs-12">
	<div class="actSession">
		<form>
		<div>
			<label><h3>選擇日期</h3></label>
			<p><b>請選擇出發日期</b></p>
			<input type="text" id="act_date" class="actSession">
		
		</div>
	
	<div class="actSessionStartTime">
	<b style="position: relative;left:-17rem;top:-0.5rem;">場次時間:</b>
		<select name="actSessionStartTimeSelect" id="actSessionStartTimeSelect">
			<c:forEach var="actSessionVO" items="${actSessionByActNo}">
				<option value="${actSessionVO.act_session_no}">${actSessionVO.act_session_start_time}</option>
			</c:forEach>
		</select>
		<label for="actPeopleNumber" class="actPeopleNumberLabel"><b>人數:</b></label>
		<input type="text" readonly id="actPeopleNumber" name="actPeopleNumber" class="actPeopleNumberInput" value="1">
		<input type="hidden" id="totalPeople" value="${actPeopleNumber}">
		<i class='bx bx-plus-circle plusIcon' id="actPricePlusBtn" onclick="plus();"></i>
		<i class='bx bx-minus-circle minusIcon' id="actPriceMinusBtn" onclick="minus();"></i>
	</div>
	</form>
	</div>
</div>

            </div>
		  </div>
	    </div>
	  </div>
	</div>           
</div>

	<%@ include file="/front_end/message.file"%>
	<!-- Message -->
	<%@ include file="/front_end/footer.file"%>
	<!-- Footer -->
	<%@ include file="/front_end/commonJS.file"%>
	<!-- 基本JS檔案 -->

<script src="<%=request.getContextPath()%>/front_end/activity/js/fotorama.js"></script>
<script src="<%=request.getContextPath()%>/front_end/activity/datetimepicker/jquery.datetimepicker.full.js"></script>
	
<script>
	$.datetimepicker.setLocale('zh');
	$('#act_date').datetimepicker({
		theme : '',
		timepicker : false,
		step : 1,
		format : 'Y-m-d',
		value : '${actSessionByActNo.stream().findFirst().get().getAct_session_start_date()}',
		minDate:'${actSessionByActNo.stream().findFirst().get().getAct_session_start_date()}',
		maxDate:'${actSessionByActNo.stream().findFirst().get().getAct_session_start_date()}',
	});
	
	let currentRequest = null;
	$('#actSessionStartTimeSelect').on('change',function(){
		currentRequest = $.ajax({
			url:"<%=request.getContextPath()%>/activity/ActivityOrderDetail",
			type:"POST",
			data:{
				action:'checkSessionPeopleNumber',
				sessionNo:$("#actSessionStartTimeSelect option:selected").val(),			
			},
			success:function(response){
				$("#totalPeople").val(response);
				currentRequest.abort();
			}
		});
	});
	document.getElementById('actPriceMinusBtn').disabled = true;
	document.getElementById('actPriceMinusBtn').setAttribute("style", "color:gray;");
	
	function plus(){
		let myInput = document.getElementById('actPeopleNumber');
		let totalPeople = parseInt(document.getElementById('totalPeople').value);
		let val = parseInt(myInput.value);
		let total = totalPeople + val;
		
		let plusBtn = document.getElementById('actPricePlusBtn');
		let minusBtn = document.getElementById('actPriceMinusBtn');
		
		minusBtn.disabled = false;
		minusBtn.removeAttribute("style", "color:gray;");
		
		if(total < 10){
			myInput.value = ++val;
		}else{
			autoClose();
			plusBtn.disabled = true;
			plusBtn.setAttribute("style", "color:gray;");
			return false;
		}		
	}
	
	function minus(){
		let myInput = document.getElementById('actPeopleNumber');
		let val = parseInt(myInput.value);
		let plusBtn = document.getElementById('actPricePlusBtn');
		let minusBtn = document.getElementById('actPriceMinusBtn');
		
		plusBtn.disabled = false;
		plusBtn.removeAttribute("style", "color:gray;");
		
		if(val > 1){
			myInput.value = --val;
		}else{
			minusBtn.disabled = true;
			minusBtn.setAttribute("style", "color:gray;");
			myInput.value = val;
			return false;
		}		
	}
	
	
	$("#queryBtn").click(function(){
		$("#queryForm").submit();
	});
	// ● header顯示目前在哪個區塊，"活動"的頁面請將nth-child(1)改成2，"美食"的頁面改成3，其他人這行可刪掉
	$(`.nav-item:nth-child(2)>a`).attr('class', 'active');
	
	// ● 以下是sweetalert2的範例也可以刪除
	// 簡易版
	function addToCart() {
		// 簡易版；標題,內文,圖示
		swal.fire('已加入購物車', '快到購物車內結帳吧！', 'success');
	}
	// 自動關閉版
	function autoClose() {
		swal.fire({
			icon : 'error', //常用的還有'error'
			title : '人數已達上限',
			showConfirmButton : false, //因為會自動關閉，所以就不顯示ok按鈕
			timer : 1000
		// 單位毫秒，1秒後自動關閉跳窗
		})
	}
</script>

</body>
</html>
