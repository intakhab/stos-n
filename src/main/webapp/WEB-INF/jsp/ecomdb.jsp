<!DOCTYPE HTML>
<html>
<title>STOS : Ecom DB Page</title>
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
				<div class="inner-block">
					<div class="card" spellcheck="false" style="padding-left: 50px;">
					<nav aria-label="breadcrumb" style="width: 70%">
							<ol class="breadcrumb">
								<li><a href="ecomdb"> <i class="fa fa-cog"
										aria-hidden="true"></i> DB
								</a></li>
								<li class="breadcrumb-item active" aria-current="page">Query
									for Automation</li>
							</ol>
						</nav>
						 
						
						<div class="card">
						 &nbsp;&nbsp;<span class="error" id="msgId"></span>
							<div class="panel-body">
								 <span class="bg-info">UPDATE ECOM.PROD_CONV_DTL SET DECLINED='N', ACCEPTED_DTE=NULL, DECLINE_REASON_CODE=NULL
								  WHERE CUST_NBR = 33497140 AND  OLD_PROD_NBR='64774' AND  NEW_PROD_NBR='2326411'</span>
								<div class="input-group">
									<span class="input-group-addon">Enter SQL Update Query</span>
									<textarea rows="4" id="sqlQueryId" required="required"
										class="form-control" cols="40" name="sqlQuery" >
														 </textarea>

								</div>
								<br/><br/>
								<div class="form-group">
									<button type="submit" id="queryId"
										class="btn btn-primary">
										<i class="fa fa-floppy-o" aria-hidden="true"></i> Run Query
										
									</button>
									<button type="button" id="re-loadId" class="btn btn-success">
										<i class="fa fa-refresh" aria-hidden="true"></i> Refresh Page
									</button>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!--inner block end here-->
			</div>
		</div>
		<!--slider menu-->
		<jsp:include page="leftmenu.jsp" />
		<div class="clearfix"></div>
		<jsp:include page="footer.jsp" />
	</div>
	<script type="text/javascript">
	
	$(function() {
		
		$("#queryId").click(function() {
		 var newV=escape($("#sqlQueryId").val());
		  if(newV==""){
				$("#sqlQueryId").focus();
				$("#msgId").text("Field value required...");
				return false;
			}
			$.ajax({
				type : "GET",
				url : 'dbrun?dbQuery='+newV,
				cache : false,
				async : true,
				success : function(data) {
					$("#msgId").text(data);
				},
				error : function(jqXHR, exception) {
					if (jqXHR.status === 0) {
						$("#msgId").html('Not connect.n Verify Network.');
					} else if (jqXHR.status == 404) {
						$("#msgId").html('Requested page not found. [404]');
					} else if (jqXHR.status == 500) {
						$("#msgId").html('Internal Server Error [500].');
					} else if (exception === 'parsererror') {
						$("#msgId").html('Requested JSON parse failed.');
					} else if (exception === 'timeout') {
						$("#msgId").html('Time out error.');
					} else if (exception === 'abort') {
						$("#msgId").html('Ajax request aborted.');
					} else {
						$("#msgId").html('Uncaught Error.n' + jqXHR.responseText);
					}
				}
			});
		});

	});
	
	</script>
</body>
</html>
