package com.uec.imonitor.request.service;
 import java.util.List;
import com.uec.imonitor.common.base.PageResponse;
import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.common.exception.RequestParamException;
import com.uec.imonitor.es.bean.result.BaseQueryResult;
import com.uec.imonitor.news.bean.QueryNewsParam;
import com.uec.imonitor.request.bean.RequestNewsDetail;
import com.uec.imonitor.request.bean.RequestNewsEntity;
import com.uec.imonitor.request.bean.RequestNewsShow;
public interface IRequestNewsService {


public RequestNewsEntity add(RequestNewsEntity entity)
;

public RequestNewsEntity findByWebpageCode(String webpageCode)
;

public RequestNewsEntity update(RequestNewsEntity entity)
;

public List<RequestNewsEntity> findByRequestId(Integer requestId)
;

public RequestNewsDetail findDetailByRequestNewsEntity(RequestNewsEntity entity)
;

public BaseQueryResult<RequestNewsShow> pageRequestNewsEs(QueryNewsParam param)
;

public RequestNewsEntity saveRequestNews(RequestNewsEntity entity)
;

public boolean delete(Integer id)
;

public void insertRequestNewsIndexJob(int do_num)
;

public void deleteRequestNewsJob(int do_num)
;

public List<RequestNewsDetail> listDetailByIds(List<Integer> idList)
;

public void insertAndUpdateIndexFailureJob()
;

public RequestNewsEntity findById(int id)
;

public PageResponse<RequestNewsShow> pageRequestNews(QueryNewsParam param)
;

public void updateRequestNewsIndexJob(int do_num)
;

}