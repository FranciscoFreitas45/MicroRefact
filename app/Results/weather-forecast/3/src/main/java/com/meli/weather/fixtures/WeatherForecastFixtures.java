package com.meli.weather.fixtures;
 import java.lang;
import java.util;
import java.io;
import java.net;
import groovy.lang;
import groovy.util;
@groovy.transform.Trait()
public interface WeatherForecastFixtures {


public com.meli.weather.domain.WeatherForecast getWeatherForecastDomain()
;

public com.meli.weather.infrastructure.model.WeatherForecast getWeatherForecastModel(java.lang.Integer day)
;

}