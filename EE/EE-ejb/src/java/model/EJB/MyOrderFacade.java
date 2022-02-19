/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.EJB;

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
    
}
