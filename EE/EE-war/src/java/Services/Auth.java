/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

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
}
