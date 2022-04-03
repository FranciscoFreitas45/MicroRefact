package com.uec.imonitor.es.mapping;
 import org.elasticsearch.common.xcontent.XContentBuilder;
import com.uec.imonitor.es.bean.index.IndexConfig;
public interface IDataMapping {


public XContentBuilder mappingConfig(IndexConfig iConfig)
;

}