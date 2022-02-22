/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.EJB.MyUserFacade;
import model.MyUser;

/**
 *
 * @author CCK
 */
public class Auth {

    private MyUserFacade userFacade;

    public Auth(MyUserFacade userFacade) {
        this.userFacade = userFacade;
    }

    public boolean attempLogin(String email, String password) {
        return this.userFacade.findAll()
                .stream()
                .filter(x -> x.getEmail().equals(email) && x.getPassword().equals(password))
                .findFirst()
                .isPresent();
    }

    public MyUser user(String email) {
        return this.userFacade.findAll()
                .stream()
                .filter(x -> x.getEmail().equals(email))
                .findFirst()
                .get();
    }

    public boolean attempRegister(String email) {
        return !this.userFacade.findAll()
                .stream()
                .filter(x -> x.getEmail().equals(email))
                .findFirst()
                .isPresent();
    }

    public MyUser register(String email, String password, String name) {
        this.userFacade.create(new MyUser(name, email, password));
        return this.user(email);
    }

    public static MyUser user(HttpServletRequest request) {
        try {
            return (MyUser) request.getSession().getAttribute("user");
        } catch (NoClassDefFoundError | ClassCastException e) {
            return new MyUser();
        }
    }

    public static boolean can(HttpServletRequest req, String name) {
        Object u = req.getSession().getAttribute("user");
        if (u == null) {
            return false;
        }
        try {
            MyUser user = (MyUser) u;
            return user.can(name) || user.is(name);
        } catch (NoClassDefFoundError | ClassCastException e) {
            return false;
        }

    }

    public static boolean canAny(HttpServletRequest req, ArrayList<String> name) {
        Object u = req.getSession().getAttribute("user");
        if (u == null) {
            return false;
        }
        try {
            MyUser user = (MyUser) u;
            return user.is("Super Admin") || name.stream().anyMatch((string) -> (user.can(string) || user.is(string)));
        } catch (NoClassDefFoundError | ClassCastException e) {
            return false;
        }
    }

}
