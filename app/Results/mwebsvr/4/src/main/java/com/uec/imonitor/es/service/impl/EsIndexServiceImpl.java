package com.uec.imonitor.es.service.impl;
 import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.common.util.CommonUtil;
import com.uec.imonitor.es.bean.index.IndexConfig;
import com.uec.imonitor.es.index.IDataIndex;
import com.uec.imonitor.es.index.IEsIndex;
import com.uec.imonitor.es.service.IEsIndexService;
import com.uec.imonitor.news.bean.NewsSpreadingAnalysisDetail;
import com.uec.imonitor.news.service.INewsSpreadingAnalysisService;
import com.uec.imonitor.request.bean.RequestNewsDetail;
import com.uec.imonitor.request.service.IRequestNewsService;
import com.uec.imonitor.Interface.IRequestNewsService;
import com.uec.imonitor.Interface.INewsSpreadingAnalysisService;
@Service("esIndexService")
@Transactional(value = "transactionManager")
public class EsIndexServiceImpl implements IEsIndexService{

 private Log logger;

@Autowired
 private  IEsIndex esIndex;

@Autowired
 private  IRequestNewsService requestNewsService;

@Autowired
 private  INewsSpreadingAnalysisService newsSpreadingAnalysisService;

@Autowired
 private  IDataIndex dataIndex;

@Value("${imonitor.es.index.request.news.name}")
 private  String requestNewsName;

@Value("${imonitor.es.index.spreading.name}")
 private  String newsSpreadingName;

@Value("${imonitor.es.index.request.news.type}")
 private  String requestNewsType;

@Value("${imonitor.es.index.spreading.type}")
 private  String newsSpreadingType;

@Value("${imonitor.es.cluster.shards.num}")
 private  int shardsNum;

@Value("${imonitor.es.cluster.replicas.num}")
 private  int replicasNum;


@Override
public boolean initAllNewsSpreading(){
    // Integer max = 3346677;
    // Integer min = 163768;
    Integer max = 150000;
    Integer min = 0;
    List<Integer> newsList = new ArrayList<>();
    for (Integer i = max; i >= min; i--) {
        newsList.add(i);
    }
    // 过滤重复的记录，过滤update中被删除的记录
    int num = 500;
    int t = (newsList.size() - 1) / num + 1;
    for (int i = 0; i < t; i++) {
        List<Integer> statusList = new ArrayList<>();
        if ((i + 1) * num < newsList.size()) {
            statusList = newsList.subList(i * num, (i + 1) * num);
        } else {
            statusList = newsList.subList(i * num, newsList.size());
        }
        try {
            // 新闻需求详情索引
            List<NewsSpreadingAnalysisDetail> newsSpreadingAnalysisList = newsSpreadingAnalysisService.listNewsSpreadingAnalysisDetailByIds(statusList);
            dataIndex.bulkUpdate(newsSpreadingName, newsSpreadingType, newsSpreadingAnalysisList);
            logger.info(CommonUtil.toJson(statusList));
        } catch (Exception e) {
            Date d = new Date();
            logger.error("执行插入需求新闻内容失败" + ", 时间=" + d + ", webpageIds =" + CommonUtil.toJson(statusList) + "," + e);
            return false;
        }
    // try {
    // Thread.sleep(1000);
    // } catch (InterruptedException e) {
    // }
    }
    return true;
}


@Override
public void indexRequsetNewsSetting(){
    IndexConfig iConfig = new IndexConfig();
    iConfig.setIndexName(requestNewsName);
    iConfig.setTypeName(requestNewsType);
    iConfig.setShardsNum(shardsNum);
    iConfig.setReplicasNum(replicasNum);
    esIndex.createIndex(iConfig);
}


@Override
public void indexNewsSpreadingSetting(){
    IndexConfig iConfig = new IndexConfig();
    iConfig.setIndexName(newsSpreadingName);
    iConfig.setTypeName(newsSpreadingType);
    iConfig.setShardsNum(shardsNum);
    iConfig.setReplicasNum(replicasNum);
    esIndex.createIndex(iConfig);
}


@Override
public void setIndexAliase(String originalName,String aliaseName){
// TODO Auto-generated method stub
}


@Override
public void EsIndexSetting(){
    this.indexNewsSpreadingSetting();
    this.indexRequsetNewsSetting();
}


@Override
public boolean initAllRequestNews(){
    // Integer max = 3346677;
    // Integer min = 163768;
    Integer max = 150000;
    Integer min = 0;
    List<Integer> newsList = new ArrayList<>();
    for (Integer i = max; i >= min; i--) {
        newsList.add(i);
    }
    // 过滤重复的记录，过滤update中被删除的记录
    int num = 500;
    int t = (newsList.size() - 1) / num + 1;
    for (int i = 0; i < t; i++) {
        List<Integer> statusList = new ArrayList<>();
        if ((i + 1) * num < newsList.size()) {
            statusList = newsList.subList(i * num, (i + 1) * num);
        } else {
            statusList = newsList.subList(i * num, newsList.size());
        }
        try {
            // 新闻需求详情索引
            List<RequestNewsDetail> RequestNewsDetailList = requestNewsService.listDetailByIds(statusList);
            dataIndex.bulkUpdate(requestNewsName, requestNewsType, RequestNewsDetailList);
            logger.info(CommonUtil.toJson(statusList));
        } catch (Exception e) {
            Date d = new Date();
            logger.error("执行插入需求新闻内容失败" + ", 时间=" + d + ", webpageIds =" + CommonUtil.toJson(statusList) + "," + e);
            return false;
        }
    // try {
    // Thread.sleep(1000);
    // } catch (InterruptedException e) {
    // }
    }
    return true;
}


}