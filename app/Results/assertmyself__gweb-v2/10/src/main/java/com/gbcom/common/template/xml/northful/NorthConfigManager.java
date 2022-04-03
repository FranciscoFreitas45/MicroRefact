package com.gbcom.common.template.xml.northful;
 import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.gbcom.system.utils.XmlFileUtil;
public class NorthConfigManager {

 private  Logger LOG;

 private  NorthConfigManager instance;

 private  NorthConfig northConfig;

private NorthConfigManager() {
    loadNorthConfigFile();
}
public NorthConfig getNorthConfig(){
    return northConfig;
}


public void loadNorthConfigFile(){
    String filePath = this.getClass().getClassLoader().getResource("/config/north/north_config.xml").getFile();
    File file = new File(filePath);
    if (file.exists()) {
        try {
            northConfig = XmlFileUtil.unmarshallerObjectFromXml(NorthConfig.class, file);
        } catch (Exception e) {
            LOG.error("failed to parse north_config.xml", e);
            System.exit(0);
        }
    } else {
        LOG.error("north_config.xml can't be found");
        System.exit(0);
    }
}


public NorthConfigManager getInstance(){
    return instance;
}


public boolean isContains(String vendor){
    return northConfig.getList().contains(vendor);
}


}