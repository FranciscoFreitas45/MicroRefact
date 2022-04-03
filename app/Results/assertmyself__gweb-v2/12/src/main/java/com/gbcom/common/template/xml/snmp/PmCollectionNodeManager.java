package com.gbcom.common.template.xml.snmp;
 import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.gbcom.op.util.xml.XStreamUtil;
public class PmCollectionNodeManager {

 private  PmCollectionContext context;

 private  Map<String,Map<String,String[]>> oidMap;

 private  PmCollectionNodeManager instance;

private PmCollectionNodeManager() {
    try {
        Class<?>[] classContext = { PmCollectionContext.class, PmCollectionVerdor.class, PmCollectionGroup.class, MibNode.class };
        URL url = this.getClass().getClassLoader().getResource("config/snmp/PmCollectionNode.xml");
        context = XStreamUtil.fromXML(PmCollectionContext.class, url.getFile(), classContext);
        List<PmCollectionVerdor> verdorList = context.getVerdorList();
        for (PmCollectionVerdor verdor : verdorList) {
            List<PmCollectionGroup> groupList = verdor.getGroupList();
            if (groupList == null || groupList.isEmpty()) {
                continue;
            }
            /**
             * 对应一项
             */
            Map<String, String[]> tmp = new HashMap<String, String[]>();
            for (PmCollectionGroup group : groupList) {
                List<MibNode> list = group.getMibNodeList();
                String[] oids = new String[list.size()];
                int i = 0;
                for (MibNode node : list) {
                    oids[i++] = node.getOid();
                }
                tmp.put(group.getId(), oids);
            }
            oidMap.put(verdor.getName(), tmp);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
public String[] getCollectionNode(String verdor,String groupId){
    Map<String, String[]> groups = getCollectionTempByVerdor(verdor);
    String[] tmp = groups.get(groupId);
    return tmp;
}


public void main(String[] args){
    @SuppressWarnings("unused")
    Map<String, String[]> template = PmCollectionNodeManager.getInstance().getCollectionTempByVerdor("aceway");
    @SuppressWarnings("unused")
    String[] oids = PmCollectionNodeManager.getInstance().getCollectionNode("aceway", "1");
}


public PmCollectionNodeManager getInstance(){
    if (instance == null) {
        instance = new PmCollectionNodeManager();
    }
    return instance;
}


public Map<String,String[]> getCollectionTempByVerdor(String verdor){
    Map<String, String[]> groups = oidMap.get(verdor);
    return groups;
}


}