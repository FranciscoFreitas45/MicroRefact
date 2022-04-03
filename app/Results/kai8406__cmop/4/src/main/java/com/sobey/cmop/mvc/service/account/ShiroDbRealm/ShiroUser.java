package com.sobey.cmop.mvc.service.account.ShiroDbRealm;
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
public class ShiroUser implements Serializable{

 private  long serialVersionUID;

 public  Integer id;

 public  String loginName;

 public  String name;

public ShiroUser(Integer id, String loginName, String name) {
    this.id = id;
    this.loginName = loginName;
    this.name = name;
}
public String getName(){
    return name;
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


@Override
public String toString(){
    return loginName;
}


}