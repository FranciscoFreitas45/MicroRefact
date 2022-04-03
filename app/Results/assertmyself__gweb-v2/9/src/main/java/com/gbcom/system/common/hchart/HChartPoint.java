package com.gbcom.system.common.hchart;
 public class HChartPoint {

 private  String ponitX;

 private  String pointY;

public HChartPoint(String x, String y) {
    this.pointY = y;
    this.ponitX = x;
}
public String getPonitX(){
    return ponitX;
}


public void setPointY(String pointY){
    this.pointY = pointY;
}


public void setPonitX(String ponitX){
    this.ponitX = ponitX;
}


public String getPointY(){
    return pointY;
}


}