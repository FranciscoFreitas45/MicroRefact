package com.easyshopping.dao.impl;
 import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;
import com.easyshopping.dao.ShippingDao;
import com.easyshopping.entity.Shipping;
import org.springframework.stereotype.Repository;
@Repository("shippingDaoImpl")
public class ShippingDaoImpl extends BaseDaoImpl<Shipping, Long>implements ShippingDao{


public Shipping findBySn(String sn){
    if (sn == null) {
        return null;
    }
    String jpql = "select shipping from Shipping shipping where lower(shipping.sn) = lower(:sn)";
    try {
        return entityManager.createQuery(jpql, Shipping.class).setFlushMode(FlushModeType.COMMIT).setParameter("sn", sn).getSingleResult();
    } catch (NoResultException e) {
        return null;
    }
}


}