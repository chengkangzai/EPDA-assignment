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
            <% if (SHelper.getOnce(request, "validation_error") != null) {
                    out.println("<div class=\"alert w-2/3 mx-auto shadow-lg alert-error my-2 \"><div><span>Error! Your form did not pass the validation.</span></div></div>");
                } else if (SHelper.getOnce(request, "error") != null) {
                    out.println("<div class=\"alert w-2/3 mx-auto shadow-lg alert-error my-2 \"><div><span>Error! " + request.getSession().getAttribute("error") + ".</span></div></div>");
                }
            %>
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
