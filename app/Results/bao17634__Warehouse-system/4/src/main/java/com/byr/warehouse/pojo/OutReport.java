package com.byr.warehouse.pojo;
 import cn.afterturn.easypoi.excel.annotation.Excel;
public class OutReport {

@Excel(name = "出库编号")
 private  String outCode;

@Excel(name = "提货单位")
 private  String demandName;

@Excel(name = "供应商名称")
 private  String goodsFrom;

@Excel(name = "入仓编号")
 private  String enterCode;

@Excel(name = "入库品名")
 private  String enterProductName;

@Excel(name = "入库料号")
 private  String materialCode;

@Excel(name = "规格")
 private  String goodsSpec;

@Excel(name = "生产日期")
 private  String produceDate;

@Excel(name = "出库品名")
 private  String outProductName;

@Excel(name = "出库料号")
 private  String outMaterialCode;

@Excel(name = "数量")
 private  int goodsSize;

@Excel(name = "数量单位")
 private  String goodsUnit;

@Excel(name = "实际出库日期")
 private  String outDate;


public String getOutProductName(){
    return outProductName;
}


public String getDemandName(){
    return demandName;
}


public void setGoodsFrom(String goodsFrom){
    this.goodsFrom = goodsFrom;
}


public String getOutMaterialCode(){
    return outMaterialCode;
}


public String getGoodsUnit(){
    return goodsUnit;
}


public int getGoodsSize(){
    return goodsSize;
}


public void setGoodsSpec(String goodsSpec){
    this.goodsSpec = goodsSpec;
}


public void setEnterProductName(String enterProductName){
    this.enterProductName = enterProductName;
}


public void setProduceDate(String produceDate){
    this.produceDate = produceDate;
}


public void setGoodsSize(int goodsSize){
    this.goodsSize = goodsSize;
}


public void setGoodsUnit(String goodsUnit){
    this.goodsUnit = goodsUnit;
}


public void setOutMaterialCode(String outMaterialCode){
    this.outMaterialCode = outMaterialCode;
}


public void setOutCode(String outCode){
    this.outCode = outCode;
}


public void setOutDate(String outDate){
    this.outDate = outDate;
}


public String getProduceDate(){
    return produceDate;
}


public String getGoodsFrom(){
    return goodsFrom;
}


public void setMaterialCode(String materialCode){
    this.materialCode = materialCode;
}


public String getOutDate(){
    return outDate;
}


public String getEnterCode(){
    return enterCode;
}


public String getOutCode(){
    return outCode;
}


public String getEnterProductName(){
    return enterProductName;
}


public void setDemandName(String demandName){
    this.demandName = demandName;
}


public void setEnterCode(String enterCode){
    this.enterCode = enterCode;
}


public String getGoodsSpec(){
    return goodsSpec;
}


public void setOutProductName(String outProductName){
    this.outProductName = outProductName;
}


public String getMaterialCode(){
    return materialCode;
}


}