package org.jeecgframework.tag.core.easyui;
 import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;
import org.jeecgframework.core.util.MutiLangUtil;
public class DataGridToolBarTag extends TagSupport{

 protected  String url;

 protected  String title;

 private  String exp;

 private  String funname;

 private  String icon;

 private  String onclick;

 private  String width;

 private  String height;

 private  String operationCode;

 private  String langArg;

 private  String id;

 private  boolean inGroup;


public boolean isInGroup(){
    return inGroup;
}


public void setLangArg(String langArg){
    this.langArg = langArg;
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


public void setOnclick(String onclick){
    this.onclick = onclick;
}


public String getId(){
    return id;
}


public String getWidth(){
    return width;
}


public void setWidth(String width){
    this.width = width;
}


public void setHeight(String height){
    this.height = height;
}


public int doEndTag(){
    title = MutiLangUtil.doMutiLang(title, langArg);
    Tag t = findAncestorWithClass(this, DataGridTag.class);
    DataGridTag parent = (DataGridTag) t;
    parent.setToolbar(url, title, icon, exp, onclick, funname, operationCode, width, height, id, inGroup);
    return EVAL_PAGE;
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


public String getHeight(){
    return height;
}


public void setOperationCode(String operationCode){
    this.operationCode = operationCode;
}


public void setExp(String exp){
    this.exp = exp;
}


public void setId(String id){
    this.id = id;
}


}