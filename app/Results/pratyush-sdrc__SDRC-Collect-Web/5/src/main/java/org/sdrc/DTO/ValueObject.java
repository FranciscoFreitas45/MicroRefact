package org.sdrc.DTO;
 public class ValueObject {

 private  String key;

 private  Object value;

 private  String description;

 private  String metadata;

 private  Integer nid;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://3";

public ValueObject(String key, Object value) {
    this.key = key;
    this.value = value;
}public ValueObject(int key, String value) {
    this(new Integer(key).toString(), value);
}public ValueObject(int key, String value, Integer nid) {
    this.key = String.valueOf(key);
    this.value = value;
    this.nid = nid;
}public ValueObject() {
// TODO Auto-generated constructor stub
}
public String getKey(){
    return key;
}


public Object getValue(){
    return value;
}


public String getMetadata(){
    return metadata;
}


public Integer getNid(){
    return nid;
}


public String getDescription(){
    return description;
}


public void setKey(String key){
    this.key = key;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setKey"))

.queryParam("key",key)
;
restTemplate.put(builder.toUriString(),null);
}


public void setNid(Integer nid){
    this.nid = nid;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setNid"))

.queryParam("nid",nid)
;
restTemplate.put(builder.toUriString(),null);
}


public void setDescription(String description){
    this.description = description;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setDescription"))

.queryParam("description",description)
;
restTemplate.put(builder.toUriString(),null);
}


}