package com.zis.purchase.dto;
 import java.util.Date;
import com.zis.purchase.bean.PurchasePlan;
public class PurchasePlanView extends PurchasePlan{

 private  Date publishDate;

 private  Float bookPrice;

 private  boolean isNewEdition;

 private  boolean repeatIsbn;

 private  boolean manualDecisionFlag;

 private  Integer stillRequireAmount;

 private  Integer stockAmount;


public Float getBookPrice(){
    return bookPrice;
}


public void setBookPrice(Float bookPrice){
    this.bookPrice = bookPrice;
}


public boolean isManualDecisionFlag(){
    return manualDecisionFlag;
}


public void setManualDecisionFlag(boolean manualDecisionFlag){
    this.manualDecisionFlag = manualDecisionFlag;
}


public void setStillRequireAmount(Integer stillRequireAmount){
    this.stillRequireAmount = stillRequireAmount;
}


public void setPublishDate(Date publishDate){
    this.publishDate = publishDate;
}


public Date getPublishDate(){
    return publishDate;
}


public Integer getStockAmount(){
    return stockAmount;
}


public void setRepeatIsbn(boolean repeatIsbn){
    this.repeatIsbn = repeatIsbn;
}


public void setNewEdition(boolean isNewEdition){
    this.isNewEdition = isNewEdition;
}


public void setStockAmount(Integer stockAmount){
    this.stockAmount = stockAmount;
}


public boolean getIsNewEdition(){
    return isNewEdition;
}


public Integer getStillRequireAmount(){
    return stillRequireAmount;
}


public boolean getRepeatIsbn(){
    return repeatIsbn;
}


}