package com.meli.weather.api;
 import com.meli.weather.application.FindWeatherForecastByDayInteractor;
import com.meli.weather.application.output.WeatherForecastOutput;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
@Controller("/weathers")
public class WeatherController {

 private  FindWeatherForecastByDayInteractor findWeatherForecastByDay;

public WeatherController(FindWeatherForecastByDayInteractor findWeatherForecastByDay) {
    this.findWeatherForecastByDay = findWeatherForecastByDay;
}
@Get(value = "/{day}", produces = MediaType.APPLICATION_JSON)
public WeatherForecastOutput getWeatherByDay(Integer day){
    return findWeatherForecastByDay.execute(day);
}


}