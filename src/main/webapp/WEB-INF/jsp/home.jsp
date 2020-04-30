<!DOCTYPE HTML>
<html>
<title>STOS : Home Page</title>
<head>
<%@ include file="lib.jsp"%>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
      google.charts.load("current", {packages:["corechart"]});
      google.charts.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Steps', 'Total Steps'],
          ['Passed',  ${pi.totalPass}],
          ['Failed', ${pi.totalFail}],
          ['Skip',   ${pi.totalSkip}],
        ]);
        var options = {
          title: 'Last run stastistic',
          colors: ['#4CAF50', 'red', 'cyan'],
          is3D: true,
        };
        var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
        chart.draw(data, options);
        google.visualization.events.addListener(chart, 'select', function() {
        	var row = chart.getSelection()[0].row;
        	window.open('showreports?status='+row,'_parent'); //This will open Reports in a  same window.
        	});
      }
    </script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawVisualization);
      function drawVisualization() {
        // Some raw data (not necessarily accurate)
        var data = google.visualization.arrayToDataTable([
          ['TIME','PASS', 'FAIL', 'TOTAL'],
          ${bar}
        ]);
        var options = {
          title : 'Last 10 run statistic',
          vAxis: {title: 'TC'},
          hAxis: {title: 'Days'},
          seriesType: 'bars',
          series: {5: {type: 'line'}},     
          is3D: true,
          colors: ['#4CAF50', 'red', 'blue']

        };
        var chart = new google.visualization.ComboChart(document.getElementById('chart_div'));
        chart.draw(data, options);
        google.visualization.events.addListener(chart, 'select', function() {
        	var row = chart.getSelection()[0].row;
        	window.open('showreports?status='+row,'_parent'); //This will open Reports in a  same window.
        	});
      }
    </script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawVisualization);
      function drawVisualization() {
        // Some raw data (not necessarily accurate)
        var data = google.visualization.arrayToDataTable([
          ['TIME','PASS', 'FAIL', 'TOTAL'],
          ${bar}
        ]);
        var options = {
          title : 'Last 10 run statistic',
          vAxis: {title: 'TC'},
          hAxis: {title: 'Days'},
          seriesType: 'pi',
          series: {5: {type: 'line'}},     
          is3D: true,
          colors: ['#4CAF50', 'red', 'blue']

        };
        var chart = new google.visualization.ComboChart(document.getElementById('chart_div2'));
        chart.draw(data, options);
        google.visualization.events.addListener(chart, 'select', function() {
        	var row = chart.getSelection()[0].row;
        	window.open('showreports','_parent'); //This will open Reports in a  same window.
        	});
      }
    </script>
</head>
<body>
	<div class="page-container">
		<div class="left-content">
			<div class="mother-grid-inner">
				<!-- Header -->
				<jsp:include page="header.jsp" />
					<div class="col-sm-14" spellcheck="false" style="padding-top: 100px;">
							<div class="card">
									    <div id="piechart_3d" style="width: 350px; height: 400px;float: left"></div>
								<!-- <img alt="" src="images/usflogo.png" height="300px;"
									width="400px;" style="opacity: 0.25;"> -->
							</div>
							<div class="card" id="chartDivId">
								 <div  style="float: right ;">
								  <pre>Total no of records: ${totalNoOfRecords}
								     <select id="totalReportsId">
								     <c:forEach begin="1" end="${noOfPages}" var="i">
								        <option value="${(i-1)}-10">${10*i}</option>
								     </c:forEach>
								     </select>
									</pre>
								 </div>
								<div id="chart_div" style="width: 800px; height: 500px;float: right;"></div>
								<div id="chart_div2" style="width: 800px; height: 500px;float: right;"></div>
							</div>
						
					</div>
				<!--inner block end here-->
			</div>
		</div>
		<!--slider menu-->
		<jsp:include page="leftmenu.jsp" />
		<div class="clearfix"></div>
		<jsp:include page="footer.jsp" />
	</div>
	<script type="text/javascript">
	$(document).ready(function() {
		var v = '${param.page}';
		if (v != '') {
			$('#totalReportsId option[value='+v+']').attr('selected','selected');
		}
		$('#totalReportsId').on('change',function () {
			window.location.href="home?page="+$(this).val();
		});
	});
	</script>
</body>
</html>
