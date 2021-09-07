<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
        <%@ include file="../commonCSS.file" %> <!-- 基本CSS檔案 -->
    </head>

	<body>
		<%@ include file="../loading.file" %> <!-- loading -->
        <%@ include file="../header.file" %> <!-- Header -->
        <%@ include file="../sidebar.file" %> <!-- sidebar -->
        
        
        <div class="main-content">
        	<button type="button" class="btn btn-primary">Primary</button>
        	<button type="button" class="btn btn-secondary">Secondary</button>
        	<button type="button" class="btn btn-success">Success</button>
        	<button type="button" class="btn btn-danger">Danger</button>
        	<button type="button" class="btn btn-warning">Warning</button>
        	<button type="button" class="btn btn-info">Info</button>
        	<button type="button" class="btn btn-light">Light</button>
        	<button type="button" class="btn btn-dark">Dark</button>
        </div>


		<%@ include file="../commonJS.file" %> <!-- 基本JS檔案 -->
		<script>
			$("#pagename").text("房間訂單管理");
		</script>
	</body>

</html>