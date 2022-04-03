package com.lingxiang2014.dao.impl;
 import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import com.lingxiang2014.dao.SnDao;
import com.lingxiang2014.entity.Sn;
import com.lingxiang2014.entity.Sn.Type;
import com.lingxiang2014.util.FreemarkerUtils;
import freemarker.template.TemplateException;
import com.lingxiang2014.DTO.Sn;
@Repository("snDaoImpl")
public class SnDaoImpl implements SnDao,InitializingBean{

 private  HiloOptimizer productHiloOptimizer;

 private  HiloOptimizer orderHiloOptimizer;

 private  HiloOptimizer paymentHiloOptimizer;

 private  HiloOptimizer refundsHiloOptimizer;

 private  HiloOptimizer shippingHiloOptimizer;

 private  HiloOptimizer returnsHiloOptimizer;

@PersistenceContext
 private  EntityManager entityManager;

@Value("${sn.product.prefix}")
 private  String productPrefix;

@Value("${sn.product.maxLo}")
 private  int productMaxLo;

@Value("${sn.order.prefix}")
 private  String orderPrefix;

@Value("${sn.order.maxLo}")
 private  int orderMaxLo;

@Value("${sn.payment.prefix}")
 private  String paymentPrefix;

@Value("${sn.payment.maxLo}")
 private  int paymentMaxLo;

@Value("${sn.refunds.prefix}")
 private  String refundsPrefix;

@Value("${sn.refunds.maxLo}")
 private  int refundsMaxLo;

@Value("${sn.shipping.prefix}")
 private  String shippingPrefix;

@Value("${sn.shipping.maxLo}")
 private  int shippingMaxLo;

@Value("${sn.returns.prefix}")
 private  String returnsPrefix;

@Value("${sn.returns.maxLo}")
 private  int returnsMaxLo;

 private  Type type;

 private  String prefix;

 private  int maxLo;

 private  int lo;

 private  long hi;

 private  long lastValue;


public long getLastValue(Type type){
    System.out.println(type.name());
    String jpql = "select sn from Sn sn where sn.type = :type";
    Sn sn = entityManager.createQuery(jpql, Sn.class).setFlushMode(FlushModeType.COMMIT).setLockMode(LockModeType.PESSIMISTIC_WRITE).setParameter("type", type).getSingleResult();
    long lastValue = sn.getLastValue();
    sn.setLastValue(lastValue + 1);
    entityManager.merge(sn);
    return lastValue;
}


public void afterPropertiesSet(){
    productHiloOptimizer = new HiloOptimizer(Type.product, productPrefix, productMaxLo);
    orderHiloOptimizer = new HiloOptimizer(Type.order, orderPrefix, orderMaxLo);
    paymentHiloOptimizer = new HiloOptimizer(Type.payment, paymentPrefix, paymentMaxLo);
    refundsHiloOptimizer = new HiloOptimizer(Type.refunds, refundsPrefix, refundsMaxLo);
    shippingHiloOptimizer = new HiloOptimizer(Type.shipping, shippingPrefix, shippingMaxLo);
    returnsHiloOptimizer = new HiloOptimizer(Type.returns, returnsPrefix, returnsMaxLo);
}


public String generate(){
    if (lo > maxLo) {
        lastValue = getLastValue(type);
        lo = lastValue == 0 ? 1 : 0;
        hi = lastValue * (maxLo + 1);
    }
    try {
        return FreemarkerUtils.process(prefix, null) + (hi + lo++);
    } catch (IOException e) {
        e.printStackTrace();
    } catch (TemplateException e) {
        e.printStackTrace();
    }
    return String.valueOf(hi + lo++);
}


}