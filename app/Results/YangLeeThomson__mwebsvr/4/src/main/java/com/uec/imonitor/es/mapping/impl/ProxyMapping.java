package com.uec.imonitor.es.mapping.impl;
 import org.elasticsearch.common.xcontent.XContentBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.uec.imonitor.es.bean.index.IndexConfig;
import com.uec.imonitor.es.mapping.IDataMapping;
@Service("proxyMapping")
public class ProxyMapping implements IDataMapping{

 private  Logger logger;

@Autowired
@Qualifier("newsSpreadingMapping")
 private  IDataMapping newsSpreadingMapping;

@Autowired
@Qualifier("requestNewsMapping")
 private  IDataMapping requestNewsMapping;

@Value("${imonitor.es.index.request.news.type}")
 private  String requestNewsType;

@Value("${imonitor.es.index.spreading.type}")
 private  String spreadingType;


@Override
public XContentBuilder mappingConfig(IndexConfig iConfig){
    XContentBuilder builder = null;
    if (iConfig == null) {
        logger.error("映射对象参数IndexConfig为空");
    } else if (iConfig.getTypeName() == null) {
        logger.error("映射对象参数IndexConfig中索引对象名称typeName为空");
    } else {
        if (requestNewsType.equals(iConfig.getTypeName())) {
            return requestNewsMapping.mappingConfig(iConfig);
        } else if (spreadingType.equals(iConfig.getTypeName())) {
            return newsSpreadingMapping.mappingConfig(iConfig);
        }
    }
    return builder;
}


}