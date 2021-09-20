<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import ="com.activity.model.*" %>
<%@ page import ="java.util.List" %>

<%
	ActivityService actSvc = new ActivityService();
	List<ActivityVO> list = actSvc.getAll();
	pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="/front_end/commonCSS.file"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/activity/css/style2.css">
<!-- 基本CSS檔案 -->
<style>
body {
	margin: 0px;
}
html {
	font-size: 62.5%;
}
div.actMain {
	position: relative;
	top: -28rem;
	right: -40rem;
}
img {
	max-width: 100%;
	box-sizing: border-box;
}
div.queryByPage{
	position: relative;
    left: 1.8rem;
}
a.btn-default{
	background-color:#00A600
}
.list-view .item-description .tour-item-title{
	color:#009100;
	font-size:2rem;
}
i.bx bx-map{
	
}
</style>
</head>
<body>
	<%-- <%@ include file="/front_end/loading.file" %> <!-- loading --> --%>
	<%@ include file="/front_end/header.file"%>
	<!-- Header -->

	<jsp:useBean id="actClassService" class="com.activityClass.model.ActivityClassService" />
	
	<div class="mt-5 mb-5 ptb-70 container" style="padding-top: 2rem; padding-bottom: 15rem;">
		<div id="wrapper">
			<div class="search-result">
				<div class="container">
					<div class="row">
						<!-- Sidebar Start -->
						<div class="col-lg-3 col-md-4 col-sm-12 col-xs-12 filter-section">
							<div class="sidebar-item sidebar-search-form" id="sidebar">
								<div class="search-price-filter" id="filtermodal" tabindex="-1"
									role="dialog" aria-labelledby="exampleModalLabel"
									aria-hidden="true">
									<div class="search-filter-body">
										<div class="search-category div-pad bd-bot">
											<h1 class="second-title">活動類別</h1>
											<c:forEach var="actClassVO" items="${actClassService.all}">
												<div class="custom-control custom-radio">
													<input type="radio" id="check${actClassVO.act_class_no}"
														name="actClass" class="custom-control-input"
														style="width: 2rem;"> <label
														class="custom-control-label"
														for="check${actClassVO.act_class_no}">${actClassVO.act_class_name}</label>
												</div>
											</c:forEach>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- Sidebar End -->
					</div>
				</div>
			</div>
		</div>
		<!--main -->
		<div class="col-lg-9 col-md-8 col-sm-12 col-xs-12 filter-result actMain">
              <div class="row filter-result-main list-view">
<%@ include file="/front_end/activity/pages/act/page1.file" %>
	<c:forEach var="actVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>"> 
                <div class="col-12 col-lg-12 col-md-12 col-sm-12 col-xs-12 filter-result-item">
<a href="xxx.html" class="tour-list-item">
                    <div class="tour-list-item-img">
                      <img src="<%=request.getContextPath()%>/activity/ActivityImage?action=actList&actNo=${actVO.act_no}">
                    </div>
                    <div class="item-description">
                      <div class="tours-left-desc">
                        
                        <p class="tour-item-title">${actVO.act_name}</p>
                        <div class="tour-item-review">
                          <span class="tour-review"><i class='bx bx-map' style="color:#F00078;margin-right:2rem;font-size:1.6rem;" >台灣 花蓮</i></span>
                          <span class="tour-review"><i class='bx bx-calendar' style="color:blue;font-size:1.6rem;">最早可預訂日:2021-10-01</i></span><br><br>
                          <span class="tour-review"><i class='bx bx-bell' style="color:red;font-size:1.6rem;">2天前免費取消</i></span>
                        </div><br>
                        <p class="tour-item-desc">${actVO.act_instruction}</p>
                      </div>
                      <div class="tour-footer">
                        
                        <div class="tour-price">
                          <span class="tour-act-price"><span class="act-price">$${actVO.act_price }</span></span>
                        </div>
                      </div>
               		</div>
</a>
                </div>
	</c:forEach>
<%@ include file="/front_end/activity/pages/act/page2.file" %> 
			</div>
		</div>
	</div>

	<%@ include file="/front_end/message.file"%>
	<!-- Message -->
	<%@ include file="/front_end/footer.file"%>
	<!-- Footer -->
	<%@ include file="/front_end/commonJS.file"%>
	<!-- 基本JS檔案 -->


	<script>
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
				icon : 'success', //常用的還有'error'
				title : '修改完成',
				showConfirmButton : false, //因為會自動關閉，所以就不顯示ok按鈕
				timer : 1000
			// 單位毫秒，1秒後自動關閉跳窗
			})
		}
	</script>

</body>
</html>
