package com.gbcom.common.template.xml.snmp;
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


public void load(){
    final Class<?>[] classContext = { MibNodeContext.class, MibNodeCollection.class, MibNode.class };
    // 首先加载核心mib contains  bblink ,so not bblink mibnode file.
    URL url = MibNodeManager.class.getResource("/config/snmp/mibnode/core_mibnode.xml");
    File file = new File(url.toURI());
    addFileInfo(file, classContext);
    // 加载北向的，如果有
    List<String> names = NorthConfigManager.getInstance().getNorthConfig().getList();
    for (String name : names) {
        String path = null;
        // 华数
        if (name.equals("wasu")) {
            path = "/config/snmp/mibnode/wasu_mibnode.xml";
            addFileInfo(new File(MibNodeManager.class.getResource(path).toURI()), classContext);
        }
    /* else if (name.equals("peng")){//鹏博士
				path = "/config/snmp/mibnode/peng_mibnode.xml";
				addFileInfo(new File(MibNodeManager.class.getResource(path).toURI()),classContext);
			} else if (name.equals("zchuan")){//中传
				path = "/config/snmp/mibnode/zchuan_mibnode.xml";
				addFileInfo(new File(MibNodeManager.class.getResource(path).toURI()),classContext);
			}*/
    }
}


public String[] getNameByItem(String item){
    final Map<String, MibNode> tmp = itemMap.get(item);
    final Set<String> relativeNameList = tmp.keySet();
    return relativeNameList.toArray(new String[relativeNameList.size()]);
}


public void addFileInfo(File file,Class<?>[] classContext){
    context = XStreamUtil.fromXML(MibNodeContext.class, file.toString(), classContext);
    final List<MibNodeCollection> collection = context.getCollectionList();
    for (MibNodeCollection mibNodeCollection : collection) {
        final List<MibNode> list = mibNodeCollection.getMibNodeList();
        if (list == null || list.isEmpty()) {
            continue;
        }
        // 对应一项
        final String item = mibNodeCollection.getItem();
        final boolean isVector = mibNodeCollection.isVector();
        final Map<String, MibNode> tmp = new LinkedHashMap<String, MibNode>();
        for (MibNode node : list) {
            node.setOid(root + node.getOid());
            tmp.put(node.getName(), node);
            nameMap.put(node.getName(), node);
        }
        itemMap.put(item, tmp);
        isVectorMap.put(item, Boolean.valueOf(isVector));
    }
}


public void main(String[] args){
    SnmpTempManager.getInstance().init();
    String[] oids = MibNodeManager.getInstance().getOidByItem("example");
    LOG.info(Arrays.asList(oids));
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


public boolean isVectorByItem(String item){
    Assert.notNull(item);
    return isVectorMap.get(item);
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