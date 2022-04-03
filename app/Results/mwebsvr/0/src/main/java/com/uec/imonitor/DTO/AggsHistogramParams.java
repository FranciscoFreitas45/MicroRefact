package com.uec.imonitor.DTO;
 import java.util.Date;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
public class AggsHistogramParams {

 private  String aggsName;

 private  String aggsField;

 private  DateHistogramInterval interval;

 private  String timeZone;

 private  String format;

 private  Date minDate;

 private  Date maxDate;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://3";


public DateHistogramInterval getInterval(){
    return interval;
}


public String getTimeZone(){
    return timeZone;
}


public Date getMinDate(){
    return minDate;
}


public Date getMaxDate(){
    return maxDate;
}


public String getAggsField(){
    return aggsField;
}


public String getAggsName(){
    return aggsName;
}


public String getFormat(){
    return format;
}


public void setAggsName(String aggsName){
    this.aggsName = aggsName;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAggsName"))

.queryParam("aggsName",aggsName)
;
restTemplate.put(builder.toUriString(),null);
}


public void setAggsField(String aggsField){
    this.aggsField = aggsField;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAggsField"))

.queryParam("aggsField",aggsField)
;
restTemplate.put(builder.toUriString(),null);
}


public void setInterval(DateHistogramInterval interval){
    this.interval = interval;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setInterval"))

.queryParam("interval",interval)
;
restTemplate.put(builder.toUriString(),null);
}


public void setTimeZone(String timeZone){
    this.timeZone = timeZone;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setTimeZone"))

.queryParam("timeZone",timeZone)
;
restTemplate.put(builder.toUriString(),null);
}


public void setFormat(String format){
    this.format = format;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setFormat"))

.queryParam("format",format)
;
restTemplate.put(builder.toUriString(),null);
}


}