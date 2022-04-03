package com.uec.imonitor.es.index;
 import com.uec.imonitor.es.bean.index.IndexConfig;
public interface IEsIndex {


public boolean isExistsIndex(String indexName)
;

public boolean createIndex(IndexConfig iConfig)
;

public boolean isExistsType(String indexName,String indexType)
;

}