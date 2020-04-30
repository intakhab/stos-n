<!DOCTYPE HTML>
<html>
<title>STOS : Show TC Page</title>
<head>
<%@ include file="lib.jsp"%>
<link rel="stylesheet" type="text/css" href="/css/buttons.dataTables.min.css">
<script type="text/javascript" src="/js/dataTables.buttons.min.js"></script>
<script type="text/javascript" src="/js/buttons.flash.min.js"></script>
<script type="text/javascript" src="/js/jszip.min.js"></script>
<script type="text/javascript" src="/js/pdfmake.min.js"></script>
<script type="text/javascript" src="/js/vfs_fonts.js"></script>
<script type="text/javascript" src="/js/buttons.html5.min.js"></script>
<script type="text/javascript" src="/js/buttons.print.min.js"></script>
<script type="text/javascript" class="init">
	$(document).ready(function() {
		var table=$('#regTableId').DataTable({
			dom : 'Bfrtip',
			buttons : [ 'excel', 'pdf' ],
			"pageLength" : 10,
			"processing" : true,
			buttons : [ {
				extend : 'excel',
				text : 'Download Template for Register TC',
				title : 'RegisterTC',
				exportOptions : {
					modifier : {
						order : 'index', // 'current', 'applied',
						page : 'all', // 'all', 'current'
						search : 'none' // 'none', 'applied', 'removed'
					},
					columns : [0,1, 2, 3, 4, 5 ]
				}
			},
			 {
	            text : '<A id="registertcId" href="javascript:registertc()">Upload Excel for Register TC</A>'
             }
			],
			"columnDefs": [
	            {
	                "targets": [ 6 ],
	                "visible": false,
	                "searchable": false
	            },
	            {
	                "targets": [ 7 ],
	                "visible": false,
	                "searchable": false
	            }
	          
	        ],
		});
		
		$('#regTableId tbody').on('click', 'td.details-control', function () {
	        var tr = $(this).closest('tr');
	        var row = table.row( tr );
	 
	        if ( row.child.isShown() ) {
	            // This row is already open - close it
	            row.child.hide();
	            tr.removeClass('shown');
	           // tr.td.closest('div').removeClass('crop');
	        }
	        else {
	            // Open this row
	            row.child( format(row.data()) ).show();
	            tr.addClass('shown');
	        }
	    } );
	});
	function format ( d ) {
	    return '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;background-color:light-grey">'+
	        '<tr>'+
	            '<td>Creation Date:</td>'+
	            '<td >'+d[6]+'</td>'+
	        '</tr>'+
	        '<tr>'+
	            '<td>Note:</td>'+
	            '<td>'+d[7]+'</td>'+
	        '</tr>'+
	       
	    '</table>';
	}
</script>
</head>
<body>
	<div class="page-container">
		<div class="left-content">
			<div class="mother-grid-inner">
				<!-- Header -->
				<jsp:include page="header.jsp" />
				<div style="padding-left: 100px; padding-top: 100px;">
					<nav aria-label="breadcrumb" style="width: 85%">
						<ol class="breadcrumb">
							<li><a href="showregisteredtc"> 
							   <i
									class="fa fa-floppy-o" aria-hidden="true"></i> Run
							</a></li>
							<li class="breadcrumb-item active" aria-current="page">TC</li>
						</ol>
					</nav>
					     <c:if test="${groupList.size()>0}">
					       <div class="form-inline">
									<label for="groupName">Group name </label>
									<input list="groupsname" name="groupname"
										class="form-control" autocomplete="off" required="required"
										style="width: 500px;" id="groupsnameId" />
									<datalist id="groupsname">
										<c:forEach items="${groupList}" var="dt">
											<option hidden="${dt.groupId}">${dt.groupname}</option>
										</c:forEach>
									</datalist>
									&nbsp;&nbsp; <input type="submit" name="saveGroup"
										value="Run Suite" id="runBtn" onclick="runTestCase()"  class="btn btn-success" />
									<input type="button" name="Refresh"
										value="Refresh" id="refreshBtn" onclick="callRefresh()" class="btn btn-primary" />	
										 <br />
									<br /> <br />
								</div>
						</c:if>		
					<div class="col-sm-11">
						<div id='alertId' class='alert alert-success' data-dismiss='alert'
							aria-label='Close' role='alert'>
							<i class='fa fa-check-circle-o' aria-hidden='true' style='font-size:30px;color:green'></i>&nbsp;&nbsp;
							${param.msg}
							<span style='float: right; cursor: pointer;'>&times;</span>
						</div>
						<!-- table-striped table-bordered -->
						<table id="regTableId" class="display nowrap" style="width: 100%">
							<thead>
								<tr>
								    <th></th>
									<th>#</th>
									<th>TCName</th>
									<th>SheetName</th>
									<th>Sprint</th>
									<th>UserStory</th>
									<th>Created Date</th>
									<th>Note</th>
									<th></th>
									<th>
										<%-- <button id="btnSatusId${dt.tcId}" type="button"
											onclick="runTestCase()" class="btn_link btn-success"
											title="Please click here to run application">
											<i class="fa fa-play-circle" aria-hidden="true">&nbsp;&nbsp;Run TC</i>
										</button> --%>
									</th>
									<th>
									</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${infoObj}" var="dt" varStatus="count">
									<tr>
									    <td class="details-control"></td>
										<td id="rowId${count.count}">${count.count}</td>
										<td><a href="javascript:void(0)" onclick="singleRun('${dt.tcId}','${dt.tcName}')">${dt.tcName}</a></td>
										<td>${dt.tcSheetName}</td>
										<td>
										    ${dt.tcVersion}
										</td>
										<td>
										<div class="crop">
										<a href="javascript:void(0)" title='${dt.tcStory}'>${dt.tcStory}</a>
										</div>
										</td>
										<td>${dt.tcRegDate}</td>
										<td>${dt.tcRegNote}</td>
										
										<td><a href="editregistertc?id=${dt.tcId}">
												<button type="button" title="Edit TC"
													class="btn_link btn-primary">
													<i class="fa fa-pencil-square-o" aria-hidden="true"></i>
												</button>
										</a>
											<button onclick="deleteTC('${dt.tcId}')" type="button"
												title="Del TC" class="btn_link btn-danger">
												<i class="fa fa-trash" aria-hidden="true"></i>
											</button> 
										 
										 </td>
										<td>
										<input type="checkbox" id="${dt.tcName}"  title="Select here" class="check_"
											value="${dt.tcId}-${dt.tcName}" onchange="changeCheckBox('${dt.tcName}')">
										&nbsp;&nbsp;&nbsp;
										<label class="switch">
										  <input type="checkbox" id="jiraId${dt.tcName}" onclick="jiraTicket('${dt.tcName}')" title="Want to reports bugs at JIRA portal"  >
										  <span class="slider round"></span>
										</label>
										
										</td>
										<td>
										<span id="runStatusId${dt.tcId}" style="display: none">
										    <img height="17px" width="17px" src="/images/spin.gif" style="float: right;">
										</span>	
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<div id="chkboxId" style="display:none"></div>
						<div id="chkboxId2" style="display:"></div>
						<pre >Selected TC:<span style="font-weight: bold;color:red"
						 id="example-console-rows">0</span></pre>
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
var whichTCRunId="";
$(window).bind("load", function() {
	//alert(sessionStorage.getItem("tcidx"))
	if(sessionStorage.getItem("tcidx")!=""){
	 $("#runStatusId" + sessionStorage.getItem("tcidx")).show();
	}
});
function callRefresh(){
	window.location.href="showregisteredtc";
}
function jiraTicket(idx){
	//var isJiraCheck=$("#jiraId"+id).is(':checked');
	var id = "#jiraId" + idx;
	// var count=0;
	if ($(id).is(':checked')) {
		count = Number(count) + 1;
		$('#chkboxId2').append(idx + ",");
	} else {
		if(count!=0){
		  count = Number(count) - 1;
		}
		var arr = $('#chkboxId2').html().split(",");
		var unchkv = idx;
		$('#chkboxId2').html("");
		for (var i = 0; i < arr.length; i++) {
			var arrV = arr[i];
			if (unchkv != arrV && arrV != "") {
				$('#chkboxId2').append(arrV + ",")
			}
		}
	}
}
$(document).ready(function() {
	
	$(".paginate_button").click(function(){
		 $("#runStatusId" + sessionStorage.getItem("tcidx")).show();
	});
	var v = '${param.msg}';
	if (v == '') {
		$("#alertId").hide();
	}
	
	
	$("#closeMyModel3").click(function() {
		 $("#myModal3").hide();
	});
	$("#registertcId").click(function() {
		 $("#myModal3").show();
	});
	
 $("#groupsnameId").change(function() {
		count = 0;
		$("#example-console-rows").html("");
		$(".check_").removeAttr('checked');
		$('#chkboxId').html("");
		$.ajax({
			type : "GET",
			url : "ajaxgrouptc?groupName=" + $(this).val(),
			cache : false,
			async : true,
			dataType : "html",
			success : function(data) {
				//alert(data)
				data = $.parseJSON(data);
				var ln=table.page.len();
				table.page.len(200).draw();
				$("#example-console-rows").html(data.length);
				$.each(data, function(i, item) {
					//  $("#" + item.split("-")[1]).trigger("click");
					  $("#" + item.split("-")[1]).attr("checked", "checked");
					  $('#chkboxId').append(item + ",");
				});
			    table.page.len(ln).draw();
			}
		});
		/* }else{
		    $(".check_").removeAttr('checked');

		} */
 });

$("#uploadId").click(function(event){
        event.preventDefault();
		$("#errorId2").html("")
		$("#spinId2").show();
		$("#uploadId").attr("disabled", true);
        var form = $('#uploadForm')[0];
		// Create an FormData object 
        var data = new FormData(form);
		$.ajax({
			type : "POST",
			url : "uploadexcel",
	        enctype: 'multipart/form-data',
	        processData: false,  // Important!
	        contentType: false,
            data: data,
	        cache: false,
			success : function(data) {
				if(data=='OK'){
				window.location.href="showregisteredtc?msg=TC Registered successfully..";
				}else{
					$("#errorId2").html("There is problem in excel sheet please check manually")
					$("#spinId2").hide();
					$("#uploadId").attr("disabled", false);
				}
			},
			error : function(jqXHR, exception) {
					$("#errorId2").html('Error');
			}
		});
		
	});

});

var count = 0;
function changeCheckBox(tc) {
	var id = "#" + tc;
	// var count=0;
	if ($(id).is(':checked')) {
		count = Number(count) + 1;
		$('#chkboxId').append($(id).val() + ",");
	} else {
		if(count!=0){
		  count = Number(count) - 1;
		}
		var arr = $('#chkboxId').html().split(",");
		var unchkv = $(id).val();
		$('#chkboxId').html("");
		for (var i = 0; i < arr.length; i++) {
			var arrV = arr[i];
			if (unchkv != arrV && arrV != "") {
				$('#chkboxId').append(arrV + ",")
			}
		}
	}
	$("#example-console-rows").html((count));
}

function deleteTC(id) {
	var message = "Do you sure want to delete TC?";
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
				window.location.href = "deleteregistertc?id=" + id;
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
//
function singleRun(id,obj){
	 $('#chkboxId').html("");//Reset first
	  $("#" + obj).attr("checked", "checked");
	  //$('#chkboxId').append( $("#" + obj).val()+",");
	  changeCheckBox(obj);
	  runTestCase();
	  $("#example-console-rows").html("");
	//  $("#runStatusId" + id).show();
}

function runTestCase() {
	var chkVal = $('#chkboxId').text();
	chkVal = chkVal.slice(0, -1); // trim last character
	if (chkVal == '') {
		commonConfirmation("Please Select any TC checkbox!!!")
		return;
	}
	var jirVal=$('#chkboxId2').text();
	jirVal = jirVal.slice(0, -1); // trim last character
	
	var message = "<i class='fa fa-info-circle' aria-hidden='true' style='font-size:35px;color:red'></i><span style='padding-top:10px;'>&nbsp;&nbsp;&nbsp;"
			+ "Do You Sure Want to Run Selected TC "
			+ name
			+"<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
			+ "Please be ensure data setup properly in StosData Sheet</span>";
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
				var idx=chkVal.split("-")[0]
				saveTc(idx);
				$("#runStatusId" + idx).show();
				//$("#btnSatusId" + chkVal).hide();
				$.ajax({
					type : "GET",
					url : "run/" + chkVal+'?jiraId='+jirVal,
					cache : false,
					async : true,
					dataType : "html",
					success : function(data) {
					    $("#runStatusId" + idx).hide();
					    sessionStorage.setItem("tcidx", "");
					}
				});
			},
			No : function() {
				$(".check_").removeAttr('checked');
				$('#chkboxId').html("");
				$(this).dialog("close");
			}
		},
		close : function(event, ui) {
			$(this).remove();
		}
	});
}
function saveTc(idx){
	if (typeof(Storage) !== "undefined") {
		  // Store
		  sessionStorage.setItem("tcidx", idx);
		} else {
			$('#alertId').show();
			$('#alertId').html("Sorry, your browser does not support Web Storage...");
		   
		}
}

</script>
</body>
</html>
