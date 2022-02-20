/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware;

import Services.ServletHelper;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author CCK
 */
public class RedirectIfLoggedIn {

    public static void handle(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String username = ServletHelper.getParam(req, "email");

        if (!username.isEmpty()) {
            System.out.println("redirect user with Email" + username + "To dashboard as it is logged in");
            ServletHelper.redirectTo(req, res, "/index.html");
        }
    }
}
