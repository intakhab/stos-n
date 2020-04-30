<script type="text/javascript">
$("#emailCheckedId").click(function(e) {
	if ($('#emailCheckedId').prop('checked')) {
		$('#emailCheckedTextAreaId').show();
		$('#toWhomEmailId').prop('required',true);
	} else {
		$('#emailCheckedTextAreaId').hide();
		$('#toWhomEmailId').val("");
	    $('#toWhomEmailId').prop('required',false);
	}
});

$("#enableCronId").click(function(e) {
	if ($('#enableCronId').prop('checked')) {
		$('#cronDivId').show();
	} else {
		$('#cronDivId').hide();
	}
});

$("#enableNodeId").click(function(e) {
	if ($('#enableNodeId').prop('checked')) {
		$('#nodeDivId').show();
	} else {
		$('#nodeDivId').hide();
	}
});
$("#enableCronSeqId").click(function(e) {
	if ($('#enableCronSeqId').prop('checked')) {
		$('#cronDivSeqId').show();
	} else {
		$('#cronDivSeqId').hide();
	}
});



function confirmMsg(msg){
	var message="<i class='fa fa-warning aria-hidden='true' style='font-size:35px;color:red'></i>&nbsp;&nbsp;<span class='error'>You have changed "+msg+" timing !!! <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; STOS will be re-started automcatically to get the latest value!<span>";
	   $('<div></div>').appendTo('body').html('<div><h6>'+message+'</h6></div>')
	    .dialog({
	        modal: true, 
	        title: 'Confirmation',
	        zIndex: -1, 
	        autoOpen: true,
	        width: 'auto', 
	        resizable: false,
	        buttons: {
	            Yes: function () {
	            	$("#cronHit").val(true);
	              //  document.getElementById("configForm").submit();
	                $("#configForm").submit();
	            },
	            No: function (){
	            	//ob.val(textVal);
	                $(this).dialog("close");
	            }
	        }
	    });
	
}

function chckTab(id){
	$('#ecomTabId1').removeClass("active");
	$('#emailTabId1').removeClass("active");
	$('#settingsTabId1').removeClass("active");
	$('#nodeTabId1').removeClass("active");
	$("#"+id).addClass("active");
	$("#tabId").val(id);
 };
 /****
  * This will fire cron error
  */
 function cronError(){
 	var message="<i class='fa fa-info-circle' aria-hidden='true' style='font-size:35px;color:red'></i>&nbsp;&nbsp;Input Cron Value is incorrect Please put one sapce in between characters like.. * * * * * *  ";
 	   $('<div></div>').appendTo('body').html('<div><h6>'+message+'</h6></div>')
 	    .dialog({
 	        modal: true, 
 	        title: 'Information ',
 	        zIndex: -1,
 	        width: 'auto', 
 			cache : false,
 	        autoOpen: true,
 	        buttons: { 
 	           OK: function () {
                 $(this).dialog("close");

                }
 	        }
 	    });
 	
 }
$(document).ready(function() {
	$(".msgc" ).change(function() {
        var idx=$(this).attr("id");
		$.ajax({
			type : "GET",
			url : 'cronvalidator?param='+$(this).val(),
			cache : false,
			async : true,
			success : function(data) {
				if(data==false){
					$("#"+idx).css("background-color", "red");
					 cronError();
	                 $(":submit").attr("disabled", true);
				}else{
				    $(":submit").removeAttr("disabled");
					$("#"+idx).css("background-color", "white");
				}
	             
			}
		});
	});
	
	$("#tab4").addClass("active");
	$("#tab1").removeClass("active");
	$("#re-loadId").click(function(e) {
		window.location.href="settings";

	});
    
    $('#submitButtonId').click(function () {
        $('input:invalid').each(function () {
            // Find the tab-pane that this element is inside, and get the id
            var $closest = $(this).closest('.tab-pane');
            var id = $closest.attr('id');
            // Find the link that corresponds to the pane and have it show
            $('.nav a[href="#' + id + '"]').tab('show');
            $("#ecomTabId1").removeClass("active");
            $("#nodeTabId1").removeClass("active");
			$('#emailTabId1').removeClass("active");
			$('#settingsTabId1').removeClass("active");
			$("#"+id+"1").addClass("active");
				// Only want to do it once
				return false;
			});
		});
});//End

/***
 * 
 */
$( "#configForm" ).submit(function( event ) {
	   event.preventDefault();
   var message="<i class='fa fa-info' aria-hidden='true' style='font-size:35px;color:red'></i>&nbsp;&nbsp;Are you sure want to save the Settings Configuration?";
	     $('<div></div>').appendTo('body').html('<div><h6>'+message+'</h6></div>')
    .dialog({
        modal: true, title: 'Confirmation', zIndex: 10000, autoOpen: true,
        width: 'auto', 
        resizable: false,
        buttons: {
            Yes: function () {
                $(this).dialog("close");
                $("#cronHit").val(true);
                document.getElementById("configForm").submit();
            },
            No: function () {                           		                      
                $(this).dialog("close");
            }
        },
        close: function (event, ui) {
            $(this).remove();
        }
    });
  });
  //
$(window).bind("load", function() {
	if($("#cronHit").val()=="true" || $("#cronHit").val()==true){
		restart();
	}
	var tt='${param.tabId}';
	if(tt!=""){
		var tabsId= tt.substring(0, tt.length - 1);
        $("a[href='#"+tabsId+"']").trigger("click");
	}
	if($("#checkBoxId").val().trim()==true || $("#checkBoxId").val().trim()=="true"){
	     $('#emailCheckedTextAreaId').show();
	     $('#emailCheckedId').prop('checked', true);
		 $('#toWhomEmailId').prop('required',true);
	}else{
  	     $('#emailCheckedTextAreaId').hide();
  	     $('#emailCheckedId').prop('checked' ,false)
		 $('#toWhomEmailId').prop('required',false);
	}
	if($("#checkBoxId2").val().trim()==true || $("#checkBoxId2").val().trim()=="true"){
	     $('#cronDivId').show();
	     $('#enableCronId').prop('checked' ,true)
	}else{
 	     $('#cronDivId').hide();
  	     $('#enableCronId').prop('checked' ,false)
	}	
	if($("#checkBoxId3").val().trim()==true || $("#checkBoxId3").val().trim()=="true"){
	     $('#nodeDivId').show();
	     $('#enableNodeId').prop('checked' ,true)
	}else{
	     $('#nodeDivId').hide();
 	     $('#enableNodeId').prop('checked' ,false)
	}	
	if($("#checkBoxId4").val().trim()==true || $("#checkBoxId4").val().trim()=="true"){
	     $('#cronDivSeqId').show();
	     $('#enableCronSeqId').prop('checked' ,true)
	}else{
	     $('#cronDivSeqId').hide();
 	     $('#enableCronSeqId').prop('checked' ,false)
	}
}); 
$(document).ready(function() {
    var max_fields      = 3; //maximum input boxes allowed
    var wrapper         = $(".input_fields_wrap"); //Fields wrapper
    var add_button      = $(".add-more"); //Add button ID
    var x = 0; //initlal text box count
    $(add_button).click(function(e){ //on add input button click
        e.preventDefault();
        if(x < max_fields){ //max input box allowed
            x++; //text box increment
            $(wrapper).append('<p></p><div class="form-group">'
					+'<div class="entry input-group">'
					+'<label for="action_id"></label>'
					+'<input list="groupsname" id="groupsnameId'+x+'" name="groupList['+x+']"  type="text" class="form-control input-md" required="required" />'
					+'<span class="input-group-btn">'
					+'<input id="action_id'+x+'" style="width: 250px;" name="cronList['+x+']" class="form-control msgc" required="required" />'
					+'<button class="btn btn-danger remove_field" type="button"><span class="glyphicon glyphicon-minus"></span>	</button>'
					+'</span>'
					+'</div>'
					+'</div>'); //add input box
        }else{
        	commonConfirmation("Maximun supports in this version");
        }
    });
    $(wrapper).on("click",".remove_field", function(e){ //user click on remove text
           e.preventDefault(); 
           $(this).parent('span').parent('div').remove();x--;
    })
});

$(document).ready(function() {
    var max_fields      = 9; //maximum input boxes allowed
    var wrapper         = $(".input_fields_wrap2"); //Fields wrapper
    var add_button      = $(".add-more2"); //Add button ID
    var x = 0; //initlal text box count
    $(add_button).click(function(e){ //on add input button click
        e.preventDefault();
        if(x < max_fields){ //max input box allowed
            x++; //text box increment
            $(wrapper).append('<p></p><div class="form-group">'
					+'<div class="entry input-group">'
					+'<span class="input-group-btn">'
					+'<input id="action_id" name="nodeList['+x+']" class="form-control" placeholder="http://2SRDPJ2K602610:6200" required="required" />'
					+'<button class="btn btn-danger remove_field2" type="button"><span class="glyphicon glyphicon-minus"></span</button>'
					+'<button id="test-more" name="test" class="btn btn-warning" type="button"><span class="fa fa-circle"></span></button>'
					+'</span>'
					+'</div>'
					+'</div>'); //add input box
           // legnth++;
        }else{
        	commonConfirmation("Maximun supports in this version")
        }
    });
    $(wrapper).on("click",".remove_field2", function(e){ //user click on remove text
           e.preventDefault(); 
           $(this).parent('span').parent('div').remove();x--;
    })
   
});

</script>