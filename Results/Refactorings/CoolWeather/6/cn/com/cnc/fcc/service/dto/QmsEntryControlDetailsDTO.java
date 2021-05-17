public class QmsEntryControlDetailsDTO {

 private  Long id;

 private  String materielId;

 private  String materielName;

 private  String inspectionItem;

 private  String technicalRequirement;

 private  String standard;

 private  String remark;

 private  String numberCount;


public void setMaterielName(String materielName){
    this.materielName = materielName;
}


public String getInspectionItem(){
    return inspectionItem;
}


public String getStandard(){
    return standard;
}


public String getMaterielId(){
    return materielId;
}


public Long getId(){
    return id;
}


public String getMaterielName(){
    return materielName;
}


public void setNumberCount(String numberCount){
    this.numberCount = numberCount;
}


public void setStandard(String standard){
    this.standard = standard;
}


public String getNumberCount(){
    return numberCount;
}


public void setMaterielId(String materielId){
    this.materielId = materielId;
}


public void setRemark(String remark){
    this.remark = remark;
}


public String getRemark(){
    return remark;
}


public void setId(Long id){
    this.id = id;
}


public void setInspectionItem(String inspectionItem){
    this.inspectionItem = inspectionItem;
}


public void setTechnicalRequirement(String technicalRequirement){
    this.technicalRequirement = technicalRequirement;
}


public String getTechnicalRequirement(){
    return technicalRequirement;
}


}