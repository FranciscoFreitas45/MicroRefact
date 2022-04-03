package com.uec.imonitor.es.index.impl;
 import java.io.IOException;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.admin.indices.exists.types.TypesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.types.TypesExistsResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.uec.imonitor.es.bean.EsClient;
import com.uec.imonitor.es.bean.index.IndexConfig;
import com.uec.imonitor.es.index.IEsIndex;
import com.uec.imonitor.es.mapping.IDataMapping;
@Service("esIndex")
public class EsIndexImpl implements IEsIndex{

 private  Logger logger;

@Autowired
 private  EsClient esClient;

@Autowired
@Qualifier("proxyMapping")
 private  IDataMapping proxyMapping;


@Override
public boolean isExistsIndex(String indexName){
    if (indexName == null) {
        logger.error("索引库名称参数为空");
        return false;
    } else {
        // Client client = ESUtil.getESClient();
        Client client = esClient.getTransportClient();
        IndicesExistsResponse response = client.admin().indices().exists(new IndicesExistsRequest().indices(new String[] { indexName })).actionGet();
        boolean flag = response.isExists();
        logger.info("es集群中索引名称为" + indexName + "的index是否存在：" + flag);
        return flag;
    }
}


public boolean doIndexMapping(IndexConfig iConfig){
    XContentBuilder indexBuilder = null;
    Client client = esClient.getTransportClient();
    try {
        indexBuilder = XContentFactory.jsonBuilder().startObject().startObject("settings").field("number_of_shards", // 设置分片数量
        iConfig.getShardsNum()).field("number_of_replicas", // 设置副本数量
        iConfig.getReplicasNum()).endObject().endObject();
    } catch (IOException e) {
        logger.error("es索引构建时，json转换异常：" + e);
    }
    if (indexBuilder == null) {
        return false;
    } else {
        CreateIndexRequestBuilder cirb = // index名称
        client.admin().indices().prepareCreate(iConfig.getIndexName()).setSource(indexBuilder);
        XContentBuilder mappingBulider = proxyMapping.mappingConfig(iConfig);
        // mapping设置
        cirb.addMapping(iConfig.getTypeName(), mappingBulider);
        CreateIndexResponse response = cirb.execute().actionGet();
        if (response.isAcknowledged()) {
            logger.info("名为" + iConfig.getIndexName() + "的索引创建成功");
            System.out.println("名为" + iConfig.getIndexName() + "的索引创建成功！");
            return true;
        } else {
            logger.info("名为" + iConfig.getIndexName() + "的索引创建失败");
            System.err.println("名为" + iConfig.getIndexName() + "的索引创建失败！");
            return false;
        }
    }
}


@Override
public boolean createIndex(IndexConfig iConfig){
    if (iConfig == null) {
        logger.error("索引配置参数为空");
        return false;
    } else if (iConfig.getIndexName() == null) {
        logger.error("索引名称参数为空");
        return false;
    } else if (iConfig.getTypeName() == null) {
        logger.error("索引type参数为空");
        return false;
    } else {
        String indexName = iConfig.getIndexName();
        String typeName = iConfig.getTypeName();
        if (this.isExistsIndex(indexName)) {
            logger.info("索引名称为" + indexName + "的索引已存在");
            if (this.isExistsType(indexName, typeName)) {
                logger.info("名称为" + indexName + "，类型名为" + typeName + "的索引已存在");
                return true;
            } else {
                return doIndexMapping(iConfig);
            }
        } else {
            return doIndexMapping(iConfig);
        }
    }
}


@Override
public boolean isExistsType(String indexName,String indexType){
    if (indexName == null) {
        logger.error("索引库名称参数为空");
        return false;
    } else if (indexType == null) {
        logger.error("索引库type参数为空");
        return false;
    } else {
        // Client client = ESUtil.getESClient();
        Client client = esClient.getTransportClient();
        TypesExistsResponse response = client.admin().indices().typesExists(new TypesExistsRequest(new String[] { indexName }, indexType)).actionGet();
        boolean flag = response.isExists();
        logger.info("es集群中索引名称为" + indexName + "，type类型为" + indexType + "的index是否存在：" + flag);
        return flag;
    }
}


}