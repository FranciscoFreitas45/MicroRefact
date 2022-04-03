package org.sdrc.Interface;
public interface DashboardService {

   public List<UtDataModel> fetchPdfData(String indicatorId,String sourceId,String areaId,String timePeriodId,Integer childLevel);
}