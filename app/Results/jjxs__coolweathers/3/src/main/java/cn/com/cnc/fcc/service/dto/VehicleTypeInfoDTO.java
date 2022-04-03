package cn.com.cnc.fcc.service.dto;
 import javax.persistence.Column;
public class VehicleTypeInfoDTO {

 private  Long id;

 private  String vehicleType;

 private  String vehicleTypeName;

 private  String vehicleClassName;

 private  String remark;


public void setRemark(String remark){
    this.remark = remark;
}


public void setVehicleClassName(String vehicleClassName){
    this.vehicleClassName = vehicleClassName;
}


public String getRemark(){
    return remark;
}


public void setVehicleType(String vehicleType){
    this.vehicleType = vehicleType;
}


public void setVehicleTypeName(String vehicleTypeName){
    this.vehicleTypeName = vehicleTypeName;
}


public void setId(Long id){
    this.id = id;
}


public Long getId(){
    return id;
}


public String getVehicleTypeName(){
    return vehicleTypeName;
}


public String getVehicleClassName(){
    return vehicleClassName;
}


public String getVehicleType(){
    return vehicleType;
}


}