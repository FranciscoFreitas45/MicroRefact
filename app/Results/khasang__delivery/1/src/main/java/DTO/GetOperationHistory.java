package DTO;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.russianpost.operationhistory.data.AuthorizationHeader;
import org.russianpost.operationhistory.data.OperationHistoryRequest;
import Interface.OperationHistoryRequest;
public class GetOperationHistory {

 protected  OperationHistoryRequest operationHistoryRequest;

 protected  AuthorizationHeader authorizationHeader;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://5";


public OperationHistoryRequest getOperationHistoryRequest(){
    return operationHistoryRequest;
}


public AuthorizationHeader getAuthorizationHeader(){
    return authorizationHeader;
}


public void setAuthorizationHeader(AuthorizationHeader value){
    this.authorizationHeader = value;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAuthorizationHeader"))

.queryParam("value",value)
;
restTemplate.put(builder.toUriString(),null);
}


public void setOperationHistoryRequest(OperationHistoryRequest value){
    this.operationHistoryRequest = value;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setOperationHistoryRequest"))

.queryParam("value",value)
;
restTemplate.put(builder.toUriString(),null);
}


}