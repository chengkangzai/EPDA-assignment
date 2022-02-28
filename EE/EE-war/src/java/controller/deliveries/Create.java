/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.deliveries;

import Services.SHelper;
import java.io.IOException;
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
@WebServlet(name = "Deliveries.Create", urlPatterns = {"/Deliveries/Create"})
public class Create extends HttpServlet {

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Gate.authorise(request, response, "Create Delivery");
        response.setContentType("text/html;charset=UTF-8");
        //Create
        if (request.getMethod().toUpperCase().equals("GET")) {
            String deliveryStaff = this.myUserFacade.findAll()
                    .stream()
                    .filter(x -> x.getRole().getName().equals("Delivery Staff"))
                    .map(x -> x.toSelection())
                    .collect(Collectors.joining());

            String orders = this.myOrderFacade.findAll()
                    .stream()
                    .filter(x -> x.getDelivery() == null)
                    .map(x -> x.toSelection())
                    .collect(Collectors.joining());

            SHelper.setSession(request, "form:deliveryStaff", deliveryStaff);
            SHelper.setSession(request, "form:orders", orders);
            request.getRequestDispatcher("Create.jsp").include(request, response);
        }
//Store
        if (request.getMethod().toUpperCase().equals("POST")) {
            String db = SHelper.getParam(request, "deliveryBy");
            String o = SHelper.getParam(request, "orders");

            if (db.isEmpty() || o.isEmpty()) {
                SHelper.setSession(request, "validation_error", "");
                SHelper.back(request, response);
                return;
            }

            MyUser deliveryBy = this.myUserFacade.findAll()
                    .stream()
                    .filter(x -> x.getId().equals(Integer.parseInt(db)))
                    .findFirst()
                    .get();
            
            
            this.myOrderFacade.findAll().stream().sorted().limit(3);
            
            MyOrder order = this.myOrderFacade
                    .findAll()
                    .stream()
                    .filter(x -> x.getId().equals(Integer.parseInt(o)))
                    .findFirst()
                    .get();

            Delivery devliery = new Delivery(Delivery.Status.PENDING, order, order.getPurchaseBy().getAddress(), deliveryBy);

            this.deliveryFacade.create(devliery);
            order.setDelivery(devliery);
            this.myOrderFacade.edit(order);

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
