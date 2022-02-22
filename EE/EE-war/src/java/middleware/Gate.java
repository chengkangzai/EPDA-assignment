/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware;

import Services.Auth;
import Services.SHelper;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author CCK
 */
public class Gate {

    public static String UNAUTHORIZED = "/401.jsp";
    public static String FORBIIDEN = "/403.jsp";

    public static void authorise(HttpServletRequest req, HttpServletResponse res, String name) throws ServletException, IOException {
//        if (Auth.user(req) == null) {
//            SHelper.redirectTo(req, res, Gate.UNAUTHORIZED);
//            return;
//        }
//
//        if (!Auth.can(req, name)) {
//            SHelper.redirectTo(req, res, Gate.FORBIIDEN);
//            return;
//        }

    }
}
