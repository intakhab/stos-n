<!DOCTYPE HTML>
<html>
<title>STOS : h2 database Page</title>
<head>
<!-- Lib all css and  -->
<jsp:include page="lib.jsp"></jsp:include>
<style>
.iframe-container {
	overflow: scroll;
}

.iframe-container iframe {
	border: 0;
	left: 0;
	top: 100;
}
</style>
</head>
<body>
	<div class="page-container">
		<div class="left-content">
			<div class="mother-grid-inner">
				<!-- Header -->

				<jsp:include page="header.jsp" />
				<div style="padding-left: 50px; padding-top: 100px;">
					
					<div class="embed-responsive embed-responsive-16by9">
						<iframe src="<%=request.getContextPath()%>/h2-console"
							class="iframe-container col-sm-12" height="800px;"></iframe>

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
	var $iframes = $("iframe");

	//Find &amp;amp;#x26; save the aspect ratio for all iframes
	$iframes.each(function() {
		$(this).data("ratio", this.height / this.width)
		// Remove the hardcoded width &amp;amp;#x26; height attributes
		.removeAttr("width").removeAttr("height");
	});

	//Resize the iframes when the window is resized
	$(window).resize(function() {
		$iframes.each(function() {
			// Get the parent container&amp;amp;#x27;s width
			var width = $(this).parent().width();
			$(this).width(width).height(width * $(this).data("ratio"));
		});
		//Resize to fix all iframes on page load.
	}).resize();
</script>
</html>

