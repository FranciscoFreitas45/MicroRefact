package com.uec.imonitor.Interface;
public interface INewsStatusService {

   public List<NewsStatusEntity> listTopNInsertRecordsByTableName(String tableName,int topN);
   public Boolean deleteEsStatusList(List<NewsStatusEntity> esStatusList);
   public List<NewsStatusEntity> listTopNDeletedRecordsByTableName(String tableName,int topN);
   public List<NewsStatusEntity> listTopNUpdateRecordsByTableName(String tableName,int topN);
}