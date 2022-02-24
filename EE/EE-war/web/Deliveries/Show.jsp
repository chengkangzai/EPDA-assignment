<%-- 
    Document   : Show
    Created on : Feb 23, 2022, 5:31:43 PM
    Author     : CCK
--%>

<%@page import="model.Delivery"%>
<%@page import="Services.HTML"%>
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
        <% Gate.authorise(request, response, "Read Delivery");%>

        <div class="pt-10">
            <h2 class="text-3xl font-bold text-center">View Delivery Details</h2>
        </div>
        <jsp:include page="../include/alert.jsp" />
        <div class="flex flex-row-reverse w-4/5">
            <%
                Delivery delivery = (Delivery) request.getSession().getAttribute("delivery");
                if (delivery.getFeedback() == null) {
                    out.println(new HTML().wrapModal(
                            "Give Feedback",
                            "What is your feedback on this delivery?",
                            new HTML()
                                    .form("POST", "/EE-war/Feedbacks/Create?deliveryId=" + delivery.getId())
                                    .input("text", "Feedback")
                                    .submit()
                                    .form()
                    ).getHtml());
                }
            %>
        </div>
        <%
            if (delivery.getFeedback() != null) {
                out.println("<table class='table w-2/3 mx-auto border mt-2'><tr><td>Feedback</td><td>" + delivery.getFeedback().getFeedback() + "</td></tr></table>");
            }
        %>
    </body>
</html>
