package com.ushahidi.swiftriver.core.api.dao.impl;
 import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.ushahidi.swiftriver.core.api.dao.GenericDao;
public class AbstractJpaDao implements GenericDao<T>{

@PersistenceContext
 protected  EntityManager em;

 private  Class<T> type;

@SuppressWarnings({ "unchecked", "rawtypes" })
public AbstractJpaDao() {
    Type t = getClass().getGenericSuperclass();
    ParameterizedType pt = (ParameterizedType) t;
    type = (Class) pt.getActualTypeArguments()[0];
}
public EntityManager getEm(){
    return em;
}


@Override
public T findById(Object id){
    return (T) em.find(type, id);
}


@Override
public T create(T t){
    em.persist(t);
    return t;
}


@Override
public T update(T t){
    return em.merge(t);
}


@Override
public void delete(T t){
    em.remove(t);
}


public void setEm(EntityManager em){
    this.em = em;
}


}