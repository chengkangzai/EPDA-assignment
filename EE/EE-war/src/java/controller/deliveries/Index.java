/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.deliveries;

import Services.Auth;
import java.io.IOException;
import java.io.PrintWriter;
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
import model.MyUser;

/**
 *
 * @author CCK
 */
@WebServlet(name = "Deliveries.Index", urlPatterns = {"/Deliveries/Index"})
public class Index extends HttpServlet {

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
        Gate.authorise(request, response, "Read Delivery");
        request.getRequestDispatcher("Index.jsp").include(request, response);

        List<Delivery> deliveries = this.deliveryFacade.findAll();

        MyUser user = Auth.user(request);
        if (user.is("Customer")) {
            deliveries = deliveries
                    .stream()
                    .filter(x -> x.getOrder().getPurchaseBy().equals(user))
                    .collect(Collectors.toList());
        } else if (user.is("Delivery Staff")) {
            deliveries = deliveries
                    .stream()
                    .filter(x -> x.getDeliverBy().equals(user))
                    .collect(Collectors.toList());
        } 

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            deliveries.forEach(x -> out.println(x.toTd(Auth.user(request))));

            out.println("</tbody></table>");
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
