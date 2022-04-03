package com.zis.storage.dto;
 public class StockDTO {

 private  Integer stockId;

 private  Integer posId;

 private  String posLabel;

 private  Integer repoId;

 private  Integer productId;

 private  Integer skuId;

 private  Integer totalAmt;

 private  Integer occupyAmt;

public StockDTO() {
}public StockDTO(Integer stockId, Integer posId, String posLabel, Integer repoId, Integer productId, Integer skuId, Integer totalAmt, Integer occupyAmt) {
    this.stockId = stockId;
    this.posId = posId;
    this.posLabel = posLabel;
    this.repoId = repoId;
    this.productId = productId;
    this.skuId = skuId;
    this.totalAmt = totalAmt;
    this.occupyAmt = occupyAmt;
}
public Integer getRepoId(){
    return repoId;
}


public void setPosLabel(String posLabel){
    this.posLabel = posLabel;
}


public Integer getOccupyAmt(){
    return occupyAmt;
}


public void setSkuId(Integer skuId){
    this.skuId = skuId;
}


public void setOccupyAmt(Integer occupyAmt){
    this.occupyAmt = occupyAmt;
}


public Integer getPosId(){
    return posId;
}


public Integer getSkuId(){
    return skuId;
}


public void setStockId(Integer stockId){
    this.stockId = stockId;
}


public void setPosId(Integer posId){
    this.posId = posId;
}


public void setProductId(Integer productId){
    this.productId = productId;
}


public Integer getTotalAmt(){
    return totalAmt;
}


public Integer getStockId(){
    return stockId;
}


public void setTotalAmt(Integer totalAmt){
    this.totalAmt = totalAmt;
}


public Integer getProductId(){
    return productId;
}


public void setRepoId(Integer repoId){
    this.repoId = repoId;
}


public String getPosLabel(){
    return posLabel;
}


}