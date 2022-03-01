<%-- 
    Document   : Login
    Created on : Feb 20, 2022, 1:27:57 PM
    Author     : CCK
--%>
<%@page import="middleware.Gate"%>
<%@page import="middleware.RedirectIfLoggedIn"%>
<%@page import="Services.SHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bootstrap App</title>
        <jsp:include page="include/style.jsp" />
    </head>
    <body>
        <% // Gate.authorise(request, response, "Bootstrap App");%>
        <jsp:include page="include/nav.jsp" />


        <section class="max-w-4xl p-6 mt-14 mx-auto bg-white rounded-md shadow-xl dark:bg-gray-800">
            <div>
                <h2 class="text-2xl font-semibold text-gray-700 capitalize dark:text-white">Bootstrap App</h2>
                <jsp:include page="include/alert.jsp" />
                <form action="/EE-war/BootstrapApp" method="POST">
                    <div class="grid grid-cols-1 gap-6 mt-4 sm:grid-cols-2">
                        <input type="submit" role="button" name="submit" value='Seed Order' class="block w-full px-4 py-2 mt-2 text-white text-center font-semibold bg-gray-800 hover:bg-gray-700 border-gray-200 rounded-md">
                        <input type="submit" role="button" name="submit" value='Seed Product' class="block w-full px-4 py-2 mt-2 text-white text-center font-semibold bg-gray-800 hover:bg-gray-700 border-gray-200 rounded-md">
                        <input type="submit" role="button" name="submit" value='Seed Delivery' class="block w-full px-4 py-2 mt-2 text-white text-center font-semibold bg-gray-800 hover:bg-gray-700 border-gray-200 rounded-md">
                        <input type="submit" role="button" name="submit" value='Seed Rating' class="block w-full px-4 py-2 mt-2 text-white text-center font-semibold bg-gray-800 hover:bg-gray-700 border-gray-200 rounded-md">
                        <input type="submit" role="button" name="submit" value='Seed Feedback' class="block w-full px-4 py-2 mt-2 text-white text-center font-semibold bg-gray-800 hover:bg-gray-700 border-gray-200 rounded-md">
                        <input type="submit" role="button" name="submit" value='Seed User' class="block w-full px-4 py-2 mt-2 text-white text-center font-semibold bg-gray-800 hover:bg-gray-700 border-gray-200 rounded-md">
                    </div> 
                </form>
            </div>

            <div>
                <h2 class="text-2xl font-semibold text-gray-700 capitalize dark:text-white">Truncate Data</h2>
                <jsp:include page="include/alert.jsp" />
                <form action="/EE-war/BootstrapApp" method="POST">
                    <div class="grid grid-cols-1 gap-6 mt-4 sm:grid-cols-2">
                        <input type="submit" role="button" name="submit" value='Truncate Order' class="block w-full px-4 py-2 mt-2 text-white text-center font-semibold bg-red-700 hover:bg-red-700 border-red-200 rounded-md">
                        <input type="submit" role="button" name="submit" value='Truncate Product' class="block w-full px-4 py-2 mt-2 text-white text-center font-semibold bg-red-700 hover:bg-red-700 border-red-200 rounded-md">
                        <input type="submit" role="button" name="submit" value='Truncate Delivery' class="block w-full px-4 py-2 mt-2 text-white text-center font-semibold bg-red-700 hover:bg-red-700 border-red-200 rounded-md">
                        <input type="submit" role="button" name="submit" value='Truncate Rating' class="block w-full px-4 py-2 mt-2 text-white text-center font-semibold bg-red-700 hover:bg-red-700 border-red-200 rounded-md">
                        <input type="submit" role="button" name="submit" value='Truncate Feedback' class="block w-full px-4 py-2 mt-2 text-white text-center font-semibold bg-red-700 hover:bg-red-700 border-red-200 rounded-md">
                    </div> 
                </form>
            </div>
        </section>
    </body>
</html>
