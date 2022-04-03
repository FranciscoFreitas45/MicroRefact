package com.DTO;
 public class DictBean {

 public  int type_id;

 public  String type_name;

 public  String type_code;

 public  int status;

 public  int parent_id;

 public  String remark;


public String getType_name(){
    return type_name;
}


public String getType_code(){
    return type_code;
}


public String getRemark(){
    return remark;
}


public int getStatus(){
    return status;
}


public int getType_id(){
    return type_id;
}


public int getParent_id(){
    return parent_id;
}


}