package org.jeecgframework.tag.core.easyui;
 import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.enums.MenuButtonsEnum;
import org.jeecgframework.core.online.util.FreemarkerHelper;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.web.system.pojo.base.TSOperation;
import org.jeecgframework.web.system.service.SystemService;
public class MenuButtonsTag extends TagSupport{

 private  long serialVersionUID;

 private  String name;

 private  String codes;

 private  boolean notIn;

 private  boolean mm;

 private  boolean group;

 private  boolean superQuery;

 private  SystemService systemService;


public boolean hasAuth(String code,List<String> optcodes){
    // 该request中获取的code list是配置了但未经授权的 所以这里要取反
    return !optcodes.contains(name + "-" + code);
}


public void setName(String name){
    this.name = name;
}


public void addAdvancedQuery(StringBuffer sb,String queryCode,String tableName){
    MenuButtonsEnum menu = MenuButtonsEnum.getMenuByCode("superQuery");
    sb.append("<a title=\"" + menu.getTitle() + "\" onclick=\"superQuery()\" href=\"####\" class=\"btn-menu " + menu.getIcon() + " menu-more\" ></a>");
// onclick=\"superQuery('"+codes+"','"+tableName+"')\"
/*FreemarkerHelper free = new FreemarkerHelper();
		Map<String, Object> mainConfig = new HashMap<String, Object>();
		mainConfig.put("queryCode", queryCode);
		mainConfig.put("tableName", tableName);
		String complexSuperQuery = free.parseTemplate("/org/jeecgframework/tag/ftl/complexSuperQueryForPage.ftl", mainConfig);
		appendLine(sb,complexSuperQuery);*/
}


public String getName(){
    return name;
}


public boolean isNotIn(){
    return notIn;
}


public void setMm(boolean mm){
    this.mm = mm;
}


public boolean isSuperQuery(){
    return superQuery;
}


public int doEndTag(){
    JspWriter out = null;
    try {
        out = this.pageContext.getOut();
        out.print(end());
        out.flush();
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (out != null) {
            try {
                out.clear();
                out.close();
            } catch (Exception e) {
            }
        }
    }
    return EVAL_PAGE;
}


public void loadGroupJs(StringBuffer sb){
    // sb.append("<script>function showTabDorpdownMenuty(obj){$(obj).parent('.awesome-group').addClass('dropdown');}$('.awesome-group .righticon').hover(function(){$(this).parent('.awesome-group').addClass('dropdown');");
    sb.append("<script>$('.awesome-group .lefticon').hover(function(){$(this).parent('.awesome-group').addClass('dropdown');");
    sb.append("},function(){});$('.awesome-group').hover(function(){$(this).addClass('active');},function(){$(this).removeClass('active');$(this).removeClass('dropdown');});</script>");
}


public int doStartTag(){
    return super.doStartTag();
}


public List<String> getOperationcodes(){
    // 权限判断
    List<String> list = new ArrayList<String>();
    Set<String> operationCodeIds = (Set<String>) super.pageContext.getRequest().getAttribute(Globals.OPERATIONCODES);
    systemService = ApplicationContextUtil.getContext().getBean(SystemService.class);
    if (null != operationCodeIds) {
        for (String operationCodeId : operationCodeIds) {
            if (oConvertUtils.isEmpty(operationCodeId))
                continue;
            TSOperation operation = systemService.getEntity(TSOperation.class, operationCodeId);
            if (operation != null) {
                list.add(operation.getOperationcode());
            }
        }
    }
    return list;
}


public void setGroup(boolean group){
    this.group = group;
}


public void initMenu(StringBuffer sb,MenuButtonsEnum menu){
    if (!"superQuery".equals(menu.getFun())) {
        String arg = mm ? "1" : "";
        if (menu.getCode().contains("group")) {
            this.group = true;
            String[] funs = menu.getFun().split("&");
            String[] titles = menu.getTitle().split("&");
            String[] icons = menu.getIcon().split("&");
            sb.append("<div class='awesome-group'>");
            sb.append("<a onclick='" + funs[0] + "(" + arg + ")' href='####' class='withicon group-btn lefticon " + icons[icons.length - 1] + "'></a>");
            // <a onclick='showTabDorpdownMenuty(this)' href='####' class='withicon group-btn righticon fa fa-caret-down'></a>
            sb.append("<ul class='awe-group-dropdown'>");
            for (int i = 0; i < funs.length; i++) {
                sb.append("<li><a onclick='" + funs[i] + "(" + arg + ")' href='####' title='" + titles[i] + "' class='withmenu group-btn " + icons[i] + "'></a></li>");
            }
            sb.append("</ul></div>");
        } else {
            sb.append("<a onclick=\"" + menu.getFun() + "(" + arg + ")\" href=\"####\" class=\"btn-menu " + menu.getIcon() + " menu-more\" title=\"" + menu.getTitle() + "\"></a>");
        }
    }
}


public String getCodes(){
    return codes;
}


public void setCodes(String codes){
    this.codes = codes;
}


public void setNotIn(boolean notIn){
    this.notIn = notIn;
}


public void setSuperQuery(boolean superQuery){
    this.superQuery = superQuery;
}


public void appendLine(StringBuffer sb,String str){
    // 调试  格式化
    String format = "\r\n";
    sb.append(str).append(format);
}


public String end(){
    if (oConvertUtils.isEmpty(codes)) {
        return "";
    }
    StringBuffer sb = new StringBuffer();
    List<String> optcodes = null;
    boolean flag = false;
    if (ResourceUtil.getSessionUser().getUserName().equals("admin") || !Globals.BUTTON_AUTHORITY_CHECK) {
        flag = true;
    } else {
        optcodes = getOperationcodes();
        if (optcodes == null || optcodes.size() <= 0) {
            flag = true;
        }
    }
    if (superQuery) {
        addAdvancedQuery(sb, codes, name);
    }
    if (codes.equals("ALL")) {
        // 所有菜单均加载
        MenuButtonsEnum[] menuArr = MenuButtonsEnum.values();
        for (int k = menuArr.length; k > 0; k--) {
            MenuButtonsEnum menu = menuArr[k - 1];
            if (flag) {
                initMenu(sb, menu);
            } else {
                if (hasAuth(menu.getCode(), optcodes)) {
                    initMenu(sb, menu);
                }
            }
        }
    } else {
        if (isNotIn()) {
            // 加载非
            MenuButtonsEnum[] menuArr = MenuButtonsEnum.values();
            for (int k = menuArr.length; k > 0; k--) {
                MenuButtonsEnum menu = menuArr[k - 1];
                // TODO 用的是indexOf有隐患
                if (codes.indexOf(menu.getCode()) < 0) {
                    if (flag) {
                        initMenu(sb, menu);
                    } else {
                        if (hasAuth(menu.getCode(), optcodes)) {
                            initMenu(sb, menu);
                        }
                    }
                }
            }
        } else {
            String[] codeArr = codes.split(",");
            for (int i = codeArr.length; i > 0; i--) {
                String c = codeArr[i - 1];
                MenuButtonsEnum menu = MenuButtonsEnum.getMenuByCode(c);
                if (menu == null) {
                    continue;
                }
                if (flag) {
                    initMenu(sb, menu);
                } else {
                    if (hasAuth(c, optcodes)) {
                        initMenu(sb, menu);
                    }
                }
            }
        }
    }
    // System.out.println(name+"--"+sb.toString());
    if (this.group) {
        loadGroupJs(sb);
    }
    return sb.toString();
}


public boolean isMm(){
    return mm;
}


public boolean isGroup(){
    return group;
}


}