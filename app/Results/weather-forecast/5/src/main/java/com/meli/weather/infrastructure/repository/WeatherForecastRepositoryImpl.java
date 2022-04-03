package com.meli.weather.infrastructure.repository;
 import com.meli.weather.commons.exception.NotFoundException;
import com.meli.weather.domain.WeatherEnum;
import com.meli.weather.domain.WeatherForecast;
import com.meli.weather.domain.repository.WeatherForecastRepository;
import com.meli.weather.infrastructure.service.WeatherForecastService;
import javax.inject.Singleton;
@Singleton
public class WeatherForecastRepositoryImpl implements WeatherForecastRepository{

 private  WeatherForecastJpaRepository jpaRepository;

 private  WeatherForecastService weatherForecastService;

public WeatherForecastRepositoryImpl(WeatherForecastJpaRepository jpaRepository, WeatherForecastService weatherForecastService) {
    this.jpaRepository = jpaRepository;
    this.weatherForecastService = weatherForecastService;
}
@Override
public WeatherForecast findByDay(Integer day){
    return weatherForecastService.fromModelToDomain(jpaRepository.findByDay(day).orElseThrow(() -> new NotFoundException("No forecast found for the requested day")));
}


@Override
public WeatherForecast create(WeatherForecast weatherForecast){
    return weatherForecastService.fromModelToDomain(jpaRepository.save(weatherForecastService.fromDomainToModel(weatherForecast)));
}


@Override
public void updateHeavyRainDays(){
    jpaRepository.updateWeatherForGreatestRainIntensity(WeatherEnum.HEAVY_RAIN.name());
}


@Override
public void clearForecasts(){
    jpaRepository.deleteAll();
}


}