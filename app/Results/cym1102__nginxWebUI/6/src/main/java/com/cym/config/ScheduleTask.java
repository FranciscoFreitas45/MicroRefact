package com.cym.config;
 import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import com.cym.controller.adminPage.CertController;
import com.cym.controller.adminPage.ConfController;
import com.cym.controller.adminPage.RemoteController;
import com.cym.model.Cert;
import com.cym.model.Http;
import com.cym.model.Remote;
import com.cym.model.Upstream;
import com.cym.model.UpstreamServer;
import com.cym.service.HttpService;
import com.cym.service.LogService;
import com.cym.service.RemoteService;
import com.cym.service.SettingService;
import com.cym.service.UpstreamService;
import com.cym.utils.MessageUtils;
import com.cym.utils.SendMailUtils;
import com.cym.utils.TelnetUtils;
import cn.craccd.sqlHelper.utils.SqlHelper;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.ZipUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.cym.Interface.CertController;
import com.cym.Interface.SettingService;
import com.cym.Interface.ConfController;
import com.cym.Interface.UpstreamService;
import com.cym.Interface.SendMailUtils;
import com.cym.Interface.HttpService;
import com.cym.Interface.MessageUtils;
import com.cym.DTO.Http;
import com.cym.DTO.Cert;
import com.cym.DTO.Upstream;
// 1.主要用于标记配置类，兼备Component的效果。
@Configuration
// 2.开启定时任务
@EnableScheduling
public class ScheduleTask {

@Value("${server.port}")
 private Integer port;

@Autowired
 private SqlHelper sqlHelper;

@Autowired
 private CertController certController;

@Autowired
 private SettingService settingService;

@Autowired
 private ConfController confController;

@Autowired
 private RemoteController remoteController;

@Autowired
 private RemoteService remoteService;

@Autowired
 private UpstreamService upstreamService;

@Autowired
 private LogService logInfoService;

@Autowired
 private SendMailUtils sendMailUtils;

@Autowired
 private HttpService httpService;

@Autowired
 private MessageUtils m;


public void cutLog(Http http){
    String path = http.getValue();
    if (StrUtil.isNotEmpty(path)) {
        // 去掉格式化
        path = path.split(" ")[0];
        if (FileUtil.exist(path)) {
            String date = DateUtil.format(new Date(), "yyyy-MM-dd");
            // 分隔日志
            File dist = new File(path + "." + date);
            FileUtil.move(new File(path), dist, true);
            // 打包
            ZipUtil.zip(dist.getPath(), dist.getPath() + ".zip", false);
            // 删除原文件
            FileUtil.del(dist);
            // 重载Nginx产生新的文件
            confController.reload(null, null, null);
            // 删除多余文件
            long time = System.currentTimeMillis();
            File dir = new File(path).getParentFile();
            for (File file : dir.listFiles()) {
                if (file.getName().contains(new File(path).getName()) && file.getName().endsWith(".zip")) {
                    String[] array = file.getName().split("[.]");
                    String dateStr = array[array.length - 2];
                    DateTime dateTime = DateUtil.parse(dateStr, "yyyy-MM-dd");
                    if (time - dateTime.getTime() > TimeUnit.DAYS.toMillis(8)) {
                        FileUtil.del(file);
                    }
                }
            }
        }
    }
}


@Scheduled(cron = "0 55 23 * * ?")
public void diviLog(){
    Http access = httpService.getName("access_log");
    if (access != null) {
        cutLog(access);
    }
    Http error = httpService.getName("error_log");
    if (access != null) {
        cutLog(error);
    }
}


@Scheduled(cron = "0 0 0 * * ?")
public void delBak(){
    long time = System.currentTimeMillis();
    File dir = new File(InitConfig.home + "bak/");
    if (dir.exists()) {
        for (File file : dir.listFiles()) {
            if (file.getName().contains("nginx.conf.") && (file.getName().endsWith(".zip") || file.getName().endsWith(".bak"))) {
                String dateStr = file.getName().replace("nginx.conf.", "").replace(".zip", "").replace(".bak", "").split("_")[0];
                DateTime date = null;
                if (dateStr.length() != 10) {
                    FileUtil.del(file);
                } else {
                    date = DateUtil.parse(dateStr, "yyyy-MM-dd");
                    if (time - date.getTime() > TimeUnit.DAYS.toMillis(8)) {
                        FileUtil.del(file);
                    }
                }
            }
        }
    }
}


@Scheduled(cron = "0/30 * * * * ?")
public void nginxTasks(){
    String lastNginxSend = settingService.get("lastNginxSend");
    String mail = settingService.get("mail");
    String nginxMonitor = settingService.get("nginxMonitor");
    String mailInterval = settingService.get("mail_interval");
    if (StrUtil.isEmpty(mailInterval)) {
        mailInterval = "30";
    }
    if ("true".equals(nginxMonitor) && StrUtil.isNotEmpty(mail) && (StrUtil.isEmpty(lastNginxSend) || System.currentTimeMillis() - Long.parseLong(lastNginxSend) > TimeUnit.MINUTES.toMillis(Integer.parseInt(mailInterval)))) {
        List<String> names = new ArrayList<>();
        // 测试远程
        List<Remote> remoteList = remoteService.getMonitorRemoteList();
        for (Remote remote : remoteList) {
            try {
                String json = HttpUtil.get(remote.getProtocol() + "://" + remote.getIp() + ":" + remote.getPort() + "/adminPage/remote/version?creditKey=" + remote.getCreditKey(), 1000);
                Map<String, Object> map = JSONUtil.toBean(json, new TypeReference<Map<String, Object>>() {
                }.getType(), false);
                if ((Integer) map.get("nginx") == 0 && remote.getMonitor() == 1) {
                    names.add(remote.getDescr() + "[" + remote.getIp() + ":" + remote.getPort() + "]");
                }
            } catch (Exception e) {
                e.printStackTrace();
                names.add(remote.getDescr() + "[" + remote.getIp() + ":" + remote.getPort() + "]");
            }
        }
        // 测试本地
        if ("1".equals(settingService.get("monitorLocal"))) {
            Map<String, Object> map = remoteController.version();
            if ((Integer) map.get("nginx") == 0) {
                names.add(0, m.get("remoteStr.local") + "[127.0.0.1:" + port + "]");
            }
        }
        if (names.size() > 0) {
            sendMailUtils.sendMailSmtp(mail, m.get("mailStr.nginxFail"), m.get("mailStr.nginxTips") + StrUtil.join(" ", names));
            settingService.set("lastNginxSend", String.valueOf(System.currentTimeMillis()));
        }
    }
}


@Scheduled(cron = "0 0 2 * * ?")
public void certTasks(){
    List<Cert> certList = sqlHelper.findAll(Cert.class);
    // 检查需要续签的证书
    long time = System.currentTimeMillis();
    for (Cert cert : certList) {
        // 大于50天的续签
        if (cert.getMakeTime() != null && cert.getAutoRenew() == 1 && time - cert.getMakeTime() > TimeUnit.DAYS.toMillis(50)) {
            certController.apply(cert.getId(), "renew");
            // 重载nginx使证书生效
            confController.reload(null, null, null);
        }
    }
}


@Scheduled(cron = "0/30 * * * * ?")
public void nodeTasks(){
    String lastUpstreamSend = settingService.get("lastUpstreamSend");
    String mail = settingService.get("mail");
    String upstreamMonitor = settingService.get("upstreamMonitor");
    String mailInterval = settingService.get("mail_interval");
    if (StrUtil.isEmpty(mailInterval)) {
        mailInterval = "30";
    }
    if ("true".equals(upstreamMonitor)) {
        List<UpstreamServer> upstreamServers = upstreamService.getAllServer();
        List<String> ips = new ArrayList<>();
        for (UpstreamServer upstreamServer : upstreamServers) {
            if (!TelnetUtils.isRunning(upstreamServer.getServer(), upstreamServer.getPort())) {
                Upstream upstream = sqlHelper.findById(upstreamServer.getUpstreamId(), Upstream.class);
                if (upstream.getMonitor() == 1 && StrUtil.isNotEmpty(mail) && (StrUtil.isEmpty(lastUpstreamSend) || System.currentTimeMillis() - Long.parseLong(lastUpstreamSend) > TimeUnit.MINUTES.toMillis(Integer.parseInt(mailInterval)))) {
                    ips.add(upstreamServer.getServer() + ":" + upstreamServer.getPort());
                }
                upstreamServer.setMonitorStatus(0);
            } else {
                upstreamServer.setMonitorStatus(1);
            }
            sqlHelper.updateById(upstreamServer);
        }
        if (ips.size() > 0) {
            String dateStr = DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");
            if (settingService.get("lang") != null && settingService.get("lang").equals("en_US")) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss", Locale.ENGLISH);
                dateStr = dateFormat.format(new Date());
            }
            sendMailUtils.sendMailSmtp(mail, m.get("mailStr.upstreamFail"), m.get("mailStr.upstreamTips") + StrUtil.join(" ", ips) + "\r\n" + dateStr);
            settingService.set("lastUpstreamSend", String.valueOf(System.currentTimeMillis()));
        }
    }
}


}