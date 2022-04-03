package com.gbcom.system.utils;
 import com.gbcom.system.domain;
import com.hc.core.utils.Constant;
import org.apache.commons.lang.StringUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
public class PrivilegeUtils {


public String getProjectCondition(){
    return getProjectCondition("");
}


public Map<Long,SysUserPrivilege> userPrivilegeListToMap(List<SysUserPrivilege> list){
    Map<Long, SysUserPrivilege> ret = new HashMap<Long, SysUserPrivilege>();
    if (list != null && list.size() > 0) {
        for (SysUserPrivilege bean : list) {
            ret.put(bean.getPrivilege().getId(), bean);
        }
    }
    return ret;
}


public Map<Long,SysPrivilege> listToMap(List<SysPrivilege> list){
    Map<Long, SysPrivilege> ret = new HashMap<Long, SysPrivilege>();
    if (list != null && list.size() > 0) {
        for (SysPrivilege bean : list) {
            ret.put(bean.getId(), bean);
        }
    }
    return ret;
}


}