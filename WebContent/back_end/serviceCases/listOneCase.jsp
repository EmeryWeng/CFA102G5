<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.serviceCases.model.*"%>
<%
  ServiceCasesVO serviceCasesVO = (ServiceCasesVO) request.getAttribute("serviceCasesVO");
%>

<html>
<head>
<title>案件新增 - listOneCase.jsp</title>

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
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>
<table id="table-1">
	<tr><td>
		 <h3>案件資料 - listOneCase.jsp</h3></td><td>
		 <h4><a href="<%=request.getContextPath()%>/back_end/serviceCases/listAllCase.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>案件編號</th>
		<th>會員編號</th>
		<th>案件標題</th>
		<th>案件描述</th>
		<th>案件回覆</th>
		<th>案件狀態</th>
		
	</tr>
	<tr>
<!-- 	<td>1</td> -->
		<td><%=serviceCasesVO.getCase_no()%></td>
		<td><%=serviceCasesVO.getMem_no()%></td>
		<td><%=serviceCasesVO.getCase_title()%></td>
		<td><%=serviceCasesVO.getCase_des()%></td>
		<td><%=serviceCasesVO.getCase_reply()%></td>
		<td><%=serviceCasesVO.getCase_state()%></td>

	</tr>
</table>

</body>
</html>