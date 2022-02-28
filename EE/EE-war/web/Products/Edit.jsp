<%-- 
    Document   : Edit
    Created on : Feb 22, 2022, 3:33:07 PM
    Author     : CCK
--%>

<%@page import="middleware.Gate"%>
<%@page import="model.Product"%>
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
        <% Gate.authorise(request, response, "Update Product");%>
        <div class='pt-10'>
            <h2 class="text-3xl font-bold text-center">Edit Products</h2>
            <jsp:include page="../include/alert.jsp" />
            <%
                Product produt = (Product) request.getSession().getAttribute("form:product");
                out.println(new HTML()
                        .wrap()
                        .form("POST", "/EE-war/Products/Edit?id=" + produt.getId())
                        .input("text", "Name", produt.getName())
                        .input("number", "Price", produt.getPrice().toString())
                        .submit()
                        .form()
                        .wrapped()
                        .getHtml()
                );%>
        </div>
    </body>
</html>
