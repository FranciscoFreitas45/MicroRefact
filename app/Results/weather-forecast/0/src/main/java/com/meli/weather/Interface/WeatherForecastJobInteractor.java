package com.meli.weather.Interface;
public interface WeatherForecastJobInteractor {

   public void execute(ForecastTimeInput timeInput,BigDecimal errorMargin);
}