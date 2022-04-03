package org.jeecgframework.core.util;
 import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.jeecgframework.core.enums.SysThemesEnum;
public class SysThemesUtil {


public String getValidformTablefrom(SysThemesEnum sysThemesEnum,String basePath){
    StringBuffer sb = new StringBuffer("");
    if ("metro".equals(sysThemesEnum.getThemes())) {
        sb.append("<link rel=\"stylesheet\" href=\"" + basePath + "/plug-in/Validform/css/metro/tablefrom.css\" type=\"text/css\"/>");
    } else if ("metrole".equals(sysThemesEnum.getThemes())) {
        sb.append("<link rel=\"stylesheet\" href=\"" + basePath + "/plug-in/Validform/css/metrole/tablefrom.css\" type=\"text/css\"/>");
    } else {
        sb.append("<link rel=\"stylesheet\" href=\"" + basePath + "/plug-in/Validform/css/tablefrom.css\" type=\"text/css\"/>");
    }
    return sb.toString();
}


public String getEasyUiTheme(SysThemesEnum sysThemesEnum,String basePath){
    StringBuffer sb = new StringBuffer("");
    sb.append("<link id=\"easyuiTheme\" rel=\"stylesheet\" href=\"" + basePath + "/plug-in/easyui/themes/" + sysThemesEnum.getThemes() + "/easyui.css\" type=\"text/css\"></link>");
    return sb.toString();
}


public String getBootstrapTabTheme(SysThemesEnum sysThemesEnum){
    StringBuffer sb = new StringBuffer("");
    String basePath = ResourceUtil.getBasePath();
    if ("metro".equals(sysThemesEnum.getThemes())) {
        sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/ace/js/bootstrap-tab.js\"></script>");
    } else if ("metrole".equals(sysThemesEnum.getThemes())) {
        sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/ace/js/bootstrap-tab.js\"></script>");
    }
    return sb.toString();
}


public SysThemesEnum getSysTheme(HttpServletRequest request){
    String indexStyle = null;
    try {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie == null || StringUtils.isEmpty(cookie.getName())) {
                continue;
            }
            if (cookie.getName().equalsIgnoreCase("JEECGINDEXSTYLE")) {
                indexStyle = cookie.getValue();
            }
        }
    } catch (Exception e) {
    }
    return SysThemesEnum.toEnum(indexStyle);
}


public String getCommonTheme(SysThemesEnum sysThemesEnum,String basePath){
    StringBuffer sb = new StringBuffer("");
    if ("metro".equals(sysThemesEnum.getThemes())) {
        sb.append("<link rel=\"stylesheet\" href=\"" + basePath + "/plug-in/tools/css/metro/common.css\" type=\"text/css\"></link>");
    } else if ("metrole".equals(sysThemesEnum.getThemes())) {
        sb.append("<link rel=\"stylesheet\" href=\"" + basePath + "/plug-in/tools/css/metrole/common.css\" type=\"text/css\"></link>");
    } else {
        sb.append("<link rel=\"stylesheet\" href=\"" + basePath + "/plug-in/tools/css/common.css\" type=\"text/css\"></link>");
    }
    // update-start--Author: chenj  Date:20160815 for：TASK #1040 【UI按钮标签ace样式】列表后面的操作按钮支持按钮标签样式设置，
    sb.append("<link rel=\"stylesheet\" href=\"" + basePath + "/plug-in/ace/css/font-awesome.css\" type=\"text/css\"></link>");
    // update-start--Author: chenj  Date:20160815 for：TASK #1040 【UI按钮标签ace样式】列表后面的操作按钮支持按钮标签样式设置，
    return sb.toString();
}


public String getValidformStyleTheme(SysThemesEnum sysThemesEnum,String basePath){
    StringBuffer sb = new StringBuffer("");
    if ("metro".equals(sysThemesEnum.getThemes())) {
        sb.append("<link rel=\"stylesheet\" href=\"" + basePath + "/plug-in/Validform/css/metro/style.css\" type=\"text/css\"/>");
    } else if ("metrole".equals(sysThemesEnum.getThemes())) {
        sb.append("<link rel=\"stylesheet\" href=\"" + basePath + "/plug-in/Validform/css/metrole/style.css\" type=\"text/css\"/>");
    } else {
        sb.append("<link rel=\"stylesheet\" href=\"" + basePath + "/plug-in/Validform/css/style.css\" type=\"text/css\"/>");
    }
    return sb.toString();
}


public String getLhgdialogTheme(SysThemesEnum sysThemesEnum,String basePath){
    StringBuffer sb = new StringBuffer("");
    if ("metro".equals(sysThemesEnum.getThemes())) {
        sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/lhgDialog/lhgdialog.min.js?skin=metro\"></script>");
    } else if ("metrole".equals(sysThemesEnum.getThemes())) {
        sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/lhgDialog/lhgdialog.min.js?skin=metrole\"></script>");
    } else {
        sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/lhgDialog/lhgdialog.min.js\"></script>");
    }
    return sb.toString();
}


public String getEasyUiIconTheme(SysThemesEnum sysThemesEnum){
    StringBuffer sb = new StringBuffer("");
    String basePath = ResourceUtil.getBasePath();
    if ("metrole".equals(sysThemesEnum.getThemes())) {
        sb.append("<link id=\"easyuiTheme\" rel=\"stylesheet\" href=\"" + basePath + "/plug-in/easyui/themes/metrole/icon.css\" type=\"text/css\"></link>");
    } else {
        sb.append("<link id=\"easyuiTheme\" rel=\"stylesheet\" href=\"" + basePath + "/plug-in/easyui/themes/icon.css\" type=\"text/css\"></link>");
    }
    return sb.toString();
}


public String getReportTheme(SysThemesEnum sysThemesEnum){
    StringBuffer sb = new StringBuffer("");
    String basePath = ResourceUtil.getBasePath();
    if ("metro".equals(sysThemesEnum.getThemes())) {
        sb.append("<link rel=\"stylesheet\" href=\"" + basePath + "/plug-in/graphreport/css/metro/report.css\" type=\"text/css\"></link>");
    } else if ("metrole".equals(sysThemesEnum.getThemes())) {
        sb.append("<link rel=\"stylesheet\" href=\"" + basePath + "/plug-in/graphreport/css/metrole/report.css\" type=\"text/css\"></link>");
    } else {
        sb.append("<link rel=\"stylesheet\" href=\"" + basePath + "/plug-in/graphreport/css/report.css\" type=\"text/css\"></link>");
    }
    return sb.toString();
}


public String getEasyUiMainTheme(SysThemesEnum sysThemesEnum,String basePath){
    StringBuffer sb = new StringBuffer("");
    if ("metro".equals(sysThemesEnum.getThemes())) {
        sb.append("<link id=\"easyuiTheme\" rel=\"stylesheet\" href=\"" + basePath + "/plug-in/easyui/themes/metro/main.css\" type=\"text/css\"></link>");
    } else if ("metrole".equals(sysThemesEnum.getThemes())) {
        sb.append("<link id=\"easyuiTheme\" rel=\"stylesheet\" href=\"" + basePath + "/plug-in/easyui/themes/metrole/main.css\" type=\"text/css\"></link>");
    }
    return sb.toString();
}


public String getValidformDivfromTheme(SysThemesEnum sysThemesEnum){
    StringBuffer sb = new StringBuffer("");
    String basePath = ResourceUtil.getBasePath();
    if ("metro".equals(sysThemesEnum.getThemes())) {
        sb.append("<link rel=\"stylesheet\" href=\"" + basePath + "/plug-in/Validform/css/metro/divfrom.css\" type=\"text/css\"/>");
    } else if ("metrole".equals(sysThemesEnum.getThemes())) {
        sb.append("<link rel=\"stylesheet\" href=\"" + basePath + "/plug-in/Validform/css/metrole/divfrom.css\" type=\"text/css\"/>");
    } else {
        sb.append("<link rel=\"stylesheet\" href=\"" + basePath + "/plug-in/Validform/css/divfrom.css\" type=\"text/css\"/>");
    }
    return sb.toString();
}


}