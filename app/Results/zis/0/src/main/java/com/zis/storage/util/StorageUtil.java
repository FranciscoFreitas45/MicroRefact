package com.zis.storage.util;
 import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.subject.Subject;
import com.zis.shiro.dto.ActiveUser;
import com.zis.shiro.realm.CustomRealm;
public class StorageUtil {


public Integer getRepoId(){
    Subject user = SecurityUtils.getSubject();
    ActiveUser au = (ActiveUser) user.getPrincipals().getPrimaryPrincipal();
    Integer stockId = au.getStockId();
    if (stockId == null) {
        throw new RuntimeException("您没有公司没有仓库，请联系管理员添加仓库后重新登录");
    }
    return stockId;
}


public Integer getCompanyId(){
    Subject user = SecurityUtils.getSubject();
    ActiveUser au = (ActiveUser) user.getPrincipals().getPrimaryPrincipal();
    Integer companyId = au.getCompanyId();
    if (companyId == null) {
        throw new RuntimeException("您没有公司，请联系管理员添加公司后重新登录");
    }
    return companyId;
}


public String getUserName(){
    Subject user = SecurityUtils.getSubject();
    ActiveUser au = (ActiveUser) user.getPrincipals().getPrimaryPrincipal();
    return au.getUserName();
}


public String getRealName(){
    Subject user = SecurityUtils.getSubject();
    ActiveUser au = (ActiveUser) user.getPrincipals().getPrimaryPrincipal();
    return au.getRealName();
}


public Integer getUserId(){
    Subject user = SecurityUtils.getSubject();
    ActiveUser au = (ActiveUser) user.getPrincipals().getPrimaryPrincipal();
    return au.getUserId();
}


public void clearAllCached(){
    RealmSecurityManager securityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
    CustomRealm userRealm = (CustomRealm) securityManager.getRealms().iterator().next();
    userRealm.clearAllCachedAuthorizationInfo();
    userRealm.clearAllCachedAuthenticationInfo();
}


}