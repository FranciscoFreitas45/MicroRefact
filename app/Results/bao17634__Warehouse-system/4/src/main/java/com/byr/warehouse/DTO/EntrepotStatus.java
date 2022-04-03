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


public void setEnterCode(String enterCode){
    this.enterCode = enterCode;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setEnterCode"))

.queryParam("enterCode",enterCode)
;
restTemplate.put(builder.toUriString(),null);
}


public void setMaterialCode(String materialCode){
    this.materialCode = materialCode;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setMaterialCode"))

.queryParam("materialCode",materialCode)
;
restTemplate.put(builder.toUriString(),null);
}


public void setEntranceDate(Date entranceDate){
    this.entranceDate = entranceDate;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setEntranceDate"))

.queryParam("entranceDate",entranceDate)
;
restTemplate.put(builder.toUriString(),null);
}


public void setUpdateDate(Date updateDate){
    this.updateDate = updateDate;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setUpdateDate"))

.queryParam("updateDate",updateDate)
;
restTemplate.put(builder.toUriString(),null);
}


public void setProduceDate(String produceDate){
    this.produceDate = produceDate;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setProduceDate"))

.queryParam("produceDate",produceDate)
;
restTemplate.put(builder.toUriString(),null);
}


public void setMaterialSpec(String materialSpec){
    this.materialSpec = materialSpec;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setMaterialSpec"))

.queryParam("materialSpec",materialSpec)
;
restTemplate.put(builder.toUriString(),null);
}


public void setProductName(String productName){
    this.productName = productName;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setProductName"))

.queryParam("productName",productName)
;
restTemplate.put(builder.toUriString(),null);
}


public void setTotalSize(int totalSize){
    this.totalSize = totalSize;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setTotalSize"))

.queryParam("totalSize",totalSize)
;
restTemplate.put(builder.toUriString(),null);
}


public void setPosition(String position){
    this.position = position;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setPosition"))

.queryParam("position",position)
;
restTemplate.put(builder.toUriString(),null);
}


}