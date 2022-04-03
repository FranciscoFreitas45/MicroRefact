package com.byr.warehouse.pojo;
 import cn.afterturn.easypoi.excel.annotation.Excel;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
@Entity
public class ApplyEnter {

@Id
// 设置主键并且设置主键为自增
@GeneratedValue(strategy = GenerationType.AUTO)
 private  int enterId;

@Excel(name = "入库编号")
 private  String enterCode;

@Excel(name = "料号")
 private  String materialCode;

@Excel(name = "规格")
 private  String spec;

@Excel(name = "数量")
 private  int number;

@Excel(name = "单位")
 private  String unit;

 private  double unitPrice;

 private  String coinType;

 private  double sumMoney;

@Excel(name = "发票号")
 private  String billNumber;

@Excel(name = "净重")
 private  double suttleWeight;

@Excel(name = "毛重")
 private  double roughWeight;

@Excel(name = "品名")
 private  String productName;

 private  String originCountry;

 private  String dataCode;

 private  String lotno;

 private  String pono;

 private  String poLoneno;

 private  String position;

 private  String treasury;

@Excel(name = "供应商")
 private  String goodsFrom;

@Excel(name = "件数")
 private  int suitNumber;

 private  String suitUnit;

 private  String status;

 private  String applyPersonId;

 private  String ensurePersonId;

 private  String remark;

@Excel(name = "生产日期")
 private  String produceDate;

 private  Date applyDate;


public double getSumMoney(){
    return sumMoney;
}


public void setGoodsFrom(String goodsFrom){
    this.goodsFrom = goodsFrom;
}


public void setProductName(String productName){
    this.productName = productName;
}


public void setEnterId(int enterId){
    this.enterId = enterId;
}


public String getStatus(){
    return status;
}


public void setPosition(String position){
    this.position = position;
}


public String getProductName(){
    return productName;
}


public void setProduceDate(String produceDate){
    this.produceDate = produceDate;
}


public void setDataCode(String dataCode){
    this.dataCode = dataCode;
}


public String getEnsurePersonId(){
    return ensurePersonId;
}


public void setRemark(String remark){
    this.remark = remark;
}


public void setEnsurePersonId(String ensurePersonId){
    this.ensurePersonId = ensurePersonId;
}


public String getRemark(){
    return remark;
}


public void setSuitUnit(String suitUnit){
    this.suitUnit = suitUnit;
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


public void setUnitPrice(int unitPrice){
    this.unitPrice = unitPrice;
}


public void setApplyPersonId(String applyPersonId){
    this.applyPersonId = applyPersonId;
}


public Date getApplyDate(){
    return applyDate;
}


public void setMaterialCode(String materialCode){
    this.materialCode = materialCode;
}


public void setSuitNumber(int suitNumber){
    this.suitNumber = suitNumber;
}


public void setCoinType(String coinType){
    this.coinType = coinType;
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


public void setEnterCode(String enterCode){
    this.enterCode = enterCode;
}


public void setLotno(String lotno){
    this.lotno = lotno;
}


public void setTreasury(String treasury){
    this.treasury = treasury;
}


public void setBillNumber(String billNumber){
    this.billNumber = billNumber;
}


public String getCoinType(){
    return coinType;
}


public void setRoughWeight(int roughWeight){
    this.roughWeight = roughWeight;
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


public void setOriginCountry(String originCountry){
    this.originCountry = originCountry;
}


public void setSumMoney(int sumMoney){
    this.sumMoney = sumMoney;
}


public String getSuitUnit(){
    return suitUnit;
}


public void setUnit(String unit){
    this.unit = unit;
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


public void setSpec(String spec){
    this.spec = spec;
}


public void setNumber(int number){
    this.number = number;
}


public String getEnterCode(){
    return enterCode;
}


public void setStatus(String status){
    this.status = status;
}


public String getPono(){
    return pono;
}


public String getPosition(){
    return position;
}


public void setPoLoneno(String poLoneno){
    this.poLoneno = poLoneno;
}


public String getTreasury(){
    return treasury;
}


public double getRoughWeight(){
    return roughWeight;
}


@Override
public String toString(){
    return "ApplyEnter{" + "enterId=" + enterId + ", sumMoney=" + sumMoney + ", originCountry='" + originCountry + '\'' + ", suitNumber=" + suitNumber + ", status='" + status + '\'' + '}';
}


public String getMaterialCode(){
    return materialCode;
}


public void setApplyDate(Date applyDate){
    this.applyDate = applyDate;
}


public String getUnit(){
    return unit;
}


}