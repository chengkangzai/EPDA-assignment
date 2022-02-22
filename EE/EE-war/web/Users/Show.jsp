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

    <div class="pt-10">
        <h2 class="text-3xl font-bold text-center">View Users Details</h2>
    </div>
</body>
</html>
