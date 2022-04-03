package com.kingen.service;
 import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import com.google.common.collect.Maps;
import com.kingen.bean.User;
import com.kingen.repository.CommonDao;
import com.kingen.util.BeanUtils;
import com.kingen.util.Page;
import com.kingen.util.Reflections;
import com.kingen.web.workflow.PaginationThreadUtils;
@Service
@Transactional
public class CommonService {

 private  Logger logger;

@Autowired
 private  CommonDao<T,PK> dao;


public Serializable add(T bean){
    return dao.saveWithReturnId(bean);
}


public List<T> getAllList(String tableSimpleName){
    StringBuilder sff = new StringBuilder();
    sff.append("select a from ").append(tableSimpleName).append(" a ");
    List<T> list = dao.createQuery(sff.toString()).list();
    if (list.size() > 0) {
        return list;
    } else {
        return Collections.emptyList();
    }
}


public Serializable addObj(Object bean){
    return dao.saveObjWithReturnId(bean);
}


public void delThem(List<PK> ids,String idName){
    Assert.notEmpty(ids, "ID不应为空");
    for (PK id : ids) {
        dao.delete(id, idName);
    }
}


public void updateNotNull(Serializable pk,T bean){
    updateNotNull(pk, bean);
}


@Deprecated
public T one(String entityName,Map<String,Object> params){
    return dao.oneByEntity(entityName, params);
}


public void update(T bean){
    dao.update(bean);
}


public List<X> list(Class<X> clazz,Map<String,Object> params){
    List<X> list = dao.findByEntity(clazz.getSimpleName(), params);
    if (!CollectionUtils.isEmpty(list)) {
        return list;
    } else {
        return Collections.emptyList();
    }
}


public List<T> findByPage(String tableSimpleName,String[] columns,String[] values){
    Integer totalSum = 0;
    List<T> list = new ArrayList<T>();
    if (columns.length <= 0 && values.length <= 0) {
        list = getAllList(tableSimpleName);
    } else {
        list = findByWhere(tableSimpleName, columns, values);
    }
    if (!CollectionUtils.isEmpty(list)) {
        totalSum = list.size();
    }
    int[] pageParams = PaginationThreadUtils.setPage(totalSum);
    StringBuilder sb = new StringBuilder();
    sb.append("select a from ").append(tableSimpleName).append(" a where ");
    if (columns.length == values.length) {
        for (int i = 0; i < columns.length; i++) {
            sb.append("a.").append(columns[i]).append("='").append(values[i]).append("'");
            if (i < columns.length - 1) {
                sb.append(" and ");
            }
        }
        String hql = sb.toString();
        if (hql.endsWith("where ")) {
            hql = hql.substring(0, hql.length() - 6);
        }
        logger.info("findByPage: HQL: " + hql);
        list = dao.find1(hql, pageParams[0], pageParams[1]);
        // list = dao.findByPage(hql, pageParams[0], pageParams[1]);
        if (list.size() > 0) {
            return list;
        } else {
            return Collections.emptyList();
        }
    } else {
        logger.info("findByPage: columns.length != values.length");
        return Collections.emptyList();
    }
}


public void delete(T bean){
    dao.delete(bean);
}


public void delThemEntity(String entityName,String idName,List<PK> ids){
    Assert.hasText(entityName, "entityName不应为空");
    Assert.hasText(idName, "idName不应为空");
    Assert.notEmpty(ids, "ID不应为空");
    for (PK id : ids) {
        dao.deleteEntity(entityName, idName, id);
    }
}


public Page<X> find(Page<X> page,Class<X> clazz){
    return dao.findByEntity(page, clazz.getSimpleName(), null);
}


public T unique(Serializable id){
    return dao.unique(id);
}


public T getUnique(String tableSimpleName,String[] columns,String[] values){
    StringBuilder sb = new StringBuilder();
    sb.append("select a from ").append(tableSimpleName).append(" a where ");
    if (columns.length == values.length) {
        for (int i = 0; i < columns.length; i++) {
            sb.append("a.").append(columns[i]).append("='").append(values[i]).append("'");
            if (i < columns.length - 1) {
                sb.append(" and ");
            }
        }
        T entity = dao.unique(sb.toString());
        return entity;
    } else {
        logger.error("columns.length != values.length");
        return null;
    }
}


public List<T> findByWhere(String tableSimpleName,String[] columns,String[] values){
    StringBuilder sb = new StringBuilder();
    sb.append("select a from ").append(tableSimpleName).append(" a where ");
    if (columns.length == values.length) {
        for (int i = 0; i < columns.length; i++) {
            sb.append("a.").append(columns[i]).append("='").append(values[i]).append("'");
            if (i < columns.length - 1) {
                sb.append(" and ");
            }
        }
        List<T> list = dao.createQuery(sb.toString()).list();
        // 最好用JDK提供的Collections.emptyList()来返回一个空的集合对象 而不是 null
        // Collections.EMPTY_LIST 是返回一个空集合对象，emptyList()是对EMPTY_LIST做了一个泛型支持，具体看源码
        // Collections.EMPTY_LIST的返回值是一个不可变的空List，不能进行例如add的各种操作！
        if (list.size() > 0) {
            return list;
        } else {
            return Collections.emptyList();
        }
    } else {
        return Collections.emptyList();
    }
}


public T getBean(Class<T> obj,Serializable id){
    return dao.getById(obj, id);
}


public List<T> getCount(String tableSimpleName){
    String hql = "select count(*) from " + tableSimpleName;
    List<T> list = dao.createQuery(hql).list();
    return list;
}


public List<E> getEntityBy(String beanClazz,String property,Object val,Object rawValue,String action){
    if (StringUtils.isEmpty(action) || "insert".equals(action)) {
        // 新增
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(1);
        params.put(property, val);
        return dao.findByEntity(beanClazz, params);
    } else if ("edit".equals(action) || "update".equals(action)) {
        return dao.findExcept(beanClazz, property, val, rawValue);
    }
    return null;
}


public void saveOrUpdate(T bean){
    dao.saveOrUpdate(bean);
}


public X uniqueEntity(Class<X> clazz,String idName,PK id){
    return dao.uniqueEntity(clazz.getSimpleName(), idName, id);
}


}