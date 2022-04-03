package com.gs.bean;
 public class CarModel {

 private  String modelId;

 private  String modelName;

 private  String modelDes;

 private  String brandId;

 private  String modelStaus;

 private  CarBrand carBrand;


public String getModelId(){
    return modelId;
}


public void setModelId(String modelId){
    this.modelId = modelId;
}


public CarBrand getCarBrand(){
    return carBrand;
}


public void setModelStaus(String modelStaus){
    this.modelStaus = modelStaus;
}


public void setModelName(String modelName){
    this.modelName = modelName;
}


public void setBrandId(String brandId){
    this.brandId = brandId;
}


public void setCarBrand(CarBrand carBrand){
    this.carBrand = carBrand;
}


public void setModelDes(String modelDes){
    this.modelDes = modelDes;
}


public String getModelDes(){
    return modelDes;
}


public String getModelStaus(){
    return modelStaus;
}


public String getModelName(){
    return modelName;
}


public String getBrandId(){
    return brandId;
}


}