package cn.maxcj.core.shiro;
 import cn.maxcj.modular.system.model.User;
import cn.maxcj.core.shiro.service.UserAuthService;
import cn.maxcj.core.shiro.service.impl.UserAuthServiceServiceImpl;
import cn.stylefeng.roses.core.util.ToolUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class ShiroDbRealm extends AuthorizingRealm{


@Override
public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals){
    UserAuthService shiroFactory = UserAuthServiceServiceImpl.me();
    ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
    List<Integer> roleList = shiroUser.getRoleList();
    Set<String> permissionSet = new HashSet<>();
    Set<String> roleNameSet = new HashSet<>();
    for (Integer roleId : roleList) {
        List<String> permissions = shiroFactory.findPermissionsByRoleId(roleId);
        if (permissions != null) {
            for (String permission : permissions) {
                if (ToolUtil.isNotEmpty(permission)) {
                    permissionSet.add(permission);
                }
            }
        }
        String roleName = shiroFactory.findRoleNameByRoleId(roleId);
        roleNameSet.add(roleName);
    }
    SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
    info.addStringPermissions(permissionSet);
    info.addRoles(roleNameSet);
    return info;
}


@Override
public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher){
    HashedCredentialsMatcher md5CredentialsMatcher = new HashedCredentialsMatcher();
    md5CredentialsMatcher.setHashAlgorithmName(ShiroKit.hashAlgorithmName);
    md5CredentialsMatcher.setHashIterations(ShiroKit.hashIterations);
    super.setCredentialsMatcher(md5CredentialsMatcher);
}


@Override
public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken){
    UserAuthService shiroFactory = UserAuthServiceServiceImpl.me();
    UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
    User user = shiroFactory.user(token.getUsername());
    ShiroUser shiroUser = shiroFactory.shiroUser(user);
    return shiroFactory.info(shiroUser, user, super.getName());
}


}