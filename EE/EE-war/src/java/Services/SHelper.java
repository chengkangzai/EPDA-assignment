/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author CCK
 */
public class SHelper {

    public static String getParam(HttpServletRequest req, String parameter) {
        return req.getParameter(parameter) == null ? "" : req.getParameter(parameter);
    }

    public static void redirectTo(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
        if (response.getHeader("Referer") != null) {
            response.sendRedirect(request.getContextPath() + url);
        } else {
            SHelper.forward(request, response, url);
        }
    }

    public static void back(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(request.getHeader("Referer"));
    }

    public static void forward(HttpServletRequest req, HttpServletResponse res, String url) throws ServletException, IOException {
        req.getRequestDispatcher(url).forward(req, res);
    }

    public static void incldue(HttpServletRequest req, HttpServletResponse res, String url) throws ServletException, IOException {
        req.getRequestDispatcher(url).include(req, res);
    }

    public static void setSession(HttpServletRequest req, String key, Object value) {
        req.getSession().setAttribute(key, value);
    }

    public static Object getOnce(HttpServletRequest request, String parameter) {
        Object temp = request.getSession().getAttribute(parameter);
        SHelper.setSession(request, parameter, null);
        return temp;
    }

    public static Object getSession(HttpServletRequest request, String parameter) {
        return request.getSession().getAttribute(parameter);
    }

}
