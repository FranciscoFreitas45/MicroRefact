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


public String getSerumAlbumin(){
    return serumAlbumin;
}


public String getRbs(){
    return rbs;
}


public Date getDateAlphos(){
    return dateAlphos;
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


public String getAptt(){
    return aptt;
}


public String getSerumCreatinine(){
    return serumCreatinine;
}


public Date getDateAptt(){
    return dateAptt;
}


public PT getPt(){
    return pt;
}


public Date getDateOfDuplexScanDopplerStudy(){
    return dateOfDuplexScanDopplerStudy;
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


public String getDuplexScanDopplerStudy(){
    return duplexScanDopplerStudy;
}


public String getFdp(){
    return fdp;
}


public String getAlphos(){
    return alphos;
}


public Date getDateDDimer(){
    return dateDDimer;
}


public String getdDimer(){
    return dDimer;
}


public Date getDateCReactiveProtein(){
    return dateCReactiveProtein;
}


@Override
public Long getId(){
    return id;
}


public String getSgpt(){
    return sgpt;
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


public String getFnacHistopathology(){
    return fnacHistopathology;
}


public String getAlbuminGlobulinRatio(){
    return albuminGlobulinRatio;
}


public Date getDateFdp(){
    return dateFdp;
}


public String getxRayUsg(){
    return xRayUsg;
}


}