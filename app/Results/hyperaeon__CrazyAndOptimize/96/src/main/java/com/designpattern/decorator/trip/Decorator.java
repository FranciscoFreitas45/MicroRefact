package com.designpattern.decorator.trip;
 public class Decorator implements Sourceable{

 private  Source source;

public Decorator(Source source) {
    super();
    this.source = source;
}
@Override
public void method(){
    before();
    source.method();
    after();
}


public void before(){
    System.out.println("Before method");
}


public void after(){
    System.out.println("After method");
}


}