package com.wxcrm.Interface;
public interface IHibernateDao {

   public Serializable add(Object entity);
   public void flush();
   public T get(Class<T> entityClass,Serializable id);
   public void update(Object entity);
}