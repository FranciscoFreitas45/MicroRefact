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
import com.fosun.fc.projects.creepers.Request.TCreepersAccountBakRequest;
import com.fosun.fc.projects.creepers.Request.Impl.TCreepersAccountBakRequestImpl;
import com.fosun.fc.projects.creepers.DTO.TCreepersAccountBak;
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


public String getAssetDisposalOrg(){
    return this.assetDisposalOrg;
}


public Long getId(){
    return this.id;
}


public Date getLastBackDt(){
    return this.lastBackDt;
}


public String getMemo(){
    return this.memo;
}


public BigDecimal getBalance(){
    return this.balance;
}


public TCreepersAccountBak getTCreepersAccountBak(){
  this.TCreepersAccountBak = tcreepersaccountbakrequest.getTCreepersAccountBak(this.id);
return this.TCreepersAccountBak;
}


public String getRptNo(){
    return this.rptNo;
}


public BigDecimal getAssetDisposalAmount(){
    return this.assetDisposalAmount;
}


}