package cn.com.cnc.fcc.domain;
 public class QmsBomTechnologyDTO {

 private  Long technologyId;

 private  Long materielId;

 private  String materielCd;

 private  String materielName;

 private  String technologyCd;

 private  String technologyName;

 private  String remark;

 private  String jhiDescribe;

 private  String operationType;

 private  String processName;


public void setTechnologyName(String technologyName){
    this.technologyName = technologyName;
}


public String getProcessName(){
    return processName;
}


public void setMaterielName(String materielName){
    this.materielName = materielName;
}


public Long getMaterielId(){
    return materielId;
}


public String getMaterielCd(){
    return materielCd;
}


public String getMaterielName(){
    return materielName;
}


public void setOperationType(String operationType){
    this.operationType = operationType;
}


public String getTechnologyCd(){
    return technologyCd;
}


public String getTechnologyName(){
    return technologyName;
}


public String getOperationType(){
    return operationType;
}


public Long getTechnologyId(){
    return technologyId;
}


public void setProcessName(String processName){
    this.processName = processName;
}


public void setMaterielCd(String materielCd){
    this.materielCd = materielCd;
}


public void setJhiDescribe(String jhiDescribe){
    this.jhiDescribe = jhiDescribe;
}


public void setMaterielId(Long materielId){
    this.materielId = materielId;
}


public void setRemark(String remark){
    this.remark = remark;
}


public String getRemark(){
    return remark;
}


public void setTechnologyCd(String technologyCd){
    this.technologyCd = technologyCd;
}


public String getJhiDescribe(){
    return jhiDescribe;
}


public void setTechnologyId(Long technologyId){
    this.technologyId = technologyId;
}


}