package com.evolvingreality.onleave.domain;
 import java.util.ArrayList;
import java.util.List;
public class Year {

 private  Integer year;

 private  List<Month> months;


public Integer getYear(){
    return year;
}


public void setMonths(List<Month> months){
    this.months = months;
}


public void setYear(Integer year){
    this.year = year;
}


public List<Month> getMonths(){
    return months;
}


}