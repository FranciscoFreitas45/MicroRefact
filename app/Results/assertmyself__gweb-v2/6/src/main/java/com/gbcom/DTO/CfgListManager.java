package com.gbcom.DTO;
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
public CfgItem getItem(String name){
    return cfgMap.get(name);
}


public Map<String,CfgItem> getCfgMap(){
    return cfgMap;
}


public CfgListManager getInstance(){
    return instance;
}


public Set<String> getKeySet(){
    return cfgMap.keySet();
}


}