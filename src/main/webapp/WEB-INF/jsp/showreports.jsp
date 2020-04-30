<!DOCTYPE HTML>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>STOS : Reports Page</title>
<head>
<meta http-equiv="refresh" content="90">
<%@ include file="lib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="/css/buttons.dataTables.min.css">
<script type="text/javascript" src="/js/dataTables.buttons.min.js"></script>
<script type="text/javascript" src="/js/buttons.flash.min.js"></script>
<script type="text/javascript" src="/js/jszip.min.js"></script>
<script type="text/javascript" src="/js/pdfmake.min.js"></script>
<script type="text/javascript" src="/js/vfs_fonts.js"></script>
<script type="text/javascript" src="/js/buttons.html5.min.js"></script>
<script type="text/javascript" src="/js/buttons.print.min.js"></script>
<script type="text/javascript" class="init">
	$(document).ready(function() {
	    var selected = [];
		var table=	$('#reportsTable').DataTable({
			dom : 'Bfrtip',
	        "scrollX": true,
			buttons : [ 'excel', 'pdf','pdf'],
			"pageLength": 10,
	        "processing": true,
	        buttons : [{
	            extend : 'excel',
	            text : 'Download Run TC',
	            title : 'USFBuddy Automation',
	            exportOptions : {
	                modifier : {
	                    order : 'index', // 'current', 'applied',
	                    page : 'all', // 'all', 'current'
	                    search : 'none' // 'none', 'applied', 'removed'
	                },
	                columns: [ 1, 2, 3, 4, 5,6, 7,8,9 ]
	            }
	        },
	        {
	            text : '<A id="callReportsId" href="javascript:callReportsId()">Download Reports</A>'
            }
	        ,
            {
	            text : '<a class="error" href="javascript:void(0)" onclick="clearAllReports()" title="Delete All Report(s)">Delete All Report(s)</a>'
            }
	      ],
	        "columnDefs": [
	            {
	                "targets": [ 5 ],
	                "visible": false,
	                "searchable": false
	            },
	            {
	                "targets": [ 10 ],
	                "visible": false,
	                "searchable": false
	            },
	            {
	                "targets": [ 11 ],
	                "visible": false,
	                "searchable": false
	            },
	            {
	                "targets": [ 12 ],
	                "visible": false,
	                "searchable": false
	            }
	        ]
		});
		
		 $('#reportsTable tbody').on('click', 'tr', function () {
			// var t= table.column( 4 ).data().sum();
			// alert(t);
		        var id = this.id;
		        var index = $.inArray(id, selected);
		        if ( index === -1 ) {
		            selected.push( id );
		        } else {
		            selected.splice( index, 1 );
		        }
		        $(this).toggleClass('selected');
		    });
		$('#reportsTable tbody').on('click', 'td.details-control', function () {
	        var tr = $(this).closest('tr');
	        var row = table.row( tr );
	        if ( row.child.isShown() ) {
	            // This row is already open - close it
	            row.child.hide();
	            tr.removeClass('shown');
	        }
	        else {
	            // Open this row
	            row.child( format(row.data()) ).show();
	            tr.addClass('shown');
	        }
	    });
	});
	function format ( d ) {
		var story=d[4];
		story=story.replace("crop","")
		//var failRes=d[11].replace("escape(","").replace(")","");
	    return '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">'+
	    	'<tr>'+
       			 '<td>Sprint:</td>'+
                '<td>'+d[3]+'</td>'+
   		    '</tr>'+
	   		 '<tr>'+
				 '<td>User Story:</td>'+
	             '<td>'+story+'</td>'+
		    '</tr>'+
			 '<tr>'+
		        '<td>Browser, JIRA and Confluence URL:</td>'+
		        '<td>'+d[5]+'</td>'+
		    '</tr>'+
	        '<tr>'+
	            '<td>Run Time (Total time taken to run in mins):</td>'+
	            '<td>'+d[10]+'</td>'+
	        '</tr>'+
	        '<tr>'+
	            '<td>Fail Reason:</td>'+
	            '<td>'+d[11]+'</td>'+
	        '</tr>'+
	        '<tr>'+
            	'<td>Host name:</td>'+
           		'<td>'+d[12]+'</td>'+
        '</tr>'+
	    '</table>';
	}
</script>
<style type="text/css">
.tooltip-inner {
	word-break: break-all !important;
}
</style>
</head>
<body>
	<div class="page-container">
		<div class="left-content">
			<div class="mother-grid-inner">
				<!-- Header -->
				<jsp:include page="header.jsp" />
				<div style="padding-left: 100px; padding-top: 100px;">
					<nav aria-label="breadcrumb" style="width: 90%">
						<ol class="breadcrumb">
							<li><a href="showreports"> <i class="fa fa-floppy-o"
									aria-hidden="true"></i> Reports
							</a></li>
							<li class="breadcrumb-item active" aria-current="page">Reports
								Page</li>
						</ol>
					</nav>
					<div class="col-sm-11" id="editor">
						<div id='alertId' class='alert alert-success' data-dismiss='alert'
							aria-label='Close' role='alert'>
							<i class='fa fa-check-circle-o' aria-hidden='true'
								style='font-size: 30px; color: green'></i>&nbsp;&nbsp;
							${param.msg} <span style='float: right; cursor: pointer;'>&times;</span>
						</div>
						<span id="msgId"></span>

						<table id="reportsTable" class="display nowrap"
							style="width: 100%; font-size: 13px">
							<thead>
								<tr>
									<th></th>
									<th>#</th>
									<th>TCName</th>
									<th>Sprint</th>
									<th>User Story</th>
									<th>Browser, JIRA and Confluence URL</th>
									<th>Pass</th>
									<th>Fail</th>
									<th>Total</th>
									<th>Status</th>
									<th>Run Time</th>
									<th>Description</th>
									<th>Host Address</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${infoObj}" var="dt" varStatus="count">
									<tr>
										<td class="details-control"></td>
										<td>${count.count}</td>
										<td>${dt.tcName}</td>
										<td>${dt.tcVersion}</td>
										<td><div class="crop">
												<a href="javascript:void(0)" title='${dt.tcStory}'>${dt.tcStory}</a>
											</div></td>
										<td>${dt.browserType}-JIRA ticket-<span style="color: blue">${dt.jiraTicket}</span> Confluence URL-<span style="color: blue">${dt.confluenceUrl}</span></td>
										<td>${dt.passSteps}</td>
										<td>${dt.failSteps}</td>
										<td>${dt.totalSteps}</td>
										<td>
											<c:if test="${dt.runStatus eq 'pass' or dt.runStatus eq 'PASS'}">
												<span style="color: green;" title="Passed"> <i
													class="fa fa-check-circle" aria-hidden="true"></i></span>
											</c:if> 
											<c:if test="${dt.runStatus eq 'Running'}">
												<img height="17px" width="17px" src="/images/spin.gif">
												<%-- <span id="runStatusId${dt.tcId}" style="display: none"></span> --%>
											</c:if> 
											<c:if test="${dt.runStatus eq 'fail' or dt.runStatus eq 'FAIL'}">
												<span style="color: red;" title="Failed"><i
													class="fa fa-times-circle" aria-hidden="true"></i></span>
											</c:if> 
											<c:if test="${dt.runStatus eq 'skip' or dt.runStatus eq 'SKIP'}">
												<span style="color: grey;" title="Skipped"><i
													class="fa fa-times-circle" aria-hidden="true"></i></span>
											</c:if>
											 <span style="visibility: hidden;"> 
											    <c:if test="${dt.runStatus eq 'pass' or dt.runStatus eq 'PASS'}">
											  	  Passed
											   </c:if>
											    <c:if test="${dt.runStatus eq 'fail' or dt.runStatus eq 'FAIL'}">
											     Failed
											   </c:if>
											    <c:if test="${dt.runStatus eq 'skip' or dt.runStatus eq 'SKIP'}">
											     Skipped
											   </c:if>
											    <c:if test="${dt.runStatus eq 'Running' or dt.runStatus eq 'RUNNING'}">
											    Running
										      </c:if>
										   </span>
										</td>
										<td>${dt.tcRunTime}- ${dt.runDuration} </td>
										<td>escape("${dt.reasonFail}")</td>
										<td>${dt.host} - ${dt.envUrl}</td>
										<td><a>
												<button
													onclick="uploadReports('${dt.runTcId}','${dt.tcName}')"
													type="button" title="Upload TC Reports to JIRA"
													class="btn_link btn-primary">
													<i class="fa fa-upload" aria-hidden="true"></i>
												</button>
										</a> &nbsp; <a
											href="repdownload?ftype=html&fpath=${dt.reportsPath}">
												<button type="button" title="Dowload TC Reports"
													class="btn_link btn-success">
													<i class="fa fa-download"></i>
												</button>
										</a> &nbsp;
											<button
												onclick="deleteReports('${dt.runTcId}','${dt.reportsPath}')"
												type="button" title="Del Reports"
												class="btn_link btn-danger">
												<i class="fa fa-trash" aria-hidden="true"></i>
											</button></td>
									</tr>
								</c:forEach>
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
	<script type="text/javascript">
$(document).ready(function() {
	
	var v = '${param.msg}';
	if (v == '') {
		$("#alertId").hide();
	}
	
	$("#callReportsId").click(function(){
		window.location.href="callreports";
	})
	$("#closeMyModel").click(function(){
		$("#myModal2").hide();
	});
	$("#emailCheckId").click(function(){
		if ($(this).is(':checked')) {
			$("#emailTextId").show();
		}else{
			$("#emailTextId").hide();
		}
	});
	
	$("#processId").click(function(){
		$("#errorId").html("")
		var createJiraTicket=$("#jiraCreatelCheckId").is(':checked');
		var confUploadFile=$("#confuUploadCheckId").is(':checked');
		if(createJiraTicket==false && confUploadFile==false){
			$("#errorId").html("Pls select at least on checkbox")
			return;
		}
		var jiraComments=$("#jirComment").val();
		//
		var confuComment=$("#confluenceComment").val();
		
		var emailCheckText="";
		var emailCheckId=$("#emailCheckId").is(':checked');
		if(emailCheckId==true || emailCheckId=="true"){
			emailCheckText=$("#emailTextId").val();
		}else{
			emailCheckText="";
		}
		$("#spinId").show();
		$("#processId").attr("disabled", true);
		$.ajax({
			type : "GET",
			url : "devopsprocess?tcId="+$("#tcId").val()+"&createJiraTicket="+createJiraTicket+"&jiraComments="+jiraComments+"&confUploadFile="+confUploadFile+"&confuComment="+confuComment+"&emailCheckId="+emailCheckId+"&emailCheckText="+emailCheckText,
			cache : false,
			async : true,
			dataType : "html",
			success : function(data) {
				window.location.href="showreports?msg=Jira ticket created and file uploaded to confulence!";
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
	
	sessionStorage.setItem("tcidx", "");
});
 function showDes(val){
	 description(val);
 }
 function clearAllReports(){
		var message = "<i class='fa fa-info-circle' aria-hidden='true' style='font-size:35px;color:red'></i><span style='padding-top:10px;'>&nbsp;&nbsp;&nbsp;"
				+ "Do you sure want to delete all Report(s)"
				+ "<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
				+ "It will be completely deleted all records, which can not undo in futuren</span>";
		$('<div></div>').appendTo('body').html(
				'<div><h6>' + message + '</h6></div>').dialog({
			modal : true,
			title : 'Confirmation',
			zIndex : 10000,
			autoOpen : true,
			width : 'auto',
			resizable : false,
			buttons : {
				Yes : function() {
					$(this).dialog("close");
					$.ajax({
						type : "GET",
						url : "reportsdelall",
						cache : false,
						async : true,
						dataType : "html",
						success : function(data) {
                                 window.location.href="showreports";
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
				},
				No : function() {
					$(this).dialog("close");
				}
			},
			close : function(event, ui) {
				$(this).remove();
			}
		});
 }

 $(document).ready(function() {
	 var v = '${param.status}';
      if (v != '') {
		var searchVal = "pass";
		if (v == 1) {
			searchVal = "fail";
		} else if (v == 0) {
			searchVal = "pass";
		} else {
			searchVal = "skip";
		}
		$('input[type="search"]').val(searchVal).keyup();
	}
});
 
 function deleteReports(id,path) {
		var message = "<b>Do you sure want to Delete Report?</b>";
		$('<div class="btn_link btn-success"></div>').appendTo('body').html(
				'<div ><h6>' + message + '</h6></div>').dialog({
			modal : true,
			title : 'Confirmation',
			zIndex : -0.55,
			autoOpen : true,
			width : 'auto',
			resizable : false,
			buttons : {
				Yes : function() {
					$(this).dialog("close");
					window.location.href = "deletereports?id=" + id+"&path="+path;
				},
				No : function() {
					$(this).dialog("close");
				}
			},
			close : function(event, ui) {
				$(this).remove();
			}
		});

	}
 function uploadReports(id,tcName) {
	 $("#myModal2").show();
	 $("#tcName").html(tcName)
	 $("#tcId").val(id)
	}
</script>
</body>
</html>
