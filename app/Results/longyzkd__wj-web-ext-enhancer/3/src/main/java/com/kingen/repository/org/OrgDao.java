package com.kingen.repository.org;
 import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import com.google.common.collect.Maps;
import com.kingen.bean.SysOrg;
import com.kingen.bean.SysUserOrg;
import com.kingen.bean.User;
import com.kingen.repository.CommonDao;
import com.kingen.util.Page;
@Component
public class OrgDao extends CommonDao<SysOrg, String>{


public SysOrg findByPK(String pk){
    DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SysOrg.class);
    detachedCriteria.add(Restrictions.eq("id", pk));
    List<SysOrg> result = find(detachedCriteria);
    return CollectionUtils.isEmpty(result) ? null : result.get(0);
}


public void delOrgUsers(String orgId,List<String> userIds){
    String hql = " delete from SysUserOrg where id.orgId=:p1 and id.userId in :p2";
    Map<String, Object> parameter = Maps.newHashMap();
    parameter.put("p1", orgId);
    parameter.put("p2", userIds);
    executeHql(hql, parameter);
}


public Page<SysOrg> findOrgs(Page<SysOrg> page,SysOrg object){
    Map<String, Object> parameter = Maps.newHashMap();
    StringBuilder hql = new StringBuilder("from SysOrg ");
    return find(page, hql.toString(), parameter);
}


public List<SysUserOrg> findSysUserOrgs(String userId){
    Map<String, Object> parameter = Maps.newHashMap();
    parameter.put("userId", userId);
    StringBuilder hql = new StringBuilder("from SysUserOrg where id.userId=:userId ");
    return findme(hql.toString(), parameter);
}


public void delOrg(String orgId){
    delete(orgId);
    String hql = " delete from SysUserOrg where id.orgId=:p1 ";
    Map<String, Object> parameter = Maps.newHashMap();
    parameter.put("p1", orgId);
    executeHql(hql, parameter);
}


}