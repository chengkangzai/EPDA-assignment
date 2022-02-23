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
            <h2 class="text-3xl font-bold text-center">Edit Delivery</h2>
            <% if (SHelper.getOnce(request, "validation_error") != null) {
                    out.println("<div class=\"alert w-2/3 mx-auto shadow-lg alert-error my-2 \"><div><span>Error! Your form did not pass the validation.</span></div></div>");
                } else if (SHelper.getOnce(request, "error") != null) {
                    out.println("<div class=\"alert w-2/3 mx-auto shadow-lg alert-error my-2 \"><div><span>Error! " + request.getSession().getAttribute("error") + ".</span></div></div>");
                }
            %>
            <%
                String customers = SHelper.getOnce(request, "form:customers").toString();
                String deliveryStaff = SHelper.getOnce(request, "form:deliveryStaff").toString();
                String orders = SHelper.getOnce(request, "form:orders").toString();
                String status = SHelper.getOnce(request, "form:status").toString();
                Delivery delivery = (Delivery) request.getSession().getAttribute("form:delivery");
                out.println(new HTML()
                        .wrap()
                        .form("POST", "/EE-war/Deliveries/Edit?id=" + delivery.getId())
                        .select("deliveryTo", "Customer", customers)
                        .select("deliveryBy", "Assign to", deliveryStaff)
                        .select("orders", "Order", orders)
                        .divide()
                        .select("status", "Status", status)
                        .datePicker("deliveryAt", "Deliver at")
                        .submit()
                        .form()
                        .wrapped()
                        .getHtml()
                );%>
        </div>
</html>
