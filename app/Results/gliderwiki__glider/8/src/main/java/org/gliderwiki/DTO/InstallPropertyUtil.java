package org.gliderwiki.DTO;
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

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://6";


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
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/CreateCionfigProperties"))

.queryParam("mailUserId",mailUserId)
.queryParam("mailUserKey",mailUserKey)
.queryParam("siteDomain",siteDomain)
.queryParam("configPath",configPath)
;
int aux = restTemplate.getForObject(builder.toUriString(),int.class);
return aux;
}


}