package org.opengeoportal.config.search;
 public class SearchRepository {

 private String id;

 private Boolean selected;


public Boolean getSelected(){
    return selected;
}


public void setSelected(Boolean selected){
    this.selected = selected;
}


public void setId(String id){
    this.id = id;
}


public String getId(){
    return id;
}


}