<!DOCTYPE HTML>
<html>
<head>
<%@ include file="lib.jsp"%>
</head>
<body>
	<div class="page-container">
		<div class="left-content">
			<div class="mother-grid-inner">
				<!-- Header -->
				<jsp:include page="header.jsp" />
				<div style="padding-left: 10px; padding-top: 100px;">
					<div class="col-sm-6">
						<div class="alert alert-danger" role="alert">
							Found error : ${errorMsg}
							<%=request.getAttribute("param1")%>

						</div>
							<div class="error-404">
								<div class="error-page-left">
									<img src="images/404.png" alt="">
								</div>
								<div class="error-right">
									<h2>Oops! Page Not Open</h2>
									<a href="home">Go Back</a>
								</div>
							</div>
						
					</div>
				</div>


			</div>
		</div>
		<!--slider menu-->
		<div class="clearfix"></div>
		<jsp:include page="footer.jsp" />
	</div>
</body>
</html>
