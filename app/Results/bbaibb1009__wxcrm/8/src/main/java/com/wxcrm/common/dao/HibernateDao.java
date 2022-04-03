package com.wxcrm.common.dao;
 import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class HibernateDao implements IHibernateDao{

@Autowired
 private  HibernateTemplate hibernateTemplate;


public Serializable add(Object entity){
    return hibernateTemplate.save(entity);
}


public void flush(){
    hibernateTemplate.flush();
}


public T get(Class<T> entityClass,Serializable id){
    return hibernateTemplate.get(entityClass, id);
}


public List<T> query(String hql,Object[] args,int index,int max){
    return hibernateTemplate.executeWithNativeSession(new HibernateCallback<List<T>>() {

        @SuppressWarnings("unchecked")
        public List<T> doInHibernate(Session session) throws HibernateException {
            Query queryObject = session.createQuery(hql);
            // prepareQuery(queryObject); 源码执行了此方法
            if (args != null) {
                for (int i = 0; i < args.length; i++) {
                    queryObject.setParameter(i, args[i]);
                }
            }
            queryObject.setFirstResult(index);
            queryObject.setMaxResults(max);
            return queryObject.list();
        }
    });
}


public void clear(){
    hibernateTemplate.clear();
}


public void update(Object entity){
    hibernateTemplate.update(entity);
}


public int bulkUpdate(String hql,Object[] args){
    return hibernateTemplate.bulkUpdate(hql, args);
}


@SuppressWarnings("unchecked")
public List<T> doInHibernate(Session session){
    Query queryObject = session.createQuery(hql);
    // prepareQuery(queryObject); 源码执行了此方法
    if (args != null) {
        for (int i = 0; i < args.length; i++) {
            queryObject.setParameter(i, args[i]);
        }
    }
    queryObject.setFirstResult(index);
    queryObject.setMaxResults(max);
    return queryObject.list();
}


public void delete(Object entity){
    hibernateTemplate.delete(entity);
}


public void evict(Object entity){
    hibernateTemplate.evict(entity);
}


}