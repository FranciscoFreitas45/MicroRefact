package com.meli.weather.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class WeatherForecastRepositoryController {

 private WeatherForecastRepository weatherforecastrepository;


@GetMapping
("/findByDay")
public WeatherForecast findByDay(@RequestParam(name = "day") Integer day){
  return weatherforecastrepository.findByDay(day);
}


@GetMapping
("/create")
public WeatherForecast create(@RequestParam(name = "weatherForecast") WeatherForecast weatherForecast){
  return weatherforecastrepository.create(weatherForecast);
}


@PutMapping
("/updateHeavyRainDays")
public void updateHeavyRainDays(){
weatherforecastrepository.updateHeavyRainDays();
}


}