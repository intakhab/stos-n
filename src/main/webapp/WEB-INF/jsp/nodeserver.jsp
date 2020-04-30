<!DOCTYPE HTML>
<html>
<title>STOS : Node ${server} Page</title>
<head>
<!-- Lib all css and  -->
<jsp:include page="lib.jsp"></jsp:include>
</head>
<body>
	<div class="page-container">
		<div class="">
			<div class="mother-grid-inner">
				<!-- Header -->
				<div class="left-content"><jsp:include page="header.jsp" /></div>
				<div style="padding-left: -10px; padding-top: 10px;">
					<div id="mydataid" class="col-sm-12">
					  <div class="spinner-border text-danger">Loading...</div>
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
<script type="text/javascript">
    var cusurl='${server}'+"?call=${param.call}";
	$(window).bind("load", function() {
		$.ajax({
			type : "GET",
			url : cusurl,
		    timeout:20000, //20 second timeout
			cache : false,
			async : true,
		    beforeSend: function() {
		        $("#mydataid").html("Pls wait! Loading...");
		    },
			success : function(data) {
				$("#mydataid").html(data);
			},
			error : function(jqXHR, exception) {
				if (jqXHR.status === 0) {
					alert('Not connect.n Verify Network.');
				} else if (jqXHR.status == 404) {
					alert('Requested page not found. [404]');
				} else if (jqXHR.status == 500) {
					alert('Internal Server Error [500].');
				} else if (exception === 'parsererror') {
					alert('Requested JSON parse failed.');
				} else if (exception === 'timeout') {
					alert('Time out error.');
				} else if (exception === 'abort') {
					alert('Ajax request aborted.');
				} else {
					alert('Uncaught Error.n' + jqXHR.responseText);
				}
			}
		}); 
	});
</script>
</html>

