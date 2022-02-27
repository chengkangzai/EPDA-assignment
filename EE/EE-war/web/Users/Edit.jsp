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
        <jsp:include page="../include/style.jsp" />
    </head>
    <jsp:include page="../include/nav.jsp" />
    <% Gate.authorise(request, response, "Create User");%>

    <div class="pt-10">
        <h2 class="text-3xl font-bold text-center">Edit Users</h2>
        <jsp:include page="../include/alert.jsp" />
        <%
            String roles = SHelper.getOnce(request, "form:roles").toString();
            MyUser user = (MyUser) request.getSession().getAttribute("form:user");
            out.println(new HTML()
                    .wrap()
                    .form("POST", "/EE-war/Users/Edit?id=" + user.getId())
                    .input("email", "Email", user.getEmail())
                    .input("text", "Name", user.getName())
                    .select("role", "Assign role", roles)
                    .submit()
                    .form()
                    .wrapped()
                    .getHtml()
            );%>
    </div>
</body>
</html>
