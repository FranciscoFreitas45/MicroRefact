package com.cym.config;
 import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import com.cym.model.Basic;
import com.cym.model.Http;
import com.cym.service.BasicService;
import com.cym.service.SettingService;
import com.cym.utils.MessageUtils;
import com.cym.utils.NginxUtils;
import com.cym.utils.SystemTool;
import cn.craccd.sqlHelper.utils.SqlHelper;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.RuntimeUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.ZipUtil;
import com.cym.Interface.BasicService;
import com.cym.Interface.ScheduleTask;
@Component
public class InitConfig {

 private Logger logger;

@Autowired
 protected  MessageUtils m;

 public  String acmeSh;

 public  String acmeShDir;

 public  String home;

@Autowired
 private SettingService settingService;

@Autowired
 private BasicService basicService;

@Autowired
 private ScheduleTask scheduleTask;

@Autowired
 private SqlHelper sqlHelper;

@Autowired
 private JdbcTemplate jdbcTemplate;


@PostConstruct
public void init(){
    // 初始化base值
    Long count = sqlHelper.findAllCount(Basic.class);
    if (count == 0) {
        List<Basic> basics = new ArrayList<Basic>();
        basics.add(new Basic("worker_processes", "auto", 1l));
        basics.add(new Basic("events", "{\r\n" + "    worker_connections  1024;\r\n    accept_mutex on;\r\n" + "}", 2l));
        sqlHelper.insertAll(basics);
    }
    // 初始化http值
    count = sqlHelper.findAllCount(Http.class);
    if (count == 0) {
        List<Http> https = new ArrayList<Http>();
        https.add(new Http("include", "mime.types", 0l));
        https.add(new Http("default_type", "application/octet-stream", 1l));
        sqlHelper.insertAll(https);
    }
    // 释放nginx.conf,mime.types
    if (!FileUtil.exist(home + "nginx.conf")) {
        ClassPathResource resource = new ClassPathResource("nginx.conf");
        FileUtil.writeFromStream(resource.getInputStream(), home + "nginx.conf");
    }
    if (!FileUtil.exist(home + "mime.types")) {
        ClassPathResource resource = new ClassPathResource("mime.types");
        FileUtil.writeFromStream(resource.getInputStream(), home + "mime.types");
    }
    // 设置nginx配置文件
    String nginxPath = settingService.get("nginxPath");
    if (StrUtil.isEmpty(nginxPath)) {
        nginxPath = home + "nginx.conf";
        // 设置nginx.conf路径
        settingService.set("nginxPath", nginxPath);
    }
    if (SystemTool.isLinux()) {
        // 释放acme全新包
        ClassPathResource resource = new ClassPathResource("acme.zip");
        InputStream inputStream = resource.getInputStream();
        FileUtil.writeFromStream(inputStream, InitConfig.home + "acme.zip");
        FileUtil.mkdir(acmeShDir);
        ZipUtil.unzip(home + "acme.zip", acmeShDir);
        FileUtil.del(home + "acme.zip");
        // 修改acme.sh文件
        List<String> res = FileUtil.readUtf8Lines(acmeSh);
        for (int i = 0; i < res.size(); i++) {
            if (res.get(i).contains("DEFAULT_INSTALL_HOME=\"$HOME/.$PROJECT_NAME\"")) {
                res.set(i, "DEFAULT_INSTALL_HOME=\"" + acmeShDir + "\"");
            }
        }
        FileUtil.writeUtf8Lines(res, acmeSh);
        RuntimeUtil.exec("chmod a+x " + acmeSh);
        // 查找ngx_stream_module模块
        if (!basicService.contain("ngx_stream_module.so")) {
            logger.info(m.get("commonStr.ngxStream"));
            List<String> list = RuntimeUtil.execForLines(CharsetUtil.systemCharset(), "find / -name ngx_stream_module.so");
            for (String path : list) {
                if (path.contains("ngx_stream_module.so") && path.length() < 80) {
                    Basic basic = new Basic("load_module", path, -10l);
                    sqlHelper.insert(basic);
                    break;
                }
            }
        }
        // 判断是否是容器中
        if (inDocker()) {
            // 设置nginx执行文件
            settingService.set("nginxExe", "nginx");
        }
        // 尝试启动nginx
        String nginxExe = settingService.get("nginxExe");
        String nginxDir = settingService.get("nginxDir");
        logger.info("nginxIsRun:" + NginxUtils.isRun());
        if (!NginxUtils.isRun() && StrUtil.isNotEmpty(nginxExe) && StrUtil.isNotEmpty(nginxPath)) {
            String cmd = nginxExe + " -c " + nginxPath;
            if (StrUtil.isNotEmpty(nginxDir)) {
                cmd += " -p " + nginxDir;
            }
            logger.info("runCmd:" + cmd);
            RuntimeUtil.execForStr("/bin/sh", "-c", cmd);
        }
    }
}


public Boolean inDocker(){
    List<String> rs = RuntimeUtil.execForLines("cat /proc/1/cgroup");
    for (String str : rs) {
        // logger.info(str);
        if (str.contains("docker")) {
            logger.info("I am in docker");
            return true;
        }
        if (str.contains("kubepods")) {
            logger.info("I am in k8s");
            return true;
        }
    }
    logger.info("I am not in docker");
    return false;
}


@Value("${project.home}")
public void setHome(String home){
    InitConfig.home = home;
    InitConfig.acmeShDir = home + ".acme.sh/";
    InitConfig.acmeSh = home + ".acme.sh/acme.sh";
}


}