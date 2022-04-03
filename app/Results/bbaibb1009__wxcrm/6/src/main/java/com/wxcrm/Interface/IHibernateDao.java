package com.wxcrm.Interface;
public interface IHibernateDao {

   public Serializable add(Object entity);
   public void update(Object entity);
   public T get(Class<T> entityClass,Serializable id);
}