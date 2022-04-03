package com.hmm.DTO;
 import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.hmm.logistics.stock.util.StockType;
import com.hmm.logistics.stock.util.YesOrNoSend;
public class Stock {

 private  Long id;

 private  String goodsName;

 private  String unit;

 private  float amount;

 private  StockType stockType;

 private  YesOrNoSend yesOrNoSend;

 private  String goodsNo;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://8";


public String getGoodsName(){
    return goodsName;
}


public String getGoodsNo(){
    return goodsNo;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
public Long getId(){
    return id;
}


public StockType getStockType(){
    return stockType;
}


public void setId(Long id){
    this.id = id;
}


public YesOrNoSend getYesOrNoSend(){
    return yesOrNoSend;
}


public String getUnit(){
    return unit;
}


public void setYesOrNoSend(YesOrNoSend yesOrNoSend){
    this.yesOrNoSend = yesOrNoSend;
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


}