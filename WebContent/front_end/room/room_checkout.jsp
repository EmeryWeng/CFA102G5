<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
    <head>
        <%@ include file="../commonCSS.file" %> <!-- 基本CSS檔案 -->
    </head>
    <body>
		<%@ include file="../loading.file" %> <!-- loading -->
        <%@ include file="../header.file" %> <!-- Header -->




		<%@ include file="../message.file" %> <!-- Message --> 
        <%@ include file="../footer.file" %> <!-- Footer -->      
        <%@ include file="../commonJS.file" %> <!-- 基本JS檔案 -->
        <script> 
        	$(`.nav-item:nth-child(1)>a`).attr('class', 'active');
        </script>              
    </body>
</html>