package com.crontab;
 import java.util.Date;
public class OrderSizeTier {

 private  String currency;

 private  double factor;

 private  String groupId;

 private  long maxSize;

 private  long minSize;

 private  int tierLevel;

 private  String updatedBy;

 private  Date updatedDatetime;

public OrderSizeTier() {
}
public String getCurrency(){
    return this.currency;
}


public void setUpdatedBy(String updatedBy){
    this.updatedBy = updatedBy;
}


public void setGroupId(String groupId){
    this.groupId = groupId;
}


public String getUpdatedBy(){
    return this.updatedBy;
}


public Date getUpdatedDatetime(){
    return this.updatedDatetime;
}


public long getMinSize(){
    return this.minSize;
}


public int getTierLevel(){
    return this.tierLevel;
}


public void setCurrency(String currency){
    this.currency = currency;
}


public void setMaxSize(long maxSize){
    this.maxSize = maxSize;
}


public double getFactor(){
    return this.factor;
}


public String getGroupId(){
    return groupId;
}


public void setFactor(double factor){
    this.factor = factor;
}


public long getMaxSize(){
    return this.maxSize;
}


public void setUpdatedDatetime(Date updatedDatetime){
    this.updatedDatetime = (Date) updatedDatetime.clone();
}


public void setMinSize(long minSize){
    this.minSize = minSize;
}


@Override
public String toString(){
    return "OrderSizeTier [currency=" + currency + ", factor=" + factor + ", groupId=" + groupId + ", maxSize=" + maxSize + ", minSize=" + minSize + ", tierLevel=" + tierLevel + ", updatedBy=" + updatedBy + ", updatedDatetime=" + updatedDatetime + "]";
}


public void setTierLevel(int tierLevel){
    this.tierLevel = tierLevel;
}


}