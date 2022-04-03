package com.fosun.fc.projects.creepers.DTO;
 import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.fosun.fc.projects.creepers.Request.TCreepersAccountBakRequest;
import com.fosun.fc.projects.creepers.Request.Impl.TCreepersAccountBakRequestImpl;
import com.fosun.fc.projects.creepers.DTO.TCreepersAccountBak;
public class TCreepersPublicTax {

 private  long serialVersionUID;

 private  Long id;

 private  String competentTaxAuthority;

 private  String memo;

 private  String rptNo;

 private  BigDecimal taxAmount;

 private  Date taxStatisticDt;

 private  String taxpayerNo;

 private  TCreepersAccountBak TCreepersAccountBak;

 private Long id;

 private TCreepersAccountBakRequest tcreepersaccountbakrequest = new TCreepersAccountBakRequestImpl();;

public TCreepersPublicTax() {
}
public BigDecimal getTaxAmount(){
    return this.taxAmount;
}


public void setTaxAmount(BigDecimal taxAmount){
    this.taxAmount = taxAmount;
}


public Long getId(){
    return this.id;
}


public void setTaxStatisticDt(Date taxStatisticDt){
    this.taxStatisticDt = taxStatisticDt;
}


public void setTCreepersAccountBak(TCreepersAccountBak TCreepersAccountBak){
 tcreepersaccountbakrequest.setTCreepersAccountBak(TCreepersAccountBak,this.id);
}



public void setCompetentTaxAuthority(String competentTaxAuthority){
    this.competentTaxAuthority = competentTaxAuthority;
}


public void setTaxpayerNo(String taxpayerNo){
    this.taxpayerNo = taxpayerNo;
}


public Date getTaxStatisticDt(){
    return this.taxStatisticDt;
}


public void setMemo(String memo){
    this.memo = memo;
}


public String getMemo(){
    return this.memo;
}


public String getCompetentTaxAuthority(){
    return this.competentTaxAuthority;
}


public void setRptNo(String rptNo){
    this.rptNo = rptNo;
}


public TCreepersAccountBak getTCreepersAccountBak(){
  this.TCreepersAccountBak = tcreepersaccountbakrequest.getTCreepersAccountBak(this.id);
return this.TCreepersAccountBak;
}


public void setId(Long id){
    this.id = id;
}


public String getRptNo(){
    return this.rptNo;
}


public String getTaxpayerNo(){
    return this.taxpayerNo;
}


}