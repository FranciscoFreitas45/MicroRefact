package org.jeecgframework.core.util;
 import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.jeecgframework.core.enums.SysACEIconEnum;
import org.jeecgframework.web.system.pojo.base.TSFunction;
public class ListtoMenu {

 static  int count;


public String getAceMultistageTree(Map<Integer,List<TSFunction>> map){
    if (map == null || map.size() == 0 || !map.containsKey(0)) {
        return "不具有任何权限,\n请找管理员分配权限";
    }
    StringBuffer menuString = new StringBuffer();
    List<TSFunction> list = map.get(0);
    int curIndex = 0;
    for (TSFunction function : list) {
        menuString.append("<li>");
        // update-begin--Author:chenj  Date:20160722 for：添加菜单图标样式
        // -- update-start--Author: dangzhenghui Date:20170608 for: TASK #2088 【图标问题】首页风格图标统一
        if (function.getFunctionIconStyle() != null && !function.getFunctionIconStyle().trim().equals("")) {
            menuString.append("<a href=\"#\" class=\"dropdown-toggle\" ><i class=\"fa " + function.getFunctionIconStyle() + "\"></i>");
        } else {
            menuString.append("<a href=\"#\" class=\"dropdown-toggle\" ><i class=\"fa fa-columns\"></i>");
        }
        // -- update-end--Author: dangzhenghui Date:20170608 for: TASK #2088 【图标问题】首页风格图标统一
        // update-begin--Author:chenj  Date:20160722 for：添加菜单图标样式
        menuString.append("<span class=\"menu-text\">");
        menuString.append(getMutiLang(function.getFunctionName()));
        menuString.append("</span>");
        /* int submenusize = function.getSubFunctionSize();
                if (submenusize == 0) {
                    menuString.append("</a></li>");
                }
                if (submenusize > 0) {
                    menuString.append("<b class=\"arrow\"></b><ul  class=\"submenu\" >");
                }
                menuString.append(getSubMenu(function,1,map));
                if (submenusize > 0) {
                    menuString.append("</ul></li>");
                }*/
        if (!function.hasSubFunction(map)) {
            menuString.append("</a></li>");
        // menuString.append(getSubMenu(function,1,map));
        } else {
            menuString.append("<b class=\"arrow icon-angle-down\"></b></a><ul  class=\"submenu\" >");
            menuString.append(getACESubMenu(function, 1, map));
            menuString.append("</ul></li>");
        }
        curIndex++;
    }
    return menuString.toString();
}


public String getLeafOfDIYTree(TSFunction function){
    StringBuffer menuString = new StringBuffer();
    String icon = "folder";
    if (function.getTSIcon() != null) {
        icon = ResourceUtil.allTSIcons.get(function.getTSIcon().getId()).getIconClas();
    }
    String name = getMutiLang(function.getFunctionName());
    menuString.append("<li iconCls=\"");
    menuString.append(icon);
    menuString.append("\"> <a href=\"javascript:loadModule(\'");
    menuString.append(name);
    menuString.append("\',\'");
    menuString.append(function.getFunctionUrl());
    if (function.getFunctionUrl().indexOf("?") > -1) {
        menuString.append("&clickFunctionId=");
    } else {
        menuString.append("?clickFunctionId=");
    }
    menuString.append(function.getId());
    menuString.append("\',\'");
    menuString.append(icon);
    menuString.append("\')\"  title=\"");
    menuString.append(name);
    menuString.append("\" url=\"");
    menuString.append(function.getFunctionUrl());
    menuString.append("\"  >");
    menuString.append(name);
    menuString.append("</a></li>");
    return menuString.toString();
}


public String getLeafOfFineuiTree(TSFunction function,Map<Integer,List<TSFunction>> map){
    StringBuffer menuString = new StringBuffer();
    String icon = "folder";
    if (function.getTSIcon() != null) {
        icon = ResourceUtil.allTSIcons.get(function.getTSIcon().getId()).getIconClas();
    }
    // addTabs({id:'home',title:'首页',close: false,url: 'loginController.do?home'});
    String name = getMutiLang(function.getFunctionName());
    menuString.append("<li> <a class=\"F_menuItem\" href=\"").append(function.getFunctionUrl()).append("\">");
    if (!function.hasSubFunction(map)) {
        // update-begin--Author:likai  Date:20160904 for：[1345]二级菜单图标样式设置了，但是菜单样式无效果--------------------
        if (function.getFunctionIconStyle() != null && !function.getFunctionIconStyle().trim().equals("")) {
            menuString.append("<i class=\"fa " + function.getFunctionIconStyle() + "\"></i>");
        }
        menuString.append("<span>");
        menuString.append(name);
        menuString.append("</span>");
        // update-end--Author:likai  Date:20160904 for：[1345]二级菜单图标样式设置了，但是菜单样式无效果--------------------
        menuString.append("</a>");
        menuString.append("</li>");
    } else {
        // update-begin--Author:likai  Date:20160904 for：[1345]二级菜单图标样式设置了，但是菜单样式无效果--------------------
        if (function.getFunctionIconStyle() != null && !function.getFunctionIconStyle().trim().equals("")) {
            menuString.append("<i class=\"fa " + function.getFunctionIconStyle() + "\"></i>");
        } else {
            menuString.append("<i class=\"fa fa-columns\"></i>");
        }
        menuString.append("<span>");
        menuString.append(name);
        menuString.append("</span>");
        // update-end--Author:likai  Date:20160904 for：[1345]二级菜单图标样式设置了，但是菜单样式无效果--------------------
        menuString.append("<i class=\"icon-font icon-right\"></i>");
        menuString.append("</a>");
        menuString.append("<ul class=\"menu-item-child\" >");
        menuString.append(getFineuiSubMenu(function, 2, map));
        menuString.append("</ul></li>");
    }
    return menuString.toString();
}


public String getIconAndNameForDesk(TSFunction function){
    StringBuffer dataString = new StringBuffer();
    String colName = function.getTSIconDesk() == null ? null : function.getTSIconDesk().getIconPath();
    colName = (colName == null || colName.equals("")) ? "plug-in/sliding/icon/default.png" : colName;
    String functionName = getMutiLang(function.getFunctionName());
    dataString.append("icon:'" + colName + "',");
    dataString.append("name:'" + functionName + "',");
    return dataString.toString();
}


public String getLeafOfHplusTree(TSFunction function,Map<Integer,List<TSFunction>> map){
    StringBuffer menuString = new StringBuffer();
    String icon = "folder";
    if (function.getTSIcon() != null) {
        icon = ResourceUtil.allTSIcons.get(function.getTSIcon().getId()).getIconClas();
    }
    // addTabs({id:'home',title:'首页',close: false,url: 'loginController.do?home'});
    String name = getMutiLang(function.getFunctionName());
    menuString.append("<li> <a class=\"J_menuItem\" href=\"").append(function.getFunctionUrl()).append("\">");
    if (!function.hasSubFunction(map)) {
        // update-begin--Author:likai  Date:20160904 for：[1345]二级菜单图标样式设置了，但是菜单样式无效果--------------------
        if (function.getFunctionIconStyle() != null && !function.getFunctionIconStyle().trim().equals("")) {
            menuString.append("<i class=\"fa " + function.getFunctionIconStyle() + "\"></i>");
        }
        menuString.append("<span class=\"menu-text\">");
        menuString.append(name);
        menuString.append("</span>");
        // update-end--Author:likai  Date:20160904 for：[1345]二级菜单图标样式设置了，但是菜单样式无效果--------------------
        menuString.append("</a>");
        menuString.append("</li>");
    } else {
        // update-begin--Author:likai  Date:20160904 for：[1345]二级菜单图标样式设置了，但是菜单样式无效果--------------------
        if (function.getFunctionIconStyle() != null && !function.getFunctionIconStyle().trim().equals("")) {
            menuString.append("<i class=\"fa " + function.getFunctionIconStyle() + "\"></i>");
        } else {
            menuString.append("<i class=\"fa fa-columns\"></i>");
        }
        menuString.append("<span class=\"menu-text\">");
        menuString.append(name);
        menuString.append("</span>");
        // update-end--Author:likai  Date:20160904 for：[1345]二级菜单图标样式设置了，但是菜单样式无效果--------------------
        menuString.append("<span class=\"fa arrow\">");
        menuString.append("</span>");
        menuString.append("</a>");
        menuString.append("<ul class=\"nav nav-third-level\" >");
        menuString.append(getHplusSubMenu(function, 2, map));
        menuString.append("</ul></li>");
    }
    return menuString.toString();
}


public String getFineuiMultistageTree(Map<Integer,List<TSFunction>> map){
    if (map == null || map.size() == 0 || !map.containsKey(0)) {
        return "不具有任何权限,\n请找管理员分配权限";
    }
    StringBuffer menuString = new StringBuffer();
    List<TSFunction> list = map.get(0);
    int curIndex = 0;
    for (TSFunction function : list) {
        String order = function.getFunctionOrder();
        menuString.append("<li class='menu-item'>");
        if (function.getFunctionIconStyle() != null && !function.getFunctionIconStyle().trim().equals("")) {
            menuString.append("<a href><i class=\"fa " + function.getFunctionIconStyle() + "\"></i>");
        } else {
            menuString.append("<a href><i class=\"fa fa-columns\"></i>");
        }
        menuString.append("<span class=\"menu-text\">");
        menuString.append(getMutiLang(function.getFunctionName()));
        menuString.append("</span>");
        if (!function.hasSubFunction(map)) {
            menuString.append("</a></li>");
        // menuString.append(getSubMenu(function,1,map));
        } else {
            menuString.append("<i class=\"icon-font icon-right\"></i>");
            /**/
            // menuString.append("<b class=\"arrow icon-angle-down\"></b></a><ul  class=\"submenu\" >");
            // class="" id="3" style='display: block;'
            menuString.append("</a><ul  class=\"menu-item-child\" id='menu-child-" + order + "' >");
            menuString.append(getFineuiSubMenu(function, 1, map));
            menuString.append("</ul></li>");
        }
        curIndex++;
    }
    return menuString.toString();
}


public String getLeafOfACETree(TSFunction function,Map<Integer,List<TSFunction>> map){
    StringBuffer menuString = new StringBuffer();
    String icon = "folder";
    if (function.getTSIcon() != null) {
        icon = ResourceUtil.allTSIcons.get(function.getTSIcon().getId()).getIconClas();
    }
    // addTabs({id:'home',title:'首页',close: false,url: 'loginController.do?home'});
    String name = getMutiLang(function.getFunctionName());
    menuString.append("<li> <a href=\"javascript:addTabs({id:\'").append(function.getId());
    menuString.append("\',title:\'").append(name).append("\',close: true,url:\'");
    menuString.append(function.getFunctionUrl());
    if (function.getFunctionUrl().indexOf("?") > -1) {
        menuString.append("&clickFunctionId=");
    } else {
        menuString.append("?clickFunctionId=");
    }
    menuString.append(function.getId());
    // menuString.append("\',\'");
    // menuString.append(icon);
    menuString.append("\'})\"  title=\"");
    menuString.append(name);
    menuString.append("\" url=\"");
    menuString.append(function.getFunctionUrl());
    menuString.append("\"  >");
    /* 20160830 wangkun TASK #1330 【改造】ace首页风格，菜单不支持三级菜单，改造支持三级*/
    if (function.hasSubFunction(map)) {
        menuString.append("<i class=\"icon-double-angle-right\"></i>");
    }
    /* 20160830 wangkun TASK #1330 【改造】ace首页风格，菜单不支持三级菜单，改造支持三级*/
    menuString.append(name);
    menuString.append("</a></li>");
    return menuString.toString();
}


public String getDIYSubMenu(TSFunction parent,int level,Map<Integer,List<TSFunction>> map){
    StringBuffer menuString = new StringBuffer();
    List<TSFunction> list = map.get(level);
    for (TSFunction function : list) {
        if (function.getTSFunction().getId().equals(parent.getId())) {
            if (!function.hasSubFunction(map)) {
                menuString.append(getLeafOfDIYTree(function));
            } else if (map.containsKey(level + 1)) {
                String icon = "folder";
                try {
                    if (function.getTSIcon() != null) {
                        icon = ResourceUtil.allTSIcons.get(function.getTSIcon().getId()).getIconClas();
                    }
                } catch (Exception e) {
                // TODO handle icon load exception
                }
                menuString.append("<li><a href=\"#\" ><i class=\"menu-icon fa fa-eye pink\" iconCls=\"" + icon + "\" ></i>" + getMutiLang(function.getFunctionName()) + "<b class=\"arrow\"></b>");
                menuString.append("<ul class=\"submenu\">");
                menuString.append(getChildOfTree(function, level + 1, map));
                menuString.append("</ul></li>");
            }
        }
    }
    return menuString.toString();
}


public String getLeafOfTree(TSFunction function){
    StringBuffer menuString = new StringBuffer();
    String icon = "folder";
    if (function.getTSIcon() != null) {
        icon = ResourceUtil.allTSIcons.get(function.getTSIcon().getId()).getIconClas();
    }
    menuString.append("<li iconCls=\"");
    menuString.append(icon);
    menuString.append("\"> <a onclick=\"addTab(\'");
    menuString.append(getMutiLang(function.getFunctionName()));
    menuString.append("\',\'");
    menuString.append(function.getFunctionUrl());
    // update-begin ---author:scott---for:外部链接在系统中配置无效----date:20150325
    // 如果是外部链接，则不加菜单ID
    if (function.getFunctionUrl().indexOf("http:") == -1) {
        // update-begin--Author:jg_renjie  Date:20150715 for：解决rest风格时，传入id错误的问题
        if (function.getFunctionUrl().indexOf("?") == -1) {
            menuString.append("?clickFunctionId=");
        } else {
            menuString.append("&clickFunctionId=");
        }
        // update-end--Author:jg_renjie  Date:20150715 for：解决rest风格时，传入id错误的问题
        menuString.append(function.getId());
    }
    // update-end ---author:scott---for:外部链接在系统中配置无效----date:20150325
    menuString.append("\',\'");
    menuString.append(icon);
    menuString.append("\')\"  title=\"");
    menuString.append(getMutiLang(function.getFunctionName()));
    menuString.append("\" url=\"");
    menuString.append(function.getFunctionUrl());
    menuString.append("\" href=\"#\" ><span class=\"nav\" >");
    menuString.append(getMutiLang(function.getFunctionName()));
    menuString.append("</span></a></li>");
    return menuString.toString();
}


public String getLeaf(TSFunction function){
    StringBuffer menuString = new StringBuffer();
    String icon = "folder";
    if (function.getTSIcon() != null) {
        icon = ResourceUtil.allTSIcons.get(function.getTSIcon().getId()).getIconClas();
    }
    menuString.append("<li><div onclick=\"addTab(\'");
    menuString.append(getMutiLang(function.getFunctionName()));
    menuString.append("\',\'");
    menuString.append(function.getFunctionUrl());
    if (function.getFunctionUrl().indexOf("?") > -1) {
        menuString.append("&clickFunctionId=");
    } else {
        menuString.append("?clickFunctionId=");
    }
    menuString.append(function.getId());
    menuString.append("\',\'");
    menuString.append(icon);
    menuString.append("\')\"  title=\"");
    menuString.append(getMutiLang(function.getFunctionName()));
    menuString.append("\" url=\"");
    menuString.append(function.getFunctionUrl());
    menuString.append("\" iconCls=\"");
    menuString.append(icon);
    menuString.append("\"> <a class=\"");
    menuString.append(getMutiLang(function.getFunctionName()));
    menuString.append("\" href=\"#\" > <span class=\"icon ");
    menuString.append(icon);
    menuString.append("\" >&nbsp;</span> <span class=\"nav\" >");
    menuString.append(getMutiLang(function.getFunctionName()));
    menuString.append("</span></a></div></li>");
    return menuString.toString();
}


public String getBootstrapMenu(Map<Integer,List<TSFunction>> map){
    StringBuffer menuString = new StringBuffer();
    menuString.append("<ul class=\"nav\">");
    List<TSFunction> pFunctions = (List<TSFunction>) map.get(0);
    if (pFunctions == null || pFunctions.size() == 0) {
        return "";
    }
    for (TSFunction pFunction : pFunctions) {
        // 是否有子菜单
        boolean hasSub = pFunction.hasSubFunction(map);
        // 绘制一级菜单
        menuString.append("	<li class=\"dropdown\"> ");
        menuString.append("		<a href=\"javascript:;\" class=\"dropdown-toggle\" data-toggle=\"dropdown\"> ");
        menuString.append("			<span class=\"bootstrap-icon\" style=\"background-image: url('" + ResourceUtil.allTSIcons.get(pFunction.getTSIcon().getId()).getIconPath() + "')\"></span> " + pFunction.getFunctionName() + " ");
        if (hasSub) {
            menuString.append("			<b class=\"caret\"></b> ");
        }
        menuString.append("		</a> ");
        // 绘制二级菜单
        if (hasSub) {
            menuString.append(getBootStrapChild(pFunction, 1, map));
        }
        menuString.append("	</li> ");
    }
    menuString.append("</ul>");
    return menuString.toString();
}


public String getMenu(List<TSFunction> set,List<TSFunction> set1){
    StringBuffer buffer = new StringBuffer();
    buffer.append("{\"menus\":[");
    for (TSFunction node : set) {
        // 权限图标样式
        String iconClas = "default";
        if (node.getTSIcon() != null) {
            iconClas = ResourceUtil.allTSIcons.get(node.getTSIcon().getId()).getIconClas();
        }
        buffer.append("{\"menuid\":\"" + node.getId() + "\",\"icon\":\"" + iconClas + "\"," + "\"menuname\":\"" + getMutiLang(node.getFunctionName()) + "\",\"menus\":[");
        iterGet(set1, node.getId(), buffer);
        buffer.append("]},");
    }
    buffer.append("]}");
    // 将,\n]替换成\n]
    String tmp = buffer.toString();
    tmp = tmp.replaceAll(",\n]", "\n]");
    tmp = tmp.replaceAll(",]}", "]}");
    return tmp;
}


public String getDIYMultistageTree(Map<Integer,List<TSFunction>> map){
    if (map == null || map.size() == 0 || !map.containsKey(0)) {
        return "不具有任何权限,\n请找管理员分配权限";
    }
    StringBuffer menuString = new StringBuffer();
    List<TSFunction> list = map.get(0);
    int curIndex = 0;
    for (TSFunction function : list) {
        menuString.append("<li>");
        // update-begin--Author:chenj  Date:20160722 for：添加菜单图标样式
        if (function.getFunctionIconStyle() != null && !function.getFunctionIconStyle().trim().equals("")) {
            menuString.append("<a href=\"#\" class=\"dropdown-toggle\" ><i class=\"menu-icon fa " + function.getFunctionIconStyle() + "\"></i>");
        } else {
            menuString.append("<a href=\"#\" class=\"dropdown-toggle\" ><i class=\"menu-icon fa fa-desktop\"></i>");
        }
        // update-begin--Author:chenj  Date:20160722 for：添加菜单图标样式
        menuString.append(getMutiLang(function.getFunctionName()));
        /* int submenusize = function.getSubFunctionSize();
                if (submenusize == 0) {
                    menuString.append("</a></li>");
                }
                if (submenusize > 0) {
                    menuString.append("<b class=\"arrow\"></b><ul  class=\"submenu\" >");
                }
                menuString.append(getSubMenu(function,1,map));
                if (submenusize > 0) {
                    menuString.append("</ul></li>");
                }*/
        if (!function.hasSubFunction(map)) {
            menuString.append("</a></li>");
            menuString.append(getDIYSubMenu(function, 1, map));
        } else {
            menuString.append("<b class=\"arrow\"></b><ul  class=\"submenu\" >");
            menuString.append(getDIYSubMenu(function, 1, map));
            menuString.append("</ul></li>");
        }
        curIndex++;
    }
    return menuString.toString();
}


public String getWebosMenu(Map<Integer,List<TSFunction>> map){
    StringBuffer menuString = new StringBuffer();
    StringBuffer DeskpanelString = new StringBuffer();
    StringBuffer dataString = new StringBuffer();
    String menu = "";
    String desk = "";
    String data = "";
    // menu的全部json，这里包括对菜单的展示及每个二级菜单的点击出详情
    // menuString.append("[");
    menuString.append("{");
    // 绘制data.js数组，用于替换data.js中的app:{//桌面1 'dtbd':{ appid:'2534',,······
    dataString.append("{app:{");
    // 绘制Deskpanel数组，用于替换webos-core.js中的Icon1:['dtbd','sosomap','jinshan'],······
    DeskpanelString.append("{");
    List<TSFunction> pFunctions = (List<TSFunction>) map.get(0);
    if (pFunctions == null || pFunctions.size() == 0) {
        return "";
    }
    int n = 1;
    for (TSFunction pFunction : pFunctions) {
        // 是否有子菜单
        boolean hasSub = pFunction.hasSubFunction(map);
        // 绘制一级菜单
        // menuString.append("{ ");
        menuString.append("\"" + pFunction.getId() + "\":");
        menuString.append("{\"id\":\"" + pFunction.getId() + "\",\"name\":\"" + pFunction.getFunctionName() + "\",\"path\":\"" + ResourceUtil.allTSIcons.get(pFunction.getTSIcon().getId()).getIconPath() + "\",\"level\":\"" + pFunction.getFunctionLevel() + "\",");
        menuString.append("\"child\":{");
        // 绘制Deskpanel数组
        DeskpanelString.append("Icon" + n + ":[");
        // 绘制二级菜单
        if (hasSub) {
            // menuString.append(getWebosChild(pFunction, 1, map));
            DeskpanelString.append(getWebosDeskpanelChild(pFunction, 1, map));
            dataString.append(getWebosDataChild(pFunction, 1, map));
        }
        DeskpanelString.append("],");
        menuString.append("}},");
        n++;
    }
    menu = menuString.substring(0, menuString.toString().length() - 1);
    // menu += "]";
    menu += "}";
    data = dataString.substring(0, dataString.toString().length() - 1);
    data += "}}";
    desk = DeskpanelString.substring(0, DeskpanelString.toString().length() - 1);
    desk += "}";
    // 初始化为1，需减少一个个数。
    n = n - 1;
    // System.out.println("-------------------");
    // System.out.println(menu+"$$"+desk+"$$"+data+"$$"+"{\"total\":"+n+"}");
    return menu + "$$" + desk + "$$" + data + "$$" + n;
}


public String getEasyuiMultistageMenu(Map<Integer,List<TSFunction>> map){
    StringBuffer menuString = new StringBuffer();
    List<TSFunction> list = map.get(0);
    for (TSFunction function : list) {
        menuString.append("<div   title=\"" + getMutiLang(function.getFunctionName()) + "\" iconCls=\"" + ResourceUtil.allTSIcons.get(function.getTSIcon().getId()).getIconClas() + "\">");
        /*int submenusize = function.getSubFunctionSize();
			if (submenusize == 0) {
				menuString.append("</div>");
			}
			if (submenusize > 0) {
				menuString.append("<ul>");
			}
			menuString.append(getChild(function,1,map));
			if (submenusize > 0) {
				menuString.append("</ul></div>");
			}*/
        if (!function.hasSubFunction(map)) {
            menuString.append("</div>");
            menuString.append(getChild(function, 1, map));
        } else {
            menuString.append("<ul>");
            menuString.append(getChild(function, 1, map));
            menuString.append("</ul></div>");
        }
    }
    return menuString.toString();
}


public String getChild(TSFunction parent,int level,Map<Integer,List<TSFunction>> map){
    StringBuffer menuString = new StringBuffer();
    List<TSFunction> list = map.get(level);
    for (TSFunction function : list) {
        if (function.getTSFunction().getId().equals(parent.getId())) {
            if (!function.hasSubFunction(map)) {
                menuString.append(getLeaf(function));
            } else if (map.containsKey(level + 1)) {
                menuString.append("<div  class=\"easyui-accordion\"  fit=\"false\" border=\"false\">");
                // easy ui 默认展开第一级,所以这里设置一个控制,就不展开了
                menuString.append("<div></div>");
                menuString.append("<div title=\"" + getMutiLang(function.getFunctionName()) + "\" iconCls=\"" + ResourceUtil.allTSIcons.get(function.getTSIcon().getId()).getIconClas() + "\"><ul>");
                menuString.append(getChild(function, level + 1, map));
                menuString.append("</ul></div>");
                menuString.append("</div>");
            }
        }
    }
    return menuString.toString();
}


public String getWebosDeskpanelChild(TSFunction parent,int level,Map<Integer,List<TSFunction>> map){
    StringBuffer DeskpanelString = new StringBuffer();
    String desk = "";
    List<TSFunction> list = map.get(level);
    if (list == null || list.size() == 0) {
        return "";
    }
    for (TSFunction function : list) {
        if (function.getTSFunction().getId().equals(parent.getId())) {
            DeskpanelString.append("'" + function.getId() + "',");
        }
    }
    desk = DeskpanelString.substring(0, DeskpanelString.toString().length() - 1);
    return desk;
}


public String getHplusMultistageTree(Map<Integer,List<TSFunction>> map){
    if (map == null || map.size() == 0 || !map.containsKey(0)) {
        return "不具有任何权限,\n请找管理员分配权限";
    }
    StringBuffer menuString = new StringBuffer();
    List<TSFunction> list = map.get(0);
    int curIndex = 0;
    for (TSFunction function : list) {
        menuString.append("<li>");
        // update-begin--Author:chenj  Date:20160722 for：添加菜单图标样式
        if (function.getFunctionIconStyle() != null && !function.getFunctionIconStyle().trim().equals("")) {
            menuString.append("<a href=\"#\" class=\"\" ><i class=\"fa " + function.getFunctionIconStyle() + "\"></i>");
        } else {
            menuString.append("<a href=\"#\" class=\"\" ><i class=\"fa fa-columns\"></i>");
        }
        // update-begin--Author:chenj  Date:20160722 for：添加菜单图标样式
        menuString.append("<span class=\"menu-text\">");
        menuString.append(getMutiLang(function.getFunctionName()));
        menuString.append("</span>");
        menuString.append("<span class=\"fa arrow\">");
        menuString.append("</span>");
        if (!function.hasSubFunction(map)) {
            menuString.append("</a></li>");
        // menuString.append(getSubMenu(function,1,map));
        } else {
            // menuString.append("<b class=\"arrow icon-angle-down\"></b></a><ul  class=\"submenu\" >");
            menuString.append("</a><ul  class=\"nav nav-second-level\" >");
            menuString.append(getHplusSubMenu(function, 1, map));
            menuString.append("</ul></li>");
        }
        curIndex++;
    }
    return menuString.toString();
}


public String getChildOfTree(TSFunction parent,int level,Map<Integer,List<TSFunction>> map){
    StringBuffer menuString = new StringBuffer();
    List<TSFunction> list = map.get(level);
    for (TSFunction function : list) {
        if (function.getTSFunction().getId().equals(parent.getId())) {
            if (!function.hasSubFunction(map)) {
                menuString.append(getLeafOfTree(function));
            } else if (map.containsKey(level + 1)) {
                menuString.append("<li state=\"closed\" iconCls=\"" + ResourceUtil.allTSIcons.get(function.getTSIcon().getId()).getIconClas() + "\" ><span>" + getMutiLang(function.getFunctionName()) + "</span>");
                menuString.append("<ul >");
                menuString.append(getChildOfTree(function, level + 1, map));
                menuString.append("</ul></li>");
            }
        }
    }
    return menuString.toString();
}


public String getBootStrapChild(TSFunction parent,int level,Map<Integer,List<TSFunction>> map){
    StringBuffer menuString = new StringBuffer();
    List<TSFunction> list = map.get(level);
    if (list == null || list.size() == 0) {
        return "";
    }
    menuString.append("		<ul class=\"dropdown-menu\"> ");
    for (TSFunction function : list) {
        if (function.getTSFunction().getId().equals(parent.getId())) {
            boolean hasSub = function.hasSubFunction(map);
            String menu_url = function.getFunctionUrl();
            if (StringUtils.isNotEmpty(menu_url)) {
                if (function.getFunctionUrl().indexOf("?") > -1) {
                    menu_url += "&clickFunctionId=" + function.getId();
                } else {
                    menu_url += "?clickFunctionId=" + function.getId();
                }
            }
            menuString.append("		<li onclick=\"showContent(\'" + getMutiLang(function.getFunctionName()) + "\',\'" + menu_url + "\')\"  title=\"" + getMutiLang(function.getFunctionName()) + "\" url=\"" + function.getFunctionUrl() + "\" ");
            if (hasSub) {
                menuString.append(" class=\"dropdown-submenu\"");
            }
            menuString.append(" > ");
            menuString.append("			<a href=\"javascript:;\"> ");
            menuString.append("				<span class=\"bootstrap-icon\" style=\"background-image: url('" + function.getTSIcon().getIconPath() + "')\"></span>		 ");
            menuString.append(getMutiLang(function.getFunctionName()));
            menuString.append("			</a> ");
            if (hasSub) {
                menuString.append(getBootStrapChild(function, level + 1, map));
            }
            menuString.append("		</li> ");
        }
    }
    menuString.append("		</ul> ");
    return menuString.toString();
}


public String getMutiLang(String functionName){
    String lang_context = MutiLangUtil.getLang(functionName);
    return lang_context;
}


public String getWebosChild(TSFunction parent,int level,Map<Integer,List<TSFunction>> map){
    StringBuffer menuString = new StringBuffer();
    String menu = "";
    List<TSFunction> list = map.get(level);
    if (list == null || list.size() == 0) {
        return "";
    }
    for (TSFunction function : list) {
        if (function.getTSFunction().getId().equals(parent.getId())) {
            boolean hasSub = function.hasSubFunction(map);
            menuString.append("\"" + function.getId() + "\":");
            menuString.append("{\"id\":\"" + function.getId() + "\",\"name\":\"" + getMutiLang(function.getFunctionName()) + "\",\"path\":\"" + function.getTSIcon().getIconPath() + "\",\"url\":\"" + function.getFunctionUrl() + "\",\"level\":\"" + function.getFunctionLevel() + "\"}");
            if (hasSub) {
                menuString.append("\"child\":{");
                menuString.append(getWebosChild(function, level + 1, map));
                menuString.append("	} ");
            }
            menuString.append(",");
        }
    }
    menu = menuString.substring(0, menuString.toString().length() - 1);
    return menu;
}


public String getWebosDataChild(TSFunction parent,int level,Map<Integer,List<TSFunction>> map){
    StringBuffer dataString = new StringBuffer();
    String data = "";
    List<TSFunction> list = map.get(level);
    if (list == null || list.size() == 0) {
        return "";
    }
    for (TSFunction function : list) {
        if (function.getTSFunction().getId().equals(parent.getId())) {
            dataString.append("'" + function.getId() + "':{ ");
            dataString.append("appid:'" + function.getId() + "',");
            dataString.append("url:'" + function.getFunctionUrl() + "',");
            // update-begin--Author:zhangguoming  Date:20140509 for：添加云桌面图标
            // dataString.append(getIconandName(function.getFunctionName()));
            dataString.append(getIconAndNameForDesk(function));
            // update-end--Author:zhangguoming  Date:20140509 for：添加云桌面图标
            dataString.append("asc :" + function.getFunctionOrder());
            dataString.append(" },");
        }
    }
    // data = dataString.substring(0, dataString.toString().length()-1);
    data = dataString.toString();
    return data;
}


public String getBootMenu(List<TSFunction> pFunctions,List<TSFunction> functions){
    StringBuffer menuString = new StringBuffer();
    menuString.append("<ul>");
    for (TSFunction pFunction : pFunctions) {
        menuString.append("<li><a href=\"#\"><span class=\"icon16 icomoon-icon-stats-up\"></span><b>" + getMutiLang(pFunction.getFunctionName()) + "</b></a>");
        /*
			int submenusize = pFunction.getSubFunctionSize();
			if (submenusize == 0) {
				menuString.append("</li>");
			}
			if (submenusize > 0) {
				menuString.append("<ul class=\"sub\">");
			}
			for (TSFunction function : functions) {

				if (function.getTSFunction().getId().equals(pFunction.getId())) {
					menuString
							.append("<li><a href=\""
									+ function.getFunctionUrl()
									+ "\" target=\"contentiframe\"><span class=\"icon16 icomoon-icon-file\"></span>"
									+ getMutiLang(function.getFunctionName()) + "</a></li>");
				}
			}
			if (submenusize > 0) {
				menuString.append("</ul></li>");
			}*/
        boolean hasSubfun = pFunction.hasSubFunction(functions);
        if (!hasSubfun) {
            menuString.append("</li>");
        } else {
            menuString.append("<ul class=\"sub\">");
        }
        for (TSFunction function : functions) {
            if (function.getTSFunction().getId().equals(pFunction.getId())) {
                menuString.append("<li><a href=\"" + function.getFunctionUrl() + "\" target=\"contentiframe\"><span class=\"icon16 icomoon-icon-file\"></span>" + getMutiLang(function.getFunctionName()) + "</a></li>");
            }
        }
        if (hasSubfun) {
            menuString.append("</ul></li>");
        }
    }
    menuString.append("</ul>");
    return menuString.toString();
}


public String getACESubMenu(TSFunction parent,int level,Map<Integer,List<TSFunction>> map){
    StringBuffer menuString = new StringBuffer();
    List<TSFunction> list = map.get(level);
    for (TSFunction function : list) {
        if (function.getTSFunction().getId().equals(parent.getId())) {
            if (!function.hasSubFunction(map)) {
                menuString.append(getLeafOfACETree(function, map));
            } else {
                /* 20160830 wangkun TASK #1330 【改造】ace首页风格，菜单不支持三级菜单，改造支持三级*/
                menuString.append("<li>");
                // update-begin--Author:chenj  Date:20160722 for：添加菜单图标样式
                if (function.getFunctionIconStyle() != null && !function.getFunctionIconStyle().trim().equals("")) {
                    menuString.append("<a href=\"#\" class=\"dropdown-toggle\" ><i class=\"" + function.getFunctionIconStyle() + "\"></i>");
                } else {
                    menuString.append("<a href=\"#\" class=\"dropdown-toggle\" ><i class=\"" + SysACEIconEnum.toEnum(function.getTSIcon().getIconClas()).getThemes() + "\"></i>");
                }
                menuString.append("<span class=\"menu-text\">");
                menuString.append(getMutiLang(function.getFunctionName()));
                menuString.append("</span>");
                menuString.append("<b class=\"arrow icon-angle-down\"></b></a><ul  class=\"submenu\" >");
                menuString.append(getACESubMenu(function, 2, map));
                menuString.append("</ul></li>");
            /* 20160830 wangkun TASK #1330 【改造】ace首页风格，菜单不支持三级菜单，改造支持三级*/
            }
        }
    }
    return menuString.toString();
}


public String getHplusSubMenu(TSFunction parent,int level,Map<Integer,List<TSFunction>> map){
    StringBuffer menuString = new StringBuffer();
    List<TSFunction> list = map.get(level);
    for (TSFunction function : list) {
        if (function.getTSFunction().getId().equals(parent.getId())) {
            if (!function.hasSubFunction(map)) {
                menuString.append(getLeafOfHplusTree(function, map));
            } else {
                menuString.append(getLeafOfHplusTree(function, map));
            }
        }
    }
    return menuString.toString();
}


public String getEasyuiMultistageTree(Map<Integer,List<TSFunction>> map,String style){
    if (map == null || map.size() == 0 || !map.containsKey(0)) {
        return "不具有任何权限,\n请找管理员分配权限";
    }
    StringBuffer menuString = new StringBuffer();
    List<TSFunction> list = map.get(0);
    int curIndex = 0;
    if ("easyui".equals(style)) {
        for (TSFunction function : list) {
            if (curIndex == 0) {
                // 第一个菜单，默认展开
                menuString.append("<li>");
            } else {
                menuString.append("<li state='closed'>");
            }
            menuString.append("<span>").append(getMutiLang(function.getFunctionName())).append("</span>");
            if (!function.hasSubFunction(map)) {
                menuString.append("</li>");
                menuString.append(getChildOfTree(function, 1, map));
            } else {
                menuString.append("<ul>");
                menuString.append(getChildOfTree(function, 1, map));
                menuString.append("</ul></li>");
            }
            curIndex++;
        }
    } else if ("shortcut".equals(style)) {
        for (TSFunction function : list) {
            menuString.append("<div   title=\"" + getMutiLang(function.getFunctionName()) + "\" iconCls=\"" + ResourceUtil.allTSIcons.get(function.getTSIcon().getId()).getIconClas() + "\">");
            if (!function.hasSubFunction(map)) {
                menuString.append("</div>");
                menuString.append(getChildOfTree(function, 1, map));
            } else {
                menuString.append("<ul class=\"easyui-tree tree-lines\"  fit=\"false\" border=\"false\">");
                menuString.append(getChildOfTree(function, 1, map));
                menuString.append("</ul></div>");
            }
        }
    }
    return menuString.toString();
}


public void iterGet(List<TSFunction> set1,String pid,StringBuffer buffer){
    for (TSFunction node : set1) {
        // 查找所有父节点为pid的所有对象，然后拼接为json格式的数据
        count++;
        if (node.getTSFunction().getId().equals(pid)) {
            buffer.append("{\"menuid\":\"" + node.getId() + " \",\"icon\":\"" + ResourceUtil.allTSIcons.get(node.getTSIcon().getId()).getIconClas() + "\"," + "\"menuname\":\"" + getMutiLang(node.getFunctionName()) + "\",\"url\":\"" + node.getFunctionUrl() + "\"");
            if (count == set1.size()) {
                buffer.append("}\n");
            }
            buffer.append("},\n");
        }
    }
}


@Deprecated
public String getIconandName(String functionName){
    StringBuffer dataString = new StringBuffer();
    if ("online开发".equals(functionName)) {
        dataString.append("icon:'Customize.png',");
    } else if ("表单配置".equals(functionName)) {
        dataString.append("icon:'Applications Folder.png',");
    } else if ("动态表单配置".equals(functionName)) {
        dataString.append("icon:'Documents Folder.png',");
    } else if ("用户分析".equals(functionName)) {
        dataString.append("icon:'User.png',");
    } else if ("报表分析".equals(functionName)) {
        dataString.append("icon:'Burn.png',");
    } else if ("用户管理".equals(functionName)) {
        dataString.append("icon:'Finder.png',");
    } else if ("数据字典".equals(functionName)) {
        dataString.append("icon:'friendnear.png',");
    } else if ("角色管理".equals(functionName)) {
        dataString.append("icon:'friendgroup.png',");
    } else if ("菜单管理".equals(functionName)) {
        dataString.append("icon:'kaikai.png',");
    } else if ("图标管理".equals(functionName)) {
        dataString.append("icon:'kxjy.png',");
    } else if ("表单验证".equals(functionName)) {
        dataString.append("icon:'qidianzhongwen.png',");
    } else if ("一对多模型".equals(functionName)) {
        dataString.append("icon:'qqread.png',");
    } else if ("特殊布局".equals(functionName)) {
        dataString.append("icon:'xiami.png',");
    } else if ("在线word".equals(functionName)) {
        dataString.append("icon:'musicbox.png',");
    } else if ("多附件管理".equals(functionName)) {
        dataString.append("icon:'vadio.png',");
    } else if ("数据监控".equals(functionName)) {
        dataString.append("icon:'Super Disk.png',");
    } else if ("定时任务".equals(functionName)) {
        dataString.append("icon:'Utilities.png',");
    } else if ("系统日志".equals(functionName)) {
        dataString.append("icon:'fastsearch.png',");
    } else if ("在线维护".equals(functionName)) {
        dataString.append("icon:'Utilities Folder.png',");
    } else {
        dataString.append("icon:'folder_o.png',");
    }
    dataString.append("name:'" + functionName + "',");
    return dataString.toString();
}


public String getFineuiSubMenu(TSFunction parent,int level,Map<Integer,List<TSFunction>> map){
    StringBuffer menuString = new StringBuffer();
    List<TSFunction> list = map.get(level);
    for (TSFunction function : list) {
        if (function.getTSFunction().getId().equals(parent.getId())) {
            if (!function.hasSubFunction(map)) {
                menuString.append(getLeafOfFineuiTree(function, map));
            } else {
                menuString.append(getLeafOfFineuiTree(function, map));
            }
        }
    }
    return menuString.toString();
}


public String getEasyuiMenu(List<TSFunction> pFunctions,List<TSFunction> functions){
    StringBuffer menuString = new StringBuffer();
    for (TSFunction pFunction : pFunctions) {
        menuString.append("<div  title=\"" + getMutiLang(pFunction.getFunctionName()) + "\" iconCls=\"" + ResourceUtil.allTSIcons.get(pFunction.getTSIcon().getId()).getIconClas() + "\">");
        /*int submenusize = pFunction.getSubFunctionSize();
			if (submenusize == 0) {
				menuString.append("</div>");
			}
			if (submenusize > 0) {
				menuString.append("<ul>");
			}
			for (TSFunction function : functions) {

				if (function.getTSFunction().getId().equals(pFunction.getId())) {
					String icon = "folder";
					if (function.getTSIcon() != null) {
						icon = ResourceUtil.allTSIcons.get(function.getTSIcon().getId()).getIconClas();
					}
					menuString.append("<li><div onclick=\"addTab(\'"
							+ getMutiLang(function.getFunctionName()) + "\',\'"
							+ function.getFunctionUrl() + "&clickFunctionId="
							+ function.getId() + "\',\'" + icon
							+ "\')\"  title=\"" + getMutiLang(function.getFunctionName())
							+ "\" url=\"" + function.getFunctionUrl()
							+ "\" iconCls=\"" + icon + "\"> <a class=\""
							+ getMutiLang(function.getFunctionName())
							+ "\" href=\"#\" > <span class=\"icon " + icon
							+ "\" >&nbsp;</span> <span class=\"nav\" >"
							+ getMutiLang(function.getFunctionName())
							+ "</span></a></div></li>");
				}
			}
			if (submenusize > 0) {
				menuString.append("</ul></div>");
			}*/
        boolean hasSubfun = pFunction.hasSubFunction(functions);
        if (!hasSubfun) {
            menuString.append("</div>");
        } else {
            menuString.append("<ul>");
        }
        for (TSFunction function : functions) {
            if (function.getTSFunction().getId().equals(pFunction.getId())) {
                String icon = "folder";
                if (function.getTSIcon() != null) {
                    icon = ResourceUtil.allTSIcons.get(function.getTSIcon().getId()).getIconClas();
                }
                String funUrl = function.getFunctionUrl();
                if (funUrl.indexOf("?") > -1) {
                    funUrl += "&clickFunctionId=" + function.getId();
                } else {
                    funUrl += "?clickFunctionId=" + function.getId();
                }
                menuString.append("<li><div onclick=\"addTab(\'" + getMutiLang(function.getFunctionName()) + "\',\'" + funUrl + "\',\'" + icon + "\')\"  title=\"" + getMutiLang(function.getFunctionName()) + "\" url=\"" + function.getFunctionUrl() + "\" iconCls=\"" + icon + "\"> <a class=\"" + getMutiLang(function.getFunctionName()) + "\" href=\"#\" > <span class=\"icon " + icon + "\" >&nbsp;</span> <span class=\"nav\" >" + getMutiLang(function.getFunctionName()) + "</span></a></div></li>");
            }
        }
        if (hasSubfun) {
            menuString.append("</ul></div>");
        }
    }
    return menuString.toString();
}


}