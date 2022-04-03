package com.easyshopping.dao.impl;
 import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;
import com.easyshopping.dao.DeliveryTemplateDao;
import com.easyshopping.entity.DeliveryTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
@Repository("deliveryTemplateDaoImpl")
public class DeliveryTemplateDaoImpl extends BaseDaoImpl<DeliveryTemplate, Long>implements DeliveryTemplateDao{


public DeliveryTemplate findDefault(){
    try {
        String jpql = "select deliveryTemplate from DeliveryTemplate deliveryTemplate where deliveryTemplate.isDefault = true";
        return entityManager.createQuery(jpql, DeliveryTemplate.class).setFlushMode(FlushModeType.COMMIT).getSingleResult();
    } catch (NoResultException e) {
        return null;
    }
}


@Override
public DeliveryTemplate merge(DeliveryTemplate deliveryTemplate){
    Assert.notNull(deliveryTemplate);
    if (deliveryTemplate.getIsDefault()) {
        String jpql = "update DeliveryTemplate deliveryTemplate set deliveryTemplate.isDefault = false where deliveryTemplate.isDefault = true and deliveryTemplate != :deliveryTemplate";
        entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT).setParameter("deliveryTemplate", deliveryTemplate).executeUpdate();
    }
    return super.merge(deliveryTemplate);
}


@Override
public void persist(DeliveryTemplate deliveryTemplate){
    Assert.notNull(deliveryTemplate);
    if (deliveryTemplate.getIsDefault()) {
        String jpql = "update DeliveryTemplate deliveryTemplate set deliveryTemplate.isDefault = false where deliveryTemplate.isDefault = true";
        entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT).executeUpdate();
    }
    super.persist(deliveryTemplate);
}


}