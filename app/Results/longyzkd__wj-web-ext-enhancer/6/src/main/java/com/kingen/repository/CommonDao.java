package com.kingen.repository;
 import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.shiro.util.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import com.google.common.collect.Maps;
import com.kingen.util.Page;
import com.kingen.util.Parameter;
import com.kingen.util.Reflections;
import DTO.Page;
@Repository(value = "commonDao")
public class CommonDao {

@Autowired
 private  SessionFactory sessionFactory;

 private  Class<T> entityClass;

/**
 * 构造方法，根据实例类自动获取实体类类型
 */
public CommonDao() {
    // 构造器 在spring初始化的时候执行，创建各个BEAN
    entityClass = Reflections.getClassGenricType(getClass());
}
public void setEntityClass(Class<T> entityClass){
    this.entityClass = entityClass;
}


public void flushAndClear(){
    getCurrentSession().flush();
    getCurrentSession().clear();
}


public void savePre(T entity){
    try {
        // 获取实体编号
        Object id = null;
        for (Method method : entity.getClass().getMethods()) {
            Id idAnn = method.getAnnotation(Id.class);
            if (idAnn != null) {
                id = method.invoke(entity);
                break;
            }
        }
        // 插入前执行方法
        // if (StringUtils.isBlank((String)id)){
        if (id == null) {
            // id为Long
            for (Method method : entity.getClass().getMethods()) {
                PrePersist pp = method.getAnnotation(PrePersist.class);
                if (pp != null) {
                    method.invoke(entity);
                    break;
                }
            }
        } else // 更新前执行方法
        {
            for (Method method : entity.getClass().getMethods()) {
                PreUpdate pu = method.getAnnotation(PreUpdate.class);
                if (pu != null) {
                    method.invoke(entity);
                    break;
                }
            }
        }
    } catch (IllegalArgumentException e) {
        e.printStackTrace();
    } catch (IllegalAccessException e) {
        e.printStackTrace();
    } catch (InvocationTargetException e) {
        e.printStackTrace();
    }
    // catch (Exception e) { //catch住  不会回滚
    // e.printStackTrace();
    // }
    getCurrentSession().saveOrUpdate(entity);
}


public void save(T o){
    if (o != null) {
        getCurrentSession().saveOrUpdate(o);
    }
}


public void update(T o){
    if (o != null) {
        getCurrentSession().update(o);
    }
}


public SQLQuery createSqlQuery(String sqlString){
    SQLQuery query = getCurrentSession().createSQLQuery(sqlString);
    setParameter(query, null);
    return query;
}


public Serializable saveWithReturnId(T o){
    return getCurrentSession().save(o);
}


@Deprecated
public T getByHql(String hql,Map<String,Object> params){
    Query q = createQuery(hql, params);
    List<T> l = q.list();
    if (l != null && l.size() > 0) {
        return l.get(0);
    }
    return null;
}


public int delete(PK id,String idName){
    return deleteEntity(entityClass.getSimpleName(), idName, id);
}


public List<T> findAll(){
    return getCurrentSession().createCriteria(entityClass).list();
}


public void setResultTransformer(Query query,Class<?> resultClass){
    if (resultClass != null) {
        if (resultClass == Map.class) {
            query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        } else if (resultClass == List.class) {
            query.setResultTransformer(Transformers.TO_LIST);
        } else {
            // 任意类
            query.setResultTransformer(Transformers.aliasToBean(resultClass));
        }
    }
}


public int deleteEntity(String entityName,String idName,PK id){
    String hql = idHqlSetter(entityName, idName);
    hql = "delete from " + hql;
    Map<String, Object> params = Maps.newHashMapWithExpectedSize(1);
    params.put("p1", id);
    Query q = createQuery(hql, params);
    return q.executeUpdate();
}


@SuppressWarnings("unchecked")
public List<T> find(DetachedCriteria detachedCriteria,ResultTransformer resultTransformer){
    Criteria criteria = detachedCriteria.getExecutableCriteria(getCurrentSession());
    criteria.setResultTransformer(resultTransformer);
    return criteria.list();
}


public List<T> find1(String hql,int first,int max){
    Query q = createQuery(hql);
    return q.setFirstResult(first).setMaxResults(max).list();
}


public List<E> findme(String hql,Map<String,Object> params){
    Query q = createQuery(hql, params);
    return q.list();
}


public void setParameter(Query query,Map<String,Object> parameter){
    if (parameter != null) {
        Set<String> keySet = parameter.keySet();
        for (String string : keySet) {
            Object value = parameter.get(string);
            // 这里考虑传入的参数是什么类型，不同类型使用的方法不同
            if (value instanceof Collection<?>) {
                query.setParameterList(string, (Collection<?>) value);
            } else if (value instanceof Object[]) {
                query.setParameterList(string, (Object[]) value);
            } else {
                query.setParameter(string, value);
            }
        }
    }
}


public DetachedCriteria createDetachedCriteria(Criterion criterions){
    DetachedCriteria dc = DetachedCriteria.forClass(entityClass);
    for (Criterion c : criterions) {
        dc.add(c);
    }
    return dc;
}


public void saveOrUpdate(T o){
    if (o != null) {
        getCurrentSession().saveOrUpdate(o);
    }
}


public BigInteger countBySql(String sql,Map<String,Object> params){
    SQLQuery q = createSqlQuery(sql);
    return (BigInteger) q.uniqueResult();
}


public void saveme(Object o){
    if (o != null) {
        getCurrentSession().saveOrUpdate(o);
    }
}


public X uniqueEntity(String entityName,String idName,PK id){
    Validate.isTrue(id != null, "id不应为空");
    String hql = idHqlSetter(entityName, idName);
    hql = "from " + hql;
    Map<String, Object> params = Maps.newHashMapWithExpectedSize(1);
    params.put("p1", id);
    Query q = createQuery(hql, params);
    return (X) q.uniqueResult();
}


public String removeOrders(String qlString){
    Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
    Matcher m = p.matcher(qlString);
    StringBuffer sb = new StringBuffer();
    while (m.find()) {
        m.appendReplacement(sb, "");
    }
    m.appendTail(sb);
    return sb.toString();
}


public String queryHqlSetter(String entityName,String fieldNames){
    Assert.hasText(entityName, "entityName不应为空");
    String hql = "from " + entityName + " where 1=1 ";
    if (!ArrayUtils.isEmpty(fieldNames)) {
        for (String field : fieldNames) {
            hql += " and " + field + " =:" + field;
        }
    }
    return hql;
}


public Query createQuery(String qlString){
    Query query = getCurrentSession().createQuery(qlString);
    return query;
}


public T one(String hql,Map<String,Object> params){
    return (T) createQuery(hql, params).uniqueResult();
}


@SuppressWarnings("rawtypes")
public long count(DetachedCriteria detachedCriteria){
    Criteria criteria = detachedCriteria.getExecutableCriteria(getCurrentSession());
    long totalCount = 0;
    try {
        // Get orders
        Field field = CriteriaImpl.class.getDeclaredField("orderEntries");
        field.setAccessible(true);
        List orderEntrys = (List) field.get(criteria);
        // Remove orders
        field.set(criteria, new ArrayList());
        // Get count
        criteria.setProjection(Projections.rowCount());
        totalCount = Long.valueOf(criteria.uniqueResult().toString());
        // Clean count
        criteria.setProjection(null);
        // Restore orders
        field.set(criteria, orderEntrys);
    } catch (NoSuchFieldException e) {
        e.printStackTrace();
    } catch (IllegalAccessException e) {
        e.printStackTrace();
    }
    return totalCount;
}


public List<T> findCriteria(){
    DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entityClass);
    return find(detachedCriteria);
}


public String removeSelect(String qlString){
    int beginPos = qlString.toLowerCase().indexOf("from");
    return qlString.substring(beginPos);
}


public int executeHql(String hql,Map<String,Object> params){
    Query q = createQuery(hql, params);
    return q.executeUpdate();
}


public List<T> findListBySql(String sql,int page,int rows,T t){
    SQLQuery q = getCurrentSession().createSQLQuery(sql).addEntity(t.toString(), t.getClass());
    return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
}


public String idHqlSetter(String entityName,String idName){
    Assert.hasText(entityName, "entityName不应为空");
    Assert.hasText(idName, "idName不应为空");
    // 无法保证id的类型，只能用setParameter方法 传参
    String hql = entityName + " where " + idName + "  = :p1";
    return hql;
}


public Class<T> getEntityClass(){
    return entityClass;
}


public List<X> findByEntity(X x,String fields){
    String hql = queryHqlSetter(x.getClass().getSimpleName(), fields);
    Map<String, Object> params = Maps.newHashMapWithExpectedSize(fields.length);
    for (String field : fields) {
        params.put(field, Reflections.getFieldValue(x, field));
    }
    Query q = createQuery(hql, params);
    return q.list();
}


@SuppressWarnings("unchecked")
public List<E> findBySql(String sqlString,Map<String,Object> parameter,Class<?> resultClass){
    SQLQuery query = createSqlQuery(sqlString, parameter);
    setResultTransformer(query, resultClass);
    return query.list();
}


@SuppressWarnings("unchecked")
public Page<X> findPageByHql(Page<X> page,String hql,Parameter params){
    String qlString = hql;
    // get count
    String countQlString = "select count(*) " + removeSelect(removeOrders(qlString));
    Query query = createQuery(countQlString, params);
    List<Object> list = query.list();
    if (list.size() > 0) {
        page.setTotal(Integer.valueOf(list.get(0).toString()));
    } else {
        page.setTotal(list.size());
    }
    if (page.getTotal() < 1) {
        return page;
    }
    // order by
    String ql = qlString;
    if (StringUtils.isNotBlank(page.getOrderBy())) {
        ql += " order by " + page.getOrderBy();
    }
    Query query1 = createQuery(ql, params);
    // set page
    query1.setFirstResult(page.getFirstResult());
    query1.setMaxResults(page.getLimit());
    page.setDataList(query1.list());
    return page;
}


public void updateme(Object o){
    if (o != null) {
        getCurrentSession().update(o);
    }
}


public T getById(Class<T> c,Serializable id){
    return (T) getCurrentSession().get(c, id);
}


public Session getCurrentSession(){
    return sessionFactory.getCurrentSession();
}


public T unique(Serializable id){
    return getById(entityClass, id);
}


@SuppressWarnings("unchecked")
public Page<X> findPageHiberEntityBySql(Page<X> page,String sql,Parameter params,Class<X> resultClass){
    String qlString = sql;
    // get count
    String countQlString = "select count(*) " + removeSelect(removeOrders(qlString));
    SQLQuery query = createSqlQuery(countQlString, params);
    List<Object> list = query.list();
    if (list.size() > 0) {
        page.setTotal(Integer.valueOf(list.get(0).toString()));
    } else {
        page.setTotal(list.size());
    }
    if (page.getTotal() < 1) {
        return page;
    }
    // order by
    String ql = qlString;
    if (StringUtils.isNotBlank(page.getOrderBy())) {
        ql += " order by " + page.getOrderBy();
    }
    SQLQuery query1 = createSqlQuery(ql, params);
    // set page
    query1.setFirstResult(page.getFirstResult());
    query1.setMaxResults(page.getLimit());
    setResultTransformer(query1, resultClass);
    page.setDataList(query1.list());
    return page;
}


@Deprecated
public T oneByEntity(String entityName,Map<String,Object> params){
    String hql = queryHqlSetter(entityName, params);
    Query q = createQuery(hql, params);
    return (T) q.uniqueResult();
}


public void saveOrUpdateX(X o){
    if (o != null) {
        getCurrentSession().saveOrUpdate(o);
    }
}


public Serializable saveObjWithReturnId(Object o){
    return getCurrentSession().save(o);
}


public List<E> findExcept(String beanClazz,String property,Object val,Object rawValue){
    String hql = " from " + beanClazz + "  where id not in ( select id  from " + beanClazz + " where  " + property + " = :p1 ) and " + property + " =:p2";
    return findme(hql, new Parameter(rawValue, val));
}


@SuppressWarnings("unchecked")
public Page<X> findPageBySql(Page<X> page,String sql,Parameter params,Class<X> resultClass){
    String qlString = sql;
    // get count
    String countQlString = "select count(*) " + removeSelect(removeOrders(qlString));
    Query query = createSqlQuery(countQlString, params);
    List<Object> list = query.list();
    if (list.size() > 0) {
        page.setTotal(Integer.valueOf(list.get(0).toString()));
    } else {
        page.setTotal(list.size());
    }
    if (page.getTotal() < 1) {
        return page;
    }
    // order by
    String ql = qlString;
    if (StringUtils.isNotBlank(page.getOrderBy())) {
        ql += " order by " + page.getOrderBy();
    }
    Query query1 = createSqlQuery(ql, params);
    // set page
    query1.setFirstResult(page.getFirstResult());
    query1.setMaxResults(page.getLimit());
    setResultTransformer(query1, resultClass);
    page.setDataList(query1.list());
    return page;
}


public int executeSql(String sql,Map<String,Object> params){
    SQLQuery q = createSqlQuery(sql, params);
    return q.executeUpdate();
}


}