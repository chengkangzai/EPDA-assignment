/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.Auth;
import Services.SHelper;
import Services.Validator;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import middleware.Gate;
import model.EJB.MyUserFacade;
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
            String email = SHelper.getParam(request, "email");
            String name = SHelper.getParam(request, "name");
            String password = SHelper.getParam(request, "password");
            String tp = SHelper.getParam(request, "tpnumber");
            String address = SHelper.getParam(request, "address");

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                SHelper.setSession(request, "validation_error", name);
                SHelper.back(request, response);
                return;
            }

            if (Auth.user(request).is("Customer")) {
                if (tp.isEmpty() || address.isEmpty()) {
                    SHelper.setSession(request, "validation_error", name);
                    SHelper.back(request, response);
                    return;
                }
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
            SHelper.setSession(request, "user", user);
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
