package com.ipe.module.core.web.security;
 import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ipe.module.core.entity.Log;
import com.ipe.module.core.entity.Resource;
import com.ipe.module.core.entity.Role;
import com.ipe.module.core.entity.User;
import com.ipe.module.core.service.LogService;
import com.ipe.module.core.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
public class SystemRealm extends AuthorizingRealm{

 private  UserService userService;

 private  LogService logService;

 private  String userId;

 private  String userAccount;

 private  String userName;

 private  Date userLoginTime;


public Date getUserLoginTime(){
    return userLoginTime;
}


public void setLogService(LogService logService){
    this.logService = logService;
}


@Override
public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection){
    UserInfo user = (UserInfo) getAvailablePrincipal(principalCollection);
    // String userId =(String)principalCollection.fromRealm(getName()).iterator().next();
    SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
    // User user=userService.get(userId.getId());
    String userId = user.getUserId();
    // 得到用户所拥有有角色
    List<Role> roles = userService.getUserRole(userId);
    for (Role role : roles) {
        info.addRole(role.getRoleName());
        // 得到用户所拥有的资源权限
        Set<Resource> resources = userService.getUserAuthority(role.getId());
        for (Resource resource : resources) {
            info.addStringPermission(resource.getResourceUrl());
        }
    }
    return info;
}


public String getUserAccount(){
    return userAccount;
}


public void setUserService(UserService userService){
    this.userService = userService;
}


public String getUserName(){
    return userName;
}


public User getLoginUser(AuthenticationToken authenticationToken){
    CustUsernamePasswordToken upToken = (CustUsernamePasswordToken) authenticationToken;
    upToken.setRememberMe(true);
    List<User> users = userService.login(upToken.getUsername(), String.valueOf(upToken.getPassword()));
    User user = users.get(0);
    if (user != null) {
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("user", JSON.toJSONString(user, SerializerFeature.WriteDateUseDateFormat));
        // /保存日志
        Log log = new Log();
        log.setAccessIp(upToken.getHost());
        log.setAccessMethod("login");
        log.setAccessPerson(user.getUserAccount() + "-" + user.getUserName());
        log.setAccessTime(new Date());
        log.setOperate(upToken.getMethod() + "_" + upToken.getAccessUrl());
        log.setLogType("01");
        log.setAccessUserid(user.getId());
        logService.save(log);
        return user;
    }
    return null;
}


public String getUserId(){
    return userId;
}


@Override
public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken){
    User user = getLoginUser(authenticationToken);
    if (user != null) {
        return new SimpleAuthenticationInfo(new UserInfo(user), user.getUserPwd(), getName());
    }
    return null;
}


}