package org.jeecgframework.tag.core.easyui;
 import org.jeecgframework.core.util.MutiLangUtil;
import org.jeecgframework.core.util.StringUtil;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;
public class DataGridColumnTag extends TagSupport{

 private  long serialVersionUID;

 protected  String title;

 protected  String field;

 protected  Integer width;

 protected  Integer showLen;

 protected  String rowspan;

 protected  String colspan;

 protected  String align;

 protected  boolean sortable;

 protected  boolean checkbox;

 protected  String formatter;

 protected  String formatterjs;

 protected  boolean hidden;

 protected  String replace;

 protected  String treefield;

 protected  boolean image;

 protected  boolean query;

 private  String queryMode;

 private  boolean frozenColumn;

 protected  boolean bSearchable;

 protected  String url;

 protected  String funname;

 protected  String arg;

 protected  String dictionary;

 protected  boolean popup;

 protected  String extend;

 protected  String style;

 protected  String imageSize;

 protected  String downloadName;

 private  boolean autocomplete;

 private  String extendParams;

 private  String langArg;

 protected  String editor;

 private  String defaultVal;

 protected  String showMode;

 protected  boolean newColumn;

 protected  String dictCondition;

 private  String filterType;

 private  boolean optsMenu;

 private  boolean isAjaxDict;


public void setFilterType(String filterType){
    this.filterType = filterType;
}


public void setReplace(String replace){
    this.replace = replace;
}


public String getShowMode(){
    return showMode;
}


public void setDictionary(String dictionary){
    this.dictionary = dictionary;
}


public void setRowspan(String rowspan){
    this.rowspan = rowspan;
}


public void setdictCondition(String dictCondition){
    this.dictCondition = dictCondition;
}


public void setArg(String arg){
    this.arg = arg;
}


public void setHidden(boolean hidden){
    this.hidden = hidden;
}


public boolean isOptsMenu(){
    return optsMenu;
}


public void setQuery(boolean query){
    this.query = query;
}


public void setFormatterjs(String formatterjs){
    this.formatterjs = formatterjs;
}


public void setShowMode(String showMode){
    this.showMode = showMode;
}


public void setAutocomplete(boolean autocomplete){
    this.autocomplete = autocomplete;
}


public String getDefaultVal(){
    return defaultVal;
}


public Integer getShowLen(){
    return showLen;
}


public boolean isNewColumn(){
    return newColumn;
}


public void setTitle(String title){
    this.title = title;
}


public void setFunname(String funname){
    this.funname = funname;
}


public void setUrl(String url){
    this.url = url;
}


public void setFrozenColumn(boolean frozenColumn){
    this.frozenColumn = frozenColumn;
}


public int doStartTag(){
    return EVAL_PAGE;
}


public void setFormatter(String formatter){
    this.formatter = formatter;
}


public void setEditor(String editor){
    this.editor = editor;
}


public void setbSearchable(boolean bSearchable){
    this.bSearchable = bSearchable;
}


public void setImage(boolean image){
    this.image = image;
}


public String getEditor(){
    return editor;
}


public void setField(String field){
    this.field = field;
}


public void setQueryMode(String queryMode){
    this.queryMode = queryMode;
}


public void setIsAjaxDict(boolean isAjaxDict){
    this.isAjaxDict = isAjaxDict;
}


public void setExtendParams(String extendParams){
    // update--begin--author:zhangjiaqiang date:20170705 for:TASK #2195 【ui标签参数美化改进】这个参数用途，为什么多个逗号
    if (StringUtil.isNotEmpty(extendParams) && !extendParams.endsWith(",")) {
        extendParams = extendParams + ",";
    }
    // update--end--author:zhangjiaqiang date:20170705 for:TASK #2195 【ui标签参数美化改进】这个参数用途，为什么多个逗号
    this.extendParams = extendParams;
}


public void setColspan(String colspan){
    this.colspan = colspan;
}


public String getExtend(){
    return extend;
}


public void setImageSize(String imageSize){
    this.imageSize = imageSize;
}


public boolean getIsAjaxDict(){
    return isAjaxDict;
}


public void setStyle(String style){
    this.style = style;
}


public void setTreefield(String treefield){
    this.treefield = treefield;
}


public void setSortable(boolean sortable){
    this.sortable = sortable;
}


public void setPopup(boolean popup){
    this.popup = popup;
}


public void setShowLen(Integer showLen){
    this.showLen = showLen;
}


public String getQueryMode(){
    return queryMode;
}


public boolean isPopup(){
    return popup;
}


public void setLangArg(String langArg){
    this.langArg = langArg;
}


public void setDownloadName(String downloadName){
    this.downloadName = downloadName;
}


public String getFilterType(){
    return filterType;
}


public void setCheckbox(boolean checkbox){
    this.checkbox = checkbox;
}


public void setExtend(String extend){
    this.extend = extend;
}


public void setWidth(Integer width){
    this.width = width;
}


public int doEndTag(){
    title = MutiLangUtil.doMutiLang(title, langArg);
    Tag t = findAncestorWithClass(this, DataGridTag.class);
    DataGridTag parent = (DataGridTag) t;
    // update-begin--Author:xuelin  Date:20170706 for：TASK #2205 【UI标签库】列表查询条件动态生成，下拉换成redio模式切换--------------------
    // update--begin--author:zhangjiaqiang Date:20170815 for:TASK #2273 【demo】datagrid 多表头demo
    // -- update-begin--Author:gj_shaojc  Date:20180314 for：TASK #2560 【UI标签扩展】column列表字典属性扩展dictCondition加sql条件  --
    parent.setColumn(title, field, width, showLen, rowspan, colspan, align, sortable, checkbox, formatter, formatterjs, hidden, replace, treefield, image, imageSize, query, url, funname, arg, queryMode, dictionary, popup, frozenColumn, extend, style, downloadName, autocomplete, extendParams, editor, defaultVal, showMode, newColumn, dictCondition, filterType, optsMenu, isAjaxDict);
    // -- update-end--Author:gj_shaojc  Date:20180314 for：TASK #2560 【UI标签扩展】column列表字典属性扩展dictCondition加sql条件  --
    // update--end--author:zhangjiaqiang Date:20170815 for:TASK #2273 【demo】datagrid 多表头demo
    // update-end--Author:xuelin  Date:20170706 for：TASK #2205 【UI标签库】列表查询条件动态生成，下拉换成redio模式切换----------------------
    return EVAL_PAGE;
}


public void setAlign(String align){
    this.align = align;
}


public void setDefaultVal(String defaultVal){
    this.defaultVal = defaultVal;
}


public boolean isFrozenColumn(){
    return frozenColumn;
}


public void setOptsMenu(boolean optsMenu){
    this.optsMenu = optsMenu;
}


public void setNewColumn(boolean newColumn){
    this.newColumn = newColumn;
}


}