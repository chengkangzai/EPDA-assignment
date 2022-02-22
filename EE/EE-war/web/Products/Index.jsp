<%@page import="Services.Auth"%>
<%@page import="Services.SHelper"%>
<%@page import="middleware.Gate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Products</title>
        <jsp:include page="../include/style.jsp" />
    </head>
    <jsp:include page="../include/nav.jsp" />
    <% Gate.authorise(request, response, "Read Product");%>

    <div class="flex flex-row-reverse w-4/5">
        <% if (Auth.can(request, "Create Product")) {
                out.println("<a class='btn btn-primary' href='/EE-war/Products/Create'> Create Product </a>");
            }
        %>

    </div>

    <div class="overflow-x-auto">
        <table class="table w-2/3 mx-auto">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>

                </body>
                </html>
