/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author CCK
 */
@Entity
public class Product extends Model implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private Double price;

    @OneToMany(mappedBy = "product")
    private ArrayList<Rating> rating;

    private Date createdAt;
    private Date deletedAt;

    @ManyToMany(mappedBy = "products")
    private ArrayList<MyOrder> myOrders;

    public Product(String name, Double price) {
        this.createdAt = new Date(new java.util.Date().getTime());
        this.name = name;
        this.price = price;
    }

    public Product() {
        this.createdAt = new Date(new java.util.Date().getTime());
    }

    public ArrayList<Rating> getRating() {
        return rating;
    }

    public void setRating(ArrayList<Rating> rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public String getPriceInString() {
        return String.format("%.2f", price);
    }

    public ArrayList<MyOrder> getMyOrders() {
        return myOrders;
    }

    public void setMyOrders(ArrayList<MyOrder> myOrders) {
        this.myOrders = myOrders;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", price=" + price + '}';
    }

    @Override
    public String toTd(MyUser user) {
        return "<tr><td>" + this.getId() + "</td><td>" + this.getName()
                + "</td><td>" + this.getPriceInString() + "</td><td class='flex gap-1'>"
                + (user.can("Read Product") ? "<a class='btn btn-sm btn-success' href='/EE-war/Products/Show?id=" + this.getId() + "'>Show</a>" : "")
                + (user.can("Update Product") ? "<a class='btn btn-sm btn-accent' href='/EE-war/Products/Edit?id=" + this.getId() + "'>Edit</a>" : "")
                + (user.can("Delete Product") ? "<a class='btn btn-sm btn-info' href='/EE-war/Products/Delete?id=" + this.getId() + "'>Delete</a>" : "")
                + "</td></tr>";
    }

    @Override
    public String toSelection() {
        return "<option value='" + this.getId() + "'>" + this.getName() + "</option>";
    }

    @Override
    public String toShowTable() {
        return "<div class='overflow-x-auto mt-10'><table class='table w-2/3 mx-auto border'>"
                + "<tr class='border'><td>ID</td><td>" + this.getId() + " </td></tr>"
                + "<tr class='border'><td>Name</td><td>" + this.getName() + " </td></tr>"
                + "<tr class='border'><td>Price</td><td>" + this.getPriceInString() + " </td></tr>"
                + "</table></div>";
    }

}
