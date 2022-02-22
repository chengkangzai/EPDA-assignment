<%-- 
    Document   : dashboard
    Created on : Feb 22, 2022, 1:18:21 PM
    Author     : CCK
--%>

<%@page import="middleware.Guest"%>
<%@page import="middleware.Gate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="include/style.jsp" />
    </head>
    <body>
        <jsp:include page="include/nav.jsp" />
        <% Guest.authorise(request, response);%>
    </body>
</html>
