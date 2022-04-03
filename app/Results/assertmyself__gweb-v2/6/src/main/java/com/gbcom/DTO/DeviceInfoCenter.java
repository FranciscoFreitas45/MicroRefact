package com.gbcom.DTO;
 import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import com.gbcom.op.util.xml.XStreamUtil;
public class DeviceInfoCenter {

 private  Logger logger;

 private  Map<String,DevInfoGuide> guideMap;

 private  Map<String,Map<Object,List<DeviceInfo>>> datas;

 private  DeviceInfoCenter instance;

private DeviceInfoCenter() {
    load();
}
public Map<Object,List<DeviceInfo>> getDatasByGuide(String code){
    return datas.get(code);
}


public DevInfoGuide getGuideByCode(String code){
    return guideMap.get(code);
}


public DeviceInfoCenter getInstance(){
    return DevInfoCenterHolder.instance;
}


public Map<String,DevInfoGuide> getGuideMap(){
    return guideMap;
}


public Map<String,Map<Object,List<DeviceInfo>>> getDatas(){
    return datas;
}


}