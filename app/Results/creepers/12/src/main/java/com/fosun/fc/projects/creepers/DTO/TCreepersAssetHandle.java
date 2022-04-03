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
import com.fosun.fc.modules.entity.BaseEntity;
public class TCreepersAssetHandle extends BaseEntity{

 private  long serialVersionUID;

 private  Long id;

 private  BigDecimal assetDisposalAmount;

 private  Date assetDisposalDt;

 private  String assetDisposalOrg;

 private  BigDecimal balance;

 private  Date lastBackDt;

 private  String memo;

 private  String rptNo;

 private  TCreepersAccountBak TCreepersAccountBak;

 private Long id;

public TCreepersAssetHandle() {
}
public Date getAssetDisposalDt(){
    return this.assetDisposalDt;
}


public void setLastBackDt(Date lastBackDt){
    this.lastBackDt = lastBackDt;
}


public String getAssetDisposalOrg(){
    return this.assetDisposalOrg;
}


public void setAssetDisposalAmount(BigDecimal assetDisposalAmount){
    this.assetDisposalAmount = assetDisposalAmount;
}


public Long getId(){
    return this.id;
}


public Date getLastBackDt(){
    return this.lastBackDt;
}


public void setTCreepersAccountBak(TCreepersAccountBak TCreepersAccountBak){
    this.TCreepersAccountBak = TCreepersAccountBak;
}


public void setMemo(String memo){
    this.memo = memo;
}


public void setAssetDisposalOrg(String assetDisposalOrg){
    this.assetDisposalOrg = assetDisposalOrg;
}


public String getMemo(){
    return this.memo;
}


public BigDecimal getBalance(){
    return this.balance;
}


public void setRptNo(String rptNo){
    this.rptNo = rptNo;
}


public TCreepersAccountBak getTCreepersAccountBak(){
    return this.TCreepersAccountBak;
}


public void setId(Long id){
    this.id = id;
}


public String getRptNo(){
    return this.rptNo;
}


public BigDecimal getAssetDisposalAmount(){
    return this.assetDisposalAmount;
}


public void setBalance(BigDecimal balance){
    this.balance = balance;
}


public void setAssetDisposalDt(Date assetDisposalDt){
    this.assetDisposalDt = assetDisposalDt;
}


}