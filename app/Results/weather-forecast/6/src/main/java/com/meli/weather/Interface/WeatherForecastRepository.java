package com.meli.weather.Interface;
public interface WeatherForecastRepository {

   public WeatherForecast create(WeatherForecast weatherForecast);
   public void updateHeavyRainDays();
}