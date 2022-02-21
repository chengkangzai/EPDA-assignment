<%@page import="Services.SHelper"%>
<%@page import="middleware.Gate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Users</title>
        <jsp:include page="../include/style.jsp" />
    </head>
    <jsp:include page="../include/nav.jsp" />
    <% Gate.authorise(request, response, "Read User");%>
    
    <div class="overflow-x-auto">
        <table class="table w-2/3 mx-auto">
            <!-- head -->
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Role</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
        
</body>
</html>
