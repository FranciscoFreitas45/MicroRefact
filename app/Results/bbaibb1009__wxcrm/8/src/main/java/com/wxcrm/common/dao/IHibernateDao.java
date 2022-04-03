package com.wxcrm.common.dao;
 import java.io.Serializable;
import java.util.List;
public interface IHibernateDao {


public Serializable add(Object entity)
;

public void flush()
;

public T get(Class<T> entityClass,Serializable id)
;

public List<T> query(String hql,Object[] args,int index,int max)
;

public void clear()
;

public void update(Object entity)
;

public int bulkUpdate(String hql,Object[] args)
;

public void delete(Object entity)
;

public void evict(Object entity)
;

}