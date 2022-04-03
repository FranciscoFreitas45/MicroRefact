package com.kingen.repository.account;
 import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import com.google.common.collect.Maps;
import com.kingen.bean.User;
import com.kingen.repository.CommonDao;
import com.kingen.util.Page;
@Component
public class UserDao extends CommonDao<User, String>{


public User findByLoginName(String loginName){
    DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
    detachedCriteria.add(Restrictions.eq("userId", loginName));
    List<User> result = find(detachedCriteria);
    return CollectionUtils.isEmpty(result) ? null : result.get(0);
}


public Page<User> findOrgUsers(Page<User> page,String orgId){
    Assert.hasText(orgId, "组织ID不应为空");
    Map<String, Object> parameter = Maps.newHashMap();
    parameter.put("p1", orgId);
    StringBuilder hql = new StringBuilder("select u from User u,SysUserOrg uo where uo.id.orgId=:p1 and uo.id.userId=u.userId");
    return find(page, hql.toString(), parameter);
}


public void updatePwd(User user){
// ShiroUser u = (ShiroUser)SecurityUtils.getSubject().getPrincipal();
// Parameter p = new Parameter(user.getPassword(),DateUtils.parseDate(DateUtils.getDate()),u.loginName,user.getId());
// update("update User set password = :p1 ,updateDate=:p2 ,updateBy=:p3  where id = :p4", p);
}


public User findByUserid(String userid){
    DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
    detachedCriteria.add(Restrictions.eq("userId", userid));
    List<User> result = find(detachedCriteria);
    return CollectionUtils.isEmpty(result) ? null : result.get(0);
}


public Page<User> findOrgUsersRemain(Page<User> page,String orgId){
    Assert.hasText(orgId, "组织ID不应为空");
    Map<String, Object> parameter = Maps.newHashMap();
    parameter.put("p1", orgId);
    StringBuilder hql = new StringBuilder("  select u from User u where  not exists (select 1 from SysUserOrg uo where uo.id.orgId=:p1 and uo.id.userId=u.userId )   ");
    // StringBuilder hql = new StringBuilder("  select u from User u where  not exists (select 1 from SysUserOrg uo where uo.id.orgId=:p1 and uo.id.userId=u.userId )  and u.userId<>'admin'   ");
    return find(page, hql.toString(), parameter);
}


public void deleteUserMenu(String userId){
    String hql = "delete from Tmanageruserfun where id.userId=:userId";
    Map<String, Object> parameter = Maps.newHashMap();
    parameter.put("userId", userId);
    executeHql(hql, parameter);
}


public Page<User> findUsers(Page<User> page,User user){
    Map<String, Object> parameter = Maps.newHashMap();
    // StringBuilder hql = new StringBuilder("from User where 1=1  "); //超级管理员不在页面显示
    // 超级管理员不在页面显示
    StringBuilder hql = new StringBuilder("from User where userId<>'admin' ");
    if (user != null && !StringUtils.isBlank(user.getUserId())) {
        hql.append(" and userId like :userId");
        parameter.put("userId", "%" + user.getUserId() + "%");
    }
    hql.append(" order by status desc");
    return find(page, hql.toString(), parameter);
}


}