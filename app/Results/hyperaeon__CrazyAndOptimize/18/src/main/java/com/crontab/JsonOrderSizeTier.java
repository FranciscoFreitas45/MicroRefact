package com.crontab;
 import java.math.BigDecimal;
import java.util.List;
public class JsonOrderSizeTier extends AbstractJson{

 private  List<OrderSizeTier> ccy1tiers;

 private  List<OrderSizeTier> ccy2tiers;

 private  BigDecimal ccy1MinimalSize;

 private  BigDecimal ccy2MinimalSize;


public void setCcy1MinimalSize(BigDecimal ccy1MinimalSize){
    this.ccy1MinimalSize = ccy1MinimalSize;
}


public List<OrderSizeTier> getCcy2tiers(){
    return ccy2tiers;
}


public void setCcy2tiers(List<OrderSizeTier> ccy2tiers){
    this.ccy2tiers = ccy2tiers;
}


public BigDecimal getCcy1MinimalSize(){
    return ccy1MinimalSize;
}


public BigDecimal getCcy2MinimalSize(){
    return ccy2MinimalSize;
}


public void setCcy1tiers(List<OrderSizeTier> ccy1tiers){
    this.ccy1tiers = ccy1tiers;
}


public void setCcy2MinimalSize(BigDecimal ccy2MinimalSize){
    this.ccy2MinimalSize = ccy2MinimalSize;
}


public List<OrderSizeTier> getCcy1tiers(){
    return ccy1tiers;
}


}