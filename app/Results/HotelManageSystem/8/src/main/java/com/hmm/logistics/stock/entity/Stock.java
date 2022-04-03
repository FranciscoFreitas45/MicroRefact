package com.hmm.logistics.stock.entity;
 import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.hmm.logistics.stock.util.StockType;
import com.hmm.logistics.stock.util.YesOrNoSend;
@Entity
@Table(name = "t_stock")
public class Stock {

 private  Long id;

 private  String goodsName;

 private  String unit;

 private  float amount;

 private  StockType stockType;

 private  YesOrNoSend yesOrNoSend;

 private  String goodsNo;


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


public void setUnit(String unit){
    this.unit = unit;
}


public StockType getStockType(){
    return stockType;
}


public void setStockType(StockType stockType){
    this.stockType = stockType;
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


public void setAmount(float amount){
    this.amount = amount;
}


public void setYesOrNoSend(YesOrNoSend yesOrNoSend){
    this.yesOrNoSend = yesOrNoSend;
}


public float getAmount(){
    return amount;
}


}