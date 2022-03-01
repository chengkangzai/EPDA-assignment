
<%@page import="Services.SHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String sales = SHelper.getOnce(request, "report:sales").toString();
    String dates = SHelper.getOnce(request, "report:dates").toString();
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Report</title>
        <jsp:include page="../include/style.jsp" />
    </head>
    <jsp:include page="../include/nav.jsp" />
    <body>
        <div class='mx-auto container'>
            <canvas id="myChart"></canvas>
        </div>
        <script>
            const labels = <% out.println(dates); %>;
            const data = {
                labels: labels,
                datasets: [{
                        label: 'Sales report',
                        data: <% out.println(sales); %>,
                        fill: false,
                        borderColor: 'rgb(75, 192, 192)',
                        tension: 0.1
                    }]
            };

            const config = {
                type: 'line',
                data: data,
                options: {}
            };

            const myChart = new Chart(document.getElementById('myChart'),config);
        </script>
    </body>
</html>
