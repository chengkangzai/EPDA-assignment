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

    public Product(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public Product() {
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

    public void setPrice(Double price) {
        this.price = price;
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
    public String toTd() {
        return "<tr><td>" + this.getId() + "</td><td>" + this.getName()
                + "</td><td>" + this.getPrice() + "</td><td class='flex gap-1'>"
                + "<a class='btn btn-sm btn-success' href='/EE-war/Products/Show?id=" + this.getId() + "'>Show</a>"
                + "<a class='btn btn-sm btn-accent' href='/EE-war/Products/Edit?id=" + this.getId() + "'>Edit</a>"
                + "<a class='btn btn-sm btn-info' href='/EE-war/Products/Delete?id=" + this.getId() + "'>Delete</a>"
                + "</td></tr>";
    }

    @Override
    public String toSelection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toShowTable() {
         return "<div class='overflow-x-auto mt-10'><table class='table w-2/3 mx-auto border'>"
                + "<tr class='border'><td>ID</td><td>" + this.getId() + " </td></tr>"
                + "<tr class='border'><td>Name</td><td>" + this.getName() + " </td></tr>"
                + "<tr class='border'><td>Price</td><td>" + this.getPrice()+ " </td></tr>"
                + "</table></div>";
    }

}
