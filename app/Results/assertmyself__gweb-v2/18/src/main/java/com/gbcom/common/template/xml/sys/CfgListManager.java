package com.gbcom.common.template.xml.sys;
 import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import com.gbcom.op.util.xml.XStreamUtil;
public class CfgListManager {

 private  CfgListContext context;

 private  Map<String,CfgItem> cfgMap;

 private  CfgListManager instance;

private CfgListManager() {
    load();
}
public void load(){
    final Class<?>[] classContext = { CfgListContext.class, CfgItem.class };
    final URL url = Thread.currentThread().getContextClassLoader().getResource("config/sys/cfg_list.xml");
    context = XStreamUtil.fromXML(CfgListContext.class, url.getFile(), classContext);
    for (CfgItem item : context.getList()) {
        String name = item.getName();
        cfgMap.put(name, item);
    }
}


public CfgItem getItem(String name){
    return cfgMap.get(name);
}


public Map<String,CfgItem> getCfgMap(){
    return cfgMap;
}


public void main(String[] args){
    CfgItem item = CfgListManager.getInstance().getItem("DHCPConfigEntity");
    System.out.println(item.getDesc());
}


public CfgListManager getInstance(){
    return instance;
}


public Set<String> getKeySet(){
    return cfgMap.keySet();
}


}