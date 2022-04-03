package org.gliderwiki.install;
 import org.gliderwiki.web.vo.BaseObjectBean;
public class MySQLVariable extends BaseObjectBean{

 private  String variable_name;

 private  String value;

 private  int rowCount;


public String getValue(){
    return value;
}


public int getRowCount(){
    return rowCount;
}


public void setRowCount(int rowCount){
    this.rowCount = rowCount;
}


public void setVariable_name(String variable_name){
    this.variable_name = variable_name;
}


public void setValue(String value){
    this.value = value;
}


public String getVariable_name(){
    return variable_name;
}


}