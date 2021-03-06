package com.uec.imonitor.es.index.impl;
 import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequestBuilder;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateRequestBuilder;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.uec.imonitor.common.base.BaseEntity;
import com.uec.imonitor.common.util.CommonUtil;
import com.uec.imonitor.es.bean.EsClient;
import com.uec.imonitor.es.index.IDataIndex;
import com.uec.imonitor.DTO.BaseEntity;
@Service("dataIndex")
public class DataIndexImpl implements IDataIndex{

 private  Logger logger;

@Autowired
 private  EsClient esClient;


@Override
public boolean add(String indexName,String indexType,BaseEntity entity){
    Client client = esClient.getTransportClient();
    if (null != client && null != entity) {
        String webpageJson = CommonUtil.toJson(entity);
        IndexResponse indexResponse = client.prepareIndex(indexName, indexType, entity.getEsPrimaryId()).setSource(webpageJson).get();
        return indexResponse.isCreated();
    } else {
        return false;
    }
}


@SuppressWarnings("hiding")
@Override
public boolean bulkAdd(String indexName,String indexType,List<T> entityList){
    Client client = esClient.getTransportClient();
    if (null != client && !CollectionUtils.isEmpty(entityList)) {
        BulkRequestBuilder bulkRequest = client.prepareBulk();
        // ???????????????????????????
        for (BaseEntity hn : entityList) {
            String jsonlist = CommonUtil.toJson(hn);
            IndexRequestBuilder indexRequestBuilder = client.prepareIndex(indexName, indexType, hn.getEsPrimaryId()).setSource(jsonlist);
            bulkRequest.add(indexRequestBuilder);
        }
        // either use client#prepare, or use Requests# to directly build
        // index/delete requests
        BulkResponse bulkResponse = bulkRequest.get();
        if (bulkResponse.hasFailures()) {
            logger.error("????????????????????????" + bulkResponse.buildFailureMessage());
            return false;
        } else {
            return true;
        }
    }
    return false;
}


@Override
public boolean bulkDelete(String indexName,String indexType,List<String> primaryKeyList){
    Client client = esClient.getTransportClient();
    if (null != client && !CollectionUtils.isEmpty(primaryKeyList)) {
        BulkRequestBuilder bulkRequest = client.prepareBulk();
        // ???????????????????????????
        for (String hn : primaryKeyList) {
            DeleteRequestBuilder deleteRequestBuilder = client.prepareDelete(indexName, indexType, hn);
            bulkRequest.add(deleteRequestBuilder);
        }
        // either use client#prepare, or use Requests# to directly build
        // index/delete requests
        BulkResponse bulkResponse = bulkRequest.get();
        if (bulkResponse.hasFailures()) {
            logger.error("????????????????????????" + bulkResponse.buildFailureMessage());
            return false;
        } else {
            return true;
        }
    }
    return false;
}


@Override
public boolean update(String indexName,String indexType,String primaryKey,Map<String,String> updateMap){
    Client client = esClient.getTransportClient();
    if (null != client && null != updateMap && !updateMap.isEmpty()) {
        String webpageJson = CommonUtil.toJson(updateMap);
        try {
            IndexRequest indexResponse = new IndexRequest(indexName, indexType, primaryKey).source(webpageJson);
            UpdateRequest updateRequest = new UpdateRequest(indexName, indexType, primaryKey).doc(webpageJson).upsert(indexResponse);
            @SuppressWarnings("unused")
            UpdateResponse updateResponse = client.update(updateRequest).get();
            logger.info("?????????????????????primaryKey = " + primaryKey);
            return true;
        } catch (Exception e) {
            // ?????????????????????add????????????
            logger.error("?????????????????????primaryKey =" + primaryKey, e);
            return false;
        }
    } else {
        return false;
    }
}


@SuppressWarnings("hiding")
@Override
public boolean bulkUpdate(String indexName,String indexType,List<T> entityList){
    Client client = esClient.getTransportClient();
    if (null != client && !CollectionUtils.isEmpty(entityList)) {
        BulkRequestBuilder bulkRequest = client.prepareBulk();
        // ???????????????????????????
        for (BaseEntity hn : entityList) {
            // String webpageJson = mapper.writeValueAsString(hn);
            String webpageJson = CommonUtil.toJson(hn);
            IndexRequest indexRequest = new IndexRequest(indexName, indexType, hn.getEsPrimaryId()).source(webpageJson);
            UpdateRequestBuilder updateRequestBuilder = client.prepareUpdate(indexName, indexType, hn.getEsPrimaryId()).setDoc(webpageJson).setUpsert(indexRequest);
            bulkRequest.add(updateRequestBuilder);
        }
        // either use client#prepare, or use Requests# to directly build
        // index/delete requests
        BulkResponse bulkResponse = bulkRequest.get();
        if (bulkResponse.hasFailures()) {
            logger.error("????????????????????????" + bulkResponse.buildFailureMessage());
            return false;
        } else {
            logger.info("??????????????????");
            return true;
        }
    }
    return false;
}


@Override
public boolean delete(String indexName,String indexType,String primaryKey){
    Client client = esClient.getTransportClient();
    if (null != client && StringUtils.isNoneBlank(primaryKey)) {
        DeleteResponse deleteResponse = client.prepareDelete(indexName, indexType, primaryKey).get();
        return deleteResponse.isFound();
    } else {
        return false;
    }
}


}