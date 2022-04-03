package org.sdrc.devinfo.domain;
 import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Entity
@Table(name = "ut_indicator_unit_subgroup")
@NamedQuery(name = "UtIndicatorUnitSubgroup.findAll", query = "SELECT u FROM UtIndicatorUnitSubgroup u")
public class UtIndicatorUnitSubgroup implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  int IUSNId;

 private  BigDecimal avlMaxDataValue;

 private  String avlMaxTimePeriod;

 private  BigDecimal avlMinDataValue;

 private  String avlMinTimePeriod;

 private  byte data_Exist;

 private  int indicator_NId;

 private  byte isDefaultSubgroup;

 private  double max_Value;

 private  double min_Value;

 private  String subgroup_NIds;

 private  int subgroup_Val_NId;

 private  int unit_NId;

public UtIndicatorUnitSubgroup() {
}
public int getIUSNId(){
    return this.IUSNId;
}


public void setAvlMinDataValue(BigDecimal avlMinDataValue){
    this.avlMinDataValue = avlMinDataValue;
}


public int getIndicator_NId(){
    return this.indicator_NId;
}


public void setAvlMinTimePeriod(String avlMinTimePeriod){
    this.avlMinTimePeriod = avlMinTimePeriod;
}


public String getSubgroup_NIds(){
    return this.subgroup_NIds;
}


public void setMin_Value(double min_Value){
    this.min_Value = min_Value;
}


public double getMax_Value(){
    return this.max_Value;
}


public int getSubgroup_Val_NId(){
    return this.subgroup_Val_NId;
}


public void setSubgroup_Val_NId(int subgroup_Val_NId){
    this.subgroup_Val_NId = subgroup_Val_NId;
}


public void setMax_Value(double max_Value){
    this.max_Value = max_Value;
}


public void setIndicator_NId(int indicator_NId){
    this.indicator_NId = indicator_NId;
}


public BigDecimal getAvlMinDataValue(){
    return this.avlMinDataValue;
}


public void setSubgroup_NIds(String subgroup_NIds){
    this.subgroup_NIds = subgroup_NIds;
}


public BigDecimal getAvlMaxDataValue(){
    return this.avlMaxDataValue;
}


public void setIUSNId(int IUSNId){
    this.IUSNId = IUSNId;
}


public int getUnit_NId(){
    return this.unit_NId;
}


public void setData_Exist(byte data_Exist){
    this.data_Exist = data_Exist;
}


public byte getIsDefaultSubgroup(){
    return this.isDefaultSubgroup;
}


public void setIsDefaultSubgroup(byte isDefaultSubgroup){
    this.isDefaultSubgroup = isDefaultSubgroup;
}


public double getMin_Value(){
    return this.min_Value;
}


public void setUnit_NId(int unit_NId){
    this.unit_NId = unit_NId;
}


public String getAvlMaxTimePeriod(){
    return this.avlMaxTimePeriod;
}


public void setAvlMaxDataValue(BigDecimal avlMaxDataValue){
    this.avlMaxDataValue = avlMaxDataValue;
}


public String getAvlMinTimePeriod(){
    return this.avlMinTimePeriod;
}


public byte getData_Exist(){
    return this.data_Exist;
}


public void setAvlMaxTimePeriod(String avlMaxTimePeriod){
    this.avlMaxTimePeriod = avlMaxTimePeriod;
}


}