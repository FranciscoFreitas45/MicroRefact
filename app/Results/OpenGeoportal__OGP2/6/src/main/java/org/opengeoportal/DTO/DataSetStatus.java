package org.opengeoportal.DTO;
 import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
public class DataSetStatus {

 private String ogpLayerId;

 private String data_type;

 private String title;

 private String link;

 private String name;

 private String state;

 private int feature_count;

 private String id;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://8";


public String getName(){
    return name;
}


public String getId(){
    return id;
}


public int getFeature_count(){
    return feature_count;
}


public String getLink(){
    return link;
}


public String getTitle(){
    return title;
}


public String getState(){
    return state;
}


public String getOgpLayerId(){
    return ogpLayerId;
}


public String getData_type(){
    return data_type;
}


public void setOgpLayerId(String ogpLayerId){
    this.ogpLayerId = ogpLayerId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setOgpLayerId"))

.queryParam("ogpLayerId",ogpLayerId)
;
restTemplate.put(builder.toUriString(),null);
}


}