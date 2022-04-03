package com.uec.imonitor.Interface;
public interface IDataIndex {

   public boolean bulkUpdate(String indexName,String indexType,List<T> entityList);
   public boolean delete(String indexName,String indexType,String primaryKey);
}