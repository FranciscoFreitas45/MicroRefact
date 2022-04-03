package com.gs.DTO;
 public class ComboBox4EasyUI {

 private  String id;

 private  String text;

 private  boolean selected;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://4";


public String getText(){
    return text;
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


public void setText(String text){
    this.text = text;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setText"))

.queryParam("text",text)
;
restTemplate.put(builder.toUriString(),null);
}


}