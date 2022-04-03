package com.zis.purchase.calcMode;
 import java.util.Map;
public class CalcModeFactory {

 private  Map<Integer,CalculateModeInterface> map;


public void setMap(Map<Integer,CalculateModeInterface> map){
    CalcModeFactory.map = map;
}


public CalculateModeInterface getInstance(Integer mode){
    return map.get(mode);
}


public Map<Integer,CalculateModeInterface> getMap(){
    return map;
}


}