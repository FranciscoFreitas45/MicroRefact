package com.gbcom.system.utils;
 import com.gbcom.system.domain.SysArea;
import com.gbcom.system.domain.SysAreaNes;
import com.gbcom.system.manager.SysAreaManager;
import com.hc.core.utils.SpringUtils;
public class SysAreaUtil {


public String sysAreaNeIDs(SysArea area){
    String keys = "";
    for (SysAreaNes ne : area.getNes()) {
        if (ne != null) {
        }
        keys += ne.getNeID() + ",";
    }
    return keys.contains(",") ? keys.substring(0, keys.lastIndexOf(",")) : "";
}


public String sysAreaNeNames(SysArea area){
    String keys = "";
    for (SysAreaNes ne : area.getNes()) {
        if (ne != null) {
        }
        keys += ne.getNeName() + ",";
    }
    return keys.contains(",") ? keys.substring(0, keys.lastIndexOf(",")) : "";
}


public String allSysAreaNeIDs(){
    SysAreaManager manager = ((SysAreaManager) SpringUtils.getBean("sysAreaManager"));
    String keys = "";
    for (SysAreaNes ne : manager.getAllAreaNes()) {
        if (ne != null) {
        }
        keys += ne.getNeID() + ",";
    }
    return keys.contains(",") ? keys.substring(0, keys.lastIndexOf(",")) : "";
}


}