<%-- 
    Document   : Create
    Created on : Feb 22, 2022, 3:33:01 PM
    Author     : CCK
--%>

<%@page import="middleware.Gate"%>
<%@page import="Services.HTML"%>
<%@page import="Services.SHelper"%>
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
        <% Gate.authorise(request, response, "Create Product");%>

        <div class="pt-10">
            <h2 class="text-3xl font-bold text-center">Create new Product</h2>
            <jsp:include page="../include/alert.jsp" />
            <%
                out.println(new HTML()
                        .wrap()
                        .form("POST", "/EE-war/Products/Create")
                        .input("text", "Name")
                        .input("number", "Price")
                        .submit()
                        .form()
                        .wrapped()
                        .getHtml()
                );%>
        </div>
    </body>
</html>
