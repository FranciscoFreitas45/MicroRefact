package DTO;
 import java.util.List;
public class Highchart {

 private  String name;

 private  String type;

 private  List data;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://2";


public String getName(){
    return name;
}


public String getType(){
    return type;
}


public List getData(){
    return data;
}


public void setName(String name){
    this.name = name;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setName"))

.queryParam("name",name)
;
restTemplate.put(builder.toUriString(),null);
}


public void setType(String type){
    this.type = type;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setType"))

.queryParam("type",type)
;
restTemplate.put(builder.toUriString(),null);
}


public void setData(List data){
    this.data = data;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setData"))

.queryParam("data",data)
;
restTemplate.put(builder.toUriString(),null);
}


}