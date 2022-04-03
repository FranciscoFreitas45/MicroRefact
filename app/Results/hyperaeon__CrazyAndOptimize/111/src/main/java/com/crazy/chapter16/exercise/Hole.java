package com.crazy.chapter16.exercise;
 public class Hole {

 private  String holeName;

 private  Long holeLength;

public Hole(String holeName, Long holeLength) {
    this.holeName = holeName;
    this.holeLength = holeLength;
}
public String getHoleName(){
    return holeName;
}


public Long getHoleLength(){
    return holeLength;
}


public void setHoleLength(Long holeLength){
    this.holeLength = holeLength;
}


public void setHoleName(String holeName){
    this.holeName = holeName;
}


}