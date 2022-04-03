package org.gliderwiki.Interface;
public interface EntityService {

   public int insertEntity(T domain);
   public T getRowEntity(T domain);
}