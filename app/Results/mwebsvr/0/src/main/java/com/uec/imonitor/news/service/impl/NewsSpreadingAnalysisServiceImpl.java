package com.uec.imonitor.news.service.impl;
 import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import com.uec.imonitor.common.base.BaseService;
import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.common.exception.RequestParamException;
import com.uec.imonitor.common.util.CommonUtil;
import com.uec.imonitor.common.util.ConstantUtil;
import com.uec.imonitor.common.util.ESConstantUtil;
import com.uec.imonitor.common.util.ESUtil;
import com.uec.imonitor.es.bean.NewsStatusEntity;
import com.uec.imonitor.es.bean.params.AggsHistogramParams;
import com.uec.imonitor.es.bean.params.MatchParams;
import com.uec.imonitor.es.bean.params.QueryParams;
import com.uec.imonitor.es.bean.params.RangeParams;
import com.uec.imonitor.es.bean.params.SortParams;
import com.uec.imonitor.es.bean.result.BaseQueryResult;
import com.uec.imonitor.es.index.IDataIndex;
import com.uec.imonitor.es.search.IDataSearch;
import com.uec.imonitor.es.service.INewsStatusService;
import com.uec.imonitor.news.bean.NewsSpreadingAnalysisDetail;
import com.uec.imonitor.news.bean.NewsSpreadingAnalysisEntity;
import com.uec.imonitor.news.bean.NewsWebpageEntity;
import com.uec.imonitor.news.bean.QueryNewsParam;
import com.uec.imonitor.news.dao.INewsSpreadingAnalysisJPARepository;
import com.uec.imonitor.news.service.INewsSpreadingAnalysisService;
import com.uec.imonitor.news.service.INewsWebpageService;
import com.uec.imonitor.request.bean.RequestNewsEntity;
import com.uec.imonitor.request.bean.TenantRequestEntity;
import com.uec.imonitor.request.service.IRequestNewsService;
import com.uec.imonitor.request.service.ITenantRequestService;
import com.uec.imonitor.task.bean.JobFailureEntity;
import com.uec.imonitor.task.service.IJobFailureService;
import com.uec.imonitor.user.bean.UserEntity;
import com.uec.imonitor.Interface.ITenantRequestService;
import com.uec.imonitor.Interface.IDataSearch;
import com.uec.imonitor.Interface.IDataIndex;
import com.uec.imonitor.Interface.IJobFailureService;
import com.uec.imonitor.Interface.INewsStatusService;
import com.uec.imonitor.DTO.MatchParams;
import com.uec.imonitor.DTO.AggsHistogramParams;
@Service("newsSpreadingAnalysisService")
@Transactional(value = "transactionManager")
public class NewsSpreadingAnalysisServiceImpl extends BaseServiceimplements INewsSpreadingAnalysisService{

 private  Logger logger;

@Autowired
 private  INewsSpreadingAnalysisJPARepository newsSpreadingAnalysisRepository;

@Autowired
@Qualifier("newsWebpageService")
 private  INewsWebpageService newsWebpageService;

@Autowired
@Qualifier("tenantRequestService")
 private  ITenantRequestService tenantRequestService;

@Autowired
@Qualifier("requestNewsService")
 private  IRequestNewsService requestNewsService;

@Autowired
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

@Value("${imonitor.es.index.spreading.name.alias}")
 private  String newsSpreadingName;

@Value("${imonitor.es.index.spreading.type}")
 private  String newsSpreadingType;


@Override
public List<NewsSpreadingAnalysisEntity> findByNewsId(Integer newsId){
    if (null == newsId) {
        throw new RequestParamException(new String[] { "newsId" });
    }
    List<NewsSpreadingAnalysisEntity> list = newsSpreadingAnalysisRepository.findByNewsId(newsId);
    return list;
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
public List<NewsSpreadingAnalysisEntity> findByWebpageCode(String webpageCode){
    if (StringUtils.isBlank(webpageCode)) {
        throw new RequestParamException(new String[] { "webpageCode" });
    }
    List<NewsSpreadingAnalysisEntity> entity = newsSpreadingAnalysisRepository.findByWebpageCode(webpageCode);
    return entity;
}


@Override
public List<NewsSpreadingAnalysisEntity> findByNewsIdAndNotMarked(Integer newsId){
    if (null == newsId) {
        throw new RequestParamException(new String[] { "newsId" });
    }
    List<NewsSpreadingAnalysisEntity> list = newsSpreadingAnalysisRepository.findByNewsIdAndStatus(newsId, 0);
    return list;
}


@Override
public List<NewsSpreadingAnalysisDetail> listNewsSpreadingAnalysisDetailByIds(List<Integer> idList){
    if (null == idList || CollectionUtils.isEmpty(idList)) {
        throw new RequestParamException(new String[] { "entity" });
    }
    List<NewsSpreadingAnalysisEntity> newsEntityList = newsSpreadingAnalysisRepository.findAll(idList);
    List<NewsSpreadingAnalysisDetail> newsDetailList = new ArrayList<>();
    for (NewsSpreadingAnalysisEntity entity : newsEntityList) {
        NewsSpreadingAnalysisDetail newsDetail = this.findDetailByNewsSpreadingAnalysisEntity(entity);
        newsDetailList.add(newsDetail);
    }
    return newsDetailList;
}


@Override
public BaseQueryResult<NewsSpreadingAnalysisDetail> pageReprintNews(QueryNewsParam param){
    // 索引库集
    String[] indexArray = { newsSpreadingName };
    // 类型集
    String[] typeArray = { newsSpreadingType };
    // 查询词
    List<MatchParams> matchList = new ArrayList<>();
    // 需求
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
        // 租户id从session取
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
    // 被监控新闻id
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
    // 爬取来源
    if (StringUtils.isNotBlank(param.getSourceCrawl())) {
        List<String> fields = new ArrayList<String>();
        fields.add("sourceCrawl");
        List<String> values = new ArrayList<String>();
        values.add(param.getSourceCrawl());
        MatchParams match = new MatchParams(fields, values, ESConstantUtil.OPT_OR, ESConstantUtil.OPT_FILTER, ESConstantUtil.ANALYZER_NOT);
        matchList.add(match);
    }
    // 标题和编号
    if (StringUtils.isNotBlank(param.getQueryStr())) {
        List<String> fields = new ArrayList<String>();
        fields.add("title");
        fields.add("originalCode");
        List<String> values = new ArrayList<String>();
        values.add(param.getQueryStr());
        MatchParams match = new MatchParams(fields, values, ESConstantUtil.OPT_OR, ESConstantUtil.OPT_AND, ESConstantUtil.ANALYZER_IK_SMART);
        matchList.add(match);
    }
    // 转载类型
    if (null != param.getReprintType()) {
        List<String> fields = new ArrayList<String>();
        fields.add("reprintType");
        List<String> values = new ArrayList<String>();
        values.add(param.getReprintType() + "");
        MatchParams match = new MatchParams(fields, values, ESConstantUtil.OPT_OR, ESConstantUtil.OPT_FILTER, ESConstantUtil.ANALYZER_NOT);
        matchList.add(match);
    }
    // 状态
    if (null != param.getStatus()) {
        List<String> fields = new ArrayList<String>();
        fields.add("status");
        List<String> values = new ArrayList<String>();
        values.add(param.getStatus() + "");
        MatchParams match = new MatchParams(fields, values, ESConstantUtil.OPT_OR, ESConstantUtil.OPT_FILTER, ESConstantUtil.ANALYZER_NOT);
        matchList.add(match);
    }
    // 查询未删除新闻
    MatchParams match = new MatchParams(new ArrayList<String>() {

        {
            add("isDeleted");
        }
    }, new ArrayList<String>() {

        {
            add("0");
        }
    });
    match.setOutOpt(ESConstantUtil.OPT_FILTER);
    matchList.add(match);
    // 转载时间范围筛选
    List<RangeParams> rangeList = null;
    RangeParams range = null;
    Date startTime = param.getStartTime();
    Date endTime = param.getEndTime();
    if (null != startTime || null != endTime) {
        rangeList = new ArrayList<RangeParams>();
        range = new RangeParams("releaseDatetime", startTime, endTime);
        rangeList.add(range);
    }
    // 原始新闻发布时间筛选-- 可选
    RangeParams reportRange = null;
    Date reportStartTime = param.getReportStartTime();
    Date reportEndTime = param.getReportEndTime();
    if (null != reportStartTime || null != reportEndTime) {
        rangeList = new ArrayList<RangeParams>();
        reportRange = new RangeParams("reqReportDatetime", reportStartTime.getTime(), reportEndTime.getTime());
        rangeList.add(reportRange);
    }
    // 排序
    String sortStr = param.getSortField();
    if (StringUtils.isBlank(sortStr)) {
        sortStr = "releaseDatetime";
    }
    String sortType = param.getSortType();
    if (StringUtils.isBlank(sortType)) {
        sortType = ESConstantUtil.SORT_DESC;
    }
    // 排序
    SortParams sp = new SortParams(sortStr, sortType);
    List<SortParams> spl = new ArrayList<SortParams>();
    spl.add(sp);
    // 分页
    int pageStart = 0;
    int pageSize = 20;
    if (null != param.getPageStart()) {
        pageStart = param.getPageStart();
    }
    if (null != param.getPageSize()) {
        pageSize = param.getPageSize();
    }
    QueryParams<NewsSpreadingAnalysisDetail> qp = new QueryParams<NewsSpreadingAnalysisDetail>(matchList, rangeList, null, pageStart, pageSize, spl, NewsSpreadingAnalysisDetail.class);
    qp.setIndexArray(indexArray);
    qp.setTypeArray(typeArray);
    qp.setAggsParams(param.getAggsParams());
    BaseQueryResult<NewsSpreadingAnalysisDetail> textSearcher = dataSearch.phraseSearcher(qp);
    return textSearcher;
}


@Override
public NewsSpreadingAnalysisEntity findByNewsIdAndCode(Integer newsId,String webpageCode){
    NewsSpreadingAnalysisEntity entity = newsSpreadingAnalysisRepository.findByNewsIdAndCode(newsId, webpageCode);
    return entity;
}


@Override
public void deletedSpreadingAnalysisIndexJob(int do_num){
    logger.info("传播分析结果 删除索引任务开始");
    List<NewsStatusEntity> statusList = new ArrayList<>();
    try {
        statusList = newsStatusService.listTopNDeletedRecordsByTableName(ConstantUtil.TABLE_SPREAD_ANALYSIS, do_num);
    } catch (RequestParamException e) {
        logger.error("获取新闻记录状态表中传播分析结果 删除记录 数据异常" + e);
    }
    if (!CollectionUtils.isEmpty(statusList)) {
        for (NewsStatusEntity status : statusList) {
            boolean f = dataIndex.delete(newsSpreadingName, newsSpreadingType, status.getRecordId().toString());
            if (f) {
                logger.info("删除传播分析结果成功，innerid =" + status.getRecordId());
            } else {
                logger.error("删除传播分析结果失败，innerid= " + status.getRecordId());
            }
        }
    }
    try {
        newsStatusService.deleteEsStatusList(statusList);
    } catch (RequestParamException e) {
        logger.error("删除新闻记录状态表中 传播分析结果 数据异常，statusList = " + statusList + " ," + e);
    }
    // 删除记录表信息
    logger.info("传播分析结果 删除索引任务结束");
}


@Override
public NewsSpreadingAnalysisDetail findDetailByNewsSpreadingAnalysisEntity(NewsSpreadingAnalysisEntity entity){
    if (null == entity) {
        throw new RequestParamException(new String[] { "entity" });
    }
    String webpageCode = entity.getWebpageCode();
    if (StringUtils.isBlank(webpageCode)) {
        throw new RequestParamException(new String[] { "entity.webpageCode" });
    }
    NewsWebpageEntity webpageEntity = newsWebpageService.findByWebpageCode(webpageCode);
    // 结果对象
    NewsSpreadingAnalysisDetail result = new NewsSpreadingAnalysisDetail(webpageEntity);
    BeanUtils.copyProperties(entity, result);
    // 状态名称
    Integer status = result.getStatus();
    if (null != status && ConstantUtil.REPRINT_STATUS_MAP.containsKey(status)) {
        result.setStatusName(ConstantUtil.REPRINT_STATUS_MAP.get(status));
    }
    // 转载类型名称
    Integer reprintType = result.getReprintType();
    if (null != reprintType && ConstantUtil.REPRINT_TYPE_MAP.containsKey(reprintType)) {
        result.setReprintTypeName(ConstantUtil.REPRINT_TYPE_MAP.get(reprintType));
    }
    // 新闻类型名称
    Integer newsType = result.getNewsType();
    if (null != newsType && ConstantUtil.NEWS_TYPE_NAME_MAP.containsKey(newsType)) {
        result.setNewsTypeName(ConstantUtil.NEWS_TYPE_NAME_MAP.get(newsType));
    }
    Integer newsid = entity.getNewsId();
    if (null != newsid) {
        RequestNewsEntity req = requestNewsService.findById(newsid);
        if (null != req) {
            result.setReqReportDatetime(req.getReportDatetime());
        }
    }
    result.setEsPrimaryId(String.valueOf(entity.getInnerid()));
    return result;
}


@Override
public void updateSpreadingAnalysisIndexJob(int do_num){
    logger.info("传播分析结果 更新索引任务开始");
    List<NewsStatusEntity> newsList = new ArrayList<>();
    try {
        newsList = newsStatusService.listTopNUpdateRecordsByTableName(ConstantUtil.TABLE_SPREAD_ANALYSIS, do_num);
    } catch (RequestParamException e) {
        logger.error("获取新闻记录状态表中 传播分析结果 更新记录 数据异常" + e);
    }
    // 过滤重复的记录，过滤update中被删除的记录
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
            // 新增最新新闻
            doIndexAnalysis(0, insertList, false);
            // 删除记录集
            try {
                newsStatusService.deleteEsStatusList(statusList);
            } catch (RequestParamException e) {
                logger.error("删除新闻记录状态表中 传播分析结果 数据异常，statusList = " + statusList + " ," + e);
            }
            // 删除记录状态表中对应已索引的数据
            logger.info("删除新闻记录状态表中 传播分析结果 数据成功");
        }
    }
    logger.info("传播分析结果 删除索引任务结束");
}


@Override
public void insertAndUpdateIndexFailureJob(){
    // 获取插入最新新闻任务
    List<JobFailureEntity> jobList = null;
    try {
        jobList = jobFailureService.listTopNByJobAndTableName(ConstantUtil.TASK_SPREADING_INSERT, ConstantUtil.TABLE_SPREAD_ANALYSIS, 10);
    } catch (RequestParamException e) {
        logger.error("获取传播分析结果失败任务异常 " + e);
    }
    for (JobFailureEntity job : jobList) {
        // 执行次数
        int n = job.getNum();
        if (n < ConstantUtil.TASK_FAILURE_NUM) {
            String idSet = job.getIdSet();
            List<Integer> idList = CommonUtil.parseJson2List(idSet, Integer.class);
            // 新增最新新闻
            doIndexAnalysis(n, idList, true);
            System.out.println(idList.toArray());
            // 执行结束，删除当前job任务
            try {
                jobFailureService.deleteJobFailure(job.getInnerid());
            } catch (RequestParamException e) {
                logger.error("删除传播分析结果失败任务异常 " + e);
            }
        }
    }
}


@Override
public BaseQueryResult<NewsSpreadingAnalysisDetail> countByDate(QueryNewsParam param){
    // 索引库集
    String[] indexArray = { newsSpreadingName };
    // 类型集
    String[] typeArray = { newsSpreadingType };
    // 查询词
    List<MatchParams> matchList = new ArrayList<>();
    // 需求
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
        // 租户id从session取
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
    // 被监控新闻id
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
    // 爬取来源
    if (StringUtils.isNotBlank(param.getSourceCrawl())) {
        List<String> fields = new ArrayList<String>();
        fields.add("sourceCrawl");
        List<String> values = new ArrayList<String>();
        values.add(param.getSourceCrawl());
        MatchParams match = new MatchParams(fields, values, ESConstantUtil.OPT_OR, ESConstantUtil.OPT_FILTER, ESConstantUtil.ANALYZER_NOT);
        matchList.add(match);
    }
    // 标题和编号
    if (StringUtils.isNotBlank(param.getQueryStr())) {
        List<String> fields = new ArrayList<String>();
        fields.add("title");
        fields.add("originalCode");
        List<String> values = new ArrayList<String>();
        values.add(param.getQueryStr());
        MatchParams match = new MatchParams(fields, values, ESConstantUtil.OPT_OR, ESConstantUtil.OPT_AND, ESConstantUtil.ANALYZER_IK_SMART);
        matchList.add(match);
    }
    // 转载类型
    if (null != param.getReprintType()) {
        List<String> fields = new ArrayList<String>();
        fields.add("reprintType");
        List<String> values = new ArrayList<String>();
        values.add(param.getReprintType() + "");
        MatchParams match = new MatchParams(fields, values, ESConstantUtil.OPT_OR, ESConstantUtil.OPT_FILTER, ESConstantUtil.ANALYZER_NOT);
        matchList.add(match);
    }
    // 状态
    if (null != param.getStatus()) {
        List<String> fields = new ArrayList<String>();
        fields.add("status");
        List<String> values = new ArrayList<String>();
        values.add(param.getStatus() + "");
        MatchParams match = new MatchParams(fields, values, ESConstantUtil.OPT_OR, ESConstantUtil.OPT_FILTER, ESConstantUtil.ANALYZER_NOT);
        matchList.add(match);
    }
    // 查询未删除新闻
    MatchParams match = new MatchParams(new ArrayList<String>() {

        {
            add("isDeleted");
        }
    }, new ArrayList<String>() {

        {
            add("0");
        }
    });
    match.setOutOpt(ESConstantUtil.OPT_FILTER);
    matchList.add(match);
    // 时间范围筛选
    List<RangeParams> rangeList = null;
    RangeParams range = null;
    Date startTime = param.getStartTime();
    Date endTime = param.getEndTime();
    if (null != startTime || null != endTime) {
        rangeList = new ArrayList<RangeParams>();
        range = new RangeParams("releaseDatetime", startTime, endTime);
        rangeList.add(range);
    }
    // 排序
    String sortStr = param.getSortField();
    if (StringUtils.isBlank(sortStr)) {
        sortStr = "releaseDatetime";
    }
    String sortType = param.getSortType();
    if (StringUtils.isBlank(sortType)) {
        sortType = ESConstantUtil.SORT_DESC;
    }
    // 排序
    SortParams sp = new SortParams(sortStr, sortType);
    List<SortParams> spl = new ArrayList<SortParams>();
    spl.add(sp);
    // 分页
    int pageStart = 0;
    int pageSize = 20;
    if (null != param.getPageStart()) {
        pageStart = param.getPageStart();
    }
    if (null != param.getPageSize()) {
        pageSize = param.getPageSize();
    }
    QueryParams<NewsSpreadingAnalysisDetail> qp = new QueryParams<NewsSpreadingAnalysisDetail>(matchList, rangeList, null, pageStart, pageSize, spl, NewsSpreadingAnalysisDetail.class);
    qp.setIndexArray(indexArray);
    qp.setTypeArray(typeArray);
    // 时间聚合
    AggsHistogramParams histogramParams = new AggsHistogramParams();
    histogramParams.setAggsName(ESConstantUtil.AGGS_DAY_HOURS_NAME);
    histogramParams.setAggsField(ESConstantUtil.RELEASE_DATETIME);
    // 时间间隔
    histogramParams.setInterval(DateHistogramInterval.days(1));
    // 时区纠正
    histogramParams.setTimeZone(ESConstantUtil.TIME_ZONE);
    // 格式化
    histogramParams.setFormat("yyyy-MM-dd");
    qp.setHistogramParams(histogramParams);
    BaseQueryResult<NewsSpreadingAnalysisDetail> textSearcher = dataSearch.textHistogramSearcher(qp);
    return textSearcher;
}


@Override
public void insertSpreadingAnalysisIndexJob(int do_num){
    logger.info("传播分析结果 插入索引任务开始");
    List<NewsStatusEntity> newsList = new ArrayList<>();
    try {
        newsList = newsStatusService.listTopNInsertRecordsByTableName(ConstantUtil.TABLE_SPREAD_ANALYSIS, do_num);
    } catch (RequestParamException e) {
        logger.error("获取新闻记录状态表中传播分析结果 插入记录 数据异常" + e);
    }
    // 过滤重复的记录，过滤update中被删除的记录
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
            // 新增最新新闻
            doIndexAnalysis(0, insertList, true);
            // 删除记录集
            try {
                newsStatusService.deleteEsStatusList(statusList);
            } catch (RequestParamException e) {
                logger.error("删除新闻记录状态表中 传播分析结果 数据异常，statusList = " + statusList + " ," + e);
            }
            // 删除记录状态表中对应已索引的数据
            logger.info("删除新闻记录状态表中传播分析结果 数据成功");
        }
    }
    logger.info("传播分析结果 插入索引任务结束");
}


public void doIndexAnalysis(int n,List<Integer> idList,boolean type){
    if (null != idList && !CollectionUtils.isEmpty(idList)) {
        List<NewsSpreadingAnalysisDetail> newsSpreadingInsertList = new ArrayList<>();
        try {
            newsSpreadingInsertList = this.listNewsSpreadingAnalysisDetailByIds(idList);
        } catch (Exception e) {
            logger.error("通过ids获取 传播分析结果 详情对象集异常 + idList = " + idList + "," + e);
            Date d = new Date();
            try {
                jobFailureService.saveFailureJob(ConstantUtil.TASK_SPREADING_INSERT, ConstantUtil.TABLE_SPREAD_ANALYSIS, idList, d, n + 1);
            } catch (RequestParamException e1) {
                logger.error("传播分析结果 失败任务入库异常 + idList = " + idList + "," + e1);
            }
        }
        if (!CollectionUtils.isEmpty(newsSpreadingInsertList)) {
            // 获取最终获取的新闻集ids
            List<Integer> newsIds = ESUtil.listIdsOfSpreadAnalysisDetailList(newsSpreadingInsertList);
            // 新插入新闻索引 任务
            boolean flag = dataIndex.bulkUpdate(newsSpreadingName, newsSpreadingType, newsSpreadingInsertList);
            if (flag == false) {
                // 任务失败，将记录存储
                Date d = new Date();
                try {
                    jobFailureService.saveFailureJob(ConstantUtil.TASK_SPREADING_INSERT, ConstantUtil.TABLE_SPREAD_ANALYSIS, newsIds, d, n + 1);
                } catch (RequestParamException e) {
                    logger.error("索引 传播分析结果 内容失败" + ", 时间=" + d + ", newsIds =" + CommonUtil.toJson(newsIds));
                }
                logger.error("执行插入/update传播分析结果 内容失败" + ", 时间=" + d + ", newsIds =" + CommonUtil.toJson(newsIds));
            } else {
                logger.info("执行插入/update传播分析结果索引成功");
            }
        }
    }
}


}