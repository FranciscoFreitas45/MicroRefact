package org.sdrc.Interface;
public interface UtAreaEnRepository {

   public List<UtAreaEn> findAll();
   public List<UtAreaEn> findByAreaShortName(String string);
}