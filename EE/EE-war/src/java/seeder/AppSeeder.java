/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seeder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import model.Delivery;
import model.Delivery.Status;
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
        this.truncate().seedProduct().seedOrder().seedDelivery().seedRating().seedFeedback();
    }

    private AppSeeder truncate() {
        this.ratingFacade.truncate();
        this.feedbackfacade.truncate();
        this.deliveryfacade.truncate();
        this.orderFacade.truncate();
        this.productFacade.truncate();
        return this;
    }

    private AppSeeder seedRating() {
        List<Product> p = this.productFacade.findAll();
        List<MyUser> u = this.userFacade.findAll().stream().filter(x -> x.is("Customer")).collect(Collectors.toList());
        for (int i = 0; i < new Random().nextInt(15); i++) {
            Product product = p.get(new Random().nextInt(p.size()));
            MyUser user = u.get(new Random().nextInt(u.size()));
            Rating rating = new Rating(new Random().nextInt(5), product, user);
            this.ratingFacade.create(rating);
            user.setRating(rating);
            this.userFacade.edit(user);
            product.getRating().add(rating);
            this.productFacade.edit(product);
        }
        return this;
    }

    private AppSeeder seedFeedback() {
        List<Delivery> d = this.deliveryfacade.findAll();
        for (int i = 0; i < d.size(); i++) {
            Delivery delivery = d.get(i);
            Feedback feedback = new Feedback("Feedback " + i, delivery);
            this.feedbackfacade.create(feedback);
            delivery.setFeedback(feedback);
            this.deliveryfacade.edit(delivery);
        }
        return this;
    }

    private AppSeeder seedProduct() {
        ArrayList<Product> s = new ArrayList();
        s.add(new Product("MINERAL WATER 600ml", 1.8));
        s.add(new Product("BUN", 2.9));
        s.add(new Product("NESTLE MILO ICE 240ml", 3.1));
        s.add(new Product("Can Drinks", 2.5));
        s.add(new Product("POKKA MILK COFFEE 500ml", 4.6));
        s.add(new Product("SPRITZER NATURAL MINERAL WATER 600ml", 2.0));
        s.add(new Product("INDO MIE SOTO FLAVOUR 65G", 3.0));
        s.add(new Product("MAGGI HOT CUP CURRY 58g", 3.0));
        s.add(new Product("PEPSI REGULAR (CAN) 325ml", 2.5));
        s.add(new Product("NESCAFE LATTE (CAN) 240ml", 3.1));
        s.add(new Product("DOLL VEGETARIAN WITH WTH SESAME OIL 107g", 6.5));
        s.add(new Product("PANADOL CAPLET 500mg", 4.7));
        s.add(new Product("MAGGIE HOT MEALZ AYAM BIJAN PANGGANG", 4.1));
        s.add(new Product("PENANG WHITE CURRY", 6.8));
        s.forEach(this.productFacade::create);
        return this;
    }

    private AppSeeder seedOrder() {
        List<Product> products = this.productFacade.findAll();
        List<MyUser> users = this.userFacade.findAll().stream().filter(x -> x.getRole().getName().equals("Customer")).collect(Collectors.toList());

        for (int i = 0; i < new Random().nextInt(30); i++) {
            Collections.shuffle(products);
            Collections.shuffle(users);

            List<Product> product = products.stream().limit(2).collect(Collectors.toList());
            MyUser user = users.get(new Random().nextInt(users.size()));

            MyOrder order = new MyOrder(product, user);
            // generate random date that is between 1 and 10 days ago
            Date date = new Date(new Date().getTime() - (new Random().nextInt(10) + 1) * 24 * 60 * 60 * 1000);
            order.setCreatedAt(new java.sql.Date(date.getTime()));

            this.orderFacade.create(order);
            product.forEach(x -> {
                x.getMyOrders().add(order);
                this.productFacade.edit(x);
            });
        }

        return this;
    }

    private AppSeeder seedDelivery() {
        List<MyOrder> orders = this.orderFacade.findAll();
        List<MyUser> users = this.userFacade.findAll();
        List<MyUser> deliveryStaff = users.stream().filter(x -> x.getRole().getName().equals("Delivery Staff")).collect(Collectors.toList());
        List<Status> status = Delivery.getAllStatus();

        orders.stream().forEach((order) -> {
            Delivery d = new Delivery(
                    status.get(new Random().nextInt(status.size())),
                    order,
                    order.getPurchaseBy().getAddress(),
                    deliveryStaff.get(new Random().nextInt(deliveryStaff.size()))
            );
            this.deliveryfacade.create(d);
            order.setDelivery(d);
            this.orderFacade.edit(order);
        });

        return this;
    }

}
