package com.zis.shop.util;
 import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.subject.Subject;
import com.zis.shiro.dto.ActiveUser;
import com.zis.shiro.realm.CustomRealm;
public class ShopUtil {


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


public void clearAllCached(){
    RealmSecurityManager securityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
    CustomRealm userRealm = (CustomRealm) securityManager.getRealms().iterator().next();
    userRealm.clearAllCachedAuthorizationInfo();
    userRealm.clearAllCachedAuthenticationInfo();
}


}