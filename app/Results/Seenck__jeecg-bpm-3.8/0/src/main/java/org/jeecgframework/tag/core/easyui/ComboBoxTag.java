package org.jeecgframework.tag.core.easyui;
 import java.io.IOException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import org.jeecgframework.core.common.model.json.ComboBox;
public class ComboBoxTag extends TagSupport{

 protected  String id;

 protected  String text;

 protected  String url;

 protected  String name;

 protected  Integer width;

 protected  Integer listWidth;

 protected  Integer listHeight;

 protected  boolean editable;


public int doStartTag(){
    return EVAL_PAGE;
}


public void setName(String name){
    this.name = name;
}


public void setEditable(boolean editable){
    this.editable = editable;
}


public boolean isEditable(){
    return editable;
}


public void setId(String id){
    this.id = id;
}


public StringBuffer end(){
    StringBuffer sb = new StringBuffer();
    ComboBox comboBox = new ComboBox();
    comboBox.setText(text);
    comboBox.setId(id);
    sb.append("<script type=\"text/javascript\">" + "$(function() {" + "$(\'#" + name + "\').combobox({" + "url:\'" + url + "&id=" + id + "&text=" + text + "\'," + "editable:\'" + editable + "\'," + "valueField:\'id\'," + "textField:\'text\'," + "width:\'" + width + "\'," + "listWidth:\'" + listWidth + "\'," + "listHeight:\'" + listWidth + "\'," + "onChange:function(){" + "var val = $(\'#" + name + "\').combobox(\'getValues\');" + "$(\'#" + name + "hidden\').val(val);" + "}" + "});" + "});" + "</script>");
    sb.append("<input type=\"hidden\" name=\"" + name + "\" id=\"" + name + "hidden\" > " + "<input class=\"easyui-combobox\" " + "multiple=\"true\" panelHeight=\"auto\" name=\"" + name + "name\" id=\"" + name + "\" >");
    return sb;
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


public void setText(String text){
    this.text = text;
}


public void setUrl(String url){
    this.url = url;
}


}