public class QmsQualityControlDetailsDto implements Cloneable{

 private  Long id;

 private  Integer resultId;

 private  Integer controlId;

 private  String testValue;

 private  Integer bomTechnologyId;

 private  String inspectionItem;

 private  String technicalRequirement;

 private  String inspectionInstrument;

 private  String placeDiff;

 private  String resultPlaceDiff;

 private  Double standard;

 private  Double upperDeviation;

 private  Double lowerDeviation;

 private  String inspectionResultDiff;

 private  String remark;

 private  String inspectionType;

 private  String isCheckObj;

 private  String abcType;

 private  String flagStatus;

 private  String compPkid;

 private  String reserveFirst;

 private  String reserveSecond;

 private  String reserveThird;

 private  String makeUser;

 private  String modifyUser;


public void setInspectionInstrument(String inspectionInstrument){
    this.inspectionInstrument = inspectionInstrument;
}


public String getMakeUser(){
    return makeUser;
}


public void setReserveFirst(String reserveFirst){
    this.reserveFirst = reserveFirst;
}


public void setReserveThird(String reserveThird){
    this.reserveThird = reserveThird;
}


public void setResultId(Integer resultId){
    this.resultId = resultId;
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


public String getIsCheckObj(){
    return isCheckObj;
}


public void setControlId(Integer controlId){
    this.controlId = controlId;
}


public String getInspectionItem(){
    return inspectionItem;
}


public Double getStandard(){
    return standard;
}


public void setMakeUser(String makeUser){
    this.makeUser = makeUser;
}


public void setAbcType(String abcType){
    this.abcType = abcType;
}


public String getInspectionResultDiff(){
    return inspectionResultDiff;
}


public void setIsCheckObj(String isCheckObj){
    this.isCheckObj = isCheckObj;
}


public void setFlagStatus(String flagStatus){
    this.flagStatus = flagStatus;
}


public Double getLowerDeviation(){
    return lowerDeviation;
}


public void setResultPlaceDiff(String resultPlaceDiff){
    this.resultPlaceDiff = resultPlaceDiff;
}


public Double getUpperDeviation(){
    return upperDeviation;
}


public void setModifyUser(String modifyUser){
    this.modifyUser = modifyUser;
}


public Integer getControlId(){
    return controlId;
}


public void setUpperDeviation(Double upperDeviation){
    this.upperDeviation = upperDeviation;
}


public String getTestValue(){
    return testValue;
}


public Long getId(){
    return id;
}


public Integer getBomTechnologyId(){
    return bomTechnologyId;
}


public void setCompPkid(String compPkid){
    this.compPkid = compPkid;
}


public void setLowerDeviation(Double lowerDeviation){
    this.lowerDeviation = lowerDeviation;
}


public String getPlaceDiff(){
    return placeDiff;
}


public String getReserveThird(){
    return reserveThird;
}


public Integer getResultId(){
    return resultId;
}


public String getAbcType(){
    return abcType;
}


public String getReserveFirst(){
    return reserveFirst;
}


public void setReserveSecond(String reserveSecond){
    this.reserveSecond = reserveSecond;
}


public String getInspectionInstrument(){
    return inspectionInstrument;
}


public void setTestValue(String testValue){
    this.testValue = testValue;
}


public String getReserveSecond(){
    return reserveSecond;
}


public void setInspectionType(String inspectionType){
    this.inspectionType = inspectionType;
}


public void setPlaceDiff(String placeDiff){
    this.placeDiff = placeDiff;
}


public String getCompPkid(){
    return compPkid;
}


public void setInspectionResultDiff(String inspectionResultDiff){
    this.inspectionResultDiff = inspectionResultDiff;
}


public String getModifyUser(){
    return modifyUser;
}


public void setStandard(Double standard){
    this.standard = standard;
}


public void setBomTechnologyId(Integer bomTechnologyId){
    this.bomTechnologyId = bomTechnologyId;
}


public Object clone(){
    Object obj = null;
    try {
        obj = super.clone();
    } catch (CloneNotSupportedException e) {
        e.printStackTrace();
    }
    return obj;
}


public String getInspectionType(){
    return inspectionType;
}


public String getTechnicalRequirement(){
    return technicalRequirement;
}


public String getResultPlaceDiff(){
    return resultPlaceDiff;
}


public String getFlagStatus(){
    return flagStatus;
}


}