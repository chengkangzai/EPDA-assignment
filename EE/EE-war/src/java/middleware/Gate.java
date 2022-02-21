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
public class Gate {

    public static String FORBIIDEN = "/403.jsp";

    public static void authorise(HttpServletRequest req, HttpServletResponse res, String name) throws ServletException, IOException {
        Object param = req.getSession().getAttribute("user");
        if (param == null) {
            SHelper.redirectTo(req, res, Gate.FORBIIDEN);
            return;
        }
        try {
            MyUser user = (MyUser) param;
            if (!user.can(name) || !user.is(name)) {
                SHelper.redirectTo(req, res, Gate.FORBIIDEN);
            }
        } catch (NoClassDefFoundError | ClassCastException e) {
            SHelper.redirectTo(req, res, Gate.FORBIIDEN);
        }

    }
}
