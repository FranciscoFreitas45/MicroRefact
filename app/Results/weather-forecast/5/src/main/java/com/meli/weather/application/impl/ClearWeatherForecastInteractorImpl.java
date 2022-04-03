package com.meli.weather.application.impl;
 import com.meli.weather.application.ClearWeatherForecastInteractor;
import com.meli.weather.domain.repository.WeatherForecastRepository;
import javax.inject.Singleton;
@Singleton
public class ClearWeatherForecastInteractorImpl implements ClearWeatherForecastInteractor{

 private  WeatherForecastRepository weatherForecastRepository;

public ClearWeatherForecastInteractorImpl(WeatherForecastRepository weatherForecastRepository) {
    this.weatherForecastRepository = weatherForecastRepository;
}
@Override
public void execute(){
    this.weatherForecastRepository.clearForecasts();
}


}