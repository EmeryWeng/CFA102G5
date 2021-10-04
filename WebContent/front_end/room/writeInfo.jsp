<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.roomType.model.*"%>

<jsp:useBean id="roomTypeSvc" scope="page" class="com.roomType.model.RoomTypeService" />
<jsp:useBean id="memberSvc" class="com.member.model.MemberService" />
<jsp:useBean id="crdSvc" class="com.creditcard.model.CreditcardService" />

<!doctype html>
<html>
    <head>
        <%@ include file="/front_end/commonCSS.file" %> <!-- 基本CSS檔案 -->
        <style>
	input[type=checkbox], input[type=radio] {
		width: 20px;
	}
	/*             上方步驟區域 */
	.inner-title {
		padding: 1%;
		background-image: linear-gradient(to left, transparent, #ededed 80%);
	}
	
	.inner-title>div.row {
		margin-left: 3%;
		margin-bottom: 1%;
	}
	
	ul.check {
		list-style-type: none;
	}
	
	ul.check li {
		font-size: 16px;
		display: inline;
		padding: 0 20px;
	}
	
	.check i.bx-check-circle {
		padding: 0 5px;
		color: #B38C61;
	}
	
	.outer_circle_step {
		background-color: #fff;
		width: 38px;
		height: 38px;
		border-radius: 50%;
		border: 1px solid #666;
		color: #adadad;
		margin-right: 6px;
	}
	
	.step-item {
		font-weight: 700;
		margin: 1% 2%;
	}
	
	.step_item_label {
		color: #555;
		padding-right: 5px;
		padding-left: 5px;
		letter-spacing: .7px;
		max-width: 130px;
	}
	
	.step-item:hover .step_item_label {
		color: #996A4D;
	}
	
	.step_item_label_not_select {
		color: #adadad;
	}
	
	.step-item:hover .step_item_label_not_select {
		color: #adadad;
	}
	
	.flex_center {
		display: flex;
		flex-wrap: wrap;
		align-items: center;
		justify-content: center;
	}
	
	.inner_circle_step {
		font-size: 16px;
		width: 32px;
		height: 32px;
		border-radius: 50%;
	}
	
	.shapeborder_selected_out {
		border: 1px solid #d0af6d;
		color: #fff;
	}
	
	.shapeborder_selected_in {
		background-color: #d0af6d;
	}
	
	.not_select {
		cursor: default;
	}
	
	body {
		/*  			  	background-image: linear-gradient(rgba(228, 214, 196, 0.1), rgba(228, 214, 196, 0.3)),  */
		/*                    url(../assets/img/color_bg.png);  */
		/*                 background-image: linear-gradient(170deg, transparent, rgba(228, 214, 196, 0.5)); */
		background-color: #f7f9fa;
	}
	
	p {
		display: inline;
	}
	.room-area {
		padding-top: 1%;
	}
	.room-card-area {
		background-color: #fff; 
		padding: 2% 5%;
	}
	.room-card-area>div>div form div.checkbox-form>div {
		margin: 100px;
	}
	</style>
    </head>
    <body>
		<%@ include file="/front_end/loading.file" %> <!-- loading -->
        <%@ include file="/front_end/header.file" %> <!-- Header -->
		
		<div class="mt-5 mb-5 pt-20 container">
			<div class="inner-title">
				<div>
					<ul class="check">
						<li><i class='bx bx-check-circle'></i>入住1天前免費取消</li>
						<li><i class='bx bx-check-circle'></i>訂房皆含早餐</li>
						<li><i class='bx bx-check-circle'></i>送活動折價券</li>
					</ul>
				</div>
				<div class="row">
					<div class="step-item flex_center">
	                    <div class="outer_circle_step flex_center shapeborder_selected_out">
	                        <div class="flex_center inner_circle_step shapeborder_selected_in">
	                            1</div>
	                    </div>
	                    <a href="<%=request.getContextPath()%>/front_end/room/roomList.jsp" class="step_item_label">搜尋</a>
	                </div>
                
	                <div class="step-item flex_center">
	                    <div class="outer_circle_step flex_center shapeborder_selected_out">
	                        <div class="flex_center inner_circle_step shapeborder_selected_in">
	                            2</div>
	                    </div>
	                	<a href="<%=request.getContextPath()%>/room/RoomType?type_no=${type_no}&action=getOneForShow" class="step_item_label">選擇日期</a>
	                </div>
                
	                <div class="step-item flex_center not_select">
	                    <div class="outer_circle_step flex_center shapeborder_selected_out">
	                        <div class="flex_center inner_circle_step shapeborder_selected_in">
	                            3</div>
	                    </div>
	                    <a class="step_item_label">填寫資料/付款</a>
	                </div>
                
	                <div class="step-item flex_center not_select">
	                    <div class="outer_circle_step flex_center">
	                        <div class="flex_center inner_circle_step">
	                            4</div>
	                    </div>
	                    <a class="step_item_label step_item_label_not_select">確認</a>
	                </div>
				</div>
			</div>
			
			<div class="room-card-area">
            <div class="row">
                <!-- **左邊 -->
                <div class="col-lg-6 col-12">
                    <form action="#">
                        <div class="checkbox-form">

                            <h3 class="title">訂購人資料</h3>
                            <p>已帶入您的會員資料，若訂購人與會員不同請再修改</p>

                            <div class="row">
                                <div class="col-md-6">
                                    <label>稱謂 <span class="required">*</span></label>
                                    <select class="myniceselect nice-select wide rounded-0">
                                        <option value="先生" ${memberSvc.getOneBymail(mem_mail).mem_sex == 1 ? 'selected' : '' }>先生</option>
                                        <option value="女士" ${memberSvc.getOneBymail(mem_mail).mem_sex == 2 ? 'selected' : '' }>女士</option>                           
                                    </select>
                                </div>

                                <div class="col-md-8">
                                    <div class="checkout-form-list">
                                        <label>姓名 <span class="required">*</span></label>
                                        <input type="text" value="${memberSvc.getOneBymail(mem_mail).mem_name}">
                                    </div>
                                </div>

                                <div class="col-md-12">
                                    <div class="checkout-form-list">
                                        <label>電話 <span class="required">*</span></label>
                                        <input type="text" maxlength="10" placeholder="請輸入10碼行動電話" value="${memberSvc.getOneBymail(mem_mail).mem_mobile}">
                                    </div>
                                </div>

                                <div class="col-md-12">
                                    <div class="checkout-form-list">
                                        <label>e-mail <span class="required">*</span></label>
                                        <input type="email" placeholder="請輸入有效的email" value="${mem_mail}">
                                    </div>
                                </div>
                            </div>

                            <h3 class="title">付款資料</h3>
                            <div class="col-12">
                            	<c:forEach var="crdVO" items="${crdSvc.getallByMem_no(memberSvc.getOneBymail(mem_mail).mem_no)}">
                                    <div>
                                        <input type="radio" name="creditcard"
                                            id="${crdVO.crd_no}">
                                        <label for="${crdVO.crd_no}">
                                            ${crdVO.crd_num}
                                        </label>
                                    </div>
                              	</c:forEach>
                            </div>

							<h3 class="title">住客偏好</h3>
                            <div class="order-notes">                                
                                <div class="checkout-form-list checkout-form-list-2">
                                	<input type="checkbox" id="note1">
                                	<label for="note1"> 禁菸房</label>
                                </div>
                                <div class="checkout-form-list checkout-form-list-2">
                                	<input type="checkbox" id="note2">
                                	<label for="note2"> 靠近電梯</label>
                                </div>
                                <div class="checkout-form-list checkout-form-list-2">
                                    <label>備註</label>
                                    <textarea id="checkout-mess" cols="60" rows="3"
                                        placeholder="房間偏好需視實際住房情況而定。我們將會竭盡所能滿足您的要求"></textarea>
                            	</div>
                            </div>

							<div >
								<div class="form-group">
									<input type="checkbox" id="cancelRules">
	                                <label for="cancelRules">
										我同意取消政策
	                                </label>
								</div>
							</div>
						</div>
                    </form>
                </div>

                <!-- **右邊 -->
                <div class="col-lg-6 col-12">
                    <!-- Your Order Area Start -->
                    <div class="your-order-area border">

                        <!-- Title Start -->
                        <h3 class="title">訂購明細</h3>
                        <!-- Title End -->

                        <!-- Your Order Table Start -->
                        <div class="your-order-table table-responsive">
                            <div>${start_date} 到 ${end_date}</div>
                            <div>幾晚阿 | ${qty} 客房</div>

                            <div>
                                <div>${roomTypeSvc.getOneRoomType(type_no).type_name}</div>
                                <div>每晚單價 <fmt:formatNumber value="${roomTypeSvc.getOneRoomType(type_no).type_price}" pattern="NT$ ###,###" /></div>
                            </div>
                            <div>
                                <div></div>
                                <div>總金額 $ 12,800</div>
                            </div>
                        </div>

                        <div class="col-12">
	                        <button type="submit" class="btn btn-primary col-5">付款</button>
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
		// ● header顯示目前在哪個區塊，"活動"的頁面請將nth-child(1)改成2，"美食"的頁面改成3，其他人這行可刪掉
        $(`.nav-item:nth-child(1)>a`).attr('class', 'active');
        
		// ● 以下是sweetalert2的範例也可以刪除
        // 簡易版
        function addToCart() {
					// 簡易版；標題,內文,圖示
        	swal.fire('已加入購物車','快到購物車內結帳吧！','success');
        }
     	// 自動關閉版
        function autoClose() {
			swal.fire({
			  icon: 'success',  //常用的還有'error'
			  title: '已加入購物車',
			  showConfirmButton: false, //因為會自動關閉，所以就不顯示ok按鈕
			  timer: 1000 // 單位毫秒，1秒後自動關閉跳窗
			})
		}
        
        </script>
        
    </body>
</html>
