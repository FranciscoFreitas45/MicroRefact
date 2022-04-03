package com.uec.imonitor.news.service;
 import java.util.List;
import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.common.exception.RequestParamException;
import com.uec.imonitor.es.bean.result.BaseQueryResult;
import com.uec.imonitor.news.bean.NewsSpreadingAnalysisDetail;
import com.uec.imonitor.news.bean.NewsSpreadingAnalysisEntity;
import com.uec.imonitor.news.bean.QueryNewsParam;
public interface INewsSpreadingAnalysisService {


public List<NewsSpreadingAnalysisEntity> findByNewsId(Integer newsId)
;

public void deletedSpreadingAnalysisIndexJob(int do_num)
;

public List<NewsSpreadingAnalysisEntity> findByWebpageCode(String webpageCode)
;

public NewsSpreadingAnalysisDetail findDetailByNewsSpreadingAnalysisEntity(NewsSpreadingAnalysisEntity entity)
;

public List<NewsSpreadingAnalysisEntity> findByNewsIdAndNotMarked(Integer newsId)
;

public void updateSpreadingAnalysisIndexJob(int do_num)
;

public void insertAndUpdateIndexFailureJob()
;

public List<NewsSpreadingAnalysisDetail> listNewsSpreadingAnalysisDetailByIds(List<Integer> idList)
;

public BaseQueryResult<NewsSpreadingAnalysisDetail> countByDate(QueryNewsParam param)
;

public NewsSpreadingAnalysisEntity findByNewsIdAndCode(Integer newsId,String webpageCode)
;

public BaseQueryResult<NewsSpreadingAnalysisDetail> pageReprintNews(QueryNewsParam param)
;

public void insertSpreadingAnalysisIndexJob(int do_num)
;

}