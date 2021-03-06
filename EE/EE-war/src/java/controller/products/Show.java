/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.products;

import Services.Auth;
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
import model.EJB.ProductFacade;
import model.MyUser;
import model.Product;

/**
 *
 * @author CCK
 */
@WebServlet(name = "Products.Show", urlPatterns = {"/Products/Show"})
public class Show extends HttpServlet {

    @EJB
    private ProductFacade productFacade;

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
        Gate.authorise(request, response, "Read Product");

        String id = request.getParameter("id");

        Product product = this.productFacade.findAll().stream().filter(x -> x.getId().equals(Integer.parseInt(id))).findFirst().get();
        SHelper.setSession(request, "product", product);

        MyUser user = Auth.user(request);
        Boolean isBroughtedB4 = product.getMyOrders().stream().filter(x -> x.getPurchaseBy().equals(user)).count() >= 1;
        Boolean isRateB4 = product.getRating().stream().filter(x -> x.getRateBy().equals(user)).count() >= 1;
        Boolean shouldAskForRating = isBroughtedB4 && !isRateB4;

        SHelper.setSession(request, "shouldAskForRating", shouldAskForRating);
        Double star = product.getRating().stream().mapToDouble(x -> x.getStar()).average().orElse(0);

        SHelper.setSession(request, "star", star);

        request.getRequestDispatcher("Show.jsp").include(request, response);

        try (PrintWriter out = response.getWriter()) {
            out.println(product.toShowTable());
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
