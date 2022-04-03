package com.byr.warehouse.pojo;
 import cn.afterturn.easypoi.excel.annotation.Excel;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
@Entity
public class ApplyOutPut {

@Id
// 设置主键并且设置主键为自增
@GeneratedValue(strategy = GenerationType.AUTO)
 private  int id;

@Excel(name = "出仓编号")
 private  String outCode;

@Excel(name = "入仓编号")
 private  String enterCode;

@Excel(name = "入库料号")
 private  String materialCode;

@Excel(name = "出库料号")
 private  String outMaterialCode;

@Excel(name = "规格")
 private  String spec;

@Excel(name = "数量")
 private  int size;

@Excel(name = "净重")
 private  double suttleWeight;

@Excel(name = "毛重")
 private  double roughWeight;

@Excel(name = "单价")
 private  double price;

 private  double amout;

@Excel(name = "提货单位")
 private  String demandName;

 private  String applyPersonid;

 private  String packagePersonid;

 private  String ensurePersonid;

@Excel(name = "生产日期")
 private  String produceDate;

 private  Date applyDate;

 private  String remark;

 private  String status;


public String getDemandName(){
    return demandName;
}


public void setRoughWeight(double roughWeight){
    this.roughWeight = roughWeight;
}


public void setApplyPersonid(String applyPersonid){
    this.applyPersonid = applyPersonid;
}


public String getOutMaterialCode(){
    return outMaterialCode;
}


public int getId(){
    return id;
}


public double getAmout(){
    return amout;
}


public void setPrice(double price){
    this.price = price;
}


public String getStatus(){
    return status;
}


public void setSize(int size){
    this.size = size;
}


public void setProduceDate(String produceDate){
    this.produceDate = produceDate;
}


public void setAmout(double amout){
    this.amout = amout;
}


public void setRemark(String remark){
    this.remark = remark;
}


public void setSuttleWeight(double suttleWeight){
    this.suttleWeight = suttleWeight;
}


public String getRemark(){
    return remark;
}


public void setId(int id){
    this.id = id;
}


public void setOutMaterialCode(String outMaterialCode){
    this.outMaterialCode = outMaterialCode;
}


public void setOutCode(String outCode){
    this.outCode = outCode;
}


public String getProduceDate(){
    return produceDate;
}


public Date getApplyDate(){
    return applyDate;
}


public void setMaterialCode(String materialCode){
    this.materialCode = materialCode;
}


public String getApplyPersonid(){
    return applyPersonid;
}


public void setSpec(String spec){
    this.spec = spec;
}


public String getPackagePersonid(){
    return packagePersonid;
}


public double getPrice(){
    return price;
}


public void setStatus(String status){
    this.status = status;
}


public String getEnterCode(){
    return enterCode;
}


public String getOutCode(){
    return outCode;
}


public double getSuttleWeight(){
    return suttleWeight;
}


public void setPackagePersonid(String packagePersonid){
    this.packagePersonid = packagePersonid;
}


public int getSize(){
    return size;
}


public String getSpec(){
    return spec;
}


public void setDemandName(String demandName){
    this.demandName = demandName;
}


public String getEnsurePersonid(){
    return ensurePersonid;
}


public void setEnterCode(String enterCode){
    this.enterCode = enterCode;
}


public void setEnsurePersonid(String ensurePersonid){
    this.ensurePersonid = ensurePersonid;
}


public double getRoughWeight(){
    return roughWeight;
}


@Override
public String toString(){
    return "ApplyOutPut{" + "id=" + id + ", materialCode='" + materialCode + '\'' + ", spec='" + spec + '\'' + ", size=" + size + ", amout=" + amout + ", demandName='" + demandName + '\'' + '}';
}


public String getMaterialCode(){
    return materialCode;
}


public void setApplyDate(Date applyDate){
    this.applyDate = applyDate;
}


}