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
					<li>Sign Up</li>
				</ul>
				<h3>Sign Up</h3>
			</div>
		</div>
	</div>
	<!-- Inner Banner End -->

	<!-- 註冊 -->
	<div class="sign-up-area ptb-70">
		<div class="container text-center user-all-form">
			<div class="contact-form">
				<div class="section-title text-center">
					<span class="sp-color">Sign Up</span>
					<h2>註冊</h2>
				</div>
				<form id="contactForm">
					<div class="row">
						<div class="col-lg-12">
							<div class="form-group">
								<input type="text" name="name" class="form-control" required
									data-error="請輸入您的姓名" placeholder="Name">
							</div>
						</div>

						<div class="form-group form-check col-lg-3 col-sm-3 pl-5">
							<input type="radio" class="form-check-input" id="male"
								name="radio-stacked" required> <label
								class="form-check-label" for="male">男性</label>
						</div>
						<div class="form-group form-check col-lg-3 col-sm-3">
							<input type="radio" class="form-check-input" id="female"
								name="radio-stacked" required> <label
								class="form-check-label" for="female">女性</label>
						</div>

						<div class="form-check col-lg-12">
							<div class="form-group">
								<input type="email" name="email" id="" class="form-control"
									required data-error="請輸入正確的email格式" placeholder="Email">
							</div>
						</div>

						<div class="col-lg-12">
							<div class="form-group">
								<input type="text" name="text" id="" class="form-control"
									pattern="^09[0-9]{8}$" required data-error="請輸入10碼數字"
									placeholder="Phone">
								<div class="help-block with-errors"></div>
							</div>
						</div>

						<div class="col-12">
							<div class="form-group">
								<input class="form-control" type="password" name="password"
									pattern="[a-zA-Z0-9]{6,30}" required
									placeholder="Password (長度6-30字內，要包含數字、英文字)">
								<div class="help-block with-errors"></div>
							</div>
						</div>

						<div class="col-lg-12 col-md-12 text-center">
							<button type="submit" class="default-btn btn-bg-one">註冊
							</button>
						</div>

						<div class="col-12">
							<p class="account-desc">
								已經有帳號? <a href="signin.jsp">登入</a>
							</p>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- Sign Up Area End -->

	<%@ include file="../message.file" %> <!-- Message --> 
        <%@ include file="../footer.file" %> <!-- Footer -->      
        <%@ include file="../commonJS.file" %> <!-- 基本JS檔案 -->
    
        
    </body>
</html>
