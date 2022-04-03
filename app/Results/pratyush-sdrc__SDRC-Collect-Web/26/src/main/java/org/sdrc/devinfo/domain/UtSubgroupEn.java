package org.sdrc.devinfo.domain;
 import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Entity
@Table(name = "ut_subgroup_en")
@NamedQuery(name = "UtSubgroupEn.findAll", query = "SELECT u FROM UtSubgroupEn u")
public class UtSubgroupEn implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  int subgroup_NId;

 private  String subgroup_GId;

 private  byte subgroup_Global;

 private  String subgroup_Name;

 private  Integer subgroup_Order;

 private  int subgroup_Type;

public UtSubgroupEn() {
}
public void setSubgroup_NId(int subgroup_NId){
    this.subgroup_NId = subgroup_NId;
}


public void setSubgroup_Global(byte subgroup_Global){
    this.subgroup_Global = subgroup_Global;
}


public byte getSubgroup_Global(){
    return this.subgroup_Global;
}


public void setSubgroup_Order(Integer subgroup_Order){
    this.subgroup_Order = subgroup_Order;
}


public void setSubgroup_Name(String subgroup_Name){
    this.subgroup_Name = subgroup_Name;
}


public String getSubgroup_GId(){
    return this.subgroup_GId;
}


public void setSubgroup_Type(int subgroup_Type){
    this.subgroup_Type = subgroup_Type;
}


public Integer getSubgroup_Order(){
    return this.subgroup_Order;
}


public int getSubgroup_NId(){
    return this.subgroup_NId;
}


public String getSubgroup_Name(){
    return this.subgroup_Name;
}


public void setSubgroup_GId(String subgroup_GId){
    this.subgroup_GId = subgroup_GId;
}


public int getSubgroup_Type(){
    return this.subgroup_Type;
}


}