package com.designpattern.decorator.duplicate;
 public class Decorator implements Sourceable{

 private  Source source;

public Decorator(Source source) {
    super();
    this.source = source;
}
public void setSource(Source source){
    this.source = source;
}


@Override
public void method(){
    System.out.println("before decorator");
    source.method();
    System.out.println("after decorator");
}


public Source getSource(){
    return source;
}


}