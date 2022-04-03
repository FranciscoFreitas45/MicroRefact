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

public TCreepersPublicSanction() {
}
public String getPunishmentContent(){
    return this.punishmentContent;
}


public void setReconsiderationRslt(String reconsiderationRslt){
    this.reconsiderationRslt = reconsiderationRslt;
}


public void setPunishmentContent(String punishmentContent){
    this.punishmentContent = punishmentContent;
}


public void setPunishDt(Date punishDt){
    this.punishDt = punishDt;
}


public BigDecimal getPunishmentAmount(){
    return this.punishmentAmount;
}


public Long getId(){
    return this.id;
}


public void setPunishmentAmount(BigDecimal punishmentAmount){
    this.punishmentAmount = punishmentAmount;
}


public void setTCreepersAccountBak(TCreepersAccountBak TCreepersAccountBak){
    this.TCreepersAccountBak = TCreepersAccountBak;
}


public Date getPunishmentIssueDt(){
    return this.punishmentIssueDt;
}


public String getDocNo(){
    return this.docNo;
}


public void setMemo(String memo){
    this.memo = memo;
}


public void setPunishmentIssueDt(Date punishmentIssueDt){
    this.punishmentIssueDt = punishmentIssueDt;
}


public String getMemo(){
    return this.memo;
}


public Date getPunishDt(){
    return this.punishDt;
}


public void setRptNo(String rptNo){
    this.rptNo = rptNo;
}


public void setPunishmentOrg(String punishmentOrg){
    this.punishmentOrg = punishmentOrg;
}


public void setDocNo(String docNo){
    this.docNo = docNo;
}


public String getReconsiderationRslt(){
    return this.reconsiderationRslt;
}


public TCreepersAccountBak getTCreepersAccountBak(){
    return this.TCreepersAccountBak;
}


public void setId(Long id){
    this.id = id;
}


public String getReconsiderationFlag(){
    return this.reconsiderationFlag;
}


public void setReconsiderationFlag(String reconsiderationFlag){
    this.reconsiderationFlag = reconsiderationFlag;
}


public String getRptNo(){
    return this.rptNo;
}


public String getPunishmentOrg(){
    return this.punishmentOrg;
}


}