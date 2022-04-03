package com.byr.warehouse.DTO;
 import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
public class EntrepotStatus {

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

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://5";


public String getGoodsStatus(){
    return goodsStatus;
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


public void setEntrepotType(String entrepotType){
    this.entrepotType = entrepotType;
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


public void setSupplyName(String supplyName){
    this.supplyName = supplyName;
}


public String getMaterialSpec(){
    return materialSpec;
}


public String getTaxCode(){
    return taxCode;
}


public void setTaxCode(String taxCode){
    this.taxCode = taxCode;
}


public String getProduceDate(){
    return produceDate;
}


public int getTotalSize(){
    return totalSize;
}


public String getEnterCode(){
    return enterCode;
}


public String getPosition(){
    return position;
}


@Override
public String toString(){
    return "EntrepotStatus{" + "enterCode='" + enterCode + '\'' + ", taxCode='" + taxCode + '\'' + ", entranceDate=" + entranceDate + ", updateDate=" + updateDate + '}';
}


public String getMaterialCode(){
    return materialCode;
}


public void setGoodsStatus(String goodsStatus){
    this.goodsStatus = goodsStatus;
}


public void setTotalSize(int totalSize){
    this.totalSize = totalSize;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setTotalSize"))

.queryParam("totalSize",totalSize)
;
restTemplate.put(builder.toUriString(),null);
}


}