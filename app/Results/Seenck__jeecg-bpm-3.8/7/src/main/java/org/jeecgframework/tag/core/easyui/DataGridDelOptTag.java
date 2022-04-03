package org.jeecgframework.tag.core.easyui;
 import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;
import org.jeecgframework.core.util.MutiLangUtil;
public class DataGridDelOptTag extends TagSupport{

 protected  String url;

 protected  String title;

 private  String message;

 private  String exp;

 private  String funname;

 private  String operationCode;

 private  String langArg;

 private  String urlStyle;

 private  String urlclass;

 private  String urlfont;

 private  boolean inGroup;


public boolean isInGroup(){
    return inGroup;
}


public String getUrlclass(){
    return urlclass;
}


public void setLangArg(String langArg){
    this.langArg = langArg;
}


public String getUrlStyle(){
    return urlStyle;
}


public void setInGroup(boolean inGroup){
    this.inGroup = inGroup;
}


public void setTitle(String title){
    this.title = title;
}


public void setFunname(String funname){
    this.funname = funname;
}


public void setMessage(String message){
    this.message = message;
}


public int doEndTag(){
    title = MutiLangUtil.doMutiLang(title, langArg);
    Tag t = findAncestorWithClass(this, DataGridTag.class);
    DataGridTag parent = (DataGridTag) t;
    // update-begin-author：jiaqiankun date:20180713 for:TASK #2872 【新功能】列表按钮改造成配置，支持折叠模式，增加一个参数
    parent.setDelUrl(url, title, message, exp, funname, operationCode, urlStyle, urlclass, urlfont, inGroup);
    // update-end-author：jiaqiankun date:20180713 for:TASK #2872 【新功能】列表按钮改造成配置，支持折叠模式，增加一个参数
    return EVAL_PAGE;
}


public void setUrl(String url){
    this.url = url;
}


public int doStartTag(){
    return EVAL_PAGE;
}


public void setUrlStyle(String urlStyle){
    this.urlStyle = urlStyle;
}


public void setOperationCode(String operationCode){
    this.operationCode = operationCode;
}


public void setUrlclass(String urlclass){
    this.urlclass = urlclass;
}


public String getUrlfont(){
    return urlfont;
}


public void setExp(String exp){
    this.exp = exp;
}


public void setUrlfont(String urlfont){
    this.urlfont = urlfont;
}


}