package com.gs.DTO;
 import java.util.Date;
import com.gs.Interface.CarColor;
import com.gs.Interface.CarPlate;
import com.gs.Interface.Company;
import com.gs.Interface.User;
public class Checkin {

 private  String checkinId;

 private  String userId;

 private  String appointmentId;

 private  String userName;

 private  String userPhone;

 private  String brandId;

 private  String colorId;

 private  String modelId;

 private  String plateId;

 private  String carPlate;

 private  Date arriveTime;

 private  Double carMileage;

 private  String carThings;

 private  String intactDegrees;

 private  String userRequests;

 private  String maintainOrFix;

 private  Date checkinCreatedTime;

 private  String companyId;

 private  String checkinStatus;

 private  String ifClearCar;

 private  Double nowOil;

 private  CarBrand brand;

 private  CarColor color;

 private  CarModel model;

 private  CarPlate plate;

 private  Company company;

 private  User user;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://3";


public String getModelId(){
    return modelId;
}


public Double getNowOil(){
    return nowOil;
}


public String getUserName(){
    return userName;
}


public String getUserRequests(){
    return userRequests;
}


public CarPlate getPlate(){
    return plate;
}


public CarModel getModel(){
    return model;
}


public String getCheckinStatus(){
    return checkinStatus;
}


public Double getCarMileage(){
    return carMileage;
}


public Company getCompany(){
    return company;
}


public String getPlateId(){
    return plateId;
}


public User getUser(){
    return user;
}


public String getCheckinId(){
    return checkinId;
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


public String getIfClearCar(){
    return ifClearCar;
}


public String getIntactDegrees(){
    return intactDegrees;
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


public Date getCheckinCreatedTime(){
    return checkinCreatedTime;
}


public String getCompanyId(){
    return companyId;
}


public String getUserPhone(){
    return userPhone;
}


public String getCarThings(){
    return carThings;
}


public String getUserId(){
    return userId;
}


public void setCheckinId(String checkinId){
    this.checkinId = checkinId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCheckinId"))

.queryParam("checkinId",checkinId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setCompanyId(String companyId){
    this.companyId = companyId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCompanyId"))

.queryParam("companyId",companyId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setCheckinStatus(String checkinStatus){
    this.checkinStatus = checkinStatus;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCheckinStatus"))

.queryParam("checkinStatus",checkinStatus)
;
restTemplate.put(builder.toUriString(),null);
}


public void setUserPhone(String userPhone){
    this.userPhone = userPhone;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setUserPhone"))

.queryParam("userPhone",userPhone)
;
restTemplate.put(builder.toUriString(),null);
}


public void setCarPlate(String carPlate){
    this.carPlate = carPlate;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCarPlate"))

.queryParam("carPlate",carPlate)
;
restTemplate.put(builder.toUriString(),null);
}


public void setUserName(String userName){
    this.userName = userName;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setUserName"))

.queryParam("userName",userName)
;
restTemplate.put(builder.toUriString(),null);
}


}