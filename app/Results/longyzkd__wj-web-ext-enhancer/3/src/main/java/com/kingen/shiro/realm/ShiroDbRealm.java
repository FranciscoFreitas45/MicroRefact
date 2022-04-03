package com.kingen.shiro.realm;
 import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.kingen.bean.Menu;
import com.kingen.bean.User;
import com.kingen.service.account.AccountService;
import com.kingen.shiro.credentials.RetryLimitHashedCredentialsMatcher;
import com.kingen.util.Encodes;
public class ShiroDbRealm extends AuthorizingRealm{

 private  Logger logger;

@Autowired
 protected  AccountService accountService;


@Override
public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals){
    logger.info("doGetAuthorizationInfo----");
    User user = (User) principals.getPrimaryPrincipal();
    // Authorization 授权，即权限验证，验证某个已认证的用户是否拥有某个权限
    SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
    try {
        Set<String> roles = new HashSet<String>();
        // 本系统设计为一个用户属于一个（多个）用户组，即用户组就是用户的角色（employee、finance、hr、boss..）；每个用户组有不同的权限（资源）
        // 在本系统中 除了管理员是admin其他组都用user标识，除了老板，其他用户组的操作都和员工组一样的。
        roles.add("admin".equals(user.getUserId()) ? "admin" : "user");
        List<Menu> menus = accountService.findMenuByuserId(user.getUserId());
        Set<String> resources = new HashSet<String>();
        for (Menu m : menus) {
            if (!StringUtils.isEmpty(m.getFunId())) {
                // 一级菜单的权限字符串 为NULL，不需要对一级菜单进行认证
                resources.add(m.getFunId());
            }
        }
        authorizationInfo.setRoles(roles);
        authorizationInfo.setStringPermissions(resources);
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        logger.error("realm 错误！");
    }
    return authorizationInfo;
}


public void setAccountService(AccountService accountService){
    this.accountService = accountService;
}


@Override
public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken){
    logger.info("doGetAuthenticationInfo----");
    UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
    User user = null;
    try {
        user = accountService.findUserByLoginName(token.getUsername());
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    if (user != null) {
        // 16进制的
        byte[] salt = Encodes.decodeHex(user.getSalt());
        // user 可以自定义一个principal类
        return new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(salt), getName());
    // return new SimpleAuthenticationInfo();
    } else {
        return null;
    }
}


}