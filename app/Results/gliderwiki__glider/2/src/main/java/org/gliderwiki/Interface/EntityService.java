package org.gliderwiki.Interface;
public interface EntityService {

   public List<T> getListEntityOrdered(T domain,String orderQuery);
   public int insertEntity(T domain);
   public T getRowEntity(T domain);
   public int updateEntity(T domain);
}