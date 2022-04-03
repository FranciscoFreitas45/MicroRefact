package com.meli.weather.application.impl;
 import com.meli.weather.application.FindWeatherForecastByDayInteractor;
import com.meli.weather.application.output.WeatherForecastOutput;
import com.meli.weather.domain.repository.WeatherForecastRepository;
import javax.inject.Singleton;
import com.meli.weather.Interface.WeatherForecastRepository;
import com.meli.weather.DTO.WeatherForecastRepository;
@Singleton
public class FindWeatherForecastByDayInteractorImpl implements FindWeatherForecastByDayInteractor{

 private  WeatherForecastRepository weatherForecastRepository;

public FindWeatherForecastByDayInteractorImpl(WeatherForecastRepository weatherForecastRepository) {
    this.weatherForecastRepository = weatherForecastRepository;
}
@Override
public WeatherForecastOutput execute(Integer day){
    var weatherForecast = this.weatherForecastRepository.findByDay(day);
    return new WeatherForecastOutput(weatherForecast.weather(), weatherForecast.day());
}


}