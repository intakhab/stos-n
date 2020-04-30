<!DOCTYPE HTML>
<html>
<title>STOS : Show TC Page</title>
<head>
<!-- Lib all css and  -->
<%@ include file="lib.jsp"%>
<style type="text/css">
.crossover-box {
	color: #5A53EA;
	height: 250px !important;
}

.row {
	margin-top: 1rem;
}
</style>
</head>
<body>
	<div class="page-container">
		<div class="left-content">
			<div class="mother-grid-inner">
				<!-- Header -->
				<jsp:include page="header.jsp" />
				<div class="container"
					style="padding-left: 100px; padding-top: 100px;">
					<nav aria-label="breadcrumb" style="width: 85%">
						<ol class="breadcrumb">
							<li><a href="grouptc"> <i class="fa fa-floppy-o"
									aria-hidden="true"></i> Group
							</a></li>
							<li class="breadcrumb-item active" aria-current="page">Test
								case</li>
						</ol>
					</nav>
					<div class="col-sm-10">
												<div>${msg}</div>
					
						<form:form name="form" role="form" method="POST" id="configForm"
							action="savegroup" modelAttribute="infoObj">
							<div id="demo-wrapper">
								<div class="form-inline">
									<label for="groupName">Group name </label>
									<form:input list="groupsname" path="groupname"
										class="form-control" autocomplete="off" required="required"
										style="width: 500px;" id="groupsnameId" />
									<datalist id="groupsname">
										<c:forEach items="${groupList}" var="dt">
											<option hidden="${dt.groupId}">${dt.groupname}</option>
										</c:forEach>
									</datalist>
									&nbsp;&nbsp; <input type="submit" name="saveGroup"
										value="Save Group" id="saveBtn" class="btn btn-success" /> <br />
									<br /> <br />
								</div>
								<div class="row">
									<div class="col-md-5">
										<label for="items">Registered Test Case</label>
										<!-- <select
											multiple  id="items">
										</select> -->
										<form:select id="items" class="form-control crossover-box"
											path="groupTestName" items="${tcListReg}"  multiple="true" />
									</div>

									<div class="col-md-1">
										<br />
										<p></p>
										<p></p>
										<p></p>
										<p></p>
										<button type="button" class="btn_link btn-success"
											id="crossover-btn-add">&nbsp;+&nbsp;&nbsp;</button>
										<br /> <br />
										<button type="button" class="btn_link btn-success"
											id="crossover-btn-add-all">+&nbsp;+</button>
										<br /> <br />
										<button type="button" class="btn_link btn-danger"
											id="crossover-btn-remove">&nbsp;&nbsp;-&nbsp;&nbsp;</button>
										<br /> <br />
										<button type="button" class="btn_link btn-danger "
											id="crossover-btn-remove-all">&nbsp;-&nbsp;-&nbsp;</button>
									</div>

									<div class="col-md-5">
										<label for="selected">Selected Test Case</label>
										<form:select path="selectedGroupTestName" items="${tcListSelect}"  multiple="true"
											class="form-control crossover-box" id="selected"
											 />
									</div>
								</div>
								<br /> <br />

							</div>

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
	<script type="text/javascript">
		$(document).ready(function() {
			var v = '${param.msg}';
			if (v == '') {
				//$("#alertId").hide();

			}

		});
		//configForm
		 $('#saveBtn').click(function() {
		        $('#selected option').prop('selected', true);
		        $("#configForm").sumbit();

         });
		$("#groupsnameId").change(function(){
			window.location.href="callgrouptc?groupName="+$("#groupsnameId").val();
		});
		function group(id) {
			if ($(id).is(':checked')) {
				$("#selectBoxId").show();
			} else {
				$("#selectBoxId").hide();
			}
		}
	</script>
	<script type="text/javascript" src="js/crossover.js"></script>
	<script type="text/javascript">
		$(function() {
			crossover();
			$(window).scroll(
					function() {
						var scroll = $(window).scrollTop();
						if (scroll > 65) {
							$('nav, .navbar-brand, .nav-link, .active-link')
									.addClass('scrolled');
						} else {
							$('nav, .navbar-brand, .nav-link, .active-link')
									.removeClass('scrolled');
						}
					});

			$('.nav-link').click(function() {
				$('.active-link').removeClass('active-link');
				$(this).addClass('active-link');

				$('html, body').animate({
					scrollTop : $(this.hash).offset().top
				}, 900, function() {
					window.location.hash = this.hash;
				});
			});

		});
	</script>
</body>
</html>
