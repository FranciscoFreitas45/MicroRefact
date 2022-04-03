package com.ushahidi.swiftriver.core.DTO;
 import java.util.Date;
public class TrendFilter {

 private  int page;

 private  int count;

 private  Date dateFrom;

 private  Date dateTo;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://2";


public Date getDateFrom(){
    return dateFrom;
}


public int getPage(){
    return page;
}


public Date getDateTo(){
    return dateTo;
}


public int getCount(){
    return count;
}


public void setDateTo(Date dateTo){
    this.dateTo = dateTo;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setDateTo"))

.queryParam("dateTo",dateTo)
;
restTemplate.put(builder.toUriString(),null);
}


public void setDateFrom(Date dateFrom){
    this.dateFrom = dateFrom;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setDateFrom"))

.queryParam("dateFrom",dateFrom)
;
restTemplate.put(builder.toUriString(),null);
}


public void setCount(int count){
    this.count = count;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCount"))

.queryParam("count",count)
;
restTemplate.put(builder.toUriString(),null);
}


public void setPage(int page){
    this.page = page;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setPage"))

.queryParam("page",page)
;
restTemplate.put(builder.toUriString(),null);
}


}