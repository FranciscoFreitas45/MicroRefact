package org.live.sys.repository;
 import org.live.common.base.BaseRepositoryImpl;
import org.live.sys.vo.SidebarNode;
import java.util.List;
public class MenuRepositoryImpl extends BaseRepositoryImpl{


public List<SidebarNode> findMenu4SidebarNodeByUserId(String userId){
    String hql = "select distinct new org.live.sys.vo.SidebarNode(m.id, m.menuName, m.menuUrl, m.menuIcon, m.serialNo, m.parent.id)" + " from User u, GroupRole gr, RolePermission rp, Menu m" + " where u.group=gr.group and gr.role=rp.role and rp.permission=m.permission" + " and m.permission.isEnable=1 and gr.group.isEnable=1 and gr.role.isEnable=1" + " and u.id=?0 order by m.serialNo ASC";
    return this.find(hql, new Object[] { userId });
}


}