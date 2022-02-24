<%-- 
    Document   : Show
    Created on : Feb 22, 2022, 5:03:04 PM
    Author     : CCK
--%>

<%@page import="Services.Auth"%>
<%@page import="middleware.Gate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Orders</title>
        <jsp:include page="../include/style.jsp" />
    </head>
    <body>
        <jsp:include page="../include/nav.jsp" />
        <% Gate.authorise(request, response, "Read Feedback");%>

        <div class="overflow-x-auto">
            <table class="table w-2/3 mx-auto">
                <!-- head -->
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Feedback</th>
                        <th>Delivery</th>
                    </tr>
                </thead>
                <tbody>
</body>
</html>
