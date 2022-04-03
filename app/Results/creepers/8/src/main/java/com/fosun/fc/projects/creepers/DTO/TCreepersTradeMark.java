package com.fosun.fc.projects.creepers.DTO;
 import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
public class TCreepersTradeMark {

 private  long serialVersionUID;

 private  long id;

 private  String applicant;

 private  String applyNo;

 private  String categoryNo;

 private  String merName;

 private  String merNo;

 private  BigDecimal seqNo;

 private  String tradeMarkName;

 private  String ware;

 private  String memo;

public TCreepersTradeMark() {
}
public String getMerNo(){
    return merNo;
}


public long getId(){
    return this.id;
}


public BigDecimal getSeqNo(){
    return this.seqNo;
}


public void setApplicant(String applicant){
    this.applicant = applicant;
}


public void setSeqNo(BigDecimal seqNo){
    this.seqNo = seqNo;
}


public String getMemo(){
    return memo;
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