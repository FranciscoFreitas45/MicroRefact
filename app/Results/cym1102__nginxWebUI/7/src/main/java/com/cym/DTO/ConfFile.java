package com.cym.DTO;
 public class ConfFile {

 private String name;

 private String conf;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://3";


public String getName(){
    return name;
}


public String getConf(){
    return conf;
}


public void setConf(String conf){
    this.conf = conf;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setConf"))

.queryParam("conf",conf)
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


}