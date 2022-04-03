package com.gbcom.system.manager;
 import com.gbcom.system.daoservice.SysPrivilegeService;
import com.gbcom.system.daoservice.SysRolePrivilegeService;
import com.gbcom.system.daoservice.SysRoleService;
import com.gbcom.system.domain.SysPrivilege;
import com.gbcom.system.domain.SysRole;
import com.gbcom.system.domain.SysRolePrivilege;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Iterator;
import java.util.List;
@Service
public class SysRoleManager {

@Autowired
 private  SysPrivilegeService sysPrivilegeService;

@Autowired
 private  SysRoleService sysRoleService;

@Autowired
 private  SysRolePrivilegeService sysRolePrivilegeService;


public List<SysPrivilege> getRolePrivileges(Long roleId){
    String hql = "select t2 from SysRolePrivilege t1, SysPrivilege t2 ";
    hql += " where t1.privilege=t2.id and t1.role = " + roleId;
    hql += " order by t2.id asc";
    return sysPrivilegeService.find(hql);
}


@SuppressWarnings("null")
public void saveRolePrivilege(String ids,Long roleId){
    String[] pids = StringUtils.split(ids, ",");
    SysRole role = sysRoleService.get(roleId);
    // 删除该角色已有的授权信息
    // role.getSysRolePrivileges().iterator();
    Iterator<SysRolePrivilege> it = null;
    while (it.hasNext()) {
        sysRolePrivilegeService.delete(it.next());
        sysRolePrivilegeService.getSession().flush();
    }
    if (pids != null && pids.length > 0) {
        for (String sid : pids) {
            Long id = new Long(sid);
            SysPrivilege privilege = sysPrivilegeService.get(id);
            if (privilege != null) {
                SysRolePrivilege entity = new SysRolePrivilege();
                entity.setRole(role);
                entity.setPrivilege(privilege);
                sysRolePrivilegeService.save(entity);
            }
        }
    }
}


}