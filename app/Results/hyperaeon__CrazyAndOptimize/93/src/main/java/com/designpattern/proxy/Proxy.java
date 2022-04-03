package com.designpattern.proxy;
 public class Proxy implements Sourceable{

 private  Source source;

public Proxy() {
    super();
    this.source = new Source();
}
@Override
public void method(){
    before();
    source.method();
    after();
}


public void before(){
    System.out.println("Before proxy!");
}


public void after(){
    System.out.println("After Proxy!");
}


}