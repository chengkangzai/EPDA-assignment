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
public class Feedback implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String feedback;

    @OneToOne
    private Delivery delivery;

    public Feedback() {
    }

    public Feedback(String feedback, Delivery delivery) {
        this.feedback = feedback;
        this.delivery = delivery;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
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
        if (!(object instanceof Feedback)) {
            return false;
        }
        Feedback other = (Feedback) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Feedback[ id=" + id + " ]";
    }

}
