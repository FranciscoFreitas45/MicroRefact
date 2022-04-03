package org.sdrc.Interface;
public interface UtDataRepository {

   public UtAreaEn[] getAreaNid(String areaId,Integer childLevel);
   public List<Object[]> findDataForCompositeIndex(Integer iusNId,Integer timePeriodNid,Integer[] areaNid);
}