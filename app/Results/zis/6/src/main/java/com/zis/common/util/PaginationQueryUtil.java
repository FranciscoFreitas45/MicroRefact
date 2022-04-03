package com.zis.common.util;
 import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class PaginationQueryUtil {

 private  SessionFactory sessionFactory;


public void initSessionFactory(){
    ApplicationContext context = new ClassPathXmlApplicationContext("conf-ds.xml");
    sessionFactory = (SessionFactory) context.getBean("sessionFactory");
}


@SuppressWarnings("rawtypes")
public List paginationQuery(DetachedCriteria detachedCriteria,Page page){
    if (sessionFactory == null) {
        initSessionFactory();
    }
    Session session = sessionFactory.openSession();
    Criteria c = detachedCriteria.getExecutableCriteria(session);
    c.setFirstResult(page.getBeginIndex());
    c.setMaxResults(page.getPageSize());
    List resultList = c.list();
    session.close();
    return resultList;
}


public Integer getTotalCount(DetachedCriteria detachedCriteria){
    if (sessionFactory == null) {
        initSessionFactory();
    }
    Session session = sessionFactory.openSession();
    Criteria c = detachedCriteria.getExecutableCriteria(session);
    // Integer result = (Integer) c.setProjection(Projections.rowCount()).uniqueResult();
    Integer result = ((Long) c.setProjection(Projections.rowCount()).uniqueResult()).intValue();
    c.setProjection(null);
    session.close();
    return result;
}


}