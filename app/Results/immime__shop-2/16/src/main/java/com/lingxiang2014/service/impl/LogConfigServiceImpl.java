package com.lingxiang2014.service.impl;
 import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import com.lingxiang2014.CommonAttributes;
import com.lingxiang2014.LogConfig;
import com.lingxiang2014.service.LogConfigService;
@Service("logConfigServiceImpl")
public class LogConfigServiceImpl implements LogConfigService{


@SuppressWarnings("unchecked")
@Cacheable("logConfig")
public List<LogConfig> getAll(){
    try {
        File shopxxXmlFile = new ClassPathResource(CommonAttributes.SHOPXX_XML_PATH).getFile();
        Document document = new SAXReader().read(shopxxXmlFile);
        List<org.dom4j.Element> elements = document.selectNodes("/shopxx/logConfig");
        List<LogConfig> logConfigs = new ArrayList<LogConfig>();
        for (org.dom4j.Element element : elements) {
            String operation = element.attributeValue("operation");
            String urlPattern = element.attributeValue("urlPattern");
            LogConfig logConfig = new LogConfig();
            logConfig.setOperation(operation);
            logConfig.setUrlPattern(urlPattern);
            logConfigs.add(logConfig);
        }
        return logConfigs;
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}


}