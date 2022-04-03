package org.sdrc.Interface;
public interface SourceRepository {

   public List<UtIndicatorClassificationsEn> findByIUS_Nid(Integer iusNid);
   public List<UtIndicatorClassificationsEn> findByIUSandLevel_Nid(Integer iusNid,Integer levelNid);
}