package com.dtdhehe.ptulife.DTO;
 import javax.persistence.Entity;
import javax.persistence.Id;
public class Market {

 private  String id;

 private  String userId;

 private  String goodsName;

 private  String goodsDesc;

 private  String goodsImg;

 private  String goodsUser;

 private  String goodsPrice;

 private  String phone;

 private  String createTime;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://6";


public String getPhone(){
    return phone;
}


public String getGoodsName(){
    return goodsName;
}


public String getCreateTime(){
    return createTime;
}


public String getGoodsImg(){
    return goodsImg;
}


public String getGoodsUser(){
    return goodsUser;
}


public String getId(){
    return id;
}


public String getGoodsPrice(){
    return goodsPrice;
}


public String getGoodsDesc(){
    return goodsDesc;
}


@Override
public String toString(){
    return "Market{" + "id='" + id + '\'' + ", userId='" + userId + '\'' + ", goodsName='" + goodsName + '\'' + ", goodsDesc='" + goodsDesc + '\'' + ", goodsImg='" + goodsImg + '\'' + ", goodsUser='" + goodsUser + '\'' + ", goodsPrice='" + goodsPrice + '\'' + ", phone='" + phone + '\'' + ", createTime='" + createTime + '\'' + '}';
}


public void setGoodsPrice(String goodsPrice){
    this.goodsPrice = goodsPrice;
}


public String getUserId(){
    return userId;
}


public void setUserId(String userId){
    this.userId = userId;
}


public void setCreateTime(String createTime){
    this.createTime = createTime;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setCreateTime"))

.queryParam("createTime",createTime)
;
restTemplate.put(builder.toUriString(),null);
}


}