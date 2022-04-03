package com.zis.storage.dto;
 import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
public class FastTakeGoodsDTO {

@NotEmpty(message = "库位不能为空")
 private  String posLabel;

@NotNull(message = "数量不能为空")
 private  Integer amount;

@NotNull(message = "图书isbn不能为空")
 private  Integer skuId;

 private  String bookinfoStr;


public void setPosLabel(String posLabel){
    this.posLabel = posLabel;
}


public void setSkuId(Integer skuId){
    this.skuId = skuId;
}


public void setBookinfoStr(String bookinfoStr){
    this.bookinfoStr = bookinfoStr;
}


public Integer getSkuId(){
    return skuId;
}


public void setAmount(Integer amount){
    this.amount = amount;
}


public String getBookinfoStr(){
    return bookinfoStr;
}


public String getPosLabel(){
    return posLabel;
}


public Integer getAmount(){
    return amount;
}


}