package com.byr.warehouse.pojo;
 import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class FreightSpace {

@Id
// 设置主键并且设置主键为自增
@GeneratedValue(strategy = GenerationType.AUTO)
 private  int id;

 private  String freightCode;

 private  String remark;


public void setRemark(String remark){
    this.remark = remark;
}


public String getRemark(){
    return remark;
}


public void setId(int id){
    this.id = id;
}


public void setFreightCode(String freightCode){
    this.freightCode = freightCode;
}


public int getId(){
    return id;
}


public String getFreightCode(){
    return freightCode;
}


}