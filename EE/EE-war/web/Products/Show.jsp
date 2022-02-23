<%-- 
    Document   : Show
    Created on : Feb 22, 2022, 3:33:16 PM
    Author     : CCK
--%>

<%@page import="middleware.Gate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Products</title>
        <jsp:include page="../include/style.jsp" />
    </head>
    <body>
        <jsp:include page="../include/nav.jsp" />
        <% Gate.authorise(request, response, "Read Product");%>

        <div class="pt-10">
            <h2 class="text-3xl font-bold text-center">View Product Details</h2>
        </div>
    </body>
</html>
