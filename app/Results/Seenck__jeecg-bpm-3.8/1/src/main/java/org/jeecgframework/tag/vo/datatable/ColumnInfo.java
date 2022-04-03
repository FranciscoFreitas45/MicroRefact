package org.jeecgframework.tag.vo.datatable;
 public class ColumnInfo {

 private  String name;

 private  Boolean regex;

 private  Boolean searchable;

 private  String search;

 private  Boolean sortable;


public void setName(String name){
    this.name = name;
}


public Boolean getRegex(){
    return regex;
}


public String getName(){
    return name;
}


public Boolean getSortable(){
    return sortable;
}


public void setRegex(Boolean regex){
    this.regex = regex;
}


public Boolean getSearchable(){
    return searchable;
}


public void setSearchable(Boolean searchable){
    this.searchable = searchable;
}


public void setSearch(String search){
    this.search = search;
}


public void setSortable(Boolean sortable){
    this.sortable = sortable;
}


public String getSearch(){
    return search;
}


}