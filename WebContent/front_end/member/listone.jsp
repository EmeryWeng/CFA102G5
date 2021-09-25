<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%
MemberClassVO memVO = (MemberClassVO) request.getAttribute("memVO");
%>
<!doctype html>
<html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <head>
        <%@ include file="/front_end/commonCSS.file" %> <!-- 基本CSS檔案 -->
    </head>
    <body>
		<%@ include file="/front_end/loading.file" %> <!-- loading -->
        <%@ include file="/front_end/header.file" %> <!-- Header -->
	<!-- Inner Banner -->
	<div class="inner-banner">
		<div class="container">
			<div class="inner-title">
				<ul>
					<li><a href="index.html">Home</a></li>
					<li><i class='bx bx-chevron-right'></i></li>
					<li><a href="index.html">singin</a></li>
					<li><i class='bx bx-chevron-right'></i></li>
					<li>MemberCentre</li>
				</ul>
				<h3>會員中心</h3>
			</div>
		</div>
	</div>
	<!-- Inner Banner End -->
<style>

footer.footer{
  margin-top: 20px;
}


 footer.footer2{ 
 width: 100%; 
 position: absolute; 
 bottom: 0 
 } 

.button-container form,
.button-container form div {
    display: inline; 
    text-align: center
}

</style>
<div>
				<form method="post" action="<%=request.getContextPath()%>/member/member.do" name="front1" >
					<div class="card-body d-flex justify-content-center">
						<div class="col-xl-8">
							<div class="row mb-2">
							    <label for="type_name" class="col-sm-3 col-form-label">Name</label>
							    <div class="col-sm-8">
							    	<div class="pk col-sm-8">${memVO.mem_name}</div>
							    </div>	
							</div>
							<div class="row mb-2">
							    <label for="type_name" class="col-sm-3 col-form-label">Sex</label>
							    <div class="col-sm-8">
							    	<div class="pk col-sm-8">${memVO.mem_sex == 1 ? '男' : '女'}</div>
							    </div>
							</div>
							<div class="row mb-2">
							    <label for="type_qty" class="col-sm-3 col-form-label">Email</label>
							    <div class="col-sm-8">
							    	<div class="pk col-sm-8">${memVO.mem_mail}</div>
							    </div>
							</div>
							<div class="row mb-2">
							    <label for="type_price" class="col-sm-3 col-form-label">Password</label>
							    <div class="col-sm-8">
							    	<div class="pk col-sm-8">${memVO.mem_password}</div>
							    </div>
							</div>
							<div class="row mb-2">
							    <label for="type_size" class="col-sm-3 col-form-label">Mobile</label>
							    <div class="col-sm-8">
							    	<div class="pk col-sm-8">${memVO.mem_mobile}</div>
							    </div>
							</div>
							<div class="row mb-2">
							    <label for="bed_size" class="col-sm-3 col-form-label">Address</label>
							    <div class="col-sm-8">
							    	<div class="pk col-sm-8">${memVO.mem_add}</div>
							    </div>
							</div>
							<div class="row mb-2">
							    <label for="type_info" class="col-sm-3 col-form-label">狀態</label>
							    <div class="col-sm-8">
							    	<div class="pk col-sm-8">${memVO.mem_state == true ? '正常' : '未啟用'}</div>
							    </div>
							</div>
						</div>
					</div>  
		       </form>
		       
		 <div class="d-grid gap-2 d-flex justify-content-center">
		 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do">
		 	<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
				修改
			</button>

			<!-- Modal -->
			<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h3 class="modal-title" id="exampleModalLabel">修改員工資料</h3>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>
						<div class="modal-body">
							內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
							<button type="button" class="btn btn-primary">儲存</button>
						</div>
					</div>
				</div>
			</div>
<!--             <input class="btn btn-primary" type="submit" value="修改資料"> -->
<%--             <input type="hidden" name="mem_no"  value="${memVO.mem_no}"> --%>
<!--             <input type="hidden" name="action" value="getOneForUpdate"> -->
         </FORM>
            
         <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/creditcard/creditcard.do" style="margin-bottom: 0px;">
            <input class="btn btn-primary" type="submit" value="付款資訊">
            <input type="hidden" name="mem_no"  value="${memVO.mem_no}">
            <input type="hidden" name="action" value="getallByMem_no">
         </FORM>
	   </div>
				
				



 <footer class="footer2">
 		<%@ include file="/front_end/message.file" %> <!-- Message --> 
        <%@ include file="/front_end/footer.file" %> <!-- Footer -->      
        <%@ include file="/front_end/commonJS.file" %> <!-- 基本JS檔案 -->
 
 </footer>

<script type="text/javascript">


</script>	
	

</body>

</html>
