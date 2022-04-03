package cn.maxcj.core.shiro.service;
 import cn.maxcj.core.shiro.ShiroUser;
import cn.maxcj.modular.system.model.User;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import java.util.List;
public interface UserAuthService {


public List<String> findPermissionsByRoleId(Integer roleId)
;

public String findRoleNameByRoleId(Integer roleId)
;

public User user(String account)
;

public ShiroUser shiroUser(User user)
;

public SimpleAuthenticationInfo info(ShiroUser shiroUser,User user,String realmName)
;

}