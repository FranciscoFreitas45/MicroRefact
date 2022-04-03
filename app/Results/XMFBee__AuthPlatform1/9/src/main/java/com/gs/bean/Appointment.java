package com.gs.bean;
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


public String getModelId(){
    return modelId;
}


public void setModelId(String modelId){
    this.modelId = modelId;
}


public String getAppoitmentStatus(){
    return appoitmentStatus;
}


public void setBrand(CarBrand brand){
    this.brand = brand;
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


public void setColorId(String colorId){
    this.colorId = colorId;
}


public String getBrandId(){
    return brandId;
}


public String getColorId(){
    return colorId;
}


public void setMaintainOrFix(String maintainOrFix){
    this.maintainOrFix = maintainOrFix;
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


public void setPlate(CarPlate plate){
    this.plate = plate;
}


public void setPlateId(String plateId){
    this.plateId = plateId;
}


public void setCompany(Company company){
    this.company = company;
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


public void setCurrentStatus(String currentStatus){
    this.currentStatus = currentStatus;
}


public Date getArriveTime(){
    return arriveTime;
}


public void setAppCreatedTime(Date appCreatedTime){
    this.appCreatedTime = appCreatedTime;
}


public void setAppoitmentStatus(String appoitmentStatus){
    this.appoitmentStatus = appoitmentStatus;
}


public void setColor(CarColor color){
    this.color = color;
}


public Company getCompany(){
    return company;
}


public void setBrandId(String brandId){
    this.brandId = brandId;
}


public String getCompanyId(){
    return companyId;
}


public void setModel(CarModel model){
    this.model = model;
}


@Override
public String toString(){
    return "Appointment{" + "appointmentId='" + appointmentId + '\'' + ", userId='" + userId + '\'' + ", userName='" + userName + '\'' + ", userPhone='" + userPhone + '\'' + ", brandId='" + brandId + '\'' + ", colorId='" + colorId + '\'' + ", modelId='" + modelId + '\'' + ", plateId='" + plateId + '\'' + ", carPlate='" + carPlate + '\'' + ", arriveTime=" + arriveTime + ", maintainOrFix='" + maintainOrFix + '\'' + ", appCreatedTime=" + appCreatedTime + ", companyId='" + companyId + '\'' + ", appoitmentStatus='" + appoitmentStatus + '\'' + ", currentStatus='" + currentStatus + '\'' + '}';
}


public void setAppointmentId(String appointmentId){
    this.appointmentId = appointmentId;
}


public String getUserPhone(){
    return userPhone;
}


public void setCompanyId(String companyId){
    this.companyId = companyId;
}


public String getUserId(){
    return userId;
}


public void setUserId(String userId){
    this.userId = userId;
}


public void setArriveTime(Date arriveTime){
    this.arriveTime = arriveTime;
}


}