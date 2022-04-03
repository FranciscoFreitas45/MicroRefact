package com.gs.DTO;
 import java.util.Date;
import com.gs.Interface.CarBrand;
import com.gs.Interface.CarColor;
import com.gs.Interface.CarModel;
import com.gs.Interface.Company;
import com.gs.Interface.User;
public class Appointment {

 private  String appointmentId;

 private  String userId;

 private  String userName;

 private  String userPhone;

 private  String brandId;

 private  String colorId;

 private  String modelId;

 private  String plateId;

 private  String carPlate;

 private  Date arriveTime;

 private  String maintainOrFix;

 private  Date appCreatedTime;

 private  String companyId;

 private  String appoitmentStatus;

 private  String currentStatus;

 private  CarBrand brand;

 private  CarColor color;

 private  CarModel model;

 private  CarPlate plate;

 private  Company company;

 private  User user;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://9";


public String getModelId(){
    return modelId;
}


public String getAppoitmentStatus(){
    return appoitmentStatus;
}


public String getPlateId(){
    return plateId;
}


public Date getAppCreatedTime(){
    return appCreatedTime;
}


public String getBrandId(){
    return brandId;
}


public String getColorId(){
    return colorId;
}


public String getCarPlate(){
    return carPlate;
}


public String getUserName(){
    return userName;
}


public CarPlate getPlate(){
    return plate;
}


public String getCurrentStatus(){
    return currentStatus;
}


public CarModel getModel(){
    return model;
}


public CarBrand getBrand(){
    return brand;
}


public String getAppointmentId(){
    return appointmentId;
}


public CarColor getColor(){
    return color;
}


public String getMaintainOrFix(){
    return maintainOrFix;
}


public Date getArriveTime(){
    return arriveTime;
}


public Company getCompany(){
    return company;
}


public String getCompanyId(){
    return companyId;
}


public String getUserPhone(){
    return userPhone;
}


public String getUserId(){
    return userId;
}


public void setCompanyId(String companyId){
    this.companyId = companyId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCompanyId"))

.queryParam("companyId",companyId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setCurrentStatus(String currentStatus){
    this.currentStatus = currentStatus;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCurrentStatus"))

.queryParam("currentStatus",currentStatus)
;
restTemplate.put(builder.toUriString(),null);
}


}