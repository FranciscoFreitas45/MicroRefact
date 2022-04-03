package DTO;
 public class ComboBox {

 private  String id;

 private  String text;

 private  boolean selected;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";


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