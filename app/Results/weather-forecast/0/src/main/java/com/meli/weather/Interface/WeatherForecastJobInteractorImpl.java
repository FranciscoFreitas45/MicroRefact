package com.meli.weather.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.meli.weather.Interface.WeatherForecastJobInteractor;
public class WeatherForecastJobInteractorImpl implements WeatherForecastJobInteractor{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public void execute(ForecastTimeInput timeInput,BigDecimal errorMargin){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/execute"))
    .queryParam("timeInput",timeInput)
    .queryParam("errorMargin",errorMargin)
;
  restTemplate.put(builder.toUriString(), null);
}


}