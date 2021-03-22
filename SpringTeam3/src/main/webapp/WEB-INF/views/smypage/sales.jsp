<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<div style="width: 60%">

		<div>

			<canvas id="canvas" height="450" width="600"></canvas>

		</div>

</div>
<script type="text/javascript">
var chartLabels = [];

var chartData = [];

$.getJSON("/mypage/sales", function(data) {

	$.each(data, function(inx, obj) {

		chartLabels.push(obj.updatedate);

		chartData.push(obj.aprice);

	});

	createChart();

	console.log("create Chart")

});

var lineChartData = {

	labels : chartLabels,

	datasets : [

	{

		label : "Date Income",

		fillColor : "rbga(151,187,205,0.2)",

		strokeColor : "rbga(151,187,205,1)",

		pointColor : "rbga(151,187,205,1)",

		pointStrokeColor : "#fff",

		pointHighlightFill : "#fff",

		pointHighlightStroke : "rbga(151,187,205,1)",

		data : chartData

	}

	]

}

function createChart() {

	var ctx = document.getElementById("canvas").getContext("2d");

	LineChartDemo = Chart.Line(ctx, {

		data : lineChartData,

		options : {

			scales : {

				yAxes : [ {

					ticks : {

						beginAtZero : true

					}

				} ]

			}

		}

	})

}

</script>
</body>
</html>