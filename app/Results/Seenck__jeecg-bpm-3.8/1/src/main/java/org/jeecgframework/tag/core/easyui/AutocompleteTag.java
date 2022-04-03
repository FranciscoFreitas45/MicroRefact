package org.jeecgframework.tag.core.easyui;
 import java.io.IOException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
public class AutocompleteTag extends TagSupport{

 private  long serialVersionUID;

 private  String name;

 private  String entityName;

 private  String searchField;

 private  String defValue;

 private  String dataSource;

 private  Integer minLength;

 private  String datatype;

 private  String nullmsg;

 private  String errormsg;

 private  String parse;

 private  String formatItem;

 private  String result;

 private  Integer maxRows;


public void setName(String name){
    this.name = name;
}


public void setFormatItem(String formatItem){
    this.formatItem = formatItem;
}


public void setDatatype(String datatype){
    this.datatype = datatype;
}


public void setSearchField(String searchField){
    this.searchField = searchField;
}


public void setResult(String result){
    this.result = result;
}


public void setEntityName(String entityName){
    this.entityName = entityName;
}


public int doEndTag(){
    JspWriter out = null;
    try {
        out = this.pageContext.getOut();
        out.print(end().toString());
        out.flush();
    } catch (IOException e) {
        e.printStackTrace();
    }
    return EVAL_PAGE;
}


public void setNullmsg(String nullmsg){
    this.nullmsg = nullmsg;
}


public int doStartTag(){
    return EVAL_PAGE;
}


public void setErrormsg(String errormsg){
    this.errormsg = errormsg;
}


public void setParse(String parse){
    this.parse = parse;
}


public void setMinLength(Integer minLength){
    this.minLength = minLength;
}


public void setMaxRows(Integer maxRows){
    if (maxRows == null) {
        maxRows = 10;
    }
    this.maxRows = maxRows;
}


public StringBuffer end(){
    StringBuffer nsb = new StringBuffer();
    nsb.append("<script type=\"text/javascript\">");
    nsb.append("$(document).ready(function() {").append("$(\"#" + name + "\").autocomplete(\"" + dataSource + "\",{").append("max: 5,minChars: " + minLength + ",width: 200,scrollHeight: 100,matchContains: true,autoFill: false,extraParams:{").append("featureClass : \"P\",style : \"full\",	maxRows : " + maxRows + ",labelField : \"" + searchField + "\",valueField : \"" + searchField + "\",").append("searchField : \"" + searchField + "\",entityName : \"" + entityName + "\",trem: getTremValue" + name + "}");
    if (StringUtil.isNotEmpty(parse)) {
        nsb.append(",parse:function(data){return " + parse + ".call(this,data);}");
    } else {
        nsb.append(",parse:function(data){return jeecgAutoParse.call(this,data);}");
    }
    if (StringUtil.isNotEmpty(formatItem)) {
        nsb.append(",formatItem:function(row, i, max){return " + formatItem + ".call(this,row, i, max);} ");
    } else {
        nsb.append(",formatItem:function(row, i, max){return row['" + searchField + "'];}");
    }
    nsb.append("}).result(function (event, row, formatted) {");
    if (StringUtil.isNotEmpty(result)) {
        nsb.append(result + ".call(this,row); ");
    } else {
        nsb.append("$(\"#" + name + "\").val(row['" + searchField + "']);");
    }
    nsb.append("}); });").append("function getTremValue" + name + "(){return $(\"#" + name + "\").val();}").append("</script>").append("<input type=\"text\" id=\"" + name + "\" name=\"" + name + "\" ");
    if (oConvertUtils.isNotEmpty(datatype)) {
        nsb.append("datatype=\"" + datatype + "\" nullmsg=\"" + nullmsg + "\" errormsg=\"" + errormsg + "\" ");
    }
    nsb.append("/>");
    if (StringUtil.isNotEmpty(defValue)) {
        nsb.append(" value=" + defValue + " readonly=true");
    }
    return nsb;
}


public void setDataSource(String dataSource){
    this.dataSource = dataSource;
}


public void setDefValue(String defValue){
    this.defValue = defValue;
}


public String getDefValue(){
    return defValue;
}


}