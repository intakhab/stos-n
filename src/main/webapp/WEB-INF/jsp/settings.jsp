<!DOCTYPE html>
<html>
<head>
<title>STOS : Settings Page</title>
<%@ include file="lib.jsp"%>
</head>
<body>
	<div class="page-container">
		<div class="left-content">
			<div class="mother-grid-inner">
				<!-- Header -->
				<jsp:include page="header.jsp"></jsp:include>
				<div class="inner-block">
					<div style="padding-left: 250px;">
						<nav aria-label="breadcrumb" style="width: 70%">
							<ol class="breadcrumb">
								<li><a href="settings"> <i class="fa fa-cog"
										aria-hidden="true"></i> Settings
								</a></li>
								<li class="breadcrumb-item active" aria-current="page">Configuration
									for Automation</li>
							</ol>
						</nav>
						<div class="col-sm-8">
							<div>${msg}</div>
							<ul class="nav nav-tabs" id="navId">
								<li id="ecomTabId1" class="active"
									onclick="chckTab('ecomTabId1')"><a data-toggle="tab"
									href="#ecomTabId" title="Ecom Setting"> <i
										class="fa fa-book fa-fw" aria-hidden="true"></i> Ecom
								</a></li>

								<li id="emailTabId1" onclick="chckTab('emailTabId1')"><a
									data-toggle="tab" href="#emailTabId" title="Email Setting">
										<i class="fa fa-book fa-fw" aria-hidden="true"></i> Email
								</a></li>
								
								<li id="settingsTabId1" onclick="chckTab('settingsTabId1')"><a
									data-toggle="tab" href="#settingsTabId" title="Mis Setting">
										<i class="fa fa-book fa-fw" aria-hidden="true"></i>Schedular
								</a>
								</li>
								<li id="nodeTabId1" onclick="chckTab('nodeTabId1')"><a
									data-toggle="tab" href="#nodeTabId" title="Mis Setting">
										<i class="fa fa-book fa-fw" aria-hidden="true"></i>Node Server
								</a>
								</li>
							</ul>

							<form:form name="form" role="form" method="POST" id="configForm"
								action="savesetting" modelAttribute="infoObj">
								<form:hidden path="tabId" id="tabId" />
								<form:hidden path="settingsId" />
								<form:hidden path="cronHit" id="cronHit" />
								<input type="hidden" id="tabIds" value="${tabId}" />
								<div class="tab-content">
									<br>
									<!-- Start Tab 1 -->
									<div id="ecomTabId" class="tab-pane active">
										<div>
											
											<div class="form-group">
												<label for="jdeURL">Ecom Portal User ID</label>
												<form:input path="appUserId" class="form-control"
													id="appUserId" placeholder="Enter User ID"
													required="required" />
											</div>
											<div class="form-group">
												<label for="jdeURL">Ecom Portal User password</label>
												<form:password path="appUserPass" class="form-control"
													id="userPass" placeholder="Enter User password"
													required="required" value="${infoObj.appUserPass}" />
											</div>
											<div>

												<div class="form-group">
													<label for="browserType">Browser Type</label>
													<form:select path="browserType" class="form-control"
														id="browserType" required="required">
														<form:option value="chrome">Chrome</form:option>
														<form:option value="firefox">Firefox</form:option>
														<form:option value="ie">IE</form:option>
														<form:option value="edge">Edge</form:option>
													</form:select>
												</div>

												<div class="form-group">
													<label for="envType">Ecom Portal Environment</label>
													<form:select path="envType" class="form-control"
														id="envType" required="required" onchange="changeEnv()">
														<form:option value="DEV">DEV</form:option>
														<form:option value="QA">QA</form:option>
														<form:option value="PROD">PROD</form:option>
														<form:option value="SIT">SIT</form:option>
														<form:option value="UAT">UAT</form:option>
													</form:select>
												</div>
												<div class="form-group">
												   <label for="envURL">Ecom Portal Environment URL</label>
												   <form:input path="envUrl" class="form-control" id="envUrl"
													placeholder="Enter URL" required="required" />
													</div>
													<div class="form-group">
												  	 <label for="dataPath">Ecom Portal Data Path</label>
												   	<form:input path="dataPath" class="form-control" id="dataPath"
														placeholder="Enter Data Path (XLS Path)" required="required" />
													</div>
													<div class="form-group">
												  	 <label for="dataPath">Ecom Portal Reports Path</label>
												   	<form:input path="reportsPath" class="form-control" id="reportsPath"
														placeholder="Enter Reports Path" required="required" />
													</div>
												
											</div>
											<br />
										</div>
										<br />
									</div>

									<div id="emailTabId" class="tab-pane">
										<div class="form-group">
											<input type="hidden" id="checkBoxId"
												value="${infoObj.enableMail}" />
											<form:checkbox path="enableMail" value="${enableMail}"
												class="form-check-input" id="emailCheckedId" />
											<label class="form-check-label" for="emailCheckedId">Enable
												Email for Ecom Reports</label>
											<div id="emailCheckedTextAreaId">
												<div class="input-group">
													<span class="input-group-addon">@</span>
													<form:input type="email" path="toWhomEmail"
														class="form-control" id="toWhomEmailId"
														placeholder="Send Email ias@hcl.com,foo@hcl.com"
														required="required" aria-describedby="toWhomEmailIns"
														multiple="multiple" />
												</div>
												<small id="toWhomEmailIns" title="${toWhomEmail}"
													class="form-text text-muted"> <span class="impo">Write
														email id with comma separator</span> i.e <strong>ias@hcl.com,
														foo@hcl.com</strong>
												</small>
												<div class="form-row row">
													<div class="form-group col-md-4">
														<label for="host">Mail Host</label>
														<form:input path="host" class="form-control" id="hostId"
															placeholder="Email Host" />
													</div>
													<div class="form-group col-md-4">
														<label for="host">Mail Port</label>
														<form:input path="port" class="form-control" id="portId"
															placeholder="Email Port" />
													</div>
													<div class="form-group col-md-4">
														<label for="host">Debug Mail</label>
														<form:select path="debugMail" class="form-control"
															id="debugMailId">
															<form:option value="true">ON</form:option>
															<form:option value="false">OFF</form:option>

														</form:select>
													</div>
												</div>
												<div class="form-row row">
													<div class="form-group col-md-4">
														<label for="host">Mail Username</label>
														<form:input path="mailUserName" class="form-control"
															id="mailUserNameId" placeholder="Mail Username" />
													</div>
													<div class="form-group col-md-4">
														<label for="host">Mail Password</label>
														<form:input path="mailPassword" class="form-control"
															id="mailPasswordId" placeholder="Mail Password"
															autocomplete="off" />
													</div>
													<div class="form-group col-md-4">
														<label for="host">Mail from</label>
														<form:input path="fromMail" class="form-control"
															id="fromMailId" placeholder="Mail From" />
													</div>
												</div>
											</div>
										</div>
										<br /> 
									</div>
									<div id="settingsTabId" class="tab-pane">
										<div  class="form-group">
										   <input type="hidden" id="checkBoxId2" value="${infoObj.enableCron}" />
											<form:checkbox path="enableCron" value="${enableCron}"
												class="form-check-input" id="enableCronId" />
										
											<label class="form-check-label" for="enableCronId">Enable scheduling for Test Suite Run</label>
										<div id="cronDivId">		
										<div class="form-group input_fields_wrap">
										<label for="action_id"><i class="fa fa-info-circle" aria-hidden="true"></i>
										Schedular Configuration<br/></label>
									<small id="tt" class="form-text text-muted">
									<span style="color:blue"><br/>Cron Time</span> [<span style="color:red">second, minute, hour, day, month, weekday</span> ex 1. [00 05 21 * * *] ex 2. [ * */1 * * * *]]</small>	
									<c:if test="${fn:length(infoObj.groupList) eq 0}">
         									  <div class="entry input-group form-inline">
												<input  list="groupsname" id="groupsnameId${loop.index}" name="groupsname" value="groupList[0]"
													placeholder="Select Suite Group"
													class="form-control input-md" />
														<datalist id="groupsname">
															<c:forEach items="${groupList}" var="dt">
																<option hidden="${dt.groupId}">${dt.groupname}</option>
															</c:forEach>
														</datalist>
														<span class="input-group-btn">
												  <input id="action_id${loop.index}" style="width: 250px;" name="cronList[0]"
													class="form-control " placeholder="00 20 20 * * *" required="required"/>
													<button id="add-more" name="add-more" class="btn btn-success add-more msgc" type="button">
														<span class="glyphicon glyphicon-plus" ></span>
													</button>
												</span>
											</div>
								        </c:if>	
								       </div>
						              <c:if test="${fn:length(infoObj.groupList) gt 0}">
										<c:forEach items="${infoObj.groupList}" varStatus="loop">
											<div class="entry input-group form-inline">
												<form:input list="groupsname" id="groupsnameId${loop.index}" path="groupList[${loop.index}]" 
													placeholder="Select Suite Group"
													class="form-control input-md" />
														<datalist id="groupsname">
															<c:forEach items="${groupList}" var="dt">
																<option hidden="${dt.groupId}">${dt.groupname}</option>
															</c:forEach>
														</datalist>
														<span class="input-group-btn"> <form:input
														id="action_id${loop.index}" style="width: 250px;"
														path="cronList[${loop.index}]" class="form-control msgc"
														placeholder="00 20 20 * * *" />
													<button id="add-more" name="add-more"
														class="btn btn-success add-more" type="button">
														<span class="glyphicon glyphicon-plus"></span>
													</button>
												</span>
											</div>
											<p></p>
												</c:forEach>
											</c:if>
										</div>
										<div style="padding-top: 20px;">
										<input type="hidden" id="checkBoxId4" value="${infoObj.enableCronSeq}" />
											<form:checkbox path="enableCronSeq" value="${enableCronSeq}"
												class="form-check-input" id="enableCronSeqId" />
										
											<label class="form-check-label" for="enableCronSeqId">Enable Sequential scheduling for Test Suite Run</label>
									  <div id="cronDivSeqId">	
										<small id="tt" class="form-text text-muted">
										<span style="color:blue"><br/>Cron Time</span>
										 [<span style="color:red">second, minute, hour, day, month, weekday</span> ex 1. [00 05 21 * * *] ex 2. [ * */1 * * * *]]
										 </small>	
										  
										       <div class="input-group">
													<span class="input-group-addon">Health Check Cron Timing</span>
													<form:input path="cronSeq"
														class="form-control msgc" id="cronSeqId"
														placeholder="00 05 21 * * *"
														required="required"  />
														<%-- <form:input path="cronSeqSheetName"
														class="form-control" id="cronSeqSheetNameId"
														placeholder="HealthCheck"
														required="required"  />
														<form:input path="cronSeqClassName"
														class="form-control" id="cronSeqClassName"
														placeholder="HealthCheckTest"
														required="required"  disabled="true" /> --%>
												</div>	
										  </div>
										</div>
										</div>
										<br/> 
									</div>
									<div id="nodeTabId" class="tab-pane">
										<div  class="form-group">
										   <input type="hidden" id="checkBoxId3" value="${infoObj.enableNode}" />
											<form:checkbox path="enableNode" value="${enableNode}"
												class="form-check-input" id="enableNodeId" />
										
											<label class="form-check-label" for="enableNodeId">Enable Node for Test Suite Run</label>
										<div id="nodeDivId">		
										<div class="form-group input_fields_wrap2">
										<label for="action_id"><i class="fa fa-info-circle" aria-hidden="true"></i>
										Node (Child Server) Configuration<br/></label>
									<small id="tt" class="form-text text-muted" style="color:red"> http://2SRDPJ2K602610:6200
									</small>	
									<c:if test="${fn:length(infoObj.nodeList) eq 0}">
         									  <div class="entry input-group form-inline">
												<span class="input-group-btn">
												  <!-- <label class="input-group-addon">Node 1</label> -->
												  <input id="nodeId"  name="nodeList"
													class="form-control " placeholder="http://2SRDPJ2K602610:6200" />
													<button id="add-more2" name="add-more"
														class="btn btn-success add-more2" type="button">
														<span class="glyphicon glyphicon-plus" ></span>
													</button>
													<button id="test-more" name="test"
														class="btn btn-warning" type="button">
														<span class="fa fa-circle"></span>
													</button>
												</span>
											</div>
								        </c:if>	
								         </div>
						              <c:if test="${fn:length(infoObj.nodeList) gt 0}">
										<c:forEach items="${infoObj.nodeList}" varStatus="loop">
											<div class="entry input-group form-inline">
													<span class="input-group-btn"> 
													<form:input
														id="action_id" 
														path="nodeList[${loop.index}]" class="form-control "
														placeholder="http://2SRDPJ2K602610:6200" />
													<button id="add-more2" name="add-more"
														class="btn btn-success add-more2" type="button">
														<span class="glyphicon glyphicon-plus"></span>
													</button>
													<button id="test-more" name="test"
														class="btn btn-warning" type="button">
														<span class="fa fa-circle"></span>
													</button>
												</span>
											</div>
											<p></p>
												</c:forEach>
											</c:if>
										</div>
										</div>
										<br/> 
									</div>
								</div>
								<button type="submit" id="submitButtonId" class="btn btn-primary">
									<i class="fa fa-floppy-o" aria-hidden="true"></i> Save Configuration 
								</button>
								<button type="button" id="re-loadId" class="btn btn-success">
									<i class="fa fa-refresh" aria-hidden="true"></i> Refresh Page
								</button>

								<br />
								<br />
								<br />

							</form:form>

						</div>
					</div>

				</div>
				<!--inner block end here-->

			</div>
		</div>

		<!--slider menu-->
		<jsp:include page="leftmenu.jsp"></jsp:include>
		<div class="clearfix"></div>
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
	<jsp:include page="settings-script.jsp"></jsp:include>

</body>
</html>

