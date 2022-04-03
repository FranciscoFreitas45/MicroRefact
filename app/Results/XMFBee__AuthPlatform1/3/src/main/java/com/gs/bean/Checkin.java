package com.gs.bean;
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


public String getModelId(){
    return modelId;
}


public void setModelId(String modelId){
    this.modelId = modelId;
}


public void setBrand(CarBrand brand){
    this.brand = brand;
}


public void setUserPhone(String userPhone){
    this.userPhone = userPhone;
}


public Double getNowOil(){
    return nowOil;
}


public void setMaintainOrFix(String maintainOrFix){
    this.maintainOrFix = maintainOrFix;
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


public void setUser(User user){
    this.user = user;
}


public void setCarPlate(String carPlate){
    this.carPlate = carPlate;
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


public void setCarThings(String carThings){
    this.carThings = carThings;
}


public Company getCompany(){
    return company;
}


public void setBrandId(String brandId){
    this.brandId = brandId;
}


public void setUserRequests(String userRequests){
    this.userRequests = userRequests;
}


public void setModel(CarModel model){
    this.model = model;
}


public void setAppointmentId(String appointmentId){
    this.appointmentId = appointmentId;
}


public void setIfClearCar(String ifClearCar){
    this.ifClearCar = ifClearCar;
}


public void setIntactDegrees(String intactDegrees){
    this.intactDegrees = intactDegrees;
}


public void setCheckinCreatedTime(Date checkinCreatedTime){
    this.checkinCreatedTime = checkinCreatedTime;
}


public void setCompanyId(String companyId){
    this.companyId = companyId;
}


public void setUserId(String userId){
    this.userId = userId;
}


public void setArriveTime(Date arriveTime){
    this.arriveTime = arriveTime;
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


public void setNowOil(Double nowOil){
    this.nowOil = nowOil;
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


public void setUserName(String userName){
    this.userName = userName;
}


public String getCarPlate(){
    return carPlate;
}


public void setCarMileage(Double carMileage){
    this.carMileage = carMileage;
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


public void setCheckinId(String checkinId){
    this.checkinId = checkinId;
}


public String getMaintainOrFix(){
    return maintainOrFix;
}


public Date getArriveTime(){
    return arriveTime;
}


public void setColor(CarColor color){
    this.color = color;
}


public Date getCheckinCreatedTime(){
    return checkinCreatedTime;
}


public String getCompanyId(){
    return companyId;
}


public void setCheckinStatus(String checkinStatus){
    this.checkinStatus = checkinStatus;
}


@Override
public String toString(){
    return "Checkin{" + "checkinId='" + checkinId + '\'' + ", userId='" + userId + '\'' + ", appointmentId='" + appointmentId + '\'' + ", userName='" + userName + '\'' + ", userPhone='" + userPhone + '\'' + ", brandId='" + brandId + '\'' + ", colorId='" + colorId + '\'' + ", modelId='" + modelId + '\'' + ", plateId='" + plateId + '\'' + ", carPlate='" + carPlate + '\'' + ", arriveTime=" + arriveTime + ", carMileage=" + carMileage + ", carThings='" + carThings + '\'' + ", intactDegrees='" + intactDegrees + '\'' + ", userRequests='" + userRequests + '\'' + ", maintainOrFix='" + maintainOrFix + '\'' + ", checkinCreatedTime=" + checkinCreatedTime + ", companyId='" + companyId + '\'' + ", checkinStatus='" + checkinStatus + '\'' + '}';
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


}