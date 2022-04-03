package DTO;
 import java.util.HashMap;
import java.util.Map;
public class DSDataEvent {

 public  DSData dsData;

 private  String orgi;

 private  String tablename;

 private  String batid;

 private  Map<String,Object> values;

 private  boolean failures;

 private  long times;

 private  String message;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://7";


public long getTimes(){
    return times;
}


public DSData getDSData(){
    return dsData;
}


public Map<String,Object> getValues(){
    return values;
}


public String getMessage(){
    return message;
}


public String getBatid(){
    return batid;
}


public String getTablename(){
    return tablename;
}


public String getOrgi(){
    return orgi;
}


public void setDSData(DSData dsData){
    this.dsData = dsData;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setDSData"))

.queryParam("dsData",dsData)
;
restTemplate.put(builder.toUriString(),null);
}


public void setOrgi(String orgi){
    this.orgi = orgi;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setOrgi"))

.queryParam("orgi",orgi)
;
restTemplate.put(builder.toUriString(),null);
}


}