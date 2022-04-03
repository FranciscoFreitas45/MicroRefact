package com.gbcom.system.manager;
 import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gbcom.system.daoservice.SysRoleService;
import com.gbcom.system.daoservice.SysUserPrivilegeService;
import com.gbcom.system.daoservice.SysUserRoleService;
import com.gbcom.system.daoservice.SysUserService;
import com.gbcom.system.domain.SysPerson;
import com.gbcom.system.domain.SysPrivilege;
import com.gbcom.system.domain.SysRole;
import com.gbcom.system.domain.SysRolePrivilege;
import com.gbcom.system.domain.SysUser;
import com.gbcom.system.domain.SysUserPrivilege;
import com.gbcom.system.domain.SysUserRole;
import com.gbcom.system.utils.Constants;
import com.hc.core.security.user.BaseUser;
import com.hc.core.security.util.SpringSecurityUtils;
import com.hc.core.utils.JspHelper;
import com.hc.core.utils.StringHelper;
@Service
public class SysUserManager {

@Autowired
 private  SysUserService sysUserService;

@Autowired
 private  SysRoleService sysRoleService;

@Autowired
 private  SysUserRoleService sysUserRoleService;

@Autowired
 private  SysUserPrivilegeService sysUserPrivilegeService;

 private  Map<Long,Set<String>> USER_PRIVILEGE_CODES;


public void saveRoles(SysUser bean,String[] roleIds){
    Set<SysUserRole> sysUserRoles = bean.getSysUserRoles();
    for (SysUserRole sysUserRole : sysUserRoles) {
        sysUserRoleService.delete(sysUserRole);
    }
    sysUserRoleService.getSession().flush();
    if (roleIds != null) {
        if (roleIds.length > 0) {
            for (String roleId : roleIds) {
                SysRole role = sysRoleService.get(Long.valueOf(roleId));
                SysUserRole userRole = new SysUserRole();
                userRole.setUser(bean);
                userRole.setRole(role);
                sysUserRoleService.save(userRole);
            }
        }
    }
}


public void loadUserPrivileges(Long userId,Boolean isForce){
    if (userId != null) {
        if (USER_PRIVILEGE_CODES == null) {
            USER_PRIVILEGE_CODES = new HashMap<Long, Set<String>>();
        }
        if (isForce || !USER_PRIVILEGE_CODES.containsKey(userId)) {
            USER_PRIVILEGE_CODES.put(userId, getUserPrivilegeCodes(userId));
        }
    }
}


public Boolean hasPrivilege(Long userId,String privilegeCode){
    return getUserPrivilegeCodesCache(userId).contains(privilegeCode);
}


public Set<String> getUserPrivilegeCodesCache(Long userId){
    if (userId != null) {
        loadUserPrivileges(userId, false);
        Set<String> privilegeSet = USER_PRIVILEGE_CODES.get(userId);
        if (privilegeSet != null) {
            return privilegeSet;
        }
    }
    return new HashSet<String>();
}


public Set<String> getUserPrivilegeCodes(Long userId){
    Set<String> ret = new HashSet<String>();
    Set<String> deniedList = new HashSet<String>();
    if (userId != null) {
        SysUser user = sysUserService.get(userId);
        // 角色权限
        Set<SysUserRole> userRoles = user.getSysUserRoles();
        for (SysUserRole userRole : userRoles) {
            Set<SysRolePrivilege> rolePrivileges = userRole.getRole().getSysRolePrivileges();
            for (SysRolePrivilege rolePrivilege : rolePrivileges) {
                ret.add(JspHelper.getString(rolePrivilege.getPrivilege().getCode()));
            }
        }
        // 用户权限
        Set<SysUserPrivilege> sysUserPrivileges = user.getSysUserPrivileges();
        for (SysUserPrivilege sysUserPrivilege : sysUserPrivileges) {
            SysPrivilege sysPrivilege = sysUserPrivilege.getPrivilege();
            if (null == sysUserPrivilege.getIsDeny() || !sysUserPrivilege.getIsDeny()) {
                ret.add(JspHelper.getString(sysPrivilege.getCode()));
            } else {
                deniedList.add(JspHelper.getString(sysPrivilege.getCode()));
            }
        }
        // 用户禁止权限
        ret.removeAll(deniedList);
    }
    // System.out.println("ret = " + ret);
    return ret;
}


public String getSysUserNames(String[] userIds){
    String names = "";
    for (String uid : userIds) {
        if (!StringHelper.isEmpty(uid)) {
            try {
                if ("".equals(names)) {
                    names += sysUserService.get(Long.valueOf(uid)).getDisplayName();
                } else {
                    names += "," + sysUserService.get(Long.valueOf(uid)).getDisplayName();
                }
            } catch (NumberFormatException e) {
                continue;
            }
        }
    }
    return names;
}


public String[] getPersonIds(String[] userIds){
    if (userIds != null) {
        if (userIds.length > 0) {
            String s = "";
            for (String userId : userIds) {
                SysUser sysUser = sysUserService.get(Long.valueOf(userId));
                SysPerson person = sysUser.getPerson();
                if (person != null) {
                    if (!"".equals(s)) {
                        s += "," + person.getId();
                    } else {
                        s += person.getId();
                    }
                }
            }
            return StringHelper.stringToStringArray(s, ",");
        }
    }
    return null;
}


public List<SysPrivilege> getUserPrivilegeCodesForSecurity(Long userId){
    List<SysPrivilege> ret = new ArrayList<SysPrivilege>();
    Set<SysPrivilege> deniedList = new HashSet<SysPrivilege>();
    if (userId != null) {
        SysUser user = sysUserService.get(userId);
        // 角色权限
        Set<SysUserRole> userRoles = user.getSysUserRoles();
        for (SysUserRole userRole : userRoles) {
            Set<SysRolePrivilege> rolePrivileges = userRole.getRole().getSysRolePrivileges();
            for (SysRolePrivilege rolePrivilege : rolePrivileges) {
                ret.add(rolePrivilege.getPrivilege());
            }
        }
        // 用户权限
        Set<SysUserPrivilege> sysUserPrivileges = user.getSysUserPrivileges();
        for (SysUserPrivilege sysUserPrivilege : sysUserPrivileges) {
            SysPrivilege sysPrivilege = sysUserPrivilege.getPrivilege();
            if (null == sysUserPrivilege.getIsDeny() || !sysUserPrivilege.getIsDeny()) {
                ret.add(sysPrivilege);
            } else {
                deniedList.add(sysPrivilege);
            }
        }
        // 用户禁止权限
        ret.removeAll(deniedList);
    }
    return ret;
}


public void delete(Long id){
    SysUser bean = sysUserService.get(id);
    // 删除角色授权
    Set<SysUserRole> sysUserRoles = bean.getSysUserRoles();
    for (SysUserRole sysUserRole : sysUserRoles) {
        sysUserRoleService.delete(sysUserRole);
    }
    // 删除用户特权
    Set<SysUserPrivilege> sysUserPrivileges = bean.getSysUserPrivileges();
    for (SysUserPrivilege sysUserPrivilege : sysUserPrivileges) {
        sysUserPrivilegeService.delete(sysUserPrivilege);
    }
    // 删除用户
    sysUserService.delete(id);
}


public SysUser getSysUser(){
    BaseUser loginUser = SpringSecurityUtils.getCurrentUser();
    if (loginUser != null) {
        return getSysUser(loginUser.getLoginName());
    }
    return null;
}


public String getDisplayName(String loginName){
    SysUser sysUser = getSysUser(loginName);
    if (sysUser != null) {
        return sysUser.getDisplayName();
    }
    return "";
}


public List<Map<String,Object>> getRoleList(SysUser bean){
    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    Set<Long> existHS = new HashSet<Long>();
    Set<SysUserRole> sysUserRoles = bean.getSysUserRoles();
    for (SysUserRole sysUserRole : sysUserRoles) {
        existHS.add(sysUserRole.getRole().getId());
    }
    List<SysRole> roleList = sysRoleService.findByQuery("from SysRole where code <> 'SUPER_ADMIN'  order by id asc ");
    for (SysRole role : roleList) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("role", role);
        map.put("check", existHS.contains(role.getId()));
        list.add(map);
    }
    return list;
}


public SysUser getAdminUser(){
    List<SysUser> sysUserList = sysUserService.findByQuery("from SysUser where loginName='" + Constants.SYS_USER_ADMIN + "'");
    if (sysUserList.size() > 0) {
        return sysUserList.iterator().next();
    } else {
        return null;
    }
}


}