<%-- 
    Document   : Create
    Created on : Feb 23, 2022, 5:26:13 PM
    Author     : CCK
--%>

<%@page import="model.Delivery"%>
<%@page import="Services.HTML"%>
<%@page import="Services.SHelper"%>
<%@page import="Services.SHelper"%>
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
        <% Gate.authorise(request, response, "Update Delivery");%>
        <div class="pt-10">
            <h2 class="text-3xl font-bold text-center">Deliver Delivery</h2>
            <jsp:include page="../include/alert.jsp" />
        </div>

        <%
            Delivery delivery = (Delivery) request.getSession().getAttribute("delivery");
            String amount = SHelper.getOnce(request, "amount").toString();
            HTML html = new HTML()
                    .wrap()
                    .form("POST", "/EE-war/Deliveries/Deliver?id=" + delivery.getId())
                    .checkbox("delivered", "I am hereby confirm that this product is sent to the correct customer")
                    .checkbox("payment_collected", "I am hereby confirm that i receive RM : <b>" + amount + "</b> from the customer")
                    .submit()
                    .form()
                    .wrapped();
            out.println(html.getHtml());
        %>
    </body>
</html>
