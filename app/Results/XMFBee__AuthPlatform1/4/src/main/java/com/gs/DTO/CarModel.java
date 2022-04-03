package com.gs.DTO;
 public class CarModel {

 private  String modelId;

 private  String modelName;

 private  String modelDes;

 private  String brandId;

 private  String modelStaus;

 private  CarBrand carBrand;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://3";


public String getModelId(){
    return modelId;
}


public CarBrand getCarBrand(){
    return carBrand;
}


public void setModelName(String modelName){
    this.modelName = modelName;
}


public void setCarBrand(CarBrand carBrand){
    this.carBrand = carBrand;
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


public void setBrandId(String brandId){
    this.brandId = brandId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setBrandId"))

.queryParam("brandId",brandId)
;
restTemplate.put(builder.toUriString(),null);
}


}