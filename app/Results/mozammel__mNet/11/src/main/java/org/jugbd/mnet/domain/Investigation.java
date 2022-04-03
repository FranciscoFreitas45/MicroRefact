package org.jugbd.mnet.domain;
 import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.Date;
import org.jugbd.mnet.Request.RegisterRequest;
import org.jugbd.mnet.Request.Impl.RegisterRequestImpl;
import org.jugbd.mnet.DTO.Register;
@Entity
public class Investigation extends PersistentObjectimplements Auditable{

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  Long id;

@Version
 private  Long version;

@Valid
@Embedded
@AttributeOverrides({ @AttributeOverride(name = "comment", column = @Column(name = "comment_blood_cbc")), @AttributeOverride(name = "dateOfInvestigation", column = @Column(name = "date_inv_blood_cbc")) })
 private  BloodCbc bloodCbc;

@Valid
@Embedded
@AttributeOverrides({ @AttributeOverride(name = "nameOfOrganism", column = @Column(name = "blood_cbc_name_of_organism")), @AttributeOverride(name = "sensitiveAntibiotic", column = @Column(name = "blood_cbc_sensitive_antibiotic")), @AttributeOverride(name = "comment", column = @Column(name = "comment_blood_cs")), @AttributeOverride(name = "dateOfInvestigation", column = @Column(name = "date_inv_blood_cs")) })
 private  CultureAndSensitivity bloodCs;

@Size(max = 100)
 private  String rbs;

@Temporal(TemporalType.DATE)
@DateTimeFormat(pattern = "dd/MM/yyyy")
 private  Date dateRbs;

@Valid
@Embedded
@AttributeOverrides({ @AttributeOverride(name = "nameOfOrganism", column = @Column(name = "wound_cs_name_of_organism")), @AttributeOverride(name = "sensitiveAntibiotic", column = @Column(name = "wound_cs_sensitive_antibiotic")), @AttributeOverride(name = "comment", column = @Column(name = "comment_wound_cs")), @AttributeOverride(name = "dateOfInvestigation", column = @Column(name = "date_inv_wound_cs")) })
 private  CultureAndSensitivity woundCs;

@Size(max = 100)
 private  String serumCreatinine;

@Temporal(TemporalType.DATE)
@DateTimeFormat(pattern = "dd/MM/yyyy")
 private  Date dateSerumCreatinine;

@Size(max = 100)
 private  String serumAlbumin;

@Temporal(TemporalType.DATE)
@DateTimeFormat(pattern = "dd/MM/yyyy")
 private  Date dateSerumAlbumin;

@Size(max = 100)
 private  String sTotalProtein;

@Temporal(TemporalType.DATE)
@DateTimeFormat(pattern = "dd/MM/yyyy")
 private  Date dateSTotalProtein;

@Size(max = 100)
 private  String albuminGlobulinRatio;

@Temporal(TemporalType.DATE)
@DateTimeFormat(pattern = "dd/MM/yyyy")
 private  Date dateAlbuminGlobulinRatio;

@AttributeOverrides({ @AttributeOverride(name = "comment", column = @Column(name = "comment_electrolyte")), @AttributeOverride(name = "dateOfInvestigation", column = @Column(name = "date_inv_electrolyte")) })
 private  Electrolyte electrolyte;

@Size(max = 100)
 private  String sgpt;

@Temporal(TemporalType.DATE)
@DateTimeFormat(pattern = "dd/MM/yyyy")
 private  Date dateSgpt;

@Size(max = 100)
 private  String alphos;

@Temporal(TemporalType.DATE)
@DateTimeFormat(pattern = "dd/MM/yyyy")
 private  Date dateAlphos;

@Size(max = 100)
 private  String aptt;

@Temporal(TemporalType.DATE)
@DateTimeFormat(pattern = "dd/MM/yyyy")
 private  Date dateAptt;

@Valid
@Embedded
@AttributeOverrides({ @AttributeOverride(name = "comment", column = @Column(name = "comment_pt")), @AttributeOverride(name = "dateOfInvestigation", column = @Column(name = "date_inv_pt")) })
 private  PT pt;

@Size(max = 100)
 private  String fdp;

@Temporal(TemporalType.DATE)
@DateTimeFormat(pattern = "dd/MM/yyyy")
 private  Date dateFdp;

@Size(max = 100)
 private  String dDimer;

@Temporal(TemporalType.DATE)
@DateTimeFormat(pattern = "dd/MM/yyyy")
 private  Date dateDDimer;

@Size(max = 100)
 private  String cReactiveProtein;

@Temporal(TemporalType.DATE)
@DateTimeFormat(pattern = "dd/MM/yyyy")
 private  Date dateCReactiveProtein;

@Size(max = 1000)
 private  String xRayUsg;

@Temporal(TemporalType.DATE)
@DateTimeFormat(pattern = "dd/MM/yyyy")
 private  Date dateOfXRayUsg;

@Size(max = 1000)
 private  String mriMraCtEchoEcg;

@Temporal(TemporalType.DATE)
@DateTimeFormat(pattern = "dd/MM/yyyy")
 private  Date dateOfMriMraCtEchoEcg;

@Size(max = 1000)
 private  String duplexScanDopplerStudy;

@Temporal(TemporalType.DATE)
@DateTimeFormat(pattern = "dd/MM/yyyy")
 private  Date dateOfDuplexScanDopplerStudy;

@Size(max = 1000)
 private  String fnacHistopathology;

@Temporal(TemporalType.DATE)
@DateTimeFormat(pattern = "dd/MM/yyyy")
 private  Date dateOfFnacHistopathology;

@Size(max = 1000)
 private  String otherInvestigation;

@Transient
 private  Register register;

@Column(name = "id")
 private Long id;

@Transient
 private RegisterRequest registerrequest = new RegisterRequestImpl();;


public String getMriMraCtEchoEcg(){
    return mriMraCtEchoEcg;
}


public void setPt(PT pt){
    this.pt = pt;
}


public void setRbs(String rbs){
    this.rbs = rbs;
}


public String getSerumAlbumin(){
    return serumAlbumin;
}


public void setDateOfFnacHistopathology(Date dateOfFnacHistopathology){
    this.dateOfFnacHistopathology = dateOfFnacHistopathology;
}


public String getRbs(){
    return rbs;
}


public Date getDateAlphos(){
    return dateAlphos;
}


public void setSgpt(String sgpt){
    this.sgpt = sgpt;
}


public String getsTotalProtein(){
    return sTotalProtein;
}


public String getOtherInvestigation(){
    return otherInvestigation;
}


public Date getDateOfXRayUsg(){
    return dateOfXRayUsg;
}


public void setBloodCbc(BloodCbc bloodCbc){
    this.bloodCbc = bloodCbc;
}


public String getAptt(){
    return aptt;
}


public void setId(Long id){
    this.id = id;
}


public String getSerumCreatinine(){
    return serumCreatinine;
}


public void setxRayUsg(String xRayUsg){
    this.xRayUsg = xRayUsg;
}


public Date getDateAptt(){
    return dateAptt;
}


public PT getPt(){
    return pt;
}


public void setDateSerumAlbumin(Date dateSerumAlbumin){
    this.dateSerumAlbumin = dateSerumAlbumin;
}


public void setVersion(Long version){
    this.version = version;
}


public void setDateSTotalProtein(Date dateSTotalProtein){
    this.dateSTotalProtein = dateSTotalProtein;
}


public void setDateAlphos(Date dateAlphos){
    this.dateAlphos = dateAlphos;
}


public void setMriMraCtEchoEcg(String mriMraCtEchoEcg){
    this.mriMraCtEchoEcg = mriMraCtEchoEcg;
}


public Date getDateOfDuplexScanDopplerStudy(){
    return dateOfDuplexScanDopplerStudy;
}


public void setOtherInvestigation(String otherInvestigation){
    this.otherInvestigation = otherInvestigation;
}


public Date getDateSgpt(){
    return dateSgpt;
}


public Date getDateRbs(){
    return dateRbs;
}


public Date getDateAlbuminGlobulinRatio(){
    return dateAlbuminGlobulinRatio;
}


public Register getRegister(){
  this.register = registerrequest.getRegister(this.id);
return this.register;
}


public String getcReactiveProtein(){
    return cReactiveProtein;
}


public Date getDateSerumAlbumin(){
    return dateSerumAlbumin;
}


public void setFdp(String fdp){
    this.fdp = fdp;
}


public String getDuplexScanDopplerStudy(){
    return duplexScanDopplerStudy;
}


public void setBloodCs(CultureAndSensitivity bloodCs){
    this.bloodCs = bloodCs;
}


public String getFdp(){
    return fdp;
}


public void setDateRbs(Date dateRbs){
    this.dateRbs = dateRbs;
}


public void setAptt(String aptt){
    this.aptt = aptt;
}


public void setDateOfXRayUsg(Date dateOfXRayUsg){
    this.dateOfXRayUsg = dateOfXRayUsg;
}


public void setElectrolyte(Electrolyte electrolyte){
    this.electrolyte = electrolyte;
}


public void setFnacHistopathology(String fnacHistopathology){
    this.fnacHistopathology = fnacHistopathology;
}


public String getAlphos(){
    return alphos;
}


public Date getDateDDimer(){
    return dateDDimer;
}


public void setDateOfDuplexScanDopplerStudy(Date dateOfDuplexScanDopplerStudy){
    this.dateOfDuplexScanDopplerStudy = dateOfDuplexScanDopplerStudy;
}


public String getdDimer(){
    return dDimer;
}


public void setcReactiveProtein(String cReactiveProtein){
    this.cReactiveProtein = cReactiveProtein;
}


public void setSerumAlbumin(String serumAlbumin){
    this.serumAlbumin = serumAlbumin;
}


public Date getDateCReactiveProtein(){
    return dateCReactiveProtein;
}


public void setDateSgpt(Date dateSgpt){
    this.dateSgpt = dateSgpt;
}


public void setDateOfMriMraCtEchoEcg(Date dateOfMriMraCtEchoEcg){
    this.dateOfMriMraCtEchoEcg = dateOfMriMraCtEchoEcg;
}


@Override
public Long getId(){
    return id;
}


public String getSgpt(){
    return sgpt;
}


public void setRegister(Register register){
 registerrequest.setRegister(register,this.id);
}



public Date getDateSerumCreatinine(){
    return dateSerumCreatinine;
}


public CultureAndSensitivity getWoundCs(){
    return woundCs;
}


public Date getDateOfFnacHistopathology(){
    return dateOfFnacHistopathology;
}


public void setAlbuminGlobulinRatio(String albuminGlobulinRatio){
    this.albuminGlobulinRatio = albuminGlobulinRatio;
}


public void setDateAptt(Date dateAptt){
    this.dateAptt = dateAptt;
}


public void setDuplexScanDopplerStudy(String duplexScanDopplerStudy){
    this.duplexScanDopplerStudy = duplexScanDopplerStudy;
}


public void setDateDDimer(Date dateDDimer){
    this.dateDDimer = dateDDimer;
}


public CultureAndSensitivity getBloodCs(){
    return bloodCs;
}


public Date getDateSTotalProtein(){
    return dateSTotalProtein;
}


public Electrolyte getElectrolyte(){
    return electrolyte;
}


public Long getVersion(){
    return version;
}


public Date getDateOfMriMraCtEchoEcg(){
    return dateOfMriMraCtEchoEcg;
}


public BloodCbc getBloodCbc(){
    return bloodCbc;
}


public void setDateAlbuminGlobulinRatio(Date dateAlbuminGlobulinRatio){
    this.dateAlbuminGlobulinRatio = dateAlbuminGlobulinRatio;
}


public void setDateFdp(Date dateFdp){
    this.dateFdp = dateFdp;
}


public void setdDimer(String dDimer){
    this.dDimer = dDimer;
}


public String getFnacHistopathology(){
    return fnacHistopathology;
}


public void setSerumCreatinine(String serumCreatinine){
    this.serumCreatinine = serumCreatinine;
}


public void setAlphos(String alphos){
    this.alphos = alphos;
}


public void setWoundCs(CultureAndSensitivity woundCs){
    this.woundCs = woundCs;
}


public String getAlbuminGlobulinRatio(){
    return albuminGlobulinRatio;
}


public void setDateSerumCreatinine(Date dateSerumCreatinine){
    this.dateSerumCreatinine = dateSerumCreatinine;
}


public void setDateCReactiveProtein(Date dateCReactiveProtein){
    this.dateCReactiveProtein = dateCReactiveProtein;
}


public void setsTotalProtein(String sTotalProtein){
    this.sTotalProtein = sTotalProtein;
}


public Date getDateFdp(){
    return dateFdp;
}


public String getxRayUsg(){
    return xRayUsg;
}


}