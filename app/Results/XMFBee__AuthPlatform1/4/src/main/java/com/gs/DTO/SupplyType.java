package com.gs.DTO;
 public class SupplyType {

 private  String supplyTypeId;

 private  String supplyTypeName;

 private  String supplyTypeDes;

 private  String companyId;

 private  String supplyTypeStatus;

 private  Company company;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://8";


public void setSupplyTypeName(String supplyTypeName){
    this.supplyTypeName = supplyTypeName;
}


public Company getCompany(){
    return company;
}


public String getCompanyId(){
    return companyId;
}


public String getSupplyTypeId(){
    return supplyTypeId;
}


public String getSupplyTypeName(){
    return supplyTypeName;
}


public String getSupplyTypeStatus(){
    return supplyTypeStatus;
}


public void setSupplyTypeDes(String supplyTypeDes){
    this.supplyTypeDes = supplyTypeDes;
}


public String getSupplyTypeDes(){
    return supplyTypeDes;
}


public void setCompany(Company company){
    this.company = company;
}


public void setCompanyId(String companyId){
    this.companyId = companyId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCompanyId"))

.queryParam("companyId",companyId)
;
restTemplate.put(builder.toUriString(),null);
}


}