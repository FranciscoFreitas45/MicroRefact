package com.gs.DTO;
 import com.gs.Interface.Company;
public class AccessoriesType {

 private  String accTypeId;

 private  String accTypeName;

 private  String accTypeDes;

 private  String companyId;

 private  String accTypeStatus;

 private  Company company;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://2";


public String getAccTypeId(){
    return accTypeId;
}


public String getAccTypeStatus(){
    return accTypeStatus;
}


public String getAccTypeName(){
    return accTypeName;
}


public String getAccTypeDes(){
    return accTypeDes;
}


public Company getCompany(){
    return company;
}


public String getCompanyId(){
    return companyId;
}


public void setAccTypeDes(String accTypeDes){
    this.accTypeDes = accTypeDes;
}


public void setCompanyId(String companyId){
    this.companyId = companyId;
}


public void setAccTypeName(String accTypeName){
    this.accTypeName = accTypeName;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAccTypeName"))

.queryParam("accTypeName",accTypeName)
;
restTemplate.put(builder.toUriString(),null);
}


}