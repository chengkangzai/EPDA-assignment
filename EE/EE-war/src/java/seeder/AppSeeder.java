/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seeder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import model.Delivery;
import model.EJB.DeliveryFacade;
import model.EJB.FeedbackFacade;
import model.EJB.MyOrderFacade;
import model.EJB.MyUserFacade;
import model.EJB.ProductFacade;
import model.EJB.RatingFacade;
import model.Feedback;
import model.MyOrder;
import model.MyUser;
import model.Product;
import model.Rating;

/**
 *
 * @author CCK
 */
public class AppSeeder {

    private final DeliveryFacade deliveryfacade;
    private final FeedbackFacade feedbackfacade;
    private final ProductFacade productFacade;
    private final RatingFacade ratingFacade;
    private final MyUserFacade userFacade;
    private final MyOrderFacade orderFacade;

    public AppSeeder(DeliveryFacade deliveryfacade, FeedbackFacade feedbackfacade, ProductFacade productFacade, RatingFacade ratingFacade, MyUserFacade userFacade, MyOrderFacade orderFacade) {
        this.deliveryfacade = deliveryfacade;
        this.feedbackfacade = feedbackfacade;
        this.productFacade = productFacade;
        this.ratingFacade = ratingFacade;
        this.userFacade = userFacade;
        this.orderFacade = orderFacade;
    }

    public void seed() {
        this.truncate().seedRating().seedFeedback().seedProduct().seedOrder().seedDelivery();
    }

    private AppSeeder truncate() {
        this.deliveryfacade.truncate();
        this.feedbackfacade.truncate();
        this.productFacade.truncate();
        return this;
    }

    private AppSeeder seedRating() {
        ArrayList<Rating> s = new ArrayList();
        for (int i = 0; i < new Random().nextInt(15); i++) {
            s.add(new Rating(new Random().nextInt(5)));
        }
        s.forEach(this.ratingFacade::create);
        return this;
    }

    private AppSeeder seedFeedback() {
        ArrayList<Feedback> s = new ArrayList();
        for (int i = 0; i < 10; i++) {
            s.add(new Feedback("Feedback " + i));
        }
        s.forEach(this.feedbackfacade::create);
        return this;
    }

    private AppSeeder seedProduct() {
        ArrayList<Product> s = new ArrayList();
        s.add(new Product("ICE MOUNTAIN MINERALWATER 600ml (RM1.70)", 1.8));
        s.add(new Product("BUN 2.90", 2.9));
        s.add(new Product("NESTLE MILO ICE 240ml", 3.1));
        s.add(new Product("Can Drinks", 2.5));
        s.forEach(this.productFacade::create);
        return this;
    }

    private AppSeeder seedOrder() {
        ArrayList<MyOrder> s = new ArrayList();
        List<Product> products = this.productFacade.findAll();
        List<MyUser> users = this.userFacade.findAll();

        for (int i = 0; i < new Random().nextInt(); i++) {
            Collections.shuffle(products);
            Collections.shuffle(users);

            List<Product> product = products.stream().limit(2).collect(Collectors.toList());
            MyUser user = users.get(0);

            s.add(new MyOrder(product, user));
        }

        s.forEach(this.orderFacade::create);
        return this;
    }

    private AppSeeder seedDelivery() {
        ArrayList<Delivery> s = new ArrayList();
        List<MyOrder> orders = this.orderFacade.findAll();

        orders.subList(0, 1).stream().forEach((order) -> {
            Delivery d = new Delivery(Delivery.Status.PENDING, order, order.getPurchaseBy());
            s.add(d);
        });

        s.forEach(this.deliveryfacade::create);
        return this;
    }

}
