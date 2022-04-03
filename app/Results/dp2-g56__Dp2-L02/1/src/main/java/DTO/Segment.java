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


@NotNull
public Double getOriginLatitude(){
    return this.originLatitude;
}


@NotNull
public Double getDestinationLatitude(){
    return this.destinationLatitude;
}


public void setDestinationLatitude(Double destinationLatitude){
    this.destinationLatitude = destinationLatitude;
}


@NotNull
@Min(value = 0)
public Integer getTime(){
    return this.time;
}


@Override
public String toString(){
    return "Segment [getOriginLatitude()=" + this.getOriginLatitude() + ", getOriginLongitude()=" + this.getOriginLongitude() + ", getDestinationLatitude()=" + this.getDestinationLatitude() + ", getDestinationLongitude()=" + this.getDestinationLongitude() + ", getTime()=" + this.getTime() + "]";
}


@NotNull
public Double getOriginLongitude(){
    return this.originLongitude;
}


@NotNull
public Double getDestinationLongitude(){
    return this.destinationLongitude;
}


}