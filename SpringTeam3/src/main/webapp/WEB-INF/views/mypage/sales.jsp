<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.bundle.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"></script>
<style type="text/css">
 canvas{
 	text-align: center;
 }

</style>
</head>
<body>


<canvas id="chart_day" width="1500" height="600"></canvas>
<canvas id="chart_month" width="1500" height="600"></canvas>
<canvas id="chart_year" width="1500" height="600"></canvas>
<script type="text/javascript">
$(document).ready(function() {	
	


		
		$.ajax({
			type : 'get',
			url : '/mypage/sales',
			data : {
			
			},
			dataType : 'text',
			success: function() {
				$.getJSON("/mypage/sales/chart_day", function(data) {
					
					
					var arr = new Array();
					for (var i = 0; i < data.length; i++) {
						arr[i] = data[i].sellDate;
					}
					
					var brr = new Array();
					for (var i = 0; i < data.length; i++) {
						brr[i] = data[i].aPrice;
					}
					
				    var ctx = document.getElementById('chart_day');
					var myChart = new Chart(ctx, {
					
						type: 'line',
						data: {
							labels:arr,
							datasets: [{
								label: '일 매출',
								data: brr,
								backgroundColor: [
									'rgba(255, 99, 132, 0.2)',
									'rgba(54, 162, 235, 0.2)',
									'rgba(255, 206, 86, 0.2)',
									'rgba(75, 192, 192, 0.2)',
									'rgba(153, 102, 255, 0.2)',
									'rgba(255, 159, 64, 0.2)',
									'rgba(255, 159, 100, 0.2)',
									'rgba(255, 179, 120, 0.2)',
									'rgba(255, 199, 180, 0.2)',
									'rgba(255, 120, 90, 0.2)',
									'rgba(255, 50, 50, 0.2)',
									'rgba(255, 100, 140, 0.2)'
									
								],
								borderColor: [
									'rgba(255, 99, 132, 1)',
									'rgba(54, 162, 235, 1)',
									'rgba(255, 206, 86, 1)',
									'rgba(75, 192, 192, 1)',
									'rgba(153, 102, 255, 1)',
									'rgba(255, 159, 64, 1)',
									'rgba(255, 159, 100, 1)',
									'rgba(255, 179, 120, 1)',
									'rgba(255, 199, 180, 1)',
									'rgba(255, 120, 90, 1)',
									'rgba(255, 50, 50, 1)',
									'rgba(255, 100, 140,1)'
								],
								borderWidth: 1
							}]
						},
						options: {
							responsive: false,
							scales: {
								yAxes: [{
									ticks: {
										beginAtZero: true
									}
								}]
							},
						}
				})

				});


				
			}
		});
		


	
		$.ajax({
			type : 'get',
			url : '/mypage/sales',
			data : {
				
			},
			dataType : 'text',
			success: function() {
				$.getJSON("/mypage/sales/chart_month", function(data) {
					
					
					var arr = new Array();
					for (var i = 0; i < data.length; i++) {
						arr[i] = data[i].sellDate.substring(5,7)+'월';
					}
				
					var brr = new Array();
					for (var i = 0; i < data.length; i++) {
						brr[i] = data[i].aPrice;
					}
				    var ctx = document.getElementById('chart_month');
					var myChart = new Chart(ctx, {
					
						type: 'line',
						data: {
							labels:arr,
							datasets: [{
								label: '월 매출',
								data: brr,
								backgroundColor: [
									'rgba(255, 99, 132, 0.2)',
									'rgba(54, 162, 235, 0.2)',
									'rgba(255, 206, 86, 0.2)',
									'rgba(75, 192, 192, 0.2)',
									'rgba(153, 102, 255, 0.2)',
									'rgba(255, 159, 64, 0.2)',
									'rgba(255, 159, 100, 0.2)',
									'rgba(255, 179, 120, 0.2)',
									'rgba(255, 199, 180, 0.2)',
									'rgba(255, 120, 90, 0.2)',
									'rgba(255, 50, 50, 0.2)',
									'rgba(255, 100, 140, 0.2)'
									
								],
								borderColor: [
									'rgba(255, 99, 132, 1)',
									'rgba(54, 162, 235, 1)',
									'rgba(255, 206, 86, 1)',
									'rgba(75, 192, 192, 1)',
									'rgba(153, 102, 255, 1)',
									'rgba(255, 159, 64, 1)',
									'rgba(255, 159, 100, 1)',
									'rgba(255, 179, 120, 1)',
									'rgba(255, 199, 180, 1)',
									'rgba(255, 120, 90, 1)',
									'rgba(255, 50, 50, 1)',
									'rgba(255, 100, 140,1)'
								],
								borderWidth: 1
							}]
						},
						options: {
							responsive: false,
							scales: {
								yAxes: [{
									ticks: {
										beginAtZero: true
									}
								}]
							},
						}
				})

				});


				
			}
		});
			


	$.ajax({
		type : 'get',
		url : '/mypage/sales',
		data : {
			
		},
		dataType : 'text',
		success: function() {
			$.getJSON("/mypage/sales/chart_year", function(data) {
				
				var arr = new Array();
				for (var i = 0; i < data.length; i++) {
					arr[i] = data[i].sellDate+'년';
				}
			
				var brr = new Array();
				for (var i = 0; i < data.length; i++) {
					brr[i] = data[i].aPrice;
				}
			    var ctx = document.getElementById('chart_year');
				var myChart = new Chart(ctx, {
				
					type: 'line',
					data: {
						labels:arr,
						datasets: [{
							label: '연 매출',
							data: brr,
							backgroundColor: [
								'rgba(255, 99, 132, 0.2)',
								'rgba(54, 162, 235, 0.2)',
								'rgba(255, 206, 86, 0.2)',
								'rgba(75, 192, 192, 0.2)',
								'rgba(153, 102, 255, 0.2)',
								'rgba(255, 159, 64, 0.2)',
								'rgba(255, 159, 100, 0.2)',
								'rgba(255, 179, 120, 0.2)',
								'rgba(255, 199, 180, 0.2)',
								'rgba(255, 120, 90, 0.2)',
								'rgba(255, 50, 50, 0.2)',
								'rgba(255, 100, 140, 0.2)'
								
							],
							borderColor: [
								'rgba(255, 99, 132, 1)',
								'rgba(54, 162, 235, 1)',
								'rgba(255, 206, 86, 1)',
								'rgba(75, 192, 192, 1)',
								'rgba(153, 102, 255, 1)',
								'rgba(255, 159, 64, 1)',
								'rgba(255, 159, 100, 1)',
								'rgba(255, 179, 120, 1)',
								'rgba(255, 199, 180, 1)',
								'rgba(255, 120, 90, 1)',
								'rgba(255, 50, 50, 1)',
								'rgba(255, 100, 140,1)'
							],
							borderWidth: 1
						}]
					},
					options: {
						responsive: false,
						scales: {
							yAxes: [{
								ticks: {
									beginAtZero: true
								}
							}]
						},
					}
			})

			});


			
		}
	});
	

});


</script>
</body>
</html>