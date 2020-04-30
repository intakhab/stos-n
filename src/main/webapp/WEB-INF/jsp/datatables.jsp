<!DOCTYPE HTML>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>STOS : Data sheet Page</title>
<head>
<!-- Lib all css and  -->
<%@ include file="lib.jsp"%>
<link rel="stylesheet" href="/css/datasheet.css">
<!-- script>
	var size = '${sheetValueList.size()}';
	$(document).ready(function() {
		for (var i = 1; i <= size; i++) {
			table = $('#example' + i).DataTable({
				pageLength : 10,
				'columnDefs' : [ {
					'checkboxes' : {
						'selectRow' : true
					}
				} ],
				'select' : {
					'style' : 'multi'
				}
			});
		}
	}); 
</script>-->
</head>
<body style="overflow: hidden;">
	<div class="page-container">
		<div class="left-content">
			<div class="mother-grid-inner">
				<!-- Header -->
				<jsp:include page="header.jsp" />
				<div class="inner-block">
					<div class="">
						<span style="background-color: #FFFFF; width: 400px; border-radius: 14px;">
						 <i class="fa fa-floppy-o" aria-hidden="true"></i> 
						  <span 	style="width: 200px; border: 1px solid #FFFFFF">Excel / <a href="datatables">Datasheet</a></span> 
						    <span style="color: green;font-weight: bold;" id="msgId">${msg}</span>
						   </span>
						   <div class="row">
							<div class="col-md-12" style="overflow: auto; height: 450px;">
								<br />
								<ul id="myUL">
									<c:forEach items="${sheetValueList}" var="dataSheet"
										varStatus="count">
										<li>&nbsp;&nbsp;&nbsp;<span class="box"><a
												href="javascript:void(0)">${dataSheet.key}</a></span>
											<ul class="nested">
												<li>
													<div>
														<table id="example${count.count}"
															class="table table-striped table-bordered"
															style="width: 100%;font-size: 12px;">
															<c:forEach items="${dataSheet.value}" var="entryVal"
																varStatus="col">
																<!--  <li>  -->
																<thead class="thead-dark" style="background: dodgerblue">
																	<tr>
																		<c:forEach items="${entryVal.value}"
																			var="sheetDataHeader" varStatus="dd">
																			<c:if test="${col.count==1}">
																				<th>${sheetDataHeader.key}</th>
																			</c:if>
																		</c:forEach>
																	</tr>
																</thead>
																<tbody>
																	<tr>
																		<c:forEach items="${entryVal.value}"
																			var="sheetDataValue" varStatus="row">
																			<td><a href="javascript:void(0)"
																				onclick="openModel('${dataSheet.key}','${sheetDataValue.value}','${col.count}','${row.index}')">
																				  <c:choose>
																				    <c:when test="${not empty sheetDataValue.value}">
																				     <span id="copyNewVal${dataSheet.key}${col.count}${row.index}">${sheetDataValue.value}</span>  
																				    </c:when>
																				    <c:otherwise>
																				        <span id="copyNewVal${dataSheet.key}${col.count}${row.index}">	
																				        <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
																				       </span>
																				    </c:otherwise>
																				  </c:choose>
																				</a>
																				
																				</td>
																		</c:forEach>
																	</tr>
																</tbody>
																<!-- </li> -->
															</c:forEach>
														</table>
													</div>
												</li>
											</ul></li>
									</c:forEach>

								</ul>
								<div style="padding-top: 20px; padding-bottom: 20px;">
									<p></p>
									<p></p>
									<pre>
										<span>All the data in excel will be displayed above.</span>
									</pre>
									<p></p>
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

		<!-- Modal -->
		<div id="editModel" class="modal">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content" style="width: 500px;">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLongTitle">
							Edit sheet StosSheet <small style="color: blue" id="tt"
								class="form-text text-muted"> Note: before saving, close
								StosData sheet manually!</small>
						</h5>
						<span class="error" id="sheetErrorId"></span>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label for="sheetName">Sheet Name</label> <input type="text"
								id="sheetNameId" name="sheetName" class="form-control"
								readonly="readonly" />
						</div>
						<div class="form-group">
							<label for="oldValue">Old Excel Value</label> <input type="text"
								name="oldValue" id="oldValueId" class="form-control" readonly="readonly" />
						</div>
							 <input type="text" name="rowValue" id="rowValueId" style="display: none" />
							 <input type="text" name="colValue" id="colValueId"  style="display: none" />
						
						<div class="form-group">
							<label for="newValue">New Excel Value</label> 
							<input type="text" 
								name="newValue" id="newValueId" class="form-control" required="required"  />
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" id="closeId" class="btn btn-secondary"
							data-dismiss="modal">Close</button>
						<button type="button" id="saveSheetId" class="btn btn-success">Save
							changes</button>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>

<script type="text/javascript">
	var toggler = document.getElementsByClassName("box");
	var i;
	for (i = 0; i < toggler.length; i++) {
		toggler[i].addEventListener("click", function() {
			this.parentElement.querySelector(".nested").classList
					.toggle("active");
			this.classList.toggle("check-box");
		});
	}
	function openModel(sheet,oldVal, row, col) {
		$("#newValueId").val("");
		$("#sheetErrorId").text("");
		$("#editModel").show();
		$("#sheetNameId").val(sheet);
		$("#oldValueId").val(oldVal);
		$("#rowValueId").val(row);
		$("#colValueId").val(col);
	}

	$(function() {
		$("#closeId").click(function() {
			$("#editModel").hide();
		});
		$("#saveSheetId").click(function() {
		 var row=$("#rowValueId").val();
		 var col=$("#colValueId").val();
		 var newV=escape($("#newValueId").val());
		  if(newV==""){
				$("#newValueId").focus();
				$("#sheetErrorId").text("Field value required...");
				return false;
			}
			$.ajax({
				type : "GET",
				url : 'updatedatasheet?sheetName='+$("#sheetNameId").val()+'&rowValueId='+$("#rowValueId").val()+'&colValueId='+$("#colValueId").val()+'&newValueId='+newV,
				cache : false,
				async : true,
				success : function(data) {
					if (data == 'OK') {
						$("#copyNewVal"+$("#sheetNameId").val()+row+col).html($("#newValueId").val());
						$("#copyNewVal"+$("#sheetNameId").val()+row+col).addClass("bg-success")
						$("#msgId").html("Data updated successfully..")
						$("#editModel").hide();
					}else{
						$("#sheetErrorId").text(data);
					}
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
</html>
