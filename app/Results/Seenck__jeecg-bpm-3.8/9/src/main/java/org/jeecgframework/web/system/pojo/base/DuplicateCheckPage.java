package org.jeecgframework.web.system.pojo.base;
 @SuppressWarnings("serial")
public class DuplicateCheckPage {

 private  String tableName;

 private  String fieldName;

 private  String fieldVlaue;

 private  String rowObid;


public void setTableName(String tableName){
    this.tableName = tableName;
}


public void setFieldName(String fieldName){
    this.fieldName = fieldName;
}


public void setRowObid(String rowObid){
    this.rowObid = rowObid;
}


public String getTableName(){
    return tableName;
}


public String getFieldVlaue(){
    return fieldVlaue;
}


public void setFieldVlaue(String fieldVlaue){
    this.fieldVlaue = fieldVlaue;
}


public String getFieldName(){
    return fieldName;
}


public String getRowObid(){
    return rowObid;
}


}