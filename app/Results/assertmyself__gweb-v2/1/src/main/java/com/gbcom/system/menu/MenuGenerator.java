package com.gbcom.system.menu;
 import com.gbcom.system.domain.SysMenu;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class MenuGenerator {

 private  Logger logger;


public String getMenuAsString(SysMenu menu){
    String ret = "<li><a href=\"";
    if (StringUtils.isNotBlank(menu.getUrl())) {
        ret += menu.getUrl() + "\"";
    } else {
        ret += "#\"";
    }
    if (StringUtils.isNotBlank(menu.getTarget())) {
        ret += " target=\"" + menu.getTarget() + "\"";
    }
    if (StringUtils.isNotBlank(menu.getJsEvent())) {
        ret += " jsEvent=\"" + menu.getJsEvent() + "\"";
    }
    ret += ">" + menu.getName() + "</a>";
    if (menu.getIsLeaf()) {
        ret += "</li>";
    } else {
        Set<SysMenu> children = menu.getSysMenus();
        ret += getMenuListAsString(children);
        ret += "</li>";
    }
    return ret;
}


public List<SysMenu> setMenuChildren(List<SysMenu> list){
    List<SysMenu> ret = new ArrayList<SysMenu>();
    for (SysMenu menu : list) {
        if (!menu.getIsLeaf()) {
            String treeId = menu.getTreeId();
            Set<SysMenu> children = new HashSet<SysMenu>();
            for (SysMenu tmp : list) {
                String tmpTreeId = tmp.getTreeId();
                if (tmpTreeId.length() == treeId.length() + 5 && tmpTreeId.startsWith(treeId)) {
                    children.add(tmp);
                }
            }
            menu.setSysMenus(children);
            ret.add(menu);
        }
    }
    return ret;
}


public List<SysMenu> getTopMenu(List<SysMenu> list){
    List<SysMenu> ret = new ArrayList<SysMenu>();
    for (SysMenu menu : list) {
        if (menu.getParent() == null) {
            ret.add(menu);
        }
    }
    return ret;
}


public String getMenuListAsString(Set<SysMenu> list){
    String ret = "<ul>";
    for (SysMenu menu : list) {
        ret += getMenuAsString(menu);
    }
    ret += "</ul>";
    return ret;
}


public String getTopNode(SysMenu menu){
    String ret = "<a tabindex=\"0\" class=\"fg-button fg-button-icon-right ui-widget ui-state-default ui-corner-all\" id=\"" + menu.getTreeId() + "\">";
    ret += "<span class=\"ui-icon ui-icon-triangle-1-s\"></span>" + menu.getName() + "</a>";
    return ret;
}


public void main(String[] args){
    List<SysMenu> list = new ArrayList<SysMenu>();
    SysMenu menu = new SysMenu();
    menu.setTreeId("00001");
    menu.setIsLeaf(false);
    menu.setName("00001");
    list.add(menu);
    menu = new SysMenu();
    menu.setTreeId("0000100001");
    menu.setIsLeaf(true);
    menu.setName("0000100001");
    list.add(menu);
    menu = new SysMenu();
    menu.setTreeId("0000100002");
    menu.setIsLeaf(true);
    menu.setName("0000100002");
    list.add(menu);
    menu = new SysMenu();
    menu.setTreeId("0000100003");
    menu.setIsLeaf(true);
    menu.setName("0000100003");
    list.add(menu);
    menu = new SysMenu();
    menu.setTreeId("0000100004");
    menu.setIsLeaf(true);
    menu.setName("0000100004");
    list.add(menu);
    menu.setTreeId("00002");
    menu.setIsLeaf(false);
    menu.setName("00002");
    list.add(menu);
    menu = new SysMenu();
    menu.setTreeId("0000200001");
    menu.setIsLeaf(true);
    menu.setName("0000200001");
    list.add(menu);
    menu = new SysMenu();
    menu.setTreeId("0000200002");
    menu.setIsLeaf(true);
    menu.setName("0000200002");
    list.add(menu);
    menu = new SysMenu();
    menu.setTreeId("0000200003");
    menu.setIsLeaf(true);
    menu.setName("0000200003");
    list.add(menu);
    menu = new SysMenu();
    menu.setTreeId("0000200004");
    menu.setIsLeaf(true);
    menu.setName("0000200004");
    list.add(menu);
    logger.info(generateMenu(list));
    System.out.println(generateMenu(list));
}


public String generateMenu(List<SysMenu> list){
    String ret = "";
    List<SysMenu> tmpList = setMenuChildren(list);
    List<SysMenu> topList = getTopMenu(tmpList);
    for (SysMenu menu : topList) {
        ret += getTopNode(menu);
        if (menu.getSysMenus() != null && menu.getSysMenus().size() > 0) {
            ret += "<div id=\"items_\"" + menu.getId() + " class=\"hidden\">";
            ret += getMenuListAsString(menu.getSysMenus());
            ret += "</div>";
        }
    }
    return ret;
}


}