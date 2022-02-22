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
            <% if (SHelper.getOnce(request, "validation_error") != null) {
                    out.println("<div class=\"alert w-2/3 mx-auto shadow-lg alert-error my-2 \"><div><span>Error! Your form did not pass the validation.</span></div></div>");
                } else if (SHelper.getOnce(request, "error") != null) {
                    out.println("<div class=\"alert w-2/3 mx-auto shadow-lg alert-error my-2 \"><div><span>Error! " + request.getSession().getAttribute("error") + ".</span></div></div>");
                }
            %>
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
