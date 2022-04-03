package com.uec.imonitor.es.service;
 import java.util.List;
import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.common.exception.RequestParamException;
import com.uec.imonitor.es.bean.NewsStatusEntity;
public interface INewsStatusService {


public NewsStatusEntity add(NewsStatusEntity entity)
;

public List<NewsStatusEntity> listTopInsertAndUpdateByTable(String tableName,int topN)
;

public List<NewsStatusEntity> listInsertRecordsByTableName(String tableName)
;

public Boolean deleteEsStatus(int innerid)
;

public List<NewsStatusEntity> listTopNInsertRecordsByTableName(String tableName,int topN)
;

public NewsStatusEntity addOrUpdate(NewsStatusEntity entity)
;

public List<NewsStatusEntity> listTopNUpdateRecordsByTableName(String tableName,int topN)
;

public Boolean deleteEsStatusList(List<NewsStatusEntity> esStatusList)
;

public List<NewsStatusEntity> listByTableName(String tableName,int topN)
;

public List<NewsStatusEntity> listUpdateRecordsByTableName(String tableName)
;

public List<NewsStatusEntity> listTopNDeletedRecordsByTableName(String tableName,int topN)
;

}