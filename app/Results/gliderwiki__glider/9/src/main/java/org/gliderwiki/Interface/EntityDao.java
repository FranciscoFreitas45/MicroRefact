package org.gliderwiki.Interface;
public interface EntityDao {

   public T getRowEntity(T domain);
   public int updateEntity(T domain);
}