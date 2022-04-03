package com.meli.weather.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class WeatherForecastJobInteractorController {

 private WeatherForecastJobInteractor weatherforecastjobinteractor;


@PutMapping
("/execute")
public void execute(@RequestParam(name = "timeInput") ForecastTimeInput timeInput,@RequestParam(name = "errorMargin") BigDecimal errorMargin){
weatherforecastjobinteractor.execute(timeInput,errorMargin);
}


}