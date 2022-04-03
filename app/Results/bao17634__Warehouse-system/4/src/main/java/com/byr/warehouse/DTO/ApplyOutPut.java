package com.byr.warehouse.DTO;
 import cn.afterturn.easypoi.excel.annotation.Excel;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
public class ApplyOutPut {

 private  int id;

 private  String outCode;

 private  String enterCode;

 private  String materialCode;

 private  String outMaterialCode;

 private  String spec;

 private  int size;

 private  double suttleWeight;

 private  double roughWeight;

 private  double price;

 private  double amout;

 private  String demandName;

 private  String applyPersonid;

 private  String packagePersonid;

 private  String ensurePersonid;

 private  String produceDate;

 private  Date applyDate;

 private  String remark;

 private  String status;


public String getDemandName(){
    return demandName;
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


public String getStatus(){
    return status;
}


public void setProduceDate(String produceDate){
    this.produceDate = produceDate;
}


public void setRemark(String remark){
    this.remark = remark;
}


public String getRemark(){
    return remark;
}


public void setOutMaterialCode(String outMaterialCode){
    this.outMaterialCode = outMaterialCode;
}


public String getProduceDate(){
    return produceDate;
}


public Date getApplyDate(){
    return applyDate;
}


public String getApplyPersonid(){
    return applyPersonid;
}


public String getPackagePersonid(){
    return packagePersonid;
}


public double getPrice(){
    return price;
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


public int getSize(){
    return size;
}


public String getSpec(){
    return spec;
}


public String getEnsurePersonid(){
    return ensurePersonid;
}


public void setEnsurePersonid(String ensurePersonid){
    this.ensurePersonid = ensurePersonid;
}


public double getRoughWeight(){
    return roughWeight;
}


public String getMaterialCode(){
    return materialCode;
}


}