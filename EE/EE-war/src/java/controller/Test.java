/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import seeder.UserManagementSeeder;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.EJB.DeliveryFacade;
import model.EJB.FeedbackFacade;
import model.EJB.MyOrderFacade;
import model.EJB.PermissionFacade;
import model.EJB.MyRoleFacade;
import model.MyUser;
import model.EJB.MyUserFacade;
import model.EJB.ProductFacade;
import model.EJB.RatingFacade;
import seeder.AppSeeder;

/**
 *
 * @author CCK
 */
@WebServlet(name = "Test", urlPatterns = {"/Test"})
public class Test extends HttpServlet {

    @EJB
    private MyOrderFacade myOrderFacade;

    @EJB
    private RatingFacade ratingFacade;

    @EJB
    private ProductFacade productFacade;

    @EJB
    private FeedbackFacade feedbackFacade;

    @EJB
    private DeliveryFacade deliveryFacade;

    @EJB
    private PermissionFacade permissionFacade;

    @EJB
    private MyRoleFacade roleFacade;

    @EJB
    private MyUserFacade userFacade;

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
        new UserManagementSeeder(permissionFacade, userFacade, roleFacade).seed();
        new AppSeeder(deliveryFacade, feedbackFacade, productFacade, ratingFacade, userFacade, myOrderFacade).seed();

        MyUser user = this.userFacade.findAll().get(1);
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Test</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>My User ... </h1>");
            this.userFacade.findAll().forEach(t -> out.println(t.toString()));
            out.println("<p>------------------</p>");
            out.println("<h1>My role ... </h1>");
            this.roleFacade.findAll().forEach(t -> out.println(t.toString()));
            out.println("<p>------------------</p>");
            out.println("<h1>My Permission ... </h1>");
            this.permissionFacade.findAll().forEach(t -> out.println(t.toString()));
            out.println("<p>------------------</p>");
            out.println("<h1>My Rating ... </h1>");
            this.ratingFacade.findAll().forEach(t -> out.println(t.toString()));
            out.println("<p>------------------</p>");
            out.println("<h1>My Feedback ... </h1>");
            this.feedbackFacade.findAll().forEach(t -> out.println(t.toString()));
            out.println("<p>------------------</p>");
            out.println("<h1>My Product ... </h1>");
            this.productFacade.findAll().forEach(t -> out.println(t.toString()));
            out.println("<p>------------------</p>");
            out.println("<h1>My Order ... </h1>");
            this.myOrderFacade.findAll().forEach(t -> out.println(t.toString()));
            out.println("<p>------------------</p>");
            out.println("<h1>My Delivery ... </h1>");
            this.deliveryFacade.findAll().forEach(t -> out.println(t.toString()));
            out.println("<p>------------------</p>");
            out.println("</body>");
            out.println("</html>");
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
