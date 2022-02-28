/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author CCK
 */
@Entity
public class MyUser extends Model implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String email;
    private String password;

    private String TPNumber;
    private String address;

    @OneToOne()
    private MyRole role;

    public MyUser(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public MyUser(String name, String email, String password, String TPNumber, String address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.TPNumber = TPNumber;
        this.address = address;
    }

    public MyUser() {
    }

    public MyRole getRole() {
        return role;
    }

    public void setRole(MyRole role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTPNumber() {
        return TPNumber == null ? "" : TPNumber;
    }

    public void setTPNumber(String TPNumber) {
        this.TPNumber = TPNumber;
    }

    public String getAddress() {
        return address == null ? "" : address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean can(String permission) {
        return this.role.getPermissions().stream().anyMatch(x -> x.getName().equals(permission));
    }

    public boolean is(String role) {
        return this.getRole().getName().equals(role);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MyUser)) {
            return false;
        }
        MyUser other = (MyUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MyUser{" + "id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", role=" + role + '}';
    }

    @Override
    public String toTd(MyUser user) {
        return "<tr><td>" + this.getId() + "</td><td>" + this.getName()
                + "</td><td>" + this.getEmail() + "</td><td>"
                + this.getRole().getName() + "</td><td class='flex gap-1'>"
                + "<a class='btn btn-sm btn-success' href='/EE-war/Users/Show?id=" + this.getId() + "'>Show</a>"
                + "<a class='btn btn-sm btn-accent' href='/EE-war/Users/Edit?id=" + this.getId() + "'>Edit</a>"
                + "<a class='btn btn-sm btn-info' href='/EE-war/Users/Delete?id=" + this.getId() + "'>Delete</a>"
                + "</td></tr>";
    }

    @Override
    public String toShowTable() {
        return "<div class='overflow-x-auto mt-10'><table class='table w-2/3 mx-auto border'>"
                + "<tr class='border'><td>ID</td><td>" + this.getId() + " </td></tr>"
                + "<tr class='border'><td>Name</td><td>" + this.getName() + " </td></tr>"
                + "<tr class='border'><td>Email</td><td>" + this.getEmail() + " </td></tr>"
                + "<tr class='border'><td>Role</td><td>" + this.getRole().getName() + " </td></tr>"
                + "</table></div>";
    }

    @Override
    public String toSelection() {
        return "<option value='" + this.getId() + "'>" + this.getName() + " </option>";
    }

}
