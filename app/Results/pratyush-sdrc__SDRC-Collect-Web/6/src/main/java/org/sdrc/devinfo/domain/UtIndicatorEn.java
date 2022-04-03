package org.sdrc.devinfo.domain;
 import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Entity
@Table(name = "ut_indicator_en")
@NamedQuery(name = "UtIndicatorEn.findAll", query = "SELECT u FROM UtIndicatorEn u")
public class UtIndicatorEn implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  int indicator_NId;

 private  byte data_Exist;

 private  byte highIsGood;

 private  String indicator_GId;

 private  byte indicator_Global;

@Lob
 private  String indicator_Info;

 private  String indicator_Name;

 private  Integer indicator_Order;

 private  String keywords;

 private  String short_Name;

public UtIndicatorEn() {
}
public void setIndicator_Name(String indicator_Name){
    this.indicator_Name = indicator_Name;
}


public void setIndicator_NId(int indicator_NId){
    this.indicator_NId = indicator_NId;
}


public String getShort_Name(){
    return this.short_Name;
}


public int getIndicator_NId(){
    return this.indicator_NId;
}


public void setIndicator_GId(String indicator_GId){
    this.indicator_GId = indicator_GId;
}


public String getKeywords(){
    return this.keywords;
}


public void setData_Exist(byte data_Exist){
    this.data_Exist = data_Exist;
}


public void setKeywords(String keywords){
    this.keywords = keywords;
}


public byte getHighIsGood(){
    return this.highIsGood;
}


public byte getIndicator_Global(){
    return this.indicator_Global;
}


public String getIndicator_Info(){
    return this.indicator_Info;
}


public void setIndicator_Global(byte indicator_Global){
    this.indicator_Global = indicator_Global;
}


public String getIndicator_Name(){
    return this.indicator_Name;
}


public void setIndicator_Order(Integer indicator_Order){
    this.indicator_Order = indicator_Order;
}


public void setShort_Name(String short_Name){
    this.short_Name = short_Name;
}


public void setIndicator_Info(String indicator_Info){
    this.indicator_Info = indicator_Info;
}


public Integer getIndicator_Order(){
    return this.indicator_Order;
}


public byte getData_Exist(){
    return this.data_Exist;
}


public String getIndicator_GId(){
    return this.indicator_GId;
}


public void setHighIsGood(byte highIsGood){
    this.highIsGood = highIsGood;
}


}