package edu.nju.careerbridge.DTO;
 import javax.persistence;
public class Honor {

 private  Integer id;

 private  String phone;

 private  String honorName;

 private  String level;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://7";

public Honor() {
}public Honor(String phone, String honorName, String level) {
    this.phone = phone;
    this.honorName = honorName;
    this.level = level;
}
public String getPhone(){
    return phone;
}


public String getHonorName(){
    return honorName;
}


public String getLevel(){
    return level;
}


public Integer getId(){
    return id;
}


public void setLevel(String level){
    this.level = level;
}


public void setPhone(String phone){
    this.phone = phone;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setPhone"))

.queryParam("phone",phone)
;
restTemplate.put(builder.toUriString(),null);
}


public void setHonorName(String honorName){
    this.honorName = honorName;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setHonorName"))

.queryParam("honorName",honorName)
;
restTemplate.put(builder.toUriString(),null);
}


}