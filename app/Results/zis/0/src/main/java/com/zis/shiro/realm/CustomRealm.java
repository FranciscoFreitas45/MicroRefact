package com.zis.shiro.realm;
 import java.util.ArrayList;
import java.util.List;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import com.zis.shiro.bean.Permission;
import com.zis.shiro.bean.Role;
import com.zis.shiro.bean.User;
import com.zis.shiro.dto.ActiveUser;
import com.zis.shiro.service.SysService;
public class CustomRealm extends AuthorizingRealm{

 private  String OR_OPERATOR;

 private  String AND_OPERATOR;

 private  String NOT_OPERATOR;

@Autowired
 private  SysService sysService;


@Override
public void setName(String name){
    super.setName("customRealm");
}


public void clearCached(){
    PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
    super.clearCache(principals);
}


@Override
public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals){
    // 从 principals获取主身份信息
    // 将getPrimaryPrincipal方法返回值转为真实身份类型（在上边的doGetAuthenticationInfo认证通过填充到SimpleAuthenticationInfo中身份类型），
    ActiveUser activeUser = (ActiveUser) principals.getPrimaryPrincipal();
    // 根据身份信息获取权限信息
    // 从数据库获取到权限数据
    List<Permission> permissionList = null;
    // 根据身份信息获取角色信息
    // 从数据库获取到角色数据
    List<Role> RoleList = null;
    try {
        permissionList = sysService.findPermissionByUserId(activeUser.getUserId());
    } catch (Exception e) {
        e.printStackTrace();
    }
    try {
        RoleList = sysService.findRoleByUserId(activeUser.getUserId());
    } catch (Exception e) {
        e.printStackTrace();
    }
    // 单独定一个集合对象
    List<String> permissions = new ArrayList<String>();
    if (permissionList != null) {
        for (Permission sysPermission : permissionList) {
            // 将数据库中的权限标签 符放入集合
            permissions.add(sysPermission.getUrl());
        }
    }
    // 单独定一个集合对象
    String roleCode = null;
    if (RoleList != null) {
        // 将数据库中的角色标签 符放入角色
        roleCode = RoleList.get(0).getRoleCode();
    }
    // 查到权限数据，返回授权信息(要包括 上边的permissions)
    SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
    // 将上边查询到授权信息填充到simpleAuthorizationInfo对象中
    simpleAuthorizationInfo.addStringPermissions(permissions);
    // 将上边查询到角色信息填充到simpleAuthorizationInfo对象中
    simpleAuthorizationInfo.addRole(roleCode);
    return simpleAuthorizationInfo;
}


public boolean isPermittedWithNotOperator(PrincipalCollection principals,String permission){
    if (permission.startsWith(NOT_OPERATOR)) {
        return !super.isPermitted(principals, permission.substring(NOT_OPERATOR.length()));
    } else {
        return super.isPermitted(principals, permission);
    }
}


public void clearAllCachedAuthorizationInfo(){
    getAuthorizationCache().clear();
}


public void clearAllCache(){
    clearAllCachedAuthenticationInfo();
    clearAllCachedAuthorizationInfo();
}


public boolean isPermitted(PrincipalCollection principals,String permission){
    if (permission.contains(OR_OPERATOR)) {
        String[] permissions = permission.split(OR_OPERATOR);
        for (String orPermission : permissions) {
            if (isPermittedWithNotOperator(principals, orPermission)) {
                return true;
            }
        }
        return false;
    } else if (permission.contains(AND_OPERATOR)) {
        String[] permissions = permission.split(AND_OPERATOR);
        for (String orPermission : permissions) {
            if (!isPermittedWithNotOperator(principals, orPermission)) {
                return false;
            }
        }
        return true;
    } else {
        return isPermittedWithNotOperator(principals, permission);
    }
}


public void clearAllCachedAuthenticationInfo(){
    getAuthenticationCache().clear();
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
public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token){
    // token是用户输入的用户名和密码
    // 第一步从token中取出用户名
    String userCode = (String) token.getPrincipal();
    // 第二步：根据用户输入的userCode从数据库查询
    User sysUser = null;
    sysUser = sysService.findSysUserByUserName(userCode);
    // 如果查询不到返回null
    if (sysUser == null) {
        // 
        return null;
    }
    // 从数据库查询到密码
    String password = sysUser.getPassword();
    // 盐
    String salt = sysUser.getSalt();
    // 如果查询到返回认证信息AuthenticationInfo
    // activeUser就是用户身份信息
    ActiveUser activeUser = new ActiveUser();
    activeUser.setUserId(sysUser.getId());
    activeUser.setUserName(sysUser.getUserName());
    activeUser.setRealName(sysUser.getRealName());
    // 根据用户id取出菜单
    List<Permission> permissions = null;
    try {
        // 通过service取出菜单
        permissions = sysService.findPermissionByUserId(sysUser.getId());
    } catch (Exception e) {
        e.printStackTrace();
    }
    // 将用户菜单 设置到activeUser
    activeUser.setPermissions(permissions);
    activeUser.setCompanyId(sysUser.getCompanyId());
    activeUser.setStockId(this.sysService.findStorageRepoInfoId(sysUser.getCompanyId()));
    // 将activeUser设置simpleAuthenticationInfo
    SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(activeUser, password, ByteSource.Util.bytes(salt), this.getName());
    return simpleAuthenticationInfo;
}


}