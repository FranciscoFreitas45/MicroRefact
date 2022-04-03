package com.gbcom.system.manager;
 import com.gbcom.system.daoservice.SysPrivilegeService;
import com.gbcom.system.domain.SysPrivilege;
import com.hc.core.security.privilege.Privilege;
import com.hc.core.security.util.SpringSecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class SysPrivilegeManager {

@Autowired
 private  SysPrivilegeService sysPrivilegeService;


public List<SysPrivilege> getAllPrivileges(){
    String hql = "from SysPrivilege order by treeId asc";
    return sysPrivilegeService.find(hql);
}


public Map<String,Privilege> getUserButtonPrivileges(String gridId){
    List<Privilege> list = SpringSecurityUtils.getUserButtonPrivileges();
    Map<String, Privilege> ret = new HashMap<String, Privilege>();
    if (list != null && list.size() > 0) {
        for (Privilege p : list) {
            if (p.getCode().toLowerCase().indexOf(gridId.toLowerCase() + ".") != -1) {
                ret.put(p.getCode(), p);
            }
        }
    }
    return ret;
}


public boolean hasPrivilegeByCode(String privilegeCode){
    Collection<Privilege> privileges = SpringSecurityUtils.getUserAllPrivileges();
    for (Privilege privilege : privileges) {
        if (privilege.getCode().equals(privilegeCode)) {
            return true;
        }
    }
    return false;
}


public Map<String,Privilege> getUserMenuPrivilegesMap(){
    return SpringSecurityUtils.getUserMenuPrivilegesMap();
}


public Map<String,Privilege> getMenuPrivilegesMap(){
    Map<String, Privilege> ret = SpringSecurityUtils.getMenuPrivilegesMap();
    return ret;
}


public Map<String,Privilege> getButtonPrivileges(String gridId){
    List<Privilege> list = SpringSecurityUtils.getButtonPrivileges();
    Map<String, Privilege> ret = new HashMap<String, Privilege>();
    if (list != null && list.size() > 0) {
        for (Privilege p : list) {
            if (p.getCode().toLowerCase().indexOf(gridId.toLowerCase() + ".") != -1) {
                ret.put(p.getCode(), p);
            }
        }
    }
    return ret;
}


public SysPrivilege getPrivilegesByCode(String code){
    String hql = "from SysPrivilege t where t.code = '" + code + "'";
    List<SysPrivilege> list = sysPrivilegeService.find(hql);
    if (list != null && list.size() > 0) {
        return list.get(0);
    }
    return null;
}


public List<Privilege> getMenuPrivileges(){
    return SpringSecurityUtils.getMenuPrivileges();
}


}