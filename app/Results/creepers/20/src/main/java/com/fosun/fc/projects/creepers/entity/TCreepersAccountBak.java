package com.fosun.fc.projects.creepers.entity;
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
import com.fosun.fc.projects.creepers.Request.TCreepersAssetHandleRequest;
import com.fosun.fc.projects.creepers.Request.Impl.TCreepersAssetHandleRequestImpl;
import com.fosun.fc.projects.creepers.DTO.TCreepersAssetHandle;
import com.fosun.fc.projects.creepers.Request.TCreepersBasicRequest;
import com.fosun.fc.projects.creepers.Request.Impl.TCreepersBasicRequestImpl;
import com.fosun.fc.projects.creepers.DTO.TCreepersBasic;
import com.fosun.fc.projects.creepers.Request.TCreepersCcDetailRequest;
import com.fosun.fc.projects.creepers.Request.Impl.TCreepersCcDetailRequestImpl;
import com.fosun.fc.projects.creepers.DTO.TCreepersCcDetail;
import com.fosun.fc.projects.creepers.Request.TCreepersCompensatoryRequest;
import com.fosun.fc.projects.creepers.Request.Impl.TCreepersCompensatoryRequestImpl;
import com.fosun.fc.projects.creepers.DTO.TCreepersCompensatory;
import com.fosun.fc.projects.creepers.Request.TCreepersGuaranteeRequest;
import com.fosun.fc.projects.creepers.Request.Impl.TCreepersGuaranteeRequestImpl;
import com.fosun.fc.projects.creepers.DTO.TCreepersGuarantee;
import com.fosun.fc.projects.creepers.Request.TCreepersHlDetailRequest;
import com.fosun.fc.projects.creepers.Request.Impl.TCreepersHlDetailRequestImpl;
import com.fosun.fc.projects.creepers.DTO.TCreepersHlDetail;
import com.fosun.fc.projects.creepers.Request.TCreepersOlDetailRequest;
import com.fosun.fc.projects.creepers.Request.Impl.TCreepersOlDetailRequestImpl;
import com.fosun.fc.projects.creepers.DTO.TCreepersOlDetail;
import com.fosun.fc.projects.creepers.Request.TCreepersPublicCivilRequest;
import com.fosun.fc.projects.creepers.Request.Impl.TCreepersPublicCivilRequestImpl;
import com.fosun.fc.projects.creepers.DTO.TCreepersPublicCivil;
import com.fosun.fc.projects.creepers.Request.TCreepersPublicEnforcementRequest;
import com.fosun.fc.projects.creepers.Request.Impl.TCreepersPublicEnforcementRequestImpl;
import com.fosun.fc.projects.creepers.DTO.TCreepersPublicEnforcement;
import com.fosun.fc.projects.creepers.Request.TCreepersPublicIspRequest;
import com.fosun.fc.projects.creepers.Request.Impl.TCreepersPublicIspRequestImpl;
import com.fosun.fc.projects.creepers.DTO.TCreepersPublicIsp;
import com.fosun.fc.projects.creepers.Request.TCreepersPublicSanctionRequest;
import com.fosun.fc.projects.creepers.Request.Impl.TCreepersPublicSanctionRequestImpl;
import com.fosun.fc.projects.creepers.DTO.TCreepersPublicSanction;
import com.fosun.fc.projects.creepers.Request.TCreepersPublicTaxRequest;
import com.fosun.fc.projects.creepers.Request.Impl.TCreepersPublicTaxRequestImpl;
import com.fosun.fc.projects.creepers.DTO.TCreepersPublicTax;
@Entity
@Table(name = "T_CREEPERS_ACCOUNT_BAK")
@NamedQuery(name = "TCreepersAccountBak.findAll", query = "SELECT t FROM TCreepersAccountBak t")
public class TCreepersAccountBak extends BaseEntity{

 private  long serialVersionUID;

@Id
@SequenceGenerator(name = "T_CREEPERS_ACCOUNT_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_ACCOUNT")
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_ACCOUNT_ID_GENERATOR")
 private  Long id;

@Column(name = "RPT_NO")
 private  String rptNo;

 private  String filePath;

 private  String cde;

 private  String memo;

 private  String pwd;

 private  String status;

 private  String usr;

@Transient
 private  List<TCreepersAssetHandle> TCreepersAssetHandles;

@Transient
 private  List<TCreepersBasic> TCreepersBasics;

@Transient
 private  List<TCreepersCcDetail> TCreepersCcDetails;

@Transient
 private  List<TCreepersCompensatory> TCreepersCompensatories;

@OneToMany(mappedBy = "TCreepersAccountBak")
 private  List<TCreepersGeneral> TCreepersGenerals;

@Transient
 private  List<TCreepersGuarantee> TCreepersGuarantees;

@Transient
 private  List<TCreepersHlDetail> TCreepersHlDetails;

@Transient
 private  List<TCreepersOlDetail> TCreepersOlDetails;

@Transient
 private  List<TCreepersPublicCivil> TCreepersPublicCivils;

@Transient
 private  List<TCreepersPublicEnforcement> TCreepersPublicEnforcements;

@Transient
 private  List<TCreepersPublicIsp> TCreepersPublicIsps;

@Transient
 private  List<TCreepersPublicSanction> TCreepersPublicSanctions;

@Transient
 private  List<TCreepersPublicTax> TCreepersPublicTaxs;

@Transient
 private TCreepersAssetHandleRequest tcreepersassethandlerequest = new TCreepersAssetHandleRequestImpl();;

@Transient
 private TCreepersBasicRequest tcreepersbasicrequest = new TCreepersBasicRequestImpl();;

@Transient
 private TCreepersCcDetailRequest tcreepersccdetailrequest = new TCreepersCcDetailRequestImpl();;

@Transient
 private TCreepersCompensatoryRequest tcreeperscompensatoryrequest = new TCreepersCompensatoryRequestImpl();;

@Transient
 private TCreepersGuaranteeRequest tcreepersguaranteerequest = new TCreepersGuaranteeRequestImpl();;

@Transient
 private TCreepersHlDetailRequest tcreepershldetailrequest = new TCreepersHlDetailRequestImpl();;

@Transient
 private TCreepersOlDetailRequest tcreepersoldetailrequest = new TCreepersOlDetailRequestImpl();;

@Transient
 private TCreepersPublicCivilRequest tcreeperspubliccivilrequest = new TCreepersPublicCivilRequestImpl();;

@Transient
 private TCreepersPublicEnforcementRequest tcreeperspublicenforcementrequest = new TCreepersPublicEnforcementRequestImpl();;

@Transient
 private TCreepersPublicIspRequest tcreeperspublicisprequest = new TCreepersPublicIspRequestImpl();;

@Transient
 private TCreepersPublicSanctionRequest tcreeperspublicsanctionrequest = new TCreepersPublicSanctionRequestImpl();;

@Transient
 private TCreepersPublicTaxRequest tcreeperspublictaxrequest = new TCreepersPublicTaxRequestImpl();;

public TCreepersAccountBak() {
}
public void setTCreepersCcDetails(List<TCreepersCcDetail> TCreepersCcDetails){
 tcreepersccdetailrequest.setTCreepersCcDetails(TCreepersCcDetails,this.id);
}



public void setTCreepersPublicSanctions(List<TCreepersPublicSanction> TCreepersPublicSanctions){
 tcreeperspublicsanctionrequest.setTCreepersPublicSanctions(TCreepersPublicSanctions,this.id);
}



public TCreepersPublicIsp addTCreepersPublicIsp(TCreepersPublicIsp TCreepersPublicIsp){
  this.TCreepersPublicIsps = tcreeperspublicisprequest.addTCreepersPublicIsp(TCreepersPublicIsp,this.id);
return this.TCreepersPublicIsps;
}


public List<TCreepersPublicTax> getTCreepersPublicTaxs(){
  this.TCreepersPublicTaxs = tcreeperspublictaxrequest.getTCreepersPublicTaxs(this.id);
return this.TCreepersPublicTaxs;
}


public List<TCreepersBasic> getTCreepersBasics(){
  this.TCreepersBasics = tcreepersbasicrequest.getTCreepersBasics(this.id);
return this.TCreepersBasics;
}


public String getStatus(){
    return this.status;
}


public List<TCreepersPublicIsp> getTCreepersPublicIsps(){
  this.TCreepersPublicIsps = tcreeperspublicisprequest.getTCreepersPublicIsps(this.id);
return this.TCreepersPublicIsps;
}


public String getPwd(){
    return this.pwd;
}


public void setTCreepersCompensatories(List<TCreepersCompensatory> TCreepersCompensatories){
 tcreeperscompensatoryrequest.setTCreepersCompensatories(TCreepersCompensatories,this.id);
}



public void setTCreepersGuarantees(List<TCreepersGuarantee> TCreepersGuarantees){
 tcreepersguaranteerequest.setTCreepersGuarantees(TCreepersGuarantees,this.id);
}



public TCreepersPublicCivil addTCreepersPublicCivil(TCreepersPublicCivil TCreepersPublicCivil){
  this.TCreepersPublicCivils = tcreeperspubliccivilrequest.addTCreepersPublicCivil(TCreepersPublicCivil,this.id);
return this.TCreepersPublicCivils;
}


public TCreepersGeneral removeTCreepersGeneral(TCreepersGeneral TCreepersGeneral){
    getTCreepersGenerals().remove(TCreepersGeneral);
    TCreepersGeneral.setTCreepersAccountBak(null);
    return TCreepersGeneral;
}


public void setTCreepersPublicEnforcements(List<TCreepersPublicEnforcement> TCreepersPublicEnforcements){
 tcreeperspublicenforcementrequest.setTCreepersPublicEnforcements(TCreepersPublicEnforcements,this.id);
}



public void setId(Long id){
    this.id = id;
}


public TCreepersHlDetail addTCreepersHlDetail(TCreepersHlDetail TCreepersHlDetail){
  this.TCreepersHlDetails = tcreepershldetailrequest.addTCreepersHlDetail(TCreepersHlDetail,this.id);
return this.TCreepersHlDetails;
}


public List<TCreepersOlDetail> getTCreepersOlDetails(){
  this.TCreepersOlDetails = tcreepersoldetailrequest.getTCreepersOlDetails(this.id);
return this.TCreepersOlDetails;
}


public TCreepersBasic addTCreepersBasic(TCreepersBasic TCreepersBasic){
  this.TCreepersBasics = tcreepersbasicrequest.addTCreepersBasic(TCreepersBasic,this.id);
return this.TCreepersBasics;
}


public TCreepersCcDetail removeTCreepersCcDetail(TCreepersCcDetail TCreepersCcDetail){
  this.TCreepersCcDetails = tcreepersccdetailrequest.removeTCreepersCcDetail(TCreepersCcDetail,this.id);
return this.TCreepersCcDetails;
}


public void setTCreepersBasics(List<TCreepersBasic> TCreepersBasics){
 tcreepersbasicrequest.setTCreepersBasics(TCreepersBasics,this.id);
}



public List<TCreepersGeneral> getTCreepersGenerals(){
    return this.TCreepersGenerals;
}


public void setTCreepersPublicTaxs(List<TCreepersPublicTax> TCreepersPublicTaxs){
 tcreeperspublictaxrequest.setTCreepersPublicTaxs(TCreepersPublicTaxs,this.id);
}



public TCreepersPublicTax addTCreepersPublicTax(TCreepersPublicTax TCreepersPublicTax){
  this.TCreepersPublicTaxs = tcreeperspublictaxrequest.addTCreepersPublicTax(TCreepersPublicTax,this.id);
return this.TCreepersPublicTaxs;
}


public String getMemo(){
    return this.memo;
}


public void setRptNo(String rptNo){
    this.rptNo = rptNo;
}


public TCreepersGuarantee addTCreepersGuarantee(TCreepersGuarantee TCreepersGuarantee){
  this.TCreepersGuarantees = tcreepersguaranteerequest.addTCreepersGuarantee(TCreepersGuarantee,this.id);
return this.TCreepersGuarantees;
}


public String getFilePath(){
    return filePath;
}


public List<TCreepersPublicEnforcement> getTCreepersPublicEnforcements(){
  this.TCreepersPublicEnforcements = tcreeperspublicenforcementrequest.getTCreepersPublicEnforcements(this.id);
return this.TCreepersPublicEnforcements;
}


public TCreepersPublicIsp removeTCreepersPublicIsp(TCreepersPublicIsp TCreepersPublicIsp){
  this.TCreepersPublicIsps = tcreeperspublicisprequest.removeTCreepersPublicIsp(TCreepersPublicIsp,this.id);
return this.TCreepersPublicIsps;
}


public String getRptNo(){
    return rptNo;
}


public List<TCreepersHlDetail> getTCreepersHlDetails(){
  this.TCreepersHlDetails = tcreepershldetailrequest.getTCreepersHlDetails(this.id);
return this.TCreepersHlDetails;
}


public TCreepersOlDetail removeTCreepersOlDetail(TCreepersOlDetail TCreepersOlDetail){
  this.TCreepersOlDetails = tcreepersoldetailrequest.removeTCreepersOlDetail(TCreepersOlDetail,this.id);
return this.TCreepersOlDetails;
}


public TCreepersCompensatory removeTCreepersCompensatory(TCreepersCompensatory TCreepersCompensatory){
  this.TCreepersCompensatories = tcreeperscompensatoryrequest.removeTCreepersCompensatory(TCreepersCompensatory,this.id);
return this.TCreepersCompensatories;
}


public void setTCreepersGenerals(List<TCreepersGeneral> TCreepersGenerals){
    this.TCreepersGenerals = TCreepersGenerals;
}


public TCreepersCompensatory addTCreepersCompensatory(TCreepersCompensatory TCreepersCompensatory){
  this.TCreepersCompensatories = tcreeperscompensatoryrequest.addTCreepersCompensatory(TCreepersCompensatory,this.id);
return this.TCreepersCompensatories;
}


public List<TCreepersGuarantee> getTCreepersGuarantees(){
  this.TCreepersGuarantees = tcreepersguaranteerequest.getTCreepersGuarantees(this.id);
return this.TCreepersGuarantees;
}


public List<TCreepersPublicSanction> getTCreepersPublicSanctions(){
  this.TCreepersPublicSanctions = tcreeperspublicsanctionrequest.getTCreepersPublicSanctions(this.id);
return this.TCreepersPublicSanctions;
}


public TCreepersPublicCivil removeTCreepersPublicCivil(TCreepersPublicCivil TCreepersPublicCivil){
  this.TCreepersPublicCivils = tcreeperspubliccivilrequest.removeTCreepersPublicCivil(TCreepersPublicCivil,this.id);
return this.TCreepersPublicCivils;
}


public void setTCreepersOlDetails(List<TCreepersOlDetail> TCreepersOlDetails){
 tcreepersoldetailrequest.setTCreepersOlDetails(TCreepersOlDetails,this.id);
}



public TCreepersGeneral addTCreepersGeneral(TCreepersGeneral TCreepersGeneral){
    getTCreepersGenerals().add(TCreepersGeneral);
    TCreepersGeneral.setTCreepersAccountBak(this);
    return TCreepersGeneral;
}


public Long getId(){
    return this.id;
}


public void setTCreepersAssetHandles(List<TCreepersAssetHandle> TCreepersAssetHandles){
 tcreepersassethandlerequest.setTCreepersAssetHandles(TCreepersAssetHandles,this.id);
}



public void setUsr(String usr){
    this.usr = usr;
}


public void setTCreepersPublicIsps(List<TCreepersPublicIsp> TCreepersPublicIsps){
 tcreeperspublicisprequest.setTCreepersPublicIsps(TCreepersPublicIsps,this.id);
}



public TCreepersPublicTax removeTCreepersPublicTax(TCreepersPublicTax TCreepersPublicTax){
  this.TCreepersPublicTaxs = tcreeperspublictaxrequest.removeTCreepersPublicTax(TCreepersPublicTax,this.id);
return this.TCreepersPublicTaxs;
}


public List<TCreepersAssetHandle> getTCreepersAssetHandles(){
  this.TCreepersAssetHandles = tcreepersassethandlerequest.getTCreepersAssetHandles(this.id);
return this.TCreepersAssetHandles;
}


public String getUsr(){
    return this.usr;
}


public void setCde(String cde){
    this.cde = cde;
}


public List<TCreepersCcDetail> getTCreepersCcDetails(){
  this.TCreepersCcDetails = tcreepersccdetailrequest.getTCreepersCcDetails(this.id);
return this.TCreepersCcDetails;
}


public TCreepersAssetHandle addTCreepersAssetHandle(TCreepersAssetHandle TCreepersAssetHandle){
  this.TCreepersAssetHandles = tcreepersassethandlerequest.addTCreepersAssetHandle(TCreepersAssetHandle,this.id);
return this.TCreepersAssetHandles;
}


public String getCde(){
    return this.cde;
}


public TCreepersOlDetail addTCreepersOlDetail(TCreepersOlDetail TCreepersOlDetail){
  this.TCreepersOlDetails = tcreepersoldetailrequest.addTCreepersOlDetail(TCreepersOlDetail,this.id);
return this.TCreepersOlDetails;
}


public List<TCreepersPublicCivil> getTCreepersPublicCivils(){
  this.TCreepersPublicCivils = tcreeperspubliccivilrequest.getTCreepersPublicCivils(this.id);
return this.TCreepersPublicCivils;
}


public TCreepersPublicEnforcement removeTCreepersPublicEnforcement(TCreepersPublicEnforcement TCreepersPublicEnforcement){
  this.TCreepersPublicEnforcements = tcreeperspublicenforcementrequest.removeTCreepersPublicEnforcement(TCreepersPublicEnforcement,this.id);
return this.TCreepersPublicEnforcements;
}


public TCreepersPublicSanction removeTCreepersPublicSanction(TCreepersPublicSanction TCreepersPublicSanction){
  this.TCreepersPublicSanctions = tcreeperspublicsanctionrequest.removeTCreepersPublicSanction(TCreepersPublicSanction,this.id);
return this.TCreepersPublicSanctions;
}


public void setTCreepersHlDetails(List<TCreepersHlDetail> TCreepersHlDetails){
 tcreepershldetailrequest.setTCreepersHlDetails(TCreepersHlDetails,this.id);
}



public void setStatus(String status){
    this.status = status;
}


public TCreepersBasic removeTCreepersBasic(TCreepersBasic TCreepersBasic){
  this.TCreepersBasics = tcreepersbasicrequest.removeTCreepersBasic(TCreepersBasic,this.id);
return this.TCreepersBasics;
}


public TCreepersCcDetail addTCreepersCcDetail(TCreepersCcDetail TCreepersCcDetail){
  this.TCreepersCcDetails = tcreepersccdetailrequest.addTCreepersCcDetail(TCreepersCcDetail,this.id);
return this.TCreepersCcDetails;
}


public TCreepersGuarantee removeTCreepersGuarantee(TCreepersGuarantee TCreepersGuarantee){
  this.TCreepersGuarantees = tcreepersguaranteerequest.removeTCreepersGuarantee(TCreepersGuarantee,this.id);
return this.TCreepersGuarantees;
}


public TCreepersPublicEnforcement addTCreepersPublicEnforcement(TCreepersPublicEnforcement TCreepersPublicEnforcement){
  this.TCreepersPublicEnforcements = tcreeperspublicenforcementrequest.addTCreepersPublicEnforcement(TCreepersPublicEnforcement,this.id);
return this.TCreepersPublicEnforcements;
}


public void setMemo(String memo){
    this.memo = memo;
}


public List<TCreepersCompensatory> getTCreepersCompensatories(){
  this.TCreepersCompensatories = tcreeperscompensatoryrequest.getTCreepersCompensatories(this.id);
return this.TCreepersCompensatories;
}


public void setPwd(String pwd){
    this.pwd = pwd;
}


public TCreepersAssetHandle removeTCreepersAssetHandle(TCreepersAssetHandle TCreepersAssetHandle){
  this.TCreepersAssetHandles = tcreepersassethandlerequest.removeTCreepersAssetHandle(TCreepersAssetHandle,this.id);
return this.TCreepersAssetHandles;
}


public void setFilePath(String filePath){
    this.filePath = filePath;
}


public void setTCreepersPublicCivils(List<TCreepersPublicCivil> TCreepersPublicCivils){
 tcreeperspubliccivilrequest.setTCreepersPublicCivils(TCreepersPublicCivils,this.id);
}



public TCreepersHlDetail removeTCreepersHlDetail(TCreepersHlDetail TCreepersHlDetail){
  this.TCreepersHlDetails = tcreepershldetailrequest.removeTCreepersHlDetail(TCreepersHlDetail,this.id);
return this.TCreepersHlDetails;
}


public TCreepersPublicSanction addTCreepersPublicSanction(TCreepersPublicSanction TCreepersPublicSanction){
  this.TCreepersPublicSanctions = tcreeperspublicsanctionrequest.addTCreepersPublicSanction(TCreepersPublicSanction,this.id);
return this.TCreepersPublicSanctions;
}


}