package com.gbcom.common.template.xml.guide;
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


public void load(){
    logger.info("build device info guide and  datas information  begin");
    final Class<?>[] classContext = { DevInfoContext.class, DevInfoGuide.class };
    final URL url = Thread.currentThread().getContextClassLoader().getResource("config/guide/devinfo_guide.xml");
    DevInfoContext context = XStreamUtil.fromXML(DevInfoContext.class, url.getFile(), classContext);
    for (DevInfoGuide item : context.getList()) {
        String code = item.getCode();
        guideMap.put(code, item);
        // 数据基本
        datas.put(code, new HashMap<Object, List<DeviceInfo>>());
    }
    logger.info("build device guidemap and datas success!");
}


public DevInfoGuide getGuideByCode(String code){
    return guideMap.get(code);
}


public void main(String[] args){
    DevInfoGuide info = DeviceInfoCenter.getInstance().getGuideByCode("10001");
    System.out.println(info.getName());
}


public DeviceInfoCenter getInstance(){
    return DevInfoCenterHolder.instance;
}


public void pupEntry(String code,Map<Object,List<DeviceInfo>> data){
    datas.put(code, data);
}


public void clearAll(){
    guideMap.clear();
    datas.clear();
}


public Map<String,DevInfoGuide> getGuideMap(){
    return guideMap;
}


public Map<String,Map<Object,List<DeviceInfo>>> getDatas(){
    return datas;
}


public void clearData(){
    guideMap.clear();
}


}