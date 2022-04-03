package com.meli.weather.domain;
 import java.math.BigDecimal;
import java.math.RoundingMode;
public class Location {

 private  BigDecimal xAxis;

 private  BigDecimal yAxis;

 private  Integer angle;

private Location(BigDecimal xAxis, BigDecimal yAxis, Integer angle) {
    this.xAxis = xAxis;
    this.yAxis = yAxis;
    this.angle = angle;
}
public Location standard(Integer distance){
    return new Location(BigDecimal.valueOf(distance), BigDecimal.ZERO, 0);
}


public BigDecimal yAxis(){
    return yAxis;
}


public BigDecimal xAxis(){
    return xAxis;
}


public Integer angle(){
    return angle;
}


public Location fromMovement(Integer angle,Integer radius){
    double xAxis = Math.cos(Math.toRadians(angle)) * radius;
    double yAxis = Math.sin(Math.toRadians(angle)) * radius;
    return new Location(BigDecimal.valueOf(xAxis).setScale(5, RoundingMode.DOWN), BigDecimal.valueOf(yAxis).setScale(5, RoundingMode.DOWN), angle);
}


public Location sunLocation(){
    return new Location(BigDecimal.ZERO, BigDecimal.ZERO, 0);
}


}