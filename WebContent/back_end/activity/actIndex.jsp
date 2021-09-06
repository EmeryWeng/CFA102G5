<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>活動後台首頁</title>
	<link href="<%=request.getContextPath()%>/back_end/activity/css/bootstrap.min.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/back_end/activity/css/font-awesome.min.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/back_end/activity/css/datepicker3.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/back_end/activity/css/styles.css" rel="stylesheet">
	
	<!--Custom Font-->
	<link href="https://fonts.googleapis.com/css?family=Montserrat:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">
	<!--[if lt IE 9]>
	<script src="js/html5shiv.js"></script>
	<script src="js/respond.min.js"></script>
	<![endif]-->
	<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
	
	<style>
			
		.actTable{
			table-layout: auto;
			border:1px solid plum;
			color:lightsteelblue;
			width: 100%;
		}
		.actTable th{
			text-align: center;
			border:1px solid plum;
			color:lightpink;
		}
		.actTable td{
			text-align: center;
			border:1px solid plum;
			color:lightseagreen;
		}
		
</style>

</head>
<body>
	<nav class="navbar navbar-custom navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand"><span >活動後台首頁</span></a>
			</div>
		</div><!-- /.container-fluid -->
	</nav>
	<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
		<ul class="nav menu">
			<li class="active"><a href="index.html"><span class="fa fa-calendar">&nbsp;</span>活動類別</a></li>
			<li><a href="widgets.html"><span class="fa fa-calendar">&nbsp;</span>活動</a></li>
			<li><a href="charts.html"><span class="fa fa-calendar">&nbsp;</span>活動場次</a></li>
			<li><a href="elements.html"><span class="fa fa-calendar">&nbsp;</span>活動圖片</a></li>
			<li><a href="panels.html"><span class="fa fa-calendar">&nbsp;</span>活動訂單</a></li>
			<li><a href="panels.html"><span class="fa fa-calendar">&nbsp;</span>活動訂單明細</a></li>
			<li><a href="panels.html"><span class="fa fa-calendar">&nbsp;</span>活動促銷</a></li>
			<li><a href="panels.html"><span class="fa fa-calendar">&nbsp;</span>活動促銷明細</a></li>
			<li><a href="panels.html"><span class="fa fa-calendar">&nbsp;</span>活動評價</a></li>
			<li><a href="panels.html"><span class="fa fa-calendar">&nbsp;</span>活動評價檢舉</a></li>
		</ul>
	</div><!--/.sidebar-->
		
	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
		
		<div class="panel panel-container">
			<div class="row">
				<div class="col-xs-6 col-md-3 col-lg-3 no-padding">
					<div class="panel panel-teal panel-widget border-right">
						<div class="row no-padding"><em class="fa fa-xl fa-users color-teal"></em>
							<div class="large">活動類別</div>
						</div>
					</div>
				</div>
				<div class="col-xs-6 col-md-3 col-lg-3 no-padding">
					<div class="panel panel-blue panel-widget border-right">
						<div class="row no-padding"><em class="fa fa-xl fa-comments color-orange"></em>
							<div class="large">
								<form id="addForm" method="post" action="<%=request.getContextPath()%>/activity/ActivityClass">
									<input type="text" name="actClassName" id="actClassName" size="10">
									<input type="hidden" name="action" value="addActClass">
								</form>
							</div>			
						</div>
					</div>
				</div>
				<div class="col-xs-6 col-md-3 col-lg-3 no-padding">
					<div class="panel panel-orange panel-widget border-right">
						<div class="row no-padding"><em class="fa fa-xl fa-users color-teal"></em>
							<div class="large">
								<input type="button" value="新增" onclick="sendRequest(document.getElementById('addForm'));">
							</div>
						</div>
					</div>
				</div>
				<div class="col-xs-6 col-md-3 col-lg-3 no-padding">
					<div class="panel panel-red panel-widget ">
						<div class="row no-padding"><em class="fa fa-xl fa-search color-red"></em>
							<div class="large">
								<form id="addForm" method="post" action="<%=request.getContextPath()%>/activity/ActivityClass">
									<input type="hidden" name="action" value="getAllActClass">
									<input type="submit" value="查詢">
								</form>
							</div>
						</div>
					</div>
				</div>
			</div><!--/.row-->
		</div>
				
		<div class="row">
			<div class="col-xs-6 col-md-3">
				<div class="panel panel-default">
					<div class="panel-body easypiechart-panel">
										
					</div>
				</div>
			</div>
			<div class="col-xs-6 col-md-3">
				<div class="panel panel-default">
					<div class="panel-body easypiechart-panel">
								
					</div>
				</div>
			</div>
			<div class="col-xs-6 col-md-3">
				<div class="panel panel-default">
					<div class="panel-body easypiechart-panel">
										
					</div>
				</div>
			</div>
			<div class="col-xs-6 col-md-3">
				<div class="panel panel-default">
					<div class="panel-body easypiechart-panel">
						
					</div>
				</div>
			</div>
		</div><!--/.row-->


		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<table class="actTable">
							<tr>
								<th>活動類別編號</th>
								<th>活動類別名稱</th>
								<th>活動類別狀態</th>
								<th>修改活動類別</th>
								<th>切換上下架狀態</th>
							</tr>
						<c:if test="${not empty actClassVO}">
							<tr>
								<td>${actClassVO.act_class_no}</td>
								<td>${actClassVO.act_class_name}</td>
								<td>${actClassVO.act_class_state}</td>
								<td>
									<form id="addForm" method="post" action="<%=request.getContextPath()%>/activity/ActivityClass">
										<input type="hidden" name="updatePk" value="${actClassVO.act_class_no}">
										<input type="hidden" name="action" value="updateActClass">
										<input type="submit" value="修改">
									</form>
								</td>
								
								<td>
									<form id="addForm" method="post" action="<%=request.getContextPath()%>/activity/ActivityClass">
										<input type="hidden" name="updatePk" value="${actClassVO.act_class_no}">
										<input type="hidden" name="action" value="switchActClassState">
										<input type="submit" value="切換">
									</form>
								</td>
							</tr>
						</c:if>
						
						<c:if test="${not empty list}">	
							<c:forEach var="actClassVO" items="${list}">
								<tr>
									<td>${actClassVO.act_class_no}</td>
									<td>${actClassVO.act_class_name}</td>
									<td>${actClassVO.act_class_state}</td>
									<td>
										<form id="addForm" method="post" action="<%=request.getContextPath()%>/activity/ActivityClass">
											<input type="hidden" name="updatePk" value="${actClassVO.act_class_no}">
											<input type="hidden" name="action" value="updateActClass">
											<input type="submit" value="修改">
										</form>
									</td>
									
									<td>
										<form id="addForm" method="post" action="<%=request.getContextPath()%>/activity/ActivityClass">
											<input type="hidden" name="updatePk" value="${actClassVO.act_class_no}">
											<input type="hidden" name="action" value="switchActClassState">
											<input type="submit" value="切換">
										</form>
									</td>
								</tr>
							</c:forEach>
						</c:if>						
						</table>
					</div>
					<div class="panel-body"></div>
					<div class="panel-body"></div>
					<div class="panel-body"></div>
					<div class="panel-body"></div>
					<div class="panel-body"></div>
					<div class="panel-body"></div>
					<div class="panel-body"></div>
					<div class="panel-body"></div>
					<div class="panel-body"></div>
					<div class="panel-body"></div>
					
				</div>
			</div>
		</div><!--/.row-->

	</div>	<!--/.main-->
	
	<script src="<%=request.getContextPath()%>/back_end/activity/js/jquery-1.11.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/back_end/activity/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/back_end/activity/js/chart.min.js"></script>
	<script src="<%=request.getContextPath()%>/back_end/activity/js/chart-data.js"></script>
	<script src="<%=request.getContextPath()%>/back_end/activity/js/easypiechart.js"></script>
	<script src="<%=request.getContextPath()%>/back_end/activity/js/easypiechart-data.js"></script>
	<script src="<%=request.getContextPath()%>/back_end/activity/js/bootstrap-datepicker.js"></script>
	<script src="<%=request.getContextPath()%>/back_end/activity/js/custom.js"></script>
	
	<script>
		function sendRequest(formObject){
			let actClassName = document.getElementById('actClassName').value;
			let regex = new RegExp('[\u4E00-\u9FFF]');
			
			if(!regex.test(actClassName)){
				alert('只能輸入中文');
				return false;
			}
			
			formObject.submit();
		}
		
		
	</script>
</body>
</html>