<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
    <head>
        <%@ include file="../commonCSS.file" %> <!-- 基本CSS檔案 -->
    </head>
    <body>
		<%@ include file="../loading.file" %> <!-- loading -->
        <%@ include file="../header.file" %> <!-- Header -->

	<!-- Inner Banner -->
	<div class="inner-banner">
		<div class="container">
			<div class="inner-title">
				<ul>
					<li><a href="index.html">Home</a></li>
					<li><i class='bx bx-chevron-right'></i></li>
					<li>Sign In</li>
				</ul>
				<h3>Sign In</h3>
			</div>
		</div>
	</div>
	<!-- Inner Banner End -->

	<!-- 登入 -->
	<div class="sign-in-area ptb-70">
		<div class="container text-center user-all-form">
			<div class="contact-form">
				<div class="section-title text-center">
					<span class="sp-color">Sign In</span>
					<h2>登入</h2>
				</div>
				<form id="contactForm">
					<div class="row">
						<div class="col-lg-12">
							<div class="form-group">
								<input type="email" name="email" class="form-control" required
									data-error="請輸入正確的Email格式" placeholder="Email">
								<div class="help-block with-errors"></div>
							</div>
						</div>

						<div class="col-12">
							<div class="form-group">
								<input type="password" name="password" class="form-control"
									required data-error="請輸入密碼" placeholder="Password">
								<div class="help-block with-errors"></div>
							</div>
						</div>

						<div class="col-lg-11 col-sm-11 text-right">
							<a class="forget" href="#">忘記密碼</a>
						</div>

						<div class="col-lg-12 col-md-12 text-center">
							<button type="submit" class="default-btn btn-bg-one">登入
							</button>
						</div>

						<div class="col-12">
							<p class="account-desc">
								還不是會員嗎? <a href="signup.jsp">註冊</a>
							</p>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- Sign In Area End -->

	<%@ include file="../message.file" %> <!-- Message --> 
        <%@ include file="../footer.file" %> <!-- Footer -->      
        <%@ include file="../commonJS.file" %> <!-- 基本JS檔案 -->
    
        
    </body>
</html>
