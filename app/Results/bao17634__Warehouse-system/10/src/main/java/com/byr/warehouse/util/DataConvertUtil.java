package com.byr.warehouse.util;
 import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class DataConvertUtil {

 public  String value;


public void main(String[] args){
    convert(value);
}


public void convert(String s){
    String[] vallues = value.split("<cfmx>=");
    String temp1 = vallues[0];
    String temp2 = vallues[1];
    String[] value1 = temp1.replaceAll("<dymx>=", "").split("#");
    System.out.println(Arrays.asList(value1));
    String[] value22 = temp2.split("@");
    List<TempData> listValue = new ArrayList<>();
    for (String tempStr : value22) {
        tempStr = tempStr.substring(1, tempStr.length() - 1);
        String[] tmpValue = tempStr.split("#");
        TempData tempData = new TempData();
        tempData.setLocName(tmpValue[0]);
        tempData.setDate(tmpValue[1]);
        tempData.setIsValid(tmpValue[2]);
        tempData.setStatus(tmpValue[3]);
        listValue.add(tempData);
    }
    System.out.println(listValue);
}


}