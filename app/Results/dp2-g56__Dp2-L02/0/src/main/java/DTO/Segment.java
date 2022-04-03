package DTO;
 import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
public class Segment extends DomainEntity{

 private  Double originLatitude;

 private  Double originLongitude;

 private  Double destinationLatitude;

 private  Double destinationLongitude;

 private  Integer time;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://2";


@NotNull
public Double getOriginLatitude(){
    return this.originLatitude;
}


@NotNull
public Double getDestinationLatitude(){
    return this.destinationLatitude;
}


@NotNull
@Min(value = 0)
public Integer getTime(){
    return this.time;
}


@NotNull
public Double getOriginLongitude(){
    return this.originLongitude;
}


@NotNull
public Double getDestinationLongitude(){
    return this.destinationLongitude;
}


public void setDestinationLatitude(Double destinationLatitude){
    this.destinationLatitude = destinationLatitude;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setDestinationLatitude"))

.queryParam("destinationLatitude",destinationLatitude)
;
restTemplate.put(builder.toUriString(),null);
}


public void setDestinationLongitude(Double destinationLongitude){
    this.destinationLongitude = destinationLongitude;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setDestinationLongitude"))

.queryParam("destinationLongitude",destinationLongitude)
;
restTemplate.put(builder.toUriString(),null);
}


public void setOriginLatitude(Double originLatitude){
    this.originLatitude = originLatitude;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setOriginLatitude"))

.queryParam("originLatitude",originLatitude)
;
restTemplate.put(builder.toUriString(),null);
}


public void setOriginLongitude(Double originLongitude){
    this.originLongitude = originLongitude;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setOriginLongitude"))

.queryParam("originLongitude",originLongitude)
;
restTemplate.put(builder.toUriString(),null);
}


public void setTime(Integer time){
    this.time = time;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setTime"))

.queryParam("time",time)
;
restTemplate.put(builder.toUriString(),null);
}


}