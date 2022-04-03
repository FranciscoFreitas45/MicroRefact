package com.meli.weather.infrastructure.service;
 import com.meli.weather.domain.Planet;
import javax.inject.Singleton;
@Singleton
public class PlanetService {


public Planet fromModelToDomain(com.meli.weather.infrastructure.model.Planet planetModel){
    return new Planet(planetModel.getId(), planetModel.getName(), planetModel.getSpeed(), planetModel.getDistance());
}


}