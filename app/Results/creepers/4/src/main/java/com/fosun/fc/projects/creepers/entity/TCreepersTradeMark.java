package com.fosun.fc.projects.creepers.entity;
 import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name = "T_CREEPERS_TRADE_MARK")
@NamedQuery(name = "TCreepersTradeMark.findAll", query = "SELECT t FROM TCreepersTradeMark t")
public class TCreepersTradeMark {

 private  long serialVersionUID;

@Id
@SequenceGenerator(name = "T_CREEPERS_TRADE_MARK_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_TRADE_MARK")
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_TRADE_MARK_ID_GENERATOR")
@Column(unique = true, nullable = false)
 private  long id;

@Column(length = 500)
 private  String applicant;

@Column(name = "APPLY_NO", length = 200)
 private  String applyNo;

@Column(name = "CATEGORY_NO", length = 500)
 private  String categoryNo;

@Column(name = "MER_NAME", length = 1000)
 private  String merName;

@Column(name = "MER_NO", length = 100)
 private  String merNo;

@Column(name = "SEQ_NO")
 private  BigDecimal seqNo;

@Column(name = "TRADE_MARK_NAME", length = 200)
 private  String tradeMarkName;

@Column(length = 200)
 private  String ware;

@Column(length = 200)
 private  String memo;

public TCreepersTradeMark() {
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


public long getId(){
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


public void setMemo(String memo){
    this.memo = memo;
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