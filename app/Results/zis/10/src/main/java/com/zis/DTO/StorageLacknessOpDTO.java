package com.zis.DTO;
 import com.zis.storage.entity.StorageIoDetail;
public class StorageLacknessOpDTO extends StorageIoDetail{

 private  boolean lacknessMatchNewPos;

 private  Integer lackOutOrderId;

 private  boolean hasNext;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";


public boolean getLacknessMatchNewPos(){
    return lacknessMatchNewPos;
}


public Integer getLackOutOrderId(){
    return lackOutOrderId;
}


public boolean isHasNext(){
    return hasNext;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isHasNext"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


}