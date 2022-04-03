package org.jeecgframework.tag.vo.easyui;
 public class Autocomplete {

 private  String entityName;

 private  String labelField;

 private  String valueField;

 private  String searchField;

 private  String trem;

 private  Integer maxRows;

 private  Integer curPage;


public void setValueField(String valueField){
    this.valueField = valueField;
}


public void setSearchField(String searchField){
    this.searchField = searchField;
}


public void setLabelField(String labelField){
    this.labelField = labelField;
}


public void setEntityName(String entityName){
    this.entityName = entityName;
}


public String getSearchField(){
    return searchField;
}


public Integer getCurPage(){
    if (curPage == null || curPage < 1) {
        curPage = 1;
    }
    return curPage;
}


public String getLabelField(){
    return labelField;
}


public void setMaxRows(Integer maxRows){
    this.maxRows = maxRows;
}


public void setCurPage(Integer curPage){
    this.curPage = curPage;
}


public void setTrem(String trem){
    this.trem = trem;
}


public String getTrem(){
    return trem;
}


public String getValueField(){
    return valueField;
}


public Integer getMaxRows(){
    return maxRows;
}


public String getEntityName(){
    return entityName;
}


}