package com.uec.imonitor.peopledaily.service.impl;
 import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import com.uec.imonitor.common.util.ConstantUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.map.LinkedMap;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.fastjson.JSON;
import com.uec.imonitor.common.datatables.DataTablesRequestEntity;
import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.common.util.CommonUtil;
import com.uec.imonitor.news.utils.TxtUtil;
import com.uec.imonitor.peopledaily.bean.PeoplesDaily;
import com.uec.imonitor.peopledaily.bean.PeoplesDailyEntity;
import com.uec.imonitor.peopledaily.bean.PeoplesDailyImg;
import com.uec.imonitor.peopledaily.bean.PeoplesDailyImgEntity;
import com.uec.imonitor.peopledaily.bean.PeoplesDailyRelated;
import com.uec.imonitor.peopledaily.bean.PeoplesDailyRelatedEntity;
import com.uec.imonitor.peopledaily.bean.PeoplesDailyVideos;
import com.uec.imonitor.peopledaily.bean.PeoplesDailyVideosEntity;
import com.uec.imonitor.peopledaily.controller.HttpManager;
import com.uec.imonitor.peopledaily.dao.IPeoplesDailyImgJPARepository;
import com.uec.imonitor.peopledaily.dao.IPeoplesDailyJPARepository;
import com.uec.imonitor.peopledaily.dao.IPeoplesDailyRelatedJPARepository;
import com.uec.imonitor.peopledaily.dao.IPeoplesDailyVideosJPARepository;
import com.uec.imonitor.peopledaily.service.IPeoplesDailyService;
import com.uec.imonitor.DTO.DataTablesRequestEntity;
@Service("peoplesDailyService")
@Transactional(value = "transactionManager")
public class PeoplesDailyServiceImpl implements IPeoplesDailyService{

 private  Logger logger;

@Autowired
 private  IPeoplesDailyImgJPARepository peoplesDailyImgJPARepository;

@Autowired
 private  IPeoplesDailyJPARepository peoplesDailyJPARepository;

@Autowired
 private  IPeoplesDailyRelatedJPARepository peoplesDailyRelatedJPARepository;

@Autowired
 private  IPeoplesDailyVideosJPARepository peoplesDailyVideosJPARepository;

@Value("${peopleDaily.api.pushCount}")
 private  Integer APIPushCount;

@Value("${peopleDaily.imedia.pushCount}")
 private  Integer ImediaPushCount;

@Value("${peopleDaily.pushStatus}")
 private  Integer pushStatus;

@Value("${peopleDaily.ManualCheckStatus}")
 private  Integer ManualCheckStatus;

@Value("${peopleDaily.pushAlreadyStatus}")
 private  Integer pushAlreadyStatus;

@Value("${peopleDaily.failPushStatus}")
 private  Integer failPushStatus;

@Value("${peopleDaily.failContentStatus}")
 private  Integer failContentStatus;

@Value("${peopleDaily.isCore}")
 private  Integer isCore;

@Value("${apicloud.url}")
 private  String postUrlAPICloud;

@Value("${imedia.url}")
 private  String postUrlImedia;

@Value("${news.org.imedia}")
 private  String imedia;

@Value("${pushMediaFlag}")
 private  Integer pushMediaFlag;

@Value("${pushSleep}")
 private  Integer pushSleep;

@Value("${peopleDaily.verifyArticle}")
 private  Integer verifyArticle;

@Value("${peopleDaily.verifing}")
 private  Integer verifing;

@Value("${peopleDaily.api.unverify}")
 private  String unverifyAPI;

@Value("${peopleDaily.imedia.verify}")
 private  String verifyImedia;

@Value("${peopleDaily.PushingStatus}")
 private  Integer pushingStatus;

@Value("${peopleDaily.mergeChannel}")
 private  String mergeChannel;

@Value("${peopleDaily.mergedChannel}")
 private  String mergedChannel;

 public  Map<String,String> channel_map;

 public  List<String> PEOPLES_DAILY_MEDIA;


public Map<String,String> pushDataChannelHour(){
    Map<String, String> resultMap = new LinkedMap();
    List<Object> objList = peoplesDailyJPARepository.pushDataChannelHour();
    if (!CollectionUtils.isEmpty(objList)) {
        resultMap.put("推送的数据：当前时间一个小时内,Channel", "Channel分组");
        resultMap = transforMap(objList, resultMap);
    }
    return resultMap;
}


public Page<PeoplesDailyEntity> pagePeopleNews(DataTablesRequestEntity aoData){
    Pageable pageable = new PageRequest(aoData.getiDisplayStart(), aoData.getiDisplayLength());
    Page<PeoplesDailyEntity> pageList = peoplesDailyJPARepository.findAll(new Specification<PeoplesDailyEntity>() {

        @Override
        public Predicate toPredicate(Root<PeoplesDailyEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(cb.notEqual(root.get("status"), ManualCheckStatus));
            return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        }
    }, pageable);
    return pageList;
}


public String pushDataToImedia(List<PeoplesDaily> contentList,String postUrl){
    try {
        com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject();
        jsonObject.put("content", contentList);
        String infor = jsonObject.toString();
        // JSONObject jsonObject = new JSONObject();
        // jsonObject.put("content", contentList);
        // String infor = jsonObject.toString();
        PostMethod method = null;
        // try {
        HttpClient httpClient = new HttpClient();
        method = new PostMethod(postUrl);
        RequestEntity requestEntity = new StringRequestEntity(infor, "application/json", "UTF-8");
        method.setRequestEntity(requestEntity);
        httpClient.executeMethod(method);
        return method.getResponseBodyAsString();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}


public PeoplesDailyEntity peoplesDailyOpera(String webpageCode){
    PeoplesDailyEntity peoplesDailyEntity = peoplesDailyJPARepository.findByWebpageCode(webpageCode);
    peoplesDailyEntity.setStatus(ManualCheckStatus);
    return peoplesDailyJPARepository.save(peoplesDailyEntity);
}


public Map<String,String> pushDataOrgHour(){
    Map<String, String> resultMap = new LinkedMap();
    List<Object> objList = peoplesDailyJPARepository.pushDataOrgHour();
    if (!CollectionUtils.isEmpty(objList)) {
        resultMap.put("推送的数据：当前时间一个小时内,ORG", "ORG分组");
        resultMap = transforMap(objList, resultMap);
    }
    return resultMap;
}


public PeoplesDailyEntity verifyArticle(String webpageCode){
    PeoplesDailyEntity peoplesDailyEntity = peoplesDailyJPARepository.findByWebpageCode(webpageCode);
    if (null != peoplesDailyEntity) {
        peoplesDailyEntity.setStatus(verifyArticle);
        return peoplesDailyJPARepository.save(peoplesDailyEntity);
    } else {
        logger.error("根据webpagecode查询不到相应新闻，webpagecode为:" + webpageCode);
        return null;
    }
}


public String generateWebpageCode(PeoplesDaily peoplesDaily){
    String tmp = "";
    if (null != peoplesDaily.getSource() && !StringUtils.isEmpty(peoplesDaily.getSource())) {
        tmp = tmp + peoplesDaily.getSource();
    }
    if (null != peoplesDaily.getTitle() && !StringUtils.isEmpty(peoplesDaily.getTitle())) {
        tmp = tmp + peoplesDaily.getTitle();
    }
    if (null != peoplesDaily.getChannel() && !StringUtils.isEmpty(peoplesDaily.getChannel())) {
        tmp = tmp + peoplesDaily.getChannel();
    }
    if (null != peoplesDaily.getOrg() && !StringUtils.isEmpty(peoplesDaily.getOrg())) {
        tmp = tmp + peoplesDaily.getOrg();
    }
    return this.hashKeyForDisk(tmp);
}


public void dataToImediaTest(){
    logger.info("Imedia推送数据定时任务，Imedia,当前时间：" + new Date());
    try {
        List<PeoplesDailyEntity> peoplesDailyEntityListMedia = this.pageQueryForMediaTest();
        List<PeoplesDaily> contentListImedia = new ArrayList<>();
        if (!CollectionUtils.isEmpty(peoplesDailyEntityListMedia)) {
            for (PeoplesDailyEntity peoplesDailyEntity : peoplesDailyEntityListMedia) {
                String channel = peoplesDailyEntity.getChannel();
                PeoplesDaily parseObject = JSON.parseObject(peoplesDailyEntity.getRequestBody(), PeoplesDaily.class);
                if (null != channel && !StringUtils.isEmpty(channel)) {
                    parseObject.setChannel(channel);
                }
                if (null == peoplesDailyEntity.getSummary() || StringUtils.isEmpty(peoplesDailyEntity.getSummary())) {
                    parseObject.setSummary("");
                }
                String content = parseObject.getContent();
                if (null != content && !StringUtils.isEmpty(content)) {
                    content = content.replaceAll("<content>", "");
                    content = content.replaceAll("</content>", "");
                    parseObject.setContent(content);
                }
                parseObject.setNews_id(peoplesDailyEntity.getWebpageCode());
                // imedia截取ssummary
                String summary = parseObject.getSummary();
                if (StringUtils.isNoneBlank(summary)) {
                    logger.info("summary原始摘要:" + summary);
                    summary = summary.length() < 200 ? summary : summary.substring(0, 200);
                    logger.info("summary处理之后摘要:" + summary);
                    parseObject.setSummary(summary);
                }
                contentListImedia.add(parseObject);
                peoplesDailyEntity.setIsDelete(pushAlreadyStatus);
                peoplesDailyJPARepository.save(peoplesDailyEntity);
            }
        }
        // 推送
        if (!CollectionUtils.isEmpty(contentListImedia)) {
            try {
                String pushDataToImediaResult = this.pushDataToImedia(contentListImedia, postUrlImedia);
                logger.info("URLImedia 推送,Imedia接口返回信息：" + pushDataToImediaResult);
                String stringImedia = "";
                if (null != pushDataToImediaResult && !StringUtils.isEmpty(pushDataToImediaResult)) {
                    try {
                        JSONArray jsonArray = new JSONArray(pushDataToImediaResult);
                        for (int j = 0; j < jsonArray.length(); j++) {
                            Integer errorCode = jsonArray.getJSONObject(j).getInt("errorCode");
                            String errorMsg = jsonArray.getJSONObject(j).getString("errorMsg");
                            String newsId = jsonArray.getJSONObject(j).getString("newsId");
                            PeoplesDailyEntity byWebpageCode = this.findByWebpageCode(newsId);
                            switch(errorCode) {
                                case // OK
                                0:
                                    logger.info("推送成功---Imedia数据时间为：" + new Date() + "成功信息" + errorMsg + "webpagecode为：" + newsId);
                                    byWebpageCode.setIsDelete(pushAlreadyStatus);
                                    peoplesDailyJPARepository.save(byWebpageCode);
                                    break;
                                case // Analysis error!
                                1:
                                    logger.error("推送失败---Imedia数据时间为：" + new Date() + "失败信息" + errorMsg + "webpagecode为：" + newsId);
                                    this.pushSingleNewsMedia(newsId);
                                    break;
                                case // Request body is empty!
                                2:
                                    logger.error("推送失败---Imedia数据时间为：" + new Date() + "失败信息" + errorMsg + "webpagecode为：" + newsId);
                                    this.pushSingleNewsMedia(newsId);
                                    break;
                                case // News Repeat
                                3:
                                    logger.error("推送失败---Imedia数据时间为：" + new Date() + "失败信息" + errorMsg + "webpagecode为：" + newsId);
                                    byWebpageCode.setIsDelete(pushAlreadyStatus);
                                    peoplesDailyJPARepository.save(byWebpageCode);
                                    break;
                                default:
                                    logger.error("推送失败---Imedia数据时间为：" + new Date() + "失败信息" + errorMsg + "webpagecode为：" + newsId);
                                    this.pushSingleNewsMedia(newsId);
                                    break;
                            }
                        }
                    } catch (JSONException e) {
                        List<String> list = this.setStatus(peoplesDailyEntityListMedia);
                        logger.error(e + "");
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                logger.error("推送Imedia异常信息：" + e);
                List<String> list = this.setStatus(peoplesDailyEntityListMedia);
                e.printStackTrace();
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}


public String bytesToHexString(byte[] bytes){
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < bytes.length; i++) {
        String hex = Integer.toHexString(0xFF & bytes[i]);
        if (hex.length() == 1) {
            sb.append('0');
        }
        sb.append(hex);
    }
    return sb.toString();
}


public List<PeoplesDailyEntity> savePeopleDaily(String json){
    List<PeoplesDaily> peoplesDailies = new ArrayList<>();
    JSONObject jsonObject = new JSONObject(json);
    Object object = jsonObject.get("news");
    TxtUtil txtUtil = new TxtUtil();
    List<PeoplesDailyEntity> peoplesDailyEntities = new ArrayList<>();
    List<PeoplesDaily> peoplesDailiesTmp = JSON.parseArray(object.toString(), PeoplesDaily.class);
    logger.info("接受新闻时间为：" + new Date() + "进入service,执行保存方法,本次接受新闻数量为：" + peoplesDailiesTmp.size());
    // 合并频道 LifeStyle/LOL/Entertainment/Fashion 栏目合并为新栏目Life
    // String channels = "LifeStyle,LOL,Entertainment,Fashion";
    String[] split = mergeChannel.split(",");
    List<String> channelList = Arrays.asList(split);
    for (PeoplesDaily peoplesDaily : peoplesDailiesTmp) {
        peoplesDailies.add(peoplesDaily);
        if (channelList.contains(peoplesDaily.getChannel())) {
            logger.info("时间" + new Date() + "合并频道之前的数据" + CommonUtil.toJson(peoplesDaily));
            PeoplesDaily peoplesDailyTmp = new PeoplesDaily();
            BeanUtils.copyProperties(peoplesDaily, peoplesDailyTmp);
            peoplesDailyTmp.setChannel(mergedChannel);
            peoplesDailies.add(peoplesDailyTmp);
            logger.info("时间" + new Date() + "合并频道之后的数据" + CommonUtil.toJson(peoplesDailyTmp));
        }
    }
    logger.info("合并频道之后，接受新闻时间为：" + new Date() + "进入service,执行保存方法,本次接受新闻数量为：" + peoplesDailies.size());
    if (!CollectionUtils.isEmpty(peoplesDailies)) {
        for (PeoplesDaily peoplesDaily : peoplesDailies) {
            logger.info("进入service,执行保存方法,本次接受新闻为：" + CommonUtil.toJson(peoplesDaily));
            // 封装新闻
            PeoplesDailyEntity peoplesDailyEntity = new PeoplesDailyEntity();
            BeanUtils.copyProperties(peoplesDaily, peoplesDailyEntity);
            String webpageCode = "";
            if (imedia.equals(peoplesDaily.getOrg())) {
                webpageCode = peoplesDaily.getNews_id();
                peoplesDailyEntity.setIsCore(1);
                peoplesDailyEntity.setOriginal(1);
                if (webpageCode == null) {
                    webpageCode = this.generateWebpageCode(peoplesDaily);
                }
            } else {
                webpageCode = this.generateWebpageCode(peoplesDaily);
                peoplesDailyEntity.setIsCore(0);
            }
            PeoplesDailyEntity byWebpageCode = new PeoplesDailyEntity();
            byWebpageCode = peoplesDailyJPARepository.findByWebpageCode(webpageCode);
            // 专题 0-普通新闻，>0专题
            if (byWebpageCode == null && !imedia.equals(peoplesDaily.getOrg())) {
                peoplesDailyEntity.setContenttype(0);
            }
            if (null == byWebpageCode || imedia.equals(peoplesDaily.getOrg())) {
                peoplesDailyEntity.setWebpageCode(webpageCode);
                peoplesDailyEntity.setStatus(0);
                peoplesDailyEntity.setIsDelete(0);
                if (null != peoplesDaily.getContent()) {
                    peoplesDailyEntity.setNoTagContent(Html2Text(peoplesDaily.getContent()));
                }
                peoplesDailyEntity.setRequestBody(CommonUtil.toJson(peoplesDaily));
                peoplesDailyEntity.setCreateDatetime(new Date());
                peoplesDailyEntity.setUpdateDatetime(new Date());
                String mapResult = channel_map.get(peoplesDaily.getChannel());
                if (null != mapResult && !StringUtils.isEmpty(mapResult)) {
                    peoplesDailyEntity.setChannel(mapResult);
                }
                logger.info("入库新闻前的webpagecode为：" + webpageCode);
                PeoplesDailyEntity save = null;
                try {
                    save = peoplesDailyJPARepository.save(peoplesDailyEntity);
                    if (null != save) {
                        logger.info("入库成功新闻的webpagecode为：" + save.getWebpageCode());
                    }
                } catch (Exception e) {
                    logger.error("入库保存新闻出错信息：" + e);
                    e.printStackTrace();
                }
                // 封装相似
                List<PeoplesDailyRelated> related = peoplesDaily.getRelated();
                if (!CollectionUtils.isEmpty(related)) {
                    List<PeoplesDailyRelatedEntity> peoplesDailyRelatedEntities = new ArrayList<>();
                    for (PeoplesDailyRelated peoplesDailyRelated : related) {
                        PeoplesDailyRelatedEntity peoplesDailyRelatedEntity = new PeoplesDailyRelatedEntity();
                        BeanUtils.copyProperties(peoplesDailyRelated, peoplesDailyRelatedEntity);
                        peoplesDailyRelatedEntity.setWebpageCode(webpageCode);
                        peoplesDailyRelatedEntity.setStatus(0);
                        peoplesDailyRelatedEntity.setIsDelete(0);
                        peoplesDailyRelatedEntities.add(peoplesDailyRelatedJPARepository.save(peoplesDailyRelatedEntity));
                    }
                }
                // 封装图片
                List<PeoplesDailyImg> image_list = peoplesDaily.getImage_list();
                if (!CollectionUtils.isEmpty(image_list)) {
                    List<PeoplesDailyImgEntity> peoplesDailyImgEntities = new ArrayList<>();
                    for (PeoplesDailyImg peoplesDailyImg : image_list) {
                        PeoplesDailyImgEntity peoplesDailyImgEntity = new PeoplesDailyImgEntity();
                        BeanUtils.copyProperties(peoplesDailyImg, peoplesDailyImgEntity);
                        peoplesDailyImgEntity.setWebpageCode(webpageCode);
                        peoplesDailyImgEntity.setDescription(peoplesDailyImg.getDesc());
                        peoplesDailyImgEntity.setStatus(0);
                        peoplesDailyImgEntity.setIsDelete(0);
                        peoplesDailyImgEntities.add(peoplesDailyImgJPARepository.save(peoplesDailyImgEntity));
                    }
                }
                List<PeoplesDailyVideos> videos = peoplesDaily.getVideos();
                if (!CollectionUtils.isEmpty(videos)) {
                    List<PeoplesDailyVideosEntity> peoplesDailyVideosEntities = new ArrayList<>();
                    for (PeoplesDailyVideos peoplesDailyVideos : videos) {
                        PeoplesDailyVideosEntity peoplesDailyVideosEntity = new PeoplesDailyVideosEntity();
                        BeanUtils.copyProperties(peoplesDailyVideos, peoplesDailyVideosEntity);
                        peoplesDailyVideosEntity.setWebpageCode(webpageCode);
                        peoplesDailyVideosEntity.setDescription(peoplesDailyVideos.getDesc());
                        peoplesDailyVideosEntity.setVideoSize(peoplesDailyVideos.getSize());
                        peoplesDailyVideosEntity.setStatus(0);
                        peoplesDailyVideosEntity.setIsDelete(0);
                        peoplesDailyVideosEntities.add(peoplesDailyVideosJPARepository.save(peoplesDailyVideosEntity));
                    }
                }
                peoplesDailyEntities.add(save);
            } else {
                logger.info("重复数据,webpageCode为:" + webpageCode + "接受的新闻" + CommonUtil.toJson(peoplesDaily));
            }
        }
    }
    return peoplesDailyEntities;
}


@Override
public Map<String,String> groupByOrgAll(){
    Map<String, String> resultMap = new LinkedMap();
    List<Object> objList = peoplesDailyJPARepository.groupByOrgHourAll();
    if (!CollectionUtils.isEmpty(objList)) {
        resultMap.put("接受数据：当日凌晨起始,ORG分组", "ORG分组");
        resultMap = transforMap(objList, resultMap);
    }
    return resultMap;
}


public void pushSingleNewsMedia(String webpageCode){
    logger.info("推送失败新闻，单条重复推送:" + webpageCode + "时间" + new Date());
    List<String> verifyChannelList = CommonUtil.parseStringToList(verifyImedia, ",");
    List<PeoplesDaily> contentListImedia = new ArrayList<>();
    PeoplesDailyEntity peoplesDailyEntity = this.findByWebpageCode(webpageCode);
    // 推送数据webpagecode
    String channel = peoplesDailyEntity.getChannel();
    PeoplesDaily parseObject = JSON.parseObject(peoplesDailyEntity.getRequestBody(), PeoplesDaily.class);
    if (null != channel && !StringUtils.isEmpty(channel)) {
        parseObject.setChannel(channel);
    }
    if (null == peoplesDailyEntity.getSummary() || StringUtils.isEmpty(peoplesDailyEntity.getSummary())) {
        parseObject.setSummary("");
    }
    if (!CollectionUtils.isEmpty(verifyChannelList)) {
        // 频道审核
        if (verifyChannelList.contains(parseObject.getChannel())) {
            parseObject.setVerify(1);
        } else {
            parseObject.setVerify(0);
        }
    }
    String content = parseObject.getContent();
    if (null != content && !StringUtils.isEmpty(content)) {
        content = content.replaceAll("<content>", "");
        content = content.replaceAll("</content>", "");
        parseObject.setContent(content);
    }
    parseObject.setNews_id(peoplesDailyEntity.getWebpageCode());
    contentListImedia.add(parseObject);
    peoplesDailyEntity.setIsDelete(pushingStatus);
    peoplesDailyJPARepository.save(peoplesDailyEntity);
    Integer pushTotalTime = 3;
    Integer countPush = 0;
    for (int i = 0; i < pushTotalTime; i++) {
        String pushDataToImediaResult = this.pushDataToImedia(contentListImedia, postUrlImedia);
        if (null != pushDataToImediaResult && !StringUtils.isEmpty(pushDataToImediaResult)) {
            JSONArray jsonArray = new JSONArray(pushDataToImediaResult);
            Integer errorCode = jsonArray.getJSONObject(0).getInt("errorCode");
            String errorMsg = jsonArray.getJSONObject(0).getString("errorMsg");
            String newsId = jsonArray.getJSONObject(0).getString("newsId");
            switch(errorCode) {
                case // OK
                0:
                    logger.info("推送成功---Imedia数据时间为：" + new Date() + "成功信息" + errorMsg + "webpagecode为：" + newsId);
                    peoplesDailyEntity.setIsDelete(pushAlreadyStatus);
                    peoplesDailyJPARepository.save(peoplesDailyEntity);
                    break;
                default:
                    logger.error("推送失败---Imedia数据时间为：" + new Date() + "失败信息" + errorMsg + "webpagecode为：" + newsId);
                    countPush++;
                    break;
            }
        }
    }
    if (countPush == 3) {
        peoplesDailyEntity.setIsDelete(failPushStatus);
        peoplesDailyJPARepository.save(peoplesDailyEntity);
    }
}


public List<PeoplesDailyEntity> pageQueryForMedia(){
    Integer pageNum = 0;
    Integer length = ImediaPushCount;
    Pageable pageable = new PageRequest(pageNum, length);
    Page<PeoplesDailyEntity> pageList = peoplesDailyJPARepository.findAll(new Specification<PeoplesDailyEntity>() {

        @Override
        public Predicate toPredicate(Root<PeoplesDailyEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(cb.equal(root.get("isDelete"), pushStatus));
            predicates.add(cb.equal(root.get("isCore"), isCore));
            // if (!CollectionUtils.isEmpty(verifyChannelList)) {
            // Path<String> path = root.<String> get("channel");
            // In<String> ins = cb.in(path);
            // for (String in : verifyChannelList) {
            // ins.value(in);
            // }
            // predicates.add(ins);
            // }
            return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        }
    }, pageable);
    if (null != pageList) {
        return pageList.getContent();
    }
    return null;
}


public String pushDataToCloud(List<String> contentList,String postUrl){
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("content", contentList);
    String infor = jsonObject.toString();
    PostMethod method = null;
    try {
        HttpClient httpClient = new HttpClient();
        method = new PostMethod(postUrl);
        RequestEntity requestEntity = new StringRequestEntity(infor, "application/json", "UTF-8");
        method.setRequestEntity(requestEntity);
        httpClient.executeMethod(method);
        String result = method.getResponseBodyAsString();
        return result;
    } catch (Exception e) {
        logger.error("推送APICloud异常信息：" + e);
        e.printStackTrace();
        return null;
    }
}


public Map<String,String> groupByOrg(){
    Map<String, String> resultMap = new LinkedMap();
    List<Object> objList = peoplesDailyJPARepository.groupByOrgHour();
    if (!CollectionUtils.isEmpty(objList)) {
        resultMap.put("接受数据：当前时间一个小时内,ORG分组", "ORG分组");
        resultMap = transforMap(objList, resultMap);
    }
    return resultMap;
}


public String Html2Text(String inputString){
    // 含html标签的字符串
    String htmlStr = inputString;
    String textStr = "";
    java.util.regex.Pattern p_script;
    java.util.regex.Matcher m_script;
    java.util.regex.Pattern p_style;
    java.util.regex.Matcher m_style;
    java.util.regex.Pattern p_html;
    java.util.regex.Matcher m_html;
    try {
        // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
        String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
        // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
        String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
        // 定义HTML标签的正则表达式
        String regEx_html = "<[^>]+>";
        p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        m_script = p_script.matcher(htmlStr);
        // 过滤script标签
        htmlStr = m_script.replaceAll("");
        p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        m_style = p_style.matcher(htmlStr);
        // 过滤style标签
        htmlStr = m_style.replaceAll("");
        p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        m_html = p_html.matcher(htmlStr);
        // 过滤html标签
        htmlStr = m_html.replaceAll("");
        textStr = htmlStr;
    } catch (Exception e) {
        System.err.println("Html2Text: " + e.getMessage());
    }
    // 剔除空格行
    textStr = textStr.replaceAll("[ ]+", " ");
    textStr = textStr.replaceAll("(?m)^\\s*$(\\n|\\r\\n)", "");
    textStr = textStr.replaceAll("&nbsp;", "");
    // 返回文本字符串
    return textStr;
}


public List<PeoplesDailyEntity> pageQueryForMediaTest(){
    Integer pageNum = 0;
    Integer length = 1;
    Pageable pageable = new PageRequest(pageNum, length);
    Page<PeoplesDailyEntity> pageList = peoplesDailyJPARepository.findAll(new Specification<PeoplesDailyEntity>() {

        @Override
        public Predicate toPredicate(Root<PeoplesDailyEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(cb.equal(root.get("isDelete"), pushStatus));
            predicates.add(cb.equal(root.get("isCore"), isCore));
            return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        }
    }, pageable);
    if (null != pageList) {
        return pageList.getContent();
    }
    return null;
}


public PeoplesDailyEntity findByWebpageCode(String webpageCode){
    return peoplesDailyJPARepository.findByWebpageCode(webpageCode);
}


public void dataToAPICloud(){
    logger.info("推送数据定时任务，APICloud,当前时间：" + new Date());
    int countTotalMedia = 0;
    try {
        // while (pageQueryForAPI().size() > 0) {
        if (pageQueryForAPI().size() > 0) {
            logger.info("推送数据定时任务，APICloud,querySize：" + pageQueryForAPI().size());
            List<String> verifyChannelList = CommonUtil.parseStringToList(verifyImedia, ",");
            List<PeoplesDailyEntity> peoplesDailyEntities = this.pageQueryForAPI();
            List<String> contentList = new ArrayList<>();
            List<String> webpagecodeList = new ArrayList<>();
            if (!CollectionUtils.isEmpty(peoplesDailyEntities)) {
                for (PeoplesDailyEntity peoplesDailyEntity : peoplesDailyEntities) {
                    String channel = peoplesDailyEntity.getChannel();
                    if (mergedChannel.equals(channel)) {
                        channel = "Life";
                    }
                    PeoplesDaily parseObject = JSON.parseObject(peoplesDailyEntity.getRequestBody(), PeoplesDaily.class);
                    if (null != channel && !StringUtils.isEmpty(channel)) {
                        parseObject.setChannel(channel);
                    }
                    if (null == peoplesDailyEntity.getSummary() || StringUtils.isEmpty(peoplesDailyEntity.getSummary())) {
                        parseObject.setSummary("");
                    }
                    parseObject.setNews_id(peoplesDailyEntity.getWebpageCode());
                    if (!verifyChannelList.contains(parseObject.getChannel()) || imedia.equals(peoplesDailyEntity.getOrg())) {
                        contentList.add(CommonUtil.toJson(parseObject));
                        webpagecodeList.add(peoplesDailyEntity.getWebpageCode());
                        peoplesDailyEntity.setStatus(pushAlreadyStatus);
                    } else {
                        peoplesDailyEntity.setStatus(verifing);
                    }
                    peoplesDailyJPARepository.save(peoplesDailyEntity);
                }
                logger.info("时间" + new Date() + "APICloud推送数据webpagecode为：" + CommonUtil.toJson(webpagecodeList));
            }
            Integer pushTotalTime = 3;
            // APICloud
            try {
                if (!CollectionUtils.isEmpty(contentList)) {
                    Integer pushTimeAPICloud = 0;
                    for (int i = 0; i < pushTotalTime; i++) {
                        pushTimeAPICloud++;
                        logger.info("第" + pushTimeAPICloud + "推送数据webpagecode为：" + CommonUtil.toJson(webpagecodeList));
                        String pushDataToCloudResult = this.pushDataToCloud(contentList, postUrlAPICloud);
                        logger.info("URLAPICloud 推送次数：" + pushTimeAPICloud + "次 + API接口返回信息：" + pushDataToCloudResult);
                        if (null != pushDataToCloudResult && !StringUtils.isEmpty(pushDataToCloudResult)) {
                            String string = "";
                            try {
                                JSONObject myJsonObject = new JSONObject(pushDataToCloudResult);
                                string = myJsonObject.getString("errorMsg");
                                if ("OK".equals(string)) {
                                    logger.info("推送成功---APICloud数据时间为：" + new Date() + "成功信息" + string + "大小为：" + contentList.size() + "webpagecode为：" + CommonUtil.toJson(webpagecodeList));
                                    break;
                                } else if ("News Repeated".equals(string)) {
                                    logger.error("推送失败---数据重复，APICloud数据时间为：" + new Date() + "异常信息" + string + "大小为：" + contentList.size() + "webpagecode为：" + CommonUtil.toJson(webpagecodeList));
                                    break;
                                } else {
                                    logger.error("推送失败---APICloud数据时间为：" + new Date() + "异常信息" + string + "大小为：" + contentList.size() + "webpagecode为：" + CommonUtil.toJson(webpagecodeList));
                                }
                            } catch (JSONException e) {
                                logger.error("解析数据失败---APICloud数据时间为：" + new Date() + "大小为：" + contentList.size() + "webpagecode为：" + CommonUtil.toJson(webpagecodeList));
                                logger.error(e + "");
                                e.printStackTrace();
                            }
                        }
                    }
                    if (3 == pushTimeAPICloud) {
                        logger.info("推送APICloud 3次失败，本次推送数据失败时间：" + new Date() + "推送数据webpagecode为:" + CommonUtil.toJson(webpagecodeList));
                        for (PeoplesDailyEntity peoplesDailyEntity : peoplesDailyEntities) {
                            PeoplesDailyEntity byWebpageCode = peoplesDailyJPARepository.findByWebpageCode(peoplesDailyEntity.getWebpageCode());
                            byWebpageCode.setStatus(failPushStatus);
                            peoplesDailyJPARepository.save(byWebpageCode);
                        }
                    }
                }
            } catch (Exception e) {
                logger.error(e + "");
                e.printStackTrace();
            }
        // Thread.sleep(pushSleep);
        // }
        } else {
            logger.info("APICloud本次推送数据时间：" + new Date() + "本次无符合推送的数据，推送数据大小为" + 0);
        }
    } catch (Exception e) {
        logger.error(e + "");
        e.printStackTrace();
    }
}


public PeoplesDailyEntity saveNewsForMediaTest(){
    PeoplesDailyEntity peoplesDailyEntityTmp = new PeoplesDailyEntity();
    PeoplesDailyEntity peoplesDailyEntity = new PeoplesDailyEntity();
    peoplesDailyEntity.setIsDelete(0);
    peoplesDailyEntity.setIsCore(1);
    peoplesDailyEntity.setContenttype(1);
    peoplesDailyEntity.setContent(System.currentTimeMillis() + "基于微信公众号开发的h5页面，使用jssdk成功分享之后，被分享的页面再次被分享的时候jssdk出错，出现这种错误的一种可能就是，url的错误假设我们的页面叫做test.html,我们将页面放在test.com域名下面，那么我们去微信后台计算签名的url应该是这样的");
    peoplesDailyEntity.setPub_time(CommonUtil.DateToStr(new Date()));
    peoplesDailyEntity.setSummary("summary---基于微信公众号开发的h5页面，使用jssdk成功分享之后");
    peoplesDailyEntity.setOriginal(1);
    peoplesDailyEntity.setIsDelete(0);
    peoplesDailyEntity.setStatus(0);
    peoplesDailyEntity.setAuthors("UEC" + System.currentTimeMillis());
    peoplesDailyEntity.setTitle("Title" + System.currentTimeMillis());
    String newsId_webPageCode = UUID.randomUUID().toString();
    peoplesDailyEntity.setNews_id(newsId_webPageCode);
    peoplesDailyEntity.setCoreId("core_id" + System.currentTimeMillis());
    peoplesDailyEntity.setChannel("Top news");
    peoplesDailyEntity.setCreateDatetime(new Date());
    peoplesDailyEntity.setEntity("UEC,荣之联");
    peoplesDailyEntity.setKeywords("Keywords");
    peoplesDailyEntity.setM_url("m.google.com");
    peoplesDailyEntity.setUrl("www.google.com");
    peoplesDailyEntity.setNoTagContent("基于微信公众号开发的h5页面，使用jssdk成功分享之后，被分享的页面再次被分享的时候jssdk出错");
    peoplesDailyEntity.setSort(1);
    peoplesDailyEntity.setWebpageCode(newsId_webPageCode);
    peoplesDailyEntity.setTo_top(1);
    peoplesDailyEntity.setVidioImg("vidioImg");
    String requsetString = CommonUtil.toJson(peoplesDailyEntity);
    BeanUtils.copyProperties(peoplesDailyEntity, peoplesDailyEntityTmp);
    peoplesDailyEntityTmp.setRequestBody(requsetString);
    return peoplesDailyJPARepository.save(peoplesDailyEntityTmp);
}


public Map<String,String> transforMap(List<Object> objList,Map<String,String> resultMap){
    Integer totalCount = 0;
    for (int i = 0; null != objList && i < objList.size(); i++) {
        Object obj = objList.get(i);
        String s = CommonUtil.toJson(obj);
        List<String> dayMediaList = CommonUtil.parseJson2List(s, String.class);
        if (dayMediaList != null && dayMediaList.size() > 1) {
            String count = dayMediaList.get(0);
            String source = dayMediaList.get(1);
            resultMap.put(source, count);
            totalCount += Integer.valueOf(count);
        }
    }
    if (null != totalCount) {
        resultMap.put("totalCount", String.valueOf(totalCount));
    }
    return resultMap;
}


@Override
public Predicate toPredicate(Root<PeoplesDailyEntity> root,CriteriaQuery<?> query,CriteriaBuilder cb){
    List<Predicate> predicates = new ArrayList<Predicate>();
    predicates.add(cb.equal(root.get("isDelete"), pushStatus));
    predicates.add(cb.equal(root.get("isCore"), isCore));
    return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
}


public List<String> setStatus(List<PeoplesDailyEntity> peoplesDailyEntityList){
    List<String> webpageCodeList = new ArrayList<>();
    if (!CollectionUtils.isEmpty(peoplesDailyEntityList)) {
        for (PeoplesDailyEntity peoplesDaily : peoplesDailyEntityList) {
            String webpageCode = peoplesDaily.getWebpageCode();
            webpageCodeList.add(webpageCode);
            try {
                PeoplesDailyEntity byWebpageCode = this.findByWebpageCode(webpageCode);
                byWebpageCode.setIsDelete(failPushStatus);
                peoplesDailyJPARepository.save(byWebpageCode);
            } catch (Exception e) {
                logger.error("更改is_delete状态失败,webpageCode为：" + webpageCode);
                logger.error("更改is_delete状态失败，错误日志" + e);
                e.printStackTrace();
            }
        }
    }
    return webpageCodeList;
}


public Map<String,String> groupByChannel(){
    Map<String, String> resultMap = new LinkedMap();
    List<Object> objList = peoplesDailyJPARepository.groupByChannelHour();
    if (!CollectionUtils.isEmpty(objList)) {
        resultMap.put("接受数据：当前时间一个小时内,Channel分组", "Channel分组");
        resultMap = transforMap(objList, resultMap);
    }
    return resultMap;
}


@Override
public Map<String,String> groupByChannelAll(){
    Map<String, String> resultMap = new LinkedMap();
    List<Object> objList = peoplesDailyJPARepository.groupByChannelHourAll();
    if (!CollectionUtils.isEmpty(objList)) {
        resultMap.put("接受数据：当日凌晨,Channel分组", "Channel分组");
        resultMap = transforMap(objList, resultMap);
    }
    return resultMap;
}


public List<PeoplesDailyEntity> pageQueryForAPI(){
    Integer pageNum = 0;
    Integer length = APIPushCount;
    Pageable pageable = new PageRequest(pageNum, length);
    Page<PeoplesDailyEntity> pageList = peoplesDailyJPARepository.findAll(new Specification<PeoplesDailyEntity>() {

        @Override
        public Predicate toPredicate(Root<PeoplesDailyEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(cb.equal(root.get("status"), pushStatus));
            predicates.add(cb.equal(root.get("isCore"), isCore));
            return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        }
    }, pageable);
    if (null != pageList) {
        return pageList.getContent();
    }
    return null;
}


@Override
public Map<String,String> pushDataChannelHourAll(){
    Map<String, String> resultMap = new LinkedMap();
    List<Object> objList = peoplesDailyJPARepository.pushDataChannelHourAll();
    if (!CollectionUtils.isEmpty(objList)) {
        resultMap.put("推送的数据：当日凌晨,Channel", "Channel分组");
        resultMap = transforMap(objList, resultMap);
    }
    return resultMap;
}


public void dataToImedia(){
    logger.info("Imedia推送数据定时任务，Imedia,当前时间：" + new Date());
    try {
        while (pageQueryForMedia().size() > 0) {
            List<String> verifyChannelList = CommonUtil.parseStringToList(verifyImedia, ",");
            List<PeoplesDailyEntity> peoplesDailyEntityListMedia = this.pageQueryForMedia();
            List<PeoplesDaily> contentListImedia = new ArrayList<>();
            List<String> webpagecodeListImedia = new ArrayList<>();
            if (!CollectionUtils.isEmpty(peoplesDailyEntityListMedia)) {
                for (PeoplesDailyEntity peoplesDailyEntity : peoplesDailyEntityListMedia) {
                    if (!imedia.equals(peoplesDailyEntity.getOrg()) && PEOPLES_DAILY_MEDIA.contains(peoplesDailyEntity.getChannel())) {
                        String channel = peoplesDailyEntity.getChannel();
                        if (mergedChannel.equals(channel)) {
                            channel = "Life";
                        }
                        PeoplesDaily parseObject = JSON.parseObject(peoplesDailyEntity.getRequestBody(), PeoplesDaily.class);
                        if (null != channel && !StringUtils.isEmpty(channel)) {
                            parseObject.setChannel(channel);
                        }
                        if (null == peoplesDailyEntity.getSummary() || StringUtils.isEmpty(peoplesDailyEntity.getSummary())) {
                            parseObject.setSummary("");
                        }
                        if (!CollectionUtils.isEmpty(verifyChannelList)) {
                            if (verifyChannelList.contains(parseObject.getChannel())) {
                                parseObject.setVerify(1);
                            } else {
                                parseObject.setVerify(0);
                            }
                        }
                        String content = parseObject.getContent();
                        if (null != content && !StringUtils.isEmpty(content)) {
                            content = content.replaceAll("<content>", "");
                            content = content.replaceAll("</content>", "");
                            parseObject.setContent(content);
                        }
                        parseObject.setNews_id(peoplesDailyEntity.getWebpageCode());
                        // imedia截取ssummary
                        String summary = parseObject.getSummary();
                        if (StringUtils.isNoneBlank(summary)) {
                            logger.info("summary原始摘要:" + summary);
                            summary = summary.length() < 250 ? summary : summary.substring(0, 250);
                            logger.info("summary处理之后摘要:" + summary);
                            parseObject.setSummary(summary);
                        }
                        webpagecodeListImedia.add(peoplesDailyEntity.getWebpageCode());
                        contentListImedia.add(parseObject);
                        peoplesDailyEntity.setIsDelete(pushingStatus);
                        peoplesDailyJPARepository.save(peoplesDailyEntity);
                    } else {
                        peoplesDailyEntity.setIsDelete(pushAlreadyStatus);
                        peoplesDailyJPARepository.save(peoplesDailyEntity);
                    }
                }
                logger.info("Imedia时间" + new Date() + "推送数据webpagecode为：" + CommonUtil.toJson(webpagecodeListImedia));
            }
            // 推送
            if (!CollectionUtils.isEmpty(contentListImedia)) {
                logger.info("推送时间为:" + new Date() + "Imedia推送条数为:" + contentListImedia.size() + "Imedia推送数据webpagecode为：" + CommonUtil.toJson(webpagecodeListImedia));
                try {
                    String pushDataToImediaResult = this.pushDataToImedia(contentListImedia, postUrlImedia);
                    logger.info("URLImedia 推送,Imedia接口返回信息：" + pushDataToImediaResult);
                    String stringImedia = "";
                    if (null != pushDataToImediaResult && !StringUtils.isEmpty(pushDataToImediaResult)) {
                        try {
                            JSONArray jsonArray = new JSONArray(pushDataToImediaResult);
                            for (int j = 0; j < jsonArray.length(); j++) {
                                Integer errorCode = jsonArray.getJSONObject(j).getInt("errorCode");
                                String errorMsg = jsonArray.getJSONObject(j).getString("errorMsg");
                                String newsId = jsonArray.getJSONObject(j).getString("newsId");
                                PeoplesDailyEntity byWebpageCode = this.findByWebpageCode(newsId);
                                switch(errorCode) {
                                    case // OK
                                    0:
                                        logger.info("推送成功---Imedia数据时间为：" + new Date() + "成功信息" + errorMsg + "webpagecode为：" + newsId);
                                        byWebpageCode.setIsDelete(pushAlreadyStatus);
                                        peoplesDailyJPARepository.save(byWebpageCode);
                                        break;
                                    case // Analysis error!
                                    1:
                                        logger.error("推送失败---Imedia数据时间为：" + new Date() + "失败信息" + errorMsg + "webpagecode为：" + newsId);
                                        // byWebpageCode.setIsDelete(pushStatus);
                                        // peoplesDailyJPARepository.save(byWebpageCode);
                                        this.pushSingleNewsMedia(newsId);
                                        break;
                                    case // Request body is empty!
                                    2:
                                        logger.error("推送失败---Imedia数据时间为：" + new Date() + "失败信息" + errorMsg + "webpagecode为：" + newsId);
                                        // byWebpageCode.setIsDelete(pushStatus);
                                        // peoplesDailyJPARepository.save(byWebpageCode);
                                        this.pushSingleNewsMedia(newsId);
                                        break;
                                    case // News Repeat
                                    3:
                                        logger.error("推送失败---Imedia数据时间为：" + new Date() + "失败信息" + errorMsg + "webpagecode为：" + newsId);
                                        byWebpageCode.setIsDelete(pushAlreadyStatus);
                                        peoplesDailyJPARepository.save(byWebpageCode);
                                        break;
                                    default:
                                        logger.error("推送失败---Imedia数据时间为：" + new Date() + "失败信息" + errorMsg + "webpagecode为：" + newsId);
                                        // byWebpageCode.setIsDelete(pushStatus);
                                        // peoplesDailyJPARepository.save(byWebpageCode);
                                        this.pushSingleNewsMedia(newsId);
                                        break;
                                }
                            }
                        } catch (JSONException e) {
                            List<String> list = this.setStatus(peoplesDailyEntityListMedia);
                            logger.error("解析数据失败---Imedia数据时间为：" + new Date() + "异常信息" + stringImedia + "异常信息" + pushDataToImediaResult + "大小为：" + contentListImedia.size() + "本次推送数据webpagecode为：" + CommonUtil.toJson(webpagecodeListImedia));
                            logger.error(e + "");
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e) {
                    logger.error("推送Imedia异常信息：" + e);
                    List<String> list = this.setStatus(peoplesDailyEntityListMedia);
                    e.printStackTrace();
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    System.out.println("media");
}


public String hashKeyForDisk(String key){
    String cacheKey;
    try {
        final MessageDigest mDigest = MessageDigest.getInstance("MD5");
        mDigest.update(key.getBytes());
        cacheKey = this.bytesToHexString(mDigest.digest());
    } catch (NoSuchAlgorithmException e) {
        cacheKey = String.valueOf(key.hashCode());
    }
    return cacheKey;
}


@Override
public Map<String,String> pushDataOrgHourAll(){
    Map<String, String> resultMap = new LinkedMap();
    List<Object> objList = peoplesDailyJPARepository.pushDataOrgHourAll();
    if (!CollectionUtils.isEmpty(objList)) {
        resultMap.put("推送的数据：当日凌晨,ORG", "ORG分组");
        resultMap = transforMap(objList, resultMap);
    }
    return resultMap;
}


}