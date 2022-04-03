package com.gs.DTO;
 import java.util.Date;
import java.util.Map;
import com.gs.Interface.MaintainRecord;
import com.gs.Interface.Accessories;
public class MaterialList {

 private  String materialId;

 private  String maintainRecordId;

 private  String accId;

 private  Integer materialCount;

 private  Date materialCreatedTime;

 private  String materialStatus;

 private  Map other;

 private  MaintainRecord record;

 private  Accessories accessories;

 private  MaterialUse materialUse;

 private  MaterialReturn materialReturn;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";


public String getAccId(){
    return accId;
}


public Integer getMaterialCount(){
    return materialCount;
}


public Map getOther(){
    return other;
}


public String getMaintainRecordId(){
    return maintainRecordId;
}


public MaterialUse getMaterialUse(){
    return materialUse;
}


public String getMaterialId(){
    return materialId;
}


public Accessories getAccessories(){
    return accessories;
}


public Date getMaterialCreatedTime(){
    return materialCreatedTime;
}


public MaintainRecord getRecord(){
    return record;
}


public MaterialReturn getMaterialReturn(){
    return materialReturn;
}


public String getMaterialStatus(){
    return materialStatus;
}


public void setMaintainRecordId(String maintainRecordId){
    this.maintainRecordId = maintainRecordId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setMaintainRecordId"))

.queryParam("maintainRecordId",maintainRecordId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setAccId(String accId){
    this.accId = accId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAccId"))

.queryParam("accId",accId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setMaterialCount(Integer materialCount){
    this.materialCount = materialCount;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setMaterialCount"))

.queryParam("materialCount",materialCount)
;
restTemplate.put(builder.toUriString(),null);
}


}