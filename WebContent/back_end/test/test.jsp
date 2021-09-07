<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<!DOCTYPE html>
	<html lang="en">

	<head>
		<%@ include file="../commonCSS.file" %>
			<!-- 基本CSS檔案 -->
	</head>

	<body>
		<%@ include file="../loading.file" %>
			<!-- loading -->
			<%@ include file="../header.file" %>
				<!-- Header -->
				<%@ include file="../sidebar.file" %>
					<!-- sidebar -->


					<div class="main-content">
<!-- 						● 你的內容寫在main-content這個div內  -->
						
						<h2>色碼</h2>
						<span class="btn btn-square" style="background: #30504F;color: #FFF">#30504F</span>
						<span class="btn btn-square" style="background: #996A4D;color: #FFF">#996A4D</span>
						<span class="btn btn-square" style="background: #B38C61;color: #FFF">#B38C61</span>
						<span class="btn btn-square" style="background: #E4D6C4;color: #FFF">#E4D6C4</span>
						<span class="btn btn-square" style="background: #F7F6F2;color: #999">#F7F6F2</span>
						<span class="btn btn-square" style="background: #8FC2C2;color: #FFF">#8FC2C2</span>
						<span class="btn btn-square" style="background: #D1E6E6;color: #999">#D1E6E6</span>

						<hr><br><br>
					
						<h2>button</h2>
						<h3>至少要加上兩個class名稱，要什麼就直接複製去用</h3>
						<button type="button" class="btn btn-secondary">取消</button>
						<button type="button" class="btn btn-primary">確定</button>

						<hr><br>

						<h3>普通版</h3>
						<button type="button" class="btn btn-primary">primary</button>
						<button type="button" class="btn btn-secondary">secondary</button>
						<button type="button" class="btn btn-success">success</button>
						<button type="button" class="btn btn-danger">danger</button>
						<button type="button" class="btn btn-warning">warning</button>
						<button type="button" class="btn btn-info">info</button>
						<button type="button" class="btn btn-light">light</button>
						<button type="button" class="btn btn-dark">dark</button>

						<hr><br>

						<h3>淺色版</h3>
						<button type="button" class="btn light btn-primary">primary</button>
						<button type="button" class="btn light btn-secondary">secondary</button>
						<button type="button" class="btn light btn-success">success</button>
						<button type="button" class="btn light btn-danger">danger</button>
						<button type="button" class="btn light btn-warning">warning</button>
						<button type="button" class="btn light btn-info">info</button>
						<button type="button" class="btn light btn-light">light</button>
						<button type="button" class="btn light btn-dark">dark</button>

						<hr><br>

						<h3>按鈕尺寸：加尺寸的class</h3>
						<button type="button" class="btn btn-primary btn-lg">btn-lg</button>
						<button type="button" class="btn btn-primary">預設</button>
						<button type="button" class="btn btn-primary btn-sm">btn-sm</button>
						<button type="button" class="btn btn-primary btn-xs">btn-xs</button>
						<button type="button" class="btn btn-primary btn-xxs">btn-xxs</button>

						<hr><br>

						<h3>方按鈕：加btn-square</h3>
						<button type="button" class="btn btn-square btn-primary">primary</button>
						<button type="button" class="btn btn-square btn-secondary">secondary</button>
						<button type="button" class="btn btn-square btn-success">success</button>
						<button type="button" class="btn btn-square btn-danger">danger</button>
						<button type="button" class="btn btn-square btn-warning">warning</button>
						<button type="button" class="btn btn-square btn-info">info</button>
						<button type="button" class="btn btn-square btn-light">light</button>
						<button type="button" class="btn btn-square btn-dark">dark</button>

						<hr><br>

						<h3>右邊icon</h3>
						<button type="button" class="btn btn-primary">Add to cart <span class="btn-icon-end">
								<i class='bx bxs-cart'></i></span>
						</button>
						<button type="button" class="btn btn-info">Add to wishlist <span class="btn-icon-end">
								<i class='bx bxs-cart'></i></span>
						</button>
						<button type="button" class="btn btn-danger">Remove <span class="btn-icon-end">
								<i class='bx bxs-cart'></i></span>
						</button>
						<button type="button" class="btn btn-secondary">Sent message <span class="btn-icon-end">
								<i class='bx bxs-cart'></i></span>
						</button>
						<button type="button" class="btn btn-warning">Add bookmark <span class="btn-icon-end">
								<i class='bx bxs-cart'></i></span>
						</button>
						<button type="button" class="btn btn-success">Success <span class="btn-icon-end">
								<i class='bx bxs-cart'></i></span>
						</button>

						<hr><br>

						<h3>左邊icon</h3>
						<button type="button" class="btn btn-rounded btn-primary"><span
									class="btn-icon-start text-primary"><i class='bx bxs-cart'></i>
								</span>Buy</button>
						<button type="button" class="btn btn-rounded btn-info"><span
									class="btn-icon-start text-info"><i class='bx bx-plus'></i>
								</span>Add</button>
						<button type="button" class="btn btn-rounded btn-danger"><span
									class="btn-icon-start text-danger"><i class='bx bx-mail-send'></i>
								</span>Email</button>
						<button type="button" class="btn btn-rounded btn-secondary"><span
									class="btn-icon-start text-secondary"><i class='bx bxs-share'></i>
								</span>Share</button>
						<button type="button" class="btn btn-rounded btn-warning"><span
									class="btn-icon-start text-warning"><i class='bx bx-download'></i>
								</span>Download</button>
						<button type="button" class="btn btn-rounded btn-success"><span
									class="btn-icon-start text-success"><i class='bx bx-upload'></i>
								</span>Upload</button>
								
						<hr><br><br>
						
						<h2>跳窗請看這，看到喜歡的複製貼上就好，button格式會直接套用</h2>
						<a href="https://getbootstrap.com/docs/4.6/components/modal/"><h3 style="color:red">bootstrap-modal</h3></a>
						
						
							
								
					</div>


					<%@ include file="../commonJS.file" %>
						<!-- 基本JS檔案 -->
						<script>
// 						● 可在這更改header的標題，不寫也可以，但請變成空字串 
							$("#pagename").text("說明");
						</script>
	</body>

	</html>