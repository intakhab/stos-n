<!DOCTYPE HTML>
<%@ page errorPage="errorpage.jsp"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<title>STOS : Register Page</title>
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
					<nav aria-label="breadcrumb" style="width: 85%">
						<ol class="breadcrumb">
							<li><a href="registertc"><i class="fa fa-floppy-o"
									aria-hidden="true"></i> Register </a></li>
							<li class="breadcrumb-item active" aria-current="page">Test
								case</li>
						</ol>
					</nav>
					<div class="col-sm-6">
						<div>${msg}</div>

						<form:form name="form" role="form" method="POST" id="configForm"
							action="savetestcase" modelAttribute="infoObj">
							<div>
								<div class="form-group">
									<label for="testCaseIndex">TC Version</label>
									<form:select path="tcVersion" class="form-control"
										id="tcVersion" required="required">
										<option value="">Select Test Version</option>
										<c:forEach items="${version}" var="dt">
											<option value="${dt}">${dt}</option>
										</c:forEach>							
										</form:select>
								</div>
								<div class="form-group">
									<label for="tcVersion">Story/TC ID</label>
									<form:input path="tcStory" class="form-control"
										id="tcStory" placeholder="Enter Story name"
										required="required" />


								</div>
								<div class="form-group">
									<label for="tcVersion">TC Name</label>
									<form:input path="tcName" class="form-control"
										id="tcName" placeholder="Enter Test Case Name"
										required="required" />


								</div>

								<div class="form-group">
									<label for="userpass">TC Sheet Name</label>
									<form:input path="tcSheetName" class="form-control"
										id="tcSheetName" placeholder="Sheet/File Name"
										required="required" />
								</div>
								<div class="form-group">
									<label for="userpass">Note </label>
									<form:input path="tcRegNote" class="form-control"
										id="tcRegNote" placeholder="Can write info"
										 />
								</div>

							</div>
							<br />
							<br />
							<button type="submit" id="submitButtonId" class="btn btn-primary">
								<i class="fa fa-floppy-o" aria-hidden="true"></i> Save Test
								Cases
							</button>
							<button type="button" id="re-loadId" class="btn btn-success"
								onclick="load()">
								<i class="fa fa-refresh" aria-hidden="true"></i> Refresh Page
							</button>
						</form:form>
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
