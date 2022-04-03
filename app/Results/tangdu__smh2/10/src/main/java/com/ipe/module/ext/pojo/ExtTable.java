package com.ipe.module.ext.pojo;
 import java.util.List;
public class ExtTable {

 private  String entityName;

 private  String packageName;

 private  List<ExtColumn> columns;


public void setColumns(List<ExtColumn> columns){
    this.columns = columns;
}


public void setPackageName(String packageName){
    this.packageName = packageName;
}


public String getPackageName(){
    return packageName;
}


public String getEntityName(){
    return entityName;
}


public void setEntityName(String entityName){
    this.entityName = entityName;
}


public List<ExtColumn> getColumns(){
    return columns;
}


}