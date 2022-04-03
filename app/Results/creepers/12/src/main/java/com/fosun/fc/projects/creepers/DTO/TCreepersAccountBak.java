package com.fosun.fc.projects.creepers.DTO;
 import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.fosun.fc.modules.entity.BaseEntity;
public class TCreepersAccountBak extends BaseEntity{

 private  long serialVersionUID;

 private  Long id;

 private  String rptNo;

 private  String filePath;

 private  String cde;

 private  String memo;

 private  String pwd;

 private  String status;

 private  String usr;

 private  List<TCreepersAssetHandle> TCreepersAssetHandles;

 private  List<TCreepersBasic> TCreepersBasics;

 private  List<TCreepersCcDetail> TCreepersCcDetails;

 private  List<TCreepersCompensatory> TCreepersCompensatories;

 private  List<TCreepersGeneral> TCreepersGenerals;

 private  List<TCreepersGuarantee> TCreepersGuarantees;

 private  List<TCreepersHlDetail> TCreepersHlDetails;

 private  List<TCreepersOlDetail> TCreepersOlDetails;

 private  List<TCreepersPublicCivil> TCreepersPublicCivils;

 private  List<TCreepersPublicEnforcement> TCreepersPublicEnforcements;

 private  List<TCreepersPublicIsp> TCreepersPublicIsps;

 private  List<TCreepersPublicSanction> TCreepersPublicSanctions;

 private  List<TCreepersPublicTax> TCreepersPublicTaxs;

public TCreepersAccountBak() {
}
public List<TCreepersPublicTax> getTCreepersPublicTaxs(){
    return this.TCreepersPublicTaxs;
}


public List<TCreepersBasic> getTCreepersBasics(){
    return this.TCreepersBasics;
}


public String getStatus(){
    return this.status;
}


public List<TCreepersPublicIsp> getTCreepersPublicIsps(){
    return this.TCreepersPublicIsps;
}


public String getPwd(){
    return this.pwd;
}


public List<TCreepersOlDetail> getTCreepersOlDetails(){
    return this.TCreepersOlDetails;
}


public List<TCreepersGeneral> getTCreepersGenerals(){
    return this.TCreepersGenerals;
}


public String getMemo(){
    return this.memo;
}


public String getFilePath(){
    return filePath;
}


public List<TCreepersPublicEnforcement> getTCreepersPublicEnforcements(){
    return this.TCreepersPublicEnforcements;
}


public String getRptNo(){
    return rptNo;
}


public List<TCreepersHlDetail> getTCreepersHlDetails(){
    return this.TCreepersHlDetails;
}


public List<TCreepersGuarantee> getTCreepersGuarantees(){
    return this.TCreepersGuarantees;
}


public List<TCreepersPublicSanction> getTCreepersPublicSanctions(){
    return this.TCreepersPublicSanctions;
}


public Long getId(){
    return this.id;
}


public List<TCreepersAssetHandle> getTCreepersAssetHandles(){
    return this.TCreepersAssetHandles;
}


public String getUsr(){
    return this.usr;
}


public List<TCreepersCcDetail> getTCreepersCcDetails(){
    return this.TCreepersCcDetails;
}


public String getCde(){
    return this.cde;
}


public List<TCreepersPublicCivil> getTCreepersPublicCivils(){
    return this.TCreepersPublicCivils;
}


public List<TCreepersCompensatory> getTCreepersCompensatories(){
    return this.TCreepersCompensatories;
}


}