package com.meli.weather.Interface;
public interface WeatherForecastRepository {

   public WeatherForecast findByDay(Integer day);
}