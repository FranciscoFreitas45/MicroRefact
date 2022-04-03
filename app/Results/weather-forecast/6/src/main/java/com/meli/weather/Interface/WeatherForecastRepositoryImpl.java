package com.meli.weather.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.meli.weather.Interface.WeatherForecastRepository;
public class WeatherForecastRepositoryImpl implements WeatherForecastRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public WeatherForecast create(WeatherForecast weatherForecast){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/create"))
    .queryParam("weatherForecast",weatherForecast)
;  WeatherForecast aux = restTemplate.getForObject(builder.toUriString(), WeatherForecast.class);

 return aux;
}


public void updateHeavyRainDays(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateHeavyRainDays"))
;
  restTemplate.put(builder.toUriString(), null);
}


}