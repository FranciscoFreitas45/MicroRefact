package com.byr.warehouse.pojo;
 import cn.afterturn.easypoi.excel.annotation.Excel;
import java.util.Date;
public class EnterReport {

@Excel(name = "入库编号")
 private  String enterCode;

@Excel(name = "供应商名称")
 private  String goodsFrom;

@Excel(name = "品名")
 private  String productName;

@Excel(name = "料号")
 private  String materialCode;

@Excel(name = "规格")
 private  String goodsSpec;

@Excel(name = "生产日期")
 private  String produceDate;

@Excel(name = "数量")
 private  int goodsNumber;

@Excel(name = "单位")
 private  String goodsUnit;

@Excel(name = "件数")
 private  int suitNumber;

@Excel(name = "净重")
 private  double suttleWeight;

@Excel(name = "毛重")
 private  double roughWeight;

@Excel(name = "")
 private  String billNumber;

@Excel(name = "实际入库日期")
 private  Date applyDate;


public void setBillNumber(String billNumber){
    this.billNumber = billNumber;
}


public void setGoodsFrom(String goodsFrom){
    this.goodsFrom = goodsFrom;
}


public void setRoughWeight(double roughWeight){
    this.roughWeight = roughWeight;
}


public void setProductName(String productName){
    this.productName = productName;
}


public String getGoodsUnit(){
    return goodsUnit;
}


public String getProductName(){
    return productName;
}


public void setGoodsSpec(String goodsSpec){
    this.goodsSpec = goodsSpec;
}


public void setProduceDate(String produceDate){
    this.produceDate = produceDate;
}


public void setSuttleWeight(double suttleWeight){
    this.suttleWeight = suttleWeight;
}


public void setGoodsUnit(String goodsUnit){
    this.goodsUnit = goodsUnit;
}


public int getSuitNumber(){
    return suitNumber;
}


public void setGoodsNumber(int goodsNumber){
    this.goodsNumber = goodsNumber;
}


public String getProduceDate(){
    return produceDate;
}


public String getBillNumber(){
    return billNumber;
}


public Date getApplyDate(){
    return applyDate;
}


public String getGoodsFrom(){
    return goodsFrom;
}


public void setMaterialCode(String materialCode){
    this.materialCode = materialCode;
}


public void setSuitNumber(int suitNumber){
    this.suitNumber = suitNumber;
}


public String getEnterCode(){
    return enterCode;
}


public double getSuttleWeight(){
    return suttleWeight;
}


public void setEnterCode(String enterCode){
    this.enterCode = enterCode;
}


public String getGoodsSpec(){
    return goodsSpec;
}


public int getGoodsNumber(){
    return goodsNumber;
}


public double getRoughWeight(){
    return roughWeight;
}


public String getMaterialCode(){
    return materialCode;
}


public void setApplyDate(Date applyDate){
    this.applyDate = applyDate;
}


}