/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.ratings;

import Services.SHelper;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import middleware.Gate;
import model.EJB.ProductFacade;
import model.EJB.RatingFacade;
import model.Product;
import model.Rating;

/**
 *
 * @author CCK
 */
@WebServlet(name = "Ratings.Create", urlPatterns = {"/Ratings/Create"})
public class Create extends HttpServlet {

    @EJB
    private ProductFacade productFacade;

    @EJB
    private RatingFacade ratingFacade;

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
        Gate.authorise(request, response, "Create Rating");

        if (request.getMethod().toUpperCase().equals("GET")) {
            SHelper.redirectTo(request, response, "405.jsp");
            return;
        }

        if (request.getMethod().toUpperCase().equals("POST")) {
            String star = SHelper.getParam(request, "star");
            String id = SHelper.getParam(request, "productId");
            if (star.isEmpty()) {
                SHelper.setSession(request, "validation_error", "");
                SHelper.back(request, response);
                return;
            }

            Product product = this.productFacade.findAll().stream().filter(x -> x.getId().equals(Integer.parseInt(id))).findFirst().get();

            if (product == null) {
                SHelper.redirectTo(request, response, "404.jsp");
                return;
            }

            this.ratingFacade.create(new Rating(Integer.parseInt(star), product));
            SHelper.back(request, response);
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
