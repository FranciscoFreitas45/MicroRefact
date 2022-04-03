package org.gliderwiki.Interface;
public interface EntityService {

   public int updateEntity(T domain);
   public int insertEntity(T domain);
}