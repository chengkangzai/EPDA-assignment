<%@page import="Services.Auth"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.ArrayList"%>
<div class="navbar bg-base-100 mb-4 shadow-xl rounded-box print:hidden">
    <div class="navbar-start">
        <a class="btn btn-ghost normal-case text-xl" href='/EE-war/Login.jsp'>APStock</a>       
    </div>
    <div class="navbar-center">
        <!--Rating,Feedback-->
        <% if (Auth.canAny(request, new ArrayList(Arrays.asList("Create Feedback", "Read Feedback", "Update Feedback", "Delete Feedback")))) {
                out.println("<a class='btn btn-ghost uppercase' href='/EE-war/Feedbacks/Index'>Feedback</a>");
            }
        %>
        <% if (Auth.canAny(request, new ArrayList(Arrays.asList("Create Delivery", "Read Delivery", "Update Delivery", "Delete Delivery")))) {
                out.println("<a class='btn btn-ghost uppercase' href='/EE-war/Deliveries/Index'>Delivery</a>");
            }
        %>
        <% if (Auth.canAny(request, new ArrayList(Arrays.asList("Create Order", "Read Order", "Update Order", "Delete Order")))) {
                out.println("<a class='btn btn-ghost uppercase' href='/EE-war/Orders/Index'>Order</a>");
            }
        %>

        <% if (Auth.canAny(request, new ArrayList(Arrays.asList("Create Product", "Read Product", "Update Product", "Delete Product")))) {
                out.println(" <a class='btn btn-ghost uppercase' href='/EE-war/Products/Index'>Products</a>");
            }
        %>
        <% if (Auth.canAny(request, new ArrayList(Arrays.asList("Managing Staff")))) {
                out.println(" <a class='btn btn-ghost uppercase' href='/EE-war/Users/Index'>User Mngt</a>");
            }
        %>

    </div>
    <div class="navbar-end">
        <% if (Auth.user(request) != null) { %>
        <div class="dropdown dropdown-end">
            <label tabindex="0" class="btn btn-ghost btn-circle">
                <div class="w-6 rounded-full">
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z" />
                    </svg>
                </div>
            </label>
            <ul tabindex="0" class="mt-3 p-2 shadow menu menu-compact dropdown-content bg-base-100 rounded-box w-52">

                <li class='cursor-default no-animation'>
                    <a href='/EE-war/Profile.jsp'> <% out.println(Auth.user(request).getName()); %></a>
                </li>
                <div class='divider h-1 my-2'></div>
                <li><a  href='/EE-war/Profile.jsp'>Profile</a></li>
                <li><a href="/EE-war/Logout">Logout</a></li>
            </ul>
        </div>
    </div>
    <%}%>
</div>
</div>