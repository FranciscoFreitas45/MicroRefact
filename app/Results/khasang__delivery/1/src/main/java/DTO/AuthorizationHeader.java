package DTO;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
public class AuthorizationHeader {

 protected  String login;

 protected  String password;

 protected  String mustUnderstand;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://5";


public String getLogin(){
    return login;
}


public String getPassword(){
    return password;
}


public String getMustUnderstand(){
    return mustUnderstand;
}


public void setLogin(String value){
    this.login = value;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setLogin"))

.queryParam("value",value)
;
restTemplate.put(builder.toUriString(),null);
}


public void setPassword(String value){
    this.password = value;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setPassword"))

.queryParam("value",value)
;
restTemplate.put(builder.toUriString(),null);
}


}