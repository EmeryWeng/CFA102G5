<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>活動-新增</title>
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

	<jsp:useBean id="actImgService" class="com.activityImage.model.ActivityImageService" />
	<jsp:useBean id="actService" class="com.activity.model.ActivityService" />

	<div class="main-content">
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color:red;font-size:2.5rem;">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red;font-size:1.5rem;">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		<div class="row">
			<div class="col-lg-12">
				<div class="card">
					<div class="card-header">
						<h1 class="card-title" style="font-size:2rem;color:green;">新增活動圖片-表單</h1>
					</div>
					<div class="card-body">
						<div class="form-validation">
							<form class="needs-validation" method="post" action="<%=request.getContextPath()%>/activity/ActivityImage" id="addActImgForm" enctype="multipart/form-data">
								<div class="row">
									<div class="col-xl-6">
										<div class="mb-3 row">
											<label class="col-lg-4 col-form-label" for="actName">活動名稱
												<span class="text-danger">*</span>
											</label>
											<div class="col-lg-6">
												<div class="mb-3 row">
													<select name="actNoSelect" class="select" style="width:80%;">
														<c:forEach var="actNo" items="${actImgService.getAll().stream().map(act -> act.getAct_no()).distinct().toList()}" varStatus="selectedNumber">
															<c:forEach var="actVO" items="${actService.all}">
																<c:if test="${actNo == actVO.act_no }">
																	<option value="${actNo}">${actVO.act_name}</option>
																</c:if>
															</c:forEach>
														</c:forEach>
													</select>
												</div>
											</div>
										</div>
										<div class="mb-3 row">
											<label class="col-lg-4 col-form-label" for="actPrice">活動圖片
												<span class="text-danger">*</span>
											</label>
											<div class="col-lg-6">
												<input type="file" class="form-control" name="actImg"
													id="uploadImg" onchange="loadImg(event);">
												<img id="showImg" style="margin-top:3rem;">
											</div>
										</div>
									</div>
								</div>
								<div class="mb-3 row twoBtn" >
									<div class="col-lg-2">
										<input type="hidden" name="action" value="addActImg">
										<button type="button" class="btn btn-primary" style="margin-left:-50rem;width:10rem;height:5rem;font-size:2rem" onclick="check_file();">確定</button>
									</div>
									<div class="col-lg-2">
										<button type="reset" class="btn btn-secondary" style="margin-left:-45rem;">重填</button>
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
	function loadImg(e){
		let output = document.getElementById('showImg');
	    output.src = URL.createObjectURL(e.target.files[0]);
	    output.onload = function() {
	      URL.revokeObjectURL(output.src);
	    }
	}
	function check_file(){
		let file = document.getElementById('uploadImg').value;
		let type = file.substring(file.lastIndexOf('.')).toLowerCase();
		let myForm = document.getElementById('addActImgForm');

		if(file ===''){
			alert("請選擇檔案");
			
			return false;
		}
		
		if(type === '.jpg' || type === '.png' || type === '.gif'){
			myForm.submit();
		}else{
			alert('圖片不符合格式: .jpg .png .gif')
		}
		
	}
		// ● 可在這更改header的標題，不寫也可以，但請變成空字串 
		$("#pagename").text("說明");
</script>
</body>
</html>