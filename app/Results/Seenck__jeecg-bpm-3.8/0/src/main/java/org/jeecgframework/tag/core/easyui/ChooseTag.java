package org.jeecgframework.tag.core.easyui;
 import java.io.IOException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import org.jeecgframework.core.util.MutiLangUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.UUIDGenerator;
public class ChooseTag extends TagSupport{

 private  long serialVersionUID;

 protected  String hiddenName;

 protected  String textname;

 protected  String icon;

 protected  String title;

 protected  String url;

 protected  String top;

 protected  String left;

 protected  String width;

 protected  String height;

 protected  String name;

 protected  String hiddenid;

 protected  Boolean isclear;

 protected  String fun;

 protected  String inputTextname;

 protected  String langArg;

 protected  Boolean isInit;


public void setName(String name){
    this.name = name;
}


public String getLangArg(){
    return langArg;
}


public void setLeft(String left){
    this.left = left;
}


public void setLangArg(String langArg){
    this.langArg = langArg;
}


public void setTitle(String title){
    this.title = title;
}


public void setHiddenName(String hiddenName){
    this.hiddenName = hiddenName;
}


public String getInputTextname(){
    return inputTextname;
}


public void setIsInit(Boolean isInit){
    this.isInit = isInit;
}


public void setWidth(String width){
    this.width = width;
}


public void setHeight(String height){
    this.height = height;
}


public int doEndTag(){
    JspWriter out = null;
    try {
        title = MutiLangUtil.doMutiLang(title, langArg);
        out = this.pageContext.getOut();
        out.print(end().toString());
        out.flush();
    } catch (IOException e) {
        e.printStackTrace();
    }
    return EVAL_PAGE;
}


public void clearAll(StringBuffer sb,String methodname){
    String[] textnames = null;
    String[] inputTextnames = null;
    // update-begin--Author: jg_huangxg  Date: 20160330 for： 防止空指针
    if (!StringUtil.isEmpty(this.textname)) {
        textnames = textname.split(",");
    }
    // update-end--Author: jg_huangxg  Date: 20160330 for： 防止空指针
    if (StringUtil.isNotEmpty(inputTextname)) {
        inputTextnames = inputTextname.split(",");
    } else {
        inputTextnames = textnames;
    }
    if (isclear && StringUtil.isNotEmpty(textname)) {
        sb.append("function clearAll_" + methodname + "(){");
        for (int i = 0; i < textnames.length; i++) {
            inputTextnames[i] = inputTextnames[i].replaceAll("\\[", "\\\\\\\\[").replaceAll("\\]", "\\\\\\\\]").replaceAll("\\.", "\\\\\\\\.");
            sb.append("if($(\'#" + inputTextnames[i] + "\').length>=1){");
            sb.append("$(\'#" + inputTextnames[i] + "\').val('');");
            sb.append("$(\'#" + inputTextnames[i] + "\').blur();");
            sb.append("}");
            sb.append("if($(\"input[name='" + inputTextnames[i] + "']\").length>=1){");
            sb.append("$(\"input[name='" + inputTextnames[i] + "']\").val('');");
            sb.append("$(\"input[name='" + inputTextnames[i] + "']\").blur();");
            sb.append("}");
        }
        sb.append("$(\'#" + hiddenName + "\').val(\"\");");
        sb.append("}");
    }
}


public void setIsclear(Boolean isclear){
    this.isclear = isclear;
}


public void setUrl(String url){
    this.url = url;
}


public int doStartTag(){
    return EVAL_PAGE;
}


public void setIcon(String icon){
    this.icon = icon;
}


public void setTextname(String textname){
    this.textname = textname;
}


public void setTop(String top){
    this.top = top;
}


public void setHiddenid(String hiddenid){
    this.hiddenid = hiddenid;
}


public void callback(StringBuffer sb,String methodname){
    sb.append("function clickcallback_" + methodname + "(){");
    sb.append("iframe = this.iframe.contentWindow;");
    String[] textnames = null;
    String[] inputTextnames = null;
    if (StringUtil.isNotEmpty(textname)) {
        textnames = textname.split(",");
        if (StringUtil.isNotEmpty(inputTextname)) {
            inputTextnames = inputTextname.split(",");
        } else {
            inputTextnames = textnames;
        }
        for (int i = 0; i < textnames.length; i++) {
            inputTextnames[i] = inputTextnames[i].replaceAll("\\[", "\\\\\\\\[").replaceAll("\\]", "\\\\\\\\]").replaceAll("\\.", "\\\\\\\\.");
            sb.append("var " + textnames[i] + "=iframe.get" + name + "Selections(\'" + textnames[i] + "\');	");
            sb.append("if($(\'#" + inputTextnames[i] + "\').length>=1){");
            sb.append("$(\'#" + inputTextnames[i] + "\').val(" + textnames[i] + ");");
            sb.append("$(\'#" + inputTextnames[i] + "\').blur();");
            sb.append("}");
            sb.append("if($(\"input[name='" + inputTextnames[i] + "']\").length>=1){");
            sb.append("$(\"input[name='" + inputTextnames[i] + "']\").val(" + textnames[i] + ");");
            sb.append("$(\"input[name='" + inputTextnames[i] + "']\").blur();");
            sb.append("}");
        }
    }
    if (StringUtil.isNotEmpty(hiddenName)) {
        sb.append("var id =iframe.get" + name + "Selections(\'" + hiddenid + "\');");
        sb.append("if (id!== undefined &&id!=\"\"){");
        sb.append("$(\'#" + hiddenName + "\').val(id);");
        sb.append("}");
    }
    if (StringUtil.isNotEmpty(fun)) {
        // 执行自定义函数
        sb.append("" + fun + "();");
    }
    sb.append("}");
}


public StringBuffer end(){
    String confirm = MutiLangUtil.getLang("common.confirm");
    String cancel = MutiLangUtil.getLang("common.cancel");
    String methodname = UUIDGenerator.generate().replaceAll("-", "");
    StringBuffer sb = new StringBuffer();
    sb.append("<a href=\"#\" class=\"easyui-linkbutton\" plain=\"true\" icon=\"" + icon + "\" onClick=\"choose_" + methodname + StringUtil.replace("()\">{0}</a>", "{0}", MutiLangUtil.getLang("common.select", langArg)));
    if (isclear && StringUtil.isNotEmpty(textname)) {
        sb.append("<a href=\"#\" class=\"easyui-linkbutton\" plain=\"true\" icon=\"icon-redo\" onClick=\"clearAll_" + methodname + StringUtil.replace("();\">{0}</a>", "{0}", MutiLangUtil.getLang("common.clear", langArg)));
    }
    sb.append("<script type=\"text/javascript\">");
    // --author：scott-----start----date:20170407--------for: 异常捕获避免js报错-------------
    sb.append("var windowapi;");
    sb.append("try{");
    sb.append("windowapi = frameElement.api, W = windowapi.opener;");
    sb.append("}catch(e){}");
    // --author：scott-----end----date:20170407--------for: 异常捕获避免js报错-------------
    sb.append("function choose_" + methodname + "(){");
    // --author：zhoujf-----start----date:20150531--------for: 编辑用户，选择角色,弹出的角色列表页面，默认没选中 标签扩展
    sb.append("var url = ").append("'").append(url).append("';");
    if (isInit) {
        sb.append("var initValue = ").append("$(\'#" + hiddenName + "\').val();");
        sb.append("url += ").append("'&ids='+initValue;");
    }
    // --author：zhoujf------end-----date:20150531--------for: 编辑用户，选择角色,弹出的角色列表页面，默认没选中 标签扩展
    sb.append("if(typeof(windowapi) == 'undefined'){");
    sb.append("$.dialog({");
    sb.append("content: \'url:\'+url,");
    // update-begin--author: scott  Date: 20170307 for： 弹出窗口遮挡问题--
    sb.append("zIndex: getzIndex(),");
    // update-end--author: scott  Date: 20170307 for： 弹出窗口遮挡问题--
    if (title != null) {
        sb.append("title: \'" + title + "\',");
    }
    sb.append("lock : true,");
    if (width != null) {
        if (!width.toLowerCase().endsWith("px")) {
            sb.append("width :\'" + width + "px\',");
        } else {
            sb.append("width :\'" + width + "\',");
        }
    } else {
        sb.append("width :400,");
    }
    if (height != null) {
        if (!height.toLowerCase().endsWith("px")) {
            sb.append("height :\'" + height + "px\',");
        } else {
            sb.append("height :\'" + height + "\',");
        }
    } else {
        sb.append("height :350,");
    }
    if (left != null) {
        sb.append("left :\'" + left + "\',");
    } else {
        sb.append("left :'85%',");
    }
    if (top != null) {
        sb.append("top :\'" + top + "\',");
    } else {
        sb.append("top :'65%',");
    }
    sb.append("opacity : 0.4,");
    sb.append("button : [ {");
    sb.append(StringUtil.replace("name : \'{0}\',", "{0}", confirm));
    sb.append("callback : clickcallback_" + methodname + ",");
    sb.append("focus : true");
    sb.append("}, {");
    sb.append(StringUtil.replace("name : \'{0}\',", "{0}", cancel));
    sb.append("callback : function() {");
    sb.append("}");
    sb.append("} ]");
    sb.append("});");
    sb.append("}else{");
    sb.append("$.dialog({");
    sb.append("content: \'url:\'+url,");
    // update-begin--author: scott  Date: 20170307 for： 弹出窗口遮挡问题--
    sb.append("zIndex: getzIndex(),");
    // update-end--author: scott  Date: 20170307 for： 弹出窗口遮挡问题--
    if (title != null) {
        sb.append("title: \'" + title + "\',");
    }
    sb.append("lock : true,");
    sb.append("parent:windowapi,");
    if (width != null) {
        if (!width.toLowerCase().endsWith("px")) {
            sb.append("width :\'" + width + "px\',");
        } else {
            sb.append("width :\'" + width + "\',");
        }
    } else {
        sb.append("width :400,");
    }
    if (height != null) {
        if (!height.toLowerCase().endsWith("px")) {
            sb.append("height :\'" + height + "px\',");
        } else {
            sb.append("height :\'" + height + "\',");
        }
    } else {
        sb.append("height :350,");
    }
    if (left != null) {
        sb.append("left :\'" + left + "\',");
    } else {
        sb.append("left :'85%',");
    }
    if (top != null) {
        sb.append("top :\'" + top + "\',");
    } else {
        sb.append("top :'65%',");
    }
    sb.append("opacity : 0.4,");
    sb.append("button : [ {");
    sb.append(StringUtil.replace("name : \'{0}\',", "{0}", confirm));
    sb.append("callback : clickcallback_" + methodname + ",");
    sb.append("focus : true");
    sb.append("}, {");
    sb.append(StringUtil.replace("name : \'{0}\',", "{0}", cancel));
    sb.append("callback : function() {");
    sb.append("}");
    sb.append("} ]");
    sb.append("});");
    sb.append("}");
    sb.append("}");
    clearAll(sb, methodname);
    callback(sb, methodname);
    sb.append("</script>");
    return sb;
}


public void setInputTextname(String inputTextname){
    this.inputTextname = inputTextname;
}


public void setFun(String fun){
    this.fun = fun;
}


}