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
public class TCreepersPublicSanction {

 private  long serialVersionUID;

 private  Long id;

 private  String docNo;

 private  String memo;

 private  Date punishDt;

 private  BigDecimal punishmentAmount;

 private  String punishmentContent;

 private  Date punishmentIssueDt;

 private  String punishmentOrg;

 private  String reconsiderationFlag;

 private  String reconsiderationRslt;

 private  String rptNo;

 private  TCreepersAccountBak TCreepersAccountBak;

 private Long id;

public TCreepersPublicSanction() {
}
public String getPunishmentContent(){
    return this.punishmentContent;
}


public BigDecimal getPunishmentAmount(){
    return this.punishmentAmount;
}


public Long getId(){
    return this.id;
}


public Date getPunishmentIssueDt(){
    return this.punishmentIssueDt;
}


public String getDocNo(){
    return this.docNo;
}


public String getMemo(){
    return this.memo;
}


public Date getPunishDt(){
    return this.punishDt;
}


public String getReconsiderationRslt(){
    return this.reconsiderationRslt;
}


public TCreepersAccountBak getTCreepersAccountBak(){
  this.TCreepersAccountBak = tcreepersaccountbakrequest.getTCreepersAccountBak(this.id);
return this.TCreepersAccountBak;
}


public String getReconsiderationFlag(){
    return this.reconsiderationFlag;
}


public String getRptNo(){
    return this.rptNo;
}


public String getPunishmentOrg(){
    return this.punishmentOrg;
}


}