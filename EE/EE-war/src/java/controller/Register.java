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
import middleware.RedirectIfLoggedIn;
import model.EJB.MyUserFacade;

/**
 *
 * @author CCK
 */
@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class Register extends HttpServlet {

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

        if (request.getMethod().equals("GET")) {
            SHelper.redirectTo(request, response, "/Register.jsp");
            return;
        }

        String email = SHelper.getParam(request, "email");
        String name = SHelper.getParam(request, "name");
        String param = SHelper.getParam(request, "password");

        if (email.isEmpty() || name.isEmpty() || param.isEmpty() || !Validator.isValidEmail(email)) {
            SHelper.setSession(request, "validation_error", "");
            System.out.println("Register Servelet: Validation Error");
            SHelper.incldue(request, response, "Register.jsp");
            return;
        }

        Auth a = new Auth(userFacade);

        if (!a.attempRegister(email)) {
            SHelper.setSession(request, "error", "The Email is being used!");
            System.out.println("Register Servelet: The Email is being used!");
            SHelper.incldue(request, response, "Register.jsp");
        } else {
            SHelper.setSession(request, "user", a.register(email, param, name));
            System.out.println("Register Servelet: User Registered");
            RedirectIfLoggedIn.handle(request, response);
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
