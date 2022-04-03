package org.jeecgframework.tag.core.easyui;
 import java.io.IOException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import org.jeecgframework.core.util.StringUtil;
public class CkeditorTag extends TagSupport{

 private  long serialVersionUID;

 protected  String name;

 protected  String value;

 protected  String type;


public void setName(String name){
    this.name = name;
}


public int doStartTag(){
    return EVAL_PAGE;
}


public String getValue(){
    return value;
}


public String getName(){
    return name;
}


public String getType(){
    return type;
}


public void setValue(String value){
    this.value = value;
}


public StringBuffer end(){
    StringBuffer sb = new StringBuffer();
    sb.append("<textarea id=\"" + name + "_text\" name=\"" + name + "\">" + value + "</textarea>");
    sb.append("<script type=\"text/javascript\">var ckeditor_" + name + "=CKEDITOR.replace(\"" + name + "_text\",{");
    if (StringUtil.isNotEmpty(type))
        sb.append(type);
    sb.append("});");
    sb.append("</script>");
    return sb;
}


public void setType(String type){
    this.type = type;
}


public int doEndTag(){
    JspWriter out = null;
    try {
        out = this.pageContext.getOut();
        out.print(end().toString());
        out.flush();
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
            out.clear();
            out.close();
        } catch (Exception e2) {
        }
    }
    return EVAL_PAGE;
}


}