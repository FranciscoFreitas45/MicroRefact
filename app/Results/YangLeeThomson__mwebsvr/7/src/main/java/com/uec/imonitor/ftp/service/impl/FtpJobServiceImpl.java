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
    // 获取开始时间
    long startTime = System.currentTimeMillis();
    FtpUtil ftpUtil = new FtpUtil();
    try {
        ftpUtil.getFTPClient(host, port, username, password);
        log.info("开始从服务器下载文件。。。");
        readAllTxt = ftpUtil.batchDownLoadFile(serverPath, localPath);
        ftpUtil.disconnectFtp();
    } catch (IOException e) {
        log.error("下载失败," + e);
    }
    // 获取结束时间
    long endTime = System.currentTimeMillis();
    log.info("从服务器下载文件结束。。。");
    log.info("本次下载文件耗时。。。" + (endTime - startTime) / 1000 + "s");
    // 处理txt文件
    TxtUtil txtUtil = new TxtUtil();
    // ++++++++++++++++++++++++++++++++++
    // 拷贝
    try {
        if (!new File(copyPath).exists()) {
            new File(copyPath).mkdirs();
            log.info("创建稿件存档文件夹:" + copyPath);
        }
        txtUtil.copy(new File(localPath), new File(copyPath));
        log.info("拷贝文件结束。。。");
    } catch (IOException e2) {
        log.error("拷贝文件失败," + e2);
    }
    // ++++++++++++++++++++++++++++++++++
    // Map<String, Map<String, String>> readAllTxt = txtUtil.readAllTxt(localPath);
    // 本地临时文件删除
    // txtUtil.deleteDirectory(localPath);
    // log.info("本地删除文件结束。。。");
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
                    // 稿件创建时间
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
                    // 设置结束时间为14天后的凌晨
                    Date weeHours = weeHours(date);
                    calendar.setTime(weeHours);
                    calendar.add(Calendar.DATE, +crawlDays);
                    date = calendar.getTime();
                    requestNewsEntity.setEndDatetime(date);
                    // end
                    requestNewsService.add(requestNewsEntity);
                    successCount++;
                    log.info("文件路径为" + entry.getKey() + "的文件保存成功。。。");
                } catch (Exception e) {
                    failCount++;
                    log.error("文件路径为" + entry.getKey() + "的文件保存失败。。。");
                    log.error(e.toString());
                }
            } else {
                System.err.println("***空指针异常信息：" + JSON.toJSONString(valueMap));
                failCount++;
                log.info("该稿件格式暂不支持,稿件路径为:" + entry.getKey());
            }
        }
    }
    // 获取结束时间
    endTime = System.currentTimeMillis();
    log.info("入库成功文件个数" + successCount);
    log.info("入库失败文件个数" + failCount);
    log.info("本次文件入库耗时。。。" + (endTime - startTime) / 1000 + "s");
}


public Date weeHours(Date date){
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    int hour = cal.get(Calendar.HOUR_OF_DAY);
    int minute = cal.get(Calendar.MINUTE);
    int second = cal.get(Calendar.SECOND);
    // 时分秒（毫秒数）
    long millisecond = hour * 60 * 60 * 1000 + minute * 60 * 1000 + second * 1000;
    // 凌晨00:00:00
    cal.setTimeInMillis(cal.getTimeInMillis() - millisecond);
    return cal.getTime();
}


}