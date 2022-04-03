package com.easyshopping.dao.impl;
 import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;
import com.easyshopping.dao.DeliveryCenterDao;
import com.easyshopping.entity.DeliveryCenter;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
@Repository("deliveryCenterDaoImpl")
public class DeliveryCenterDaoImpl extends BaseDaoImpl<DeliveryCenter, Long>implements DeliveryCenterDao{


public DeliveryCenter findDefault(){
    try {
        String jpql = "select deliveryCenter from DeliveryCenter deliveryCenter where deliveryCenter.isDefault = true";
        return entityManager.createQuery(jpql, DeliveryCenter.class).setFlushMode(FlushModeType.COMMIT).getSingleResult();
    } catch (NoResultException e) {
        return null;
    }
}


@Override
public DeliveryCenter merge(DeliveryCenter deliveryCenter){
    Assert.notNull(deliveryCenter);
    if (deliveryCenter.getIsDefault()) {
        String jpql = "update DeliveryCenter deliveryCenter set deliveryCenter.isDefault = false where deliveryCenter.isDefault = true and deliveryCenter != :deliveryCenter";
        entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT).setParameter("deliveryCenter", deliveryCenter).executeUpdate();
    }
    return super.merge(deliveryCenter);
}


@Override
public void persist(DeliveryCenter deliveryCenter){
    Assert.notNull(deliveryCenter);
    if (deliveryCenter.getIsDefault()) {
        String jpql = "update DeliveryCenter deliveryCenter set deliveryCenter.isDefault = false where deliveryCenter.isDefault = true";
        entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT).executeUpdate();
    }
    super.persist(deliveryCenter);
}


}