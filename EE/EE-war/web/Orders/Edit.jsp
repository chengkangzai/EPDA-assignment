<%-- 
    Document   : Show
    Created on : Feb 22, 2022, 5:03:04 PM
    Author     : CCK
--%>

<%@page import="model.MyOrder"%>
<%@page import="Services.HTML"%>
<%@page import="Services.SHelper"%>
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
        <% Gate.authorise(request, response, "Update Order");%>
        <div class="pt-10">
            <h2 class="text-3xl font-bold text-center">Update Order</h2>
            <jsp:include page="../include/alert.jsp" />
            <%
                String products = SHelper.getOnce(request, "form:products").toString();
                MyOrder order = (MyOrder) request.getSession().getAttribute("form:order");
                out.println(new HTML()
                        .wrap()
                        .form("POST", "/EE-war/Orders/Edit?id=" + order.getId())
                        .multiSelect("products", "Products", products)
                        .submit()
                        .form()
                        .wrapped()
                        .getHtml()
                );%>
        </div>

    </body>
</html>
