package com.uec.imonitor.ftp.service.impl;
 import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.fastjson.JSON;
import com.uec.imonitor.ftp.service.IFtpJobService;
import com.uec.imonitor.news.utils.FtpUtil;
import com.uec.imonitor.news.utils.TxtUtil;
import com.uec.imonitor.request.bean.RequestNewsEntity;
import com.uec.imonitor.request.service.IRequestNewsService;
import com.uec.imonitor.request.service.ITenantRequestService;
import com.uec.imonitor.Interface.IRequestNewsService;
import com.uec.imonitor.Interface.ITenantRequestService;
import com.uec.imonitor.DTO.RequestNewsEntity;
@Service("ftpJobService")
@Transactional(value = "transactionManager")
public class FtpJobServiceImpl implements IFtpJobService{

 private  Logger log;

@Autowired
 private  IRequestNewsService requestNewsService;

@Autowired
 private  ITenantRequestService tenantRequestService;

@Value("${Ftp.ZGJYB_HOST}")
 private  String host;

@Value("${Ftp.ZGJYB_PORT}")
 private  Integer port;

@Value("${Ftp.ZGJYB_USERNAME}")
 private  String username;

@Value("${Ftp.ZGJYB_PASSWORD}")
 private  String password;

@Value("${Ftp.ZGJYB_WEBNAME}")
 private  String webName;

@Value("${Ftp.ZGJYB_REQUESTID}")
 private  Integer request_id;

@Value("${Ftp.ZGJYB_SERVERPATH}")
 private  String serverPath;

@Value("${Ftp.ZGJYB_LOCALPATH}")
 private  String localPath;

@Value("${Ftp.ZGJYB_COPYPATH}")
 private  String copyPath;


public void ftpDown(){
    // localPath = localPath +  new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    // copyPath = copyPath +  new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    Map<String, Map<String, String>> readAllTxt = null;
    log.info("ftpService..." + new Date());
    // ??????????????????
    long startTime = System.currentTimeMillis();
    FtpUtil ftpUtil = new FtpUtil();
    try {
        ftpUtil.getFTPClient(host, port, username, password);
        log.info("???????????????????????????????????????");
        readAllTxt = ftpUtil.batchDownLoadFile(serverPath, localPath);
        ftpUtil.disconnectFtp();
    } catch (IOException e) {
        log.error("????????????," + e);
    }
    // ??????????????????
    long endTime = System.currentTimeMillis();
    log.info("???????????????????????????????????????");
    log.info("?????????????????????????????????" + (endTime - startTime) / 1000 + "s");
    // ??????txt??????
    TxtUtil txtUtil = new TxtUtil();
    // ++++++++++++++++++++++++++++++++++
    // ??????
    try {
        if (!new File(copyPath).exists()) {
            new File(copyPath).mkdirs();
            log.info("???????????????????????????:" + copyPath);
        }
        txtUtil.copy(new File(localPath), new File(copyPath));
        log.info("???????????????????????????");
    } catch (IOException e2) {
        log.error("??????????????????," + e2);
    }
    // ++++++++++++++++++++++++++++++++++
    // Map<String, Map<String, String>> readAllTxt = txtUtil.readAllTxt(localPath);
    // ????????????????????????
    // txtUtil.deleteDirectory(localPath);
    // log.info("?????????????????????????????????");
    System.err.println(readAllTxt.size());
    int successCount = 0;
    int failCount = 0;
    for (Map.Entry<String, Map<String, String>> entry : readAllTxt.entrySet()) {
        if (null != entry.getValue()) {
            Map<String, String> valueMap = entry.getValue();
            if (valueMap.get("errorMsg") == null) {
                RequestNewsEntity requestNewsEntity = new RequestNewsEntity();
                requestNewsEntity.setNewsSectionId(valueMap.get("sectionNo"));
                requestNewsEntity.setNewsSection(valueMap.get("section"));
                requestNewsEntity.setTitle(valueMap.get("Headline"));
                requestNewsEntity.setSubtitle(valueMap.get("title"));
                requestNewsEntity.setNewsAuthor(valueMap.get("author"));
                requestNewsEntity.setContent(valueMap.get("content"));
                requestNewsEntity.setIsDeleted(0);
                requestNewsEntity.setRequestId(request_id);
                requestNewsEntity.setNewsSource(webName);
                requestNewsEntity.setOriginalCode(valueMap.get("originalCode"));
                String webpagecode = txtUtil.hashKeyForDisk(webName + valueMap.get("author") + valueMap.get("Headline"));
                requestNewsEntity.setWebpageCode(webpagecode);
                try {
                    // ??????????????????
                    String createTime = valueMap.get("createTime");
                    SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
                    // System.out.println(new Date());
                    Date date = sdf.parse(createTime);
                    requestNewsEntity.setReportDatetime(date);
                    Integer crawlDays = tenantRequestService.findById(request_id).getCrawlDays();
                    Calendar calendar = Calendar.getInstance();
                    date = new Date(System.currentTimeMillis());
                    requestNewsEntity.setCreateDatetime(date);
                    requestNewsEntity.setStartDatetime(date);
                    // ?????????????????????14???????????????
                    Date weeHours = weeHours(date);
                    calendar.setTime(weeHours);
                    calendar.add(Calendar.DATE, +crawlDays);
                    date = calendar.getTime();
                    requestNewsEntity.setEndDatetime(date);
                    // end
                    requestNewsService.add(requestNewsEntity);
                    successCount++;
                    log.info("???????????????" + entry.getKey() + "??????????????????????????????");
                } catch (Exception e) {
                    failCount++;
                    log.error("???????????????" + entry.getKey() + "??????????????????????????????");
                    log.error(e.toString());
                }
            } else {
                System.err.println("***????????????????????????" + JSON.toJSONString(valueMap));
                failCount++;
                log.info("???????????????????????????,???????????????:" + entry.getKey());
            }
        }
    }
    // ??????????????????
    endTime = System.currentTimeMillis();
    log.info("????????????????????????" + successCount);
    log.info("????????????????????????" + failCount);
    log.info("?????????????????????????????????" + (endTime - startTime) / 1000 + "s");
}


public Date weeHours(Date date){
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    int hour = cal.get(Calendar.HOUR_OF_DAY);
    int minute = cal.get(Calendar.MINUTE);
    int second = cal.get(Calendar.SECOND);
    // ????????????????????????
    long millisecond = hour * 60 * 60 * 1000 + minute * 60 * 1000 + second * 1000;
    // ??????00:00:00
    cal.setTimeInMillis(cal.getTimeInMillis() - millisecond);
    return cal.getTime();
}


}