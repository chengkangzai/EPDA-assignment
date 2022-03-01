/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.report;

import Services.SHelper;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.EJB.MyOrderFacade;
import model.MyOrder;
import model.Product;

/**
 *
 * @author CCK
 */
@WebServlet(name = "Report.Index", urlPatterns = {"/Report/Index"})
public class Index extends HttpServlet {

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

        List<MyOrder> orders = this.myOrderFacade.findAll();

        String dates = orders
                .stream()
                .map(MyOrder::getCreatedAt)
                .sorted()
                .distinct()
                .map(date -> {
                    return date.toString();
                })
                .collect(Collectors.joining("','", "['", "']"));

        String sales = orders
                .stream()
                .map(MyOrder::getCreatedAt)
                .sorted()
                .distinct()
                .map(date -> {
                    Double total = orders
                            .stream()
                            .filter(order -> order.getCreatedAt().equals(date))
                            .mapToDouble(MyOrder::getTotalPrice)
                            .sum();
                    return String.format("%.2f", total);
                })
                .collect(Collectors.joining(",", "[", "]"));
        SHelper.setSession(request, "report:sales", sales);
        SHelper.setSession(request, "report:dates", dates);

        //get all products
        List<Product> products = this.myOrderFacade.findAll()
                .stream()
                .map(MyOrder::getProducts)
                .flatMap(List::stream)
                .distinct()
                .collect(Collectors.toList());

        //calculate the total sales of all products
        Double totalSales = orders
                .stream()
                .mapToDouble(MyOrder::getTotalPrice)
                .sum();

        //calculate the percentage of each product per total sales
        String productPercentage = products
                .stream()
                .map(product -> {
                    Double total = orders
                            .stream()
                            .filter(order -> order.getProducts().contains(product))
                            .mapToDouble(MyOrder::getTotalPrice)
                            .sum();
                    return String.format("{x : '%s', y: %.2f}", product.getName(), total / totalSales * 100);
                })
                .collect(Collectors.joining(",", "[", "]"));
        

        SHelper.setSession(request, "report:productPercentage", productPercentage);

        request.getRequestDispatcher("Index.jsp").include(request, response);

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
