/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.deliveries;

import Services.SHelper;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
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
import model.EJB.MyOrderFacade;
import model.EJB.MyUserFacade;
import model.MyOrder;
import model.MyUser;

/**
 *
 * @author CCK
 */
@WebServlet(name = "Deliveries.Edit", urlPatterns = {"/Deliveries/Edit"})
public class Edit extends HttpServlet {

    @EJB
    private MyOrderFacade myOrderFacade;

    @EJB
    private MyUserFacade myUserFacade;

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
    @SuppressWarnings("empty-statement")
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Gate.authorise(request, response, "Update Delivery");
        //Create
        if (request.getMethod().toUpperCase().equals("GET")) {
            String id = SHelper.getParam(request, "id");
            Delivery delivery = this.deliveryFacade.findAll()
                    .stream()
                    .filter(x -> x.getId().equals(Integer.parseInt(id)))
                    .findFirst()
                    .get();

            String deliveryStaff = this.myUserFacade.findAll()
                    .stream()
                    .filter(x -> x.getRole().getName().equals("Delivery Staff"))
                    .map(x -> x.toSelection())
                    .collect(Collectors.joining())
                    .replaceAll("value='" + delivery.getDeliverBy().getId() + "'", "value='" + delivery.getDeliverBy().getId() + "' selected");

            String orders = this.myOrderFacade
                    .findAll()
                    .stream()
                    .map(x -> x.toSelection())
                    .collect(Collectors.joining())
                    .replaceAll("value='" + delivery.getOrder().getId() + "'", "value='" + delivery.getOrder().getId() + "' selected");

            String status = Delivery.statusToSelection()
                    .replaceAll("value='" + delivery.getStatus() + "'", "value='" + delivery.getStatus() + "' selected");

            SHelper.setSession(request, "form:deliveryStaff", deliveryStaff);
            SHelper.setSession(request, "form:orders", orders);
            SHelper.setSession(request, "form:delivery", delivery);
            SHelper.setSession(request, "form:status", status);

            request.getRequestDispatcher("Edit.jsp").include(request, response);
        }
//Store
        if (request.getMethod().toUpperCase().equals("POST")) {

            String dt = SHelper.getParam(request, "deliveryTo");
            String db = SHelper.getParam(request, "deliveryBy");
            String o = SHelper.getParam(request, "orders");
            String s = SHelper.getParam(request, "status");
            String da = SHelper.getParam(request, "deliveryAt");

            if (dt.isEmpty() || db.isEmpty() || o.isEmpty() || s.isEmpty()) {
                SHelper.setSession(request, "validation_error", "");
                SHelper.back(request, response);
                return;
            }

            List<MyUser> users = this.myUserFacade.findAll();
            MyUser deliveryBy = users.stream().filter(x -> x.getId().equals(Integer.parseInt(db))).findFirst().get();

            MyOrder order = this.myOrderFacade.findAll().stream().filter(x -> x.getId().equals(Integer.parseInt(o))).findFirst().get();
            
            String id = SHelper.getParam(request, "id");
            Delivery delivery = this.deliveryFacade.findAll().stream().filter(x -> x.getId().equals(Integer.parseInt(id))).findFirst().get();
            
            delivery.setDeliverBy(deliveryBy);
            delivery.setOrder(order);

            if (!da.isEmpty()) {
                delivery.setDeliverAt(Date.valueOf(da));
            }

            this.deliveryFacade.edit(delivery);

            // if delivered, make sure it have delivered date
            SHelper.redirectTo(request, response, "/Deliveries/Index");
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
