package com.easyshopping.dao.impl;
 import java.util.List;
import javax.persistence.FlushModeType;
import com.easyshopping.dao.AttributeDao;
import com.easyshopping.entity.Attribute;
import com.easyshopping.entity.Product;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
@Repository("attributeDaoImpl")
public class AttributeDaoImpl extends BaseDaoImpl<Attribute, Long>implements AttributeDao{


@Override
public void persist(Attribute attribute){
    Assert.notNull(attribute);
    String jpql = "select attribute.propertyIndex from Attribute attribute where attribute.productCategory = :productCategory";
    List<Integer> propertyIndexs = entityManager.createQuery(jpql, Integer.class).setFlushMode(FlushModeType.COMMIT).setParameter("productCategory", attribute.getProductCategory()).getResultList();
    for (int i = 0; i < Product.ATTRIBUTE_VALUE_PROPERTY_COUNT; i++) {
        if (!propertyIndexs.contains(i)) {
            attribute.setPropertyIndex(i);
            super.persist(attribute);
            break;
        }
    }
}


@Override
public void remove(Attribute attribute){
    if (attribute != null) {
        String propertyName = Product.ATTRIBUTE_VALUE_PROPERTY_NAME_PREFIX + attribute.getPropertyIndex();
        String jpql = "update Product product set product." + propertyName + " = null where product.productCategory = :productCategory";
        entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT).setParameter("productCategory", attribute.getProductCategory()).executeUpdate();
        super.remove(attribute);
    }
}


}