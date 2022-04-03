package com.fosun.fc.projects.creepers.DTO;
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
public class TCreepersBasic {

 private  long serialVersionUID;

 private  Long id;

 private  String idNo;

 private  String idType;

 private  String maritalStatus;

 private  String memo;

 private  String name;

 private  Date queryDt;

 private  Date rptDt;

 private  String rptNo;

 private  TCreepersAccountBak TCreepersAccountBak;

 private Long id;

public TCreepersBasic() {
}
public String getName(){
    return this.name;
}


public String getIdType(){
    return this.idType;
}


public String getMaritalStatus(){
    return this.maritalStatus;
}


public Date getRptDt(){
    return this.rptDt;
}


public Long getId(){
    return this.id;
}


public String getIdNo(){
    return this.idNo;
}


public String getMemo(){
    return this.memo;
}


public TCreepersAccountBak getTCreepersAccountBak(){
  this.TCreepersAccountBak = tcreepersaccountbakrequest.getTCreepersAccountBak(this.id);
return this.TCreepersAccountBak;
}


public String getRptNo(){
    return this.rptNo;
}


public Date getQueryDt(){
    return this.queryDt;
}


}