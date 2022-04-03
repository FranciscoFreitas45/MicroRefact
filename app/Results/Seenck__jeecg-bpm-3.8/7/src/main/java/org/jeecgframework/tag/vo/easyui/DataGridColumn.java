package org.jeecgframework.tag.vo.easyui;
 public class DataGridColumn {

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

 protected  String treefield;

 protected  boolean image;

 protected  boolean query;

 protected  String queryMode;

 protected  boolean autoLoadData;

 private  boolean frozenColumn;

 protected  String url;

 protected  String funname;

 protected  String arg;

 protected  String dictionary;

 protected  String dictCondition;

 protected  boolean popup;

 protected  String replace;

 protected  String extend;

 protected  String style;

 protected  String imageSize;

 protected  String downloadName;

 protected  boolean autocomplete;

 protected  String extendParams;

 protected  String editor;

 private  String defaultVal;

 protected  String showMode;

 protected  boolean newColumn;

 private  String filterType;

 private  boolean optsMenu;

 private  boolean isAjaxDict;


public void setAjaxDict(boolean isAjaxDict){
    this.isAjaxDict = isAjaxDict;
}


public void setFilterType(String filterType){
    this.filterType = filterType;
}


public boolean isSortable(){
    return sortable;
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


public String getTitle(){
    return title;
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


public boolean isAutocomplete(){
    return autocomplete;
}


public void setShowMode(String showMode){
    this.showMode = showMode;
}


public String getDownloadName(){
    return downloadName;
}


public void setAutocomplete(boolean autocomplete){
    this.autocomplete = autocomplete;
}


public String getFunname(){
    return funname;
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


public String getArg(){
    return arg;
}


public String getField(){
    return field;
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


public String getUrl(){
    return url;
}


public boolean isImage(){
    return image;
}


public void setFormatter(String formatter){
    this.formatter = formatter;
}


public void setEditor(String editor){
    this.editor = editor;
}


public String getFormatterjs(){
    return formatterjs;
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


public Integer getWidth(){
    return width;
}


public void setExtendParams(String extendParams){
    this.extendParams = extendParams;
}


public void setColspan(String colspan){
    this.colspan = colspan;
}


public String getExtend(){
    return extend;
}


public String getImageSize(){
    return imageSize;
}


public void setImageSize(String imageSize){
    this.imageSize = imageSize;
}


public String getStyle(){
    return style;
}


public String getTreefield(){
    return treefield;
}


public void setDictCondition(String dictCondition){
    this.dictCondition = dictCondition;
}


public boolean getIsAjaxDict(){
    return isAjaxDict;
}


public void setAutoLoadData(boolean autoLoadData){
    this.autoLoadData = autoLoadData;
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


public boolean isCheckbox(){
    return checkbox;
}


public void setShowLen(Integer showLen){
    this.showLen = showLen;
}


public void setPopup(boolean popup){
    this.popup = popup;
}


public String getQueryMode(){
    return queryMode;
}


public boolean isPopup(){
    return popup;
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


public String getAlign(){
    return align;
}


public boolean isQuery(){
    return query;
}


public String getDictionary(){
    return dictionary;
}


public void setExtend(String extend){
    this.extend = extend;
}


public void setWidth(Integer width){
    this.width = width;
}


public String getRowspan(){
    return rowspan;
}


public boolean isHidden(){
    return hidden;
}


public String getDictCondition(){
    return dictCondition;
}


public String getColspan(){
    return colspan;
}


public boolean isAutoLoadData(){
    return autoLoadData;
}


public String getExtendParams(){
    return extendParams;
}


public void setAlign(String align){
    this.align = align;
}


public String getReplace(){
    return replace;
}


public void setDefaultVal(String defaultVal){
    this.defaultVal = defaultVal;
}


@Override
public String toString(){
    return "DataGridColumn [title=" + title + ", field=" + field + ", width=" + width + ", showLen=" + showLen + ", rowspan=" + rowspan + ", colspan=" + colspan + ", align=" + align + ", sortable=" + sortable + ", checkbox=" + checkbox + ", formatter=" + formatter + ", formatterjs=" + formatterjs + ", hidden=" + hidden + ", treefield=" + treefield + ", image=" + image + ", query=" + query + ", queryMode=" + queryMode + ", autoLoadData=" + autoLoadData + ", frozenColumn=" + frozenColumn + ", url=" + url + ", funname=" + funname + ", arg=" + arg + ", dictionary=" + dictionary + ", popup=" + popup + ", replace=" + replace + ", extend=" + extend + ", style=" + style + ", imageSize=" + imageSize + ", downloadName=" + downloadName + ", autocomplete=" + autocomplete + ", extendParams=" + extendParams + ", editor=" + editor + ", defaultVal=" + defaultVal + ", showMode=" + showMode + ", newColumn=" + newColumn + "]";
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


public String getFormatter(){
    return formatter;
}


}