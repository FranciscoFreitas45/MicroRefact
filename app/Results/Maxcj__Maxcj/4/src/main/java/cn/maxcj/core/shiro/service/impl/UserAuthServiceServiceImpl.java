package cn.maxcj.core.shiro.service.impl;
 import cn.hutool.core.convert.Convert;
import cn.maxcj.core.common.constant.factory.ConstantFactory;
import cn.maxcj.core.shiro.ShiroUser;
import cn.maxcj.core.shiro.service.UserAuthService;
import cn.maxcj.modular.system.dao.MenuMapper;
import cn.maxcj.modular.system.dao.UserMapper;
import cn.maxcj.modular.system.model.User;
import cn.maxcj.core.common.constant.state.ManagerStatus;
import cn.stylefeng.roses.core.util.SpringContextHolder;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import cn.maxcj.Interface.MenuMapper;
@Service
@DependsOn("springContextHolder")
@Transactional(readOnly = true)
public class UserAuthServiceServiceImpl implements UserAuthService{

@Autowired
 private  UserMapper userMapper;

@Autowired
 private  MenuMapper menuMapper;


@Override
public List<String> findPermissionsByRoleId(Integer roleId){
    return menuMapper.getResUrlsByRoleId(roleId);
}


public UserAuthService me(){
    return SpringContextHolder.getBean(UserAuthService.class);
}


@Override
public String findRoleNameByRoleId(Integer roleId){
    return ConstantFactory.me().getSingleRoleTip(roleId);
}


@Override
public User user(String account){
    User user = userMapper.getByAccount(account);
    // 账号不存在
    if (null == user) {
        throw new CredentialsException();
    }
    // 账号被冻结
    if (user.getStatus() != ManagerStatus.OK.getCode()) {
        throw new LockedAccountException();
    }
    return user;
}


@Override
public ShiroUser shiroUser(User user){
    ShiroUser shiroUser = new ShiroUser();
    shiroUser.setId(user.getId());
    shiroUser.setAccount(user.getAccount());
    shiroUser.setDeptId(user.getDeptid());
    shiroUser.setDeptName(ConstantFactory.me().getDeptName(user.getDeptid()));
    shiroUser.setName(user.getName());
    Integer[] roleArray = Convert.toIntArray(user.getRoleid());
    List<Integer> roleList = new ArrayList<Integer>();
    List<String> roleNameList = new ArrayList<String>();
    for (int roleId : roleArray) {
        roleList.add(roleId);
        roleNameList.add(ConstantFactory.me().getSingleRoleName(roleId));
    }
    shiroUser.setRoleList(roleList);
    shiroUser.setRoleNames(roleNameList);
    return shiroUser;
}


@Override
public SimpleAuthenticationInfo info(ShiroUser shiroUser,User user,String realmName){
    String credentials = user.getPassword();
    // 密码加盐处理
    String source = user.getSalt();
    ByteSource credentialsSalt = new Md5Hash(source);
    return new SimpleAuthenticationInfo(shiroUser, credentials, credentialsSalt, realmName);
}


}