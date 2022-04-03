package com.byr.warehouse.pojo;
 import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class MaterialInfo {

@Id
// 设置主键并且设置主键为自增
@GeneratedValue(strategy = GenerationType.AUTO)
 private  int id;

 private  String materialCode;

 private  String supplyCode;

 private  String spec;

 private  String itemCodel;

 private  String productName;

 private  String productId;

 private  int unit;

 private  int minPackage;


public String getSupplyCode(){
    return supplyCode;
}


public void setSupplyCode(String supplyCode){
    this.supplyCode = supplyCode;
}


public void setProductId(String productId){
    this.productId = productId;
}


public void setProductName(String productName){
    this.productName = productName;
}


public int getId(){
    return id;
}


public void setMaterialCode(String materialCode){
    this.materialCode = materialCode;
}


public void setMinPackage(int minPackage){
    this.minPackage = minPackage;
}


public void setSpec(String spec){
    this.spec = spec;
}


public String getProductName(){
    return productName;
}


public String getProductId(){
    return productId;
}


public String getSpec(){
    return spec;
}


public String getItemCodel(){
    return itemCodel;
}


public void setUnit(int unit){
    this.unit = unit;
}


public void setId(int id){
    this.id = id;
}


public String getMaterialCode(){
    return materialCode;
}


public int getUnit(){
    return unit;
}


public int getMinPackage(){
    return minPackage;
}


public void setItemCodel(String itemCodel){
    this.itemCodel = itemCodel;
}


}