package com.meli.weather.infrastructure.model;
 import com.meli.weather.domain.WeatherEnum;
import javax.persistence;
import java.math.BigDecimal;
@Entity
@Table(name = "weather_forecasts")
public class WeatherForecast {

@Id
 private  Integer day;

@Enumerated(EnumType.STRING)
 private  WeatherEnum weather;

 private  BigDecimal rainIntensity;


public BigDecimal getRainIntensity(){
    return rainIntensity;
}


public void setRainIntensity(BigDecimal rainIntensity){
    this.rainIntensity = rainIntensity;
}


public WeatherEnum getWeather(){
    return weather;
}


public void setDay(Integer day){
    this.day = day;
}


public void setWeather(WeatherEnum weather){
    this.weather = weather;
}


public Integer getDay(){
    return day;
}


}