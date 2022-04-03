package com.gbcom.system.manager;
 import com.gbcom.system.daoservice.SysMenuService;
import com.gbcom.system.domain.SysMenu;
import com.hc.core.utils.JspHelper;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util;
import com.gbcom.Interface.SysUserManager;
@Service
public class SysMenuManager {

@Autowired
 private  SysMenuService sysMenuService;

@Autowired
 private  SysUserManager sysUserManager;


public List<SysMenu> getAllMenu(){
    String hql = "select t from SysMenu t where parent is null and isValid=1 order by treeId asc";
    return sysMenuService.findByQuery(hql);
}


public String getAllMenuJson(){
    // 获取所有菜单
    return JSONArray.fromObject(getMenuList()).toString();
}


public String getUserMenuJson(Long userId){
    List<Map<String, Object>> retList = new ArrayList<Map<String, Object>>();
    Set<String> privileges = sysUserManager.getUserPrivilegeCodesCache(userId);
    List<Map<String, Object>> menuList = getMenuList();
    for (Map<String, Object> menuItem : menuList) {
        List<Map<String, Object>> childMenuItems = getChildMenuItem(menuItem, privileges);
        if (childMenuItems != null) {
            menuItem.put("menus", childMenuItems);
            retList.add(menuItem);
        } else if (privileges.contains(JspHelper.getString(menuItem.get("privilege")))) {
            menuItem.put("menus", "");
            retList.add(menuItem);
        }
    }
    return JSONArray.fromObject(retList).toString();
}


public List<Map<String,Object>> getChildMenuList(Set<SysMenu> children){
    List<Map<String, Object>> ret = new ArrayList<Map<String, Object>>();
    for (SysMenu menu : children) {
        if (!menu.getIsValid()) {
            continue;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("menuid", menu.getId() + "");
        map.put("menuname", menu.getName());
        map.put("icon", JspHelper.getString(menu.getIcon()));
        map.put("privilege", JspHelper.getString(menu.getPrivilege()));
        map.put("jsevent", JspHelper.getString(menu.getJsEvent()));
        // map.put("url", JspHelper.getString(menu.getUrl()));
        Set<SysMenu> nextChildren = menu.getSysMenus();
        if (nextChildren.size() > 0) {
            map.put("menus", getChildMenuList(nextChildren));
        } else {
            map.put("url", JspHelper.getString(menu.getUrl()));
        }
        ret.add(map);
    }
    return ret;
}


public List<Map<String,Object>> getMenuList(){
    List<Map<String, Object>> ret = new ArrayList<Map<String, Object>>();
    List<SysMenu> list = getAllMenu();
    for (SysMenu menu : list) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("menuid", menu.getId() + "");
        map.put("menuname", menu.getName());
        map.put("icon", JspHelper.getString(menu.getIcon()));
        map.put("privilege", JspHelper.getString(menu.getPrivilege()));
        Set<SysMenu> children = menu.getSysMenus();
        if (children.size() > 0) {
            map.put("menus", getChildMenuList(children));
        } else {
            map.put("url", JspHelper.getString(menu.getUrl()));
        }
        ret.add(map);
    }
    return ret;
}


@SuppressWarnings("unchecked")
public List<Map<String,Object>> getChildMenuItem(Map<String,Object> menuItem,Set<String> privilegeMap){
    List<Map<String, Object>> ret = new ArrayList<Map<String, Object>>();
    List<Map<String, Object>> itemList = (List<Map<String, Object>>) menuItem.get("menus");
    if (itemList != null) {
        for (Map<String, Object> item : itemList) {
            if (item.get("menus") != null) {
                menuItem.put("menus", getChildMenuItem(item, privilegeMap));
                ret.add(item);
            } else if (privilegeMap.contains(JspHelper.getString(item.get("privilege")))) {
                item.put("menus", "");
                ret.add(item);
            }
        }
    }
    if (ret.size() > 0) {
        return ret;
    } else {
        return null;
    }
}


}