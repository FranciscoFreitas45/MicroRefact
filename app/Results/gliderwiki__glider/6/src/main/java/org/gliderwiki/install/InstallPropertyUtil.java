package org.gliderwiki.install;
 import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import org.gliderwiki.web.system.SystemConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class InstallPropertyUtil {

 private Logger logger;


public int CreateMailProperties(String mailUserId,String passwd,String mailUserKey,String smtpHostName,String smtpServerPort,String mailCharset,String siteDomain,String siteOwner,String configPath){
    int result = 0;
    Properties prop = null;
    try {
        prop = new Properties();
        prop.setProperty("mail.user.id", mailUserId);
        prop.setProperty("mail.user.password", passwd);
        prop.setProperty("mail.user.key", mailUserKey);
        prop.setProperty("smtp.host.name", smtpHostName);
        prop.setProperty("smtp.server.port", smtpServerPort);
        prop.setProperty("mail.charset", mailCharset);
        prop.setProperty("mail.site.domain", siteDomain);
        prop.setProperty("mail.site.owner", siteOwner);
        File file = new File(configPath);
        // File file = new File(svcPath);
        logger.debug("file : " + file.toString());
        logger.info("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        logger.info("✚ System create mail properties in " + configPath + " Start !");
        logger.info("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        // 파일을 생성한다.
        prop.store(new FileOutputStream(configPath + "/mail.properties"), null);
        Set<Object> keySet = prop.keySet();
        Iterator<Object> iterator = keySet.iterator();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            logger.info("##Property Key, Values : " + key + "=" + prop.getProperty((String) key));
        }
        logger.info("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        logger.info("✚ System create mail properties in " + configPath + " Finish !");
        logger.info("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        // 정상적으로 처리 되었을때 return 되는 값 입니다.
        result = 1;
    } catch (IOException ex) {
        result = -1;
        ex.printStackTrace();
    }
    return result;
}


public int CreateCionfigProperties(String mailUserId,String mailUserKey,String siteDomain,String configPath){
    int result = 0;
    Properties prop = null;
    try {
        prop = new Properties();
        prop.setProperty("we.email", mailUserId);
        prop.setProperty("server.domain", SystemConst.SERVER_DOMAIN);
        prop.setProperty("we.active.key", mailUserKey);
        prop.setProperty("file.maxSize", "10");
        prop.setProperty("alarm.key", "FkCPBhGUATYufevoIp1-uQ");
        prop.setProperty("we.domain", siteDomain);
        prop.setProperty("install.status", "Y");
        File file = new File(configPath);
        // File file = new File(svcPath);
        logger.debug("file : " + file.toString());
        logger.info("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        logger.info("✚ System create config properties in " + configPath + " Start !");
        logger.info("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        // 파일을 생성한다.
        prop.store(new FileOutputStream(configPath + "/config.properties"), null);
        Set<Object> keySet = prop.keySet();
        Iterator<Object> iterator = keySet.iterator();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            logger.info("##Property Key, Values : " + key + "=" + prop.getProperty((String) key));
        }
        logger.info("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        logger.info("✚ System create config properties in " + configPath + " Finish !");
        logger.info("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        // 정상적으로 처리 되었을때 return 되는 값 입니다.
        result = 1;
    } catch (IOException ex) {
        result = -1;
        ex.printStackTrace();
    }
    return result;
}


public int CreateJDBCProperties(String jdbcUrl,String jdbcId,String jdbcPw,String jdbcPath){
    int result = 0;
    Properties prop = null;
    try {
        prop = new Properties();
        prop.setProperty("jdbc.driverClassName", SystemConst.MYSQL_DRIVER_NAME);
        prop.setProperty("jdbc.url", jdbcUrl);
        prop.setProperty("jdbc.username", jdbcId);
        prop.setProperty("jdbc.password", jdbcPw);
        File file = new File(jdbcPath);
        logger.debug("file : " + file.toString());
        logger.info("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        logger.info("✚ System create jdbc properties in " + jdbcPath + " Start !");
        logger.info("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        prop.store(new FileOutputStream(jdbcPath + "/jdbc.properties"), null);
        Set<Object> keySet = prop.keySet();
        Iterator<Object> iterator = keySet.iterator();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            logger.info("##Property Key, Values : " + key + "=" + prop.getProperty((String) key));
        }
        logger.info("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        logger.info("✚ System create jdbc properties in " + jdbcPath + " Finish!");
        logger.info("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        // 정상적으로 처리 되었을때 return 되는 값 입니다.
        result = 1;
    } catch (IOException ex) {
        result = -1;
        ex.printStackTrace();
    }
    return result;
}


public void createInitProperties(String installYn,String version,String initPath){
    Properties prop = null;
    try {
        prop = new Properties();
        prop.setProperty("install.status", installYn);
        prop.setProperty("install.version", version);
        File file = new File(initPath);
        // File file = new File(svcPath);
        logger.debug("file : " + file.toString());
        logger.info("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        logger.info("✚ System create init properties in " + initPath + " Start !");
        logger.info("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        // 파일을 생성한다.
        prop.store(new FileOutputStream(initPath + "/init.properties"), null);
        Set<Object> keySet = prop.keySet();
        Iterator<Object> iterator = keySet.iterator();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            logger.info("##Property Key, Values : " + key + "=" + prop.getProperty((String) key));
        }
        logger.info("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        logger.info("✚ System create init properties in " + initPath + " Finish !");
        logger.info("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    } catch (IOException ex) {
        ex.printStackTrace();
    }
}


}