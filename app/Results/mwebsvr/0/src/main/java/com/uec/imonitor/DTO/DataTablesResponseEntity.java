package com.uec.imonitor.DTO;
 import java.util.List;
public class DataTablesResponseEntity {

 private  int sEcho;

 private  long iTotalRecords;

 private  long iTotalDisplayRecords;

 private  List<T> aaData;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://10";

public DataTablesResponseEntity() {
    super();
}
public int getsEcho(){
    return sEcho;
}


public long getiTotalRecords(){
    return iTotalRecords;
}


public List<T> getAaData(){
    return aaData;
}


public long getiTotalDisplayRecords(){
    return iTotalDisplayRecords;
}


public void setiTotalDisplayRecords(long iTotalDisplayRecords){
    if (iTotalDisplayRecords > 10000) {
        this.iTotalDisplayRecords = 10000;
    } else {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setiTotalDisplayRecords"))

.queryParam("iTotalDisplayRecords",iTotalDisplayRecords)
;
restTemplate.put(builder.toUriString(),null);
}


public void setiTotalRecords(long iTotalRecords){
    this.iTotalRecords = iTotalRecords;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setiTotalRecords"))

.queryParam("iTotalRecords",iTotalRecords)
;
restTemplate.put(builder.toUriString(),null);
}


public void setsEcho(int sEcho){
    this.sEcho = sEcho;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setsEcho"))

.queryParam("sEcho",sEcho)
;
restTemplate.put(builder.toUriString(),null);
}


public void setAaData(List<T> list){
    this.aaData = list;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAaData"))

.queryParam("list",list)
;
restTemplate.put(builder.toUriString(),null);
}


}