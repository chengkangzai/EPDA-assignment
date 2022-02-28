<%-- 
    Document   : Show
    Created on : Feb 22, 2022, 3:33:16 PM
    Author     : CCK
--%>


<%@page import="model.Product"%>
<%@page import="Services.HTML"%>
<%@page import="middleware.Gate"%>
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
        <% Gate.authorise(request, response, "Read Product");%>

        <div class="pt-10">
            <h2 class="text-3xl font-bold text-center">View Product Details</h2>
        </div>
        <div class="flex flex-row-reverse w-4/5">
            <%
                Product product = (Product) request.getSession().getAttribute("product");
                Boolean shouldAskForRating = (Boolean) request.getSession().getAttribute("shouldAskForRating");

                if (shouldAskForRating) {
                    out.println(new HTML().wrapModal(
                            "Rating this product",
                            "How many star you wanna give to this product?",
                            new HTML()
                                    .form("POST", "/EE-war/Ratings/Create?productId=" + product.getId())
                                    .raw(
                                            "<div class='form-control w-full'>"
                                            + "  <label class='label'>"
                                            + "    <span class='label-text'>Rating</span>"
                                            + "  </label>"
                                            + "  <input required name='star' type='number' placeholder='Rating' class='input input-bordered w-full' min=1 max=5>"
                                            + "</div>"
                                    )
                                    .submit()
                                    .form()
                    ).getHtml());
                }
            %>
        </div>
        <%
            Double star = (Double) request.getSession().getAttribute("star");

            out.println("<table class='table w-2/3 mx-auto border mt-2'><tr><td>Rating</td><td>");
            out.println("<div class='rating'>");
            for (int i = 1; i <= 5; i++) {
                if (i <= star) {
                    out.println("<input type='radio' name='rating-2' class='mask mask-star-2 bg-orange-400' checked>");
                } else {
                    out.println("<input type='radio' name='rating-2' class='mask mask-star-2 bg-orange-400'>");
                }
            }
            out.println("</div></td></table>");

        %>
    </body>
</html>
