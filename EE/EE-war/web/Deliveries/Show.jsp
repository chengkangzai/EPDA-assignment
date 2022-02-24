<%-- 
    Document   : Show
    Created on : Feb 23, 2022, 5:31:43 PM
    Author     : CCK
--%>

<%@page import="middleware.Gate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Deliveries</title>
        <jsp:include page="../include/style.jsp" />
    </head>
    <body>
        <jsp:include page="../include/nav.jsp" />
        <% Gate.authorise(request, response, "Read Delivery");%>

        <div class="pt-10">
            <h2 class="text-3xl font-bold text-center">View Delivery Details</h2>
        </div>
    </body>
</html>
