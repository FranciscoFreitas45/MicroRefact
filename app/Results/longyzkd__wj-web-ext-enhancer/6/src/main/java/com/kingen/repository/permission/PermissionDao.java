package com.kingen.repository.permission;
 import java.util.Map;
import org.springframework.stereotype.Component;
import com.google.common.collect.Maps;
import com.kingen.bean.SysOrgMenu;
import com.kingen.bean.SysOrgMenuId;
import com.kingen.repository.CommonDao;
@Component
public class PermissionDao extends CommonDao<SysOrgMenu, SysOrgMenuId>{


public void delOrgMenus(String orgId){
    String hql = " delete from SysOrgMenu where id.orgId=:p1 ";
    Map<String, Object> parameter = Maps.newHashMap();
    parameter.put("p1", orgId);
    executeHql(hql, parameter);
}


public void saveOrgMenus(String orgId,String[] menuIds){
    for (String menuId : menuIds) {
        SysOrgMenuId id = new SysOrgMenuId(menuId, orgId);
        SysOrgMenu m = new SysOrgMenu(id);
        save(m);
    }
}


}