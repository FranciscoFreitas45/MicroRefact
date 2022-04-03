package com.meli.weather.fixtures;
 import java.lang;
import java.util;
import java.io;
import java.net;
import groovy.lang;
import groovy.util;
@groovy.transform.Trait()
public interface PlanetFixtures {


public com.meli.weather.infrastructure.model.Planet getPlanetModel()
;

public com.meli.weather.infrastructure.model.Planet getVulcanoModel()
;

public com.meli.weather.domain.Planet getBetasoideDomain()
;

public com.meli.weather.domain.Planet getPlanetDomain(java.lang.Integer speed,java.lang.Integer distance)
;

public com.meli.weather.infrastructure.model.Planet getBetasoideModel()
;

public com.meli.weather.domain.Planet getVulcanoDomain()
;

public com.meli.weather.infrastructure.model.Planet getFerengiModel()
;

public com.meli.weather.domain.Planet getFerengiDomain()
;

}