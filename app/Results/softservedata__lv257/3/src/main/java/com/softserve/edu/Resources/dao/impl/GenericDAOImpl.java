package com.softserve.edu.Resources.dao.impl;
 import com.softserve.edu.Resources.dao.GenericDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.util;
import java.util.stream.Collectors;
public class GenericDAOImpl implements GenericDAO<T, ID>{

 static  Logger LOGGER;

 private  Logger logger;

@PersistenceContext
 protected  EntityManager em;

 protected  Class<T> entityClass;

public GenericDAOImpl(Class<T> entityClass) {
    this(entityClass, LOGGER);
}protected GenericDAOImpl(Class<T> entityClass, Logger logger) {
    this.entityClass = entityClass;
    this.logger = logger;
}
public String collectParamsString(Map<String,Object> params){
    return params.values().stream().map(Object::toString).collect(Collectors.joining("', '", "'", "'"));
}


@Override
public Optional<T> querySingleResult(String queryWithNamedParams,Map<String,Object> params){
    Optional<T> result = Optional.empty();
    try {
        final TypedQuery<T> query = em.createQuery(queryWithNamedParams, entityClass);
        params.entrySet().forEach(entry -> query.setParameter(entry.getKey(), entry.getValue()));
        result = Optional.ofNullable(query.getSingleResult());
    } catch (NoResultException nre) {
        final String warn = String.format("{} - failed to find %s matching to params [%s]", entityClass.getSimpleName(), collectParamsString(params));
        logger.warn(warn, nre);
    } catch (PersistenceException pe) {
        logger.error("{} {}", pe, pe.getMessage());
    }
    return result;
}


public Optional<T> findReferenceById(ID id){
    try {
        return Optional.ofNullable(em.getReference(entityClass, id));
    } catch (EntityNotFoundException e) {
        return Optional.empty();
    }
}


public void flush(){
    em.flush();
}


@Override
public List<T> queryResultList(String queryWithNamedParams,Map<String,Object> params){
    try {
        final TypedQuery<T> query = em.createQuery(queryWithNamedParams, entityClass);
        params.entrySet().forEach(entry -> query.setParameter(entry.getKey(), entry.getValue()));
        return query.getResultList();
    } catch (PersistenceException pe) {
        logger.error("{} {}", pe, pe.getMessage());
        return Collections.emptyList();
    }
}


public void setEntityManager(EntityManager em){
    this.em = em;
}


public Optional<T> findById(ID id,LockModeType lockModeType){
    return Optional.ofNullable(em.find(entityClass, id, lockModeType));
}


public void makeTransient(T instance){
    em.remove(instance);
}


public void checkVersion(T entity,boolean forceUpdate){
    em.lock(entity, forceUpdate ? LockModeType.OPTIMISTIC_FORCE_INCREMENT : LockModeType.OPTIMISTIC);
}


public T makePersistent(T instance){
    // update() handles transient AND detached instances
    return em.merge(instance);
}


public List<T> findAll(){
    CriteriaQuery<T> c = em.getCriteriaBuilder().createQuery(entityClass);
    c.select(c.from(entityClass));
    return em.createQuery(c).getResultList();
}


public Long getCount(){
    CriteriaQuery<Long> c = em.getCriteriaBuilder().createQuery(Long.class);
    c.select(em.getCriteriaBuilder().count(c.from(entityClass)));
    return em.createQuery(c).getSingleResult();
}


}