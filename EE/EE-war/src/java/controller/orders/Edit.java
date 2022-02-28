/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.orders;

import Services.Auth;
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
import model.EJB.MyOrderFacade;
import model.EJB.ProductFacade;
import model.MyOrder;
import model.MyUser;
import model.Product;

/**
 *
 * @author CCK
 */
@WebServlet(name = "Orders.Edit", urlPatterns = {"/Orders/Edit"})
public class Edit extends HttpServlet {

    @EJB
    private ProductFacade productFacade;

    @EJB
    private MyOrderFacade myOrderFacade;

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
        Gate.authorise(request, response, "Update Order");
        MyUser user = Auth.user(request);
        if (!user.is("Managing Staff")) {
            SHelper.redirectTo(request, response, Gate.FORBIIDEN);
            SHelper.setSession(request, "error", "Only Managing User Can do this action");
            return;
        }
        if (request.getMethod().toUpperCase().equals("GET")) {
            String id = SHelper.getParam(request, "id");
            MyOrder order = this.myOrderFacade
                    .findAll()
                    .stream()
                    .filter(x -> x.getId().equals(Integer.parseInt(id)))
                    .findFirst()
                    .get();

            String products = this.productFacade
                    .findAll()
                    .stream()
                    .filter(x -> !order.getProducts().contains(x))
                    .map(x -> x.toSelection())
                    .collect(Collectors.joining(""));

            SHelper.setSession(request, "form:products", products);
            SHelper.setSession(request, "form:order", order);
            request.getRequestDispatcher("Edit.jsp").include(request, response);
        }
//Store
        if (request.getMethod().toUpperCase().equals("POST")) {

            String p = SHelper.getParam(request, "products");
            if (p.isEmpty()) {
                SHelper.setSession(request, "validation_error", "");
                SHelper.back(request, response);
                return;
            }
            String[] split = p.split(",");
            List<Product> products = this.productFacade.findAll()
                    .stream()
                    .filter((product) -> {
                        boolean found = false;
                        for (String split1 : split) {
                            if (!found) {
                                found = product.getId().equals(Integer.parseInt(split1));;
                            }
                        }
                        return found;
                    })
                    .collect(Collectors.toList());

            if (products.isEmpty()) {
                SHelper.setSession(request, "validation_error", "");
                SHelper.back(request, response);
                return;
            }

            String id = SHelper.getParam(request, "id");
            MyOrder order = this.myOrderFacade
                    .findAll()
                    .stream()
                    .filter(x -> x.getId().equals(Integer.parseInt(id)))
                    .findFirst()
                    .get();

            if (order.getDelivery() != null) {
                SHelper.setSession(request, "error", "The Order cannot be modify as it already been processed");
                SHelper.back(request, response);
                return;
            }
            order.getProducts().addAll(products);

            this.myOrderFacade.edit(order);
            SHelper.redirectTo(request, response, "/Orders/Index");
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
