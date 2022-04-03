package com.kingen.repository.account;
 import java.util.List;
import java.util.Map;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Component;
import com.google.common.collect.Maps;
import com.kingen.bean.Menu;
import com.kingen.repository.CommonDao;
@Component
public class MenuDao extends CommonDao<Menu, String>{


public List<Menu> find(){
    DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Menu.class);
    List<Menu> result = find(detachedCriteria);
    return result;
}


public List<Menu> findMenusBy(String userIdString){
    // 防止一个人多个组，查询重复菜单出来
    String sql = "SELECT distinct t.* FROM tmanagermenu t " + " JOIN sys_org_menu a ON a.menu_id=t.menuid " + " join sys_user_org uo on uo.org_id=a.org_id " + " join tmanageruser u on uo.user_id=u.userid   and  u.userid=:p1";
    Map<String, Object> params = Maps.newHashMap();
    params.put("p1", userIdString);
    return findBySql(sql, params, Menu.class);
}


}