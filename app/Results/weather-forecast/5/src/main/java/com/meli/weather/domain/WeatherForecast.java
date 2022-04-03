package com.meli.weather.domain;
 import java.math.BigDecimal;
public class WeatherForecast {

 private  Integer day;

 private  WeatherEnum weather;

 private  BigDecimal rainIntensity;

public WeatherForecast(Integer day, WeatherEnum weather, BigDecimal rainIntensity) {
    this.day = day;
    this.weather = weather;
    this.rainIntensity = rainIntensity;
}
public BigDecimal rainIntensity(){
    return rainIntensity;
}


public WeatherEnum weather(){
    return weather;
}


public Integer day(){
    return day;
}


}