package com.uec.imonitor.es.index;
 import java.util.List;
import java.util.Map;
import com.uec.imonitor.common.base.BaseEntity;
public interface IDataIndex {


public boolean add(String indexName,String indexType,T entity)
;

public boolean bulkAdd(String indexName,String indexType,List<T> entityList)
;

public boolean bulkDelete(String indexName,String indexType,List<String> primaryKeyList)
;

public boolean update(String indexName,String indexType,String primaryKey,Map<String,String> updateMap)
;

public boolean bulkUpdate(String indexName,String indexType,List<T> entityList)
;

public boolean delete(String indexName,String indexType,String primaryKey)
;

}