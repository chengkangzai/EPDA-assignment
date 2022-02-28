<%-- 
    Document   : Index
    Created on : Feb 23, 2022, 5:31:37 PM
    Author     : CCK
--%>

<%@page import="Services.Auth"%>
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
        <div class="flex flex-row-reverse w-4/5">
            <% if (Auth.can(request, "Create Delivery")) {
                    out.println("<a class='btn btn-primary' href='/EE-war/Deliveries/Create'> Create Delivery </a>");
                }
            %> 
        </div>

        <div class="overflow-x-auto container mx-auto py-10">
            <table class="table" id='datatable'>
                <!-- head -->
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Status</th>
                        <th>Address</th>
                        <th>Assigned to </th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    </body>
                    </html>
