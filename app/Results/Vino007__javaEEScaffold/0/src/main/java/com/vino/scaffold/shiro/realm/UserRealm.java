package com.vino.scaffold.shiro.realm;
 import java.util.Date;
import org.apache.shiro.authc;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import com.vino.scaffold.shiro.entity.User;
import com.vino.scaffold.shiro.service.UserService;
public class UserRealm extends AuthorizingRealm{

 private  UserService userService;


@Override
public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals){
    String username = (String) principals.getPrimaryPrincipal();
    SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
    // ��ѯ�û��Ľ�ɫ����ƾ֤��
    authorizationInfo.setRoles(userService.findAllRoleNamesByUsername(username));
    // ��ѯ�û�Ȩ�޷���ƾ֤��
    authorizationInfo.setStringPermissions(userService.findAllPermissionsByUsername(username));
    return authorizationInfo;
}


public void clearAllCachedAuthorizationInfo(){
    getAuthorizationCache().clear();
}


public void clearAllCache(){
    clearAllCachedAuthenticationInfo();
    clearAllCachedAuthorizationInfo();
}


public void clearAllCachedAuthenticationInfo(){
    getAuthenticationCache().clear();
}


public void setUserService(UserService userService){
    this.userService = userService;
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
    String username = (String) token.getPrincipal();
    User user = userService.findByUsername(username);
    // ���µ�¼ʱ��
    User curUser = userService.findByUsername(username);
    if (curUser.getLoginTime() != null) {
        curUser.setLastLoginTime(curUser.getLoginTime());
    }
    curUser.setLoginTime(new Date());
    userService.update(curUser);
    System.out.println("doGetAuthenticationInfo ��¼");
    if (user == null) {
        // û�ҵ��ʺ�
        throw new UnknownAccountException();
    }
    if (Boolean.TRUE.equals(user.getLocked())) {
        // �ʺ�����
        throw new LockedAccountException();
    }
    // ����AuthenticatingRealmʹ��CredentialsMatcher��������ƥ�䣬��������˼ҵĲ��ÿ����Զ���ʵ��
    SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(// �û���
    user.getUsername(), // ����
    user.getPassword(), // salt=username+salt
    ByteSource.Util.bytes(user.getSalt()), // realm name
    getName());
    return authenticationInfo;
}


}