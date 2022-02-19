/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.Timeout;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author CCK
 */
@Entity
public class MyOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date createdAt;

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Product> products;

    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private MyUser purchaseBy;

    @OneToOne(mappedBy = "order")
    private Delivery delivery;

    public MyOrder(List<Product> products, MyUser purchaseBy) {
        this.createdAt = new Date();
        this.products = products;
        this.purchaseBy = purchaseBy;
    }

    public MyOrder() {
        this.createdAt = new Date();
    }

    public MyUser getPurchaseBy() {
        return purchaseBy;
    }

    public void setPurchaseBy(MyUser purchaseBy) {
        this.purchaseBy = purchaseBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
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
        if (!(object instanceof MyOrder)) {
            return false;
        }
        MyOrder other = (MyOrder) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Order[ id=" + id + " ]";
    }

}
