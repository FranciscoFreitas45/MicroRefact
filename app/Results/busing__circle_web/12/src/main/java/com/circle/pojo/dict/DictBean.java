package com.circle.pojo.dict;
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


public void setRemark(String remark){
    this.remark = remark;
}


public String getType_code(){
    return type_code;
}


public String getRemark(){
    return remark;
}


public void setParent_id(int parent_id){
    this.parent_id = parent_id;
}


public void setType_id(int type_id){
    this.type_id = type_id;
}


public int getStatus(){
    return status;
}


public void setType_name(String type_name){
    this.type_name = type_name;
}


public int getType_id(){
    return type_id;
}


public void setType_code(String type_code){
    this.type_code = type_code;
}


public int getParent_id(){
    return parent_id;
}


public void setStatus(int status){
    this.status = status;
}


}