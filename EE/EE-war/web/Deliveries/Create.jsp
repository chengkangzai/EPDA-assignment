<%-- 
    Document   : Create
    Created on : Feb 23, 2022, 5:26:13 PM
    Author     : CCK
--%>

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
        <% Gate.authorise(request, response, "Create Delivery");%>
        <div class="pt-10">
            <h2 class="text-3xl font-bold text-center">Create new Delivery</h2>
            <jsp:include page="../include/alert.jsp" />
            <%
                String deliveryStaff = SHelper.getOnce(request, "form:deliveryStaff").toString();
                String orders = SHelper.getOnce(request, "form:orders").toString();
                out.println(new HTML()
                        .wrap()
                        .form("POST", "/EE-war/Deliveries/Create")
                        .select("deliveryBy", "Assign to", deliveryStaff)
                        .select("orders", "Order", orders)
                        .submit()
                        .form()
                        .wrapped()
                        .getHtml()
                );%>
        </div>
</html>
