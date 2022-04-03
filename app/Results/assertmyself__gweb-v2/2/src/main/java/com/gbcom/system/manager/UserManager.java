package com.gbcom.system.manager;
 import com.gbcom.system.daoservice.SysPrivilegeService;
import com.gbcom.system.daoservice.SysUserService;
import com.gbcom.system.domain.SysPrivilege;
import com.gbcom.system.domain.SysUser;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
public class UserManager {

@Autowired
 private  SysUserService sysUserService;

@Autowired
 private  SysPrivilegeService sysPrivilegeService;


public List<SysUser> findByProperty(String propertyName,String value){
    return sysUserService.findByProperty(propertyName, value);
}


@SuppressWarnings("unchecked")
public List<SysPrivilege> getUserPrivileges(SysUser user,String isDeny){
    List<SysPrivilege> ret = new ArrayList();
    String sql = "from SysPrivilege t, SysUserPrivilege t2 " + "where t.id = t2.privilege ";
    if (StringUtils.isNotEmpty(isDeny)) {
        sql += " and t2.isDeny = " + isDeny;
    }
    sql += " and t2.user in (select id from SysUser " + "where loginName = '" + user.getLoginName() + "')";
    List list = sysPrivilegeService.find(sql);
    if (list != null && list.size() > 0) {
        for (int i = 0; i < list.size(); i++) {
            Object[] obj = (Object[]) list.get(i);
            SysPrivilege p = (SysPrivilege) obj[0];
            // p.setPrivilegeType();
            ret.add(p);
        }
    }
    return ret;
}


public List<SysPrivilege> getUserRolePrivileges(SysUser user,String isDeny){
    String sql = "from SysPrivilege t" + " where t.id in (select privilege " + " from SysRolePrivilege " + " where role in (select role from SysUserRole where user = " + user.getId() + ")) ";
    if (StringUtils.isNotEmpty(isDeny)) {
        sql += " and t.id not in (select privilege from SysUserPrivilege where user = " + user.getId() + " and isDeny = " + isDeny + ")";
    }
    List<SysPrivilege> list = sysPrivilegeService.find(sql);
    List<SysPrivilege> ret = new ArrayList<SysPrivilege>();
    for (SysPrivilege tmp : list) {
        // tmp.setPrivilegeType();
        ret.add(tmp);
    }
    return ret;
}


}