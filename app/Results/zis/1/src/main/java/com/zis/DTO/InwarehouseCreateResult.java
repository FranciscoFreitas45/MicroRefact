package com.zis.DTO;
 public class InwarehouseCreateResult {

 private  boolean isSuccess;

 private  Integer inwarehouseId;

 private  String failReason;

 private  String currentPosition;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://6";


public String getFailReason(){
    return failReason;
}


public String getCurrentPosition(){
    return currentPosition;
}


public Integer getInwarehouseId(){
    return inwarehouseId;
}


public void setSuccess(boolean isSuccess){
    this.isSuccess = isSuccess;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSuccess"))

.queryParam("isSuccess",isSuccess)
;
restTemplate.put(builder.toUriString(),null);
}


public void setInwarehouseId(Integer inwarehouseId){
    this.inwarehouseId = inwarehouseId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setInwarehouseId"))

.queryParam("inwarehouseId",inwarehouseId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setCurrentPosition(String currentPosition){
    this.currentPosition = currentPosition;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCurrentPosition"))

.queryParam("currentPosition",currentPosition)
;
restTemplate.put(builder.toUriString(),null);
}


}