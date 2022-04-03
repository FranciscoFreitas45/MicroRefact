package com.easyshopping.dao.impl;
 import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import javax.persistence.FlushModeType;
import com.easyshopping.dao.ParameterDao;
import com.easyshopping.dao.ParameterGroupDao;
import com.easyshopping.entity.Parameter;
import com.easyshopping.entity.ParameterGroup;
import com.easyshopping.entity.Product;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import com.easyshopping.DTO.Product;
@Repository("parameterGroupDaoImpl")
public class ParameterGroupDaoImpl extends BaseDaoImpl<ParameterGroup, Long>implements ParameterGroupDao{

@Resource(name = "parameterDaoImpl")
 private  ParameterDao parameterDao;


@Override
public ParameterGroup merge(ParameterGroup parameterGroup){
    Assert.notNull(parameterGroup);
    Set<Parameter> excludes = new HashSet<Parameter>();
    CollectionUtils.select(parameterGroup.getParameters(), new Predicate() {

        public boolean evaluate(Object object) {
            Parameter parameter = (Parameter) object;
            return parameter != null && parameter.getId() != null;
        }
    }, excludes);
    List<Parameter> parameters = parameterDao.findList(parameterGroup, excludes);
    for (int i = 0; i < parameters.size(); i++) {
        Parameter parameter = parameters.get(i);
        String jpql = "select product from Product product join product.parameterValue parameterValue where index(parameterValue) = :parameter";
        List<Product> products = entityManager.createQuery(jpql, Product.class).setFlushMode(FlushModeType.COMMIT).setParameter("parameter", parameter).getResultList();
        for (Product product : products) {
            product.getParameterValue().remove(parameter);
            if (i % 20 == 0) {
                super.flush();
                super.clear();
            }
        }
    }
    return super.merge(parameterGroup);
}


public boolean evaluate(Object object){
    Parameter parameter = (Parameter) object;
    return parameter != null && parameter.getId() != null;
}


@Override
public void remove(ParameterGroup parameterGroup){
    if (parameterGroup != null) {
        for (int i = 0; i < parameterGroup.getParameters().size(); i++) {
            Parameter parameter = parameterGroup.getParameters().get(i);
            String jpql = "select product from Product product join product.parameterValue parameterValue where index(parameterValue) = :parameter";
            List<Product> products = entityManager.createQuery(jpql, Product.class).setFlushMode(FlushModeType.COMMIT).setParameter("parameter", parameter).getResultList();
            for (Product product : products) {
                product.getParameterValue().remove(parameter);
                if (i % 20 == 0) {
                    super.flush();
                    super.clear();
                }
            }
        }
        super.remove(super.merge(parameterGroup));
    }
}


}