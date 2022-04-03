package com.sobey.cmop.mvc.service.account;
 import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.google.common.collect.Lists;
import com.sobey.cmop.mvc.comm.BaseSevcie;
import com.sobey.cmop.mvc.constant.AccountConstant;
import com.sobey.cmop.mvc.dao.DepartmentDao;
import com.sobey.cmop.mvc.dao.GroupDao;
import com.sobey.cmop.mvc.dao.UserDao;
import com.sobey.cmop.mvc.dao.custom.AccountDaoCustom;
import com.sobey.cmop.mvc.entity.Department;
import com.sobey.cmop.mvc.entity.Group;
import com.sobey.cmop.mvc.entity.User;
import com.sobey.framework.utils.Digests;
import com.sobey.framework.utils.DynamicSpecifications;
import com.sobey.framework.utils.Encodes;
import com.sobey.framework.utils.SearchFilter;
import com.sobey.framework.utils.SearchFilter.Operator;
@Service
// 默认将类中的所有public函数纳入事务管理.
@Transactional(readOnly = true)
public class AccountService extends BaseSevcie{

 private  Logger logger;

 private  UserDao userDao;

 private  GroupDao groupDao;

 private  AccountDaoCustom accountDao;

 private  DepartmentDao departmentDao;

 private  ShiroDbRealm shiroRealm;


public User getCurrentUser(){
    return userDao.findOne(getCurrentUserId());
}


public User findUserByRedmineUserId(Integer redmineUserId){
    return userDao.findByRedmineUserId(redmineUserId);
}


public Department getDepartment(Integer id){
    return departmentDao.findOne(id);
}


public User getUser(Integer id){
    return userDao.findOne(id);
}


public void entryptPassword(User user){
    byte[] salt = Digests.generateSalt(AccountConstant.SALT_SIZE);
    user.setSalt(Encodes.encodeHex(salt));
    byte[] hashPassword = Digests.sha1(user.getPlainPassword().getBytes(), salt, AccountConstant.HASH_INTERATIONS);
    user.setPassword(Encodes.encodeHex(hashPassword));
}


public boolean isDefautlGroup(Integer id){
    List<Integer> list = new ArrayList<Integer>();
    list.add(AccountConstant.DefaultGroups.admin.toInteger());
    list.add(AccountConstant.DefaultGroups.apply.toInteger());
    list.add(AccountConstant.DefaultGroups.audit.toInteger());
    list.add(AccountConstant.DefaultGroups.om_a.toInteger());
    list.add(AccountConstant.DefaultGroups.om_b.toInteger());
    return list.contains(id);
}


@Resource
public void setShiroRealm(ShiroDbRealm shiroRealm){
    this.shiroRealm = shiroRealm;
}


@Resource
public void setGroupDao(GroupDao groupDao){
    this.groupDao = groupDao;
}


public List<User> getUsers(){
    return (List<User>) userDao.findAll();
}


public List<User> getUserListByType(Integer type){
    return userDao.findByType(type);
}


@Transactional(readOnly = false)
public void initializeUser(){
    List<User> users = (List<User>) userDao.findAll();
    for (User user : users) {
        String email = user.getEmail();
        // 如果email中包含@,取@前的字符串赋予给loginName
        String loginName = email.indexOf("@") == -1 ? email : email.substring(0, email.indexOf("@"));
        user.setLoginName(loginName);
        user.setPlainPassword(AccountConstant.DEFAULT_PASSWORD);
        entryptPassword(user);
        user.setCreateTime(new Date());
        user.setEmail(email + "@sobey.com");
        userDao.save(user);
    }
}


@SuppressWarnings("rawtypes")
public Group findGroupByUserId(Integer userId){
    List list = accountDao.getUserGroupByUserId(userId);
    return list.isEmpty() ? getGroup(AccountConstant.DefaultGroups.apply.toInteger()) : getGroup((Integer) list.get(0));
}


@Transactional(readOnly = false)
public boolean deleteGroup(Integer id){
    if (this.isDefautlGroup(id)) {
        logger.warn("操作员{}尝试删除默认权限组", SecurityUtils.getSubject().getPrincipal());
        return false;
    } else {
        groupDao.delete(id);
        shiroRealm.clearAllCachedAuthorizationInfo();
        return true;
    }
}


public User findUserByEmail(String email){
    return userDao.findByEmail(email);
}


public Group findGroupByName(String name){
    return groupDao.findByName(name);
}


public List<Group> getGroupListById(Integer groupId){
    List<Group> groupList = Lists.newArrayList();
    groupList.add(this.getGroup(groupId));
    return groupList;
}


@Resource
public void setDepartmentDao(DepartmentDao departmentDao){
    this.departmentDao = departmentDao;
}


public User findUserByLoginName(String loginName){
    return userDao.findByLoginName(loginName);
}


public List<Group> findAllGroup(){
    return (List<Group>) groupDao.findAll((new Sort(Direction.ASC, "id")));
}


@Transactional(readOnly = false)
public void saveGroup(Group group){
    groupDao.save(group);
    shiroRealm.clearAllCachedAuthorizationInfo();
}


@Resource
public void setAccountDao(AccountDaoCustom accountDao){
    this.accountDao = accountDao;
}


public Page<User> getUserPageable(Map<String,Object> searchParams,int pageNumber,int pageSize){
    PageRequest pageRequest = buildPageRequest(pageNumber, pageSize);
    // User创建动态查询条件组合.
    Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
    filters.put("user.status", new SearchFilter("status", Operator.EQ, AccountConstant.UserStatus.ENABLED.toInteger()));
    Specification<User> spec = DynamicSpecifications.bySearchFilter(filters.values(), User.class);
    return userDao.findAll(spec, pageRequest);
}


@Transactional(readOnly = false)
public void updateUser(User user){
    if (StringUtils.isNotBlank(user.getPlainPassword())) {
        entryptPassword(user);
    }
    userDao.save(user);
    shiroRealm.clearCachedAuthorizationInfo(user.getLoginName());
}


public Page<Group> getGroupPageable(Map<String,Object> searchParams,int pageNumber,int pageSize){
    PageRequest pageRequest = buildPageRequest(pageNumber, pageSize);
    Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
    Specification<Group> spec = DynamicSpecifications.bySearchFilter(filters.values(), Group.class);
    return groupDao.findAll(spec, pageRequest);
}


public boolean isSupervisor(Integer id){
    return id == 1;
}


public Group getGroup(Integer id){
    return groupDao.findOne(id);
}


@Transactional(readOnly = false)
public void registerUser(User user){
    user.setStatus(AccountConstant.UserStatus.ENABLED.toInteger());
    entryptPassword(user);
    user.setCreateTime(new Date());
    userDao.save(user);
    shiroRealm.clearCachedAuthorizationInfo(user.getLoginName());
}


@Transactional(readOnly = false)
public boolean deleteUser(Integer id){
    boolean flag = false;
    if (this.isSupervisor(id)) {
        logger.warn("操作员{}尝试删除超级管理员用户", SecurityUtils.getSubject().getPrincipal());
    } else {
        userDao.delete(id);
        flag = true;
    }
    return flag;
}


public List<String> getPermissionByGroupId(Integer groupId){
    return accountDao.getGroupPermissionByGroupId(groupId);
}


@Resource
public void setUserDao(UserDao userDao){
    this.userDao = userDao;
}


}