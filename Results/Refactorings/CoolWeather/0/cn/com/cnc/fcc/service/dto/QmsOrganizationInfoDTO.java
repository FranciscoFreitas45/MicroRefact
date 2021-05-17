import java.math.BigInteger;
public class QmsOrganizationInfoDTO {

 private  BigInteger id;

 private  String parentCd;

 private  String organizationCd;

 private  String organizationName;

 private  String vehicleType;

 private  String materielCd;

 private  String materielName;

 private  String parentMaterielCd;

 private  String rootMaterielCd;

 private  String rootMaterielName;


public void setMaterielName(String materielName){
    this.materielName = materielName;
}


public void setRootMaterielCd(String rootMaterielCd){
    this.rootMaterielCd = rootMaterielCd;
}


public String getParentCd(){
    return parentCd;
}


public String getOrganizationName(){
    return organizationName;
}


public void setParentMaterielCd(String parentMaterielCd){
    this.parentMaterielCd = parentMaterielCd;
}


public String getRootMaterielCd(){
    return rootMaterielCd;
}


public void setRootMaterielName(String rootMaterielName){
    this.rootMaterielName = rootMaterielName;
}


public BigInteger getId(){
    return id;
}


public String getMaterielCd(){
    return materielCd;
}


public String getMaterielName(){
    return materielName;
}


public void setOrganizationName(String organizationName){
    this.organizationName = organizationName;
}


public void setMaterielCd(String materielCd){
    this.materielCd = materielCd;
}


public void setParentCd(String parentCd){
    this.parentCd = parentCd;
}


public void setVehicleType(String vehicleType){
    this.vehicleType = vehicleType;
}


public void setId(BigInteger id){
    this.id = id;
}


public void setOrganizationCd(String organizationCd){
    this.organizationCd = organizationCd;
}


public String getOrganizationCd(){
    return organizationCd;
}


public String getRootMaterielName(){
    return rootMaterielName;
}


public String getParentMaterielCd(){
    return parentMaterielCd;
}


public String getVehicleType(){
    return vehicleType;
}


}