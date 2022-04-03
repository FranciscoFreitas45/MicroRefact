package org.sdrc.devinfo.domain;
 import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Entity
@Table(name = "ut_subgroup_type_en")
@NamedQuery(name = "UtSubgroupTypeEn.findAll", query = "SELECT u FROM UtSubgroupTypeEn u")
public class UtSubgroupTypeEn implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  int subgroup_Type_NId;

 private  String subgroup_Type_GID;

 private  byte subgroup_Type_Global;

 private  String subgroup_Type_Name;

 private  double subgroup_Type_Order;

public UtSubgroupTypeEn() {
}
public void setSubgroup_Type_GID(String subgroup_Type_GID){
    this.subgroup_Type_GID = subgroup_Type_GID;
}


public String getSubgroup_Type_GID(){
    return this.subgroup_Type_GID;
}


public void setSubgroup_Type_Global(byte subgroup_Type_Global){
    this.subgroup_Type_Global = subgroup_Type_Global;
}


public byte getSubgroup_Type_Global(){
    return this.subgroup_Type_Global;
}


public void setSubgroup_Type_Order(double subgroup_Type_Order){
    this.subgroup_Type_Order = subgroup_Type_Order;
}


public void setSubgroup_Type_Name(String subgroup_Type_Name){
    this.subgroup_Type_Name = subgroup_Type_Name;
}


public double getSubgroup_Type_Order(){
    return this.subgroup_Type_Order;
}


public String getSubgroup_Type_Name(){
    return this.subgroup_Type_Name;
}


public void setSubgroup_Type_NId(int subgroup_Type_NId){
    this.subgroup_Type_NId = subgroup_Type_NId;
}


public int getSubgroup_Type_NId(){
    return this.subgroup_Type_NId;
}


}