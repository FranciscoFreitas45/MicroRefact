package com.wxcrm.Interface;
public interface IHibernateDao {

   public Serializable add(Object entity);
   public T get(Class<T> entityClass,Serializable id);
   public void update(Object entity);
   public List<T> query(String hql,Object[] args,int index,int max);
}