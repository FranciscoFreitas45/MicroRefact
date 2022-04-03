package net.shangtech.ssh.core.base;
 import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
@SuppressWarnings("unchecked")
public class BaseDao extends HibernateDaoSupport{


public int countAll(){
    String queryString = "select count(o) from " + getEntityClass().getSimpleName() + " o ";
    Object count = gather(queryString);
    if (count != null)
        return ((Long) count).intValue();
    return 0;
}


public int count(String hql,Object values){
    String queryString = "select count(o) from " + getEntityClass().getSimpleName() + " o " + hql;
    Object count = gather(queryString, values);
    if (count != null)
        return ((Long) count).intValue();
    return 0;
}


public void insert(T entity){
    this.getHibernateTemplate().save(entity);
}


public void update(T entity){
    this.getHibernateTemplate().merge(entity);
}


@Override
public List<Object> doInHibernate(Session session){
    Query query = session.createSQLQuery(sql);
    if (values != null && values.length > 0) {
        for (int i = 0; i < values.length; i++) {
            query.setParameter(i, values[i]);
        }
    }
    return query.list();
}


public Object gather(String queryString,Object values){
    List<Object> list = super.getHibernateTemplate().find(queryString, values);
    if (list != null && !list.isEmpty())
        return list.get(0);
    return null;
}


public void delete(int id){
    this.getHibernateTemplate().delete(this.find(id));
}


public List<T> findAll(int start,int limit){
    return findAll(start, limit, null);
}


public void execute(String queryString,Object values){
    super.getHibernateTemplate().bulkUpdate(queryString, values);
}


public Class<?> getEntityClass(){
    Class<?> entityClass = (Class<?>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    return entityClass;
}


public List<T> find(int start,int limit,String hql,Object values){
    final String queryString = "select o from " + getEntityClass().getSimpleName() + " o " + hql;
    return super.getHibernateTemplate().execute(new HibernateCallback<List<T>>() {

        public List<T> doInHibernate(Session session) throws HibernateException, SQLException {
            Query query = session.createQuery(queryString);
            if (values != null && values.length > 0) {
                for (int i = 0; i < values.length; i++) {
                    query.setParameter(i, values[i]);
                }
            }
            query.setFirstResult(start);
            query.setMaxResults(limit);
            return query.list();
        }
    });
}


public T findUnique(String hql,Object values){
    List<T> list = find(hql, values);
    if (!list.isEmpty()) {
        return list.get(0);
    }
    return null;
}


@Autowired
public void setMySessionFactory(SessionFactory sessionFactory){
    super.setSessionFactory(sessionFactory);
}


public List<Object> executeSql(String sql,Object values){
    return getHibernateTemplate().execute(new HibernateCallback<List<Object>>() {

        @Override
        public List<Object> doInHibernate(Session session) throws HibernateException, SQLException {
            Query query = session.createSQLQuery(sql);
            if (values != null && values.length > 0) {
                for (int i = 0; i < values.length; i++) {
                    query.setParameter(i, values[i]);
                }
            }
            return query.list();
        }
    });
}


}