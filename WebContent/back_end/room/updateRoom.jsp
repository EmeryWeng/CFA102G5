<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.room.model.*"%>
<%@ page import="com.roomType.model.*"%>

<jsp:useBean id="roomTypeSvc" scope="page" class="com.roomType.model.RoomTypeService" />
<%
  RoomVO roomVO = (RoomVO) request.getAttribute("roomVO");
%>

<!DOCTYPE html>
<html>
	<head>
		<%@ include file="/back_end/commonCSS.file" %> <!-- 基本CSS檔案 -->
		<style>
		.card {
			background-color: #f7f6f2;
			margin: 0 auto;
		}
		.imgs {
			background-color: #fff;
		}
		.col-form-label {
			font-size: 20px;
			color: #30504F;
			text-align: right;
			padding-top: 13px;
		}
		ul {
			display: inline-block;
		}
		.pk {
			font-weight: 600;
			color: #30504F;
			font-size: 20px;
			padding: 13px 0 0 30px;
		}
		.form-select {
			color: #30504F;
			font-size: 16px;
 			height: 50px;  
		}
		textarea.form-control {
			height: auto;
			font-size: 16px;
		}
		</style>
	</head>

	<body>
		<%@ include file="/back_end/loading.file" %> <!-- loading -->
		<%@ include file="/back_end/header.file" %> <!-- Header -->
		<%@ include file="/back_end/sidebar.file" %> <!-- sidebar -->
		
		<div class="main-content">
			<a class="btn btn-secondary light" href="<%=request.getContextPath()%>/back_end/room/listAllRoom.jsp">&lt; 回房間列表</a>
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
<!-- 				<font style="color:red">請修正以下錯誤:</font> -->
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color:red">${message}</li>
						</c:forEach>
					</ul>
			</c:if>
			<div class="card col-xl-9">
<!-- 				<div class="card-header"></div>                     -->
				<form method="post" action="<%=request.getContextPath()%>/room/Room" name="updateRoom">
					<div class="card-body d-flex justify-content-center">
						<div class="col-xl-8">
							<div class="row mb-2">
							    <label for="type_name" class="col-sm-3 col-form-label">房間編號</label>
							    <div class="pk col-sm-8">${roomVO.rm_no}</div>
							</div>
							<div class="row mb-2">
							    <label for="type_name" class="col-sm-3 col-form-label">房型</label>
							    <div class="col-sm-8">
							    	<select size="1" name="type_no">
<%-- 										<c:forEach var="roomTypeVO" items="${roomTypeSvc.all}"> --%>
<%-- 											<option value="${roomTypeVO.type_no}">${roomTypeVO.type_name}</option> --%>
<%-- 										</c:forEach> --%>
<%--  ${(roomVO.type_no==roomTypeVO.type_no)?'selected':'' } --%>
									</select>
							    </div>
							</div>
							<div class="row mb-2">
							    <label for="type_info" class="col-sm-3 col-form-label">房間資訊</label>
							    <div class="col-sm-8">
							    	<textarea name="type_info" rows="3" class="form-control" id="type_info">${roomVO.rm_info}</textarea>
							    </div>
							</div>
							<div class="row mb-2">
							    <label for="type_name" class="col-sm-3 col-form-label">入住人姓名</label>
							    <div class="col-sm-8">
							    	<input type="text" name="type_name" maxlength="10" class="form-control" value="${roomVO.name_title}">
							    </div>
							</div>
							<div class="row mb-2">
							    <label class="col-sm-3 col-form-label">房間狀態</label>
							    <div class="col-sm-8">
									<select class="mt-2 form-select" id="type_facility" name="type_state">
	                                    <option value="0" ${roomVO.rm_state == 0 ? 'selected' : '' }>已停用</option>
										<option value="1" ${roomVO.rm_state == 1 ? 'selected' : '' }>空房</option>
										<option value="2" ${roomVO.rm_state == 2 ? 'selected' : '' }>入住中</option>
	                                </select>
                                </div>	
							</div>
						</div>
					</div>
					<div class="mb-3 d-flex justify-content-center align-items-center">
						<input type="hidden" name="action" value="update">
						<input type="hidden" name="type_no" value="${roomTypeVO.type_no}">
						<button type="submit" class="btn btn-primary col-lg-3">修改</button>
                	</div>
				</form>
			</div>
		</div>
		
		<%@ include file="/back_end/commonJS.file" %> <!-- 基本JS檔案 -->
		<script>
			$(document).ready(function() {
				$("#pagename").text("修改房間");
			} );
		</script>
	</body>
</html>