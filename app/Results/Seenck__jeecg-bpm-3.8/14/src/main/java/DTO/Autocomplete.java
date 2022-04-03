package DTO;
 public class Autocomplete {

 private  String entityName;

 private  String labelField;

 private  String valueField;

 private  String searchField;

 private  String trem;

 private  Integer maxRows;

 private  Integer curPage;


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