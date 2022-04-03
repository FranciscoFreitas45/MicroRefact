package org.jugbd.mnet.DTO;
 import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.Date;
public class Investigation extends PersistentObjectimplements Auditable{

 private  Long id;

 private  Long version;

 private  BloodCbc bloodCbc;

 private  CultureAndSensitivity bloodCs;

 private  String rbs;

 private  Date dateRbs;

 private  CultureAndSensitivity woundCs;

 private  String serumCreatinine;

 private  Date dateSerumCreatinine;

 private  String serumAlbumin;

 private  Date dateSerumAlbumin;

 private  String sTotalProtein;

 private  Date dateSTotalProtein;

 private  String albuminGlobulinRatio;

 private  Date dateAlbuminGlobulinRatio;

 private  Electrolyte electrolyte;

 private  String sgpt;

 private  Date dateSgpt;

 private  String alphos;

 private  Date dateAlphos;

 private  String aptt;

 private  Date dateAptt;

 private  PT pt;

 private  String fdp;

 private  Date dateFdp;

 private  String dDimer;

 private  Date dateDDimer;

 private  String cReactiveProtein;

 private  Date dateCReactiveProtein;

 private  String xRayUsg;

 private  Date dateOfXRayUsg;

 private  String mriMraCtEchoEcg;

 private  Date dateOfMriMraCtEchoEcg;

 private  String duplexScanDopplerStudy;

 private  Date dateOfDuplexScanDopplerStudy;

 private  String fnacHistopathology;

 private  Date dateOfFnacHistopathology;

 private  String otherInvestigation;

 private  Register register;


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
    return register;
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
    this.register = register;
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