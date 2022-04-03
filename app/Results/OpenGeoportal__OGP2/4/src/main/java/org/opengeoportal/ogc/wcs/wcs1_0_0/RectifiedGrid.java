package org.opengeoportal.ogc.wcs.wcs1_0_0;
 import java.util.List;
public class RectifiedGrid {

 private int dimension;

 private String srsName;

 private List<String> axisName;

 private int width;

 private int height;

 private Double resx;

 private Double resy;


public Double getResx(){
    return resx;
}


public int getWidth(){
    return width;
}


public Double getResy(){
    return resy;
}


public void setWidth(int width){
    this.width = width;
}


public void setHeight(int height){
    this.height = height;
}


public void setSrsName(String srsName){
    this.srsName = srsName;
}


public String getSrsName(){
    return srsName;
}


public int getHeight(){
    return height;
}


public void setResy(Double resy){
    this.resy = resy;
}


public void setResx(Double resx){
    this.resx = resx;
}


public int getDimension(){
    return dimension;
}


public void setDimension(int dimension){
    this.dimension = dimension;
}


public void setAxisName(List<String> axisName){
    this.axisName = axisName;
}


public List<String> getAxisName(){
    return axisName;
}


}