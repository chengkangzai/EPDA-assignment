/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.SHelper;
import Services.SendMail;
import Services.Validator;
import java.io.IOException;
import java.util.Random;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.EJB.MyUserFacade;
import model.MyUser;

/**
 *
 * @author CCK
 */
@WebServlet(name = "ForgotPassword", urlPatterns = {"/ForgotPassword"})
public class ForgotPassword extends HttpServlet {

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
            SHelper.redirectTo(request, response, "/ForgotPassword.jsp");
            return;
        }

        if (request.getMethod().equals("POST")) {
            String email = SHelper.getParam(request, "email");

            if (email.isEmpty() || !Validator.isValidEmail(email)) {
                SHelper.setSession(request, "validation_error", "");
                SHelper.incldue(request, response, "/ForgotPassword.jsp");
                return;
            }

            MyUser user = this.userFacade.findAll()
                    .stream()
                    .filter(x -> x.getEmail().equals(email))
                    .findFirst()
                    .orElse(null);

            if (user == null) {
                SHelper.setSession(request, "error", "User with that email does not match");
                SHelper.incldue(request, response, "/ForgotPassword.jsp");
                return;
            }

            Integer password = new Random().nextInt(1000000);
            user.setPassword(String.valueOf(password));
            this.userFacade.edit(user);
            new SendMail()
                    .to(email)
                    .subject("Reset Password")
                    .salute("Hi there!")
                    .content("Your new password is: " + password)
                    .signature()
                    .send();
            SHelper.setSession(request, "success", "Reset password email is sent");
            SHelper.back(request, response);
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
