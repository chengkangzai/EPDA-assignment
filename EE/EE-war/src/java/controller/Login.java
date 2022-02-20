/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.Auth;
import Services.ServletHelper;
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
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    @EJB
    private MyUserFacade myUserFacade;

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
            ServletHelper.redirectTo(request, response, "/Login.jsp");
            return;
        }

        String email = ServletHelper.getParam(request, "email");
        String password = ServletHelper.getParam(request, "password");

        if (email.isEmpty() || password.isEmpty() || !Validator.isValidEmail(email)) {
            ServletHelper.setSession(request, "validation_error", "");
            System.out.println("Login Servelet: Validation Error");
            System.out.println("Email :" + email);
            System.out.println("Password :" + password);
            ServletHelper.incldue(request, response, "Login.jsp");
            return;
        }

        Auth a = new Auth(myUserFacade);

        if (!a.attempLogin(email, password)) {
            ServletHelper.setSession(request, "error", "Your credential do not match in the system");
            System.out.println("Login Servelet: Credential donot match");
            ServletHelper.incldue(request, response, "Login.jsp");
        } else {
            ServletHelper.setSession(request, "user", a.user(email));
            System.out.println("Login Servelet: Credential match");
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
