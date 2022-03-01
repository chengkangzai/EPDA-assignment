
<%@page import="Services.SHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String sales = SHelper.getOnce(request, "report:sales").toString();
    String dates = SHelper.getOnce(request, "report:dates").toString();
    String productPercentage = SHelper.getOnce(request, "report:productPercentage").toString();
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Report</title>
        <jsp:include page="../include/style.jsp" />
    </head>
    <jsp:include page="../include/nav.jsp" />
    <body>
        <div class='grid px-10 grid-cols-2 gap-8'>
            <div class='w-full'>
                <p class='text-center text-3xl font-semibold my-4'>Sales Report</p>
                <canvas id="myChart"></canvas>
            </div>
            <div class='w-full'>
                <p class='text-center text-3xl font-semibold my-4'>Top Sales by Product</p>
                <canvas id="myChart1"></canvas>
            </div>
        </div>
        <script>
            const config = {
                type: 'line',
                data: {
                    labels: <% out.println(dates); %>,
                    datasets: [{
                            label: 'Sales',
                            data: <% out.println(sales);%>,
                            fill: false,
                            borderColor: 'rgb(75, 192, 192)',
                            tension: 0.1
                        }]
                },
                options: {}
            };

            const myChart = new Chart(document.getElementById('myChart'), config);

            const config1 = {
                type: 'bar',
                data: {
                    datasets: [{
                            label: 'Product',
                            backgroundColor: [
                                'rgba(255, 99, 132, 0.2)',
                                'rgba(255, 159, 64, 0.2)',
                                'rgba(255, 205, 86, 0.2)',
                                'rgba(75, 192, 192, 0.2)',
                                'rgba(54, 162, 235, 0.2)',
                                'rgba(153, 102, 255, 0.2)',
                                'rgba(201, 203, 207, 0.2)'
                            ],
                            data:<% out.println(productPercentage);%>
                        }]
                }
            };

            const myChart1 = new Chart(document.getElementById('myChart1'), config1);
        </script>
    </body>
</html>
