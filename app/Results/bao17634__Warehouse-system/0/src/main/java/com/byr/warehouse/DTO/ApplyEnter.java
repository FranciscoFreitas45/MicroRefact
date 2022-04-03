package com.byr.warehouse.DTO;
 import cn.afterturn.easypoi.excel.annotation.Excel;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
public class ApplyEnter {

 private  int enterId;

 private  String enterCode;

 private  String materialCode;

 private  String spec;

 private  int number;

 private  String unit;

 private  double unitPrice;

 private  String coinType;

 private  double sumMoney;

 private  String billNumber;

 private  double suttleWeight;

 private  double roughWeight;

 private  String productName;

 private  String originCountry;

 private  String dataCode;

 private  String lotno;

 private  String pono;

 private  String poLoneno;

 private  String position;

 private  String treasury;

 private  String goodsFrom;

 private  int suitNumber;

 private  String suitUnit;

 private  String status;

 private  String applyPersonId;

 private  String ensurePersonId;

 private  String remark;

 private  String produceDate;

 private  Date applyDate;


public double getSumMoney(){
    return sumMoney;
}


public void setProductName(String productName){
    this.productName = productName;
}


public String getStatus(){
    return status;
}


public String getProductName(){
    return productName;
}


public void setDataCode(String dataCode){
    this.dataCode = dataCode;
}


public String getEnsurePersonId(){
    return ensurePersonId;
}


public void setEnsurePersonId(String ensurePersonId){
    this.ensurePersonId = ensurePersonId;
}


public String getRemark(){
    return remark;
}


public double getUnitPrice(){
    return unitPrice;
}


public String getOriginCountry(){
    return originCountry;
}


public String getPoLoneno(){
    return poLoneno;
}


public void setApplyPersonId(String applyPersonId){
    this.applyPersonId = applyPersonId;
}


public Date getApplyDate(){
    return applyDate;
}


public void setSuitNumber(int suitNumber){
    this.suitNumber = suitNumber;
}


public double getSuttleWeight(){
    return suttleWeight;
}


public int getEnterId(){
    return enterId;
}


public String getSpec(){
    return spec;
}


public void setLotno(String lotno){
    this.lotno = lotno;
}


public void setBillNumber(String billNumber){
    this.billNumber = billNumber;
}


public String getCoinType(){
    return coinType;
}


public void setPono(String pono){
    this.pono = pono;
}


public String getApplyPersonId(){
    return applyPersonId;
}


public int getNumber(){
    return number;
}


public void setSumMoney(int sumMoney){
    this.sumMoney = sumMoney;
}


public String getSuitUnit(){
    return suitUnit;
}


public void setSuttleWeight(int suttleWeight){
    this.suttleWeight = suttleWeight;
}


public String getDataCode(){
    return dataCode;
}


public int getSuitNumber(){
    return suitNumber;
}


public String getLotno(){
    return lotno;
}


public String getProduceDate(){
    return produceDate;
}


public String getBillNumber(){
    return billNumber;
}


public String getGoodsFrom(){
    return goodsFrom;
}


public void setNumber(int number){
    this.number = number;
}


public String getEnterCode(){
    return enterCode;
}


public String getPono(){
    return pono;
}


public String getPosition(){
    return position;
}


public String getTreasury(){
    return treasury;
}


public double getRoughWeight(){
    return roughWeight;
}


public String getMaterialCode(){
    return materialCode;
}


public String getUnit(){
    return unit;
}


}