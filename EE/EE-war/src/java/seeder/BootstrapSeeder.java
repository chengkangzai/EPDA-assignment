package seeder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import model.MyRole;
import model.MyUser;
import model.Permission;
import model.EJB.PermissionFacade;
import model.EJB.MyRoleFacade;
import model.EJB.MyUserFacade;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author CCK
 */
public class BootstrapSeeder {

    private final PermissionFacade permissionfacade;
    private final MyUserFacade userFacade;
    private final MyRoleFacade roleFacade;

    public BootstrapSeeder(PermissionFacade permissionfacade, MyUserFacade userFacade, MyRoleFacade roleFacade) {
        this.permissionfacade = permissionfacade;
        this.userFacade = userFacade;
        this.roleFacade = roleFacade;
    }

    public void seed() {
        this.truncate().seedRoles().seedPermissions().seedUser().assignPermissions().assignRole();
    }

    private BootstrapSeeder truncate() {
        this.userFacade.truncate();
        this.permissionfacade.truncate();
        this.roleFacade.truncate();
        return this;
    }

    private BootstrapSeeder seedUser() {
        ArrayList<MyUser> s = new ArrayList();
        s.add(new MyUser("Super Admin", "pycck@hotmail.com", "password"));
        s.add(new MyUser("Managing Staff", "managing@EE.com", "password"));
        s.add(new MyUser("Delivery Staff", "delivery@EE.com", "password"));
        s.add(new MyUser("Customer", "customer@EE.com", "password"));
        s.forEach(this.userFacade::create);
        return this;
    }

    private BootstrapSeeder seedRoles() {
        ArrayList<MyRole> s = new ArrayList();
        s.add(new MyRole("Super Admin"));
        s.add(new MyRole("Delivery Staff"));
        s.add(new MyRole("Managing Staff"));
        s.add(new MyRole("Customer"));
        s.forEach(this.roleFacade::create);
        return this;
    }

    private BootstrapSeeder seedPermissions() {
        this.permissionfacade.truncate();

        ArrayList<Permission> p = new ArrayList();
        List<String> module = Arrays
                .asList("User", "Rating", "Feedback", "Product", "Order");
        List<String> verb = Arrays
                .asList("Create", "Read", "Update", "Delete");

        //create a map of module and verb
        module.forEach((m) -> {
            verb.forEach((v) -> {
                p.add(new Permission(v + " " + m));
            });
        });

        p.add(new Permission("Update Profile"));

        p.forEach(this.permissionfacade::create);
        return this;
    }

    private BootstrapSeeder assignPermissions() {
        this.roleFacade.findAll().forEach((MyRole role) -> {
            List<Permission> can = getPermissionWithRole(role);
            role.setPermissions(can);
            this.roleFacade.edit(role);
        });

        return this;
    }

    private List<Permission> getPermissionWithRole(MyRole role) {
        List<Permission> p = this.permissionfacade.findAll();
        switch (role.getName()) {
            case "Managing Staff":
                return p.stream().filter((x) -> {
                    String name = x.getName();
                    return name.endsWith("User")
                            || name.endsWith("Product")
                            || name.endsWith("Delivery")
                            || name.equals("Read Feedback")
                            || name.equals("Read Rating")
                            || name.equals("Read Order")
                            || name.equals("Update Profile");
                })
                        .collect(Collectors.toList());
            case "Delivery Staff":
                return p.stream().filter((x) -> {
                    String name = x.getName();
                    return name.equals("Update Delivery")
                            || name.equals("Update Order")
                            || name.equals("Update Profile");
                })
                        .collect(Collectors.toList());
            case "Customer":
                return p.stream().filter((x) -> {
                    String name = x.getName();
                    return name.endsWith("Order")
                            || name.endsWith("Rating")
                            || name.endsWith("Feedback")
                            || name.equals("Update Profile")
                            || name.equals("Read Product");
                })
                        .collect(Collectors.toList());
            case "Super Admin":
                return p;
            default:
                return null;
        }

    }

    private BootstrapSeeder assignRole() {
        List<MyRole> roles = this.roleFacade.findAll();
        this.userFacade.findAll().forEach((MyUser x) -> {
            switch (x.getName()) {
                case "Super Admin":
                    x.setRole(roles.stream().filter(y -> y.getName().equals("Super Admin")).findFirst().get());
                    break;
                case "Customer":
                    x.setRole(roles.stream().filter(y -> y.getName().equals("Customer")).findFirst().get());
                    break;
                case "Managing Staff":
                    x.setRole(roles.stream().filter(y -> y.getName().equals("Managing Staff")).findFirst().get());
                    break;
                case "Delivery Staff":
                    x.setRole(roles.stream().filter(y -> y.getName().equals("Delivery Staff")).findFirst().get());
                    break;
            }
            this.userFacade.edit(x);
        });

        return this;
    }
}
