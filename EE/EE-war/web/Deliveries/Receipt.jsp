<%-- 
    Document   : Create
    Created on : Feb 23, 2022, 5:26:13 PM
    Author     : CCK
--%>

<%@page import="model.Product"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="Services.Auth"%>
<%@page import="model.MyUser"%>
<%@page import="model.Delivery"%>
<%@page import="Services.HTML"%>
<%@page import="Services.SHelper"%>
<%@page import="Services.SHelper"%>
<%@page import="middleware.Gate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Delivery delivery = (Delivery) request.getSession().getAttribute("delivery");
    String amount = SHelper.getOnce(request, "amount").toString();
    String productRow = SHelper.getOnce(request, "productRow").toString();
    MyUser user = delivery.getOrder().getPurchaseBy();
%>
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
        <section class="bg-gray-100 py-20 min-h-screen">
            <div class="max-w-2xl mx-auto py-0 md:py-16">
                <article class="shadow-none md:shadow-md md:rounded-md overflow-hidden">
                    <div class="md:rounded-b-md  bg-white">
                        <div class="p-9 border-b border-gray-200">
                            <div class="space-y-6">
                                <div class="flex justify-between items-top">
                                    <div class="space-y-4">
                                        <div>
                                            <p class="font-bold text-3xl"> APSTock </p>
                                            <p class="font-semibold text-lg"> Invoice </p>
                                        </div>
                                        <div>
                                            <p class="font-medium text-sm text-gray-400"> Billed To </p>
                                            <p> <% out.print(user.getName());%> </p>
                                            <p> <% out.print(user.getTPNumber());%> </p>
                                            <p> <% out.print(user.getEmail());%> </p>
                                        </div>
                                    </div>
                                    <div class="space-y-2">
                                        <div>
                                            <p class="font-medium text-sm text-gray-400"> Invoice Number </p>
                                            <p> <% out.print(delivery.getId()); %> </p>
                                        </div>
                                        <div>
                                            <p class="font-medium text-sm text-gray-400"> Invoice Date </p>
                                            <p> <% out.print(delivery.getDeliverAt().toString());%> </p>
                                        </div>
                                        <div class='print:hidden'>
                                            <a href="javascript:window.print()" class="inline-flex items-center text-sm font-medium text-blue-500 hover:opacity-75 "> Print receipt  
                                                <svg class="ml-0.5 h-4 w-4 fill-current" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor" aria-hidden="true">
                                                <path d="M11 3a1 1 0 100 2h2.586l-6.293 6.293a1 1 0 101.414 1.414L15 6.414V9a1 1 0 102 0V4a1 1 0 00-1-1h-5z"></path>
                                                <path d="M5 5a2 2 0 00-2 2v8a2 2 0 002 2h8a2 2 0 002-2v-3a1 1 0 10-2 0v3H5V7h3a1 1 0 000-2H5z"></path>
                                                </svg>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="p-9 border-b border-gray-200">
                            <p class="font-medium text-sm text-gray-400"> Note </p>
                            <p class="text-sm"> Thank you for your order. </p>
                        </div>
                        <table class="w-full divide-y divide-gray-200 text-sm">
                            <thead>
                                <tr>
                                    <th scope="col" class="px-9 py-4 text-left font-semibold text-gray-400"> Item </th>
                                    <th scope="col" class="py-3 text-left font-semibold text-gray-400"> Amount </th>

                                </tr>
                            </thead>
                            <tbody class="divide-y divide-gray-200">
                                <%
                                    out.println(productRow);
                                %>
                            </tbody>
                        </table>
                        <div class="p-9 border-b border-gray-200">
                            <div class="space-y-3">
                                <div class="flex justify-between">
                                    <div>
                                        <p class="font-bold text-black text-lg"> Total </p>
                                    </div>
                                    <p class="font-bold text-black text-lg"> RM <% out.println(amount); %> </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </article>
            </div>
        </section>

    </body>
</html>
