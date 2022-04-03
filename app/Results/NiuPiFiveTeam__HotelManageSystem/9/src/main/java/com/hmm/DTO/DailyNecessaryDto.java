package com.hmm.DTO;
 public class DailyNecessaryDto {

 private  String id;

 private  String show;

 private  String name;

 private  int number;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://10";


public int getNumber(){
    return number;
}


public String getName(){
    return name;
}


public String getShow(){
    return show;
}


public String getId(){
    return id;
}


public void setId(String id){
    this.id = id;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setId"))

.queryParam("id",id)
;
restTemplate.put(builder.toUriString(),null);
}


public void setName(String name){
    this.name = name;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setName"))

.queryParam("name",name)
;
restTemplate.put(builder.toUriString(),null);
}


public void setNumber(int number){
    this.number = number;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setNumber"))

.queryParam("number",number)
;
restTemplate.put(builder.toUriString(),null);
}


public void setShow(String show){
    this.show = show;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setShow"))

.queryParam("show",show)
;
restTemplate.put(builder.toUriString(),null);
}


}