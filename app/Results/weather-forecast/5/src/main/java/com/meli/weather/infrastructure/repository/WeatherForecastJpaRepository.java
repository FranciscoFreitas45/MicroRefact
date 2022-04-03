package com.meli.weather.infrastructure.repository;
 import com.meli.weather.infrastructure.model.WeatherForecast;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.transaction.annotation.TransactionalAdvice;
import javax.persistence.EntityManager;
import java.util.Optional;
@Repository
public class WeatherForecastJpaRepository implements CrudRepository<WeatherForecast, Integer>{

 private  EntityManager entityMAnager;

public WeatherForecastJpaRepository(EntityManager entityMAnager) {
    this.entityMAnager = entityMAnager;
}
@TransactionalAdvice
public void updateWeatherForGreatestRainIntensity(String weather){
    entityMAnager.createNativeQuery("UPDATE weather_forecasts SET weather = ?  WHERE weather = 'RAIN' AND day IN (SELECT day FROM weather_forecasts WHERE rain_intensity in (SELECT MAX(rain_intensity) FROM weather_forecasts))").setParameter(1, weather).executeUpdate();
}


public Optional<WeatherForecast> findByDay(Integer day)


}