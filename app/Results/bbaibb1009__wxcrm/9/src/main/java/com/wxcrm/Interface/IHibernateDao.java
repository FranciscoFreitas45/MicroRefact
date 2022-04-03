package com.wxcrm.Interface;
public interface IHibernateDao {

   public List<T> query(String hql,Object[] args,int index,int max);
   public Serializable add(Object entity);
   public T get(Class<T> entityClass,Serializable id);
   public void update(Object entity);
}