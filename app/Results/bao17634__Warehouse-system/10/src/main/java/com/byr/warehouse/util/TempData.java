package com.byr.warehouse.util;
 import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class TempData {

 public  String locName;

 public  String date;

 public  String isValid;

 public  String status;


public void setIsValid(String isValid){
    this.isValid = isValid;
}


public String getLocName(){
    return locName;
}


public void setLocName(String locName){
    this.locName = locName;
}


public void setDate(String date){
    this.date = date;
}


public String getDate(){
    return date;
}


@Override
public String toString(){
    return "{" + "locName='" + locName + '\'' + ", date='" + date + '\'' + ", isValid='" + isValid + '\'' + ", status='" + status + '\'' + '}' + "\n";
}


public String getStatus(){
    return status;
}


public String getIsValid(){
    return isValid;
}


public void setStatus(String status){
    this.status = status;
}


}