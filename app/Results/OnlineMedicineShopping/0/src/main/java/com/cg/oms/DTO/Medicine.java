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
public String getMedicineDescription(){
    return medicineDescription;
}


public String getMedicineManufacturerName(){
    return medicineManufacturerName;
}


public String getMedicineName(){
    return medicineName;
}


public Integer getMedicineQuantity(){
    return medicineQuantity;
}


public LocalDateTime getMedicineManufactureDate(){
    return medicineManufactureDate;
}


public Double getMedicinePrice(){
    return medicinePrice;
}


public String getImageDir(){
    return imageDir;
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