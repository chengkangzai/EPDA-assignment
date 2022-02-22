/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.users;

import Services.SHelper;
import Services.Validator;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import middleware.Gate;
import model.EJB.MyRoleFacade;
import model.EJB.MyUserFacade;
import model.MyRole;
import model.MyUser;

/**
 *
 * @author CCK
 */
@WebServlet(name = "Users.Edit", urlPatterns = {"/Users/Edit"})
public class Edit extends HttpServlet {

    @EJB
    private MyRoleFacade myRoleFacade;

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
        Gate.authorise(request, response, "Update User");

//Edit
        if (request.getMethod().toUpperCase().equals("GET")) {
            String id = SHelper.getParam(request, "id");
            MyUser user = this.userFacade.findAll()
                    .stream()
                    .filter(x -> x.getId().equals(Integer.parseInt(id)))
                    .findFirst()
                    .get();
            String roles = this.myRoleFacade
                    .findAll()
                    .stream()
                    .map(x -> x.toSelection())
                    .collect(Collectors.joining(""))
                    .replaceAll("name='" + user.getRole().getId() + "'", "name='" + user.getRole().getId() + "' selected");
            System.out.println(roles);
            SHelper.setSession(request, "form:roles", roles);
            SHelper.setSession(request, "form:user", user);
            request.getRequestDispatcher("Edit.jsp").include(request, response);
        }
        //Update
        if (request.getMethod().toUpperCase().equals("POST")) {
            String name = SHelper.getParam(request, "name");
            String email = SHelper.getParam(request, "email");
            String role = SHelper.getParam(request, "role");

            if (role.isEmpty() || name.isEmpty()) {
                SHelper.setSession(request, "validation_error", name);
                SHelper.back(request, response);
                return;
            }

            String id = SHelper.getParam(request, "id");
            MyUser user = this.userFacade.findAll()
                    .stream()
                    .filter(x -> x.getId().equals(Integer.parseInt(id)))
                    .findFirst()
                    .get();

            user.setEmail(email);
            user.setName(name);

            MyRole assignedRole = this.myRoleFacade
                    .findAll()
                    .stream()
                    .filter(x -> {
                        return x.getId() == Integer.parseInt(role);
                    })
                    .findFirst()
                    .orElse(user.getRole());

            user.setRole(assignedRole);
            this.userFacade.edit(user);

            SHelper.redirectTo(request, response, "/Users/Index");
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
