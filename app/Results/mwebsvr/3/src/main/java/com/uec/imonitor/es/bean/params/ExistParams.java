package com.uec.imonitor.es.bean.params;
 import java.util.List;
public class ExistParams {

 private  List<String> fieldList;

public ExistParams(List<String> fieldList) {
    this.fieldList = fieldList;
}public ExistParams() {
}
public void setFieldList(List<String> fieldList){
    this.fieldList = fieldList;
}


public List<String> getFieldList(){
    return fieldList;
}


}