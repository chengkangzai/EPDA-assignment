/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.Auth;
import Services.SHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import middleware.Gate;
import model.Delivery;
import model.EJB.MyUserFacade;
import model.MyOrder;
import model.MyUser;

/**
 *
 * @author CCK
 */
@WebServlet(name = "Profile", urlPatterns = {"/Profile"})
public class Profile extends HttpServlet {

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
        Gate.authorise(request, response, "Update Profile");
        //Create
        if (request.getMethod().toUpperCase().equals("GET")) {

            request.getRequestDispatcher("Profile.jsp").include(request, response);
        }
//Store
        if (request.getMethod().toUpperCase().equals("POST")) {
            String id = SHelper.getParam(request, "id");
            String name = SHelper.getParam(request, "name");
            String email = SHelper.getParam(request, "email");
            String role = SHelper.getParam(request, "role");

            if (role.isEmpty() || name.isEmpty() || email.isEmpty()) {
                SHelper.setSession(request, "validation_error", name);
                SHelper.back(request, response);
                return;
            }

            if (!Auth.user(request).getId().equals(Integer.parseInt(id))) {
                SHelper.redirectTo(request, response, Gate.FORBIIDEN);
                return;
            }

            MyUser user = this.userFacade.findAll()
                    .stream()
                    .filter(x -> x.getId().equals(Integer.parseInt(id)))
                    .findFirst()
                    .get();

            user.setEmail(email);
            user.setName(name);

            SHelper.redirectTo(request, response, "/Dashboard.jsp");
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
