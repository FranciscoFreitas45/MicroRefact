package com.zis.youzan.response;
 import java.util.List;
import com.alibaba.fastjson.annotation.JSONField;
public class Item {

@JSONField(name = "num_iid")
 private  Long numIid;

 private  String title;

 private  List<GoodsSku> skus;

@JSONField(name = "outer_id")
 private  String outerId;

@JSONField(name = "post_fee")
 private  Double postFee;

@JSONField(name = "price")
 private  Double price;

@JSONField(name = "delivery_template_id")
 private  Long deliveryTemplateId;

 private  Long num;


public void setNumIid(Long numIid){
    this.numIid = numIid;
}


public void setNum(Long num){
    this.num = num;
}


public void setSkus(List<GoodsSku> skus){
    this.skus = skus;
}


public void setPostFee(Double postFee){
    this.postFee = postFee;
}


public void setTitle(String title){
    this.title = title;
}


public List<GoodsSku> getSkus(){
    return skus;
}


public Double getPostFee(){
    return postFee;
}


public void setPrice(Double price){
    this.price = price;
}


public Double getPrice(){
    return price;
}


public Long getDeliveryTemplateId(){
    return deliveryTemplateId;
}


public String getTitle(){
    return title;
}


public void setDeliveryTemplateId(Long deliveryTemplateId){
    this.deliveryTemplateId = deliveryTemplateId;
}


public Long getNum(){
    return num;
}


public String getOuterId(){
    return outerId;
}


@Override
public String toString(){
    return "Item [numIid=" + numIid + ", title=" + title + ", skus=" + skus + ", outerId=" + outerId + ", postFee=" + postFee + ", price=" + price + ", deliveryTemplateId=" + deliveryTemplateId + ", num=" + num + "]\n";
}


public Long getNumIid(){
    return numIid;
}


public void setOuterId(String outerId){
    this.outerId = outerId;
}


}