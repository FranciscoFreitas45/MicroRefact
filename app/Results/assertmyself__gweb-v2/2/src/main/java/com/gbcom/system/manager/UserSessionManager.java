package com.gbcom.system.manager;
 import com.gbcom.system.daoservice.SysUserService;
import com.gbcom.system.domain.SysPerson;
import com.gbcom.system.domain.SysUser;
import com.hc.core.security.user.BaseUser;
import com.hc.core.security.util.SpringSecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserSessionManager {

@Autowired
 private  SysUserService sysUserService;


public SysPerson getSysPerson(){
    SysUser sysUser = getLoginedUser();
    if (sysUser != null) {
        return sysUser.getPerson();
    }
    return null;
}


public SysUser getLoginedUser(){
    Long userId = getLoginedUserId();
    if (userId != null) {
        return sysUserService.get(userId);
    }
    return null;
}


public Long getLoginedUserId(){
    BaseUser loginUser = SpringSecurityUtils.getCurrentUser();
    if (loginUser != null) {
        return loginUser.getId();
    }
    return null;
}


}