/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.SHelper;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import middleware.Gate;
import model.EJB.DeliveryFacade;
import model.EJB.FeedbackFacade;
import model.EJB.MyOrderFacade;
import model.EJB.MyRoleFacade;
import model.EJB.MyUserFacade;
import model.EJB.PermissionFacade;
import model.EJB.ProductFacade;
import model.EJB.RatingFacade;
import seeder.AppSeeder;
import seeder.BootstrapSeeder;

/**
 *
 * @author CCK
 */
@WebServlet(name = "BootstrapApp", urlPatterns = {"/BootstrapApp"})
public class BootstrapApp extends HttpServlet {

    @EJB
    private RatingFacade ratingFacade;

    @EJB
    private ProductFacade productFacade;

    @EJB
    private MyOrderFacade myOrderFacade;

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
        if (this.userFacade.findAll().isEmpty()) {
            new BootstrapSeeder(permissionFacade, userFacade, roleFacade).seed();
            new AppSeeder(deliveryFacade, feedbackFacade, productFacade, ratingFacade, userFacade, myOrderFacade).seed();
            SHelper.setSession(request, "success", "Seeding completed, please login with credential");
            SHelper.redirectTo(request, response, "Login.jsp");
            return;
        }

        Gate.authorise(request, response, "Bootstrap App");

        if (request.getMethod().equals("GET")) {
            SHelper.redirectTo(request, response, "/BootstrapApp.jsp");
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
