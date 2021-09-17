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


/* footer.footer2{ */
/* width: 100%; */
/* position: absolute; */
/* bottom: 0 */
/* } */

.button-container form,
.button-container form div {
    display: inline; 
    text-align: center
}

</style>




<div class="info">
	 <div class="p-2 bg-light border">${memVO.mem_name}</div>
	  <div class="p-2 bg-light border">${memVO.mem_sex == 1 ? '男' : '女'}</div>
	  <div class="p-2 bg-light border">${memVO.mem_mail}</div>
	  <div class="p-2 bg-light border">${memVO.mem_password}</div>
	  <div class="p-2 bg-light border">${memVO.mem_mobile}</div>
	  <div class="p-2 bg-light border">${memVO.mem_img}</div>
	  <div class="p-2 bg-light border">${memVO.mem_add}</div>
	  <div class="p-2 bg-light border">${memVO.mem_state == true ? '正常' : '未啟用'}</div>
	  
	<div class="d-grid gap-2">
		 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do">
            <input class="btn btn-outline-secondary" type="submit" value="修改資料">
            <input type="hidden" name="mem_no"  value="${memVO.mem_no}">
            <input type="hidden" name="action" value="getOneForUpdate"></FORM>
            
            <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/creditcard/creditcard.do" style="margin-bottom: 0px;">
            <input class="btn btn-outline-secondary" type="submit" value="付款資訊">
            <input type="hidden" name="mem_no"  value="${memVO.mem_no}">
            <input type="hidden" name="action" value="getallByMem_no"></FORM>
	</div>
	
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
