package com.sobey.cmop.mvc.service.account;
 import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import com.google.common.base.Objects;
import com.sobey.cmop.mvc.constant.AccountConstant;
import com.sobey.cmop.mvc.entity.Group;
import com.sobey.cmop.mvc.entity.User;
import com.sobey.framework.utils.Encodes;
public class ShiroDbRealm extends AuthorizingRealm{

 private  AccountService accountService;

 private  long serialVersionUID;

 public  Integer id;

 public  String loginName;

 public  String name;


@PostConstruct
public void initCredentialsMatcher(){
    HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(AccountConstant.HASH_ALGORITHM);
    matcher.setHashIterations(AccountConstant.HASH_INTERATIONS);
    setCredentialsMatcher(matcher);
}


@Override
public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals){
    ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
    User user = accountService.findUserByLoginName(shiroUser.loginName);
    if (user != null) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        for (Group group : user.getGroupList()) {
            // 基于Permission的权限信息
            info.addStringPermissions(group.getPermissionList());
        }
        return info;
    } else {
        return null;
    }
}


public void clearAllCachedAuthorizationInfo(){
    Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
    if (cache != null) {
        for (Object key : cache.keys()) {
            cache.remove(key);
        }
    }
}


public String getName(){
    return name;
}


@Resource
public void setAccountService(AccountService accountService){
    this.accountService = accountService;
}


@Override
public int hashCode(){
    return Objects.hashCode(loginName);
}


@Override
public boolean equals(Object obj){
    if (this == obj) {
        return true;
    }
    if (obj == null) {
        return false;
    }
    if (getClass() != obj.getClass()) {
        return false;
    }
    ShiroUser other = (ShiroUser) obj;
    if (loginName == null) {
        if (other.loginName != null) {
            return false;
        }
    } else if (!loginName.equals(other.loginName)) {
        return false;
    }
    return true;
}


public void clearCachedAuthorizationInfo(String principal){
    SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
    clearCachedAuthorizationInfo(principals);
}


@Override
public String toString(){
    return loginName;
}


@Override
public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken){
    UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
    User user = accountService.findUserByLoginName(token.getUsername());
    // 默认为自动登录.
    token.setRememberMe(true);
    if (user != null) {
        byte[] salt = Encodes.decodeHex(user.getSalt());
        return new SimpleAuthenticationInfo(new ShiroUser(user.getId(), user.getLoginName(), user.getName()), user.getPassword(), ByteSource.Util.bytes(salt), getName());
    } else {
        return null;
    }
}


}