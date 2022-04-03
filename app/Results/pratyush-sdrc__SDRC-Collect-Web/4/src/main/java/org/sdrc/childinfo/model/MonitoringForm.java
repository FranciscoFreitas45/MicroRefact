package org.sdrc.childinfo.model;
 import java.sql.Timestamp;
import java.util.List;
public class MonitoringForm {

 private  int id;

 private  String formCode;

 private  String formPath;

 private  String formType;

 private  String isActive;

 private  String lastUpdatedBy;

 private  Timestamp lastUpdatedDate;

 private  String version;

 private  Block samikshyaBlock;

 private  Cluster samikshyaCluster;

 private  District samikshyaDistrict;

 private  State samikshyaState;

 private  List<ValueObject> samikshyaMonitoringFormTrans;


public List<ValueObject> getSamikshyaMonitoringFormTrans(){
    return samikshyaMonitoringFormTrans;
}


public String getFormType(){
    return formType;
}


public void setSamikshyaState(State samikshyaState){
    this.samikshyaState = samikshyaState;
}


public void setFormPath(String formPath){
    this.formPath = formPath;
}


public void setLastUpdatedDate(Timestamp lastUpdatedDate){
    this.lastUpdatedDate = lastUpdatedDate;
}


public District getSamikshyaDistrict(){
    return samikshyaDistrict;
}


public String getFormCode(){
    return formCode;
}


public int getId(){
    return id;
}


public void setSamikshyaBlock(Block samikshyaBlock){
    this.samikshyaBlock = samikshyaBlock;
}


public State getSamikshyaState(){
    return samikshyaState;
}


public void setFormCode(String formCode){
    this.formCode = formCode;
}


public void setId(int id){
    this.id = id;
}


public void setSamikshyaMonitoringFormTrans(List<ValueObject> samikshyaMonitoringFormTrans){
    this.samikshyaMonitoringFormTrans = samikshyaMonitoringFormTrans;
}


public Timestamp getLastUpdatedDate(){
    return lastUpdatedDate;
}


public String getVersion(){
    return version;
}


public Block getSamikshyaBlock(){
    return samikshyaBlock;
}


public void setVersion(String version){
    this.version = version;
}


public void setSamikshyaCluster(Cluster samikshyaCluster){
    this.samikshyaCluster = samikshyaCluster;
}


public void setLastUpdatedBy(String lastUpdatedBy){
    this.lastUpdatedBy = lastUpdatedBy;
}


public String getFormPath(){
    return formPath;
}


public Cluster getSamikshyaCluster(){
    return samikshyaCluster;
}


public void setFormType(String formType){
    this.formType = formType;
}


public String getIsActive(){
    return isActive;
}


public String getLastUpdatedBy(){
    return lastUpdatedBy;
}


public void setIsActive(String isActive){
    this.isActive = isActive;
}


public void setSamikshyaDistrict(District samikshyaDistrict){
    this.samikshyaDistrict = samikshyaDistrict;
}


}