package DTO;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
public class OperationHistoryRequest {

 protected  String barcode;

 protected  int messageType;

 protected  String language;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";


public int getMessageType(){
    return messageType;
}


public String getBarcode(){
    return barcode;
}


public String getLanguage(){
    return language;
}


public void setBarcode(String value){
    this.barcode = value;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setBarcode"))

.queryParam("value",value)
;
restTemplate.put(builder.toUriString(),null);
}


public void setLanguage(String value){
    this.language = value;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setLanguage"))

.queryParam("value",value)
;
restTemplate.put(builder.toUriString(),null);
}


public void setMessageType(int value){
    this.messageType = value;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setMessageType"))

.queryParam("value",value)
;
restTemplate.put(builder.toUriString(),null);
}


}