package com.uec.imonitor.auth;
 import java.util.List;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.user.bean.UserEntity;
import com.uec.imonitor.user.service.IUserService;
public class MyShiroRealm extends AuthorizingRealm{

 private  Logger logger;

@Autowired
@Qualifier("userService")
 private  IUserService userService;


@Override
public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection){
    logger.info("##################执行Shiro权限认证##################");
    // 获取当前登录输入的用户名，等价于(String) principalCollection.fromRealm(getName()).iterator().next();
    String loginName = (String) super.getAvailablePrincipal(principalCollection);
    logger.info("----------------------login name=" + loginName + "-----------------------");
    // 到数据库查是否有此对象
    SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
    try {
    // List<RoleEntity> roles = roleService.findByUserName(loginName);
    // if(roles==null||roles.isEmpty()){
    // logger.error("*******************用户角色为空！***************************");
    // }else{
    // for(RoleEntity role:roles){
    // info.addRole(role.getName());
    // //					logger.info("##为用户----"+loginName+"----获取角色###"+role.getName());
    // //					List<Permission> perms=roleAdapterService.findPermissionsByRoleId(role.getId()+"");
    // //					if(perms!=null&&!perms.isEmpty()){
    // //						for(Permission perm:perms){
    // //							info.addStringPermission(perm.getCode());
    // ////							logger.info("##为用户----"+loginName+"----获取角色----"+role.getName()+"----下的权限："+perm.getName()+"---"+perm.getCode());
    // //						}
    // //					}
    // }
    // }
    } catch (Exception e) {
        // TODO Auto-generated catch block
        logger.error("获取用户角色权限失败!");
    }
    // 返回null的话，就会导致任何用户访问被拦截的请求时，都会自动跳转到unauthorizedUrl指定的地址
    return info;
}


@Override
public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken){
    UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
    try {
        UserEntity user = userService.findByUserName(token.getUsername());
        if (null != user) {
            // 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
            return new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), getName());
        }
    } catch (BaseException e) {
        logger.error(e.getMessage());
    } catch (Exception e) {
        logger.error(e.getMessage());
    }
    return null;
}


}