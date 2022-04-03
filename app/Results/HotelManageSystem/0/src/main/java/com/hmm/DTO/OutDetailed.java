package com.hmm.DTO;
 import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
public class OutDetailed {

 private  Long id;

 private  String goodsName;

 private  String goodsNo;

 private  float amount;

 private  OutStorage outStorage;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://8";


public String getGoodsName(){
    return goodsName;
}


@ManyToOne
public OutStorage getOutStorage(){
    return outStorage;
}


public String getGoodsNo(){
    return goodsNo;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
public Long getId(){
    return id;
}


public float getAmount(){
    return amount;
}


public void setAmount(float amount){
    this.amount = amount;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAmount"))

.queryParam("amount",amount)
;
restTemplate.put(builder.toUriString(),null);
}


public void setGoodsName(String goodsName){
    this.goodsName = goodsName;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setGoodsName"))

.queryParam("goodsName",goodsName)
;
restTemplate.put(builder.toUriString(),null);
}


public void setGoodsNo(String goodsNo){
    this.goodsNo = goodsNo;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setGoodsNo"))

.queryParam("goodsNo",goodsNo)
;
restTemplate.put(builder.toUriString(),null);
}


public void setOutStorage(OutStorage outStorage){
    this.outStorage = outStorage;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setOutStorage"))

.queryParam("outStorage",outStorage)
;
restTemplate.put(builder.toUriString(),null);
}


}