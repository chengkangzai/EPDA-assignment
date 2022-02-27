<%-- 
    Document   : Login
    Created on : Feb 20, 2022, 1:27:57 PM
    Author     : CCK
--%>
<%@page import="middleware.RedirectIfLoggedIn"%>
<%@page import="Services.SHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
        <jsp:include page="include/style.jsp" />
    </head>
    <body>
        <% RedirectIfLoggedIn.handle(request, response);%>
        <jsp:include page="include/nav.jsp" />

        <form action="Register" method="POST" class="block">
            <div class="container mx-auto px-4 h-full">
                <div class="flex content-center items-center justify-center h-full">
                    <div class="w-full lg:w-6/12 px-4 pt-20">
                        <div class="relative flex flex-col min-w-0 break-words w-full mb-6 shadow-lg rounded-lg bg-gray-100 border-0">
                            <div class="flex-auto px-4 lg:px-10 py-10 pt-0">
                                <div class="text-gray-500 text-center mb-3 mt-1 text-xl font-bold">
                                    <small>Welcome! Register yourself as Customer</small>
                                </div>
                                <jsp:include page="include/alert.jsp" />

                                <form>
                                    <div class="relative w-full mb-3">
                                        <label class="block uppercase text-gray-700 text-xs font-bold mb-2">Email</label>
                                        <input type="email" name="email" required class="px-3 py-3 placeholder-gray-400 text-gray-700 bg-white rounded text-sm shadow focus:outline-none focus:shadow-outline w-full" placeholder="Email" style="transition: all 0.15s ease 0s;">
                                    </div>
                                    <div class="relative w-full mb-3">
                                        <label class="block uppercase text-gray-700 text-xs font-bold mb-2">Name</label>
                                        <input type="text" name="name" required class="px-3 py-3 placeholder-gray-400 text-gray-700 bg-white rounded text-sm shadow focus:outline-none focus:shadow-outline w-full" placeholder="Your Name" style="transition: all 0.15s ease 0s;">
                                    </div>
                                    <div class="relative w-full mb-3">
                                        <label class="block uppercase text-gray-700 text-xs font-bold mb-2">TP Number</label>
                                        <input type="text" name="TPNumber" required class="px-3 py-3 placeholder-gray-400 text-gray-700 bg-white rounded text-sm shadow focus:outline-none focus:shadow-outline w-full" placeholder="TP Number" style="transition: all 0.15s ease 0s;">
                                    </div>
                                    <div class="relative w-full mb-3">
                                        <label class="block uppercase text-gray-700 text-xs font-bold mb-2">Password</label>
                                        <input type="password" name="password" required class="px-3 py-3 placeholder-gray-400 text-gray-700 bg-white rounded text-sm shadow focus:outline-none focus:shadow-outline w-full" placeholder="Password" style="transition: all 0.15s ease 0s;">
                                    </div>
                                    <div class="relative w-full mb-3">
                                        <label class="block uppercase text-gray-700 text-xs font-bold mb-2">Address</label>
                                        <input type="text" name="address" required class="px-3 py-3 placeholder-gray-400 text-gray-700 bg-white rounded text-sm shadow focus:outline-none focus:shadow-outline w-full" placeholder="Address" style="transition: all 0.15s ease 0s;">
                                    </div>
                                    <div class="text-center mt-6">
                                        <input type="submit" value="Register" class="bg-gray-900 text-white active:bg-gray-700 text-sm font-bold uppercase px-6 py-3 rounded shadow hover:shadow-lg outline-none focus:outline-none mr-1 mb-1 w-full" type="button" style="transition: all 0.15s ease 0s;"/>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="flex flex-wrap mt-6">
                            <div class="w-1/2">

                            </div>
                            <div class="w-1/2 text-right">
                                <a href="Login.jsp" class="text-gray-300">
                                    <small>Already have an Account?</small>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </form>
    </body>
</html>
