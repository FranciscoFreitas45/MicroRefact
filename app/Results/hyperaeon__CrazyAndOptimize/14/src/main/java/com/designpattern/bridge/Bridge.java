package com.designpattern.bridge;
 public class Bridge {

 private  Sourceable source;


public void setSource(Sourceable source){
    this.source = source;
}


public void method(){
    source.method();
}


public Sourceable getSource(){
    return source;
}


}