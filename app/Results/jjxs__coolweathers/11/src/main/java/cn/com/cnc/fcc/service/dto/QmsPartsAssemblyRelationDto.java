package cn.com.cnc.fcc.service.dto;
 public class QmsPartsAssemblyRelationDto implements Cloneable{

 private  String productMode;

 private  Integer materielId;

 private  String materielName;

 private  Integer assemblyCount;

 private  String madeYmd;

 private  String madeFactoryCd;

 private  String makeUser;

 private  Integer HID;

 private  Integer productionRelationId;

 private  Integer partsAssemblyRelationId;

 private  String bianHao;

 private  String actualUseLocation;

 private  String entryType;

 private  String modifyUser;


public void setMadeFactoryCd(String madeFactoryCd){
    this.madeFactoryCd = madeFactoryCd;
}


public void setHID(Integer HID){
    this.HID = HID;
}


public Integer getMaterielId(){
    return materielId;
}


public Integer getHID(){
    return HID;
}


public void setMadeYmd(String madeYmd){
    this.madeYmd = madeYmd;
}


public String getActualUseLocation(){
    return actualUseLocation;
}


public String getMakeUser(){
    return makeUser;
}


public String getEntryType(){
    return entryType;
}


public Integer getAssemblyCount(){
    return assemblyCount;
}


public String getMadeYmd(){
    return madeYmd;
}


public void setMaterielId(Integer materielId){
    this.materielId = materielId;
}


public String getProductMode(){
    return productMode;
}


public Integer getProductionRelationId(){
    return productionRelationId;
}


public void setBianHao(String bianHao){
    this.bianHao = bianHao;
}


public Integer getPartsAssemblyRelationId(){
    return partsAssemblyRelationId;
}


public String getMadeFactoryCd(){
    return madeFactoryCd;
}


public void setProductMode(String productMode){
    this.productMode = productMode;
}


public void setMaterielName(String materielName){
    this.materielName = materielName;
}


public void setEntryType(String entryType){
    this.entryType = entryType;
}


public void setMakeUser(String makeUser){
    this.makeUser = makeUser;
}


public String getBianHao(){
    return bianHao;
}


public void setActualUseLocation(String actualUseLocation){
    this.actualUseLocation = actualUseLocation;
}


public String getMaterielName(){
    return materielName;
}


public String getModifyUser(){
    return modifyUser;
}


public void setAssemblyCount(Integer assemblyCount){
    this.assemblyCount = assemblyCount;
}


public void setPartsAssemblyRelationId(Integer partsAssemblyRelationId){
    this.partsAssemblyRelationId = partsAssemblyRelationId;
}


public void setProductionRelationId(Integer productionRelationId){
    this.productionRelationId = productionRelationId;
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


public void setModifyUser(String modifyUser){
    this.modifyUser = modifyUser;
}


}