package com.meli.weather.application;
 import com.meli.weather.application.inputs.ForecastTimeInput;
import java.math.BigDecimal;
public interface WeatherForecastJobInteractor {


public void execute(ForecastTimeInput timeInput,BigDecimal errorMargin)
;

}