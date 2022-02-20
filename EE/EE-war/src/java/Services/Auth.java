/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
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
}
