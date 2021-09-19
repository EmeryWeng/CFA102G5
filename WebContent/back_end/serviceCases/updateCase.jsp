<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.serviceCases.model.*"%>
<%
  ServiceCasesVO serviceCasesVO = (ServiceCasesVO) request.getAttribute("serviceCasesVO");
%>

<html>
<head>
<title>案件新增 - addServiceCases.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>案件回覆 - updateCase.jsp</h3></td><td>
		 <h4><a href="<%=request.getContextPath()%>/back_end/serviceCases/listAllCase.jsp">回首頁</a></h4>
	</td></tr>
</table>

<h3>案件回覆:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/serviceCases/ServiceCases.do" name="form1">

<table>
	
	<tr>
		<td>案件編號:<font color=red><b>*</b></font></td>
		<td><%=serviceCasesVO.getCase_no()%></td>
	</tr>
	<tr>
		<td>會員編號:</td>
		<td><%=serviceCasesVO.getMem_no()%></td>
	</tr>
	<tr>
		<td>案件標題:</td>
		<td><%=serviceCasesVO.getCase_title()%></td>
	</tr>
	<tr>
		<td>案件描述:</td>
		<td><%=serviceCasesVO.getCase_des()%></td>
	</tr>
	<tr>
		<td>案件回覆:</td>
		<td><input type="TEXT" name="case_reply" size="45"	value="" /></td>
	
	</tr>
	<tr>
		<td>案件狀態:</td>
		<td>
		<select name="case_state" size="1">
  		<option value=1>待處理</option>
  		<option value=2>處理完畢</option>
		</select></td>
	</tr>

</table>
<input type="hidden" name="action" value="update">
<input type="hidden" name="case_no" value="<%=serviceCasesVO.getCase_no()%>">
<input type="hidden" name="mem_no" value="<%=serviceCasesVO.getMem_no()%>">
<input type="hidden" name="case_title" value="<%=serviceCasesVO.getCase_title()%>">
<input type="hidden" name="case_des" value="<%=serviceCasesVO.getCase_des()%>">
<input type="submit" value="送出修改"></FORM>





</body>
</html>