/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.deliveries;

import Services.Auth;
import Services.SHelper;
import java.io.IOException;
import java.sql.Date;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import middleware.Gate;
import model.Delivery;
import model.EJB.DeliveryFacade;
import model.MyUser;

/**
 *
 * @author CCK
 */
@WebServlet(name = "Deliveries.Deliver", urlPatterns = {"/Deliveries/Deliver"})
public class Deliver extends HttpServlet {

    @EJB
    private DeliveryFacade deliveryFacade;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Gate.authorise(request, response, "Update Delivery");
        MyUser user = Auth.user(request);
        if (!user.is("Delivery Staff")) {
            SHelper.redirectTo(request, response, Gate.FORBIIDEN);
            SHelper.setSession(request, "error", "Only Delivery User Can do this action");
            return;
        }

        if (request.getMethod().toUpperCase().equals("GET")) {
            String id = SHelper.getParam(request, "id");
            Delivery delivery = this.deliveryFacade.findAll().stream().filter(x -> x.getId().equals(Integer.parseInt(id))).findFirst().get();
            if (delivery == null) {
                SHelper.back(request, response);
                return;
            }
            Double amount = delivery.getOrder().getProducts().stream().mapToDouble(x -> x.getPrice()).sum();
            SHelper.setSession(request, "delivery", delivery);
            SHelper.setSession(request, "amount", String.format("%.2f", amount));

            request.getRequestDispatcher("Deliver.jsp").include(request, response);
            return;
        }

        if (request.getMethod().toUpperCase().equals("POST")) {
            String id = SHelper.getParam(request, "id");
            Delivery delivery = this.deliveryFacade.findAll().stream().filter(x -> x.getId().equals(Integer.parseInt(id))).findFirst().get();
            if (delivery == null) {
                SHelper.back(request, response);
                return;
            }
            String delivered = SHelper.getParam(request, "delivered");
            String payment_collected = SHelper.getParam(request, "delivered");
            if (!delivered.equals("on") || !payment_collected.equals("on")) {
                SHelper.setSession(request, "error", "Hey you need to check both the checkbox");
                SHelper.back(request, response);
                return;
            }

            delivery.setStatus(Delivery.Status.DELIVERED);
            delivery.setDeliverAt(new Date(new java.util.Date().getTime()));
            String productRow = delivery
                    .getOrder()
                    .getProducts()
                    .stream()
                    .map(x -> {
                        return "<tr>"
                                + "<td class='px-9 py-5 whitespace-nowrap space-x-1 flex items-center'>"
                                + "<p class='font-bold'>" + x.getName() + "</p>"
                                + "</td>"
                                + "<td class='whitespace-nowrap text-gray-600 truncate'> RM " + x.getPriceInString()+ " </td>   "
                                + "</tr>";
                    })
                    .collect(Collectors.joining());
            this.deliveryFacade.edit(delivery);

            Double amount = delivery.getOrder().getProducts().stream().mapToDouble(x -> x.getPrice()).sum();
            SHelper.setSession(request, "delivery", delivery);
            SHelper.setSession(request, "amount", String.format("%.2f", amount));
            SHelper.setSession(request, "productRow", productRow);
            //redirect to Receipt page
            request.getRequestDispatcher("Receipt.jsp").include(request, response);
            return;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
