package com.gs.DTO;
 import java.util.Date;
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


public String getModelId(){
    return modelId;
}


public String getAppoitmentStatus(){
    return appoitmentStatus;
}


public void setUserPhone(String userPhone){
    this.userPhone = userPhone;
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


public void setUserName(String userName){
    this.userName = userName;
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


public void setPlateId(String plateId){
    this.plateId = plateId;
}


public void setCarPlate(String carPlate){
    this.carPlate = carPlate;
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


public void setAppoitmentStatus(String appoitmentStatus){
    this.appoitmentStatus = appoitmentStatus;
}


public Company getCompany(){
    return company;
}


public String getCompanyId(){
    return companyId;
}


@Override
public String toString(){
    return "Appointment{" + "appointmentId='" + appointmentId + '\'' + ", userId='" + userId + '\'' + ", userName='" + userName + '\'' + ", userPhone='" + userPhone + '\'' + ", brandId='" + brandId + '\'' + ", colorId='" + colorId + '\'' + ", modelId='" + modelId + '\'' + ", plateId='" + plateId + '\'' + ", carPlate='" + carPlate + '\'' + ", arriveTime=" + arriveTime + ", maintainOrFix='" + maintainOrFix + '\'' + ", appCreatedTime=" + appCreatedTime + ", companyId='" + companyId + '\'' + ", appoitmentStatus='" + appoitmentStatus + '\'' + ", currentStatus='" + currentStatus + '\'' + '}';
}


public String getUserPhone(){
    return userPhone;
}


public String getUserId(){
    return userId;
}


public void setArriveTime(Date arriveTime){
    this.arriveTime = arriveTime;
}


}