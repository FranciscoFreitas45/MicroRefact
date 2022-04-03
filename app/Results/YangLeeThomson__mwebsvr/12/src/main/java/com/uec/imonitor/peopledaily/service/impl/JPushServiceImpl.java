package com.uec.imonitor.peopledaily.service.impl;
 import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.alibaba.fastjson.JSON;
import com.uec.imonitor.common.datatables.DataTablesRequestEntity;
import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.common.util.CommonUtil;
import com.uec.imonitor.news.utils.TxtUtil;
import com.uec.imonitor.peopledaily.bean;
import com.uec.imonitor.peopledaily.dao.IPeoplesDailyImgJPARepository;
import com.uec.imonitor.peopledaily.dao.IPeoplesDailyJPARepository;
import com.uec.imonitor.peopledaily.dao.IPeoplesDailyRelatedJPARepository;
import com.uec.imonitor.peopledaily.dao.IPeoplesDailyVideosJPARepository;
import com.uec.imonitor.peopledaily.service.IJPushService;
import com.uec.imonitor.peopledaily.service.IPeoplesDailyService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.map.LinkedMap;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.lang3.StringUtils;
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
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.SimpleDateFormat;
import java.util;
import java.util.regex.Pattern;
@Service("jPushService")
@Transactional(value = "transactionManager")
public class JPushServiceImpl implements IJPushService{

 private  Logger logger;

 private  String masterSecret;

 private  String appKey;

 private  String ALERT;


public void jiguangPush(){
    // 声明别名
    String alias = "123456";
    String title = "";
    logger.info("对别名" + alias + "的用户推送信息");
    PushResult result = push(String.valueOf(alias), ALERT, title);
    if (result != null && result.isResultOK()) {
        logger.info("针对别名" + alias + "的信息推送成功！");
    } else {
        logger.info("针对别名" + alias + "的信息推送失败！");
    }
}


public PushPayload buildPushObject_all_alias_Message(String title,Map<String,String> extras){
    return PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.all()).setNotification(Notification.newBuilder().addPlatformNotification(AndroidNotification.newBuilder().addExtra("type", extras.get("type")).addExtra("txt", extras.get("txt")).setAlert(title).build()).addPlatformNotification(IosNotification.newBuilder().addExtra("type", extras.get("type")).addExtra("txt", extras.get("txt")).setAlert(title).build()).build()).setOptions(Options.newBuilder().setApnsProduction(// true-推送生产环境 false-推送开发环境（测试使用参数）
    false).setTimeToLive(// 消息在JPush服务器的失效时间（测试使用参数）
    90).build()).setOptions(Options.newBuilder().setApnsProduction(true).build()).build();
// 设置ios平台环境  True 表示推送生产环境，False 表示要推送开发环境   默认是开发
}


public PushPayload buildPushObject_android_ios_alias_alert(String alias,String alert,String title){
    return PushPayload.newBuilder().setPlatform(Platform.android_ios()).setAudience(Audience.all()).setNotification(Notification.newBuilder().addPlatformNotification(AndroidNotification.newBuilder().addExtra("type", "infomation").setAlert(alert).setTitle("").build()).addPlatformNotification(IosNotification.newBuilder().addExtra("type", "infomation").setAlert(alert).build()).build()).setOptions(Options.newBuilder().setApnsProduction(// true-推送生产环境 false-推送开发环境（测试使用参数）
    false).setTimeToLive(// 消息在JPush服务器的失效时间（测试使用参数）
    90).build()).build();
}


@Override
public void sendAllMessage(){
    JPushClient jpushClient = new JPushClient(masterSecret, appKey);
    Map<String, String> extras = new HashMap<String, String>();
    // 添加附加信息
    // extra  里面 有两个key，value，type是传channel，---txt如果type不是Photos 就传新闻的id，如果是Photo就传id|新闻张数，例如新闻id是1，一共有8张组图，txt就传1|8
    extras.put("type", "Sports");
    extras.put("txt", "3236");
    String title = "Wang Qiang stuns Stosur to reach HK Tennis Open semifinals";
    PushPayload payload = buildPushObject_all_alias_Message(title, extras);
    try {
        PushResult result = jpushClient.sendPush(payload);
        System.out.println(result);
    } catch (APIConnectionException e) {
        System.out.println(e);
    } catch (APIRequestException e) {
        System.out.println(e);
        System.out.println("Error response from JPush server. Should review and fix it. " + e);
        System.out.println("HTTP Status: " + e.getStatus());
        System.out.println("Error Code: " + e.getErrorCode());
        System.out.println("Error Message: " + e.getErrorMessage());
        System.out.println("Msg ID: " + e.getMsgId());
    }
}


public PushResult push(String alias,String alert,String title){
    ClientConfig clientConfig = ClientConfig.getInstance();
    JPushClient jpushClient = new JPushClient(masterSecret, appKey, null, clientConfig);
    PushPayload payload = buildPushObject_android_ios_alias_alert(alias, alert, title);
    try {
        PushResult result = jpushClient.sendPush(payload);
        return result;
    } catch (APIConnectionException e) {
        logger.error("Connection error. Should retry later. ", e);
        return null;
    } catch (APIRequestException e) {
        logger.error("Error response from JPush server. Should review and fix it. ", e);
        logger.info("HTTP Status: " + e.getStatus());
        logger.info("Error Code: " + e.getErrorCode());
        logger.info("Error Message: " + e.getErrorMessage());
        logger.info("Msg ID: " + e.getMsgId());
        return null;
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    } finally {
        jpushClient.close();
    }
}


}