<!-- Trigger/Open The Modal -->
<style>
.modal {
	overflow-y: auto;
}
</style>
<div id="myModal" class="modal">
	<!-- Modal content -->
	<div class="modal-content" style="width: 130px;">
		<div id="textId">
			<span style="font-size: 12px; font-weight: bold;">Restarting...&nbsp;&nbsp;&nbsp;</span>
			<div>
				<a> <img height="70px" width="70px" src="/images/spin.gif">
				</a>
			</div>
		</div>
	</div>
</div>
<div class="modal modal-body" id="myModal2" role="dialog">
	<div class="container-fluid" role="document">
		<!-- Modal content-->
		<div class="modal-content" style="width: 660px;">
			<div class="modal-header">
			           <div id="errorId" class='error' style="text-align: center"></div>
			          <div id="spinId"  style="text-align: center;display: none">
						<a> <img height="50px" width="50px" src="/images/spin.gif">
							</a>
						</div>
				<h4 class="modal-title" style="text-overflow:ellipsis;">Jira and Confluence Operation for TC&nbsp;<span id='tcName'></span>
				 <input type="hidden" id='tcId'/>
				</h4>
				<p style='font-size: 12px;color: red'>Note: Please validate properties file for Jira/Confluence Username and Password related setting(s)</p>
			</div>
			<div class="modal-body">
				<p style="color:blue">Jira Configuration</p>
				<div class="checkbox">
					<label><input type="checkbox" id="jiraCreatelCheckId" required="required">Create ticket to Jira portal with this report</label>
				</div>
				<div class="form-group">
					<label for="comment">Comment:</label>
					<textarea class="form-control" rows="2" id="jirComment"></textarea>
				</div>
				<p style="color:blue"> Confluence Configuration</p>
				<div class="checkbox">
					<label><input type="checkbox" id="confuUploadCheckId" required="required">Upload report file to Confluence portal</label>
				   
				</div>
				<div class="form-group">
					<label for="comment">Comment:</label>
					<textarea class="form-control" rows="2" id="confluenceComment"></textarea>
				</div>
				
				<div  class="form-group">
				    <label><input type="checkbox" id="emailCheckId">Send Alert for Jira Ticket Creaton and File upload to Confluence portal</label>	
				    <input  class="form-control" type="text" placeholder="ias@foo.com"  name="emailText" id="emailTextId" style="display:none" />
				</div>
			</div>
		<div style="float: right;">
			<button type="button" class="btn btn-success" id="processId">Process</button>
			<button type="button" class="btn btn-default" data-dismiss="modal"
				id="closeMyModel">Close</button>
				
		</div>
		<br/>
		</div>
	</div>
</div>
<div class="modal modal-body" id="myModal3" role="dialog">
				   	 <form method="post" enctype="multipart/form-data"   id="uploadForm">
	<div class="container-fluid" role="document">
		<!-- Modal content-->
		<div class="modal-content" style="width: 650px;">
			<div class="modal-header">
			 <div id="errorId2" class='error' style="text-align: center"></div>
			          <div id="spinId2"  style="text-align: center;display: none">
						<a> Uploading...Wait!!!<img height="50px" width="50px" src="/images/spin.gif"></a>
						</div>
				<h4 class="modal-title">Upload Excel file <span id='tcName'></span>
				<input type="hidden" id='tcId'/>
				
				</h4>
			</div>
			<div class="modal-body">
					  <div class="custom-file custom-file mb-3" >
					    <input type="file" class="custom-file-input" name="file" id="customFile"  accept=".xls,.xlsx" required="required">
					  </div>
			</div>
		<div style="float: right;">
			<button type="submit" class="btn btn-success" id="uploadId">Upload</button>
			<button type="button" class="btn btn-default" data-dismiss="modal"
				id="closeMyModel3">Close</button>
				
		</div>
		<br/>
		</div>
	</div>
</form>
</div>
