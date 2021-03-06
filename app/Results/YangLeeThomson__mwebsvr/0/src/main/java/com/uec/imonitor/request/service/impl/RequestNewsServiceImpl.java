package com.uec.imonitor.request.service.impl;
 import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import com.uec.imonitor.common.base.BaseService;
import com.uec.imonitor.common.base.PageResponse;
import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.common.exception.RequestParamException;
import com.uec.imonitor.common.util.CommonUtil;
import com.uec.imonitor.common.util.ConstantUtil;
import com.uec.imonitor.common.util.ESConstantUtil;
import com.uec.imonitor.common.util.ESUtil;
import com.uec.imonitor.es.bean.NewsStatusEntity;
import com.uec.imonitor.es.bean.params.MatchParams;
import com.uec.imonitor.es.bean.params.QueryParams;
import com.uec.imonitor.es.bean.params.RangeParams;
import com.uec.imonitor.es.bean.params.SortParams;
import com.uec.imonitor.es.bean.result.BaseQueryResult;
import com.uec.imonitor.es.index.IDataIndex;
import com.uec.imonitor.es.search.IDataSearch;
import com.uec.imonitor.es.service.INewsStatusService;
import com.uec.imonitor.news.bean.QueryNewsParam;
import com.uec.imonitor.news.utils.TxtUtil;
import com.uec.imonitor.request.bean.RequestNewsDetail;
import com.uec.imonitor.request.bean.RequestNewsEntity;
import com.uec.imonitor.request.bean.RequestNewsShow;
import com.uec.imonitor.request.bean.TenantRequestEntity;
import com.uec.imonitor.request.dao.IRequestNewsJPARepository;
import com.uec.imonitor.request.service.IRequestNewsService;
import com.uec.imonitor.request.service.ITenantRequestService;
import com.uec.imonitor.task.bean.JobFailureEntity;
import com.uec.imonitor.task.service.IJobFailureService;
import com.uec.imonitor.Interface.ITenantRequestService;
import com.uec.imonitor.Interface.IDataSearch;
import com.uec.imonitor.Interface.IDataIndex;
import com.uec.imonitor.Interface.IJobFailureService;
import com.uec.imonitor.Interface.INewsStatusService;
import com.uec.imonitor.DTO.QueryParams;
import com.uec.imonitor.DTO.TxtUtil;
@Service("requestNewsService")
@Transactional(value = "transactionManager")
public class RequestNewsServiceImpl extends BaseServiceimplements IRequestNewsService{

 private  Logger logger;

@Autowired
 private  IRequestNewsJPARepository requestNewsRepository;

@Autowired
@Qualifier("tenantRequestService")
 private  ITenantRequestService tenantRequestService;

@Autowired
@Qualifier("dataSearch")
 private  IDataSearch dataSearch;

@Autowired
@Qualifier("dataIndex")
 private  IDataIndex dataIndex;

@Autowired
@Qualifier("jobFailureService")
 private  IJobFailureService jobFailureService;

@Autowired
@Qualifier("newsStatusService")
 private  INewsStatusService newsStatusService;

@Value("${imonitor.es.index.request.news.name.alias}")
 private  String requestNewsName;

@Value("${imonitor.es.index.request.news.type}")
 private  String requestNewsType;

@Value("${Ftp.ZGJYB_WEBNAME}")
 private  String webName;


@Override
public RequestNewsEntity add(RequestNewsEntity request){
    if (null == request) {
        throw new RequestParamException(new String[] { "request" });
    }
    RequestNewsEntity entity = requestNewsRepository.save(request);
    return entity;
}


public List<NewsStatusEntity> filterRepeatNews(List<NewsStatusEntity> newsList){
    Map<Integer, NewsStatusEntity> newsMap = new HashMap<>();
    List<NewsStatusEntity> actualNewsList = new ArrayList<>();
    if (!CollectionUtils.isEmpty(newsList)) {
        for (NewsStatusEntity entity : newsList) {
            Integer newsId = entity.getRecordId();
            if (!newsMap.containsKey(newsId)) {
                newsMap.put(newsId, entity);
            }
        }
        for (Integer key : newsMap.keySet()) {
            actualNewsList.add(newsMap.get(key));
        }
    }
    return actualNewsList;
}


@Override
public RequestNewsEntity findByWebpageCode(String webpageCode){
    if (StringUtils.isBlank(webpageCode)) {
        throw new RequestParamException(new String[] { "webpageCode" });
    }
    RequestNewsEntity entity = requestNewsRepository.findByWebpageCode(webpageCode);
    return entity;
}


@Override
public RequestNewsEntity update(RequestNewsEntity request){
    if (null == request) {
        throw new RequestParamException(new String[] { "request" });
    }
    RequestNewsEntity entity = requestNewsRepository.save(request);
    return entity;
}


@Override
public List<RequestNewsEntity> findByRequestId(Integer requestId){
    if (null == requestId) {
        throw new RequestParamException(new String[] { "requestId" });
    }
    List<RequestNewsEntity> list = requestNewsRepository.findByRequestId(requestId);
    return list;
}


@Override
public RequestNewsDetail findDetailByRequestNewsEntity(RequestNewsEntity entity){
    if (null == entity) {
        throw new RequestParamException(new String[] { "entity" });
    }
    RequestNewsDetail entityDetail = new RequestNewsDetail();
    BeanUtils.copyProperties(entity, entityDetail);
    // ??????????????????
    Integer newsType = entityDetail.getNewsType();
    if (null != newsType && ConstantUtil.NEWS_TYPE_NAME_MAP.containsKey(newsType)) {
        entityDetail.setNewsTypeName(ConstantUtil.NEWS_TYPE_NAME_MAP.get(newsType));
    }
    entityDetail.setEsPrimaryId(String.valueOf(entity.getNewsId()));
    return entityDetail;
}


@Override
public BaseQueryResult<RequestNewsShow> pageRequestNewsEs(QueryNewsParam param){
    // ????????????
    String[] indexArray = { requestNewsName };
    // ?????????
    String[] typeArray = { requestNewsType };
    // ?????????
    List<MatchParams> matchList = new ArrayList<>();
    // ??????
    if (null != param.getRequestId()) {
        List<String> fields = new ArrayList<String>();
        fields.add("requestId");
        List<String> values = new ArrayList<String>();
        values.add(param.getRequestId() + "");
        MatchParams match = new MatchParams(fields, values, ESConstantUtil.OPT_OR, ESConstantUtil.OPT_FILTER, ESConstantUtil.ANALYZER_NOT);
        matchList.add(match);
    } else {
        List<String> fields = new ArrayList<String>();
        fields.add("requestId");
        List<String> values = new ArrayList<String>();
        // ??????id???session???
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        Integer tenantId = (Integer) session.getAttribute("tenantId");
        List<TenantRequestEntity> list = tenantRequestService.findByTenantId(tenantId);
        for (TenantRequestEntity tenantRequestEntity : list) {
            values.add(tenantRequestEntity.getRequestId() + "");
        }
        if (CollectionUtils.isEmpty(values)) {
            values.add(0 + "");
        }
        MatchParams match = new MatchParams(fields, values, ESConstantUtil.OPT_OR, ESConstantUtil.OPT_FILTER, ESConstantUtil.ANALYZER_NOT);
        matchList.add(match);
    }
    // ???????????????
    if (StringUtils.isNotBlank(param.getQueryStr())) {
        List<String> fields = new ArrayList<String>();
        fields.add("title");
        fields.add("originalCode");
        List<String> values = new ArrayList<String>();
        values.add(param.getQueryStr());
        MatchParams match = new MatchParams(fields, values, ESConstantUtil.OPT_OR, ESConstantUtil.OPT_AND, ESConstantUtil.ANALYZER_IK_SMART);
        matchList.add(match);
    }
    // ???????????????id
    if (!CollectionUtils.isEmpty(param.getNewsIds())) {
        List<String> fields = new ArrayList<String>();
        fields.add("newsId");
        List<String> values = new ArrayList<String>();
        for (Integer newsId : param.getNewsIds()) {
            values.add(newsId + "");
        }
        MatchParams match = new MatchParams(fields, values, ESConstantUtil.OPT_OR, ESConstantUtil.OPT_FILTER, ESConstantUtil.ANALYZER_NOT);
        matchList.add(match);
    }
    // ?????????????????????
    MatchParams match = new MatchParams(new ArrayList<String>() {

        {
            add("isDeleted");
        }
    }, new ArrayList<String>() {

        {
            add("0");
        }
    });
    matchList.add(match);
    // ??????????????????
    List<RangeParams> rangeList = null;
    RangeParams range = null;
    Date startTime = param.getStartTime();
    Date endTime = param.getEndTime();
    if (null != startTime || null != endTime) {
        rangeList = new ArrayList<RangeParams>();
        range = new RangeParams("reportDatetime", startTime, endTime);
        rangeList.add(range);
    }
    // ??????
    String sortStr = param.getSortField();
    if (StringUtils.isBlank(sortStr)) {
        sortStr = "reportDatetime";
    }
    String sortType = param.getSortType();
    if (StringUtils.isBlank(sortType)) {
        sortType = ESConstantUtil.SORT_DESC;
    }
    // ??????
    SortParams sp = new SortParams(sortStr, sortType);
    List<SortParams> spl = new ArrayList<SortParams>();
    spl.add(sp);
    // ??????
    int pageStart = 0;
    int pageSize = 20;
    if (null != param.getPageStart()) {
        pageStart = param.getPageStart();
    }
    if (null != param.getPageSize()) {
        pageSize = param.getPageSize();
    }
    QueryParams<RequestNewsShow> qp = new QueryParams<RequestNewsShow>(matchList, rangeList, null, pageStart, pageSize, spl, RequestNewsShow.class);
    qp.setIndexArray(indexArray);
    qp.setTypeArray(typeArray);
    qp.setAggsParams(param.getAggsParams());
    BaseQueryResult<RequestNewsShow> textSearcher = dataSearch.textSearcher(qp);
    return textSearcher;
}


@Override
public RequestNewsEntity saveRequestNews(RequestNewsEntity entity){
    if (null == entity) {
        throw new RequestParamException(new String[] { "requestNewsEntity" });
    }
    if (StringUtils.isBlank(entity.getTitle())) {
        throw new RequestParamException(new String[] { "title" });
    }
    if (StringUtils.isBlank(entity.getContent())) {
        throw new RequestParamException(new String[] { "content" });
    }
    if (StringUtils.isBlank(entity.getNewsAuthor())) {
        throw new RequestParamException(new String[] { "author" });
    }
    if (StringUtils.isBlank(entity.getNewsSection())) {
        throw new RequestParamException(new String[] { "section" });
    }
    if (null == entity.getReportDatetime()) {
        throw new RequestParamException(new String[] { "reportDatetime" });
    }
    if (null == entity.getRequestId()) {
        throw new RequestParamException(new String[] { "requestId" });
    }
    TenantRequestEntity tenantRequest = tenantRequestService.findById(entity.getRequestId());
    entity.setNewsSource(tenantRequest.getRequestName());
    TxtUtil txtUtil = new TxtUtil();
    String webpagecode = txtUtil.hashKeyForDisk(webName + entity.getNewsAuthor() + entity.getTitle());
    entity.setWebpageCode(webpagecode);
    entity.setStartDatetime(entity.getReportDatetime());
    entity.setEndDatetime(DateUtils.addDays(entity.getStartDatetime(), tenantRequest.getCrawlDays()));
    entity.setCreateDatetime(new Date());
    entity.setIsDeleted(0);
    RequestNewsEntity requestNewsEntity = this.add(entity);
    return requestNewsEntity;
}


@Override
public boolean delete(Integer id){
    if (null == id) {
        throw new RequestParamException(new String[] { "id" });
    }
    requestNewsRepository.delete(id);
    return true;
}


@Override
public void insertRequestNewsIndexJob(int do_num){
    logger.info("???????????? ????????????????????????");
    List<NewsStatusEntity> newsList = new ArrayList<>();
    try {
        newsList = newsStatusService.listTopNInsertRecordsByTableName(ConstantUtil.TABLE_REQUEST_NEWS, do_num);
    } catch (RequestParamException e) {
        logger.error("?????????????????????????????????????????? ???????????? ????????????" + e);
    }
    // ??????????????????????????????update?????????????????????
    newsList = filterRepeatNews(newsList);
    if (!CollectionUtils.isEmpty(newsList)) {
        int num = 300;
        int t = (newsList.size() - 1) / num + 1;
        for (int i = 0; i < t; i++) {
            List<NewsStatusEntity> statusList = new ArrayList<>();
            if ((i + 1) * num < newsList.size()) {
                statusList = newsList.subList(i * num, (i + 1) * num);
            } else {
                statusList = newsList.subList(i * num, newsList.size());
            }
            List<Integer> insertList = ESUtil.listRecordIdsOfEsStatusList(statusList);
            // ??????????????????
            doIndexAnalysis(0, insertList, true);
            // ???????????????
            try {
                newsStatusService.deleteEsStatusList(statusList);
            } catch (RequestParamException e) {
                logger.error("?????????????????????????????????????????????statusList = " + statusList + " ," + e);
            }
            // ????????????????????????????????????????????????
            logger.info("??????????????????????????????????????????");
        }
    }
    logger.info("???????????? ????????????????????????");
}


@Override
public void deleteRequestNewsJob(int do_num){
    logger.info("????????????????????????????????????");
    List<NewsStatusEntity> statusList = new ArrayList<>();
    try {
        statusList = newsStatusService.listTopNDeletedRecordsByTableName(ConstantUtil.TABLE_REQUEST_NEWS, do_num);
    } catch (RequestParamException e) {
        logger.error("?????????????????????????????????????????? ???????????? ????????????" + e);
    }
    if (!CollectionUtils.isEmpty(statusList)) {
        List<String> inneridList = new ArrayList<>();
        for (NewsStatusEntity status : statusList) {
            inneridList.add(status.getRecordId().toString());
        }
        boolean f = dataIndex.bulkDelete(requestNewsName, requestNewsType, inneridList);
        if (f) {
            logger.info("????????????????????????");
        } else {
            logger.error("???????????????????????????inneridList= " + CommonUtil.toJson(inneridList));
        }
        try {
            newsStatusService.deleteEsStatusList(statusList);
        } catch (RequestParamException e) {
            logger.error("?????????????????????????????????????????????statusList = " + statusList + " ," + e);
        }
    // ?????????????????????
    }
    logger.info("????????????????????????????????????");
}


@Override
public List<RequestNewsDetail> listDetailByIds(List<Integer> idList){
    if (CollectionUtils.isEmpty(idList)) {
        throw new RequestParamException(new String[] { "idList" });
    }
    List<RequestNewsDetail> resultList = new ArrayList<>();
    List<RequestNewsEntity> requestNewsList = requestNewsRepository.findAll(idList);
    for (RequestNewsEntity entity : requestNewsList) {
        RequestNewsDetail entityDetail = this.findDetailByRequestNewsEntity(entity);
        resultList.add(entityDetail);
    }
    return resultList;
}


@Override
public void insertAndUpdateIndexFailureJob(){
    // ??????????????????????????????
    List<JobFailureEntity> jobList = null;
    try {
        jobList = jobFailureService.listTopNByJobAndTableName(ConstantUtil.TASK_REQUEST_NEWS_INSERT, ConstantUtil.TABLE_REQUEST_NEWS, 10);
    } catch (RequestParamException e) {
        logger.error("???????????????????????????????????? " + e);
    }
    for (JobFailureEntity job : jobList) {
        // ????????????
        int n = job.getNum();
        if (n < ConstantUtil.TASK_FAILURE_NUM) {
            String idSet = job.getIdSet();
            List<Integer> idList = CommonUtil.parseJson2List(idSet, Integer.class);
            // ??????????????????
            doIndexAnalysis(n, idList, true);
            System.out.println(idList.toArray());
            // ???????????????????????????job??????
            try {
                jobFailureService.deleteJobFailure(job.getInnerid());
            } catch (RequestParamException e) {
                logger.error("???????????????????????????????????? " + e);
            }
        }
    }
}


@Override
public RequestNewsEntity findById(int id){
    if (0 == id) {
        throw new RequestParamException(new String[] { "id" });
    }
    RequestNewsEntity entity = requestNewsRepository.findOne(id);
    return entity;
}


@Override
public PageResponse<RequestNewsShow> pageRequestNews(QueryNewsParam param){
    String sortStr = param.getSortField();
    if (StringUtils.isBlank(sortStr)) {
        sortStr = "reportDatetime";
    }
    String sortType = param.getSortType();
    Sort sort;
    if ("asc".equalsIgnoreCase(sortType)) {
        sort = new Sort(Direction.ASC, sortStr);
    } else {
        sort = new Sort(Direction.DESC, sortStr);
    }
    Pageable pageable = new PageRequest(param.getPageStart() / param.getPageSize(), param.getPageSize(), sort);
    List<Integer> requestIds = new ArrayList<Integer>();
    if (null != param.getRequestId()) {
        requestIds.add(param.getRequestId());
    } else {
        // ??????id???session???
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        Integer tenantId = (Integer) session.getAttribute("tenantId");
        List<TenantRequestEntity> list = tenantRequestService.findByTenantId(tenantId);
        for (TenantRequestEntity tenantRequest : list) {
            requestIds.add(tenantRequest.getRequestId());
        }
    }
    if (CollectionUtils.isEmpty(requestIds)) {
        requestIds = null;
    }
    Page<Object[]> page = requestNewsRepository.pageRequestNews(requestIds, param.getStartTime(), param.getEndTime(), pageable);
    List<Object[]> content = page.getContent();
    List<RequestNewsShow> newsShowList = new ArrayList<RequestNewsShow>();
    for (Object[] object : content) {
        RequestNewsShow newsShow = new RequestNewsShow();
        Integer newsId = Integer.valueOf(object[0].toString());
        RequestNewsEntity newsEntity = requestNewsRepository.findOne(newsId);
        BeanUtils.copyProperties(newsEntity, newsShow);
        newsShow.setReprintCount(Integer.valueOf(object[1].toString()));
        newsShowList.add(newsShow);
    }
    PageResponse<RequestNewsShow> pageResponse = new PageResponse<RequestNewsShow>();
    pageResponse.setContent(newsShowList);
    pageResponse.setTotalElements(page.getTotalElements());
    return pageResponse;
}


public void doIndexAnalysis(int n,List<Integer> idList,boolean type){
    if (null != idList && !CollectionUtils.isEmpty(idList)) {
        List<RequestNewsDetail> requestNewsInsertList = new ArrayList<>();
        try {
            requestNewsInsertList = this.listDetailByIds(idList);
        } catch (Exception e) {
            logger.error("??????ids??????????????????????????????????????? + idList = " + idList + "," + e);
            Date d = new Date();
            try {
                jobFailureService.saveFailureJob(ConstantUtil.TASK_REQUEST_NEWS_INSERT, ConstantUtil.TABLE_REQUEST_NEWS, idList, d, n + 1);
            } catch (RequestParamException e1) {
                logger.error("???????????????????????????????????? + idList = " + idList + "," + e1);
            }
        }
        if (!CollectionUtils.isEmpty(requestNewsInsertList)) {
            // ??????????????????????????????ids
            List<Integer> newsIds = ESUtil.listIdsOfRequestNewsDetailList(requestNewsInsertList);
            // ????????????????????? ??????
            boolean flag = dataIndex.bulkUpdate(requestNewsName, requestNewsType, requestNewsInsertList);
            if (flag == false) {
                // ??????????????????????????????
                Date d = new Date();
                try {
                    jobFailureService.saveFailureJob(ConstantUtil.TASK_REQUEST_NEWS_INSERT, ConstantUtil.TABLE_REQUEST_NEWS, newsIds, d, n + 1);
                } catch (RequestParamException e) {
                    logger.error("??????????????????????????????" + ", ??????=" + d + ", newsIds =" + CommonUtil.toJson(newsIds));
                }
                logger.error("????????????/update????????????????????????" + ", ??????=" + d + ", newsIds =" + CommonUtil.toJson(newsIds));
            } else {
                logger.info("????????????/update????????????????????????");
            }
        }
    }
}


@Override
public void updateRequestNewsIndexJob(int do_num){
    logger.info("???????????? ????????????????????????");
    List<NewsStatusEntity> newsList = new ArrayList<>();
    try {
        newsList = newsStatusService.listTopNUpdateRecordsByTableName(ConstantUtil.TABLE_REQUEST_NEWS, do_num);
    } catch (RequestParamException e) {
        logger.error("?????????????????????????????????????????? ???????????? ????????????" + e);
    }
    // ??????????????????????????????update?????????????????????
    newsList = filterRepeatNews(newsList);
    if (!CollectionUtils.isEmpty(newsList)) {
        int num = 300;
        int t = (newsList.size() - 1) / num + 1;
        for (int i = 0; i < t; i++) {
            List<NewsStatusEntity> statusList = new ArrayList<>();
            if ((i + 1) * num < newsList.size()) {
                statusList = newsList.subList(i * num, (i + 1) * num);
            } else {
                statusList = newsList.subList(i * num, newsList.size());
            }
            List<Integer> insertList = ESUtil.listRecordIdsOfEsStatusList(statusList);
            // ??????????????????
            doIndexAnalysis(0, insertList, false);
            // ???????????????
            try {
                newsStatusService.deleteEsStatusList(statusList);
            } catch (RequestParamException e) {
                logger.error("?????????????????????????????????????????????statusList = " + statusList + " ," + e);
            }
            // ????????????????????????????????????????????????
            logger.info("??????????????????????????????????????????");
        }
    }
    logger.info("????????????????????????????????????");
}


}