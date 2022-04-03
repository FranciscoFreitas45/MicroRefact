package com.kingen.service.org;
 import java.util.List;
import org.activiti.engine.IdentityService;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import com.kingen.aop.ServiceLogAnnotation;
import com.kingen.bean.SysOrg;
import com.kingen.bean.SysUserOrg;
import com.kingen.bean.SysUserOrgId;
import com.kingen.bean.User;
import com.kingen.repository.account.UserDao;
import com.kingen.repository.activiti.ActivitiDao;
import com.kingen.repository.org.OrgDao;
import com.kingen.util.Page;
@Component
@Transactional
public class OrgService {

 public  String HASH_ALGORITHM;

 public  int HASH_INTERATIONS;

 private  int SALT_SIZE;

 private  Logger logger;

@Autowired
 private  OrgDao dao;

@Autowired
 private  UserDao userDao;

@Autowired
 private  ActivitiDao activitiDao;

@Autowired
 protected  IdentityService identityService;


public void synAllUserAndGroupToActiviti(){
    // 清空工作流用户、组以及关系
    deleteAllActivitiIdentifyData();
    // 复制组数据
    synOrgToActiviti();
    // 复制用户以及关系数据
    synUserWithRoleToActiviti();
}


public void deleteAllActivitiIdentifyData(){
    activitiDao.deleteAllMemerShip();
    activitiDao.deleteAllGroup();
    activitiDao.deleteAllUser();
}


public Page<User> getOrgUsersRemain(Page<User> page,String orgId){
    Page<User> p = userDao.findOrgUsersRemain(page, orgId);
    return p;
}


public Page<SysOrg> getOrgs(Page<SysOrg> page,SysOrg object){
    Page<SysOrg> p = dao.findOrgs(page, object);
    return p;
}


public SysOrg one(SysOrg o){
    return dao.findByPK(o.getId());
}


@ServiceLogAnnotation(action = "保存组用户")
public void saveOrgUser(String orgId,String userId){
    if (orgId != null) {
        SysUserOrgId userOrgId = new SysUserOrgId(userId, orgId);
        SysUserOrg u = new SysUserOrg(userOrgId);
        dao.saveme(u);
    }
}


@ServiceLogAnnotation(action = "修改组")
public void update(SysOrg SysOrg,Boolean synToActiviti){
    dao.update(SysOrg);
    // 同步数据到Activiti Identify模块
    if (synToActiviti) {
        saveActivitiGroup(SysOrg);
    }
}


public void synUserWithRoleToActiviti(){
    List<User> allUser = userDao.find();
    for (User user : allUser) {
        String userId = user.getUserId().toString();
        // 添加一个用户到Activiti
        saveActivitiUser(user);
        List<SysUserOrg> memberShips = dao.findSysUserOrgs(userId);
        if (!CollectionUtils.isEmpty(memberShips)) {
            for (SysUserOrg suo : memberShips) {
                // 角色和用户的关系
                identityService.createMembership(userId, suo.getId().getOrgId());
            }
        }
    }
}


public void saveActivitiGroup(SysOrg org){
    org.activiti.engine.identity.Group group = identityService.createGroupQuery().groupId(org.getId()).singleResult();
    if (group == null) {
        group = identityService.newGroup(org.getId());
    }
    group.setName(org.getName());
    group.setType("");
    identityService.saveGroup(group);
    logger.info("add activiti user: {}" + ToStringBuilder.reflectionToString(group));
}


public void saveActivitiUser(User user){
    org.activiti.engine.identity.User activitiUser = identityService.createUserQuery().userId(user.getUserId()).singleResult();
    if (activitiUser == null) {
        activitiUser = identityService.newUser(user.getUserId());
    }
    activitiUser.setFirstName(user.getUsername());
    activitiUser.setLastName(user.getUsername());
    activitiUser.setEmail("");
    activitiUser.setPassword(user.getPassword());
    identityService.saveUser(activitiUser);
    logger.info("add activiti user: {}" + ToStringBuilder.reflectionToString(activitiUser));
}


public Page<User> getOrgUsers(Page<User> page,String orgId){
    Page<User> p = userDao.findOrgUsers(page, orgId);
    return p;
}


public void synOrgToActiviti(){
    List<SysOrg> allGroup = dao.find();
    for (SysOrg group : allGroup) {
        String groupId = group.getId().toString();
        org.activiti.engine.identity.Group identity_group = identityService.newGroup(groupId);
        identity_group.setName(group.getName());
        identity_group.setType("");
        identityService.saveGroup(identity_group);
    }
}


@ServiceLogAnnotation(action = "删除组用户")
public void delOrgUsers(String orgId,List<String> userIds,Boolean synToActiviti){
    Assert.hasLength(orgId, "组ID不应为空");
    Assert.notEmpty(userIds, "用户ID不应为空");
    dao.delOrgUsers(orgId, userIds);
    // 同步数据到Activiti Identify模块
    if (synToActiviti) {
        for (String userId : userIds) {
            identityService.deleteMembership(userId, orgId);
        }
    }
}


@ServiceLogAnnotation(action = "保存组")
public void create(SysOrg obj,Boolean synToActiviti){
    dao.save(obj);
    // 同步数据到Activiti Identify模块
    if (synToActiviti) {
        saveActivitiGroup(obj);
    }
}


@ServiceLogAnnotation(action = "保存组用户")
public void saveOrgUsers(String orgId,List<String> userIds,Boolean synToActiviti){
    if (orgId != null) {
        Assert.notEmpty(userIds);
        for (String userId : userIds) {
            SysUserOrgId userOrgId = new SysUserOrgId(userId, orgId);
            SysUserOrg u = new SysUserOrg(userOrgId);
            dao.saveme(u);
            if (synToActiviti) {
                identityService.createMembership(userId, orgId);
            }
        }
    }
}


@ServiceLogAnnotation(action = "删除组")
public void delOrg(String orgId,Boolean synToActiviti){
    Assert.hasLength(orgId, "组ID不应为空");
    dao.delOrg(orgId);
    if (synToActiviti) {
        identityService.deleteGroup(orgId);
    }
}


}