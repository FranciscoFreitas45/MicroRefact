package com.optimize.chapter3.trip;
 public class BadHash {

 private double d;

public BadHash(double d) {
    this.d = d;
}
@Override
public int hashCode(){
    return 1;
}


}