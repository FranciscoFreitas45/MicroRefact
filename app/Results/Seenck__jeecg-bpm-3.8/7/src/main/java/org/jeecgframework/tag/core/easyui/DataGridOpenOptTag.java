package org.jeecgframework.tag.core.easyui;
 import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;
public class DataGridOpenOptTag extends TagSupport{

 protected  String url;

 protected  String width;

 protected  String height;

 protected  String title;

 private  String exp;

 private  String operationCode;

 private  String urlStyle;

 private  String urlclass;

 private  String urlfont;

 private  String openModel;

 private  boolean inGroup;


public boolean isInGroup(){
    return inGroup;
}


public String getUrlclass(){
    return urlclass;
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


public void setWidth(String width){
    this.width = width;
}


public void setHeight(String height){
    this.height = height;
}


public int doEndTag(){
    Tag t = findAncestorWithClass(this, DataGridTag.class);
    DataGridTag parent = (DataGridTag) t;
    parent.setOpenUrl(url, title, width, height, exp, operationCode, openModel, urlStyle, urlclass, urlfont, inGroup);
    return EVAL_PAGE;
}


public void setUrl(String url){
    this.url = url;
}


public int doStartTag(){
    return EVAL_PAGE;
}


public void setOpenModel(String openModel){
    this.openModel = openModel;
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