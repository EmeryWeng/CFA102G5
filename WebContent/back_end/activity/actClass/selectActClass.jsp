<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>活動類別-查詢</title>
<%@ include file="/back_end/commonCSS.file"%><!-- 基本CSS檔案 -->
<style>
	label {
		font-size:20px;
		color:deeppink;
	}
	table.dataTable tbody td{
		color:#00AEAE;
		font-size:2rem;
	}
	table.dataTable thead th{
		color:#FF0080;
		font-size:2rem;
	}
	table.dataTable tbody td.upstate{
		color:#28FF28;
	}
	table.dataTable tbody td.downstate{
		color:#FF2D2D;
	}
	@media only screen and (max-width: 87.5rem)
			table.dataTable tbody td {
    	font-size: 2rem;
    	padding: 0.5rem 0.9375rem;
	}
	@media only screen and (max-width: 87.5rem)
			table.dataTable thead th {
    	font-size: 2rem;
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
	<!-- 	● 你的內容寫在main-content這個div內  -->
		<div class="col-12">
			<div class="card">
				<div class="card-header">
					<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop">新增活動類別</button>
							<!--新增的modal -->
					<jsp:include page="/back_end/activity/modal/addActClassModal.jsp"/>
				</div>
				<div class="card-body">
					<div class="table-responsive">
						<table id="example4" class="display" style="min-width: 845px">
							<thead>
								<tr>
									<th>活動類別編號</th>
									<th>活動類別名稱</th>
									<th>活動類別狀態</th>
									<th>修改活動類別</th>
									<th>切換上下架狀態</th>									
								</tr>
							</thead>
							<tbody>
						<c:if test="${not empty selectActClassList}">
							<c:forEach var="actClassVO" items="${selectActClassList}">
								<tr>
									<td>${actClassVO.act_class_no}</td>
									<td>${actClassVO.act_class_name}</td>
									<td class="${actClassVO.act_class_state == true ? 'upstate' : 'downstate' }">${actClassVO.act_class_state}</td>
									<td>
										<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop${actClassVO.act_class_no}">修改</button>
											<!--修改的modal -->
										<jsp:include page="/back_end/activity/modal/updateActClassModal.jsp">
											<jsp:param name="actClassNo" value="${actClassVO.act_class_no}"/>
											<jsp:param name="actClassName" value="${actClassVO.act_class_name}"/>
											<jsp:param name="actClassState" value="${actClassVO.act_class_state}"/>
										</jsp:include>
									</td>
									
									<td>
										<form id="switchActClassStateForm" method="post" action="<%=request.getContextPath()%>/activity/ActivityClass">
											<input type="hidden" name="actClassNo" value="${actClassVO.act_class_no}">
											<input type="hidden" name="actClassState" value="${actClassVO.act_class_state}">						
											<input type="hidden" name="action" value="switchActClassState">
											<button class="btn btn-danger" type="submit">切換</button>
										</form>
									</td>
								</tr>
							</c:forEach>
						</c:if>								
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
  

<%@ include file="/back_end/commonJS.file"%>
<!-- 基本JS檔案 -->


<script>

	$(document).ready(function(){
		$(document).on('shown.bs.modal',function(){
			 $(this).find('[autofocus]').focus();
		});
	});
	
	function sendRequest(formObject){
		let elements = formObject.elements;
		let inputElement = elements["actClassName"];
		let actClassName = inputElement.value;
		let regex = new RegExp('^[\u4E00-\u9FA5]');
		if(actClassName === ''){
			alert('欄位不能為空');
			return false;
		}
		if(!regex.test(actClassName)){
			alert('請輸入中文');
			return false;
		}
		alert('送出成功!');
		formObject.submit();
	}
	
	function checkStateColor(state){
		if(state === true){
			$("#actClassState").css("background-color","#28FF28");
		}else{
			$("#actClassState").css("background-color","#FF2D2D");
		}
	}
	
	function get(id){
		return document.getElementById(id);
	}
	
	// ● 可在這更改header的標題，不寫也可以，但請變成空字串 
	$("#pagename").text("說明");
</script>
</body>
</html>