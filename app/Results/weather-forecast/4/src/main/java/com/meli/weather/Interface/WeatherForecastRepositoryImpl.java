package com.meli.weather.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.meli.weather.Interface.WeatherForecastRepository;
public class WeatherForecastRepositoryImpl implements WeatherForecastRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public WeatherForecast findByDay(Integer day){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByDay"))
    .queryParam("day",day)
;  WeatherForecast aux = restTemplate.getForObject(builder.toUriString(), WeatherForecast.class);

 return aux;
}


}