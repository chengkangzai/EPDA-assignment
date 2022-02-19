/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author CCK
 */
@Entity
public class Delivery implements Serializable {

    private static final long serialVersionUID = 1L;

    public enum Status {
        PENDING, WAREHOUSE, IN_TRANSIT, DELIVERED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Status status;

    @OneToOne
    private MyOrder order;

    @OneToOne
    private MyUser deliverTo;
    @OneToOne
    private MyUser deliverBy;
    @OneToOne
    private Rating rating;
    @OneToOne
    private Feedback feedback;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date deliverAt;

    public Delivery(Status status, MyOrder order, MyUser deliverTo) {
        this.status = status;
        this.order = order;
        this.deliverTo = deliverTo;
    }

    public Delivery() {
    }

    public MyOrder getOrder() {
        return order;
    }

    public void setOrder(MyOrder order) {
        this.order = order;
    }

    public MyUser getDeliverTo() {
        return deliverTo;
    }

    public void setDeliverTo(MyUser deliverTo) {
        this.deliverTo = deliverTo;
    }

    public MyUser getDeliverBy() {
        return deliverBy;
    }

    public void setDeliverBy(MyUser deliverBy) {
        this.deliverBy = deliverBy;
    }

    public Date getDeliverAt() {
        return deliverAt;
    }

    public void setDeliverAt(Date deliverAt) {
        this.deliverAt = deliverAt;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
        if (!(object instanceof Delivery)) {
            return false;
        }
        Delivery other = (Delivery) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Dellivery[ id=" + id + " ]";
    }

}
