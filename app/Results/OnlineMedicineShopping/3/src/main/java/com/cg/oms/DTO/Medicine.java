package com.cg.oms.DTO;
 import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.stereotype.Component;
public class Medicine {

 private  Long medicineId;

 private  String medicineName;

 private  String medicineCategory;

 private  String medicineDescription;

 private  Double medicinePrice;

 private  String medicineManufacturerName;

 private  LocalDateTime medicineManufactureDate;

 private  LocalDateTime medicineExpiryDate;

 private  Integer medicineQuantity;

 private  String imageDir;

/**
 * creating default constructors
 */
public Medicine() {
    super();
}
public void setMedicineName(String medicineName){
    this.medicineName = medicineName;
}


public String getMedicineDescription(){
    return medicineDescription;
}


public String getMedicineManufacturerName(){
    return medicineManufacturerName;
}


public void setMedicineManufacturerName(String medicineManufacturerName){
    this.medicineManufacturerName = medicineManufacturerName;
}


public void setImageDir(String imageDir){
    this.imageDir = imageDir;
}


public String getMedicineName(){
    return medicineName;
}


public void setMedicineExpiryDate(LocalDateTime medicineExpiryDate){
    this.medicineExpiryDate = medicineExpiryDate;
}


public void setMedicinePrice(Double medicinePrice){
    this.medicinePrice = medicinePrice;
}


public void setMedicineManufactureDate(LocalDateTime medicineManufactureDate){
    this.medicineManufactureDate = medicineManufactureDate;
}


public Integer getMedicineQuantity(){
    return medicineQuantity;
}


public void setMedicineId(Long medicineId){
    this.medicineId = medicineId;
}


public void setMedicineDescription(String medicineDescription){
    this.medicineDescription = medicineDescription;
}


public LocalDateTime getMedicineManufactureDate(){
    return medicineManufactureDate;
}


public Double getMedicinePrice(){
    return medicinePrice;
}


public void setMedicineQuantity(Integer medicineQuantity){
    this.medicineQuantity = medicineQuantity;
}


public String getImageDir(){
    return imageDir;
}


public void setMedicineCategory(String medicineCategory){
    this.medicineCategory = medicineCategory;
}


@Override
public String toString(){
    return "Medicine [medicineId=" + medicineId + ", medicineName=" + medicineName + ", medicineCategory=" + medicineCategory + ", medicineDescription=" + medicineDescription + ", medicinePrice=" + medicinePrice + ", medicineManufacturerName=" + medicineManufacturerName + ", medicineManufactureDate=" + medicineManufactureDate + ", medicineExpiryDate=" + medicineExpiryDate + ", medicineQuantity=" + medicineQuantity + "]";
}


public LocalDateTime getMedicineExpiryDate(){
    return medicineExpiryDate;
}


public Long getMedicineId(){
    return medicineId;
}


public String getMedicineCategory(){
    return medicineCategory;
}


}