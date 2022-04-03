package org.jeecgframework.web.cgform.util;
 import java.util.Map;
import com.alibaba.fastjson.JSONObject;
public class TableJson {

 private  boolean success;

 private  String msg;

 private  Integer tableType;

 private  Object tableData;

 private  Map<String,Object> subTableDate;


public void setSubTableDate(Map<String,Object> subTableDate){
    this.subTableDate = subTableDate;
}


public void setSuccess(boolean success){
    this.success = success;
}


public String getMsg(){
    return msg;
}


public Integer getTableType(){
    return tableType;
}


public void setTableData(Object tableData){
    this.tableData = tableData;
}


public Map<String,Object> getSubTableDate(){
    return subTableDate;
}


@Override
public String toString(){
    return "TableJson [success=" + success + ", msg=" + msg + ", tableType=" + tableType + ", tableData=" + tableData + ", subTableDate=" + subTableDate + "]";
}


public void setTableType(Integer tableType){
    this.tableType = tableType;
}


public void setMsg(String msg){
    this.msg = msg;
}


public Object getTableData(){
    return tableData;
}


public boolean isSuccess(){
    return success;
}


}