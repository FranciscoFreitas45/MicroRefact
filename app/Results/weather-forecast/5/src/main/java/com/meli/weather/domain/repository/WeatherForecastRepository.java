package com.meli.weather.domain.repository;
 import com.meli.weather.domain.WeatherForecast;
public interface WeatherForecastRepository {


public WeatherForecast findByDay(Integer day)
;

public WeatherForecast create(WeatherForecast weatherForecast)
;

public void updateHeavyRainDays()
;

public void clearForecasts()
;

}