package com.byr.warehouse.pojo;
 import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
@Entity
public class EntrepotStatus {

@Id
// 设置主键并且设置主键为自增
@GeneratedValue(strategy = GenerationType.AUTO)
 private  Long id;

 private  String enterCode;

 private  String materialCode;

 private  String supplyName;

 private  String taxCode;

 private  String goodsStatus;

 private  String materialSpec;

 private  String productName;

 private  String position;

 private  int totalSize;

 private  String entrepotType;

 private  String produceDate;

 private  Date entranceDate;

 private  Date updateDate;


public String getGoodsStatus(){
    return goodsStatus;
}


public void setProductName(String productName){
    this.productName = productName;
}


public Long getId(){
    return id;
}


public String getSupplyName(){
    return supplyName;
}


public String getProductName(){
    return productName;
}


public void setPosition(String position){
    this.position = position;
}


public void setEntrepotType(String entrepotType){
    this.entrepotType = entrepotType;
}


public void setProduceDate(String produceDate){
    this.produceDate = produceDate;
}


public String getEntrepotType(){
    return entrepotType;
}


public Date getEntranceDate(){
    return entranceDate;
}


public Date getUpdateDate(){
    return updateDate;
}


public void setId(Long id){
    this.id = id;
}


public void setSupplyName(String supplyName){
    this.supplyName = supplyName;
}


public String getMaterialSpec(){
    return materialSpec;
}


public String getTaxCode(){
    return taxCode;
}


public void setUpdateDate(Date updateDate){
    this.updateDate = updateDate;
}


public void setTaxCode(String taxCode){
    this.taxCode = taxCode;
}


public void setMaterialSpec(String materialSpec){
    this.materialSpec = materialSpec;
}


public String getProduceDate(){
    return produceDate;
}


public int getTotalSize(){
    return totalSize;
}


public void setMaterialCode(String materialCode){
    this.materialCode = materialCode;
}


public String getEnterCode(){
    return enterCode;
}


public void setEntranceDate(Date entranceDate){
    this.entranceDate = entranceDate;
}


public String getPosition(){
    return position;
}


public void setEnterCode(String enterCode){
    this.enterCode = enterCode;
}


@Override
public String toString(){
    return "EntrepotStatus{" + "enterCode='" + enterCode + '\'' + ", taxCode='" + taxCode + '\'' + ", entranceDate=" + entranceDate + ", updateDate=" + updateDate + '}';
}


public String getMaterialCode(){
    return materialCode;
}


public void setTotalSize(int totalSize){
    this.totalSize = totalSize;
}


public void setGoodsStatus(String goodsStatus){
    this.goodsStatus = goodsStatus;
}


}