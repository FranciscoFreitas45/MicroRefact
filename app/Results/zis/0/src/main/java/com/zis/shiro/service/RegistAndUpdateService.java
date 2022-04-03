package com.zis.shiro.service;
 import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.subject.Subject;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.zis.shiro.bean.Permission;
import com.zis.shiro.bean.Role;
import com.zis.shiro.bean.RolePermission;
import com.zis.shiro.bean.User;
import com.zis.shiro.dto.ActiveUser;
import com.zis.shiro.dto.CreatePermissionDto;
import com.zis.shiro.dto.GeneralUserPasswordUpdateDTO;
import com.zis.shiro.dto.RegistRoleDto;
import com.zis.shiro.dto.RegistUserDto;
import com.zis.shiro.dto.UpdateUserInfo;
import com.zis.shiro.realm.CustomRealm;
import com.zis.shiro.repository.PermissionDao;
import com.zis.shiro.repository.RoleDao;
import com.zis.shiro.repository.RolePermissionDao;
import com.zis.shiro.repository.UserDao;
import com.zis.shop.bean.Company;
import com.zis.shop.dto.CompanyAndStockDto;
import com.zis.shop.repository.CompanyDao;
import com.zis.Interface.StorageRepoInfoDao;
@Service
public class RegistAndUpdateService {

 protected  String NO;

 protected  String YES;

 protected  String NORMAL;

 protected  String DELETE;

 protected  String BOOK_INFO;

 protected  String PURCHASE;

 protected  String REQUIREMENT;

 protected  String TOOLKIT;

 protected  String SHIRO;

 protected  String STOCK;

 protected  String DATA;

 protected  String SHOP;

 protected  String ORDER;

@Autowired
 private  UserDao userDao;

@Autowired
 private  RoleDao roleDao;

@Autowired
 private  PermissionDao permissionDao;

@Autowired
 private  RolePermissionDao rolePermissionDao;

@Autowired
 private  CompanyDao companyDao;

@Autowired
 private  StorageRepoInfoDao storageRepoInfoDao;

 private  HashMap<String,List<Permission>> permissions;


public Page<UpdateUserInfo> findUserInfo(String userName,String realName,String companyName,Pageable page){
    if (StringUtil.isBlank(userName)) {
        if (!StringUtil.isBlank(companyName) && !StringUtils.isBlank(realName)) {
            // 查询出来可能为多条记录
            return this.userDao.findUpdateUserInfoByCompanyNameLikeAndRealName(companyName, realName, page);
        } else if (!StringUtils.isBlank(realName)) {
            // 查询出来可能为多条记录
            return this.userDao.findUpdateUserInfoByRealName(realName, page);
        } else if (!StringUtil.isBlank(companyName)) {
            // 查询出来为多条记录
            return this.userDao.findUpdateUserInfoByCompanyNameLike(companyName, page);
        } else {
            // 查询出来为多条记录
            return this.userDao.findUserAllOrderByUserUpdateTimeDesc(page);
        }
    } else {
        // 查询出来为单条记录
        return this.userDao.findUpdateUserInfoByUserName(userName, page);
    }
}


public void findAllPermission(){
    if (permissions == null) {
        permissions = new HashMap<String, List<Permission>>();
        permissions.put(BOOK_INFO, this.permissionDao.findByGroupName(BOOK_INFO));
        permissions.put(PURCHASE, this.permissionDao.findByGroupName(PURCHASE));
        permissions.put(REQUIREMENT, this.permissionDao.findByGroupName(REQUIREMENT));
        permissions.put(TOOLKIT, this.permissionDao.findByGroupName(TOOLKIT));
        permissions.put(SHIRO, this.permissionDao.findByGroupName(SHIRO));
        permissions.put(STOCK, this.permissionDao.findByGroupName(STOCK));
        permissions.put(DATA, this.permissionDao.findByGroupName(DATA));
        permissions.put(SHOP, this.permissionDao.findByGroupName(SHOP));
        permissions.put(ORDER, this.permissionDao.findByGroupName(ORDER));
    }
}


public List<UpdateUserInfo> findAllUserByCompanyId(Integer companyId){
    List<UpdateUserInfo> list = this.userDao.findAllUserByCompanyId(companyId);
    return list;
}


public List<Permission> getGroupPermissions(String groupName){
    if (permissions == null) {
        findAllPermission();
    }
    List<Permission> list = permissions.get(groupName);
    return list;
}


public Role findRoleByRoleId(Integer roleId){
    return this.roleDao.findRoleByRoleId(roleId);
}


public String getSalt(){
    Integer length = new Random().nextInt(10) + 20;
    String base = "abcdefghijklmnopqrstuvwxyz0123456789";
    Random random = new Random();
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < length; i++) {
        int number = random.nextInt(base.length());
        sb.append(base.charAt(number));
    }
    return sb.toString();
}


public List<Role> findAllRole(){
    List<Role> roleList = (List<Role>) this.roleDao.findByIdNotEqZeroAll();
    return roleList;
}


public User findOneUser(Integer userId){
    User user = this.userDao.findUserByUserId(userId);
    return user;
}


public String getPasswordMD5(String password,String salt){
    return new SimpleHash("md5", password, salt).toString();
}


@Transactional
public void deleteRole(Integer roleId){
    Role role = this.roleDao.findOne(roleId);
    // 删除角色时查询所有有权限的用户，将其权限值设置成空
    List<User> list = this.userDao.findByRoleId(roleId);
    for (User u : list) {
        u.setUpdateTime(new Date());
        u.setRoleId(0);
    }
    role.setUpdateTime(new Date());
    role.setStatus(DELETE);
    this.userDao.save(list);
    this.roleDao.save(role);
    // 清除缓存
    clearAllCached();
}


public Page<Role> findRoleInfo(String roleName,String roleCode,Pageable page){
    if (StringUtil.isBlank(roleCode)) {
        if (!StringUtil.isBlank(roleName)) {
            // 查询出来可能为多条记录
            return this.roleDao.findByRoleNameLikeOrderByUpdateTimeDesc(roleName, page);
        } else {
            // 查询出来为多条记录
            return this.roleDao.findAllOrderByUpdateTimeDesc(page);
        }
    } else {
        // 查询出来为单条记录
        return this.roleDao.findByRoleCode(roleCode, page);
    }
}


public void clearAllCached(){
    RealmSecurityManager securityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
    CustomRealm userRealm = (CustomRealm) securityManager.getRealms().iterator().next();
    userRealm.clearAllCachedAuthorizationInfo();
    userRealm.clearAllCachedAuthenticationInfo();
}


public String generalUserPasswordUpdate(GeneralUserPasswordUpdateDTO dto){
    List<User> userList = this.userDao.findByUserName(getCreateUserName());
    if (!userList.isEmpty()) {
        User user = userList.get(0);
        String oldPassword = getPasswordMD5(dto.getOldPassword(), user.getSalt());
        if (!user.getPassword().equals(oldPassword)) {
            throw new RuntimeException("密码错误");
        }
        String newPassword = getPasswordMD5(dto.getNewPassword(), user.getSalt());
        user.setPassword(newPassword);
        this.userDao.save(user);
        // 清除缓存
        clearAllCached();
    }
    return "密码修改成功";
}


public Role findOneRole(Integer roleId){
    Role role = this.roleDao.findOne(roleId);
    return role;
}


public List<CompanyAndStockDto> buildCompanyAndStockDto(List<Company> companyList){
    List<CompanyAndStockDto> list = new ArrayList<CompanyAndStockDto>();
    for (Company company : companyList) {
        CompanyAndStockDto dto = new CompanyAndStockDto();
        List<StorageRepoInfo> infoList = this.storageRepoInfoDao.findByOwnerIdOrderByGmtCreateAsc(company.getCompanyId());
        dto.setCompany(company);
        if (!infoList.isEmpty()) {
            dto.setStorageRepoInfo(infoList.get(0));
        }
        list.add(dto);
    }
    return list;
}


public boolean savePermission(CreatePermissionDto createPermissionDto){
    List<Permission> permissionCodeList = this.permissionDao.findByPermissionCode(createPermissionDto.getPermissionCode());
    List<Permission> urlList = this.permissionDao.findByUrl(createPermissionDto.getUrl());
    if (permissionCodeList.size() > 0) {
        throw new RuntimeException("权限Code重复");
    }
    if (urlList.size() > 0) {
        throw new RuntimeException("Url重复");
    }
    Permission p = new Permission();
    p.setPermissionCode(createPermissionDto.getPermissionCode());
    p.setGroupName(createPermissionDto.getGroupName());
    p.setPermissionName(createPermissionDto.getPermissionName());
    p.setPermissionDescription(createPermissionDto.getPermissionDescription());
    p.setUrl(createPermissionDto.getUrl());
    p.setCreateTime(new Date());
    p.setUpdateTime(new Date());
    this.permissionDao.save(p);
    return true;
}


@Transactional
public void deleteUser(Integer userId){
    User user = this.userDao.findOne(userId);
    user.setUpdateTime(new Date());
    user.setIsDelete(YES);
    this.userDao.save(user);
    // 清除缓存
    clearAllCached();
}


public String getCreateUserName(){
    Subject user = SecurityUtils.getSubject();
    ActiveUser au = (ActiveUser) user.getPrincipals().getPrimaryPrincipal();
    return au.getUserName();
}


@Transactional
public String registOrUpdateRole(RegistRoleDto registRoleDto){
    // 新建角色
    Role role = null;
    if (registRoleDto.getId() != null) {
        role = this.roleDao.findRoleByRoleId(registRoleDto.getId());
    }
    if (role == null) {
        role = new Role();
        role.setRoleCode(registRoleDto.getRoleCode());
        role.setCreateTime(new Date());
        role.setUpdateTime(new Date());
        role.setCreateUserName(getCreateUserName());
        role.setRoleName(registRoleDto.getRoleName());
        role.setRoleDescription(registRoleDto.getRoleDescription());
        role.setStatus(NORMAL);
    } else {
        role.setUpdateTime(new Date());
        role.setCreateUserName(getCreateUserName());
        role.setRoleName(registRoleDto.getRoleName());
        role.setRoleDescription(registRoleDto.getRoleDescription());
        // 删除原有角色权限关系
        List<RolePermission> list = this.rolePermissionDao.findByRoleId(role.getId());
        if (!list.isEmpty()) {
            for (RolePermission r : list) {
                r.setUpdateTime(new Date());
                r.setStatus(DELETE);
            }
            this.rolePermissionDao.save(list);
        }
    }
    try {
        this.roleDao.save(role);
    } catch (Exception e) {
        e.printStackTrace();
        throw new RuntimeException("角色Code重复");
    }
    // 获取选择ID获取的权限
    List<Permission> permissionList = (List<Permission>) this.permissionDao.findAll(registRoleDto.getPermissionIds());
    // 判断用户是否拥有传入的权限
    // if (!hasPermission(permissionList)) {
    // throw new UnauthorizedException("权限不足，不允许添加您没有的权限");
    // }
    // 创建角色与权限的关系
    for (Permission p : permissionList) {
        RolePermission rp = new RolePermission();
        rp.setPermissionId(p.getId());
        rp.setRoleId(role.getId());
        rp.setCreateTime(new Date());
        rp.setUpdateTime(new Date());
        rp.setStatus(NORMAL);
        this.rolePermissionDao.save(rp);
    }
    return registRoleDto.getRoleName();
}


public List<RolePermission> findRolePermissionByRoleId(Integer roleId){
    return this.rolePermissionDao.findByRoleId(roleId);
}


@Transactional
public String registAndUpdateUser(RegistUserDto registUserDto){
    // 新建用户
    User user = null;
    if (registUserDto.getId() != null) {
        user = this.findOneUser(registUserDto.getId());
    }
    String salt = getSalt();
    if (user == null) {
        user = new User();
        user.setUserName(registUserDto.getUserName());
        user.setRealName(registUserDto.getRealName());
        user.setIsDelete(NO);
        user.setPassword(getPasswordMD5(registUserDto.getPassword(), salt));
        user.setSalt(salt);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        if (registUserDto.getRoleId() != null) {
            user.setRoleId(registUserDto.getRoleId());
        } else {
            user.setRoleId(0);
        }
        if (registUserDto.getCompanyId() != null) {
            user.setCompanyId(registUserDto.getCompanyId());
        } else {
            user.setCompanyId(0);
        }
    } else {
        // 修改用户
        if (!user.getPassword().equals(registUserDto.getPassword())) {
            user.setPassword(getPasswordMD5(registUserDto.getPassword(), user.getSalt()));
            // 清除缓存
            clearAllCached();
        }
        user.setUserName(registUserDto.getUserName());
        user.setRealName(registUserDto.getRealName());
        user.setIsDelete(NO);
        user.setUpdateTime(new Date());
        if (registUserDto.getRoleId() != null) {
            user.setRoleId(registUserDto.getRoleId());
        } else {
            user.setRoleId(0);
        }
        if (registUserDto.getCompanyId() != null) {
            user.setCompanyId(registUserDto.getCompanyId());
        } else {
            user.setCompanyId(0);
        }
        clearAllCached();
    }
    try {
        this.userDao.save(user);
    } catch (Exception e) {
        throw new RuntimeException("用户名重复");
    }
    return registUserDto.getUserName();
}


public void saveOrUpdateCompanyUser(RegistUserDto dto){
    User user;
    if (dto.getId() != null) {
        user = this.userDao.findAllUserByCompanyIdAndUserId(dto.getCompanyId(), dto.getId());
        if (user == null) {
            throw new RuntimeException("请联系管理员，页面可能被篡改");
        }
        if (!user.getPassword().equals(dto.getPassword())) {
            user.setPassword(getPasswordMD5(dto.getPassword(), user.getSalt()));
        }
        user.setRealName(dto.getRealName());
        user.setUpdateTime(new Date());
        this.userDao.save(user);
        clearAllCached();
    } else {
        user = new User();
        String salt = getSalt();
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setCompanyId(dto.getCompanyId());
        user.setIsDelete(NO);
        user.setPassword(getPasswordMD5(dto.getPassword(), salt));
        user.setRealName(dto.getRealName());
        user.setRoleId(0);
        user.setSalt(salt);
        user.setUserName(dto.getUserName());
    }
}


public Company findCompanyById(Integer companyId){
    return this.companyDao.findByCompanyId(companyId);
}


public List<Company> findAllCompany(){
    return this.companyDao.findAllByStatusIsNormal();
}


}