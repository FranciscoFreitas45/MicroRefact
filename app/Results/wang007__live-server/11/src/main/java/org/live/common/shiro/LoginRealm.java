package org.live.common.shiro;
 import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.live.common.constants.Constants;
import org.live.common.exception.ServiceException;
import org.live.sys.entity.User;
import org.live.sys.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.Resource;
import java.util.List;
public class LoginRealm extends AuthorizingRealm{

 private  Logger LOGGER;

@Resource
 private  UserService userService;


@Override
public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals){
    SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
    // 账号
    String username = (String) principals.getPrimaryPrincipal();
    try {
        // 设置角色值
        info.addRoles(userService.getRoleValuesByUsername(username));
        // 设置权限值
        info.addStringPermissions(userService.getPermissionValuesByUsername(username));
    } catch (Exception e) {
        LOGGER.error("用户:{},授权异常", username, e);
    }
    return info;
}


@Override
public void clearCachedAuthorizationInfo(PrincipalCollection principals){
    super.clearCachedAuthorizationInfo(principals);
}


@Override
public void clearCache(PrincipalCollection principals){
    super.clearCache(principals);
}


@Override
public void clearCachedAuthenticationInfo(PrincipalCollection principals){
    super.clearCachedAuthenticationInfo(principals);
}


@Override
public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException{
    // 账号
    String username = (String) token.getPrincipal();
    User user = null;
    try {
        List<User> users = userService.findByUsername(username);
        // 用户数量
        int length = users == null ? 0 : users.size();
        user = length > 0 ? users.get(0) : null;
        if (length == 0) {
            throw new UnknownAccountException("用户不存在");
        } else if (length != 1) {
            new AccountException("用户信息重复");
        } else if (user.getIsDelete() == Constants.DIC_YES) {
            throw new UnknownAccountException("用户不存在");
        } else if (user.getIsLock() == Constants.DIC_YES) {
            throw new LockedAccountException("用户被锁定");
        } else {
            Session session = SecurityUtils.getSubject().getSession();
            session.setAttribute(Constants.CURRENT_LOGINUSER_KEY, user);
            session.setAttribute(Constants.CURRENT_USERTYPE_KEY, user.getUserType());
        }
    } catch (ServiceException e) {
        throw new AccountException("用户登录验证异常");
    }
    // 使用加盐
    return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), this.getName());
}


}