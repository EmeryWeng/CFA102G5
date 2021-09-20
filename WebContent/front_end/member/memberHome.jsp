<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>

<!doctype html>
<html>
<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
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


body{
  margin: 0;
}

header.header, footer.footer{

  width: 1200px;
  margin: 0 auto;
  box-sizing: border-box;
}
header.header{
  margin-bottom: 20px;
  margin-top: 20px;
}
footer.footer{
  margin-top: 20px;
}


div.parent_container{
  width: 1200px;
  margin: 0 auto;
  font-size: 0;
  box-sizing: border-box;
}
div.parent_container aside.aside, div.parent_container main.main, div.parent_container div.sub_aside{
  display: inline-block;
  vertical-align: top;
  font-size: 1rem; /* 16px */
  box-sizing: border-box;
}
div.parent_container aside.aside{

  width: 200px;
  margin-right: 20px;
}

div.parent_container main.main{

  width: calc(100% - 200px - 20px - 200px - 20px);
}

div.parent_container div.sub_aside{
  width: 200px;

  margin-left: 20px;
}
 footer.footer2{ 
 width: 100%; 
 position: absolute; 
 bottom: 0 
 } 

</style>

<header class="header">
<div>
<h2>${memberSvc.getOneBymail(mem_mail).mem_name}</h2>您好
</div>
 
</header>

<div class="parent_container">
  <aside class="aside">
  
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do">
<input type="hidden" name="action" value="getOneBymail">
<input type="hidden" name="mem_mail" value="${mem_mail}">
<input type="submit" class="default-btn btn-bg-one" value="個人資訊"></FORM><br>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do">
<input type="hidden" name="action" value="getOneBymail">
<input type="hidden" name="mem_mail" value="${mem_mail}">
<input type="submit" class="default-btn btn-bg-one" value="會員活動"></FORM><br>

<FORM METHOD="post" ACTION="">
<input type="submit" class="default-btn btn-bg-one" value="住宿管理"></FORM><br>

<FORM METHOD="post" ACTION="">
<input type="submit" class="default-btn btn-bg-one" value="客服資訊"></FORM><br>
  </aside>

  <main class="main">
    這<br>
    裡<br>
    要<br>
    放<br>
    什<br>
    麼<br>
    才<br>
    好<br>
    = =<br>
    
  </main>
  
  <div class="sub_aside">
    第二個側邊欄
  </div>
</div>

<footer class="footer">
  footer
</footer>
 <footer class="footer2">
 		<%@ include file="/front_end/message.file" %> <!-- Message --> 
        <%@ include file="/front_end/footer.file" %> <!-- Footer -->      
        <%@ include file="/front_end/commonJS.file" %> <!-- 基本JS檔案 -->
 
 </footer>

<script type="text/javascript">


</script>	
	

</body>

</html>
