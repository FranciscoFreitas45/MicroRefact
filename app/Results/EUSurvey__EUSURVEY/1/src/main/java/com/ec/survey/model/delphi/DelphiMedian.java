package com.ec.survey.model.delphi;
 import java.util.ArrayList;
import java.util.List;
public class DelphiMedian {

 private  List<String> medianUids;

 private  double median;

 private  boolean maxDistanceExceeded;

public DelphiMedian() {
    medianUids = new ArrayList<>();
}
public void setMaxDistanceExceeded(boolean maxDistanceExceeded){
    this.maxDistanceExceeded = maxDistanceExceeded;
}


public void setMedianUids(List<String> medianUids){
    this.medianUids = medianUids;
}


public double getMedian(){
    return median;
}


public List<String> getMedianUids(){
    return medianUids;
}


public void setMedian(double median){
    this.median = median;
}


public boolean isMaxDistanceExceeded(){
    return maxDistanceExceeded;
}


}