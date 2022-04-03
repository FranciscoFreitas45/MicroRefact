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


public void setGoodsNo(String goodsNo){
    this.goodsNo = goodsNo;
}


public String getGoodsName(){
    return goodsName;
}


public void setGoodsName(String goodsName){
    this.goodsName = goodsName;
}


public String getGoodsNo(){
    return goodsNo;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
public Long getId(){
    return id;
}


public void setPrice(float price){
    this.price = price;
}


public float getPrice(){
    return price;
}


public void setUnit(String unit){
    this.unit = unit;
}


public void setId(Long id){
    this.id = id;
}


public String getUnit(){
    return unit;
}


public void setAmount(float amount){
    this.amount = amount;
}


public void setInAll(InStorage inAll){
    this.inAll = inAll;
}


// 多对一级联删除，使用ALL建议先移除关系（update）再delete
@ManyToOne
public InStorage getInAll(){
    return inAll;
}


public float getAmount(){
    return amount;
}


}