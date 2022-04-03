package com.uec.imonitor.es.service.impl;
 import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.common.exception.RequestParamException;
import com.uec.imonitor.es.bean.NewsStatusEntity;
import com.uec.imonitor.es.dao.INewsStatusJPARepository;
import com.uec.imonitor.es.service.INewsStatusService;
@Service("newsStatusService")
@Transactional(value = "transactionManager")
public class NewsStatusServiceImpl implements INewsStatusService{

@Autowired
 private  INewsStatusJPARepository newsStatusJPARepository;


@Override
public NewsStatusEntity add(NewsStatusEntity entity){
    if (null == entity) {
        throw new RequestParamException(new String[] { "entity" });
    }
    NewsStatusEntity statusEntity = newsStatusJPARepository.save(entity);
    return statusEntity;
}


@Override
public List<NewsStatusEntity> listTopInsertAndUpdateByTable(String tableName,int topN){
    if (null == tableName) {
        throw new RequestParamException(new String[] { "tableName" });
    }
    if (topN < 0) {
        throw new RequestParamException(new String[] { "topN" });
    }
    List<Integer> statusList = new ArrayList<>();
    statusList.add(0);
    statusList.add(2);
    List<NewsStatusEntity> esStatusList = newsStatusJPARepository.listByTableNameAndStatusTopN(tableName, statusList, topN);
    return esStatusList;
}


@Override
public List<NewsStatusEntity> listInsertRecordsByTableName(String tableName){
    if (null == tableName) {
        throw new RequestParamException(new String[] { "tableName" });
    }
    List<NewsStatusEntity> esStatusList = newsStatusJPARepository.listByTableNameAndStatus(tableName, 0);
    return esStatusList;
}


@Override
public Boolean deleteEsStatus(int innerid){
    newsStatusJPARepository.delete(innerid);
    return true;
}


@Override
public List<NewsStatusEntity> listTopNInsertRecordsByTableName(String tableName,int topN){
    if (null == tableName) {
        throw new RequestParamException(new String[] { "tableName" });
    }
    if (topN < 0) {
        throw new RequestParamException(new String[] { "topN" });
    }
    List<Integer> statusList = new ArrayList<>();
    statusList.add(0);
    List<NewsStatusEntity> esStatusList = newsStatusJPARepository.listByTableNameAndStatusTopN(tableName, statusList, topN);
    return esStatusList;
}


@Override
public NewsStatusEntity addOrUpdate(NewsStatusEntity entity){
    if (null == entity) {
        throw new RequestParamException(new String[] { "entity" });
    }
    List<NewsStatusEntity> tempList = newsStatusJPARepository.listByTableNameAndStatusAndWebpageCode(entity.getTableName(), entity.getStatus(), entity.getWebpageCode());
    NewsStatusEntity statusEntity = new NewsStatusEntity();
    if (CollectionUtils.isEmpty(tempList)) {
        statusEntity = newsStatusJPARepository.save(entity);
    } else {
        statusEntity = tempList.get(0);
        statusEntity.setLastModifyTime(entity.getLastModifyTime());
        statusEntity.setRecordId(entity.getRecordId());
        statusEntity.setWebpageCode(entity.getWebpageCode());
        statusEntity = newsStatusJPARepository.save(statusEntity);
    }
    return statusEntity;
}


@Override
public List<NewsStatusEntity> listTopNUpdateRecordsByTableName(String tableName,int topN){
    if (null == tableName) {
        throw new RequestParamException(new String[] { "tableName" });
    }
    if (topN < 0) {
        throw new RequestParamException(new String[] { "topN" });
    }
    List<Integer> statusList = new ArrayList<>();
    statusList.add(2);
    List<NewsStatusEntity> esStatusList = newsStatusJPARepository.listByTableNameAndStatusTopN(tableName, statusList, topN);
    return esStatusList;
}


@Override
public Boolean deleteEsStatusList(List<NewsStatusEntity> esStatusList){
    if (null == esStatusList) {
        throw new RequestParamException(new String[] { "esStatusList" });
    }
    newsStatusJPARepository.deleteInBatch(esStatusList);
    return true;
}


@Override
public List<NewsStatusEntity> listByTableName(String tableName,int topN){
    if (null == tableName) {
        throw new RequestParamException(new String[] { "tableName" });
    }
    if (topN < 0) {
        throw new RequestParamException(new String[] { "topN" });
    }
    List<NewsStatusEntity> esStatusList = newsStatusJPARepository.listByTableNameTopN(tableName, topN);
    return esStatusList;
}


@Override
public List<NewsStatusEntity> listUpdateRecordsByTableName(String tableName){
    if (null == tableName) {
        throw new RequestParamException(new String[] { "tableName" });
    }
    List<NewsStatusEntity> esStatusList = newsStatusJPARepository.listByTableNameAndStatus(tableName, 2);
    return esStatusList;
}


@Override
public List<NewsStatusEntity> listTopNDeletedRecordsByTableName(String tableName,int topN){
    if (null == tableName) {
        throw new RequestParamException(new String[] { "tableName" });
    }
    if (topN < 0) {
        throw new RequestParamException(new String[] { "topN" });
    }
    List<Integer> statusList = new ArrayList<>();
    statusList.add(1);
    List<NewsStatusEntity> esStatusList = newsStatusJPARepository.listByTableNameAndStatusTopN(tableName, statusList, topN);
    return esStatusList;
}


}