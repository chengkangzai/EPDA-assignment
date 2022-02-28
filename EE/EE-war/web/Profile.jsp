<%@page import="Services.Auth"%>
<%@page import="model.MyUser"%>
<%@page import="java.util.List"%>
<%@page import="Services.HTML"%>
<%@page import="Services.SHelper"%>
<%@page import="middleware.Gate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Users</title>
        <jsp:include page="include/style.jsp" />
    </head>
    <jsp:include page="include/nav.jsp" />
    <% Gate.authorise(request, response, "Update Profile");%>

    <div class="pt-10">
        <h2 class="text-3xl font-bold text-center">Edit Profile</h2>
        <jsp:include page="include/alert.jsp" />
        <%
            MyUser user = Auth.user(request);
            HTML html = new HTML()
                    .wrap()
                    .form("POST", "/EE-war/Profile?id=" + user.getId())
                    .input("email", "Email", user.getEmail())
                    .input("text", "Name", user.getName())
                    .input("password", "Password", user.getPassword());

            if (user.is("Customer")) {
                html
                        .input("text", "TPNumber", user.getTPNumber())
                        .input("text", "Address", user.getAddress());
            }

            out.println(html
                    .submit()
                    .form()
                    .wrapped()
                    .getHtml()
            );%>
    </div>
</body>
</html>
