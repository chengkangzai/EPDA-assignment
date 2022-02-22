/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware;

import Services.SHelper;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.MyUser;

/**
 *
 * @author CCK
 */
public class RedirectIfLoggedIn {

    public static void handle(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        MyUser user = (MyUser) req.getSession().getAttribute("user");

        if (user != null) {
            System.out.println("redirect user with Email " + user + " To dashboard as it is logged in");
            SHelper.redirectTo(req, res, "/Dashboard.jsp");
        }
    }
}
