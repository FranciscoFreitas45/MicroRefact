package com.meli.weather.application.inputs;
 import javax.validation.Valid;
import javax.validation.constraints.Positive;
public class ForecastTimeInput {

@Valid
@Positive
 private  Integer daysPerYear;

@Valid
@Positive
 private  Integer numberOfYears;

public ForecastTimeInput(Integer daysPerYear, Integer numberOfYears) {
    this.daysPerYear = daysPerYear;
    this.numberOfYears = numberOfYears;
}
public Integer daysPerYear(){
    return daysPerYear;
}


public Integer numberOfYears(){
    return numberOfYears;
}


}