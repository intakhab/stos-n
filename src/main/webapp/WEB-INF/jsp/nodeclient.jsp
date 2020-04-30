<!DOCTYPE HTML>
<html>
<title>STOS : Custom TC Page</title>
<%@ page errorPage="errorpage.jsp"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<head>
<link rel="stylesheet" type="text/css" href="/css/buttons.dataTables.min.css">
<link href="/css/style.css" rel="stylesheet" type="text/css" media="all" />
<link rel="stylesheet" href="/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/jquery.dataTables.min.css">
<link rel="stylesheet" href="/css/select.dataTables.min.css">
<link rel="stylesheet" href="/css/jquery-ui.css">
<link rel="stylesheet" href="/font-awesome/css/font-awesome.css">
<!--Google Fonts-->
<link href='//fonts.googleapis.com/css?family=Carrois+Gothic' rel='stylesheet' type='text/css'>
<link href='//fonts.googleapis.com/css?family=Work+Sans:400,500,600' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="/css/main.css">
<script type="text/javascript" src="/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="/js/dataTables.select.min.js"></script>
<script type="text/javascript" src="/js/jquery-ui.js"></script>
<script type="text/javascript" src="/js/Popper.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript" src="/js/dataTables.buttons.min.js"></script>
<script type="text/javascript" src="/js/buttons.flash.min.js"></script>
<script type="text/javascript" src="/js/jszip.min.js"></script>
<script type="text/javascript" src="/js/pdfmake.min.js"></script>
<script type="text/javascript" src="/js/vfs_fonts.js"></script>
<script type="text/javascript" src="/js/buttons.html5.min.js"></script>
<script type="text/javascript" src="/js/buttons.print.min.js"></script>
<script type="text/javascript" class="init">
	$(document).ready(function() {
		table=$('#regTableId').DataTable({
			dom : 'Bfrtip',
			buttons : [ 'excel' ],
			"pageLength" : 10,
			"processing" : true,
			buttons : [ {
				extend : 'excel',
				text : 'Export to Excel',
				title : 'Regsitered TC',
				exportOptions : {
					modifier : {
						order : 'index', // 'current', 'applied',
						page : 'all', // 'all', 'current'
						search : 'none' // 'none', 'applied', 'removed'
					},
					columns : [0,1, 2, 3, 4, 5 ]
				}
			}

			],
			"columnDefs": [
	            {
	                "targets": [ 6 ],
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
		var story=d[5];
		story=story.replace("crop","")
	    return '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;background-color:light-grey">'+
	        '<tr>'+
	            '<td>User Story:</td>'+
	            '<td >'+story+'</td>'+
	        '</tr>'+
	        '<tr>'+
	            '<td>Creation Date:</td>'+
	            '<td>'+d[6]+'</td>'+
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
				<%-- <jsp:include page="header.jsp" /> --%>
				<div style="padding-left: 100px; padding-top: 100px;">
					<nav aria-label="breadcrumb" style="width: 85%">
						<ol class="breadcrumb">
							<li><a href="#"> 
							   <i
									class="fa fa-floppy-o" aria-hidden="true"></i> Run
							</a></li>
							<li class="breadcrumb-item active" aria-current="page">TC  </li>
							<li class="breadcrumb-item active" aria-current="page">Hostname: ${hostname} </li>
							<li class="breadcrumb-item active" aria-current="page">Port no:${serverport} </li>
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
					<div class="col-sm-10">
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
									<th>TCSheet Name</th>
									<th>Sprint</th>
									<th>User&nbsp;Story</th>
									<th>Created Date</th>
									<th></th>
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
										<td>
										 
										 </td>
										<td>
										<input type="checkbox" id="${dt.tcName}"  title="Select here" class="check_"
											value="${dt.tcId}-${dt.tcName}" onchange="changeCheckBox('${dt.tcName}')">
											
										<div id="runStatusId${dt.tcId}" style="display: none">
												<a> <img height="17px" width="17px" src="/images/spin.gif">
												</a>
										</div>	
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<div id="chkboxId" style="display:none"></div>
						<pre >Selected TC:<span style="font-weight: bold;color:red"
						 id="example-console-rows">0</span></pre>
					</div>
				</div>
			</div>
			
		</div>
		<!--slider menu-->
		<%-- <jsp:include page="leftmenu.jsp" /> --%>
		<div class="clearfix"></div>
		<%-- <jsp:include page="footer.jsp" /> --%>
	</div>
<script type="text/javascript">
URLClient='http://${hostname}:${serverport}/';
function callRefresh(){
	window.location.reload();
}
$(document).ready(function() {
	var v = '${param.msg}';
	if (v == '') {
		$("#alertId").hide();
	}
	$("#groupsnameId").change(function() {
		count = 0;
		$("#example-console-rows").html("");
		$(".check_").removeAttr('checked');
		$('#chkboxId').html("");
		$.ajax({
			type : "GET",
			crossDomain:true,
			url : URLClient+"ajaxgrouptc?groupName=" + $(this).val(),
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
//
function singleRun(id,obj){
	 $('#chkboxId').html("");//Reset first
	  $("#" + obj).attr("checked", "checked");
	  $('#chkboxId').append( $("#" + obj).val()+",");
	  runTestCase();
	//  $("#runStatusId" + id).show();
}

function runTestCase() {
	var chkVal = $('#chkboxId').text();
	chkVal = chkVal.slice(0, -1); // trim last character
	if (chkVal == '') {
		commonConfirmation("Please select checkbox to run TC !!!")
		return;
	}
	var hp='${hostname}-${serverport}';
	var localURL=URLClient+"run/"+chkVal+"/"+hp;
	alert(localURL);
	var message = "<i class='fa fa-info-circle' aria-hidden='true' style='font-size:35px;color:red'></i><span style='padding-top:10px;'>&nbsp;&nbsp;&nbsp;"
			+ "Do you sure want to run TC "
			+ name
			+"<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
			+ "Please be ensure data setup properly in StosData</span>";
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
				//$("#runStatusId" + index).show();
				//$("#btnSatusId" + index).hide();
				$.ajax({
					type : "GET",
					url : localURL,
					cache : false,
					async : true,
					dataType : "html",
					success : function(data) {
						//$("#runStatusId" + index).hide();
						//$("#btnSatusId" + index).show();
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

</script>
</body>
</html>
