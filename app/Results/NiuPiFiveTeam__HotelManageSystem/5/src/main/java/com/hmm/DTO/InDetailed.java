package com.hmm.DTO;
 import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.hmm.finance.logisticst.domain.InStorage;
public class InDetailed {

 private  Long id;

 private  String goodsName;

 private  String unit;

 private  float price;

 private  float amount;

 private  String goodsNo;

 private  InStorage inAll;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://9";


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


public float getPrice(){
    return price;
}


public String getUnit(){
    return unit;
}


// 多对一级联删除，使用ALL建议先移除关系（update）再delete
@ManyToOne
public InStorage getInAll(){
    return inAll;
}


public float getAmount(){
    return amount;
}


public void setPrice(float price){
    this.price = price;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setPrice"))

.queryParam("price",price)
;
restTemplate.put(builder.toUriString(),null);
}


}