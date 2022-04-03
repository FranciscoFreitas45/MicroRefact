package org.jeecgframework.tag.core.easyui;
 import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;
import org.jeecgframework.core.util.MutiLangUtil;
public class DataGridFunOptTag extends TagSupport{

 protected  String title;

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


public int doEndTag(){
    title = MutiLangUtil.doMutiLang(title, langArg);
    Tag t = findAncestorWithClass(this, DataGridTag.class);
    DataGridTag parent = (DataGridTag) t;
    parent.setFunUrl(title, exp, funname, operationCode, urlStyle, urlclass, urlfont, inGroup);
    return EVAL_PAGE;
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