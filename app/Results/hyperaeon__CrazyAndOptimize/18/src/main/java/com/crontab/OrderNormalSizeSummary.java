package com.crontab;
 import java.math.BigDecimal;
import java.util.List;
public class OrderNormalSizeSummary {

 private  OrderNormalSizeKey key;

 private  List<BigDecimal> ccy1AmountList;

 private  List<BigDecimal> ccy2AmountList;

 private  BigDecimal ccy1NormalSize;

 private  BigDecimal ccy2NormalSize;


public void setCcy2AmountList(List<BigDecimal> ccy2AmountList){
    this.ccy2AmountList = ccy2AmountList;
}


public OrderNormalSizeKey getKey(){
    return key;
}


public List<BigDecimal> getCcy1AmountList(){
    return ccy1AmountList;
}


public BigDecimal getCcy2NormalSize(){
    return ccy2NormalSize;
}


public void setCcy1NormalSize(BigDecimal ccy1NormalSize){
    this.ccy1NormalSize = ccy1NormalSize;
}


public BigDecimal getCcy1NormalSize(){
    return ccy1NormalSize;
}


public void setCcy2NormalSize(BigDecimal ccy2NormalSize){
    this.ccy2NormalSize = ccy2NormalSize;
}


public void setCcy1AmountList(List<BigDecimal> ccy1AmountList){
    this.ccy1AmountList = ccy1AmountList;
}


public void setKey(OrderNormalSizeKey key){
    this.key = key;
}


public List<BigDecimal> getCcy2AmountList(){
    return ccy2AmountList;
}


}