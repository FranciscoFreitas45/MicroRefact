package org.gliderwiki.Interface;
public interface EntityService {

   public int insertEntity(T domain);
   public T getRowEntity(T domain);
   public int updateEntity(T domain);
   public int getCountEntity(T domain);
}