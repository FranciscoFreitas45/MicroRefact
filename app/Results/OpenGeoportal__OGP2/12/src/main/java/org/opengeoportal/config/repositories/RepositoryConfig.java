package org.opengeoportal.config.repositories;
 import com.fasterxml.jackson.annotation.JsonInclude;
public class RepositoryConfig {

 private String id;

 private String shortName;

 private String fullName;

 private String iconClass;

 private String nodeType;

 private String url;

 private Boolean selected;


public void setSelected(Boolean selected){
    this.selected = selected;
}


public String getId(){
    return id;
}


public void setIconClass(String iconClass){
    this.iconClass = iconClass;
}


@JsonInclude(JsonInclude.Include.NON_EMPTY)
public void setUrl(String url){
    this.url = url;
}


public Boolean getSelected(){
    return selected;
}


@JsonInclude(JsonInclude.Include.NON_EMPTY)
public String getUrl(){
    return url;
}


public String getIconClass(){
    return iconClass;
}


public String getShortName(){
    return shortName;
}


public void setShortName(String shortName){
    this.shortName = shortName;
}


public void setId(String id){
    this.id = id;
}


public void setFullName(String fullName){
    this.fullName = fullName;
}


@JsonInclude(JsonInclude.Include.NON_EMPTY)
public String getNodeType(){
    return nodeType;
}


public String getFullName(){
    return fullName;
}


@JsonInclude(JsonInclude.Include.NON_EMPTY)
public void setNodeType(String nodeType){
    this.nodeType = nodeType;
}


}