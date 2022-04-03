package com.meli.weather.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ClearWeatherForecastInteractorController {

 private ClearWeatherForecastInteractor clearweatherforecastinteractor;


@PutMapping
("/execute")
public void execute(){
clearweatherforecastinteractor.execute();
}


}