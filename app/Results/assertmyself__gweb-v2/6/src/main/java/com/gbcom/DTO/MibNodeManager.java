package com.gbcom.DTO;
 import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;
import com.gbcom.common.template.xml.northful.NorthConfigManager;
import com.gbcom.omc.platform.da.xml.MyXMLException;
import com.gbcom.op.util.xml.XStreamUtil;
public class MibNodeManager {

 private  Logger LOG;

 final  String root;

 private  MibNodeContext context;

 private  Map<String,Map<String,MibNode>> itemMap;

 private  Map<String,MibNode> nameMap;

 private  Map<String,Boolean> isVectorMap;

 private  MibNodeManager instance;

private MibNodeManager() {
    try {
        load();
    } catch (Exception e) {
        LOG.error("******* LOAD MIBNODE FILE FAILED!!", e);
    }
}
public Map<String,MibNode> getNodeByItem(String item){
    return itemMap.get(item);
}


public String[] getNameByItem(String item){
    final Map<String, MibNode> tmp = itemMap.get(item);
    final Set<String> relativeNameList = tmp.keySet();
    return relativeNameList.toArray(new String[relativeNameList.size()]);
}


public MibNodeManager getInstance(){
    return instance;
}


public String[] getOidbyName(String item,String name){
    final Map<String, MibNode> tmp = itemMap.get(item);
    final List<String> oidList = new ArrayList<String>();
    for (String each : name) {
        if (tmp.get(each) == null) {
            continue;
        }
        oidList.add(tmp.get(each).getOid());
    }
    if (oidList.isEmpty()) {
        return new String[0];
    }
    return oidList.toArray(new String[oidList.size()]);
}


public String[] getOidByItem(String item){
    try {
        final Map<String, MibNode> tmp = itemMap.get(item);
        final List<MibNode> relativeOidList = new ArrayList<MibNode>(tmp.values());
        if (relativeOidList.isEmpty()) {
            return new String[0];
        }
        String[] oids = new String[relativeOidList.size()];
        for (int i = 0; i < relativeOidList.size(); i++) {
            oids[i] = relativeOidList.get(i).getOid();
        }
        return oids;
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        LOG.error("EE", e);
    }
    return new String[0];
}


public MibNode getNodeByName(String item,String name){
    // return nameMap.get(name);
    Map<String, MibNode> map = itemMap.get(item);
    if (map == null) {
        return null;
    }
    return map.get(name);
// return itemMap.get(name);
}


}