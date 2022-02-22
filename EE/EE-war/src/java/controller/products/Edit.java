/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.products;

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
import model.MyRole;
import model.MyUser;
import model.Product;

/**
 *
 * @author CCK
 */
@WebServlet(name = "Products.Edit", urlPatterns = {"/Products/Edit"})
public class Edit extends HttpServlet {

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
        Gate.authorise(request, response, "Update Product");
        if (request.getMethod().toUpperCase().equals("GET")) {
            String id = SHelper.getParam(request, "id");
            Product product = this.productFacade.findAll()
                    .stream()
                    .filter(x -> x.getId().equals(Integer.parseInt(id)))
                    .findFirst()
                    .get();
            SHelper.setSession(request, "form:product", product);
            request.getRequestDispatcher("Edit.jsp").include(request, response);
        }
        //Update
        if (request.getMethod().toUpperCase().equals("POST")) {
            String name = SHelper.getParam(request, "name");
            String price = SHelper.getParam(request, "price");

            if (price.isEmpty() || name.isEmpty()) {
                SHelper.setSession(request, "validation_error", name);
                SHelper.back(request, response);
                return;
            }

            String id = SHelper.getParam(request, "id");
            Product product = this.productFacade.findAll()
                    .stream()
                    .filter(x -> x.getId().equals(Integer.parseInt(id)))
                    .findFirst()
                    .get();

            product.setPrice(Double.parseDouble(price));
            product.setName(name);

            this.productFacade.edit(product);

            SHelper.redirectTo(request, response, "/Products/Index");
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
