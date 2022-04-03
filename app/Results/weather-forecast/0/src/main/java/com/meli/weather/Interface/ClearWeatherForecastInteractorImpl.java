package com.meli.weather.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.meli.weather.Interface.ClearWeatherForecastInteractor;
public class ClearWeatherForecastInteractorImpl implements ClearWeatherForecastInteractor{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public void execute(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/execute"))
;
  restTemplate.put(builder.toUriString(), null);
}


}