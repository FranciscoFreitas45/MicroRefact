import java.time.ZonedDateTime;
public class QmsBomDetaileDTO {

 private  Long id;

 private  String vehicleId;

 private  String vehicleName;

 private  Integer materielId;

 private  Integer rootMaterielId;

 private  String materielCd;

 private  Integer vId;

 private  Long mId;

 private  String materielName;

 private  Integer parentMaterielID;

 private  String parentMaterielName;

 private  String productMode;

 private  String remark;

 private  String makeUser;

 private  ZonedDateTime makeTime;


public void setmId(Long mId){
    this.mId = mId;
}


public void setVehicleName(String vehicleName){
    this.vehicleName = vehicleName;
}


public Integer getMaterielId(){
    return materielId;
}


public void setRootMaterielId(Integer rootMaterielId){
    this.rootMaterielId = rootMaterielId;
}


public String getVehicleName(){
    return vehicleName;
}


public Long getId(){
    return id;
}


public String getMaterielCd(){
    return materielCd;
}


public String getMakeUser(){
    return makeUser;
}


public void setvId(Integer vId){
    this.vId = vId;
}


public void setMaterielCd(String materielCd){
    this.materielCd = materielCd;
}


public Integer getvId(){
    return vId;
}


public ZonedDateTime getMakeTime(){
    return makeTime;
}


public void setRemark(String remark){
    this.remark = remark;
}


public void setMaterielId(Integer materielId){
    this.materielId = materielId;
}


public String getProductMode(){
    return productMode;
}


public String getRemark(){
    return remark;
}


public String getVehicleId(){
    return vehicleId;
}


public void setVehicleId(String vehicleId){
    this.vehicleId = vehicleId;
}


public void setId(Long id){
    this.id = id;
}


public Long getmId(){
    return mId;
}


public void setParentMaterielID(Integer parentMaterielID){
    this.parentMaterielID = parentMaterielID;
}


public void setProductMode(String productMode){
    this.productMode = productMode;
}


public void setMakeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
}


public void setMaterielName(String materielName){
    this.materielName = materielName;
}


public Integer getRootMaterielId(){
    return rootMaterielId;
}


public Integer getParentMaterielID(){
    return parentMaterielID;
}


public void setMakeUser(String makeUser){
    this.makeUser = makeUser;
}


public String getMaterielName(){
    return materielName;
}


public String getParentMaterielName(){
    return parentMaterielName;
}


public void setParentMaterielName(String parentMaterielName){
    this.parentMaterielName = parentMaterielName;
}


}