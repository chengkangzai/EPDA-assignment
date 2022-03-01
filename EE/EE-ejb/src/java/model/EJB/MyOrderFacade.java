/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.EJB;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.MyOrder;

/**
 *
 * @author CCK
 */
@Stateless
public class MyOrderFacade extends AbstractFacade<MyOrder> {

    @PersistenceContext(unitName = "EE-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MyOrderFacade() {
        super(MyOrder.class);
    }

    @Override
    public List<MyOrder> findAll() {
        return super.findAll().stream().filter(x -> x.getDeletedAt() == null).collect(Collectors.toList());
    }

    public List<MyOrder> findAllWithTrashed() {
        return super.findAll();
    }
    
     @Override
    public void truncate() {
        this.findAll().forEach(x -> x.setDeletedAt(new Date(new java.util.Date().getTime())));
    }
}
