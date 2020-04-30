<!DOCTYPE HTML>
<%@ page errorPage="errorpage.jsp"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<title>STOS : Help Page</title>
<head>
<!-- Lib all css and  -->
<jsp:include page="lib.jsp"></jsp:include>
</head>
<body>
	<div class="page-container">
		<div class="left-content">
			<div class="mother-grid-inner">
				<!-- Header -->
				<jsp:include page="header.jsp" />
				<div style="padding-left: 250px; padding-top: 100px;">
					<nav aria-label="breadcrumb" style="width: 60%">
						<ol class="breadcrumb">
							<li><a href="help"><i class="fa fa-floppy-o"
									aria-hidden="true"></i> Help </a></li>
							<li class="breadcrumb-item active" aria-current="page">Page</li>
						</ol>
					</nav>
					<div class="col-sm-6">
						<table class="table table-striped table-sm">
							<thead>
								<tr>
									<th>Document Name</th>
									<th>Download</th>
								</tr>
							</thead>
							<tbody>
							    <tr>
									<td>STOS Technical Document</td>
									<td><a href="repdownload?ftype=sbestp">
												<button type="button" title="Dowload Doc"
													class="btn_link btn-success">
													<i class="fa fa-download"></i>
												</button>
										</a></td>
								</tr>
								<tr>
									<td>Development Best Practices Document</td>
									<td><a href="repdownload?ftype=dbestp">
												<button type="button" title="Dowload Doc"
													class="btn_link btn-success">
													<i class="fa fa-download"></i>
												</button>
										</a></td>
								</tr>
								<tr>
									<td>How to Run Document</td>
									<td><a href="repdownload?ftype=hrun">
												<button type="button" title="Dowload Doc"
													class="btn_link btn-success">
													<i class="fa fa-download"></i>
												</button>
										</a></td>
								</tr>
								<tr>
									<td>Difference B/W Old and STOS Framework Document</td>
									<td><a href="repdownload?ftype=oldnew">
												<button type="button" title="Dowload Doc"
													class="btn_link btn-success">
													<i class="fa fa-download"></i>
												</button>
										</a></td>
								</tr>
								<tr>
									<td>Top Tips for Automation Development</td>
									<td><a href="repdownload?ftype=toptips">
												<button type="button" title="Dowload Doc"
													class="btn_link btn-success">
													<i class="fa fa-download"></i>
												</button>
										</a></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		
		<!--slider menu-->
		<jsp:include page="leftmenu.jsp" />
		<div class="clearfix"></div>
		<jsp:include page="footer.jsp" />
	</div>
</body>
</html>
