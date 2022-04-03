package com.ec.survey.DTO;
 import java.util.ArrayList;
import java.util.List;
public class DelphiMedian {

 private  List<String> medianUids;

 private  double median;

 private  boolean maxDistanceExceeded;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";

public DelphiMedian() {
    medianUids = new ArrayList<>();
}
public double getMedian(){
    return median;
}


public List<String> getMedianUids(){
    return medianUids;
}


public void setMedian(double median){
    this.median = median;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setMedian"))

.queryParam("median",median)
;
restTemplate.put(builder.toUriString(),null);
}


public void setMaxDistanceExceeded(boolean maxDistanceExceeded){
    this.maxDistanceExceeded = maxDistanceExceeded;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setMaxDistanceExceeded"))

.queryParam("maxDistanceExceeded",maxDistanceExceeded)
;
restTemplate.put(builder.toUriString(),null);
}


}