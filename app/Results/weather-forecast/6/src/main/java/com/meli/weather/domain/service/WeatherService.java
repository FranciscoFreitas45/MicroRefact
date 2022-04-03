package com.meli.weather.domain.service;
 import com.meli.weather.domain.Location;
import com.meli.weather.domain.Planet;
import com.meli.weather.domain.WeatherEnum;
import javax.inject.Singleton;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
@Singleton
public class WeatherService {


public boolean arePlanetsAlignedWithSun(BigDecimal errorMargin,Location pALocation,Location pBLocation,Location pCLocation){
    return verifyAlignment(errorMargin, Location.sunLocation(), pALocation, pBLocation, pCLocation);
}


public BigDecimal triangleArea(Location locationA,Location locationB,Location locationC){
    var determinantA = (locationA.xAxis().multiply(locationB.yAxis()).multiply(BigDecimal.ONE)).add(locationA.yAxis().multiply(locationC.xAxis()).multiply(BigDecimal.ONE)).add(locationB.xAxis().multiply(locationC.yAxis()).multiply(BigDecimal.ONE));
    var determinantB = (locationB.yAxis().multiply(locationC.xAxis()).multiply(BigDecimal.ONE)).add(locationA.xAxis().multiply(locationC.yAxis()).multiply(BigDecimal.ONE)).add(locationA.yAxis().multiply(locationB.xAxis()).multiply(BigDecimal.ONE));
    return determinantA.subtract(determinantB).abs().divide(BigDecimal.valueOf(2), RoundingMode.FLOOR);
}


public boolean arePlanetsAligned(BigDecimal errorMargin,Location pALocation,Location pBLocation,Location pCLocation){
    return verifyAlignment(errorMargin, pALocation, pBLocation, pCLocation);
}


public boolean verifyAlignment(BigDecimal errorMargin,Location locations){
    var sumX = BigDecimal.ZERO;
    var sumY = BigDecimal.ZERO;
    var sumXY = BigDecimal.ZERO;
    var sumXPow = BigDecimal.ZERO;
    var sumYPow = BigDecimal.ZERO;
    var totalLocations = BigDecimal.valueOf(locations.length);
    for (Location location : locations) {
        sumX = sumX.add(location.xAxis());
        sumY = sumY.add(location.yAxis());
        sumXY = sumXY.add(location.xAxis().multiply(location.yAxis()));
        sumXPow = sumXPow.add(location.xAxis().pow(2));
        sumYPow = sumYPow.add(location.yAxis().pow(2));
    }
    var coefficient = BigDecimal.ZERO;
    try {
        coefficient = sumXY.multiply(totalLocations).subtract(sumX.multiply(sumY)).divide(sumXPow.multiply(totalLocations).subtract(sumX.pow(2)).sqrt(MathContext.DECIMAL32).multiply(sumYPow.multiply(totalLocations).subtract(sumY.pow(2)).sqrt(MathContext.DECIMAL32)), RoundingMode.FLOOR);
    } catch (ArithmeticException ex) {
        return true;
    }
    return compareValuesWithErrorMargin(errorMargin, BigDecimal.ONE, coefficient);
}


public boolean sunInsidePlanetsTriangle(BigDecimal errorMargin,Location pALocation,Location pBLocation,Location pCLocation){
    var sunLocation = Location.sunLocation();
    var planetsTriangleArea = triangleArea(pALocation, pBLocation, pCLocation);
    var pABSunTriangle = triangleArea(pALocation, pBLocation, sunLocation);
    var pACSunTriangle = triangleArea(pALocation, pCLocation, sunLocation);
    var pBCSunTriangle = triangleArea(pBLocation, pCLocation, sunLocation);
    return compareValuesWithErrorMargin(errorMargin, planetsTriangleArea, pABSunTriangle.add(pACSunTriangle).add(pBCSunTriangle));
}


public boolean compareValuesWithErrorMargin(BigDecimal errorMargin,BigDecimal valueA,BigDecimal valueB){
    return valueA.subtract(valueB).abs().compareTo(errorMargin) <= 0;
}


public WeatherEnum forecastWeather(Planet planetA,Planet planetB,Planet planetC,BigDecimal errorMargin){
    if (arePlanetsAlignedWithSun(errorMargin, planetA.location(), planetB.location(), planetC.location())) {
        return WeatherEnum.DROUGHT;
    } else if (arePlanetsAligned(errorMargin, planetA.location(), planetB.location(), planetC.location())) {
        return WeatherEnum.PERFECT;
    } else if (sunInsidePlanetsTriangle(errorMargin, planetA.location(), planetB.location(), planetC.location())) {
        return WeatherEnum.RAIN;
    }
    return WeatherEnum.STANDARD;
}


public BigDecimal calculateRainIntensity(Planet planetA,Planet planetB,Planet planetC){
    var pABDistance = planetB.location().xAxis().subtract(planetA.location().xAxis()).pow(2).add(planetB.location().yAxis().subtract(planetA.location().yAxis()).pow(2)).sqrt(MathContext.DECIMAL32);
    var pACDistance = planetC.location().xAxis().subtract(planetA.location().xAxis()).pow(2).add(planetC.location().yAxis().subtract(planetA.location().yAxis()).pow(2)).sqrt(MathContext.DECIMAL32);
    var pBCDistance = planetC.location().xAxis().subtract(planetB.location().xAxis()).pow(2).add(planetC.location().yAxis().subtract(planetB.location().yAxis()).pow(2)).sqrt(MathContext.DECIMAL32);
    return pABDistance.add(pACDistance).add(pBCDistance);
}


}