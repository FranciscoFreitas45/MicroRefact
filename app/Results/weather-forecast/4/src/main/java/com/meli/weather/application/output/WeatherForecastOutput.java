package com.meli.weather.application.output;
 import com.meli.weather.domain.WeatherEnum;
public class WeatherForecastOutput {

 private  WeatherEnum weather;

 private  Integer day;

public WeatherForecastOutput(WeatherEnum weather, Integer day) {
    this.weather = weather;
    this.day = day;
}
public WeatherEnum getWeather(){
    return weather;
}


public void setWeather(WeatherEnum weather){
    this.weather = weather;
}


public void setDay(Integer day){
    this.day = day;
}


public Integer getDay(){
    return day;
}


}