package com.meli.weather.infrastructure.repository;
 import com.meli.weather.domain.Planet;
import com.meli.weather.domain.repository.PlanetRepository;
import com.meli.weather.infrastructure.service.PlanetService;
import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;
@Singleton
public class PlanetRepositoryImpl implements PlanetRepository{

 private  PlanetJpaRepository planetJpaRepository;

 private  PlanetService planetService;

public PlanetRepositoryImpl(PlanetJpaRepository planetJpaRepository, PlanetService planetService) {
    this.planetJpaRepository = planetJpaRepository;
    this.planetService = planetService;
}
@Override
public List<Planet> getOriginalPlanets(){
    return planetJpaRepository.firstThreePlanets().stream().map(planetService::fromModelToDomain).collect(Collectors.toList());
}


}