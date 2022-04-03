package org.jeecgframework.core.common.model.common;
 import java.io.Serializable;
import java.util.List;
public class DBTable implements Serializable{

 public  String tableName;

 public  String entityName;

 public  String tableTitle;

 public  Class<T> tableEntityClass;

 public  List<T> tableData;


public void setTableName(String tableName){
    this.tableName = tableName;
}


public void setClass1(Class<T> class1){
    this.tableEntityClass = class1;
}


public String getTableTitle(){
    return tableTitle;
}


public void setTableTitle(String tableTitle){
    this.tableTitle = tableTitle;
}


public void setTableData(List<T> tableData){
    this.tableData = tableData;
}


public String getTableName(){
    return tableName;
}


public Class<T> getClass1(){
    return tableEntityClass;
}


public String getEntityName(){
    return entityName;
}


public void setEntityName(String entityName){
    this.entityName = entityName;
}


public List<T> getTableData(){
    return tableData;
}


}