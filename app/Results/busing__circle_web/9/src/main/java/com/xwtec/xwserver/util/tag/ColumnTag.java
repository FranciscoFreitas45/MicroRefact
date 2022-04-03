package com.xwtec.xwserver.util.tag;
 import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
public class ColumnTag extends TagSupport{

 private  long serialVersionUID;

 private  String name;

 private  String index;

 private  String label;

 private  Boolean key;

 private  String width;

 private  Boolean editable;

 private  Boolean title;

 private  Boolean sortable;

 private  Boolean hidden;

 private  String edittype;

 private  String align;

 private  String editoptions;

 private  String editrules;

 private  String formatter;

 private  String unformat;

 private  String formatoptions;


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public String getLabel(){
    return label;
}


public Boolean getSortable(){
    return sortable;
}


public String getIndex(){
    return index;
}


public Boolean getEditable(){
    return editable;
}


public String getWidth(){
    return width;
}


public void setFormatoptions(String formatoptions){
    this.formatoptions = formatoptions;
}


public Boolean getTitle(){
    return title;
}


public void setEditoptions(String editoptions){
    this.editoptions = editoptions;
}


public void setHidden(Boolean hidden){
    this.hidden = hidden;
}


public String getUnformat(){
    return unformat;
}


public void setEdittype(String edittype){
    this.edittype = edittype;
}


public void setUnformat(String unformat){
    this.unformat = unformat;
}


public void setSortable(Boolean sortable){
    this.sortable = sortable;
}


public String getEdittype(){
    return edittype;
}


public void setKey(Boolean key){
    this.key = key;
}


public Boolean getKey(){
    return key;
}


public Boolean getHidden(){
    return hidden;
}


public String getFormatoptions(){
    return formatoptions;
}


public String getAlign(){
    return align;
}


public void setTitle(Boolean title){
    this.title = title;
}


public void setWidth(String width){
    this.width = width;
}


public int doEndTag(){
    return EVAL_PAGE;
}


public int doStartTag(){
    JqGridTag jqgrid = (JqGridTag) this.getParent();
    ColumnTag column = new ColumnTag();
    column.setAlign(this.getAlign());
    column.setEditable(this.getEditable());
    column.setEditoptions(this.getEditoptions());
    column.setEditrules(this.getEditrules());
    column.setEdittype(this.getEdittype());
    column.setHidden(this.getHidden());
    column.setIndex(this.getIndex());
    column.setKey(this.getKey());
    column.setLabel(this.getLabel());
    column.setName(this.getName());
    column.setFormatter(this.getFormatter());
    column.setSortable(this.getSortable());
    column.setTitle(this.getTitle());
    column.setWidth(this.getWidth());
    column.setFormatoptions(this.getFormatoptions());
    column.setUnformat(this.getUnformat());
    jqgrid.addValue(column);
    return SKIP_BODY;
}


public void setEditable(Boolean editable){
    this.editable = editable;
}


public void setFormatter(String formatter){
    this.formatter = formatter;
}


public void setIndex(String index){
    this.index = index;
}


public void setAlign(String align){
    this.align = align;
}


public String getEditrules(){
    return editrules;
}


public void setLabel(String label){
    this.label = label;
}


public String getFormatter(){
    return formatter;
}


public void setEditrules(String editrules){
    this.editrules = editrules;
}


public String getEditoptions(){
    return editoptions;
}


}