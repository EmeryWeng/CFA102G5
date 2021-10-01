<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Feliz-後台</title>
<%@ include file="/back_end/commonCSS.file"%><!-- 基本CSS檔案 -->
<link href="<%=request.getContextPath()%>/back_end/activity/css/act/addAct.css" rel="stylesheet">
</head>
<body>
	<%-- 	<%@ include file="/back_end/loading.file"%> --%>
	<!-- loading -->
	<%@ include file="/back_end/header.file"%>
	<!-- Header -->
	<%@ include file="/back_end/sidebar.file"%>
	<!-- sidebar -->

	<jsp:useBean id="actClassService" class="com.activityClass.model.ActivityClassService" />

	<div class="main-content">
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color:red;font-size:25px;">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red;font-size:18px;">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		<div class="row">
			<div class="col-lg-12">
				<div class="card">
					<div class="card-header">
						<h2 class="card-title" style="font-size:28px;color:green;">新增活動-表單</h2>
					</div>
					<div class="card-body">
						<div class="form-validation">
							<form class="needs-validation" method="post" action="<%=request.getContextPath()%>/activity/Activity">
								<div class="row">
									<div class="col-xl-6">
										<div class="mb-3 row">
											<select name="actClassNoSelect" class="select">
												<c:forEach var="actClassNo" items="${addAct_actClassNoList}" varStatus="selectedNumber">
													<c:forEach var="actClassVO" items="${actClassService.all}">
														<c:choose>
															<c:when test="${not empty addAct_selected}">
																<c:if test="${actClassNo == actClassVO.act_class_no}">
																	<option value="${actClassNo}" ${selectedNumber.count == addAct_selected ? 'selected':''}>${actClassVO.act_class_name}</option>
																</c:if>
															</c:when>
															<c:otherwise>
																<c:if test="${actClassNo == actClassVO.act_class_no }">
																	<option value="${actClassNo}">${actClassVO.act_class_name}</option>
																</c:if>
															</c:otherwise>
														</c:choose>
													</c:forEach>
												</c:forEach>
											</select>
										</div>
										
										<div class="mb-3 row">
											<label class="col-lg-4 col-form-label" for="actName">活動名稱
												<span class="text-danger">*</span>
											</label>
											<div class="col-lg-6">
												<input type="text" class="form-control" name="actName"
													id="actName" value="${addAct_actVO.act_name}">
											</div>
										</div>
										<div class="mb-3 row">
											<label class="col-lg-4 col-form-label" for="actPrice">活動價格
												<span class="text-danger">*</span>
											</label>
											<div class="col-lg-6">
												<input type="text" class="form-control" name="actPrice"
													id="actPrice" value="${addAct_actVO.act_price}">
											</div>
										</div>
									</div>
									<div class="mb-3 row">
										<label class="col-lg-4 col-form-label actInstructionLabel" for="actInstruction">活動說明
											<span class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<textarea class="form-control" name="actInstruction" id="actInstruction" oninput="autoGrow(this)">${addAct_actVO.act_instruction}
											</textarea>
										</div>
									</div>
								</div>
								<div class="col-xl-6">
									<div class="mb-3 row">
										<label class="col-lg-4 col-form-label" for="actScheduleTime">活動行程時間
											<span class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control"
												name="actScheduleTime" id="actScheduleTime" value="${addAct_actVO.act_schedule_time}">
										</div>
									</div>
									<div class="mb-3 row">
										<label class="col-lg-4 col-form-label" for="actGatherLocation">活動集合地點<span
											class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control"
												id="actGatherLocation" name="actGatherLocation" value="${addAct_actVO.act_gather_location}">
										</div>
										<div class="mb-3 row">
											<label class="col-lg-4 col-form-label"
												for="actLocationLongitude">活動地點的經度<span
												class="text-danger">*</span>
											</label>
											<div class="col-lg-6">
												<input type="text" class="form-control"
													id="actLocationLongitude" name="actLocationLongitude"
													maxlength="11" value="${addAct_actVO.act_location_longitude}">
											</div>
										</div>
										<div class="mb-3 row">
											<label class="col-lg-4 col-form-label"
												for="actLocationLatitude">活動地點的緯度 <span
												class="text-danger">*</span>
											</label>
											<div class="col-lg-6">
												<input type="text" class="form-control"
													name="actLocationLatitude" maxlength="10"
													id="actLocationLatitude" value="${addAct_actVO.act_location_latitude}">
											</div>
										<input type="hidden" name="action" value="addAct">
											<div class="mb-3 row twoBtn" >
												<div class="col-lg-2">
													<button type="submit" class="btn btn-primary">確定</button>
												</div>
												<div class="col-lg-2">
													<button type="reset" class="btn btn-secondary btn">重填</button>
												</div>
											</div>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/back_end/commonJS.file"%>
	
	<script>
		function autoGrow(element){
			element.style.height = "5px";
			element.style.height = (element.scrollHeight)+"px";
		}
		
		// ● 可在這更改header的標題，不寫也可以，但請變成空字串 
		$("#pagename").text("");
	</script>
</body>
</html>