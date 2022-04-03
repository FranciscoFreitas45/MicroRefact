package org.gliderwiki.Interface;
public interface EntityService {

   public T getRowEntity(T domain);
   public int insertEntity(T domain);
   public int getCountEntity(T domain);
   public List<T> getListEntityOrdered(T domain,String orderQuery);
   public int deleteEntity(T domain);
}