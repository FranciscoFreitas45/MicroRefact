package com.zis.common.util;
 import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import org.apache.commons.lang3.StringUtils;
public class SequenceCreator {

 public  String SEQ_BOOK_GROUP_ID;

 public  String SEQ_BOOK_RELATE_ID;

 public  String SEQ_ORDER_GROUP_NUM;

 public  String SEQ_ORDER_MANUAL_NUM;

 private  EntityManagerFactory entityManagerFactory;


public void initEntityManagerFactory(){
    // SequenceCreator.class.getClassLoader().
    // ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    // HttpServletRequest request = attributes.getRequest();
    // ApplicationContext app = (ApplicationContext) request
    // .getAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE);
    // entityManagerFactory = (EntityManagerFactory) app.getBean("entityManagerFactory");
    entityManagerFactory = (EntityManagerFactory) SpringContextHolder.getBean("entityManagerFactory");
}


public Integer getSequence(String sequenceName){
    if (StringUtils.isBlank(sequenceName)) {
        throw new RuntimeException("illegal argument, for input null");
    }
    if (entityManagerFactory == null) {
        initEntityManagerFactory();
    }
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    String sql = "select _nextval('" + sequenceName + "')";
    entityManager.getTransaction().begin();
    Query query = entityManager.createNativeQuery(sql);
    entityManager.getTransaction().commit();
    Integer seq = (Integer) query.getSingleResult();
    entityManager.close();
    // 更高效的使用垃圾回收机制
    entityManager = null;
    if (seq == null) {
        throw new RuntimeException("no such sequence defined, sequenceName=" + sequenceName);
    }
    return seq;
}


}