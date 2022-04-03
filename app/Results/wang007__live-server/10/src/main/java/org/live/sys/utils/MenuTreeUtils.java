package org.live.sys.utils;
 import org.live.common.constants.SysConstants;
import org.live.sys.vo.MenuNode;
import org.live.sys.vo.SidebarNode;
import org.live.sys.vo.TreeViewNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class MenuTreeUtils {


public String appendChildSidebarNodes(List<SidebarNode> childSidebarNodes){
    if (childSidebarNodes != null) {
        StringBuilder childNodesSb = new StringBuilder();
        childNodesSb.append("<ul class='submenu'>");
        for (SidebarNode childNode : childSidebarNodes) {
            // 子子菜单
            List<SidebarNode> childChildNodes = childNode.getChildSidebarNodes();
            // 是否存在子子菜单的标记。true:有，false：无
            boolean haveChildFlag = childChildNodes != null;
            if (haveChildFlag) {
                childNodesSb.append("<li class=''> <a href='#' class='dropdown-toggle'><i class='menu-icon fa fa-caret-right");
                // childNodesSb.append(childNode.getMenuIcon()) ;
                childNodesSb.append("'></i>");
                childNodesSb.append("<span class='menu-text'> ");
                childNodesSb.append(childNode.getMenuName());
                childNodesSb.append("</span><b class='arrow fa fa-angle-down'></b></a><b class='arrow'></b>");
                // 递归拼接子子菜单
                childNodesSb.append(appendChildSidebarNodes(childChildNodes));
                childNodesSb.append("</li>");
            } else {
                childNodesSb.append(appendNoChildSidebarNode(childNode));
            }
        }
        childNodesSb.append("</ul>");
        return childNodesSb.toString();
    } else {
        return "";
    }
}


public String appendSidebarBySidebarNodes(List<SidebarNode> sidebarNodes){
    if (sidebarNodes != null) {
        StringBuilder menuSb = new StringBuilder();
        for (SidebarNode node : sidebarNodes) {
            // 子菜单
            List<SidebarNode> childNodes = node.getChildSidebarNodes();
            // 是否存在子菜单的标记。true:有，false：无
            boolean haveChildFlag = childNodes != null;
            if (haveChildFlag) {
                menuSb.append("<li class=''> <a href='#' class='dropdown-toggle'><i class='menu-icon fa ");
                menuSb.append(node.getMenuIcon());
                menuSb.append("'></i>");
                menuSb.append("<span class='menu-text'> ");
                menuSb.append(node.getMenuName());
                menuSb.append("</span><b class='arrow fa fa-angle-down'></b></a><b class='arrow'></b>");
                menuSb.append(appendChildSidebarNodes(childNodes));
                menuSb.append("</li>");
                menuSb.append("");
            } else {
                menuSb.append(appendNoChildSidebarNode(node));
            }
        }
        return menuSb.toString();
    } else {
        return "";
    }
}


public String appendNoChildSidebarNode(SidebarNode node){
    if (node == null) {
        return "";
    }
    StringBuilder menuSb = new StringBuilder();
    // 是否存在url的标记。 true：存在， false：：不存在
    boolean haveUrlFlag = node.getMenuUrl() != null && !"".equals(node.getMenuUrl());
    menuSb.append("<li class=''>");
    if (haveUrlFlag) {
        menuSb.append("<a href='#");
        menuSb.append(node.getMenuUrl());
        menuSb.append("' data-url='");
        menuSb.append(node.getMenuUrl());
        menuSb.append("'>");
    } else {
        menuSb.append("<a href='#' data-url=''>");
    }
    menuSb.append("<i class='menu-icon fa fa-caret-right");
    // menuSb.append( node.getMenuIcon()) ;
    menuSb.append("'></i><span class='menu-text'>");
    menuSb.append(node.getMenuName());
    menuSb.append("</span></a><b class='arrow'></b></li>");
    return menuSb.toString();
}


public List<TreeViewNode> toTreeViewLayout(List<MenuNode> menuList){
    // 节点的长度
    int nodeLength = menuList == null ? 0 : menuList.size();
    // 返回的treeview的节点
    List<TreeViewNode> treeViewNodes = null;
    if (nodeLength > 0) {
        treeViewNodes = new ArrayList<TreeViewNode>(nodeLength);
        for (MenuNode menuNode : menuList) {
            // 节点内子节点的个数
            int childNodeLength = menuNode.getChildMenuNodes() == null ? 0 : menuNode.getChildMenuNodes().size();
            TreeViewNode treeviewNode = new TreeViewNode();
            // 菜单名
            treeviewNode.setText(menuNode.getMenuName());
            // 节点类型
            treeviewNode.setType(childNodeLength > 0 ? SysConstants.MENU_NODE_TYPE_FOLDER : SysConstants.MENU_NODE_TYPE_ITEM);
            Map<String, Object> additionalParameters = new HashMap<String, Object>();
            // 节点id
            additionalParameters.put("id", menuNode.getId());
            if (childNodeLength > 0) {
                // 存在子节点
                additionalParameters.put("children", true);
            }
            treeviewNode.setAdditionalParameters(additionalParameters);
            treeViewNodes.add(treeviewNode);
        }
    }
    return treeViewNodes;
}


}