/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.sql.Date;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
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
public class Delivery extends Model implements Serializable {

    private static final long serialVersionUID = 1L;

    public static enum Status {
        PENDING, WAREHOUSE, IN_TRANSIT, DELIVERED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Status status;

    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private MyOrder order;

    @OneToOne
    private MyUser deliverTo;
    @OneToOne
    private MyUser deliverBy;
    @OneToOne
    private Rating rating;

    private Date deliverAt;
    @OneToOne(mappedBy = "delivery")
    private Feedback feedback;

    public Delivery(Status status, MyOrder order, MyUser deliverTo, MyUser deliverBy) {
        this.status = status;
        this.order = order;
        this.deliverTo = deliverTo;
        this.deliverBy = deliverBy;
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

    public static String statusToSelection() {
        ArrayList<Status> s = Delivery.getAllStatus();
        return s
                .stream()
                .map(x -> "<option value='" + x + "'>" + x + "</option>")
                .collect(Collectors.joining());
    }

    public static ArrayList<Status> getAllStatus() {
        ArrayList<Status> s = new ArrayList();
        s.add(Status.DELIVERED);
        s.add(Status.IN_TRANSIT);
        s.add(Status.PENDING);
        s.add(Status.WAREHOUSE);
        return s;
    }

    public static Status findStatusByString(String status) {
        switch (status.toUpperCase()) {
            case "DELIVERED":
                return Status.DELIVERED;
            case "IN_TRANSIT":
                return Status.IN_TRANSIT;
            case "PENDING":
                return Status.PENDING;
            case "WAREHOUSE":
                return Status.WAREHOUSE;
            default:
                return Status.PENDING;
        }
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

    @Override
    public String toTd(MyUser user) {
        return "<tr><td>" + this.getId() + "</td><td>" + this.getStatus()
                + "</td><td>" + this.getDeliverTo().getName() + "</td>"
                + "<td>" + this.getDeliverBy().getName() + "</td>"
                + "<td class='flex gap-1'>"
                + (user.can("Read Delivery") ? "<a class='btn btn-sm btn-success' href='/EE-war/Deliveries/Show?id=" + this.getId() + "'>Show</a>" : "")
                + (user.can("Update Delivery") ? "<a class='btn btn-sm btn-accent' href='/EE-war/Deliveries/Edit?id=" + this.getId() + "'>Edit</a>" : "")
                + (user.can("Delete Delivery") ? "<a class='btn btn-sm btn-info' href='/EE-war/Deliveries/Delete?id=" + this.getId() + "'>Delete</a>" : "")
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
                + "<tr class='border'><td>Status</td><td>" + this.getStatus() + " </td></tr>"
                + "<tr class='border'><td>Customer</td><td>" + this.getDeliverTo().getName() + " </td></tr>"
                + "<tr class='border'><td>Assigned to </td><td>" + this.getDeliverBy().getName() + " </td></tr>"
                + "<tr class='border'><td>Order</td><td> Order ID : " + this.getOrder().getId() + " </td></tr>"
                + (this.getDeliverAt() != null ? "<tr class='border'><td>Created At</td><td>" + this.getDeliverAt().toString() + " </td></tr>" : "")
                + "</table></div>";
    }
}
