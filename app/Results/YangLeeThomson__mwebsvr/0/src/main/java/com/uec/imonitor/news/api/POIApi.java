package com.uec.imonitor.news.api;
 import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSON;
import com.uec.imonitor.common.base.BaseController;
import com.uec.imonitor.common.base.PageResponse;
import com.uec.imonitor.common.datatables.DataTablesRequestEntity;
import com.uec.imonitor.common.datatables.DataTablesResponseEntity;
import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.dic.bean.WebsiteDicEntity;
import com.uec.imonitor.dic.service.IWebsiteDicService;
import com.uec.imonitor.es.bean.result.BaseQueryResult;
import com.uec.imonitor.news.bean.NewsSpreadingAnalysisDetail;
import com.uec.imonitor.news.bean.NewsSpreadingAnalysisEntity;
import com.uec.imonitor.news.bean.NewsWebpageEntity;
import com.uec.imonitor.news.bean.QueryNewsParam;
import com.uec.imonitor.news.service.INewsSpreadingAnalysisService;
import com.uec.imonitor.news.service.INewsWebpageService;
import com.uec.imonitor.news.service.IRequestNewsPoiService;
import com.uec.imonitor.news.utils.ExcelUtil;
import com.uec.imonitor.news.utils.Py4j;
import com.uec.imonitor.request.bean.RequestNewsEntity;
import com.uec.imonitor.request.bean.RequestNewsShow;
import com.uec.imonitor.request.bean.RequestSiteEntity;
import com.uec.imonitor.request.service.IRequestNewsService;
import com.uec.imonitor.request.service.IRequestSiteService;
import com.uec.imonitor.Interface.IRequestNewsPoiService;
import com.uec.imonitor.Interface.IRequestSiteService;
import com.uec.imonitor.Interface.IWebsiteDicService;
import com.uec.imonitor.DTO.WebsiteDicEntity;
import com.uec.imonitor.DTO.Py4j;
@Scope("prototype")
@RestController
@RequestMapping(value = "/{tenantMark}/api/excel")
public class POIApi extends BaseController{

 private  Logger logger;

@Autowired
@Qualifier("requestNewsService")
 private  IRequestNewsService requestNewsService;

@Autowired
@Qualifier("requestNewsPoiService")
 private  IRequestNewsPoiService requestNewsPoiService;

@Autowired
@Qualifier("newsSpreadingAnalysisService")
 private  INewsSpreadingAnalysisService newsSpreadingAnalysisService;

@Autowired
@Qualifier("newsWebpageService")
 private  INewsWebpageService newsWebpageService;

@Autowired
@Qualifier("requestSiteService")
 private  IRequestSiteService requestSiteService;

@Autowired
@Qualifier("websiteDicService")
 private  IWebsiteDicService websiteDicService;

@Value("${inews.image.server.address}")
 private  String inewsImageServer;


public int compare(String obj1,String obj2){
    return obj1.compareTo(obj2);
}


@ResponseBody
@RequestMapping(value = "/getcompnaysource")
public ModelMap getCompnaySource(){
    List<String> queryResult = new ArrayList<String>();
    List<WebsiteDicEntity> websiteDicEntitiesTmp = new ArrayList<WebsiteDicEntity>();
    Integer request_id = 1;
    List<RequestSiteEntity> requestSiteEntities = requestSiteService.findByRequestId(request_id);
    for (RequestSiteEntity requestSiteEntity : requestSiteEntities) {
        WebsiteDicEntity websiteDicEntity = websiteDicService.findById(requestSiteEntity.getSiteId());
        websiteDicEntitiesTmp.add(websiteDicEntity);
    }
    List<WebsiteDicEntity> websiteDicEntities = new ArrayList<>(new HashSet<>(websiteDicEntitiesTmp));
    StringBuffer buffer = new StringBuffer();
    Py4j py4j = new Py4j();
    for (WebsiteDicEntity websiteDicEntity : websiteDicEntities) {
        buffer.append("@").append(py4j.getPinyin(websiteDicEntity.getName())).append("|").append(websiteDicEntity.getName()).append("|").append(websiteDicEntity.getWebsiteId()).append("|").append(py4j.stringChange(websiteDicEntity.getName())).append("|");
    }
    queryResult.add(buffer.toString());
    // 获取所有城市列表，拼音排序
    Map<String, String> cityMap = new TreeMap<String, String>(new Comparator<String>() {

        public int compare(String obj1, String obj2) {
            return obj1.compareTo(obj2);
        }
    });
    for (WebsiteDicEntity websiteDicEntity : websiteDicEntities) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("@").append(websiteDicEntity.getWebsiteId()).append("|").append(websiteDicEntity.getName());
        String flag = py4j.getPinyin(websiteDicEntity.getName()).substring(0, 1);
        if ("1".contains(flag)) {
            flag = "Y";
        }
        if ("2".contains(flag)) {
            flag = "E";
        }
        if ("3".contains(flag)) {
            flag = "S";
        }
        if ("4".contains(flag)) {
            flag = "S";
        }
        if ("5".contains(flag)) {
            flag = "W";
        }
        if ("6".contains(flag)) {
            flag = "L";
        }
        if ("7".contains(flag)) {
            flag = "Q";
        }
        if ("8".contains(flag)) {
            flag = "B";
        }
        if ("9".contains(flag)) {
            flag = "J";
        }
        if ("0".contains(flag)) {
            flag = "L";
        }
        String cityMapKey = cityMap.get(flag);
        if (null == cityMapKey) {
            cityMap.put(flag, stringBuffer.toString());
        } else {
            cityMap.put(flag, cityMap.get(flag).concat(stringBuffer.toString()));
        }
    }
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append("{");
    stringBuffer.append("\"热门\": \"@8|新浪@286|凤凰网@294|今日头条@297|网易@295|腾讯@296|搜狐\",");
    Set<String> keySet = cityMap.keySet();
    Iterator<String> iter = keySet.iterator();
    while (iter.hasNext()) {
        String key = iter.next();
        stringBuffer.append("'").append(key).append("':").append("\"").append(cityMap.get(key)).append("\",");
    }
    stringBuffer.append("}");
    queryResult.add(stringBuffer.toString());
    return super.getModelMap(queryResult);
}


@RequestMapping(value = "/exportexcel", method = RequestMethod.GET)
public String exportexcel(String selectedData,String fileName,String startTime,String endTime){
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date startTimeDate = sdf.parse(startTime);
    Date endTimeDate = sdf.parse(endTime);
    String[] selectData = selectedData.split(",");
    List<String> code = new ArrayList<String>();
    for (int i = 0; i < selectData.length; i++) {
        String string = selectData[i];
        if (!string.isEmpty() && StringUtils.isNumeric(string)) {
            code.add(string);
        }
    }
    List<RequestNewsShow> content = new ArrayList<RequestNewsShow>();
    if (null != code && code.size() > 0) {
        for (String string : code) {
            RequestNewsShow requestNewsShow = new RequestNewsShow();
            RequestNewsEntity requestNewsEntity = requestNewsService.findById(Integer.parseInt(string));
            requestNewsShow.setNewsSection(requestNewsEntity.getNewsSection());
            requestNewsShow.setTitle(requestNewsEntity.getTitle());
            requestNewsShow.setStartDatetime(requestNewsEntity.getStartDatetime());
            requestNewsShow.setNewsAuthor(requestNewsEntity.getNewsAuthor());
            List<NewsSpreadingAnalysisEntity> newsIdCount = newsSpreadingAnalysisService.findByNewsId(requestNewsEntity.getNewsId());
            requestNewsShow.setReprintCount(newsIdCount.size());
            content.add(requestNewsShow);
        }
    } else {
        QueryNewsParam param = new QueryNewsParam();
        param.setStartTime(startTimeDate);
        param.setEndTime(endTimeDate);
        param.setPageStart(0);
        param.setSortField("reprintCount");
        param.setSortType("desc");
        if (null != param.getEndTime()) {
            param.setEndTime(DateUtils.addSeconds(DateUtils.addDays(param.getEndTime(), 1), -1));
        }
        param.setReportStartTime(param.getStartTime());
        param.setPageSize(10000);
        PageResponse<RequestNewsShow> page = requestNewsService.pageRequestNews(param);
        content = page.getContent();
    }
    ExcelUtil excelUtil = new ExcelUtil();
    excelUtil.exportExcel(content, request, response, fileName);
    return null;
}


@RequestMapping(value = "/latestReprint", method = RequestMethod.GET)
public String latestReprint(String selectedData,String fileName,String startTime,String endTime,Integer reprintType,Integer status){
    if (null != reprintType && !reprintType.equals("")) {
        if (reprintType == 1) {
            fileName = fileName + "网站转载" + "-";
        } else if (reprintType == 2) {
            fileName = fileName + "微博转载" + "-";
        } else if (reprintType == 3) {
            fileName = fileName + "微信转载" + "-";
        } else if (reprintType == 4) {
            fileName = fileName + "APP转载" + "-";
        }
    } else {
        fileName = fileName + "全部转载" + "-";
    }
    if (null != status && !status.equals("")) {
        if (status == 0) {
            fileName = fileName + "疑似侵权" + "-";
        } else if (status == 1) {
            fileName = fileName + "标注来源" + "-";
        }
    } else {
        fileName = fileName + "全部情况" + "-";
    }
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date startTimeDate = sdf.parse(startTime);
    Date endTimeDate = sdf.parse(endTime);
    String[] selectData = selectedData.split(",");
    List<String> code = new ArrayList<String>();
    for (int i = 0; i < selectData.length; i++) {
        String string = selectData[i];
        if (!string.isEmpty()) {
            code.add(string);
        }
    }
    List<NewsSpreadingAnalysisDetail> content = new ArrayList<NewsSpreadingAnalysisDetail>();
    QueryNewsParam param = new QueryNewsParam();
    param.setPageStart(0);
    param.setReprintType(reprintType);
    param.setStatus(status);
    param.setStartTime(startTimeDate);
    param.setEndTime(endTimeDate);
    // if(null != param.getEndTime()){
    // param.setEndTime(DateUtils.addSeconds(DateUtils.addDays(param.getEndTime(), 1), -1));
    // }
    if (null != code && code.size() > 0) {
        // List<Integer> newsIds = null;
        List<Integer> code1 = new ArrayList<Integer>();
        for (int i = 0; i < selectData.length; i++) {
            String string = selectData[i];
            if (!string.isEmpty()) {
                List<NewsSpreadingAnalysisEntity> newsSpreadingAnalysisEntities = newsSpreadingAnalysisService.findByWebpageCode(string);
                code1.add(newsSpreadingAnalysisEntities.get(0).getNewsId());
            }
        }
        param.setNewsIds(code1);
        param.setSortField("createDatetime");
        param.setSortType("desc");
        param.setPageSize(10000);
        param.setPageStart(0);
        BaseQueryResult<NewsSpreadingAnalysisDetail> wr = newsSpreadingAnalysisService.pageReprintNews(param);
        List<NewsSpreadingAnalysisDetail> resultList = wr.getResultList();
        List<NewsSpreadingAnalysisDetail> tmp = new ArrayList<NewsSpreadingAnalysisDetail>();
        for (NewsSpreadingAnalysisDetail newsSpreadingAnalysisDetail2 : resultList) {
            if (code.contains(newsSpreadingAnalysisDetail2.getWebpageCode())) {
                tmp.add(newsSpreadingAnalysisDetail2);
            }
        }
        content = tmp;
    } else {
        if (null != param.getEndTime()) {
            param.setReportEndTime(DateUtils.addSeconds(DateUtils.addDays(param.getEndTime(), 1), -1));
        }
        param.setReportStartTime(param.getStartTime());
        param.setStartTime(null);
        param.setEndTime(null);
        // 获取转载的新闻
        param.setPageStart(0);
        param.setPageSize(10000);
        // param.setSortField("createDatetime");
        // param.setSortType("desc");
        BaseQueryResult<NewsSpreadingAnalysisDetail> wr = newsSpreadingAnalysisService.pageReprintNews(param);
        List<NewsSpreadingAnalysisDetail> resultList2 = wr.getResultList();
        for (NewsSpreadingAnalysisDetail newsSpreadingAnalysisDetail : resultList2) {
            content.add(newsSpreadingAnalysisDetail);
        }
    }
    ExcelUtil excelUtil = new ExcelUtil();
    excelUtil.latestReprint(content, request, response, fileName);
    return null;
}


@RequestMapping(value = "/articlereprintpio", method = RequestMethod.GET)
public String articlereprintpio(String selectedData,String fileName,String startTime,String endTime,Integer reprintType,Integer status,Integer newsId){
    if (null != reprintType && !reprintType.equals("")) {
        if (reprintType == 1) {
            fileName = fileName + "网站转载" + "-";
        } else if (reprintType == 2) {
            fileName = fileName + "微博转载" + "-";
        } else if (reprintType == 3) {
            fileName = fileName + "微信转载" + "-";
        } else if (reprintType == 4) {
            fileName = fileName + "APP转载" + "-";
        }
    } else {
        fileName = fileName + "全部转载" + "-";
    }
    if (null != status && !status.equals("")) {
        if (status == 0) {
            fileName = fileName + "疑似侵权" + "-";
        } else if (status == 1) {
            fileName = fileName + "标注来源" + "-";
        }
    } else {
        fileName = fileName + "全部情况" + "-";
    }
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date startTimeDate = sdf.parse(startTime);
    Date endTimeDate = sdf.parse(endTime);
    String[] selectData = selectedData.split(",");
    List<String> code = new ArrayList<String>();
    for (int i = 0; i < selectData.length; i++) {
        String string = selectData[i];
        if (!string.isEmpty() && !string.equals("on")) {
            code.add(string);
        }
    }
    List<NewsSpreadingAnalysisDetail> content = new ArrayList<NewsSpreadingAnalysisDetail>();
    QueryNewsParam param = new QueryNewsParam();
    param.setPageStart(0);
    param.setReprintType(reprintType);
    param.setStatus(status);
    if (null != code && code.size() > 0) {
        List<Integer> newsIds = null;
        List<Integer> code1 = new ArrayList<Integer>();
        for (int i = 0; i < selectData.length; i++) {
            String string = selectData[i];
            if (!string.isEmpty() && !string.equals("on")) {
                List<NewsSpreadingAnalysisEntity> newsSpreadingAnalysisEntities = newsSpreadingAnalysisService.findByWebpageCode(string);
                code1.add(newsSpreadingAnalysisEntities.get(0).getNewsId());
            }
        }
        param.setNewsIds(code1);
        param.setSortField("createDatetime");
        param.setSortType("desc");
        param.setPageSize(10000);
        param.setPageStart(0);
        BaseQueryResult<NewsSpreadingAnalysisDetail> wr = newsSpreadingAnalysisService.pageReprintNews(param);
        List<NewsSpreadingAnalysisDetail> resultList = wr.getResultList();
        List<NewsSpreadingAnalysisDetail> tmp = new ArrayList<NewsSpreadingAnalysisDetail>();
        for (NewsSpreadingAnalysisDetail newsSpreadingAnalysisDetail2 : resultList) {
            if (code.contains(newsSpreadingAnalysisDetail2.getWebpageCode())) {
                tmp.add(newsSpreadingAnalysisDetail2);
            }
        }
        content = tmp;
    } else {
        List<Integer> newsIds = new ArrayList<Integer>();
        newsIds.add(newsId);
        param.setNewsIds(newsIds);
        param.setStartTime(null);
        param.setEndTime(null);
        param.setSortField("createDatetime");
        param.setSortType("desc");
        param.setPageStart(0);
        param.setPageSize(10000);
        BaseQueryResult<NewsSpreadingAnalysisDetail> wr = newsSpreadingAnalysisService.pageReprintNews(param);
        List<NewsSpreadingAnalysisDetail> resultList = wr.getResultList();
        content = resultList;
    }
    ExcelUtil excelUtil = new ExcelUtil();
    excelUtil.latestReprint(content, request, response, fileName);
    return null;
}


@RequestMapping(value = "/unitrepropoi", method = RequestMethod.GET)
public String unitrepropoi(String selectedData,String fileName,String startTime,String endTime,Integer reprintType,Integer status,String sourceCrawl){
    if (null != reprintType && !reprintType.equals("")) {
        if (reprintType == 1) {
            fileName = fileName + "网站转载" + "-";
        } else if (reprintType == 2) {
            fileName = fileName + "微博转载" + "-";
        } else if (reprintType == 3) {
            fileName = fileName + "微信转载" + "-";
        } else if (reprintType == 4) {
            fileName = fileName + "APP转载" + "-";
        }
    } else {
        fileName = fileName + "全部转载" + "-";
    }
    if (null != status && !status.equals("")) {
        if (status == 0) {
            fileName = fileName + "疑似侵权" + "-";
        } else if (status == 1) {
            fileName = fileName + "标注来源" + "-";
        }
    } else {
        fileName = fileName + "全部情况" + "-";
    }
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date startTimeDate = sdf.parse(startTime);
    Date endTimeDate = sdf.parse(endTime);
    String[] selectData = selectedData.split(",");
    List<String> code = new ArrayList<String>();
    for (int i = 0; i < selectData.length; i++) {
        String string = selectData[i];
        if (!string.isEmpty() && !string.equals("on")) {
            code.add(string);
        }
    }
    List<NewsSpreadingAnalysisDetail> content = new ArrayList<NewsSpreadingAnalysisDetail>();
    QueryNewsParam param = new QueryNewsParam();
    param.setPageStart(0);
    param.setReprintType(reprintType);
    param.setStatus(status);
    param.setSourceCrawl(sourceCrawl);
    if (null != code && code.size() > 0) {
        List<Integer> newsIds = null;
        List<Integer> code1 = new ArrayList<Integer>();
        for (int i = 0; i < selectData.length; i++) {
            String string = selectData[i];
            if (!string.isEmpty() && !string.equals("on")) {
                List<NewsSpreadingAnalysisEntity> newsSpreadingAnalysisEntities = newsSpreadingAnalysisService.findByWebpageCode(string);
                code1.add(newsSpreadingAnalysisEntities.get(0).getNewsId());
            }
        }
        param.setNewsIds(code1);
        param.setSortField("createDatetime");
        param.setSortType("desc");
        param.setPageSize(10000);
        param.setPageStart(0);
        BaseQueryResult<NewsSpreadingAnalysisDetail> wr = newsSpreadingAnalysisService.pageReprintNews(param);
        List<NewsSpreadingAnalysisDetail> resultList = wr.getResultList();
        List<NewsSpreadingAnalysisDetail> tmp = new ArrayList<NewsSpreadingAnalysisDetail>();
        for (NewsSpreadingAnalysisDetail newsSpreadingAnalysisDetail2 : resultList) {
            if (code.contains(newsSpreadingAnalysisDetail2.getWebpageCode())) {
                tmp.add(newsSpreadingAnalysisDetail2);
            }
        }
        content = tmp;
    } else {
        param.setStartTime(startTimeDate);
        param.setEndTime(endTimeDate);
        List<Integer> newsIds = new ArrayList<Integer>();
        // 获取所有被监控的新闻
        // if(null != param.getEndTime()){
        // param.setEndTime(DateUtils.addSeconds(DateUtils.addDays(param.getEndTime(), 1), -1));
        // }
        if (null != param.getEndTime()) {
            param.setReportEndTime(DateUtils.addSeconds(DateUtils.addDays(param.getEndTime(), 1), -1));
        }
        param.setReportStartTime(param.getStartTime());
        param.setSortField("createDatetime");
        param.setSortType("desc");
        param.setPageSize(10000);
        param.setPageStart(0);
        int num = 300;
        int t = (newsIds.size() - 1) / num + 1;
        for (int i = 0; i < t; i++) {
            List<Integer> tempList = new ArrayList<>();
            if (i < t - 1) {
                // 截取前analysisNum条
                tempList = newsIds.subList(i * num, (i + 1) * num);
            } else {
                // 截取前analysisNum条
                tempList = newsIds.subList(i * num, newsIds.size());
            }
            param.setNewsIds(tempList);
            BaseQueryResult<NewsSpreadingAnalysisDetail> wr = newsSpreadingAnalysisService.pageReprintNews(param);
            List<NewsSpreadingAnalysisDetail> resultList2 = wr.getResultList();
            for (NewsSpreadingAnalysisDetail newsSpreadingAnalysisDetail : resultList2) {
                content.add(newsSpreadingAnalysisDetail);
            }
        }
    }
    ExcelUtil excelUtil = new ExcelUtil();
    excelUtil.latestReprint(content, request, response, fileName);
    return null;
}


}