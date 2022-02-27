<%@page import="Services.SHelper"%>
<% if (SHelper.getSession(request, "validation_error") != null) {
        out.println("<div class=\"alert w-2/3 mx-auto shadow-lg alert-error my-2 \"><div><span>Error! Your form did not pass the validation.</span></div></div>");
    } else if (SHelper.getSession(request, "error") != null) {
        out.println("<div class=\"alert w-2/3 mx-auto shadow-lg alert-error my-2 \"><div><span>Error! " + SHelper.getOnce(request, "error") + "</span></div></div>");   
    }
%>