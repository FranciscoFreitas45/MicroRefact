package com.fosun.fc.projects.creepers.dto;
 import java.math.BigDecimal;
public class CreepersTradeMarkDTO extends CreepersBaseDTO{

 private  long serialVersionUID;

 private  Long id;

 private  String applicant;

 private  String applyNo;

 private  String categoryNo;

 private  String merName;

 private  String merNo;

 private  BigDecimal seqNo;

 private  String tradeMarkName;

 private  String ware;

public CreepersTradeMarkDTO() {
}
public void setMerNo(String merNo){
    this.merNo = merNo;
}


public String getMerNo(){
    return merNo;
}


public void setMerName(String merName){
    this.merName = merName;
}


public Long getId(){
    return this.id;
}


public void setTradeMarkName(String tradeMarkName){
    this.tradeMarkName = tradeMarkName;
}


public BigDecimal getSeqNo(){
    return this.seqNo;
}


public void setCategoryNo(String categoryNo){
    this.categoryNo = categoryNo;
}


public void setApplicant(String applicant){
    this.applicant = applicant;
}


public void setWare(String ware){
    this.ware = ware;
}


public void setSeqNo(BigDecimal seqNo){
    this.seqNo = seqNo;
}


public String getWare(){
    return this.ware;
}


public String getApplicant(){
    return this.applicant;
}


public String getMerName(){
    return merName;
}


public void setId(long id){
    this.id = id;
}


public void setApplyNo(String applyNo){
    this.applyNo = applyNo;
}


public String getApplyNo(){
    return this.applyNo;
}


public String getTradeMarkName(){
    return this.tradeMarkName;
}


public String getCategoryNo(){
    return this.categoryNo;
}


}