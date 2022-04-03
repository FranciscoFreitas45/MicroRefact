package com.uec.imonitor.news.api;
 import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.uec.imonitor.common.base.BaseController;
import com.uec.imonitor.common.base.PageResponse;
import com.uec.imonitor.common.datatables.DataTablesRequestEntity;
import com.uec.imonitor.common.datatables.DataTablesResponseEntity;
import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.common.exception.RequestParamException;
import com.uec.imonitor.common.util.ConstantUtil;
import com.uec.imonitor.common.util.ESConstantUtil;
import com.uec.imonitor.es.bean.params.AggsTermParams;
import com.uec.imonitor.es.bean.result.BaseQueryResult;
import com.uec.imonitor.news.bean.NewsContentEntity;
import com.uec.imonitor.news.bean.NewsSpreadingAnalysisDetail;
import com.uec.imonitor.news.bean.NewsSpreadingAnalysisEntity;
import com.uec.imonitor.news.bean.NewsWebpageEntity;
import com.uec.imonitor.news.bean.QueryNewsParam;
import com.uec.imonitor.news.bean.ReprintNewsDetail;
import com.uec.imonitor.news.service.INewsContentService;
import com.uec.imonitor.news.service.INewsSpreadingAnalysisService;
import com.uec.imonitor.news.service.INewsWebpageService;
import com.uec.imonitor.request.bean.RequestNewsEntity;
import com.uec.imonitor.request.bean.RequestNewsShow;
import com.uec.imonitor.request.service.IRequestNewsService;
import com.uec.imonitor.Interface.INewsContentService;
import com.uec.imonitor.DTO.DataTablesRequestEntity;
import com.uec.imonitor.DTO.DataTablesResponseEntity;
import com.uec.imonitor.DTO.NewsContentEntity;
@Scope("prototype")
@RestController
@RequestMapping(value = "/{tenantMark}/api/news")
public class NewsApi extends BaseController{

 private  Logger logger;

@Autowired
@Qualifier("newsSpreadingAnalysisService")
 private  INewsSpreadingAnalysisService newsSpreadingAnalysisService;

@Autowired
@Qualifier("newsWebpageService")
 private  INewsWebpageService newsWebpageService;

@Autowired
@Qualifier("newsContentService")
 private  INewsContentService newsContentService;

@Autowired
@Qualifier("requestNewsService")
 private  IRequestNewsService requestNewsService;

@Value("${inews.image.server.address}")
 private  String inewsImageServer;


@RequestMapping(value = "/tortRatio", method = RequestMethod.GET)
public ModelMap tortRatio(QueryNewsParam param,Integer newsId,Model model){
    List<Integer> newsIds = null;
    if (null != newsId) {
        newsIds = new ArrayList<Integer>();
        newsIds.add(newsId);
    } else {
    // 获取所有被监控的新闻
    // param.setPageStart(0);
    // param.setPageSize(10000);
    // if(null != param.getEndTime()){
    // param.setEndTime(DateUtils.addSeconds(DateUtils.addDays(param.getEndTime(), 1), -1));
    // }
    // BaseQueryResult<RequestNewsShow> result = requestNewsService.pageRequestNewsEs(param);
    // List<RequestNewsShow> resultList = result.getResultList();
    // if(!CollectionUtils.isEmpty(resultList)){
    // newsIds = new ArrayList<Integer>();
    // for (RequestNewsShow requestNews : resultList) {
    // newsIds.add(requestNews.getNewsId());
    // }
    // }
    }
    // if(CollectionUtils.isEmpty(newsIds)){
    // return super.getModelMap(new HashMap<String, Long>());
    // }
    param.setReportEndTime(DateUtils.addSeconds(DateUtils.addDays(param.getEndTime(), 1), -1));
    param.setReportStartTime(param.getStartTime());
    param.setStartTime(null);
    param.setEndTime(null);
    // newsId列表
    param.setNewsIds(newsIds);
    // 转载次数按状态分组
    param.setPageStart(0);
    param.setPageSize(1);
    AggsTermParams aggsParams = new AggsTermParams("status", "status");
    param.setAggsParams(aggsParams);
    if (null != param.getEndTime()) {
        param.setEndTime(DateUtils.addSeconds(DateUtils.addDays(param.getEndTime(), 1), -1));
    }
    // List<Integer> newsIds = null;
    // if(null != newsId){
    // newsIds = new ArrayList<Integer>();
    // newsIds.add(newsId);
    // }
    // param.setNewsIds(newsIds);
    BaseQueryResult<NewsSpreadingAnalysisDetail> wr = newsSpreadingAnalysisService.pageReprintNews(param);
    Map<String, Long> aggsMap = wr.getAggsMap();
    return super.getModelMap(aggsMap);
}


@RequestMapping(value = "/findRequestNews", method = RequestMethod.GET)
public ModelMap findRequestNews(Integer newsId,Model model){
    RequestNewsEntity requestNewsEntity = requestNewsService.findById(newsId);
    RequestNewsShow rns = new RequestNewsShow();
    BeanUtils.copyProperties(requestNewsEntity, rns);
    if (StringUtils.isNotBlank(rns.getContent())) {
        rns.setWordCount(rns.getContent().replaceAll("\r", "").replaceAll("\n", "").length());
    } else {
        rns.setWordCount(0);
    }
    if (StringUtils.isNotBlank(requestNewsEntity.getPicPath())) {
        rns.setPicPath(requestNewsEntity.getPicPath().replace("${inewsImageServer}", inewsImageServer));
    }
    return super.getModelMap(rns);
}


@RequestMapping(value = "/reprintTypeRatio", method = RequestMethod.GET)
public ModelMap reprintTypeRatio(QueryNewsParam param,Integer newsId,Model model){
    List<Integer> newsIds = null;
    if (null != newsId) {
        newsIds = new ArrayList<Integer>();
        newsIds.add(newsId);
    } else {
        // 获取所有被监控的新闻
        param.setPageStart(0);
        param.setPageSize(10000);
        if (null != param.getEndTime()) {
            param.setEndTime(DateUtils.addSeconds(DateUtils.addDays(param.getEndTime(), 1), -1));
        }
        BaseQueryResult<RequestNewsShow> result = requestNewsService.pageRequestNewsEs(param);
        List<RequestNewsShow> resultList = result.getResultList();
        if (!CollectionUtils.isEmpty(resultList)) {
            newsIds = new ArrayList<Integer>();
            for (RequestNewsShow requestNews : resultList) {
                newsIds.add(requestNews.getNewsId());
            }
        }
    }
    if (CollectionUtils.isEmpty(newsIds)) {
        return super.getModelMap(new HashMap<String, Long>());
    }
    param.setStartTime(null);
    param.setEndTime(null);
    // newsId列表
    param.setNewsIds(newsIds);
    // 转载次数按状态分组
    param.setPageStart(0);
    param.setPageSize(1);
    AggsTermParams aggsParams = new AggsTermParams("reprintTypeName", "reprintTypeName");
    param.setAggsParams(aggsParams);
    if (null != param.getEndTime()) {
        param.setEndTime(DateUtils.addSeconds(DateUtils.addDays(param.getEndTime(), 1), -1));
    }
    // List<Integer> newsIds = null;
    // if(null != newsId){
    // newsIds = new ArrayList<Integer>();
    // newsIds.add(newsId);
    // }
    // param.setNewsIds(newsIds);
    BaseQueryResult<NewsSpreadingAnalysisDetail> wr = newsSpreadingAnalysisService.pageReprintNews(param);
    Map<String, Long> aggsMap = wr.getAggsMap();
    Map<String, Long> reprintTypeRatio = new HashMap<String, Long>();
    // 所有的转载类型
    Map<Integer, String> reprintTypeMap = ConstantUtil.REPRINT_TYPE_MAP;
    Collection<String> reprintTypes = reprintTypeMap.values();
    for (String reprintType : reprintTypes) {
        if (null != aggsMap.get(reprintType)) {
            reprintTypeRatio.put(reprintType, aggsMap.get(reprintType));
        } else {
            reprintTypeRatio.put(reprintType, 0L);
        }
    }
    return super.getModelMap(reprintTypeRatio);
}


@RequestMapping(value = "/getLatestUpdateTime", method = RequestMethod.GET)
public ModelMap getLatestUpdateTime(QueryNewsParam param,Model model){
    Date time = null;
    param.setPageStart(0);
    param.setPageSize(1);
    param.setSortField("createDatetime");
    param.setSortType("desc");
    BaseQueryResult<NewsSpreadingAnalysisDetail> result = newsSpreadingAnalysisService.pageReprintNews(param);
    List<NewsSpreadingAnalysisDetail> list = result.getResultList();
    if (!CollectionUtils.isEmpty(list)) {
        time = list.get(0).getCreateDatetime();
    }
    return super.getModelMap(time);
}


@RequestMapping(value = "/pageReprintNews", method = RequestMethod.GET)
public ModelMap pageReprintNews(DataTablesRequestEntity aoData,Integer newsId,QueryNewsParam param,Model model){
    DataTablesResponseEntity<NewsSpreadingAnalysisDetail> dataTables = new DataTablesResponseEntity<NewsSpreadingAnalysisDetail>();
    List<Integer> newsIds = null;
    if (null != newsId) {
        newsIds = new ArrayList<Integer>();
        newsIds.add(newsId);
    } else {
    // 获取所有被监控的新闻
    // param.setPageStart(0);
    // param.setPageSize(10000);
    // if(null != param.getEndTime()){
    // param.setEndTime(DateUtils.addSeconds(DateUtils.addDays(param.getEndTime(), 1), -1));
    // }
    // BaseQueryResult<RequestNewsShow> result = requestNewsService.pageRequestNewsEs(param);
    // List<RequestNewsShow> resultList = result.getResultList();
    // if(!CollectionUtils.isEmpty(resultList)){
    // newsIds = new ArrayList<Integer>();
    // for (RequestNewsShow requestNews : resultList) {
    // newsIds.add(requestNews.getNewsId());
    // }
    // }
    }
    // if(CollectionUtils.isEmpty(newsIds)){
    // dataTables.setiTotalDisplayRecords(0);
    // dataTables.setiTotalRecords(0);
    // dataTables.setsEcho(aoData.getsEcho());
    // dataTables.setAaData(new ArrayList<NewsSpreadingAnalysisDetail>());
    // return super.getModelMap(dataTables);
    // }
    param.setReportEndTime(DateUtils.addSeconds(DateUtils.addDays(param.getEndTime(), 1), -1));
    param.setReportStartTime(param.getStartTime());
    param.setStartTime(null);
    param.setEndTime(null);
    // newsId列表
    param.setNewsIds(newsIds);
    // 获取转载的新闻
    param.setPageStart(aoData.getiDisplayStart());
    param.setPageSize(aoData.getiDisplayLength());
    param.setSortField(super.getOrderValue(aoData));
    param.setSortType(super.getOrderType(aoData));
    BaseQueryResult<NewsSpreadingAnalysisDetail> wr = newsSpreadingAnalysisService.pageReprintNews(param);
    dataTables.setiTotalDisplayRecords(wr.getTotal());
    dataTables.setiTotalRecords(wr.getTotal());
    dataTables.setsEcho(aoData.getsEcho());
    dataTables.setAaData(wr.getResultList());
    return super.getModelMap(dataTables);
}


@RequestMapping(value = "/companyReprintSectionRanking", method = RequestMethod.GET)
public ModelMap companyReprintSectionRanking(QueryNewsParam param,Model model){
    param.setPageStart(0);
    param.setPageSize(10000);
    List<Integer> newsIds = null;
    // 获取时间范围内的原始新闻
    if (null != param.getEndTime()) {
        param.setEndTime(DateUtils.addSeconds(DateUtils.addDays(param.getEndTime(), 1), -1));
    }
    BaseQueryResult<RequestNewsShow> result = requestNewsService.pageRequestNewsEs(param);
    List<RequestNewsShow> resultList = result.getResultList();
    if (!CollectionUtils.isEmpty(resultList)) {
        newsIds = new ArrayList<Integer>();
        for (RequestNewsShow requestNews : resultList) {
            newsIds.add(requestNews.getNewsId());
        }
    }
    if (CollectionUtils.isEmpty(newsIds)) {
        return super.getModelMap(new HashMap<String, Long>());
    }
    param.setStartTime(null);
    param.setEndTime(null);
    param.setNewsIds(newsIds);
    BaseQueryResult<NewsSpreadingAnalysisDetail> wr = newsSpreadingAnalysisService.pageReprintNews(param);
    List<NewsSpreadingAnalysisDetail> reprintNews = wr.getResultList();
    newsIds = new ArrayList<Integer>();
    for (NewsSpreadingAnalysisDetail reprint : reprintNews) {
        newsIds.add(reprint.getNewsId());
    }
    if (CollectionUtils.isEmpty(newsIds)) {
        return super.getModelMap(new HashMap<String, Long>());
    }
    param = new QueryNewsParam();
    param.setPageStart(0);
    param.setPageSize(1);
    param.setNewsIds(newsIds);
    AggsTermParams aggsParams = new AggsTermParams("newsSection", "newsSection", ESConstantUtil.SORT_DESC, 10);
    param.setAggsParams(aggsParams);
    BaseQueryResult<RequestNewsShow> rns = requestNewsService.pageRequestNewsEs(param);
    Map<String, Long> aggsMap = rns.getAggsMap();
    return super.getModelMap(aggsMap);
}


@RequestMapping(value = "/listRequestNewsCode", method = RequestMethod.GET)
public ModelMap listRequestNewsCode(QueryNewsParam param,Model model){
    param.setPageStart(0);
    param.setPageSize(10000);
    param.setSortField("_score");
    param.setSortType("desc");
    BaseQueryResult<RequestNewsShow> result = requestNewsService.pageRequestNewsEs(param);
    return super.getModelMap(result.getResultList());
}


@RequestMapping(value = "/findNewsWebpage", method = RequestMethod.GET)
public ModelMap findNewsWebpage(String webpageCode,Integer newsId,Model model){
    NewsWebpageEntity webpageEntity = newsWebpageService.findByWebpageCode(webpageCode);
    ReprintNewsDetail reprintNews = new ReprintNewsDetail();
    BeanUtils.copyProperties(webpageEntity, reprintNews);
    NewsContentEntity content = newsContentService.findByWebpageCode(reprintNews.getWebpageCode());
    reprintNews.setContent(content.getContent());
    if (StringUtils.isNotBlank(content.getContent())) {
        reprintNews.setWordCount(content.getContent().replaceAll("\r", "").replaceAll("\n", "").length());
    } else {
        reprintNews.setWordCount(0);
    }
    if (StringUtils.isNotBlank(webpageEntity.getPicPath())) {
        reprintNews.setPicPath(webpageEntity.getPicPath().replace("${inewsImageServer}", inewsImageServer));
    }
    NewsSpreadingAnalysisEntity analysisEntity = newsSpreadingAnalysisService.findByNewsIdAndCode(newsId, reprintNews.getWebpageCode());
    reprintNews.setContentSimilarity(analysisEntity.getContentSimilarity());
    return super.getModelMap(reprintNews);
}


@RequestMapping(value = "/listReprintType", method = RequestMethod.GET)
public ModelMap listReprintType(Model model){
    Map<Integer, String> map = ConstantUtil.REPRINT_TYPE_MAP;
    return super.getModelMap(map);
}


@RequestMapping(value = "/tortWebsiteRanking", method = RequestMethod.GET)
public ModelMap tortWebsiteRanking(QueryNewsParam param,Integer newsId,Model model){
    List<Integer> newsIds = null;
    if (null != newsId) {
        newsIds = new ArrayList<Integer>();
        newsIds.add(newsId);
    } else {
    // 获取所有被监控的新闻
    // param.setPageStart(0);
    // param.setPageSize(10000);
    // if(null != param.getEndTime()){
    // param.setEndTime(DateUtils.addSeconds(DateUtils.addDays(param.getEndTime(), 1), -1));
    // }
    // BaseQueryResult<RequestNewsShow> result = requestNewsService.pageRequestNewsEs(param);
    // List<RequestNewsShow> resultList = result.getResultList();
    // if(!CollectionUtils.isEmpty(resultList)){
    // newsIds = new ArrayList<Integer>();
    // for (RequestNewsShow requestNews : resultList) {
    // newsIds.add(requestNews.getNewsId());
    // }
    // }
    }
    // if(CollectionUtils.isEmpty(newsIds)){
    // return super.getModelMap(new HashMap<String, Long>());
    // }
    param.setReportEndTime(DateUtils.addSeconds(DateUtils.addDays(param.getEndTime(), 1), -1));
    param.setReportStartTime(param.getStartTime());
    param.setStartTime(null);
    param.setEndTime(null);
    // newsId列表
    param.setNewsIds(newsIds);
    // 获取版权存疑网站及转载次数
    param.setPageStart(0);
    param.setPageSize(1);
    param.setStatus(0);
    AggsTermParams aggsParams = new AggsTermParams("sourceCrawl", "sourceCrawl", ESConstantUtil.SORT_DESC, 10);
    param.setAggsParams(aggsParams);
    // if(null != param.getEndTime()){
    // param.setEndTime(DateUtils.addSeconds(DateUtils.addDays(param.getEndTime(), 1), -1));
    // }
    // param.setNewsIds(newsIds);
    BaseQueryResult<NewsSpreadingAnalysisDetail> wr = newsSpreadingAnalysisService.pageReprintNews(param);
    Map<String, Long> aggsMap = wr.getAggsMap();
    return super.getModelMap(aggsMap);
}


@RequestMapping(value = "/pageRequestNews", method = RequestMethod.GET)
public ModelMap pageRequestNews(DataTablesRequestEntity aoData,QueryNewsParam param,Model model){
    DataTablesResponseEntity<RequestNewsShow> dataTables = new DataTablesResponseEntity<RequestNewsShow>();
    param.setPageStart(aoData.getiDisplayStart());
    param.setPageSize(aoData.getiDisplayLength());
    param.setSortField(super.getOrderValue(aoData));
    param.setSortType(super.getOrderType(aoData));
    if (null != param.getEndTime()) {
        param.setEndTime(DateUtils.addSeconds(DateUtils.addDays(param.getEndTime(), 1), -1));
    }
    PageResponse<RequestNewsShow> page = requestNewsService.pageRequestNews(param);
    dataTables.setiTotalDisplayRecords(page.getTotalElements());
    dataTables.setiTotalRecords(page.getTotalElements());
    dataTables.setsEcho(aoData.getsEcho());
    dataTables.setAaData(page.getContent());
    return super.getModelMap(dataTables);
}


@RequestMapping(value = "/listReprintTypeStatus", method = RequestMethod.GET)
public ModelMap listReprintTypeStatus(Model model){
    Map<Integer, String> map = ConstantUtil.REPRINT_STATUS_MAP;
    return super.getModelMap(map);
}


@RequestMapping(value = "/reprintNumRanking", method = RequestMethod.GET)
public ModelMap reprintNumRanking(QueryNewsParam param,Integer newsId,Model model){
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Map<String, Map<String, Long>> map = new HashMap<String, Map<String, Long>>();
    param.setPageStart(0);
    param.setPageSize(1);
    List<Integer> newsIds = null;
    if (null == newsId) {
        throw new RequestParamException(new String[] { "newsId" });
    }
    newsIds = new ArrayList<Integer>();
    newsIds.add(newsId);
    // 获取发布日期    统计1周
    Integer dayNum = 7;
    RequestNewsEntity entity = requestNewsService.findById(newsId);
    Date reportDatetime = entity.getReportDatetime();
    if (null != reportDatetime) {
        param.setStartTime(reportDatetime);
        param.setEndTime(DateUtils.addDays(reportDatetime, dayNum));
    }
    param.setNewsIds(newsIds);
    // 全部
    BaseQueryResult<NewsSpreadingAnalysisDetail> total = newsSpreadingAnalysisService.countByDate(param);
    Map<String, Long> totalMap = total.getAggsMap();
    Map<String, Long> totalLinkedMap = new LinkedHashMap<String, Long>();
    if (totalMap.size() < dayNum) {
        for (int i = 0; i < dayNum; i++) {
            String date = sdf.format(DateUtils.addDays(reportDatetime, i));
            if (!totalMap.containsKey(date)) {
                totalLinkedMap.put(date, 0L);
            } else {
                totalLinkedMap.put(date, totalMap.get(date));
            }
        }
    }
    map.put("total", totalLinkedMap);
    // 版权存疑
    param.setStatus(0);
    BaseQueryResult<NewsSpreadingAnalysisDetail> tort = newsSpreadingAnalysisService.countByDate(param);
    Map<String, Long> tortMap = tort.getAggsMap();
    Map<String, Long> tortLinkedMap = new LinkedHashMap<String, Long>();
    if (tortMap.size() < dayNum) {
        for (int i = 0; i < dayNum; i++) {
            String date = sdf.format(DateUtils.addDays(reportDatetime, i));
            if (!tortMap.containsKey(date)) {
                tortLinkedMap.put(date, 0L);
            } else {
                tortLinkedMap.put(date, tortMap.get(date));
            }
        }
    }
    map.put("tort", tortLinkedMap);
    // 合法转载
    param.setStatus(1);
    BaseQueryResult<NewsSpreadingAnalysisDetail> legal = newsSpreadingAnalysisService.countByDate(param);
    Map<String, Long> legalMap = legal.getAggsMap();
    Map<String, Long> legalLinkedMap = new LinkedHashMap<String, Long>();
    if (legalMap.size() < dayNum) {
        for (int i = 0; i < dayNum; i++) {
            String date = sdf.format(DateUtils.addDays(reportDatetime, i));
            if (!legalMap.containsKey(date)) {
                legalLinkedMap.put(date, 0L);
            } else {
                legalLinkedMap.put(date, legalMap.get(date));
            }
        }
    }
    map.put("legal", legalLinkedMap);
    return super.getModelMap(map);
}


@RequestMapping(value = "/reprintWebsiteRanking", method = RequestMethod.GET)
public ModelMap reprintWebsiteRanking(QueryNewsParam param,Model model){
    // 获取所有被监控的新闻
    param.setPageStart(0);
    param.setPageSize(10000);
    if (null != param.getEndTime()) {
        param.setReportEndTime(DateUtils.addSeconds(DateUtils.addDays(param.getEndTime(), 1), -1));
    }
    param.setReportStartTime(param.getStartTime());
    param.setStartTime(null);
    param.setEndTime(null);
    // BaseQueryResult<RequestNewsShow> result = requestNewsService.pageRequestNewsEs(param);
    // List<RequestNewsShow> resultList = result.getResultList();
    // List<Integer> newsIds = null;
    // if(!CollectionUtils.isEmpty(resultList)){
    // newsIds = new ArrayList<Integer>();
    // for (RequestNewsShow requestNews : resultList) {
    // newsIds.add(requestNews.getNewsId());
    // }
    // }
    // 
    // if(CollectionUtils.isEmpty(newsIds)){
    // return super.getModelMap(new HashMap<String, Long>());
    // }
    // newsId列表
    // param.setNewsIds(newsIds);
    // param.setStartTime(null);
    // param.setEndTime(null);
    // 获取转载网站及转载次数
    param.setPageStart(0);
    param.setPageSize(1);
    AggsTermParams aggsParams = new AggsTermParams("sourceCrawl", "sourceCrawl", ESConstantUtil.SORT_DESC, 10);
    param.setAggsParams(aggsParams);
    if (null != param.getEndTime()) {
        param.setEndTime(DateUtils.addSeconds(DateUtils.addDays(param.getEndTime(), 1), -1));
    }
    BaseQueryResult<NewsSpreadingAnalysisDetail> wr = newsSpreadingAnalysisService.pageReprintNews(param);
    Map<String, Long> aggsMap = wr.getAggsMap();
    return super.getModelMap(aggsMap);
}


}